package com.gm.demo.aio.im.server;

import com.gm.demo.aio.im.handler.ReadHandler;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jason
 */
public class Handler implements CompletionHandler<AsynchronousSocketChannel, Object> {

    public static Set<AsynchronousSocketChannel> channels = new HashSet();

    @Override
    public void completed(AsynchronousSocketChannel channel, Object attachment) {
        Server.server.accept(null, this);
        channels.add(channel);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer, buffer, new ReadHandler(channel));
    }

    @Override
    public void failed(Throwable exc, Object attachment) {
        exc.printStackTrace();
    }
}
