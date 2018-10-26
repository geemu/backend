import com.chenfangming.backend.manage.ManageApplication;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * PACKAGE_NAME
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-26 13:08
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManageApplication.class)
public class PasswordTest {


    public static void main(String[] args) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("salt");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        String encoded = encryptor.encrypt("Chen1436863821.");
        System.out.println("加密后密码为:" + encoded);
        String decoded = encryptor.decrypt(encoded);
        System.out.println("解密后密码为:" + decoded);
    }

}
