package com.gm.demo.aio.im.server;

import com.gm.demo.aio.im.handler.ReadHandler;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.HashSet;
import java.util.Set;

import static com.gm.demo.aio.im.handler.ReadHandler.ONE_SIZE;

/**
 * 服务端处理器
 *
 * @author Jason
 */
public class ServerHandler implements CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel> {

    public static Set<AsynchronousSocketChannel> channels = new HashSet();

    @Override
    public void completed(AsynchronousSocketChannel channel, AsynchronousServerSocketChannel server) {
        server.accept(server, this);
        channels.add(channel);
        ByteBuffer buffer = ByteBuffer.allocate(ONE_SIZE);
        channel.read(buffer, buffer, new ReadHandler(channel));
    }

    @Override
    public void failed(Throwable exc, AsynchronousServerSocketChannel server) {
        exc.printStackTrace();
    }
}
