package com.gmlee.demo.aio.concurrent;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class TestServer {

    public static void main(String[] args) throws Exception {
        AioServer aioServer = new AioServer();
        new CountDownLatch(1).await();
    }
}
