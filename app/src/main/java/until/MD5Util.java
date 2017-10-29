package until;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	/**
	 * 获取字符串的md5值
	 * @param s
	 * @return
	 */
	public static String getMd5(String s) {
		if(TextUtils.isEmpty(s)){
			return s;
		}
	    try {
	        // Create MD5 Hash
	        MessageDigest digest = MessageDigest.getInstance("MD5");
	        digest.update(s.getBytes());
	        byte messageDigest[] = digest.digest();

	        // Create Hex String
	        StringBuffer hexString = new StringBuffer();
	        for (int i=0, v=0; i<messageDigest.length; i++){
	        	v = 0xFF & messageDigest[i];
	        	if(v<16){
	        		hexString.append("0");
	        	}
	            hexString.append(Integer.toHexString(v));
//				hexString.append(
//	            String.format("%02x", 0xFF & messageDigest[i]));
	        }
	        return hexString.toString();
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return s;
	}

	/**
	 * 获取16位MD5
	 * @param s
	 * @return
     */
	public static String get16bitMd5(String s){
		String ret = getMd5(s);
		ret = ret.substring(8, 24);
		return ret;
	}

}
