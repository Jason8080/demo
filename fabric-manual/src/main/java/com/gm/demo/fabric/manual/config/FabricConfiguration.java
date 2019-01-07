package com.gm.demo.fabric.manual.config;

import com.gm.demo.fabric.manual.config.fabric.Org1Config;
import com.gm.demo.fabric.manual.config.fabric.Org2Config;
import com.gm.demo.fabric.manual.config.fabric.OrgConfig;
import com.gm.demo.fabric.manual.config.fabric.UserConfig;
import com.gm.demo.fabric.manual.model.SampleEnrollment;
import com.gm.demo.fabric.manual.model.SampleUser;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.security.PrivateKey;
import java.util.Properties;

/**
 * @author Jason
 */
@Configuration
public class FabricConfiguration {

    @Bean
    public HFClient client(/*SampleUser peerAdmin*/) throws Exception {
        HFClient client = HFClient.createNewInstance();
        client.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());
        return client;
        // 这里暂不设置peer节点管理员
//        client.setUserContext(peerAdmin);
    }

    @Bean
    @Qualifier("admin")
    public SampleUser admin(UserConfig config) {
        SampleUser admin = new SampleUser();
        admin.setName(config.getAdminName());
        return admin;
    }

    @Bean
    @Qualifier("peerOrg1Admin")
    public SampleUser peerOrg1Admin(Org1Config config) throws Exception {
        return createPeerAdmin(config, "peerOrg1Admin");
    }
    @Bean
    @Qualifier("peerOrg2Admin")
    public SampleUser peerOrg2Admin(Org2Config config) throws Exception {
        return createPeerAdmin(config, "peerOrg2Admin");
    }

    @Bean
    @Qualifier("caOrg1")
    public HFCAClient ca(Org1Config config) throws Exception {
        Properties properties = new Properties();
        properties.setProperty("pemFile", new File(config.getCaCert()).getAbsolutePath());
        properties.setProperty("allowAllHostNames", "true");
        return HFCAClient.createNewInstance(config.getCaLocation(), properties);
    }

    @Bean
    @Qualifier("caOrg2")
    public HFCAClient ca(Org2Config config) throws Exception {
        Properties properties = new Properties();
        properties.setProperty("pemFile", new File(config.getCaCert()).getAbsolutePath());
        properties.setProperty("allowAllHostNames", "true");
        return HFCAClient.createNewInstance(config.getCaLocation(), properties);
    }


    /**
     * 创建并配置peer管理员
     * @param config 组织配置
     * @param username 管理员名称
     * @return 管理员对象
     * @throws IOException
     */
    private SampleUser createPeerAdmin(OrgConfig config, String username) throws Exception {
        // 创建管理员
        SampleUser peerAdmin = new SampleUser(username, config.getPeerMspId());
        // 这是私钥库
        byte[] keystore = IOUtils.toByteArray(new FileInputStream(new File(config.getKeystore())));
        final Reader pemReader = new StringReader(new String(keystore));
        final PrivateKeyInfo pemPair;
        try (PEMParser pemParser = new PEMParser(pemReader)) {
            pemPair = (PrivateKeyInfo) pemParser.readObject();
        }
        PrivateKey privateKey = new JcaPEMKeyConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME).getPrivateKey(pemPair);
        // 这是证书签
        byte[] sign = IOUtils.toByteArray(new FileInputStream(new File(config.getSignCerts())));
        String certificate = new String(sign, "UTF-8");
        // 验证设置
        peerAdmin.setEnrollment(new SampleEnrollment(privateKey, certificate));
        // saveState()
        return peerAdmin;
    }

}
