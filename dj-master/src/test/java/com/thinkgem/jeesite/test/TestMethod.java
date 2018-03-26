package com.thinkgem.jeesite.test;


public class TestMethod
{
    public TestMethod()
    {
        try
        {
            SiteInfoBean bean = new SiteInfoBean(
                    "http://127.0.0.1:8080/jeesite/userfiles/12345.exe", "D:\\userfiles", "1.exe", 5);

            SiteFileFetch fileFetch = new SiteFileFetch(bean);
            fileFetch.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new TestMethod();
    }
}