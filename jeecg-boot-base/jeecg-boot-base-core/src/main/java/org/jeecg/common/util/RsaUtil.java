package org.jeecg.common.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @ClassName RsaUtil
 * @Description TODO
 * @Author 刘杨刚/Microdream
 * @Date 2021/1/15
 * @Version 1.0
 */
public class RsaUtil {
    private static String PRIVATEKEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANL8FKQAVYsAMDbw\n" +
            "YErrH+UPN3nyj7NcZy/XsrMmcZP5aIZI4gqFSWZT9DExr4c/y1Md+ttGIKkAW3NJ\n" +
            "nPzDGncDy/Mjl/zyopokkZcPij8X29EgzqlgnjSA0hbEyAHL2Z5LFyU0sUtT7Gob\n" +
            "bSVF9Epq24L7tJzMLE1obeFlNQatAgMBAAECgYAU8RJ3aNUU0/L1MQzNwuLKkKUY\n" +
            "BJyA2BYh6I4DPDif0Gywj4FoO+yaLJEbZOwgR5O970bcBuzs+W0aV18us4rnwPTS\n" +
            "leYs9PYcuIsNICjKHDuTTOM8JhcDMjIq8LflHyA+jUyZ+WJm/Rp6iCPPtmVs9zfG\n" +
            "AFDF0SyNDOwh5FQnHQJBAP5TBRcNo50HVlynh83Fq9Fvsfnf/LwjQHNKJISPzKXD\n" +
            "LJYXxMGmrskbZetQpimV6PhscA+IaGntDUTv6zU5n1sCQQDUX/U48Qe1RjF0kh2c\n" +
            "6naBlZSHIfOf/M+4eNQwSZvIuYqOvTYay1ClrVYZbgFTf6kB2fOh4W0YcXU1glZZ\n" +
            "7piXAkEA0smgWGEXkFi6fYTlC+mf5LA8xKue35ggHO4AolZE+cZwN95vV0l9aHVq\n" +
            "3cQ0VBdJxkyp/FXEpLay3sYzHiQqLQJBALhcRMiVlJdsw+nqHgLmFtIPQ8y7UH6G\n" +
            "PNxrJ+hzPeVWDE41Ow6viOcKPj/9GvGdVN3iknjo9IfPlmxJJSUU1ykCQQCJKtDt\n" +
            "XvbfF3cUo+EUKs71awun37pjgh5IuSjRFlqDwQJAaWCR4R+OYqFGbXCNkbk+hyUl\n" +
            "V2Y3T3yzKi3qEGNx";

    /**
     * 加密
     *
     * @param params
     * @return
     */
    public static String encrypt(String params) {
        RSA rsa = new RSA(PRIVATEKEY, null);
        byte[] encrypt = rsa.encrypt(params, KeyType.PrivateKey);
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encodeBuffer(encrypt).replaceAll("\r\n", "");
    }

    /**
     * 解密
     *
     * @param str
     * @return
     */
    public static String desEncrypt(String str) {
        RSA rsa = new RSA(PRIVATEKEY, null);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] dncrypt = new byte[0];
        try {
            dncrypt = decoder.decodeBuffer(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] dncrypt2 = rsa.decrypt(dncrypt, KeyType.PrivateKey);
        return StrUtil.str(dncrypt2, CharsetUtil.CHARSET_UTF_8);
    }
}
