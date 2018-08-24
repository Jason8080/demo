package com.gm.demo.aio.im.handler;

import com.gm.utils.base.Logger;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 写出数据处理器
 *
 * @author Jason
 */
public class WriteHandler implements CompletionHandler<Integer, ByteBuffer> {

    /**
     * 客户端
     */
    private final AsynchronousSocketChannel channel;

    public WriteHandler(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        //如果没有发送完，就继续发送直到完成
        if (attachment.hasRemaining()) {
            channel.write(attachment, attachment, this);
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        Logger.error(exc);
    }
}
