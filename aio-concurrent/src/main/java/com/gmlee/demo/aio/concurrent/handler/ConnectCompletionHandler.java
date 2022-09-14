package com.gmlee.demo.aio.concurrent.handler;

import com.gmlee.demo.aio.concurrent.kit.IoKit;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 连接接收处理器.
 */
public class ConnectCompletionHandler implements CompletionHandler<Void, AsynchronousSocketChannel> {

    @Override
    public void completed(Void v, AsynchronousSocketChannel channel) {
        System.out.println("连接成功" + channel);
        IoKit.read(channel);
    }

    @Override
    public void failed(Throwable exc, AsynchronousSocketChannel channel) {
        exc.printStackTrace();
    }
}
