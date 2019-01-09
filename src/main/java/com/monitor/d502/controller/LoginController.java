package com.monitor.d502.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monitor.d502.model.Record;
import com.monitor.d502.model.User;
import com.monitor.d502.model.repository.RecordRepository;
import com.monitor.d502.model.repository.UserRepository;
import com.monitor.d502.uitl.NetResult;
import com.monitor.d502.uitl.ValidatePin;



@RestController
public class LoginController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RecordRepository recordRepository;
	@Autowired
	NetResult netResult;
	@RequestMapping(value = "/getValidatePIN")
	public void getValidatePIN(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 禁止缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// 指定生成的响应是图片
		response.setContentType("image/jpeg");
		int width = 200;
		int height = 60;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // 创建BufferedImage类的对象
		Graphics g = image.getGraphics(); // 创建Graphics类的对象
		Graphics2D g2d = (Graphics2D) g; // 通过Graphics类的对象创建一个Graphics2D类的对象
		Random random = new Random(); // 实例化一个Random对象
		Font mFont = new Font("华文宋体", Font.BOLD, 50); // 通过Font构造字体
		g.setColor(ValidatePin.GetRandColor(200, 250)); // 改变图形的当前颜色为随机生成的颜色
		g.fillRect(0, 0, width, height); // 绘制一个填色矩形

		// 画一条折线
		BasicStroke bs = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL); // 创建一个供画笔选择线条粗细的对象
		g2d.setStroke(bs); // 改变线条的粗细
		g.setColor(Color.DARK_GRAY); // 设置当前颜色为预定义颜色中的深灰色
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];
		for (int j = 0; j < 3; j++) {
			xPoints[j] = random.nextInt(width - 1);
			yPoints[j] = random.nextInt(height - 1);
		}
		g.drawPolyline(xPoints, yPoints, 3);
		// 生成并输出随机的验证文字
		g.setFont(mFont);
		String sRand = "";
		int itmp = 0;
		for (int i = 0; i < 4; i++) {
			if (random.nextInt(2) == 1) {
				itmp = random.nextInt(26) + 65; // 生成A~Z的字母
			} else {
				itmp = random.nextInt(10) + 48; // 生成0~9的数字
			}
			char ctmp = (char) itmp;
			sRand += String.valueOf(ctmp);
			Color color = new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110));
			g.setColor(color);
			/**** 随机缩放文字并将文字旋转指定角度 **/
			// 将文字旋转指定角度
			Graphics2D g2d_word = (Graphics2D) g;
			AffineTransform trans = new AffineTransform();
			// trans.rotate(random.nextInt(45)*3.14/180,15*i+10,7);
			// trans.rotate(random.nextInt(45)*3.14/180,0,7);
			// 缩放文字
			float scaleSize = random.nextFloat() + 0.8f;
			if (scaleSize > 1.1f)
				scaleSize = 1f;
			trans.scale(scaleSize, scaleSize);
			g2d_word.setTransform(trans);
			/************************/
			// g.drawString(String.valueOf(ctmp),30*i+40,16);
			// g.drawString(String.valueOf(ctmp),30*i+,36);
			if (i == 0) {
				g.drawString(String.valueOf(ctmp), 40, 46);
			} else {
				g.drawString(String.valueOf(ctmp), 40 * i + 30, 46);
			}

		}
		// 将生成的验证码保存到Session中
		HttpSession session = request.getSession(true);
		session.setAttribute(ValidatePin.SESSION_CHECK_CODE, sRand);
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}
	@RequestMapping(value="/longin",method = RequestMethod.POST)
	public NetResult login(@RequestParam String loginName,@RequestParam String loginPass,@RequestParam String pin,HttpSession session,HttpServletRequest request){
		User user = userRepository.findUserByUserName(loginName);
		if (!ValidatePin.ValidatePIN(session, pin)) {
			netResult.status = 1;
			netResult.result = "验证码错误";
			return netResult;
		}
		if (user==null) {
			netResult.status=-1;
			netResult.result="用户名不存在";
			return netResult;
		}
		if(user.isPasswordCorrect(loginPass)){
			netResult.status=0;
			netResult.result="登陆成功";
			session.setAttribute("uesrid", loginName);
			return netResult;
		}else{
			netResult.status=2;
			netResult.result="账号密码错误";
			return netResult;
		}
				
	}

	
	@RequestMapping(value="/search")
	public String admiUpdatePassword(String id, String t,String h){
		if (id!=null&&t!=null&&h!=null) {
			Date date=new Date();
			Double temp=new Double(t);
			Double tumi=new Double(h);
			Record record=new Record();
			record.setDid(id);
			record.setTemperature(temp);
			record.setHuminity(tumi);
			record.setReport_time(date);
			System.out.println(id);
			Record save = recordRepository.save(record);
			return "1";
		}
		return null;
		
	}
	
}
