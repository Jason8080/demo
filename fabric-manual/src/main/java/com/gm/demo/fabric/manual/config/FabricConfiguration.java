package com.gm.demo.fabric.manual.config;

import com.gm.demo.fabric.manual.config.fabric.Org1Config;
import com.gm.demo.fabric.manual.config.fabric.Org2Config;
import com.gm.demo.fabric.manual.config.fabric.OrgConfig;
import com.gm.demo.fabric.manual.config.fabric.UserConfig;
import com.gm.demo.fabric.manual.model.SampleEnrollment;
import com.gm.demo.fabric.manual.model.SampleOrg;
import com.gm.demo.fabric.manual.model.SampleUser;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.security.PrivateKey;
import java.util.Properties;

import static java.lang.String.format;

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

    @Bean("caOrg1")
    public HFCAClient ca(Org1Config config) throws Exception {
        return getCa(config);
    }

    @Bean("caOrg2")
    public HFCAClient ca(Org2Config config) throws Exception {
        return getCa(config);
    }

    @Bean("admin")
    public SampleUser admin(UserConfig config) {
        SampleUser admin = new SampleUser();
        admin.setName(config.getAdminName());
        return admin;
    }

    @Bean("org1")
    public SampleOrg org1(Org1Config config) throws Exception {
        SampleOrg org = new SampleOrg(config.getPeerName(), config.getPeerMspId());
        org.setCa(ca(config));
        return org;
    }

    @Bean("org2")
    public SampleOrg org2(Org2Config config) throws Exception {
        SampleOrg org = new SampleOrg(config.getPeerName(), config.getPeerMspId());
        org.setCa(ca(config));
        return org;
    }

    @Bean("peerOrg1Admin")
    public SampleUser peerOrg1Admin(Org1Config config) throws Exception {
        return createPeerAdmin(config);
    }
    @Bean("peerOrg2Admin")
    public SampleUser peerOrg2Admin(Org2Config config) throws Exception {
        return createPeerAdmin(config);
    }


    /**
     * 创建并配置Ca客户端
     * @param config 组织配置
     * @return 客户端
     * @throws Exception
     */
    private HFCAClient getCa(OrgConfig config) throws Exception {
        Properties properties = new Properties();
        properties.setProperty("pemFile", new File(config.getCaCert()).getAbsolutePath());
        properties.setProperty("allowAllHostNames", "true");
        HFCAClient ca = HFCAClient.createNewInstance(config.getCaLocation(), properties);
        ca.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());
        return ca;
    }

    /**
     * 创建并配置peer管理员
     * @param config 组织配置
     * @return 管理员对象
     * @throws Exception
     */
    private SampleUser createPeerAdmin(OrgConfig config) throws Exception {
        Class<? extends FabricConfiguration> clazz = this.getClass();
        // 创建管理员
        SampleUser peerAdmin = new SampleUser(config.getPeerAdmin(), config.getPeerMspId());
        // 这是私钥库
        File inKeystore = new File(clazz.getResource(config.getKeystore()).toURI());
        byte[] keystore = IOUtils.toByteArray(new FileInputStream(findSk(inKeystore)));
        final Reader pemReader = new StringReader(new String(keystore));
        final PrivateKeyInfo pemPair;
        try (PEMParser pemParser = new PEMParser(pemReader)) {
            pemPair = (PrivateKeyInfo) pemParser.readObject();
        }
        PrivateKey privateKey = new JcaPEMKeyConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME).getPrivateKey(pemPair);
        // 这是证书签
        File inSignCerts = new File(clazz.getResource(config.getSignCerts()).toURI());
        byte[] sign = IOUtils.toByteArray(new FileInputStream(inSignCerts));
        String certificate = new String(sign, "UTF-8");
        // 验证设置
        peerAdmin.setEnrollment(new SampleEnrollment(privateKey, certificate));
        // saveState()
        return peerAdmin;
    }

    private File findSk(File directory) {
        File[] matches = directory.listFiles((dir, name) -> name.endsWith("_sk"));
        if (null == matches) {
            throw new RuntimeException(format("Matches returned null does %s directory exist?", directory.getAbsoluteFile().getName()));
        }
        if (matches.length != 1) {
            throw new RuntimeException(format("Expected in %s only 1 sk file but found %d", directory.getAbsoluteFile().getName(), matches.length));
        }
        return matches[0];

    }

}
