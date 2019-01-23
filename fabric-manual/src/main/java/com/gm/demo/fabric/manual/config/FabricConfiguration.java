package com.gm.demo.fabric.manual.config;

import com.gm.demo.fabric.manual.config.fabric.*;
import com.gm.demo.fabric.manual.model.SampleEnrollment;
import com.gm.demo.fabric.manual.model.SampleOrg;
import com.gm.demo.fabric.manual.model.SampleUser;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.ChannelConfiguration;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.Orderer;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.Reader;
import java.io.StringReader;
import java.security.PrivateKey;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

/**
 * @author Jason
 */
@Configuration
public class FabricConfiguration {

    @Bean
    public HFClient client() throws Exception {
        HFClient client = HFClient.createNewInstance();
        client.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());
        return client;
    }

    @Bean
    public SampleUser admin(UserConfig config) {
        SampleUser admin = new SampleUser();
        admin.setName(config.getAdminName());
        admin.setPass(config.getAdminPass());
        return admin;
    }

    @Bean("org1")
    public SampleOrg org1(Org1Config config) throws Exception {
        SampleOrg org = new SampleOrg(config.getPeerName(), config.getPeerMspId());
        org.setCa(getCa(config));
        org.setPeerAdmin(createPeerAdmin(config));
        return org;
    }

    @Bean("org2")
    public SampleOrg org2(Org2Config config) throws Exception {
        SampleOrg org = new SampleOrg(config.getPeerName(), config.getPeerMspId());
        org.setCa(getCa(config));
        return org;
    }


    /**
     * 创建并配置Ca客户端
     *
     * @param config 组织配置
     * @return 客户端
     * @throws Exception
     */
    public static HFCAClient getCa(OrgConfig config) throws Exception {
        Properties properties = new Properties();
        properties.setProperty("pemFile", new File(config.getCaCert()).getAbsolutePath());
        properties.setProperty("allowAllHostNames", "true");
        HFCAClient ca = HFCAClient.createNewInstance(config.getCaLocation(), properties);
        ca.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());
        return ca;
    }

    /**
     * 创建并配置peer管理员
     *
     * @param config 组织配置
     * @return 管理员对象
     * @throws Exception
     */
    public static SampleUser createPeerAdmin(OrgConfig config) throws Exception {
        Class<? extends FabricConfiguration> clazz = FabricConfiguration.class;
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
        PrivateKey privateKey = new JcaPEMKeyConverter().setProvider(BouncyCastleProvider
                .PROVIDER_NAME).getPrivateKey(pemPair);
        // 这是证书签
        File inSignCerts = new File(clazz.getResource(config.getSignCerts()).toURI());
        byte[] sign = IOUtils.toByteArray(new FileInputStream(inSignCerts));
        String certificate = new String(sign, "UTF-8");
        // 验证设置
        peerAdmin.setEnrollment(new SampleEnrollment(privateKey, certificate));
        // saveState()
        return peerAdmin;
    }

    public static File findSk(File directory) {
        File[] matches = directory.listFiles((dir, name) -> name.endsWith("_sk"));
        if (null == matches) {
            throw new RuntimeException(format("Matches returned null does %s directory exist?",
                    directory.getAbsoluteFile().getName()));
        }
        if (matches.length != 1) {
            throw new RuntimeException(format("Expected in %s only 1 sk file but found %d",
                    directory.getAbsoluteFile().getName(), matches.length));
        }
        return matches[0];

    }

    public static Channel createChannel(HFClient client, SampleOrg org, OrderConfig orderConfig, ChannelConfig config) throws Exception {
        client.setUserContext(org.getPeerAdmin());
        File file = new File(FabricConfiguration.class.getResource(config.getTx()).toURI());
        ChannelConfiguration configuration = new ChannelConfiguration(file);
        byte[] signature = client.getChannelConfigurationSignature
                (configuration, org.getPeerAdmin());
        // 创建共识
        Orderer order = client.newOrderer(orderConfig.getName(), orderConfig.getLoc(), getTlsProperties(orderConfig));
        // 创建通道
        Channel channel = client.newChannel(config.getName(), order, configuration, signature);
        // 初始化
        channel.initialize();
        // OK
        return channel;
    }



    /**
     * 不配置这个会抛出异常
     * <p>
     * grpc: Server.Serve failed to complete security handshake from "192.168.1.223:53182": tls:
     * first record does not look like a TLS handshake
     *
     * @return
     * @throws Exception
     */
    public static Properties getTlsProperties(TlsConfig tlsConfig) throws Exception {
        String name = tlsConfig.getName();
        String crt = tlsConfig.getTls();
        Properties ret = new Properties();
        ret.setProperty("hostnameOverride", name);
        File cert = new File(FabricConfiguration.class.getResource(crt).toURI());
        ret.setProperty("sslProvider", "openSSL");
        ret.setProperty("negotiationType", "TLS");
        ret.setProperty("pemFile", cert.getAbsolutePath());
        // 超时配置
        ret.put("grpc.NettyChannelBuilderOption.keepAliveTime", new Object[]{5L, TimeUnit.MINUTES});
        ret.put("grpc.NettyChannelBuilderOption.keepAliveTimeout", new Object[]{8L, TimeUnit
                .SECONDS});
        return ret;
    }
}
