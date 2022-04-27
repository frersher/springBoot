package com.shine.util;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.shine.controller.UserInfoController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SFTPUtils {

    private static final Logger log = LogManager.getLogger(UserInfoController.class);
    private  String host= "csftp-citi.ms.gxs.com"; // 主机名称/IP
    private  int port = 22; // 端口
    private  String username = "TOPPAYSFTP"; // 用户名
    private  String password ="9Iu74T2mFQ"; // 密码

    private  ChannelSftp sftp = null;
    private  Channel channel = null;
    private  Session session = null;

    public SFTPUtils(String host, String userName, String password) {
        this.host = host;
        this.username = userName;
        this.password = password;
    }

    public SFTPUtils(String host, int port, String userName, String password) {
        this.host = host;
        this.port = port;
        this.username = userName;
        this.password = password;
    }

    /**
     * 连接SFTP服务器
     *
     * @throws JSchException
     */
    public  void connect() throws JSchException {
        JSch jSch = new JSch();
        session = jSch.getSession(username, host, port);
        session.setPassword(password);
        session.setConfig(this.buildConfig());
        session.connect();
        channel = session.openChannel("sftp");
        channel.connect();
        sftp = (ChannelSftp) channel;
//        log.info("连接主机：{} 登录成功", host);
    }

    /**
     * 构建连接配置参数
     *
     * @return Properties
     */
    private  Properties buildConfig() {
        Properties properties = new Properties();
        properties.put("StrictHostKeyChecking", "no");
        return properties;
    }

    /**
     * 关闭连接
     */
    public void disconnect() {
        try {
            if (sftp.isConnected()) {
                sftp.disconnect();
            }
            if (channel.isConnected()) {
                channel.disconnect();
            }
            if (session.isConnected()) {
                session.disconnect();
            }
        } catch (Throwable e) {
            //ignore
        }
    }

    /**
     * 下载文件
     *
     * @param sftpPath   服务器路径，不指定路径默认是FTP的根路径，指定路径是指的SFTP的根路径下开始。
     *                   例如：SFTP根路径为：/sftp/file，那么默认下载文件会去根路径下载，而指定 sftpPath 也是从根路径下开始；
     *                   指定 sftpPath 为 word，那么是从 /sftp/file/word 路径中查找文件下载。为空表示忽略该参数。
     * @param fileName   文件名
     * @param toFilePath 下载保存文件路径，路径+文件名，例如：d:/test.txt
     * @return
     */
    public boolean downloadFile(String sftpPath, String fileName, String toFilePath) {
        FileOutputStream fileOutputStream = null;
        try {
            if (StringUtils.isNotBlank(sftpPath)) {
                sftp.cd(sftpPath);
            }
            fileOutputStream = new FileOutputStream(new File(toFilePath));
            sftp.get(fileName, fileOutputStream);
            return true;
        } catch (Exception e) {
            log.error("下载文件错误", e);
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    //ignore
                }
            }
        }
        return false;
    }

    /**
     * 上传文件
     *
     * @param sftpPath      服务器路径，不指定路径默认是FTP的根路径，指定路径是指的SFTP的根路径下开始。
     *                      例如：SFTP根路径为：/sftp/file，那么默认下载文件会去根路径下载，而指定 sftpPath 也是从根路径下开始；
     *                      指定 sftpPath 为 word，那么是从 /sftp/file/word 路径中查找文件下载。为空表示忽略该参数。
     * @param fileName      上传后文件名
     * @param localFilePath 文件路径，路径+文件名，例如：d:/test.txt
     * @return
     */
    public boolean uploadFile(String sftpPath, String fileName, String localFilePath) {
        FileInputStream inputStream = null;
        try {
            if (StringUtils.isNotBlank(sftpPath)) {
                sftp.cd(sftpPath);
            }
            inputStream = new FileInputStream(new File(localFilePath));
            sftp.put(inputStream, fileName);
            return true;
        } catch (Exception e) {
            log.error("上传文件错误", e);
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    //ignore
                }
            }
        }
        return false;
    }

    /**
     * 上传文件
     *
     * @param sftpPath    服务器路径，不指定路径默认是FTP的根路径，指定路径是指的SFTP的根路径下开始。
     *                    例如：SFTP根路径为：/sftp/file，那么默认下载文件会去根路径下载，而指定 sftpPath 也是从根路径下开始；
     *                    指定 sftpPath 为 word，那么是从 /sftp/file/word 路径中查找文件下载。为空表示忽略该参数。
     * @param fileName    上传后文件名
     * @param inputStream 文件输入流
     * @return
     */
    public boolean uploadFile(String sftpPath, String fileName, InputStream inputStream) {
        try {
            if (StringUtils.isNotBlank(sftpPath)) {
                sftp.cd(sftpPath);
            }
            sftp.put(inputStream, fileName);
            return true;
        } catch (Exception e) {
            log.error("上传文件错误", e);
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    //ignore
                }
            }
        }
        return false;
    }

    /**
     * 删除文件
     *
     * @param sftpPath 服务器路径，不指定路径默认是FTP的根路径，指定路径是指的SFTP的根路径下开始。
     *                 例如：SFTP根路径为：/sftp/file，那么默认下载文件会去根路径下载，而指定 sftpPath 也是从根路径下开始；
     *                 指定 sftpPath 为 word，那么是从 /sftp/file/word 路径中查找文件下载。为空表示忽略该参数。
     * @param fileName 文件名
     * @return
     */
    public boolean deleteFile(String sftpPath, String fileName) {
        try {
            if (StringUtils.isNotBlank(sftpPath)) {
                sftp.cd(sftpPath);
            }
            sftp.rm(fileName);
            return true;
        } catch (Exception e) {
            log.error("删除文件失败", e);
        }
        return false;
    }

    /**
     * 查询指定目录下信息
     *
     * @param sftpPath 服务器路径，不指定路径默认是FTP的根路径，指定路径是指的SFTP的根路径下开始。
     *                 例如：SFTP根路径为：/sftp/file，那么默认下载文件会去根路径下载，而指定 sftpPath 也是从根路径下开始；
     *                 指定 sftpPath 为 word，那么是从 /sftp/file/word 路径中查找文件下载。为空表示忽略该参数。
     * @return
     */
    public List<String> listFiles(String sftpPath) throws SftpException {
        Vector files = sftp.ls(sftpPath);
        List<String> result = new ArrayList<String>();
        Iterator iterator = files.iterator();
        while (iterator.hasNext()) {
            LsEntry isEntity = (LsEntry) iterator.next();
            result.add(isEntity.getFilename());
        }
        return result;
    }



}