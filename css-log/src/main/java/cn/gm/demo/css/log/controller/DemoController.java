package cn.gm.demo.css.log.controller;


import cn.hll.tools.base.anno.ApiPrint;
import cn.hll.tools.base.mod.JsonResult;
import cn.hll.tools.base.mod.Kv;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 申请单 前端控制器
 * </p>
 *
 * @author Jas°
 * @since 2022-04-26
 */
@Validated
@RestController
@Api(tags = {"申请单 前端控制器"})
@RequestMapping("order")
public class DemoController {
      @ApiOperation(value = "查询")
      @ApiPrint(value = "查询")
      @GetMapping(value = "get")
      public JsonResult get(Kv kv) {
          return JsonResult.OK.newly(kv);
      }
}
