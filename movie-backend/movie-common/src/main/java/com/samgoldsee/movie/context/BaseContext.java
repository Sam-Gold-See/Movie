package com.samgoldsee.movie.context;

/**
 * 线程上下文管理
 */
public class BaseContext {

    /**
     * ThreadLocal 变量存储当前线程的用户ID
     */
    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    /**
     * 获取当前线程的用户ID
     *
     * @return id 当前用户ID
     */
    public static Integer getCurrentId() {
        return threadLocal.get();
    }

    /**
     * 设置当前线程的用户ID
     *
     * @param id 当前用户ID
     */
    public static void setCurrentId(Integer id) {
        threadLocal.set(id);
    }

    /**
     * 移除当前线程的用户ID，防止内存泄漏
     */
    public static void removeCurrentId() {
        threadLocal.remove();
    }
}
