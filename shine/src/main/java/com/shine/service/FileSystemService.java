package com.shine.service;

import java.io.File;
import java.io.InputStream;

/**
 * sftp
 */
public interface FileSystemService {

    boolean uploadFile(String targetPath, InputStream inputStream) throws Exception;

    boolean uploadFile(String targetPath, File file) throws Exception;

    File downloadFile() throws Exception;

    boolean deleteFile(String targetPath) throws Exception;

    Boolean isExistDir(String filePath) throws Exception;
}
