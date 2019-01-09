package com.monitor.d502.serviceImpl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.monitor.d502.configuration.GetHttpSessionConfigurator;
import com.monitor.d502.controller.adminController;
import com.monitor.d502.model.Record;
import com.monitor.d502.model.User;
import com.monitor.d502.model.repository.RecordRepository;
import com.monitor.d502.service.AdminUserService;

//@ServerEndpoint("/websocket/{user}")
@ServerEndpoint(value = "/websocket", configurator = GetHttpSessionConfigurator.class)

@Component
public class WebSocketServer {
	private static ApplicationContext applicationContext;
	private AdminUserServiceImpl wsService;
	private HttpServletRequest httpServletRequest;

	public static void setApplicationContext(ApplicationContext applicationContext) {
		WebSocketServer.applicationContext = applicationContext;
	}

	@Autowired
	AdminUserServiceImpl adminUserServiceImpl;
	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;
	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
	private static String diid = "";
	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		this.session = session;
		webSocketSet.add(this); // 加入set中
		addOnlineCount(); // 在线数加1
		System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
		try {
			wsService = applicationContext.getBean(AdminUserServiceImpl.class);
			HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
			String did = (String) httpSession.getAttribute("did");
			diid=did;
			Record record = wsService.getWebsocket(did);
			sendMessage("连接成功" + "/" + "2018-7-08 15:14:02" + "/" + record.getTemperature().toString());
		} catch (IOException e) {
			System.out.println("websocket IO异常");
		}
	}
	// //连接打开时执行
	// @OnOpen
	// public void onOpen(@PathParam("user") String user, Session session) {
	// currentUser = user;
	// System.out.println("Connected ... " + session.getId());
	// }

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this); // 从set中删除
		subOnlineCount(); // 在线数减1
		System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message
	 *            客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(String message, Session session,EndpointConfig config) {
		
		try {
			Date date = new Date();
			wsService = applicationContext.getBean(AdminUserServiceImpl.class);
			Record record = wsService.getWebsocket(diid);
			Date da = record.getReport_time();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(da);
			sendMessage(message + "/" + dateString + "/" + record.getTemperature().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}

	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}

	public void sendDate(Date date) throws IOException {
		String da = date.toString();
		this.session.getBasicRemote().sendText(da);
	}

	/**
	 * 群发自定义消息
	 */
	public static void sendInfo(String message) throws IOException {
		System.out.println(message);
		for (WebSocketServer item : webSocketSet) {
			try {
				item.sendMessage(message);

			} catch (IOException e) {
				continue;
			}
		}
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocketServer.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocketServer.onlineCount--;
	}

}
