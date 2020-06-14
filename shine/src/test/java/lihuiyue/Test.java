package lihuiyue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: demo
 * @description: ${description}
 * @author: bang.chen
 * @create: 2020-05-13 12:15
 **/
public class Test {

    @org.junit.Test
    public void test1(){
        List<Employee> employees = Arrays.asList(new Employee("张三",12,34.9),
                                                 new Employee("李四",18,50.9),
                                                 new Employee("wangwu",21,60.9));

        employees.stream().filter((a)-> a.getAge()>12).forEach(System.out::print);

        employees.stream().map(Employee::getAge).forEach(System.out::print);
    }

    @org.junit.Test
    public void test2(){
        Consumer<String> consumer = (x)-> System.out.println(x);

        consumer.accept("wshfsdfs");
    }

    @org.junit.Test
    public void test3(){
        Comparator<Integer> comparator =(x,y)->{
            System.out.println("在次比较");
            return Integer.compare(x,y);
        };

        int compare = comparator.compare(1, 2);
        System.out.println(compare);
    }

}
