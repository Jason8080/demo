package com.gm.demo.fabric.manual.service;

import com.gm.demo.fabric.manual.config.fabric.*;
import com.gm.demo.fabric.manual.model.SampleOrg;
import com.gm.demo.fabric.manual.model.SampleUser;
import com.gm.help.base.Quick;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.ChannelConfiguration;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.Orderer;
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
    @Qualifier("fooConfig")
    ChannelConfig fooConfig;

    @Autowired
    @Qualifier("barConfig")
    ChannelConfig barConfig;

    @Autowired
    @Qualifier("order1Config")
    OrderConfig order1Config;

    @Autowired
    @Qualifier("peer0Config")
    PeerConfig peer0Config;

    public void handler() {

    }
}
