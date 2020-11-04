package com.qpyy.libcommon.utils;

import com.blankj.utilcode.util.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

public class DownloadUtils {

    public static boolean writeResponseBodyToDisk(ResponseBody body, String filePath, String fileName) {
        try {
            //判断文件夹是否存在
            File files = new File(filePath);//跟目录一个文件夹
            if (!files.exists()) {
                //不存在就创建出来
                files.mkdirs();
            }
            File file = new File(filePath,fileName);
            //创建一个文件
            FileUtils.createOrExistsFile(file);
            //初始化输入流
            InputStream inputStream = null;
            //初始化输出流
            OutputStream outputStream = null;
            try {
                //设置每次读写的字节
                byte[] fileReader = new byte[4096];
                //请求返回的字节流
                inputStream = body.byteStream();
                //创建输出流
                outputStream = new FileOutputStream(file);
                //进行读取操作
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    //进行写入操作
                    outputStream.write(fileReader, 0, read);
                }

                //刷新
                outputStream.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                if (inputStream != null) {
                    //关闭输入流
                    inputStream.close();
                }
                if (outputStream != null) {
                    //关闭输出流
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
