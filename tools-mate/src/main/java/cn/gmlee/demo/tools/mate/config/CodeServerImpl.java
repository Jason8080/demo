package cn.gmlee.demo.tools.mate.config;

import cn.gmlee.tools.base.util.ClassUtil;
import cn.gmlee.tools.base.util.DesUtil;
import cn.gmlee.tools.mate.interceptor.CodecServer;
import lombok.val;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class CodeServerImpl implements CodecServer {

    private static final String secretKey = "12345678";

    private static final List<String> fields = Arrays.asList("code");

    @Override
    public boolean enable(String codec) {
        return true;
    }

    @Override
    public void encode(Map<String, Field> fieldsMap, Object obj, String key, Field field) {
        if(!fields.contains(key)){
            return;
        }
        String value = ClassUtil.getValue(obj, field, String.class);
        String encode = DesUtil.encode(value, secretKey);
        ClassUtil.setValue(obj, field, encode);
    }

    @Override
    public void encode(Map<String, Object> valuesMap, Object obj, String key, Object value) {
        if(!fields.contains(key)){
            return;
        }
        String encode = DesUtil.encode((String) value, secretKey);
        valuesMap.put(key, encode);
    }

    @Override
    public void decode(Map<String, Field> fieldsMap, Object obj, String key, Field field) {
        if(!fields.contains(key)){
            return;
        }
        try {
            String value = ClassUtil.getValue(obj, field, String.class);
            String decode = DesUtil.decode(value, secretKey);
            ClassUtil.setValue(obj, field, decode);
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    @Override
    public void decode(Map<String, Object> valuesMap, Object obj, String key, Object value) {
        if(!fields.contains(key)){
            return;
        }
        try {
            String decode = DesUtil.decode((String) value, secretKey);
            valuesMap.put(key, decode);
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }
}
