package top.hubby.practice.refactor.after.v3;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import top.hubby.practice.refactor.after.v2.LogRandomIdGenerator;

/**
 * 第三轮重构: 编写完善的单元测试
 *
 * @author asd <br>
 * @create 2021-12-24 12:13 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class LogRandomIdGeneratorTest {

    @Test
    public void testGetLastSubstrSplittedByDot() {
        LogRandomIdGenerator idGenerator = new LogRandomIdGenerator();
        String actualSubstr = idGenerator.getLastSubstrSplittedByDot("field1.field2.field3");
        Assert.assertEquals("field3", actualSubstr);

        actualSubstr = idGenerator.getLastSubstrSplittedByDot("field1");
        Assert.assertEquals("field1", actualSubstr);

        actualSubstr = idGenerator.getLastSubstrSplittedByDot("field1#field2#field3");
        Assert.assertEquals("field1#field2#field3", actualSubstr);
    }

    // 此单元测试会失败，因为我们在代码中没有处理hostName为null或空字符串的情况
    // 这部分优化留在第36、37节课中讲解
    @Test
    public void testGetLastSubstrSplittedByDotNullOrEmpty() {
        LogRandomIdGenerator idGenerator = new LogRandomIdGenerator();
        String actualSubstr = idGenerator.getLastSubstrSplittedByDot(null);
        Assert.assertNull(actualSubstr);

        actualSubstr = idGenerator.getLastSubstrSplittedByDot("");
        Assert.assertEquals("", actualSubstr);
    }

    @Test
    public void testGenerateRandomAlphameric() {
        LogRandomIdGenerator idGenerator = new LogRandomIdGenerator();
        String actualRandomString = idGenerator.generateRandomAlphameric(6);
        Assert.assertNotNull(actualRandomString);
        Assert.assertEquals(6, actualRandomString.length());
        for (char c : actualRandomString.toCharArray()) {
            Assert.assertTrue(
                    ('0' <= c && c <= '9') || ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z'));
        }
    }

    // 此单元测试会失败，因为我们在代码中没有处理length<=0的情况
    // 这部分优化留在第36、37节课中讲解
    @Test
    public void testGenerateRandomAlphameric_lengthEqualsOrLessThanZero() {
        LogRandomIdGenerator idGenerator = new LogRandomIdGenerator();
        String actualRandomString = idGenerator.generateRandomAlphameric(0);
        Assert.assertEquals("", actualRandomString);

        actualRandomString = idGenerator.generateRandomAlphameric(-1);
        Assert.assertNull(actualRandomString);
    }
}
