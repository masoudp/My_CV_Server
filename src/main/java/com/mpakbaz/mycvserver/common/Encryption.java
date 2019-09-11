package com.mpakbaz.mycvserver.common;

import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {
    public static String encrypt(String strClearText, String strKey) throws Exception {
        String strData;

        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(strKey.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encrypted = cipher.doFinal(strClearText.getBytes());
            strData = Base64Utils.encodeToString(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return strData;
    }


    public static String decrypt(String strEncrypted, String strKey) throws Exception {
        String strData;

        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(strKey.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decrypted = cipher.doFinal(  Base64Utils.decodeFromString(strEncrypted) );
            strData = new String(decrypted);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return strData;
    }
}
