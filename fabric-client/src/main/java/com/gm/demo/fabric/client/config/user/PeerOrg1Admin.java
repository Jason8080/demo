package com.gm.demo.fabric.client.config.user;

import com.gm.demo.fabric.client.model.SampleEnrollment;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.Reader;
import java.io.StringReader;
import java.security.PrivateKey;
import java.security.Security;

import static java.lang.String.format;

/**
 * @author Jason
 */
@Data
@Component
@Configuration
@PropertySource("classpath:fabric-user.properties")
@ConfigurationProperties(prefix = "fabric.user.admin.org1")
public class PeerOrg1Admin extends AbstractUser {

    @Override
    public PeerOrg1Admin enroll() throws Exception {
        // 这是私钥库
        File inKeystore = new File(getClass().getResource(this.keystore).toURI());
        byte[] keystore = IOUtils.toByteArray(new FileInputStream(findSk(inKeystore)));
        final Reader pemReader = new StringReader(new String(keystore));
        final PrivateKeyInfo pemPair;
        try (PEMParser pemParser = new PEMParser(pemReader)) {
            pemPair = (PrivateKeyInfo) pemParser.readObject();
        }
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
        PrivateKey privateKey = new JcaPEMKeyConverter().setProvider(BouncyCastleProvider
                .PROVIDER_NAME).getPrivateKey(pemPair);
        // 这是证书签
        File inSignCerts = new File(getClass().getResource(this.cert).toURI());
        byte[] sign = IOUtils.toByteArray(new FileInputStream(inSignCerts));
        String certificate = new String(sign, "UTF-8");
        SampleEnrollment enrollment = new SampleEnrollment(privateKey, certificate);
        this.enrollment = enrollment;
        return this;
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
}
