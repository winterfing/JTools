package tools.RSA;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

public class RSATest
{

    /**
     * @throws Exception 
     * @Title: main
     * @Description: RSA加密解密
     *  RSA 公钥加密，私钥解密
     *      私钥加签，公钥验签
     * @author Dangzhang
     * @param args
     * @throws
     */
    public static void main(String[] args) throws Exception
    {
        String words = "人生路漫漫---也许，我可以不用那么急，还是，我太懈怠---";
        Map<String, String> rsaMap = RSAUtil.initKey();
        System.out.println("RSAkeyMap："+rsaMap);
        
        RSAPublicKey publicKey = RSAUtil.getpublicKey(rsaMap.get(RSAUtil.PUBLIC_KEY));
        System.out.println("转换后的RSAPublicKey公钥：" + publicKey);
        
        RSAPrivateKey privateKey = RSAUtil.getPrivateKey(rsaMap.get(RSAUtil.PRIVATE_KEY));
        System.out.println("转换后的RSAPrivateKey私钥："+privateKey);
        
        //公钥加密
        byte [] miwen = RSAUtil.encrypt(words.getBytes(), publicKey);
        System.out.println("公钥加密后："+new String(miwen));
        
        //私钥解密
        byte [] mingwen = RSAUtil.decrypt(miwen, privateKey);
        System.out.println("私钥解密后："+new String(mingwen));
        
    }
}
