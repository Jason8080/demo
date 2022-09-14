package com.gmlee.demo.aio.concurrent.handler;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 数据接收处理器.
 */
public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

    private final AsynchronousSocketChannel channel;

    /**
     * Instantiates a new Read completion handler.
     *
     * @param channel the channel
     */
    public ReadCompletionHandler(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    /**
     * 使其串行.
     *
     * @param size
     */
    @Override
    public void completed(Integer size, ByteBuffer bb) {
        try {
            System.out.println(new String(bb.array()));
        }finally {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            channel.read(buffer, buffer, this);
        }
    }

    /**
     * Failed.
     *
     * @param exc the exc
     */
    @Override
    public void failed(Throwable exc, ByteBuffer bb) {
        if(!channel.isOpen()){
            System.out.println("连接中断" + channel);
            return;
        }
        exc.printStackTrace();
    }
}
