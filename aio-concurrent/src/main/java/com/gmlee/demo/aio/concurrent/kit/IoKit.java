package com.gmlee.demo.aio.concurrent.kit;

import com.gmlee.demo.aio.concurrent.handler.ReadCompletionHandler;
import com.gmlee.demo.aio.concurrent.handler.WriteCompletionHandler;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.ReadPendingException;
import java.nio.channels.WritePendingException;

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
        try {
            ByteBuffer bb = ByteBuffer.allocate(1024);
            channel.read(bb, bb, new ReadCompletionHandler(channel));
        } catch (ReadPendingException e) {
            e.printStackTrace();
        }
    }
    /**
     * Read.
     *
     * @param channel the channel
     */
    public synchronized static void write(AsynchronousSocketChannel channel, byte... bytes) {
        try {
            ByteBuffer bb = ByteBuffer.wrap(bytes);
            channel.write(bb, bb, new WriteCompletionHandler(channel));
        } catch (WritePendingException e) {
            e.printStackTrace();
        }
    }
}
