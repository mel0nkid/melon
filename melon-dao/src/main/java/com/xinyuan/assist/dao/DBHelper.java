package com.xinyuan.assist.dao;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ConcurrentMap;

@Repository
@PropertySource("classpath:application.properties")
public class DBHelper {

    /**
     * 数据库
     */
    private DB db;

    @Value("${db.filepath}")
    private String dbFile;

    private static String DB_MELON = "melonDB";

    private static Logger logger = LoggerFactory.getLogger(DBHelper.class);

    @PostConstruct
    public void init() {
        db = DBMaker.fileDB(dbFile)
                //.checksumHeaderBypass()
                .fileMmapEnableIfSupported()//表示如果支持的话使用mmap，也就是在64为操作系统开启，32位不开启，因为太小了；
                .fileMmapPreclearDisable()// 是对使用mmap的优化，官方文档说快快快
                .cleanerHackEnable()//这是针对使用mmap时，jvm所出现的bug所做的处理
                .closeOnJvmShutdown()//指的是jvm正常关闭时，将会关闭数据库
                .transactionEnable()//开启事务，写的速度下降，但是数据安全了
                .concurrencyScale(128)//6
                .make();
    }

    public boolean saveKV(String key, Object val) {
        try {
            ConcurrentMap map = db.hashMap(DB_MELON).createOrOpen();
            map.put(key, val);
            db.commit();
            return true;
        } catch (Exception e) {
            db.rollback();
            logger.error("", e);
        }finally {
            // todo
        }
        return false;
    }

    public <T> T getByK(String key) {
        try {
            ConcurrentMap map = db.hashMap(DB_MELON).createOrOpen();
            return (T) map.get(key);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }


    @PreDestroy
    public void destory() {
        db.close();
    }


    public String getDbFile() {
        return dbFile;
    }

    public void setDbFile(String dbFile) {
        this.dbFile = dbFile;
    }

}
