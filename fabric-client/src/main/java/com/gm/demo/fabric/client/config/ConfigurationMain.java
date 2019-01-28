package com.gm.demo.fabric.client.config;

import com.gm.demo.fabric.client.config.order.Order;
import com.gm.demo.fabric.client.config.peer.Peer;
import com.gm.demo.fabric.client.config.user.Admin;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.EventHub;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.Orderer;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jason
 */
@Configuration
public class ConfigurationMain {

    public static final String js_channel_name = "jschannel";

    @Autowired
    Peer peer0Org1;
    @Autowired
    Peer peer0Org2;
    @Autowired
    Peer peer1Org1;
    @Autowired
    Peer peer1Org2;

    @Bean
    public HFClient getClient(Admin admin) throws Exception {
        HFClient client = HFClient.createNewInstance();
        client.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());
        client.setUserContext(admin.enroll());
        return client;
    }

    @Bean
    public Orderer getOrder(HFClient client, Order order) throws Exception {
        return client.newOrderer(order.getName(), order.getLoc());
    }

    @Bean
    public Channel getChannel(HFClient client, Orderer order, org.hyperledger.fabric.sdk.Peer peer, EventHub event) throws Exception {
        Channel js = client.newChannel(js_channel_name);
        js.addOrderer(order);
        js.addPeer(client.newPeer(peer0Org1.getName(), peer0Org1.getLoc()));
        js.addEventHub(client.newEventHub(peer0Org1.getName(), peer0Org1.getEventLoc()));
        return js;
    }

    @Bean
    public Void main(Channel js) throws Exception {
        js.initialize();
        return null;
    }
}
