package com.gm.demo.fabric.sdk.test;

import com.gm.demo.fabric.sdk.ca.org.Org;
import com.gm.demo.fabric.sdk.ca.sample.SampleOrg;
import com.gm.demo.fabric.sdk.ca.sample.SampleStore;
import com.gm.demo.fabric.sdk.ca.sample.SampleUser;
import com.gm.demo.fabric.sdk.cli.Client;
import com.gm.demo.fabric.sdk.kit.Util;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric_ca.sdk.HFCAClient;

import java.io.File;
import java.nio.file.Paths;


/**
 * @author Jason
 */
public class Main {
    public static final String user_name = "user1";
    public static final String user_pass = "tRHuyeCpoYwQ";

    public static final String admin_name = "admin";
    public static final String admin_pass = "adminpw";

    public static void main(String[] args) throws Exception {
        // 创建Cli
        final HFClient client = Client.create();

        File sampleStoreFile = new File(System.getProperty("java.io.tmpdir") + "/HFCSampletest.properties");
        if (sampleStoreFile.exists()) {
            sampleStoreFile.delete();
        }

        final SampleStore sampleStore = new SampleStore(sampleStoreFile);

        // 装载Org
        for (SampleOrg sampleOrg : Org.mount().values()) {

            // 装载时已经初始化的ca客户端
            HFCAClient ca = sampleOrg.getCAClient();

            // 设置管理员
            SampleUser admin = sampleStore.getMember(admin_name, sampleOrg.getName());

            admin.setEnrollment(ca.enroll(admin.getName(), admin_pass));

            admin.setMspId(sampleOrg.getMSPID());

            // 注册用户测试

            SampleUser user = sampleStore.getMember(user_name, sampleOrg.getName());

            user.setEnrollmentSecret(user_pass);

            // 添加已登记用户
            sampleOrg.addUser(user);

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
    }
}
