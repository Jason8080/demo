package com.gm.demo.tools.request.controller.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Jas°
 * @date 2021/4/25 (周日)
 */
@Data
public class Demo {
    private Long id;
    private String name;
    private String number;
    private List<Demo> demos;
    private List<String> numbers;
    private List<Map<String, String>> maps;
}
