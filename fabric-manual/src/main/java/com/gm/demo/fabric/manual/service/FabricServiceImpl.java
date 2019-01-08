package com.gm.demo.fabric.manual.service;

import com.gm.demo.fabric.manual.model.SampleOrg;
import com.gm.demo.fabric.manual.model.SampleUser;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
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
    @Qualifier("admin")
    SampleUser admin;
    @Autowired
    @Qualifier("org1")
    SampleOrg org1;
    @Autowired
    @Qualifier("org2")
    SampleOrg org2;
    @Autowired
    @Qualifier("caOrg1")
    HFCAClient caOrg1;
    @Autowired
    @Qualifier("caOrg2")
    HFCAClient caOrg2;
    @Autowired
    @Qualifier("peerOrg1Admin")
    SampleUser peerOrg1Admin;
    @Autowired
    @Qualifier("peerOrg2Admin")
    SampleUser peerOrg2Admin;

    public void handler() {
        System.out.println("hello~");
    }
}
