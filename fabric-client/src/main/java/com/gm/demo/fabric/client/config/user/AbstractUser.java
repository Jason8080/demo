package com.gm.demo.fabric.client.config.user;

import lombok.Data;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.User;

import java.util.Set;

/**
 * @author Jason
 */
@Data
public class AbstractUser implements User {
    protected String name;
    protected Set<String> roles;
    protected String account;
    protected String affiliation;
    protected Enrollment enrollment;
    protected String mspId;

    /**
     * 用户密码
     */
    protected String pass;

    /**
     * 密钥库
     */
    protected String keystore;

    /**
     * 证书
     */
    protected String cert;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<String> getRoles() {
        return roles;
    }

    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public String getAffiliation() {
        return affiliation;
    }

    @Override
    public Enrollment getEnrollment() {
        return enrollment;
    }

    @Override
    public String getMspId() {
        return mspId;
    }
}
