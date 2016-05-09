package com.example.wang.userinfoapp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {
    private boolean m_bConnect = false;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0://更新用户数
                    String strUserCount = msg.getData().getString("UserCount");
                    TextView tvUserCounter = (TextView) findViewById(R.id.textUserCounter);
                    tvUserCounter.setText(strUserCount);
                    break;
                case 1://更新连接状态
                    Button btn = (Button) findViewById(R.id.buttonConnect);
                    if (m_bConnect) {
                        btn.setText("Disconnect");
                    } else {
                        btn.setText("Connect");
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    class SocketThread extends Thread {
        private String m_strIP;
        private int m_nPort;

        public SocketThread(String strIP, int nPort) {
            m_strIP = strIP;
            m_nPort = nPort;
        }

        @Override
        public void run() {
            //创建一个Socket对象，指定服务器端的IP地址和端口号
            try {
                Socket socket = new Socket(m_strIP, m_nPort);
                BufferedReader readBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //读取发来服务器信息
                String line;
                String buffer = "";
                while (m_bConnect) {
                    if ((line = readBuffer.readLine()) != null) {
                        Bundle infoBundle = new Bundle();
                        infoBundle.putString("UserCount", line);
                        Message msgInfo = new Message();
                        msgInfo.what = 0;
                        msgInfo.setData(infoBundle);
                        mHandler.sendMessage(msgInfo);
                    }
                }
                socket.close();
            } catch (IOException e) {
                m_bConnect = false;
                Message msgInfo = new Message();
                msgInfo.what = 1;
                mHandler.sendMessage(msgInfo);
                e.printStackTrace();
            }
        }
    }

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
            EditText etServerIP = (EditText) findViewById(R.id.editServerIP);
            String strServerIP = etServerIP.getText().toString();
            EditText etServerPort = (EditText) findViewById(R.id.editServerPort);
            String strServerPort = etServerPort.getText().toString();
            int nServerPort = Integer.parseInt(strServerPort);
            SocketThread st = new SocketThread(strServerIP, nServerPort);
            st.start();
        } else {
            btn.setText("Connect");
        }
    }
}
