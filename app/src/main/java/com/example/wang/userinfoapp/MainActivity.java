package com.example.wang.userinfoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private boolean m_bConnect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ClickConnect(View view) {
        Button btn = (Button) findViewById(R.id.buttonConnect);
        m_bConnect = !m_bConnect;
        if (m_bConnect) {
            btn.setText("Disconnect");
//            //创建一个Socket对象，指定服务器端的IP地址和端口号
//            String strIP =
//            Socket socket = new Socket("192.168.1.104", 4567);
//
//            //使用InputStream读取硬盘上的文件
//
//            InputStream inputStream = new
//
//                    FileInputStream("f://file/words.txt");
//
//            //从Socket当中得到OutputStream
//
//            OutputStream outputStream = socket.getOutputStream();
//
//            byte buffer[] = new byte[4 * 1024];
//
//            int temp = 0;
//
//            //将InputStream当中的数据取出，并写入到OutputStream当中
//
//            while ((temp = inputStream.read(buffer)) != -1) {
//
//                outputStream.write(buffer, 0, temp);
//
//            }
//
//            outputStream.flush();
//
//        }
    }
    else {
        btn.setText("Connect");
//        //声明一个ServerSocket对象
//
//        ServerSocket serverSocket = null;
//
//        try {
//
//            //创建一个ServerSocket对象，并让这个Socket在4567端口监听
//
//            serverSocket = new ServerSocket(4567);
//
//            //调用ServerSocket的accept()方法，接受客户端所发送的请求，
//
//            //如果客户端没有发送数据，那么该线程就停滞不继续
//
//            Socket socket = serverSocket.accept();
//
//            //从Socket当中得到InputStream对象
//
//            InputStream inputStream = socket.getInputStream();
//
//            byte buffer [] = new byte[1024*4];
//
//            int temp = 0;
//
//            //从InputStream当中读取客户端所发送的数据
//
//            while((temp = inputStream.read(buffer)) != -1){
//
//                System.out.println(new String(buffer,0,temp));
//
//            }
//
//        } catch (IOException e) {
//
//            // TODO Auto-generated catch block
//
//            e.printStackTrace();
//
//        }
//
//        serverSocket.close();
//
//    }
}
}
}
