package com.ice.mr.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

/**
 * DES 加密工具
 */
public class DESUtil {
	public static final String PASSPORT_CRYPT_KEY = "chinahrt";
	private final static String DES = "DES";

    private static byte[] DESIV = { 0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };

	/**
	 * 加密
	 * 
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		//设置向量 
		AlgorithmParameterSpec iv = new IvParameterSpec(DESIV);
		// Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, iv, sr);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(src);
	}

	/**
	 * 解密
	 * 
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		//设置向量 
		AlgorithmParameterSpec iv = new IvParameterSpec(DESIV);
		// Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, iv, sr);
		// 现在，获取数据并解密
		// 正式执行解密操作
		return cipher.doFinal(src);
	}

	/**
	 * 密码解密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public final static String decrypt(String data) {
		try {
			return new String(decrypt(hex2byte(data),
					PASSPORT_CRYPT_KEY.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 密码加密
	 * 
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public final static String encrypt(String password) {
		try {
			return byte2hex(encrypt(password.getBytes(),
					PASSPORT_CRYPT_KEY.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}

		
	private static final char[] HEX_DIGITS = {
		'0', '1', '2', '3', '4', '5', '6', '7',
		'8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
	};
	
	private static String byte2hex(byte[] data) {
		char buf[] = new char[data.length * 2];
		for (int i = 0, x = 0; i < data.length; i++) {
			buf[x++] = HEX_DIGITS[(data[i] >>> 4) & 0xf];
			buf[x++] = HEX_DIGITS[data[i] & 0xf];
		}
		return new String(buf);
	}
	
	private static byte[] hex2byte(String hexString) { 
		if ((hexString.length() % 2) != 0) {
			throw new IllegalArgumentException("长度不是偶数");
		}
		
        int length = hexString.length() / 2;
        byte[] bytes = new byte[length];
        byte[] source = hexString.getBytes();

        for (int i = 0; i < bytes.length; ++i) {
            byte bh = Byte.decode("0x" + new String(new byte[]{source[i * 2]})).byteValue();
            bh = (byte)(bh << 4);
            byte bl = Byte.decode("0x" + new String(new byte[]{source[i * 2 + 1]})).byteValue();
            bytes[i] = (byte)(bh ^ bl);
        }
        return bytes;
    }
}