package com.xinyuan.assist.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author melonkid
 */
public class VisitDO implements Serializable {

    private static final long serialVersionUID = -4613374941817388911L;

    /**
     * 访客IP
     */
    private String ip;


    /**
     * 访问者
     */
    private UserDO vister;


    /**
     * 访问资源 url
     * <p>
     * 将来如果需要更丰富的统计时，在单独使用资源类
     */
    private String source;

    /**
     * 访问时间
     */
    private Date time;


    public UserDO getVister() {
        return vister;
    }

    public void setVister(UserDO vister) {
        this.vister = vister;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VisitDO visitDO = (VisitDO) o;
        return Objects.equals(ip, visitDO.ip) &&
                Objects.equals(vister, visitDO.vister) &&
                Objects.equals(source, visitDO.source) &&
                Objects.equals(time, visitDO.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, vister, source, time);
    }
}
