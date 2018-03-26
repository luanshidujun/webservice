package com.thinkgem.jeesite.common.persistence.dialect.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 多数据源切换
 * 
 * @author xiaohang
 * @date 2017/02/28
 */
public class DynamicDataSource extends AbstractRoutingDataSource
{
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    private static final String DATESOURCE_ONE = "dataSource";
    private static final String DATESOURCE_TWO = "dataSource2";

    /**
     * 
     * @author sa
     * @date 2012-5-18 下午4:06:44
     * @return the currentLookupKey
     */
    public static String getCurrentLookupKey()
    {
        // System.out.println((String) contextHolder.get()); //测试使用
        return (String) contextHolder.get();
    }

    /**
     * 
     * @author xiaohang
     * @date 2017/02/28
     */
    public static void setdataSourceOne()
    {
        contextHolder.set(DATESOURCE_ONE);
    }

    public static void setdataSourceTwo()
    {
        contextHolder.set(DATESOURCE_TWO);
    }

    /*
     * (non-Javadoc) 这个方法为spring核心执行数据库是会代用的方法
     * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#
     * determineCurrentLookupKey()
     */
    @Override
    protected Object determineCurrentLookupKey()
    {
        return getCurrentLookupKey();
    }
}