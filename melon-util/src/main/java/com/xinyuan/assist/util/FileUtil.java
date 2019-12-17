package com.xinyuan.assist.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * FileUtil
 *
 * @author liangyh
 * @date 2018/7/25
 */
public class FileUtil {

    /**
     * 从资源目录读取文件内容
     *
     * @param resourcePath
     * @return
     */
    public static String readFileFromResource(String resourcePath) {
        InputStream is = null;
        BufferedReader rd = null;
        try {
            is = FileUtil.class.getClassLoader().getResourceAsStream(resourcePath);
            rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                buffer.append(System.lineSeparator() + line);
            }
            return buffer.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (rd != null) {
                try {
                    rd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

}
