package com.xinyuan.assist.dao;

import java.io.Serializable;

/**
 * @author melonkid
 */
public class VisitDO implements Serializable {

    private static final long serialVersionUID = -4613374941817388911L;

    private String vsIP;

    private String pageFlag;

    private Integer vsCt;

    public String getPageFlag() {
        return pageFlag;
    }

    public void setPageFlag(String pageFlag) {
        this.pageFlag = pageFlag;
    }

    public String getVsIP() {
        return vsIP;
    }

    public void setVsIP(String vsIP) {
        this.vsIP = vsIP;
    }

    public Integer getVsCt() {
        return vsCt;
    }

    public void setVsCt(Integer vsCt) {
        this.vsCt = vsCt;
    }

    @Override
    public String toString() {
        return "VisitDO{" +
            "vsIP='" + vsIP + '\'' +
            ", pageFlag='" + pageFlag + '\'' +
            ", vsCt=" + vsCt +
            '}';
    }
}
