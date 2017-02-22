package cn.wwah.common.cipher;

import java.security.MessageDigest;

/**
 * @Description: SHA加密
 * @author: jeasinlee
 * @date: 2017-01-12 11:15
 */
public class SHA {
    public static byte[] encrypt(byte[] data) throws Exception {
        MessageDigest sha = MessageDigest.getInstance(CipherType.SHA.getType());
        sha.update(data);
        return sha.digest();
    }
}
