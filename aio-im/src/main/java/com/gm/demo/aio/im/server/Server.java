package com.gm.demo.aio.im.server;

import com.gm.demo.aio.im.handler.WriteHandler;
import com.gm.help.base.Quick;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * The type Server.
 *
 * @author Jason
 */
public final class Server extends Thread {

    private final static Server startup = new Server();

    /**
     * The constant server.
     */
    private static final AsynchronousServerSocketChannel server = Quick.exec(() -> AsynchronousServerSocketChannel.open());
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    /**
     * The constant PORT.
     */
    public final static int PORT = 8001;
    /**
     * The constant IP.
     */
    public final static String IP = "127.0.0.1";

    private Server() {
    }

    @Override
    public void run() {
        Quick.run(() -> server.bind(new InetSocketAddress(IP, PORT)));
        server.accept(server, new ServerHandler());
        String content = null;
        // 循环读写信息
        while (!Charset.defaultCharset().name().equals(content)) {
            content = Quick.exec(reader::readLine);
            // 写入控制台内容
            ByteBuffer wrap = ByteBuffer.wrap(content.getBytes());
            Iterator<AsynchronousSocketChannel> it = ServerHandler.channels.iterator();
            if(it.hasNext()) {
                AsynchronousSocketChannel channel = it.next();
                channel.write(wrap, wrap, new WriteHandler(channel));
                wrap.clear();
            }
        }
    }

    /**
     * Startup.
     */
    public static void startup() {
        startup.start();
    }
}
