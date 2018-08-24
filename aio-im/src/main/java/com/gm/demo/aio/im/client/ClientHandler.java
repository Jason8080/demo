package com.gm.demo.aio.im.client;

import com.gm.demo.aio.im.handler.ReadHandler;
import com.gm.help.base.Quick;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import static com.gm.demo.aio.im.handler.ReadHandler.ONE_SIZE;

/**
 * 客户端处理器
 *
 * @author Jason
 */
public class ClientHandler implements CompletionHandler<Void, AsynchronousSocketChannel> {
    @Override
    public void completed(Void result, AsynchronousSocketChannel client) {
        ByteBuffer buffer = ByteBuffer.allocate(ONE_SIZE);
        client.read(buffer, buffer, new ReadHandler(client));
    }

    @Override
    public void failed(Throwable exc, AsynchronousSocketChannel client) {
        if(exc instanceof IOException){
            Quick.run(client::close);
        }else {
            exc.printStackTrace();
        }
    }
}
