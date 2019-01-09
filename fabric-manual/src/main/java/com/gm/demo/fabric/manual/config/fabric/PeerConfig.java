package com.gm.demo.fabric.manual.config.fabric;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jason
 */
@Data
public class PeerConfig implements Serializable {
    private String org1Name;
    private String org1Loc;
    private String org2Name;
    private String org2Loc;
}
