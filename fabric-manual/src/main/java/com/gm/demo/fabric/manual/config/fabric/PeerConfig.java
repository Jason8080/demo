package com.gm.demo.fabric.manual.config.fabric;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jason
 */
@Data
public class PeerConfig extends TlsConfig implements Serializable {
    private String loc;
    private String eventLoc;
}
