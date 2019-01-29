package com.gm.demo.fabric.client.config.org;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jason
 */
@Data
public class Org implements Serializable {
    private String caLocation;
    private String caCert;
    private String peerName;
    private String peerMspId;
    private String peerAdmin;
    private String domain;
    private String keystore;
    private String signCerts;
}
