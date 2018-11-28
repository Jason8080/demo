package com.gm.demo.fabric.sdk.cli;

import com.gm.help.base.Quick;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.security.CryptoSuite;

/**
 * 客户端工具.
 *
 * @author Jason
 */
public class Client {

    /**
     * 创建客户端.
     *
     * @return the hf client
     */
    public static HFClient create() {
        return Quick.exec(x -> {

            HFClient client = HFClient.createNewInstance();

            client.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());

            return client;
        });
    }
}
