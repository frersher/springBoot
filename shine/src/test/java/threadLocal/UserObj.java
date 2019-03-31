package threadLocal;

/**
 * @Auther: shchenbang
 * @Date: 2018/12/11 17:21
 * @Description:
 */
public class UserObj {
    private String name;

    public UserObj(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
