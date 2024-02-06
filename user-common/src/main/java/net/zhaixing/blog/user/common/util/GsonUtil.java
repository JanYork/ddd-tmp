package net.zhaixing.blog.user.common.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Gson工具类
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
public class GsonUtil {
    private static final Gson gson;

    static {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> {
                    String datetime = json.getAsJsonPrimitive().getAsString();
                    return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                })
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> {
                    String datetime = json.getAsJsonPrimitive().getAsString();
                    return LocalDate.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                })
                .registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, type, jsonDeserializationContext) -> {
                    String datetime = json.getAsJsonPrimitive().getAsString();
                    LocalDateTime localDateTime = LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                })
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) -> new JsonPrimitive(src.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, typeOfSrc, context) -> new JsonPrimitive(src.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
                .registerTypeAdapter(Date.class, (JsonSerializer<Date>) (src, typeOfSrc, context) -> {
                    LocalDateTime localDateTime = LocalDateTime.ofInstant(src.toInstant(), ZoneId.systemDefault());
                    return new JsonPrimitive(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                })
                .create();
    }

    public GsonUtil() {
    }

    /**
     * 将object对象转成json字符串
     *
     * @param object {@link Object} 需要转换的对象
     * @return {@link String}
     */
    public static String gsonToString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 将gsonString转成泛型bean
     *
     * @param gsonString {@link String} 需要转换的字符串
     * @param cls        {@link Class} 泛型类
     * @return {@link T}
     */
    public static <T> T gsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * 转成list
     * 泛型在编译期类型被擦除导致报错
     *
     * @param gsonString {@link String} 需要转换的字符串
     * @param cls        {@link Class} 泛型类
     * @return {@link List}<{@link T}>
     */
    public static <T> List<T> gsonToList(String gsonString, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString, TypeToken.getParameterized(List.class, cls).getType());
        }
        return list;
    }

    /**
     * 转成list中有map的
     *
     * @param gsonString {@link String} 需要转换的字符串
     * @return {@link List}<{@link Map}<{@link String}, {@link T}>>
     */
    public static <T> List<Map<String, T>> gsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }


    /**
     * 转成map的
     *
     * @param gsonString {@link String} 需要转换的字符串
     * @return {@link Map}<{@link String}, {@link T}>
     */
    public static <T> Map<String, T> gsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    /**
     * 把一个bean（或者其他的字符串什么的）转成json
     *
     * @param object {@link Object} 需要转换的对象
     * @return {@link String}
     */
    public static String beanToJson(Object object) {
        return gson.toJson(object);
    }
}
