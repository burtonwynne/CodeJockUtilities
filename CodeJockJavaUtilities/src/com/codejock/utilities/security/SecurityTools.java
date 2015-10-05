package com.codejock.utilities.security;

import java.security.*;

public class SecurityTools
{
	public static byte[] getKeyedDigest(byte[] buffer, byte[] key) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(buffer);
            return md5.digest(key);
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

	public static byte[] getKeyedDigest( byte[] key ) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return md5.digest(key);
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

}