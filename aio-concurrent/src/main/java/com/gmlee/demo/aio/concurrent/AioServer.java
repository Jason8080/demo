package com.gmlee.demo.aio.concurrent;

import com.gmlee.demo.aio.concurrent.handler.AcceptCompletionHandler;
import lombok.Data;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.*;

/**
 * The type Aio server.
 */
@Data
public class AioServer {

    private AsynchronousChannelGroup group;
    private AsynchronousServerSocketChannel channel;

    private final ExecutorService executorService;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        AioServer server = new AioServer();
    }

    /**
     * Instantiates a new Aio server.
     *
     * @throws IOException the io exception
     */
    public AioServer() throws IOException {
        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Integer.MAX_VALUE, 1, TimeUnit.HOURS,
                new LinkedBlockingQueue(), (run) -> new Thread(run, String.format("Server%s  ", run.hashCode())), (Runnable r, ThreadPoolExecutor p) -> System.out.println("拒绝任务"));
        group = AsynchronousChannelGroup.withCachedThreadPool(executorService, 10);
        channel = AsynchronousServerSocketChannel.open(group);
        channel.bind(new InetSocketAddress("127.0.0.1", 9527));
        accept();
    }

    public void accept(){
        channel.accept(this, new AcceptCompletionHandler());
    }
}

