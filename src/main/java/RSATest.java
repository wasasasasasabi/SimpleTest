import lombok.SneakyThrows;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSATest {

    @SneakyThrows
    public static void main(String[] args) {
        /**
         * 生成公私钥文件
         */
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair kp = kpg.generateKeyPair();
        PublicKey public1 = kp.getPublic();
        PrivateKey private1 = kp.getPrivate();
        System.out.println("ok");

        String encodeBuffer = new BASE64Encoder().encodeBuffer(public1.getEncoded());
        System.out.println("公钥"+public1);
        System.out.println("公钥编码"+encodeBuffer);
        System.out.println("================================");
        String encodeBuffer2 = new BASE64Encoder().encodeBuffer(private1.getEncoded());
        System.out.println("私钥"+private1);
        System.out.println("私钥编码"+encodeBuffer2);
        System.out.println("================================");

        /**
         * BASE64解密公私钥字符串为字节数组，再生成公私钥对象
         */
        KeyFactory key = KeyFactory.getInstance("RSA");
        byte[] decodeBuffer = new BASE64Decoder().decodeBuffer(encodeBuffer);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decodeBuffer);
        PublicKey generatePublic = key.generatePublic(x509EncodedKeySpec);
        System.out.println("公钥对象"+generatePublic);

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(new BASE64Decoder().decodeBuffer(encodeBuffer2));
        PrivateKey generatePrivate = key.generatePrivate(pkcs8EncodedKeySpec);
        System.out.println("私钥对象"+generatePrivate);
        System.out.println("================================");
        /**
         * 加解密
         */
        String oriMsg = "古德拉克";
        String charset = "UTF-8";
        byte[] bytes = oriMsg.getBytes(charset);

        /**
         * 加密，处理为BASE64后的字符串，方便传递
         */
        Cipher cipher = Cipher.getInstance("RSA");
        int opmode = Cipher.ENCRYPT_MODE;
        cipher.init(opmode , generatePublic);
        byte[] doFinal = cipher.doFinal(bytes);
        String encodeBuffer3 = new BASE64Encoder().encodeBuffer(doFinal);
        System.out.println("公钥加密密码:"+encodeBuffer3);
        System.out.println("================================");
        int opmode2 =Cipher.DECRYPT_MODE;
        cipher.init(opmode2, generatePrivate);
        byte[] decodeBuffer2 = new BASE64Decoder().decodeBuffer(encodeBuffer3);
        byte[] doFinal2 = cipher.doFinal(decodeBuffer2);
        String decOriMsg = new String(doFinal2, charset);
        System.out.println("私钥解密密码--"+decOriMsg);

    }
}
