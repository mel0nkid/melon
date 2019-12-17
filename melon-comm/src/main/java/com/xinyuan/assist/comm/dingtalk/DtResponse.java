/**
 * MelonKid.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.xinyuan.assist.comm.dingtalk;

import java.util.Map;

/**
 * 响应钉钉
 *
 * @author melonkid
 * @version $Id: DtResponse.java, v 0.1 2019年12月03日 20:34 melonkid Exp $
 */
public class DtResponse {

    private boolean success;

    private String errorCode;

    private String errorMsg;

    private Map<String, String> fields;

    public static DtResponse success(Map<String, String> fields) {
        DtResponse dtResponse = new DtResponse();
        dtResponse.success = Boolean.TRUE;
        dtResponse.fields = fields;
        dtResponse.errorCode = "200";
        return dtResponse;
    }

    /**
     * Getter method for property <tt>success</tt>.
     *
     * @return property value of success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Setter method for property <tt>success</tt>.
     *
     * @param success value to be assigned to property success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Getter method for property <tt>errorCode</tt>.
     *
     * @return property value of errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Setter method for property <tt>errorCode</tt>.
     *
     * @param errorCode value to be assigned to property errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Getter method for property <tt>errorMsg</tt>.
     *
     * @return property value of errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * Setter method for property <tt>errorMsg</tt>.
     *
     * @param errorMsg value to be assigned to property errorMsg
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * Getter method for property <tt>fields</tt>.
     *
     * @return property value of fields
     */
    public Map<String, String> getFields() {
        return fields;
    }

    /**
     * Setter method for property <tt>fields</tt>.
     *
     * @param fields value to be assigned to property fields
     */
    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }
}