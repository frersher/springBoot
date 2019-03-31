package threadLocal;

/**
 * @Auther: shchenbang
 * @Date: 2018/12/11 17:56
 * @Description:
 */
public class UserUtils {
    public static UserObj getCurrentUser(){
        return ThreadContext.getUser();
    }
}
