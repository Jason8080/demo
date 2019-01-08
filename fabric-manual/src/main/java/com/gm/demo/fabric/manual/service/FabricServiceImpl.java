package com.gm.demo.fabric.manual.service;

import com.gm.demo.fabric.manual.config.fabric.UserConfig;
import com.gm.demo.fabric.manual.model.SampleOrg;
import com.gm.demo.fabric.manual.model.SampleOrgFactory;
import com.gm.demo.fabric.manual.model.SampleUser;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jason
 */
@Service
public class FabricServiceImpl {
    @Autowired
    HFClient client;
    @Autowired
    SampleUser admin;
    @Autowired
    SampleOrgFactory orgFactory;

    @Autowired
    UserConfig userConfig;

    public void handler() throws Exception {
        List<SampleOrg> sos = orgFactory.getSos();
        for (SampleOrg org : sos){
            HFCAClient ca = org.getCa();
            // 管理员设置
            admin.setMspId(org.getMspId());
            admin.setEnrollment(ca.enroll(admin.getName(), admin.getPass()));
            // 注册新用户
            SampleUser user = new SampleUser(userConfig.getNewName(), org.getMspId());
            RegistrationRequest rr = new RegistrationRequest(userConfig.getNewName(), "org1.department1");
            String register = ca.register(rr, admin);
            user.setEnrollment(ca.enroll(userConfig.getNewName(), register));
            // 创建新通道
            client.setUserContext(org.getPeerAdmin());

        }
    }
}
