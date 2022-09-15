package com.gmlee.demo.aio.concurrent;


import com.gmlee.demo.aio.concurrent.handler.ConnectCompletionHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 服务端.
 */
public class AioClient implements Runnable {

    private final String host;
    private final int port;
    private final int initialSize;
    private final InetSocketAddress address;

    private AsynchronousChannelGroup group;
    private AsynchronousSocketChannel channel;

    private final ExecutorService executorService;

    /**
     * Gets channel.
     *
     * @return the channel
     */
    public AsynchronousSocketChannel getChannel() {
        return channel;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public InetSocketAddress getAddress() {
        return address;
    }

    /**
     * Instantiates a new Aio server.
     */
    public AioClient() {
        this(System.getProperty("cc.io.host", "127.0.0.1"), Integer.parseInt(System.getProperty("cc.io.port", "9527")));
    }

    /**
     * Instantiates a new Aio client.
     *
     * @param host the host
     */
    public AioClient(String host) {
        this(host, Integer.parseInt(System.getProperty("cc.io.port", "9527")));
    }

    /**
     * Instantiates a new Aio client.
     *
     * @param port the port
     */
    public AioClient(int port) {
        this(System.getProperty("cc.io.host", "127.0.0.1"), port);
    }

    /**
     * Instantiates a new Aio server.
     *
     * @param host the host
     * @param port the port
     */
    public AioClient(String host, int port) {
        this(host, port, Runtime.getRuntime().availableProcessors());
    }

    /**
     * Instantiates a new Aio server.
     *
     * @param host        the host
     * @param port        the port
     * @param initialSize the initial size
     */
    public AioClient(String host, int port, int initialSize) {
        this.host = host;
        this.port = port;
        this.initialSize = initialSize;
        this.address = new InetSocketAddress(host, port);
        this.executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Integer.MAX_VALUE, 1, TimeUnit.HOURS,
                new LinkedBlockingQueue(), (run) -> new Thread(run, String.format("Client%s  ", run.hashCode())), (Runnable r, ThreadPoolExecutor p) -> System.out.println("无法建立更多的连接"));
        init();
        run();
    }

    /**
     * 初始化 .
     */
    private void init() {
        try {
            group = AsynchronousChannelGroup.withCachedThreadPool(executorService, initialSize);
            channel = AsynchronousSocketChannel.open(group);
        } catch (IOException e) {
        }
    }

    @Override
    public void run() {
        channel.connect(address, channel, new ConnectCompletionHandler());
    }
}
