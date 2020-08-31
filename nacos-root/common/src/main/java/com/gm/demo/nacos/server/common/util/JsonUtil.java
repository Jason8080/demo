package com.gm.demo.nacos.server.common.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.Map.Entry;

/**
 * JSON 转换工具
 *
 * @author Timi
 * @date 2020 /8/28 (周五)
 */
public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    /**
     * To json string.
     *
     * @param obj the obj
     * @return the string
     * @throws Exception the exception
     */
    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * To json string.
     *
     * @param map the map
     * @return the string
     */
    public static String toJson(Map map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (Exception var2) {
            var2.printStackTrace();
            return "";
        }
    }

    /**
     * To json ignore null string.
     *
     * @param obj the obj
     * @return the string
     * @throws Exception the exception
     */
    public static String toJsonIgnoreNull(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        return mapper.writeValueAsString(obj);
    }

    /**
     * To bean t.
     *
     * @param <T>   the type parameter
     * @param json  the json
     * @param clazz the clazz
     * @return the t
     * @throws Exception the exception
     */
    public static <T> T toBean(String json, Class<T> clazz) {
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * To bean t.
     *
     * @param <T>   the type parameter
     * @param map   the map
     * @param clazz the clazz
     * @return the t
     */
    public static <T> T toBean(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    /**
     * To map map.
     *
     * @param <T>  the type parameter
     * @param json the json
     * @return the map
     * @throws Exception the exception
     */
    public static <T> Map<String, Object> toMap(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        return (Map) mapper.readValue(json, Map.class);
    }

    /**
     * To map of map.
     *
     * @param <T>   the type parameter
     * @param json  the json
     * @param clazz the clazz
     * @return the map
     * @throws Exception the exception
     */
    public static <T> Map<String, T> toMapOf(String json, Class<T> clazz) throws Exception {
        Map<String, Map<String, Object>> map = (Map) objectMapper.readValue(json, new TypeReference<Map<String, T>>() {});
        Map<String, T> result = new HashMap();
        Iterator var4 = map.entrySet().iterator();

        while (var4.hasNext()) {
            Entry<String, Map<String, Object>> entry = (Entry) var4.next();
            result.put(entry.getKey(), toBean(entry.getValue(), clazz));
        }

        return result;
    }

    /**
     * To map deeply map.
     *
     * @param json the json
     * @return the map
     * @throws Exception the exception
     */
    public static Map<String, Object> toMapDeeply(String json) throws Exception {
        return toMapRecursion(json, objectMapper);
    }

    /**
     * To list list.
     *
     * @param <T>          the type parameter
     * @param jsonArrayStr the json array str
     * @param clazz        the clazz
     * @return the list
     * @throws Exception the exception
     */
    public static <T> List<T> toList(String jsonArrayStr, Class<T> clazz) throws Exception {
        JavaType javaType = getCollectionType(ArrayList.class, clazz);
        List<T> lst = (List) objectMapper.readValue(jsonArrayStr, javaType);
        return lst;
    }

    /**
     * O 2 o t.
     *
     * @param <T>   the type parameter
     * @param obj   the obj
     * @param clazz the clazz
     * @return the t
     */
    public static <T> T o2o(Object obj, Class<T> clazz) {
        return objectMapper.convertValue(obj, clazz);
    }

    private static List<Object> toListRecursion(String json, ObjectMapper mapper) throws Exception {
        if (json == null) {
            return null;
        } else {
            List<Object> list = (List) mapper.readValue(json, List.class);
            Iterator var3 = list.iterator();

            while (var3.hasNext()) {
                Object obj = var3.next();
                if (obj != null && obj instanceof String) {
                    String str = (String) obj;
                    if (str.startsWith("[")) {
                        toListRecursion(str, mapper);
                    } else if (obj.toString().startsWith("{")) {
                        toMapRecursion(str, mapper);
                    }
                }
            }

            return list;
        }
    }

    private static Map<String, Object> toMapRecursion(String json, ObjectMapper mapper) throws Exception {
        if (json == null) {
            return null;
        } else {
            Map<String, Object> map = (Map) mapper.readValue(json, Map.class);
            Iterator var3 = map.entrySet().iterator();

            while (var3.hasNext()) {
                Entry<String, Object> entry = (Entry) var3.next();
                Object obj = entry.getValue();
                if (obj != null && obj instanceof String) {
                    String str = (String) obj;
                    if (str.startsWith("[")) {
                        List<?> list = toListRecursion(str, mapper);
                        map.put(entry.getKey(), list);
                    } else if (str.startsWith("{")) {
                        Map<String, Object> mapRecursion = toMapRecursion(str, mapper);
                        map.put(entry.getKey(), mapRecursion);
                    }
                }
            }

            return map;
        }
    }

    /**
     * Gets collection type.
     *
     * @param collectionClass the collection class
     * @param elementClasses  the element classes
     * @return the collection type
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
