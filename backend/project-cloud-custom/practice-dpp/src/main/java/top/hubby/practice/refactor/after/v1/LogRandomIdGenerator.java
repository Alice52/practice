package top.hubby.practice.refactor.after.v1;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * 第一轮重构: 提高代码的可读性
 *
 * <pre>
 *    1. hostName 变量不应该被重复使用, 尤其当这两次使用时的含义还不同的时候
 *    2. 将获取 hostName 的代码抽离出来, 定义为 getLastfieldOfHostName() 函数
 *    3. 删除代码中的魔法数, 比如，57、90、97、122
 *    4. 将随机数生成的代码抽离出来, 定义为 generateRandomAlphameric() 函数
 *    5. generate() 函数中的三个 if 逻辑重复了, 且实现过于复杂, 我们要对其进行简化
 *    6. 对 IdGenerator 类重命名，并且抽象出对应的接口
 * </pre>
 *
 * LogTraceIdGenerator logTraceIdGenerator = new RandomIdGenerator();
 *
 * @author asd <br>
 * @create 2021-12-24 10:52 AM <br>
 * @project pattern <br>
 */
@Slf4j
public class LogRandomIdGenerator implements LogTraceIdGenerator {

    @Override
    public String generate() {
        String substrOfHostName = getLastfieldOfHostName();
        long currentTimeMillis = System.currentTimeMillis();
        String randomString = generateRandomAlphameric(8);
        return String.format("%s-%d-%s", substrOfHostName, currentTimeMillis, randomString);
    }

    private String getLastfieldOfHostName() {
        String substrOfHostName = null;
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            String[] tokens = hostName.split("\\.");
            substrOfHostName = tokens[tokens.length - 1];
            return substrOfHostName;
        } catch (UnknownHostException e) {
            log.warn("Failed to get the host name.", e);
        }
        return substrOfHostName;
    }

    private String generateRandomAlphameric(int length) {
        char[] randomChars = new char[length];
        int count = 0;
        Random random = new Random();
        while (count < length) {
            int maxAscii = 'z';
            int randomAscii = random.nextInt(maxAscii);
            boolean isDigit = randomAscii >= '0' && randomAscii <= '9';
            boolean isUppercase = randomAscii >= 'A' && randomAscii <= 'Z';
            boolean isLowercase = randomAscii >= 'a' && randomAscii <= 'z';
            if (isDigit || isUppercase || isLowercase) {
                randomChars[count] = (char) (randomAscii);
                ++count;
            }
        }
        return new String(randomChars);
    }
}
