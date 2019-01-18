package com.gm.demo.fabric.manual.service;

import com.gm.demo.fabric.manual.config.fabric.FooConfig;
import com.gm.demo.fabric.manual.config.fabric.Order1Config;
import com.gm.demo.fabric.manual.config.fabric.Peer0Config;
import com.gm.demo.fabric.manual.model.SampleOrg;
import com.gm.demo.fabric.manual.model.SampleUser;
import org.hyperledger.fabric.sdk.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author Jason
 */
@Service
public class FabricServiceImpl {

    @Autowired
    HFClient client;

    @Autowired
    SampleUser admin;

    @Autowired
    @Qualifier("org1")
    SampleOrg org1;

    @Autowired
    @Qualifier("org2")
    SampleOrg org2;

    @Autowired
    FooConfig fooConfig;

    @Autowired
    Order1Config order1Config;

    @Autowired
    Peer0Config peer0Config;

    public void handler() {
        try {
            Channel channel = createChannel(fooConfig.getName());
        }catch (Exception e){

        }
    }

    private Channel createChannel(String name) throws Exception {
        client.setUserContext(org1.getPeerAdmin());
        Class<? extends FabricServiceImpl> clazz = this.getClass();
        File fTx = new File(clazz.getResource(fooConfig.getTx()).toURI());
        ChannelConfiguration configuration = new ChannelConfiguration(fTx);
        byte[] signature = client.getChannelConfigurationSignature
                (configuration, org1.getPeerAdmin());
        // 创建共识
        Orderer order = client.newOrderer(order1Config.getName(), order1Config.getLoc()
                , getOrderProperties());
        // 创建通道
        Channel channel = client.newChannel(name, order, configuration, signature);
        // 初始化
        channel.initialize();
        // OK
        return channel;
    }

    /**
     * 不配置这个会抛出异常
     * <p>
     * grpc: Server.Serve failed to complete security handshake from "192.168.1.223:53182": tls:
     * first record does not look like a TLS handshake
     *
     * @return
     * @throws Exception
     */
    private Properties getOrderProperties() throws Exception {
        String name = order1Config.getName();
        String tlsServer = order1Config.getTlsServer();
        Properties ret = new Properties();
        ret.setProperty("hostnameOverride", name);
        Class<? extends FabricServiceImpl> clazz = this.getClass();
        File cert = new File(clazz.getResource(tlsServer).toURI());
        ret.setProperty("sslProvider", "openSSL");
        ret.setProperty("negotiationType", "TLS");
        ret.setProperty("pemFile", cert.getAbsolutePath());
        // 超时配置
        ret.put("grpc.NettyChannelBuilderOption.keepAliveTime", new Object[]{5L, TimeUnit.MINUTES});
        ret.put("grpc.NettyChannelBuilderOption.keepAliveTimeout", new Object[]{8L, TimeUnit
                .SECONDS});
        return ret;
    }
}
