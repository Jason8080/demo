package com.gmlee.demo.aio.concurrent.kit;

import com.gmlee.demo.aio.concurrent.handler.ReadCompletionHandler;
import com.gmlee.demo.aio.concurrent.handler.WriteCompletionHandler;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

/**
 * The type Io kit.
 */
public class IoKit {
    /**
     * Read.
     *
     * @param channel the channel
     */
    public synchronized static void read(AsynchronousSocketChannel channel) {
        ByteBuffer bb = ByteBuffer.allocate(1024);
        channel.read(bb, bb, new ReadCompletionHandler(channel));
    }
    /**
     * Read.
     *
     * @param channel the channel
     */
    public synchronized static void write(AsynchronousSocketChannel channel, byte... bytes) {
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        channel.read(bb, bb, new WriteCompletionHandler(channel));
    }
}
