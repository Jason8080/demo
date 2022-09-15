package com.gmlee.demo.aio.concurrent;

import com.gmlee.demo.aio.concurrent.kit.IoKit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class TestClient {

    public static void main(String[] args) throws Exception {
        AioClient aioClient = new AioClient();
        Thread.sleep(1000);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i< 10000; i++){
            final int j = i;
            threads.add(new Thread(() -> IoKit.write(aioClient.getChannel(), ("你好"+j).getBytes())));
        }
        threads.forEach(t -> t.start());
        new CountDownLatch(1).await();
    }
}
