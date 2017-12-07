package com.fzu.demo.common;

import com.mysql.jdbc.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Created by zzx on 2017/7/28.
 * @author zzx
 */
public class Md5Util {
    private static final Logger logger = LoggerFactory.getLogger(Md5Util.class);

    public Md5Util() {
    }

    public static String sign(String text) {
        return DigestUtils.md5Hex(getContentBytes(text, "utf-8"));
    }

    public static String sign(String text, String input_charset) {
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }

    private static byte[] getContentBytes(String content, String charset) {
        if (StringUtils.isNullOrEmpty(charset)) {
            return content.getBytes();
        } else {
            try {
                return content.getBytes(charset);
            } catch (UnsupportedEncodingException var3) {
                logger.error("MD5签名过程中出现错误,指定的编码集不对，目前指定编码为：" + charset, var3, new Object[0]);
                return null;
            }
        }
    }


}
