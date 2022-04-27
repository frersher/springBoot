package com.shine.util;
 
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import java.io.*;
import java.util.Objects;
 
/**
 * @Description: 文件相关工具类
 *
 * @Date: 2021/10/26
 * @Author: jiangXueZhi
 */
@Slf4j
public class FileUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);
 
    /**
     * 获取文件的后缀名
     *
     * @param appendDot 是否拼接.
     * @return
     */
    public static String getFileSuffix(String fullFileName, boolean appendDot) {
        if (fullFileName == null || fullFileName.indexOf(".") < 0 || fullFileName.length() <= 1) {
            return "";
        }
 
        return (appendDot ? "." : "") + fullFileName.substring(fullFileName.lastIndexOf(".") + 1);
    }
 
    /**
     * 往本地文件中写入内容
     * 若本地文件不存在，则自动创建,反之则覆盖
     *
     * @param filePath       本地文件地址
     * @param content        写入的内容
     * @param uniqueFileName 是否要求文件名唯一
     * @return 本地文件地址
     */
    public static String writeStrToFile(String filePath, String content, boolean uniqueFileName) {
        if (uniqueFileName) {
            String[] split = filePath.split("\\.");
            filePath = split[0] + "_" + NumberUtils.getRandomNickname(5) + "." + split[1];
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
            fos.write(content.getBytes());
            LOGGER.info("文件写入成功，文件地址：{}", filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }
 
    /**
     * 读取文件的内容
     *
     * @param path 文件地址
     * @return 文件内容
     */
    public static String readStrFromFile(String path) {
        StringBuilder sb = new StringBuilder();
        Reader reader = null;
        try {
            File file = new File(path);
            reader = new InputStreamReader(new FileInputStream(file));
            int ch;
            while ((ch = Objects.requireNonNull(reader).read()) != -1) {
                sb.append((char) ch);
            }
            LOGGER.info("文件读取成功，文件地址：{}", path);
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
 
    /**
     * 删除单个文件
     *
     * @param path 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String path) {
        File file = new File(path);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            LOGGER.info("文件删除成功，文件地址：{}", path);
            return file.delete();
        }
        return false;
    }
 
    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param   sPath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        if (files != null) {
            for (File file : files) {
                //删除子文件
                if (file.isFile()) {
                    flag = deleteFile(file.getAbsolutePath());
                    if (!flag) break;
                } //删除子目录
                else {
                    flag = deleteDirectory(file.getAbsolutePath());
                    if (!flag) break;
                }
            }
        }
        if (!flag) return false;
        //删除当前目录
        LOGGER.info("目录删除成功，目录地址：{}", sPath);
        return dirFile.delete();
    }
}