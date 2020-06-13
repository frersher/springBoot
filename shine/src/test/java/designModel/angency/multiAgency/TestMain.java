package designModel.angency.multiAgency;

/**
 * TODO
 *
 * @author cb
 * @since 2020/6/13
 */

public class TestMain {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        BaseService cicada =  ProxyFaxtory.newInstance(Cicada.class);
        cicada.eat();
    }
}
