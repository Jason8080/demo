package com.gm.demo.fabric.manual.service;

import com.gm.demo.fabric.manual.config.FabricConfiguration;
import com.gm.demo.fabric.manual.config.fabric.ChannelConfig;
import com.gm.demo.fabric.manual.config.fabric.OrderConfig;
import com.gm.demo.fabric.manual.config.fabric.PeerConfig;
import com.gm.demo.fabric.manual.model.SampleOrg;
import com.gm.demo.fabric.manual.model.SampleUser;
import com.gm.help.base.Quick;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.HFClient;
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
    @Qualifier("fooConfig")
    ChannelConfig fooConfig;

    @Autowired
    @Qualifier("barConfig")
    ChannelConfig barConfig;

    @Autowired
    @Qualifier("order1Config")
    OrderConfig order1Config;

    @Autowired
    @Qualifier("peer0Org1Config")
    PeerConfig peer0Org1Config;

    @Autowired
    @Qualifier("peer0Org2Config")
    PeerConfig peer0Org2Config;

    @Autowired
    @Qualifier("peer1Org1Config")
    PeerConfig peer1Org1Config;

    @Autowired
    @Qualifier("peer1Org2Config")
    PeerConfig peer1Org2Config;

    public void handler() {
        Quick.run(x -> {
            Channel foo = FabricConfiguration.createChannel(client, org1, order1Config, fooConfig);
            FabricConfiguration.joinPeer(client, foo, peer0Org1Config);
            FabricConfiguration.joinPeer(client, foo, peer1Org1Config);
            foo.initialize();


            Channel bar = FabricConfiguration.createChannel(client, org2, order1Config, barConfig);
            FabricConfiguration.joinPeer(client, bar, peer0Org2Config);
            FabricConfiguration.joinPeer(client, bar, peer1Org2Config);
            bar.initialize();

        });
    }
}
