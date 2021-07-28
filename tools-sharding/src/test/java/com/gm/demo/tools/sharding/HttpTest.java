package com.gm.demo.tools.sharding;

import cn.gmlee.tools.base.mod.HttpResult;
import cn.gmlee.tools.base.util.AsyncHttpUtil;
import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;
import org.junit.Test;

/**
 * The type User test.
 */
public class HttpTest {
    @Test
    public void test() throws Exception {
        for (int i = 0; i < 1; i++) {
            AsyncHttpUtil.get("http://pos-dev.huolala.cn:8001/actuator/health", new FutureCallback<HttpResponse>() {
                @Override
                public void completed(HttpResponse result) {
                    System.out.println(HttpResult.getInstance(result).getResponseBody());
                }

                @Override
                public void failed(Exception ex) {
                    System.out.println(HttpResult.getInstance(ex));
                }

                @Override
                public void cancelled() {
                    System.out.println("=============================");
                }
            });
        }
    }
}