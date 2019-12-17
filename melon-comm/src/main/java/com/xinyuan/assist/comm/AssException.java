/**
 * MelonKid.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package java.com.xinyuan.assist.comm;

/**
 * @author melonkid
 * @version $Id: AssException.java, v 0.1 2019年12月03日 16:23 melonkid Exp $
 */
public class AssException extends RuntimeException {


    private static final long serialVersionUID = -1L;

    /**
     * 错误码
     */
    protected AssException   errorCode;

    /**
     * 是否可重试
     */
    protected boolean         canRetry         = false;


    public AssException(String message) {
        super(message);
    }


    public AssException(boolean canRetry, String message) {
        super(message);
        this.canRetry = canRetry;
    }

    public AssException(AssException errorCode, boolean canRetry) {
        super();
        this.errorCode = errorCode;
        this.canRetry = canRetry;
    }

    /**
     * Getter method for property <tt>errorCode</tt>.
     *
     * @return property value of errorCode
     */
    public AssException getErrorCode() {
        return errorCode;
    }

    /**
     * Setter method for property <tt>errorCode</tt>.
     *
     * @param errorCode value to be assigned to property errorCode
     */
    public void setErrorCode(AssException errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Getter method for property <tt>canRetry</tt>.
     *
     * @return property value of canRetry
     */
    public boolean isCanRetry() {
        return canRetry;
    }

    /**
     * Setter method for property <tt>canRetry</tt>.
     *
     * @param canRetry value to be assigned to property canRetry
     */
    public void setCanRetry(boolean canRetry) {
        this.canRetry = canRetry;
    }
}