package com.gmlee.demo.aio.concurrent.kit;

import com.gmlee.demo.aio.concurrent.handler.ReadCompletionHandler;
import com.gmlee.demo.aio.concurrent.handler.WriteCompletionHandler;
import com.gmlee.demo.aio.concurrent.lock.WriteLock;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.ReadPendingException;
import java.nio.channels.WritePendingException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Io kit.
 */
public class IoKit {

    private static final Map<AsynchronousSocketChannel, WriteLock> map = new ConcurrentHashMap();

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
        WriteLock write = get(channel);
        write.lock.lock();
        try {
            ByteBuffer bb = ByteBuffer.wrap(bytes);
            channel.write(bb, bb, new WriteCompletionHandler(write));
            write.condition.await();
        } catch (WritePendingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            write.lock.unlock();
        }
    }

    private static WriteLock get(AsynchronousSocketChannel channel) {
        WriteLock write = map.get(channel);
        if(write == null){
            write = new WriteLock(channel);
            map.put(channel, write);
        }
        return write;
    }
}
