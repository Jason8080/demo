package com.gmlee.demo.aio.concurrent;

import com.gmlee.demo.aio.concurrent.handler.ConnectCompletionHandler;
import lombok.Data;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.*;

/**
 * The type Aio server.
 */
@Data
public class AioClient {

    private AsynchronousChannelGroup group;
    private AsynchronousSocketChannel channel;

    private final ExecutorService executorService;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        AioClient server = new AioClient();
    }

    /**
     * Instantiates a new Aio server.
     *
     * @throws IOException the io exception
     */
    public AioClient() throws IOException {
        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Integer.MAX_VALUE, 1, TimeUnit.HOURS,
                new LinkedBlockingQueue(), (run) -> new Thread(run, String.format("Client%s  ", run.hashCode())), (Runnable r, ThreadPoolExecutor p) -> System.out.println("拒绝任务"));
        group = AsynchronousChannelGroup.withCachedThreadPool(executorService, 10);
        channel = AsynchronousSocketChannel.open(group);
        connect();
    }

    /**
     * Connect.
     */
    public void connect(){
        channel.connect(new InetSocketAddress("127.0.0.1", 9527), channel, new ConnectCompletionHandler());
    }
}
