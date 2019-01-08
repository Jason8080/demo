package com.gm.demo.fabric.manual.model;

import lombok.Data;
import org.hyperledger.fabric_ca.sdk.HFCAClient;

import java.io.Serializable;

/**
 * @author Jason
 */
@Data
public class SampleOrg implements Serializable {
    private String name;
    private String mspId;
    private HFCAClient ca;

    public SampleOrg() {
    }

    public SampleOrg(String name, String mspId) {
        this.name = name;
        this.mspId = mspId;
    }
}
