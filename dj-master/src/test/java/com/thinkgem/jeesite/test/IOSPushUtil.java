package com.thinkgem.jeesite.test;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

public class IOSPushUtil
{
    public static void main(String[] args) throws Exception
    {
        try
        {
            String deviceToken = "56378f94d620b0210a9228ea513a4ba2cbe61d0b29143116812da411009c0c9e";
            PayLoad payLoad = new PayLoad();
            payLoad.addAlert("盛科维的同胞们，大家好");
            payLoad.addBadge(1);// 消息推送标记数，小红圈中显示的数字。
            payLoad.addSound("default");

            PushNotificationManager pushManager = PushNotificationManager.getInstance();
            pushManager.addDevice("iPhone", deviceToken);

            // Connect to APNs
            String host = "gateway.sandbox.push.apple.com";
            int port = 2195;
            String certificatePath = "/Users/wangjinhan/Desktop/最近技术研究/java后台推送程序/developcm.p12";
            String certificatePassword = "";
            pushManager.initializeConnection(host, port, certificatePath, certificatePassword,
                    SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);

            // Send Push
            Device client = pushManager.getDevice("iPhone");
            pushManager.sendNotification(client, payLoad);
            pushManager.stopConnection();
            pushManager.removeDevice("iPhone");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /***********************
     * 代码有几点要注意： 1.String deviceToken =
     * "56378f94d620b0210a9228ea513a4ba2cbe61d0b29143116812da411009c0c9e"; 要发送到对应的设备
     * 2.payLoad.addBadge(1); 消息推送标记数，小红圈中显示的数字。服务器上作一个累计，当点击就计数为了，如果没有查看就一直累加。 3.String
     * certificatePath= "/Users/wangjinhan/Desktop/最近技术研究/java后台推送程序/developcm.p12"; 证书的路径，不能出错
     * 4.String certificatePassword= ""; 导出证书设置的密码，没有设置密码，就如上 这样就可以推送了。
     ***********************/
}
