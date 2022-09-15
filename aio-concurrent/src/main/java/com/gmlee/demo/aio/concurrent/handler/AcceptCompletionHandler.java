package com.gmlee.demo.aio.concurrent.handler;

import com.gmlee.demo.aio.concurrent.AioServer;
import com.gmlee.demo.aio.concurrent.kit.IoKit;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 连接接收处理器.
 */
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AioServer> {

    @Override
    public void completed(AsynchronousSocketChannel channel, AioServer aioServer) {
        System.out.println("收到连接" + channel);
        try {
            aioServer.run();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    IoKit.write(channel, "我勒个去".getBytes());
                }
            }, 1000, 1000);
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
