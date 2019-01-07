package com.gm.demo.fabric.manual.config.fabric;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jason
 */
@Data
public class OrgConfig implements Serializable {
    private String caLocation;
    private String caCert;
    private String peerMspId;
    private String domain;
    private String keystore;
    private String signCerts;
}
