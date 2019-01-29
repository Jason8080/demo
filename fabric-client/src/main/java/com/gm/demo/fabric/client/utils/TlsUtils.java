package com.gm.demo.fabric.client.utils;

import com.gm.demo.fabric.client.config.order.Tls;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TlsUtils {

    /**
     * 不配置这个会抛出异常
     * <p>
     * grpc: Server.Serve failed to complete security handshake from "192.168.1.223:53182": tls:
     * first record does not look like a TLS handshake
     *
     * @return
     * @throws Exception
     */
    public static Properties getTlsProperties(Tls tls) throws Exception {
        String name = tls.getName();
        String crt = tls.getTls();
        Properties ret = new Properties();
        ret.setProperty("hostnameOverride", name);
        File cert = new File(TlsUtils.class.getResource(crt).toURI());
        ret.setProperty("sslProvider", "openSSL");
        ret.setProperty("negotiationType", "TLS");
        ret.setProperty("pemFile", cert.getAbsolutePath());
        // 超时配置
        ret.put("grpc.NettyChannelBuilderOption.keepAliveTime", new Object[]{5L, TimeUnit.MINUTES});
        ret.put("grpc.NettyChannelBuilderOption.keepAliveTimeout", new Object[]{8L, TimeUnit.MINUTES});
        return ret;
    }
}
