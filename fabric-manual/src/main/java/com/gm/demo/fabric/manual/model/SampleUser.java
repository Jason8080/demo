package com.gm.demo.fabric.manual.model;

import lombok.Data;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.User;

import java.util.Set;

/**
 * @author Jason
 */
@Data
public class SampleUser implements User {

    private String name;

    private Set<String> roles;

    private String account;

    private String affiliation;

    private Enrollment enrollment;

    private String mspId;

    public SampleUser() {
    }

    public SampleUser(String name, String mspId) {
        this.name = name;
        this.mspId = mspId;
    }
}
