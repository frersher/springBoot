package designModel.angency.multiAgency;

/**
 * Created by chenbang on 2019/12/9.
 */
public class TestMain {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        BaseService cicada = ProxyFactory.newInstane(Cicada.class);
        cicada.eat("");
    }
}
