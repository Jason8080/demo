package com.gm.demo.fabric.client.model;

import lombok.Data;
import org.hyperledger.fabric.sdk.Enrollment;

import java.security.PrivateKey;

/**
 * @author Jason
 */
@Data
public class SampleEnrollment implements Enrollment {
    private PrivateKey key;
    private String cert;

    public SampleEnrollment(PrivateKey key, String cert) {
        this.key = key;
        this.cert = cert;
    }
}
