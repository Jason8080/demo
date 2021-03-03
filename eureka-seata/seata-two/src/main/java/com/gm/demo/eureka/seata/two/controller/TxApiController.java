package com.gm.demo.eureka.seata.two.controller;

import cn.gmlee.tools.base.enums.XTime;
import cn.gmlee.tools.base.mod.JsonResult;
import cn.gmlee.tools.base.util.TimeUtil;
import com.gm.demo.eureka.seata.main.api.entity.Tx;
import com.gm.demo.eureka.seata.main.api.enums.MicroConstant;
import com.gm.demo.eureka.seata.two.service.TxService;
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
        System.out.println("================"+TimeUtil.getCurrentDatetime(XTime.LONG_DATE_PATTERN_WITH_MS_LINE));
        Tx tx = new Tx(str);
        txService.come(tx);
        return JsonResult.OK;
    }
}
