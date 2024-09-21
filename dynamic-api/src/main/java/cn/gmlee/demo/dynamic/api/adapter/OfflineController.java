package cn.gmlee.demo.dynamic.api.adapter;

import cn.gmlee.tools.base.mod.R;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

public class OfflineController {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public R handle(@RequestBody Map<String, Object> bodyMap) {
        return R.FAIL.newly(404, "The API is not publish.");
    }
}
