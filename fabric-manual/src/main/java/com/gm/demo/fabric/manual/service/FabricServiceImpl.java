package com.gm.demo.fabric.manual.service;

import com.gm.demo.fabric.manual.config.FabricConfiguration;
import com.gm.demo.fabric.manual.config.fabric.*;
import com.gm.demo.fabric.manual.model.SampleOrg;
import com.gm.demo.fabric.manual.model.SampleUser;
import com.gm.help.base.Quick;
import org.hyperledger.fabric.sdk.*;
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

    public void handler() {
        Quick.run(x->{
            Channel bar = FabricConfiguration.createChannel(client, org1, order1Config,
                    barConfig);
            Peer peer0 = client.newPeer(peer0Org1Config.getName(), peer0Org1Config.getLoc(),
                    FabricConfiguration.getTlsProperties(peer0Org1Config));
            System.out.println("end!");
        });
    }
}
