package com.gm.demo.fabric.manual.service;

import com.gm.demo.fabric.manual.config.fabric.Order1Config;
import com.gm.demo.fabric.manual.config.fabric.Peer0Config;
import com.gm.demo.fabric.manual.model.SampleOrg;
import com.gm.demo.fabric.manual.model.SampleUser;
import com.gm.help.base.Quick;
import org.hyperledger.fabric.sdk.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;

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
    Order1Config order1Config;

    @Autowired
    Peer0Config peer0Config;

    public void handler(String name) {
        Quick.run(x->createChannel(name));
    }

    private void createChannel(String name) throws Exception {
        client.setUserContext(org1.getPeerAdmin());
        Class<? extends FabricServiceImpl> clazz = this.getClass();
        File fTx = new File(clazz.getResource("/channel/foo.tx").toURI());
        ChannelConfiguration configuration = new ChannelConfiguration(fTx);
        byte[] signature = client.getChannelConfigurationSignature
                (configuration, org1.getPeerAdmin());
        // 创建共识
        Orderer order = client.newOrderer(order1Config.getName(), order1Config.getLoc());
        // 创建通道
        Channel channel = client.newChannel(name, order, configuration, signature);
        // 创建Peer
        Peer peer0 = client.newPeer(peer0Config.getOrg1Name(), peer0Config.getOrg1Loc());
        // 创建事件
        EventHub event = client.newEventHub(peer0Config.getOrg1Name(), peer0Config.getOrg1EventLoc());
        // 添加服务
        // 如果没有任何order会报错
        // Channel myChannel does not have any orderers associated with it.
        channel.addOrderer(order);
        // 添加事件
        channel.addEventHub(event);
        // 添加节点
        channel.joinPeer(peer0);
        // 初始化
        channel.initialize();
        //
        Quick.run(x->Thread.sleep(10000));
    }
}
