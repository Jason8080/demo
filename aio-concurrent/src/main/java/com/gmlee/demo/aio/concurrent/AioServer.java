package com.gmlee.demo.aio.concurrent;


import com.gmlee.demo.aio.concurrent.handler.AcceptCompletionHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 服务端.
 */
public class AioServer implements Runnable {

    private final int port;
    private final int initialSize;
    private final InetSocketAddress address;

    private AsynchronousChannelGroup group;
    private AsynchronousServerSocketChannel channel;

    private final ExecutorService executorService;

    /**
     * Gets group.
     *
     * @return the group
     */
    public AsynchronousChannelGroup getGroup() {
        return group;
    }

    /**
     * Gets channel.
     *
     * @return the channel
     */
    public AsynchronousServerSocketChannel getChannel() {
        return channel;
    }

    /**
     * Instantiates a new Aio server.
     */
    public AioServer() {
        this(System.getProperty("cc.io.host", "127.0.0.1"), Integer.parseInt(System.getProperty("cc.io.port", "9527")));
    }

    /**
     * Instantiates a new Aio server.
     *
     * @param host the host
     */
    public AioServer(String host) {
        this(host, Integer.parseInt(System.getProperty("cc.io.port", "9527")));
    }

    /**
     * Instantiates a new Aio server.
     *
     * @param port the port
     */
    public AioServer(int port) {
        this(System.getProperty("cc.io.host", "127.0.0.1"), port);
    }

    /**
     * Instantiates a new Aio server.
     *
     * @param host the host
     * @param port the port
     */
    public AioServer(String host, int port) {
        this(host, port, Runtime.getRuntime().availableProcessors());
    }

    /**
     * Instantiates a new Aio server.
     *
     * @param host        the host
     * @param port        the port
     * @param initialSize the initial size
     */
    public AioServer(String host, int port, int initialSize) {
        this.port = port;
        this.initialSize = initialSize;
        this.address = new InetSocketAddress(port);
        this.executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Integer.MAX_VALUE, 1, TimeUnit.HOURS,
                new LinkedBlockingQueue(), (run) -> new Thread(run, String.format("Server%s  ", run.hashCode())), (Runnable r, ThreadPoolExecutor p) -> System.out.println("无法接收更多的连接"));
        init();
        run();
    }

    /**
     * 初始化 .
     */
    private void init() {
        try {
            group = AsynchronousChannelGroup.withCachedThreadPool(executorService, initialSize);
            channel = AsynchronousServerSocketChannel.open(group);
            channel.bind(address);
        } catch (IOException e) {
        }
    }

    @Override
    public void run() {
        channel.accept(this, new AcceptCompletionHandler());
    }
}
