package net.zhaixing.blog.user.application.ability.share;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * 能力上下文类。
 * 这个类使用TransmittableThreadLocal来维护线程本地上下文。
 * 它允许在多线程环境中每个线程有自己的数据副本，这些数据是隔离的。
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-02
 * @since 1.0.0
 */
public class AbilityContext {
    // 使用TransmittableThreadLocal来存储线程本地的Map。
    // 这样可以确保当使用线程池时，上下文数据能够正确地传递到其他线程。
    private static final ThreadLocal<Map<String, Object>> CONTEXT = new TransmittableThreadLocal<>();

    /**
     * 初始化上下文。
     * 如果当前线程的上下文为空，则创建一个新的HashMap。
     * 如果已存在，则清空上下文中的数据。
     */
    protected static void initContext() {
        if (CONTEXT.get() == null) {
            CONTEXT.set(new HashMap<>(4)); // 使用较小的初始容量，以节省内存
        } else {
            CONTEXT.get().clear(); // 清除已有的上下文数据
        }
    }

    /**
     * 清除当前线程的上下文。
     * 从ThreadLocal中移除当前线程的Map实例。
     */
    protected static void clearContext() {
        CONTEXT.remove();
    }

    /**
     * 从上下文中获取指定键的值。
     *
     * @param key 想要获取的值的键
     * @return {@link T} 返回键对应的值，如果找不到则返回null
     */
    public static <T> T getValue(String key) {
        Map<String, Object> con = CONTEXT.get();
        if (con == null) {
            return null;
        }
        try {
            return (T) con.get(key); // 尝试获取并返回值
        } catch (ClassCastException e) {
            // 如果类型转换失败，则捕获异常并返回null
            return null;
        }
    }

    /**
     * 从上下文中获取指定键和类型的值。
     *
     * @param key   想要获取的值的键
     * @param clazz 返回值应该的类类型
     * @return {@link T} 返回键对应的值，如果找不到或类型不匹配则返回null
     */
    public static <T> T getValue(String key, Class<T> clazz) {
        Map<String, Object> con = CONTEXT.get();
        if (con == null) {
            return null;
        }
        Object object = con.get(key);
        if (clazz.isInstance(object)) {
            return clazz.cast(object); // 检查类型是否匹配，然后返回值
        }
        return null;
    }

    /**
     * 设置上下文中的键值对。
     * 如果当前线程的上下文为空，则创建一个新的HashMap，并设置键值对。
     * 如果上下文已存在，则直接设置键值对。
     *
     * @param key   要设置的键
     * @param value 要设置的值
     */
    public static void putValue(String key, Object value) {
        Map<String, Object> con = CONTEXT.get();
        if (con == null) {
            con = new HashMap<>(4);
            CONTEXT.set(con);
        }
        con.put(key, value); // 设置键值对
    }
}
