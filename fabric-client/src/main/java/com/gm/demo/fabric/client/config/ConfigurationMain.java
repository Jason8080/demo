package com.gm.demo.fabric.client.config;

import com.gm.demo.fabric.client.config.order.Order;
import com.gm.demo.fabric.client.config.org.Org;
import com.gm.demo.fabric.client.config.peer.Peer;
import com.gm.demo.fabric.client.config.user.AbstractUser;
import com.gm.demo.fabric.client.utils.TlsUtils;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.HFClient;
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
    Order order1;
    @Autowired
    Org org1;
    @Autowired
    Org org2;
    @Autowired
    Peer peer0Org1;
    @Autowired
    Peer peer0Org2;
    @Autowired
    Peer peer1Org1;
    @Autowired
    Peer peer1Org2;

    @Bean
    public HFClient getClient(AbstractUser peerOrg1Admin) throws Exception {
        HFClient client = HFClient.createNewInstance();
        client.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());
        client.setUserContext(peerOrg1Admin.enroll());
        return client;
    }

    @Bean
    public Channel getChannel(HFClient client) throws Exception {
        Channel js = client.newChannel(js_channel_name);
        js.addOrderer(client.newOrderer(order1.getName(), order1.getLoc()
                , TlsUtils.getTlsProperties(order1)));
        js.addPeer(client.newPeer(peer0Org1.getName(), peer0Org1.getLoc()
                , TlsUtils.getTlsProperties(peer0Org1)));
        js.addEventHub(client.newEventHub(peer0Org1.getName(), peer0Org1.getEventLoc()
                , TlsUtils.getTlsProperties(peer0Org1)));
        Channel channel = js.initialize();
        return channel;
    }

    @Bean
    public Void main(Channel js) throws Exception {
        return null;
    }
}
