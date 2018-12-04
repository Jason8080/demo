package com.gm.demo.fabric.sdk.test;

import com.gm.demo.fabric.sdk.ca.org.Org;
import com.gm.demo.fabric.sdk.ca.sample.SampleOrg;
import com.gm.demo.fabric.sdk.ca.sample.SampleStore;
import com.gm.demo.fabric.sdk.ca.sample.SampleUser;
import com.gm.demo.fabric.sdk.cli.Client;
import com.gm.demo.fabric.sdk.kit.Util;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.exception.EnrollmentException;
import org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;


/**
 * @author Jason
 */
public class Main {

    public static final String user_name = "user1";

    public static final String user_pass = "tRHuyeCpoYwQ";

    public static final String admin_name = "admin";

    public static final String admin_pass = "adminpw";


    public static void main(String[] args) throws Exception {
        // 1. 创建Cli
        final HFClient client = Client.create();

        File sampleStoreFile = new File(System.getProperty("java.io.tmpdir") + "/HFCSampletest.properties");
        if (sampleStoreFile.exists()) {
            sampleStoreFile.delete();
        }

        final SampleStore sampleStore = new SampleStore(sampleStoreFile);

        // 2. 装载Org
        for (SampleOrg sampleOrg : Org.mount().values()) {
            // 设置登记用户
            addUser(sampleStore, sampleOrg);
            // 设置peer管理员
            peerAdmin(sampleStore, sampleOrg);
        }

        // 3. 获取组织
        final SampleOrg peerOrg1 = Org.sampleOrgs.get("peerOrg1");
        final SampleOrg peerOrg2 = Org.sampleOrgs.get("peerOrg2");

        // 4. 获取渠道
        Channel foo = getChannel("foo", client, peerOrg1);
        Channel bar = getChannel("bar", client, peerOrg2);

        // 5. 调用链码
        exec(client, foo, true, peerOrg1, 0);
        exec(client, bar, true, peerOrg2, 100);

        // 6. 关闭渠道
        foo.shutdown(true);

        // 7. 提交区块
        commit(bar);
    }

    private static void commit(Channel channel) {


    }

    private static void exec(HFClient client, Channel channel, Boolean isInstall, SampleOrg sampleOrg, int data) {


    }

    private static Channel getChannel(String name, HFClient client, SampleOrg sampleOrg) {

        return null;
    }


    private static void addUser(SampleStore sampleStore, SampleOrg sampleOrg) {


        SampleUser user = sampleStore.getMember(user_name, sampleOrg.getName());

        user.setEnrollmentSecret(user_pass);

        sampleOrg.addUser(user);
    }


    private static void peerAdmin(SampleStore sampleStore, SampleOrg sampleOrg) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {


        final String sampleOrgDomainName = sampleOrg.getDomainName();
        SampleUser peerOrgAdmin = sampleStore.getMember(
                sampleOrg.getName().concat("Admin"),
                sampleOrg.getName(),
                sampleOrg.getMSPID(),
                Util.findFileSk(
                        Paths.get(
                                "src/test/fixture/sdkintegration/e2e-2Orgs/channel",
                                "crypto-config/peerOrganizations/",
                                sampleOrgDomainName,
                                String.format("/users/Admin@%s/msp/keystore", sampleOrgDomainName))
                                .toFile()),
                Paths.get(
                        "src/test/fixture/sdkintegration/e2e-2Orgs/channel",
                        "crypto-config/peerOrganizations/",
                        sampleOrgDomainName,
                        String.format("/users/Admin@%s/msp/signcerts/Admin@%s-cert.pem", sampleOrgDomainName, sampleOrgDomainName))
                        .toFile()
        );
        sampleOrg.setPeerAdmin(peerOrgAdmin);
    }


    private static SampleUser getAdmin(SampleStore sampleStore, SampleOrg sampleOrg) throws EnrollmentException, InvalidArgumentException {


        HFCAClient ca = sampleOrg.getCAClient();

        SampleUser admin = sampleStore.getMember(admin_name, sampleOrg.getName());

        admin.setEnrollment(ca.enroll(admin.getName(), admin_pass));

        admin.setMspId(sampleOrg.getMSPID());

        return admin;
    }
}
