package com.thinkgem.jeesite.test;

public class DownLoadTest
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        String filepath = "http://127.0.0.1:8080/jeesite/userfiles/12345.exe";
        MultiTheradDownLoad load = new MultiTheradDownLoad(filepath, 5);
        load.downloadPart();
    }
}