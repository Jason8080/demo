package cn.gmlee.demo.dynamic.api.adapter;

import cn.gmlee.tools.base.anno.ApiPrint;
import cn.gmlee.tools.base.mod.R;
import com.ldw.kit.callback.server.LdwPaymentCallbackServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LdwPaymentCallbackService implements LdwPaymentCallbackServer {

    @ApiPrint("保金支付 · 处理中心")
    public R bondHandler(String orderNo, String batchNo) {
        return R.OK;
    }
}
