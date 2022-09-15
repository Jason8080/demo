package com.gmlee.demo.aio.concurrent.handler;

import com.gmlee.demo.aio.concurrent.kit.BufferKit;
import com.gmlee.demo.aio.concurrent.kit.IoKit;
import com.gmlee.demo.aio.concurrent.lock.WriteLock;

import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;

/**
 * 数据接收处理器.
 */
public class WriteCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

    private final WriteLock write;

    public WriteCompletionHandler(WriteLock write) {
        this.write = write;
    }

    @Override
    public void completed(Integer size, ByteBuffer bb) {
        write.lock.lock();
        try {
            write.condition.signal();
            if(bb.hasRemaining()){
                IoKit.write(write.channel, BufferKit.get(bb, bb.remaining()));
            }
        } finally {
            write.lock.unlock();
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer bb) {
        if(!write.channel.isOpen()){
            System.out.println("连接中断"+ write.channel);
            return;
        }
        exc.printStackTrace();
    }
}
