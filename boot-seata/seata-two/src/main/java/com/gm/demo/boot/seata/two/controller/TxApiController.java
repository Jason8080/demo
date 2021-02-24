package com.gm.demo.boot.seata.two.controller;

import cn.gmlee.tools.base.mod.JsonResult;
import com.gm.demo.boot.seata.main.api.entity.Tx;
import com.gm.demo.boot.seata.main.api.enums.MicroConstant;
import com.gm.demo.boot.seata.two.service.TxService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Jas°
 */
@RestController
@RequestMapping(MicroConstant.two)
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
