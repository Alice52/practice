package top.hubby.practice.refactor.after.v2;

import com.google.common.annotations.VisibleForTesting;
import lombok.extern.slf4j.Slf4j;
import top.hubby.practice.refactor.after.v1.LogTraceIdGenerator;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * 第二轮重构: 提高代码的可测试性 <br>
 * - 单元测试用例如何写, 关键看你如何定义函数
 *
 * <pre>
 *   1. generate() 函数定义为静态函数, 会影响使用该函数的代码的可测试性
 *   2. generate() 函数的代码实现依赖运行环境、时间函数、随机函数, 所以 generate() 函数本身的可测试性也不好
 *      - 从 getLastfieldOfHostName() 函数中, 将逻辑比较复杂的那部分代码剥离出来
 *      - 将 generateRandomAlphameric() 和 getLastSubstrSplittedByDot() 设置为 public
 *      - 使用 @VisibleForTesting
 *    3. 写单元测试的时候, 测试对象是函数定义的功能, 而非具体的实现逻辑
 * </pre>
 *
 * @author asd <br>
 * @create 2021-12-24 12:05 PM <br>
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
            substrOfHostName = getLastSubstrSplittedByDot(hostName);
        } catch (UnknownHostException e) {
            log.warn("Failed to get the host name.", e);
        }
        return substrOfHostName;
    }

    @VisibleForTesting
    public String getLastSubstrSplittedByDot(String hostName) {
        String[] tokens = hostName.split("\\.");
        return tokens[tokens.length - 1];
    }

    @VisibleForTesting
    public String generateRandomAlphameric(int length) {
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
