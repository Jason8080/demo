package cn.gmlee.demo.tools.mate.config;

import cn.gmlee.tools.base.util.DesUtil;
import cn.gmlee.tools.mate.interceptor.CodecServer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CodeServerImpl implements CodecServer {

    private static final String secretKey = "12345678";

    private static final List<String> fields = Arrays.asList("code");

    @Override
    public boolean enable(String codec) {
        return true;
    }

    @Override
    public String encode(Object obj, String key, Object val) {
        if(val instanceof String && fields.contains(key)){
            return DesUtil.encode((String) val, secretKey);
        }
        return null;
    }

    @Override
    public String decode(Object obj, String key, Object val) {
        if(val instanceof String && fields.contains(key)){
            try {
                return DesUtil.decode((String) val, secretKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
