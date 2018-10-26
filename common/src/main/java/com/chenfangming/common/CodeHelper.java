package com.chenfangming.common;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

/**
 * 属性加解密工具类
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-26 13:43
 */
@Slf4j
public class CodeHelper {
    /** 加解密配置 **/
    private static SimpleStringPBEConfig config = new SimpleStringPBEConfig();

    static {
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations(1000);
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        config.setPoolSize(1);
    }

    /**
     * 主函数
     * @param args 参数
     */
    public static void main(String[] args) {
        //  需要加密的字符串
        final String text = "这是需要加密的字符串";
        //  密钥
        final String password = "this is my password";
        log.info("需要加密的字符串为:{},密钥为:{}", text, password);
        //  加密
        String encoded = encode(text, password);
        log.info("加密后结果为:{}", encoded);
        //  解密
        String decoded = decode(encoded, password);
        log.info("解密后结果为:{}", decoded);
        if (text.equals(decoded)) {
            log.info("加解密结果一致，认证通过");
        } else {
            log.error("加解密结果不一致，认证不通过");
        }
    }

    /**
     * 加密
     * @param text     需要加密的文本
     * @param password 密钥
     * @return 加密后的文本
     */
    private static String encode(final String text, final String password) {
        PooledPBEStringEncryptor code = new PooledPBEStringEncryptor();
        code.setPassword(password);
        code.setConfig(config);
        return code.encrypt(text);
    }

    /**
     * 解密
     * @param encoded  需要解密的文本
     * @param password 密钥
     * @return 加密后的文本
     */
    private static String decode(final String encoded, final String password) {
        PooledPBEStringEncryptor code = new PooledPBEStringEncryptor();
        code.setPassword(password);
        code.setConfig(config);
        return code.decrypt(encoded);
    }
}
