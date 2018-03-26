package com.thinkgem.jeesite.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestDownload
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        HttpURLConnection httpURLConnection = null;
        URL url = null;
        BufferedInputStream bis = null;
        byte[] buf = new byte[10240];
        int size = 0;
        String fileName = "1.zip";
        String filePath = "D:\\userfiles";
        String remoteUrl = "http://127.0.0.1:8080/jeesite/arcSyncHttp/12345.zip";

        // 检查本地文件
        RandomAccessFile rndFile = null;
        File file = new File(filePath + "\\" + fileName);
        long remoteFileSize = getRemoteFileSzie(remoteUrl);
        // long nPos = 0;
        long localFileSzie = 0;
        if (file.exists())
        {
            localFileSzie = file.length();

            if (localFileSzie < remoteFileSize)
            {
                if (localFileSzie == 0)
                {
                    System.out.println("文件不存在，开始下载...");
                }
                else
                {
                    System.out.println("文件续传...");
                }
            }
            else
            {
                System.out.println("文件存在，重新下载...");
                file.delete();
                try
                {
                    file.createNewFile();
                    localFileSzie = file.length();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            // 建立文件
            try
            {
                file.createNewFile();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        // 下载文件
        try
        {
            url = new URL(remoteUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // 设置User-Agent
            httpURLConnection.setRequestProperty("User-Agent", "Net");
            // 设置续传开始
            System.out.println("本地文件大小：" + localFileSzie);
            httpURLConnection.setRequestProperty("Range", "bytes=" + localFileSzie + "-");
            // 获取输入流
            bis = new BufferedInputStream(httpURLConnection.getInputStream());

            // long remoteFileSize = httpURLConnection.getContentLength();
            System.out.println("返回结果码：" + httpURLConnection.getResponseCode());
            System.out.println("文件名：" + httpURLConnection.getHeaderField("Content-Disposition"));
            if (localFileSzie < remoteFileSize)
            {
                if (localFileSzie == 0)
                {
                    System.out.println("文件不存在，开始下载...");
                }
                else
                {
                    System.out.println("文件续传...");
                }
            }
            else
            {
                System.out.println("文件存在，重新下载...");
                file.delete();
                try
                {
                    file.createNewFile();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            rndFile = new RandomAccessFile(filePath + "\\" + fileName, "rw");

            rndFile.seek(localFileSzie);
            int i = 0;
            while ((size = bis.read(buf)) != -1)
            {
                if (i > 10000)
                    break;
                rndFile.write(buf, 0, size);
                i++;
            }
            System.out.println("i=" + i);
            httpURLConnection.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static long getRemoteFileSzie(String url)
    {
        long size = 0;
        try
        {
            HttpURLConnection httpUrl = (HttpURLConnection) (new URL(url)).openConnection();
            size = httpUrl.getContentLength();
            httpUrl.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return size;
    }
}
