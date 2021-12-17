package common.core.util;

import cn.hutool.core.lang.Assert;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author asd <br>
 * @create 2021-12-17 9:28 AM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
public class AESUtil {

    private static final String AES = "AES";
    private static final String DEFAULT_ALG = "AES/ECB/PKCS5PADDING";

    public static String encrypt(String data, String secret) {
        return encrypt(data, secret, DEFAULT_ALG);
    }
    // 加密
    public static String encrypt(String data, String secret, String alg) {

        Assert.notEmpty(secret, "Encrypt Key is Empty");
        String decryptAlgorithm = Optional.ofNullable(alg).orElse(DEFAULT_ALG);
        try {
            byte[] key = Arrays.copyOf(secret.getBytes(UTF_8), 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, AES);
            Cipher cipher = Cipher.getInstance(decryptAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] original = cipher.doFinal(data.getBytes(UTF_8));
            // return new BASE64Encoder().encode(original);
            return Base64.getEncoder().encodeToString(original);
        } catch (Exception e) {
            log.info("Error while encrypting: ", e);
            // throw new BaseException(CommonResponseEnum.ENCODING_ERROR);
        }

        return null;
    }

    public static String decrypt(String data, String secret) {
        return decrypt(data, secret, DEFAULT_ALG);
    }
    /**
     * @param data
     * @param secret
     * @param alg AES/ECB/PKCS5PADDING
     * @return
     * @throws Exception
     */
    public static String decrypt(String data, String secret, String alg) {

        Assert.notEmpty(secret, "Decrypt Key is Empty");
        String decryptAlgorithm = Optional.ofNullable(alg).orElse(DEFAULT_ALG);
        try {
            byte[] key = Arrays.copyOf(secret.getBytes(UTF_8), 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, AES);
            Cipher cipher = Cipher.getInstance(decryptAlgorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            // byte[] original = cipher.doFinal(new BASE64Decoder().decodeBuffer(data));
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(data));
            return new String(original, UTF_8);
        } catch (Exception e) {
            log.info("Error while decrypting: ", e);
            // throw new BaseException(CommonResponseEnum.DECODING_ERROR);
        }

        return null;
    }
}
