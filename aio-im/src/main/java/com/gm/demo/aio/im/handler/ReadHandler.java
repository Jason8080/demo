package com.gm.demo.aio.im.handler;

import com.gm.demo.aio.im.server.ServerHandler;
import com.gm.help.base.Quick;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 读取数据处理器
 *
 * @author Jason
 */
public class ReadHandler implements CompletionHandler<Integer, ByteBuffer> {

    /**
     * 单次读取数据大小
     */
    public static final int ONE_SIZE = 1024;

    /**
     * 客户端
     */
    private final AsynchronousSocketChannel channel;

    /**
     * 缓冲数据
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
        ByteBuffer buffer = ByteBuffer.allocate(ONE_SIZE);
        channel.read(buffer, buffer, this);
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        if(exc instanceof IOException){
            Quick.run(channel::close);
            ServerHandler.channels.remove(channel);
        }else {
            exc.printStackTrace();
        }

    }
}
