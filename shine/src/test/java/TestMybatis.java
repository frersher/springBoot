import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * TODO
 *
 * @author cb
 * @since 2020/6/13
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestMybatis.class)
public class TestMybatis {

    @Test
    public void selectTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sessionFactory.openSession();
        List<Object> findAllUser = sqlSession.selectList("selectByUserInfo");
        System.out.println(findAllUser);
    }

}
