package com.gmlee.demo.aio.concurrent;

import com.gmlee.demo.aio.concurrent.handler.ConnectCompletionHandler;
import lombok.Data;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Executors;

/**
 * The type Aio server.
 */
@Data
public class AioClient {

    private AsynchronousChannelGroup group;
    private AsynchronousSocketChannel channel;

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
        group = AsynchronousChannelGroup.withCachedThreadPool(Executors.newCachedThreadPool(), 10);
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
