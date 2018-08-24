package com.gm.demo.aio.im.client;

import com.gm.demo.aio.im.handler.ReadHandler;
import com.gm.help.base.Quick;
import com.gm.utils.base.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;


/**
 * AIO疑惑: client为什么不能复用?
 *
 * @author Jason
 */
public class Client {
    private static final int PORT = 8001;
    private static AsynchronousSocketChannel client = Quick.exec(AsynchronousSocketChannel::open);
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final int ONE_SIZE = 1024;
    private static ByteBuffer one = ByteBuffer.allocate(0);




    public static void main(String[] args) throws Exception {
        // 连接一次服务器
        client.connect(new InetSocketAddress("127.0.0.1", PORT)).get();
        String content = null;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        client.read(buffer, buffer, new ReadHandler(client));
        // 循环读写信息
        while (!Charset.defaultCharset().name().equals(content)) {
            content = reader.readLine();
            // 写入控制台内容
            ByteBuffer wrap = ByteBuffer.wrap(content.getBytes());
            client.write(wrap, wrap, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    //如果没有发送完，就继续发送直到完成
                    if (attachment.hasRemaining()) {
                        client.write(attachment, attachment, this);
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    Logger.error(exc);
                }
            });
            wrap.clear();
        }
    }
}