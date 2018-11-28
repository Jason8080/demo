package com.gm.demo.fabric.sdk.test;

import com.gm.demo.fabric.sdk.ca.Ca;
import com.gm.demo.fabric.sdk.ca.org.Org;
import com.gm.demo.fabric.sdk.ca.sample.SampleOrg;
import com.gm.demo.fabric.sdk.ca.sample.SampleStore;
import com.gm.demo.fabric.sdk.ca.sample.SampleUser;
import com.gm.demo.fabric.sdk.cli.Client;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.RegistrationRequest;

import java.io.File;

/**
 * @author Jason
 */
public class Main {

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

            // 注册用户测试
            RegistrationRequest rr = new RegistrationRequest("user4", "org1.department1");

            // 设置管理员
            SampleUser admin = sampleStore.getMember("admin", sampleOrg.getName());

            admin.setEnrollment(ca.enroll(admin.getName(), "adminpw"));

            admin.setMspId(sampleOrg.getMSPID());

            ca.register(rr, admin);
        }
    }
}
