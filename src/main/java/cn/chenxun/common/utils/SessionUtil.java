package cn.chenxun.common.utils;

import cn.chenxun.common.enums.SessionEnum;
import cn.chenxun.system.model.user.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Session 工具类
 *
 * @author zhaozhenyao
 * @date 2018/5/10
 */
public class SessionUtil {

    /**
     * 获取当前Session
     *
     * @return 当前Session
     */
    public static Session getCurrentSession() {
        Subject subject = SecurityUtils.getSubject();
        return subject == null ? null : subject.getSession();
    }


    /**
     * 获取当前的用户对象
     *
     * @return 当前用户
     */
    public static String getCurrentLoginName() {
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        return String.valueOf(principal).replace("\"", "");
    }


    /**
     * 存储参数到Session
     *
     * @param key   存储的key
     * @param value 存储的value
     */
    public static void setAttribute(String key, Object value) {
        Session session = getCurrentSession();
        if (session != null) {
            session.setAttribute(key, value);
        }
    }

    /**
     * 存储参数到Session
     *
     * @param sessionEnum 存储的key（枚举）
     * @param value       存储的value
     */
    public static void setAttribute(SessionEnum sessionEnum, Object value) {
        setAttribute(sessionEnum.getValue(), value);
    }

}
