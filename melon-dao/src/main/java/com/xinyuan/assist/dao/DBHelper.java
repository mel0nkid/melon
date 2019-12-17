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

    private static String DB_VISIT_CNT = "visitCnt";

    private static Logger logger = LoggerFactory.getLogger(DBHelper.class);

    @PostConstruct
    private void init() {
        db = DBMaker.fileDB(dbFile)
                //.checksumHeaderBypass()
                .fileMmapEnableIfSupported()//1
                .fileMmapPreclearDisable()//2
                .cleanerHackEnable()//3
                .closeOnJvmShutdown()//4
                .transactionEnable()//5
                .concurrencyScale(128)//6
                .make();
    }

    public boolean saveKV(String key, Object val) {
        try {
            ConcurrentMap map = db.hashMap(DB_VISIT_CNT).createOrOpen();
            map.put(key, val);
            return true;
        } catch (Exception e) {
            logger.error("", e);
        }
        return false;
    }

    public Object getByK(String key) {
        try {
            ConcurrentMap map = db.hashMap(DB_VISIT_CNT).createOrOpen();
            Object val = map.get(key);
            return val;
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
