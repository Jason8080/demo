package com.gm.demo.eureka.seata.main.controller;

import cn.gmlee.tools.base.mod.JsonResult;
import com.gm.demo.eureka.seata.main.api.entity.Tx;
import com.gm.demo.eureka.seata.main.api.enums.MicroConstant;
import com.gm.demo.eureka.seata.main.service.TxService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Jas°
 */
@RestController
@RequestMapping(MicroConstant.main)
public class TxApiController {

    @Resource
    TxService txService;

    @PostMapping
    @ApiOperation("")
    public JsonResult come(String str) {
        Tx tx = new Tx(str);
        txService.come(tx);
        return JsonResult.OK;
    }
}
