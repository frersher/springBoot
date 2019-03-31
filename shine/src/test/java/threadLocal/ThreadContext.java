package threadLocal;

/**
 * @Auther: shchenbang
 * @Date: 2018/12/11 17:25
 * @Description:当ThreadLocal碰上线程池
 */
public class ThreadContext {
    private static ThreadLocal<UserObj> userResouse = new ThreadLocal<>();

    public static UserObj getUser(){
        return userResouse.get();
    }

    public static void bindUser(UserObj userObj){
        userResouse.set(userObj);
    }

    public static UserObj unBindUser(){
        UserObj userObj = userResouse.get();
        userResouse.remove();
        return userObj;
    }


}
