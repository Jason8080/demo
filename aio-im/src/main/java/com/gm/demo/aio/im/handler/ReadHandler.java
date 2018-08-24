package com.gm.demo.aio.im.handler;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author Jason
 */
public class ReadHandler implements CompletionHandler<Integer, ByteBuffer> {

    public static final int ONE_SIZE = 1024;

    /**
     * 客户端
     */
    private final AsynchronousSocketChannel channel;

    /**
     * 单次读取数据大小
     */
    private ByteBuffer one = ByteBuffer.allocate(0);


    public ReadHandler(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        one = ByteBuffer.allocate(one.limit()+result).put(one.array()).put(attachment);
        if(result<ONE_SIZE){
            System.out.println(new String(one.array()));
            one = ByteBuffer.allocate(0);
        }
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer, buffer, this);
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        exc.printStackTrace();
    }
}
