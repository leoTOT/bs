//package com.monitor.d502.serviceImpl;
// 
//import java.io.IOException;
//import java.net.InetAddress;
//import java.net.ServerSocket;
//import java.net.Socket;
// 
///**
// * 基于TCP协议的Socket通信，实现用户登录
// * 
// * 服务端
// */
// @ser
//public class ServerServiceImpl {
//    public static void main(String[] args) {
// 
//        Socket socket = null;
//        // 记录客户端数量
//        int count = 0;
//        try {
//            // 1.创建一个服务器端的 Socket，即 ServerSocket，指定绑定的端，并监听
//            ServerSocket server = new ServerSocket(8888);
//            // 2.调用 accept 方法开始监听，等待客户端连接
//            System.out.println("****服务器开始启动，等待客户端上线****");
//            // 循环监听客户端的连接
//            while (true) {
//                socket = server.accept();
//                // 创建一个新的线程
//                ServerThread st = new ServerThread(socket);
//                // 启动线程
//                st.start();
// 
//                count++;// 客户端数量增加
//                System.out.println("客户端数量为:" + count);
// 
//                InetAddress address = server.getInetAddress();
//                System.out.println("客户端IP：" + address.getHostAddress());
//            }
// 
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
// 
//    }
//}
