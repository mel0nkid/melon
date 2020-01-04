package com.xinyuan.assist.service.study;

import com.xinyuan.assist.dao.WordDAO;
import com.xinyuan.assist.util.ExcelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.List;

@Service
public class EnglishServiceImpl implements EnglishService {

    @Autowired
    private WordDAO wordDao;

    /**
     * LOG
     */
    private Logger logger = LoggerFactory.getLogger(EnglishServiceImpl.class);

    @Override
    public String randomAWord() {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/word.xls");
            List<List<List<String>>> wdSheets = ExcelUtils.readExcel(inputStream, 0, 1);
            if (CollectionUtils.isEmpty(wdSheets)) {
                return "unknow";
            }
            List<List<String>> wdRows = wdSheets.get(0);
            if (CollectionUtils.isEmpty(wdRows)) {
                return "unknow";
            }
            int rows = wdRows.size();
            int rdNum = wordDao.generateWdNo(rows);
            return wdRows.get(rdNum).get(0);
        } catch (Exception e) {
            logger.error("", e);
        }
        return "unknow";
    }

}
