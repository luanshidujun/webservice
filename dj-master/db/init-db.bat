@echo off
rem /**
rem  * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
rem  *
rem  * Author: ThinkGem@163.com
rem  */
echo.
echo [��Ϣ] �ؽ��������ݿⲢ�����ʼ���ݡ�(һ��Ҫ�ֶ����ÿ����ݿ⣬����ȷ��jeesite.properties��jdbc.url������������)
echo.
pause
echo.
echo [��Ϣ] �˲���������������ݱ�����ݣ����ָ���ʼ״̬��(һ��Ҫ�ֶ����ÿ����ݿ⣬����ȷ��jeesite.properties��jdbc.url������������)
echo.
echo [��Ϣ] ȷ�ϼ����𣿷�����رմ��ڡ�(һ��Ҫ�ֶ����ÿ����ݿ⣬����ȷ��jeesite.properties��jdbc.url������������)
echo.
pause
echo.
echo [��Ϣ] �����ȷ�ϼ����𣿷�����رմ��ڡ�(һ��Ҫ�ֶ����ÿ����ݿ⣬����ȷ��jeesite.properties��jdbc.url������������)
echo.
pause
echo.

cd %~dp0
cd ..

call mvn antrun:run -Pinit-db

cd db
pause