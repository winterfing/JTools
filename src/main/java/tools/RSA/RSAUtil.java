package tools.RSA;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public class RSAUtil {

    public static final String PUBLIC_KEY = "RSAPublicKey";
    public static final String PRIVATE_KEY = "RSAPrivateKey";
    
    /**
     * 生成RSA的公钥和私钥
     */
    public static Map<String, String> initKey() throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);  //512-65536 & 64的倍数
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, String> keyMap = new HashMap<String, String>();
        
        //用base64转为字符串
        String sPublicKey = Base64.encode(publicKey.getEncoded());
        String sPrivateKey = Base64.encode(privateKey.getEncoded());
        keyMap.put(PUBLIC_KEY, sPublicKey);
        keyMap.put(PRIVATE_KEY, sPrivateKey);
        
        return keyMap;
    }
    
    /**
     * 获得公钥
     * @throws Exception 
     */
    public static RSAPublicKey getpublicKey(String sPublicKey) throws Exception{
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(sPublicKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKey publicKey = (RSAPublicKey)keyFactory.generatePublic(keySpec);
        return publicKey;
    }
    
    /**
     * 获得私钥
     * @throws Exception 
     */
    public static RSAPrivateKey getPrivateKey(String sPrivateKey) throws Exception{
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(sPrivateKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPrivateKey privateKey = (RSAPrivateKey)keyFactory.generatePrivate(keySpec);
        
        return privateKey;
    }
    
    /**
     * 公钥加密
     * @throws Exception 
     */
    public static byte[] encrypt(byte[] data, RSAPublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] cipherBytes = cipher.doFinal(data);
        return cipherBytes;
    }
    
    /**
     * 私钥解密
     */
    public static byte[] decrypt(byte[] data, RSAPrivateKey privateKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] plainBytes = cipher.doFinal(data);
        return plainBytes;
    }
}