package com.gmlee.demo.aio.concurrent.handler;

import com.gmlee.demo.aio.concurrent.kit.BufferKit;
import com.gmlee.demo.aio.concurrent.kit.IoKit;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 数据接收处理器.
 */
public class WriteCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

    private final AsynchronousSocketChannel channel;

    public WriteCompletionHandler(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void completed(Integer size, ByteBuffer bb) {
        if(bb.hasRemaining()){
            IoKit.write(channel, BufferKit.get(bb, bb.remaining()));
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer bb) {
        if(!channel.isOpen()){
            System.out.println("连接中断"+channel);
            return;
        }
        exc.printStackTrace();
    }
}
