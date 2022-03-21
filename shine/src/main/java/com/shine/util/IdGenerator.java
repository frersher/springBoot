package com.shine.util;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

@Slf4j
public class IdGenerator {
    private static final Logger logger = LoggerFactory.getLogger(IdGenerator.class);

    public static String generate() {
        String id = "";
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            String[] tokens = hostName.split("\\.");
            if (tokens.length > 0) {
                hostName = tokens[tokens.length - 1];
            }
            char[] randomChars = new char[8];
            int count = 0;
            Random random = new Random();
            while (count < 8) {
                int randomAscii = random.nextInt(122);
                if (randomAscii >= 48 && randomAscii <= 57) {
                    randomChars[count] = (char) ('0' + (randomAscii - 48));
                    count++;
                } else if (randomAscii >= 65 && randomAscii <= 90) {
                    randomChars[count] = (char) ('A' + (randomAscii - 65));
                    count++;
                } else if (randomAscii >= 97 && randomAscii <= 122) {
                    randomChars[count] = (char) ('a' + (randomAscii - 97));
                    count++;
                }
            }
            id = String.format("%s-%d-%s", hostName,
                    System.currentTimeMillis(), new String(randomChars));
        } catch (UnknownHostException e) {
            logger.warn("Failed to get the host name.", e);
        }
        return id;
    }

    public static void main(String[] args) {
        System.out.println(generate());
    }

//    目录设置是否合理、模块划分是否清晰、代码结构是否满足“高内聚、松耦合”？
//    是否遵循经典的设计原则和设计思想（SOLID(单依职责)、DRY（不要开发重复代码）、KISS（尽量保证代码简洁，使用通用技术(同事都懂的技术)、不重复造轮子、不过度优化）
//    、YAGNI（不去设计与开发当前功能用不到的代码）、LOD（迪米特法则：不该有直接依赖关系的类之间，不要有依赖；有依赖关系的类之间，尽量只依赖必要的接口） 等）？
//    设计模式是否应用得当？是否有过度设计？
//    代码是否容易扩展？如果要添加新功能，是否容易实现？
//    代码是否可以复用？是否可以复用已有的项目代码或类库？是否有重复造轮子？
//    代码是否容易测试？单元测试是否全面覆盖了各种正常和异常的情况？
//    代码是否易读？是否符合编码规范（比如命名和注释是否恰当、代码风格是否一致
//    等）？
}