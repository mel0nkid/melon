/**
 * MelonKid.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.xinyuan.assist.service;

/**
 * @author melonkid
 * @version $Id: PushCallback.java, v 0.1 2019年12月03日 16:38 melonkid Exp $
 */
public interface PushCallback {

    /**
     *
     * @return
     */
    String[] generatePushUrls();

    String generateMsg();
}