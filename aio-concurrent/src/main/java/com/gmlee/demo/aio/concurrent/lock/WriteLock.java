package com.gmlee.demo.aio.concurrent.lock;

import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The type Write lock.
 */
public class WriteLock {

    public final ReentrantLock lock;
    public final Condition condition;
    public final AsynchronousSocketChannel channel;

    /**
     * Instantiates a new Write lock.
     * @param channel
     */
    public WriteLock(AsynchronousSocketChannel channel) {
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
        this.channel = channel;
    }
}
