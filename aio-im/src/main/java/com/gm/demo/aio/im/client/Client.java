package com.gm.demo.aio.im.client;

import com.gm.demo.aio.im.handler.WriteHandler;
import com.gm.help.base.Quick;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;


/**
 *
 * @author Jason
 */
public class Client extends Thread {
    private static final int PORT = 8001;
    private static AsynchronousSocketChannel client = Quick.exec(AsynchronousSocketChannel::open);
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void run() {
        // 连接一次服务器
        client.connect(new InetSocketAddress("127.0.0.1", PORT), client, new ClientHandler());
        String content = null;
        // 循环读写信息
        while (!Charset.defaultCharset().name().equals(content)) {
            content = Quick.exec(reader::readLine);
            // 写入控制台内容
            ByteBuffer wrap = ByteBuffer.wrap(content.getBytes());
            client.write(wrap, wrap, new WriteHandler(client));
            wrap.clear();
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Client().start();
    }
}