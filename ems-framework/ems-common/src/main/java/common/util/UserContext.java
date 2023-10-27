package common.util;

public class UserContext {
    private static final ThreadLocal<String> tl = new ThreadLocal<>();
    private static final ThreadLocal<Long> tl2 = new ThreadLocal<>();

    /**
     * 获取当前登录用户信息
     *
     * @return 用户id
     */
    public static String getUser() {
        return tl.get();
    }

    /**
     * 保存当前登录用户信息到ThreadLocal
     *
     * @param userId 用户id
     */
    public static void setUser(String userId) {
        tl.set(userId);
    }

    public static Long getUserId() {
        return tl2.get();
    }

    public static void setUserId(Long userId) {
        tl2.set(userId);
    }

    /**
     * 移除当前登录用户信息
     */
    public static void removeUser() {
        tl.remove();
    }

    public static void removeUserId() {
        tl.remove();
    }
}
