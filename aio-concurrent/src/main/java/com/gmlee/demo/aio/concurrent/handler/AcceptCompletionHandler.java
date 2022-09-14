package com.gmlee.demo.aio.concurrent.handler;

import com.gmlee.demo.aio.concurrent.AioServer;
import com.gmlee.demo.aio.concurrent.kit.IoKit;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 连接接收处理器.
 */
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AioServer> {

    @Override
    public void completed(AsynchronousSocketChannel channel, AioServer aioServer) {
        System.out.println("收到连接" + channel);
        try {
            aioServer.accept();
        } finally {
            IoKit.read(channel);
        }
    }

    @Override
    public void failed(Throwable exc, AioServer aioServer) {
        if(!aioServer.getChannel().isOpen()){
            System.out.println("连接中断");
            return;
        }
        exc.printStackTrace();
    }
}
