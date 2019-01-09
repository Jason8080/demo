package com.gm.demo.fabric.manual.service;

import com.gm.demo.fabric.manual.config.fabric.Order1Config;
import com.gm.demo.fabric.manual.config.fabric.Peer0Config;
import com.gm.demo.fabric.manual.model.SampleOrg;
import com.gm.demo.fabric.manual.model.SampleUser;
import com.gm.help.base.Quick;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.Orderer;
import org.hyperledger.fabric.sdk.Peer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

    public void handler() {
        Quick.run(x->createChannel("myChannel"));
    }

    private void createChannel(String name) throws Exception {
        client.setUserContext(org1.getPeerAdmin());
        // 创建通道
        Channel channel = client.newChannel(name);
        // 创建共识
        Orderer order = client.newOrderer(order1Config.getName(), order1Config.getLoc());
        // 创建Peer
        Peer peer0 = client.newPeer(peer0Config.getOrg1Name(), peer0Config.getOrg1Loc());
        // 添加节点
        channel.addPeer(peer0);
        // 添加服务
        // 如果没有任何order会报错
        // Channel myChannel does not have any orderers associated with it.
        channel.addOrderer(order);
        // 初始化
        channel.initialize();
    }
}
