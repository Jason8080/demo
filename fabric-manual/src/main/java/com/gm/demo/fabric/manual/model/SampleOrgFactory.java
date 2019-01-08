package com.gm.demo.fabric.manual.model;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 组织工厂.
 *
 * @author Jason
 */
@Data
public class SampleOrgFactory {
    private List<SampleOrg> sos;
    private Map<String, Properties> orderProperties;
}
