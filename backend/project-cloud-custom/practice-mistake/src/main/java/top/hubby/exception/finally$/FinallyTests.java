package top.hubby.exception.finally$;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author asd <br>
 * @create 2021-10-28 2:48 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class FinallyTests {

    @Test
    public void useresourcewrong() throws Exception {
        TestResource testResource = new TestResource();
        try {
            testResource.read();
        } finally {
            testResource.close();
        }
    }

    @Test
    public void useresourceright() throws Exception {
        try (TestResource testResource = new TestResource()) {
            testResource.read();
        }
    }

    @Test
    public void wrong() {
        try {
            log.info("try");
            throw new RuntimeException("try");
        } finally {
            log.info("finally");
            throw new RuntimeException("finally");
        }
    }

    @Test
    public void right() {
        try {
            log.info("try");
            throw new RuntimeException("try");
        } finally {
            log.info("finally");
            try {
                throw new RuntimeException("finally");
            } catch (Exception ex) {
                log.error("finally", ex);
            }
        }
    }

    @Test
    public void right2() throws Exception {
        Exception e = null;
        try {
            log.info("try");
            throw new RuntimeException("try");
        } catch (Exception ex) {
            e = ex;
        } finally {
            log.info("finally");
            try {
                throw new RuntimeException("finally");
            } catch (Exception ex) {
                if (e != null) {
                    e.addSuppressed(ex);
                } else {
                    e = ex;
                }
            }
        }
        throw e;
    }
}
