package top.hubby.oom;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

/**
 * @author asd <br>
 * @create 2021-11-10 8:32 AM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class WeakHashMapOOMTests {
    ConcurrentReferenceHashMap<User, UserProfile> map =
            new ConcurrentReferenceHashMap<>(16, ConcurrentReferenceHashMap.ReferenceType.WEAK);
    private Map<User, UserProfile> cache = new WeakHashMap<>();
    private Map<User, WeakReference<UserProfile>> cache2 = new WeakHashMap<>();
    private ConcurrentReferenceHashMap<User, UserProfile> cache3 =
            new ConcurrentReferenceHashMap<>(16, ConcurrentReferenceHashMap.ReferenceType.WEAK);

    /**
     * explaining
     *
     * <pre>
     *    1. WeakHashMap 的 Key 虽然是弱引用
     *    2. 但是其 Value 却持有 Key 中对象的强引用,
     *    3. Value 被 Entry 引用, Entry 被 WeakHashMap 引用, 最终导致 Key 无法回收
     * </pre>
     *
     * @throws InterruptedException
     */
    @Test
    public void wrong() throws InterruptedException {
        String userName = "zhuye";
        ScheduledExecutorService scheduledExecutorService =
                Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(
                () -> log.info("cache size:{}", cache.size()), 1, 1, TimeUnit.SECONDS);
        LongStream.rangeClosed(1, 2000000)
                .forEach(
                        i -> {
                            User user = new User(userName + i);
                            cache.put(user, new UserProfile(user, "location" + i));
                        });

        scheduledExecutorService.awaitTermination(1, TimeUnit.HOURS);
    }

    @Test
    public void testConcurrentReferenceHashMap() throws InterruptedException {
        ConcurrentReferenceHashMap<String, String> map =
                new ConcurrentReferenceHashMap<>(16, ConcurrentReferenceHashMap.ReferenceType.WEAK);
        map.put("key", "val");

        // {key=val}
        log.info("{}", map);

        System.gc();
        Thread.sleep(5000);

        // {}
        log.info("{}", map);
    }

    @Test
    public void testConcurrentReferenceHashMapV2() throws InterruptedException {

        //        User user = new User("zack");
        //        UserProfile location = new UserProfile(user, "location");
        //        map.put(user, location);
        LongStream.rangeClosed(1, 20000)
                .forEach(
                        i -> {
                            User user = new User(String.valueOf(i));
                            map.put(user, new UserProfile(user, "location" + i));
                        });

        // {xxx=xxx}
        log.info("{}", map.size());

        System.gc();
        Thread.sleep(5000);

        // {}
        log.info("{}", map);
    }

    @Test
    public void testWeakHashMapScope() throws InterruptedException {
        WeakHashMap map = testWeakHashMap();
        Thread.sleep(5000);
        log.info("{}", map);

        System.gc();

        log.info("{}", map);
    }

    public WeakHashMap testWeakHashMap() throws InterruptedException {
        WeakHashMap<User, UserProfile> map = new WeakHashMap<>();

        User user = new User("zack");
        UserProfile location = new UserProfile(user, "location");
        map.put(user, new UserProfile());

        return map;
    }

    @SneakyThrows
    @Test
    public void right4() {
        right3();

        TimeUnit.MINUTES.sleep(1);
        log.info("1 minute is done.");
        System.gc();

        TimeUnit.HOURS.sleep(1);
    }

    @Test
    public void right() throws InterruptedException {
        String userName = "zhuye";
        val scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(
                () -> log.info("cache size:{}", cache2.size()), 1, 1, TimeUnit.SECONDS);
        LongStream.rangeClosed(1, 200000)
                .forEach(
                        i -> {
                            User user = new User(userName + i);
                            cache2.put(
                                    user, new WeakReference(new UserProfile(user, "location" + i)));
                        });
    }

    @Test
    @Deprecated
    public void right2() throws InterruptedException {
        String userName = "zhuye";
        val scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(
                () -> log.info("cache size:{}", cache.size()), 1, 1, TimeUnit.SECONDS);
        LongStream.rangeClosed(1, 2000000)
                .forEach(
                        i -> {
                            User user = new User(userName + i);
                            cache.put(
                                    user,
                                    new UserProfile(new User(user.getName()), "location" + i));
                        });
    }

    @Test
    public void right3() throws InterruptedException {
        // String userName = "zhuye";
        //        val scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //        scheduledExecutorService.scheduleAtFixedRate(
        //                () -> log.info("cache size:{}", cache3.size()), 1, 1, TimeUnit.SECONDS);
        LongStream.rangeClosed(1, 20000)
                .forEach(
                        i -> {
                            User user = new User(String.valueOf(i));
                            cache3.put(user, new UserProfile(user, "location" + i));
                        });

        log.info("map size: {}", cache3.size());
        System.gc();
        Thread.sleep(5000);

        log.info("map size: {}", cache3.get(1));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class User {
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class UserProfile {
        private User user;
        private String location;
    }
}
