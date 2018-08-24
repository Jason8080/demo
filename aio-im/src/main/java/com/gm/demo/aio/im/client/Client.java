package com.gm.demo.aio.im.client;

import com.gm.demo.aio.im.handler.WriteHandler;
import com.gm.help.base.Quick;
import com.gm.utils.base.BoolUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

import static com.gm.demo.aio.im.server.Server.IP;
import static com.gm.demo.aio.im.server.Server.PORT;


/**
 * The type Client.
 *
 * @author Jason
 */
public final class Client extends Thread {

    private final static Client startup = new Client();

    private static AsynchronousSocketChannel client = getClient();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private Client() {
    }

    @Override
    public void run() {
        // 连接一次服务器
        String content = null;
        // 循环读写信息
        while (!Charset.defaultCharset().name().equals(content)) {
            content = Quick.exec(reader::readLine);
            // 写入控制台内容
            ByteBuffer wrap = ByteBuffer.wrap(content.getBytes());
            getClient().write(wrap, wrap, new WriteHandler(getClient()));
            wrap.clear();
        }
    }

    /**
     * 获取客户端.
     *
     * @return the client
     */
    public synchronized static AsynchronousSocketChannel getClient() {
        if(BoolUtils.isNull(client) || !client.isOpen() || BoolUtils.isNull(Quick.exec(client::getRemoteAddress))){
            client = Quick.exec(AsynchronousSocketChannel::open);
            client.connect(new InetSocketAddress(IP, PORT), client, new ClientHandler());
        }
        return client;
    }

    /**
     * Startup.
     */
    public static void startup() {
        startup.start();
    }
}