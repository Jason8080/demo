package com.gm.demo.fabric.client.config.peer;

import com.gm.demo.fabric.client.config.order.Tls;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Jason
 */
@Data
public class Peer extends Tls implements Serializable {
    private String loc;
    private String eventLoc;
}
