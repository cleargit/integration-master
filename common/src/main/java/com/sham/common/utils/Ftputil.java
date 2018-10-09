package com.sham.common.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;

public class Ftputil {

    @Value("ftp.address:127.0.0.1")
    private static String ADDRESS;

    @Value("ftp.port:21")
    private static int PORT;

    @Value(("ftp.user:admin"))
    private static String USER;

    @Value("ftp.password:123")
    private static String PASSWORD;

    @Value("ftp.basepath:/root")
    private static String BASEPATH;
    public static boolean upload(String originName, InputStream file){
        boolean success=false;
        FTPClient ftpClient=new FTPClient();
        ftpClient.setControlEncoding("GBK");
        try {
            int reply;
            ftpClient.connect(ADDRESS,PORT);
            ftpClient.login(USER,PASSWORD);
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return success;
            }
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.makeDirectory(BASEPATH );
            ftpClient.changeWorkingDirectory(BASEPATH );
            ftpClient.storeFile(originName,file);
            file.close();
            ftpClient.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }
}
