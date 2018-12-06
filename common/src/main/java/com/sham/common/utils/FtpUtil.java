package com.sham.common.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class FtpUtil {

    @Value("${ftp.hostName}")
    private String hostName;

    @Value(("${ftp.port}"))
    private Integer port;

    @Value("${ftp.userName}")
    private String userName;

    @Value("${ftp.password}")
    private String password;


    private FTPClient getFtpconnect() {
        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(hostName, port);
            ftpClient.login(userName, password);
            ftpClient.setControlEncoding("UTF-8");//解决中文文件名
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);//切换流
            ftpClient.enterLocalPassiveMode();//被动模式
            ftpClient.setKeepAlive(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ftpClient;
    }

    //上传文件
    public boolean uploadFile(String fileName, String remotePath, InputStream is) throws IOException {
        boolean result = false;
        FTPClient ftpClient = getFtpconnect();
        if (ftpClient == null) {
            return result;
        }
        if (!ftpClient.isConnected()) {
            return result;
        }
        ftpClient.changeWorkingDirectory(remotePath);
        result = ftpClient.storeFile(fileName, is);
        close(ftpClient);
        return result;
    }

    //上传文件或文件夹
    public void uploadFile(String filePath, String remotePath) throws IOException {
        uploadFile(new File(filePath), remotePath);
    }

    //上传文件或文件夹
    public void uploadFile(File file, String remotePath) throws IOException {
        FTPClient ftpClient = getFtpconnect();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            String path = remotePath + "/" + file.getName();
            ftpClient.makeDirectory(path);
            for (File info : files) {
                if (info.isDirectory()) {
                    ftpClient.changeWorkingDirectory(path);
                    String subFileName = info.getName();
                    if (!ftpClient.changeWorkingDirectory(path + "/" + subFileName)) {
                        ftpClient.makeDirectory(subFileName);
                    }
                    uploadFile(info, path);
                } else if (info.isFile()) {
                    uploadFile(info.getName(), path, new FileInputStream(info));
                }
            }

        } else {
            uploadFile(file.getName(), remotePath, new FileInputStream(file));
        }
        close(ftpClient);
    }

    public void uploadTry(AtomicInteger failCount, final int maxFailCount, String filePath, String remotePath) {
        try {
            uploadFile(filePath, remotePath);
        } catch (Exception e) {
            int count = failCount.incrementAndGet();
            if (count < maxFailCount) {
                uploadTry(failCount, maxFailCount, filePath, remotePath);
                LoggerUtils.error("上传文件到FTP服务器失败，第" + count + "失败后再次传输，最大失败重传次数为：" + maxFailCount);
            }
            e.printStackTrace();
        }
    }

    private void close(FTPClient ftpClient) throws IOException {
        if (ftpClient != null) {
            ftpClient.logout();
        }
    }


}
