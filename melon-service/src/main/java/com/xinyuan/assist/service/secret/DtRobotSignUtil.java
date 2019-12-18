/**
 * MelonKid.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.xinyuan.assist.service.secret;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.xinyuan.assist.dao.DingTalkRobotDO;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author melonkid
 * @version $Id: SignUtil.java, v 0.1 2019年12月03日 14:47 melonkid Exp $
 */
public class DtRobotSignUtil {

    private static Logger logger = LoggerFactory.getLogger(DtRobotSignUtil.class);

    public static String generateCurr(DingTalkRobotDO robotDO) {
        try {
            String webhook = robotDO.getWebhook();
            String secret = robotDO.getSecret();
            Long timestamp = System.currentTimeMillis();
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            String base64Code = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
            return webhook + "&" + "timestamp" + "=" + timestamp
                    + "&" + "sign" + "=" + base64Code;
        } catch (Exception e) {
            logger.error("", e);
        }
        return "";
    }
}