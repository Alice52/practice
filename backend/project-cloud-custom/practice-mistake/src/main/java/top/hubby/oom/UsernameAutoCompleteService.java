package top.hubby.oom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author asd <br>
 * @create 2021-11-01 4:35 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class UsernameAutoCompleteService {
    private ConcurrentHashMap<String, List<UserDTO>> autoCompleteIndex = new ConcurrentHashMap<>();

    @Test
    public void wrong() {
        List<UserEntity> entities =
                LongStream.rangeClosed(1, 10000)
                        .mapToObj(i -> new UserEntity(i, randomName()))
                        .collect(Collectors.toList());

        entities.forEach(
                userEntity -> {
                    int len = userEntity.getName().length();
                    for (int i = 0; i < len; i++) {
                        String key = userEntity.getName().substring(0, i + 1);
                        autoCompleteIndex
                                .computeIfAbsent(key, s -> new ArrayList<>())
                                .add(new UserDTO(userEntity.getName()));
                    }
                });
        log.info(
                "autoCompleteIndex size:{} count:{}",
                autoCompleteIndex.size(),
                autoCompleteIndex.entrySet().stream()
                        .map(item -> item.getValue().size())
                        .reduce(0, Integer::sum));
    }

    /** also can be optimized by dictionary-tree. */
    @Test
    public void right() {
        List<UserEntity> entities =
                LongStream.rangeClosed(1, 10000)
                        .mapToObj(i -> new UserEntity(i, randomName()))
                        .collect(Collectors.toList());

        HashSet<UserDTO> cache =
                entities.stream()
                        .map(item -> new UserDTO(item.getName()))
                        .collect(Collectors.toCollection(HashSet::new));

        cache.forEach(
                userDTO -> {
                    int len = userDTO.getName().length();
                    for (int i = 0; i < len; i++) {
                        String key = userDTO.getName().substring(0, i + 1);
                        autoCompleteIndex.computeIfAbsent(key, s -> new ArrayList<>()).add(userDTO);
                    }
                });
        log.info(
                "autoCompleteIndex size:{} count:{}",
                autoCompleteIndex.size(),
                autoCompleteIndex.entrySet().stream()
                        .map(item -> item.getValue().size())
                        .reduce(0, Integer::sum));
    }

    /**
     * 随机生成长度为6的英文名称，字母包含 abcdefghij
     *
     * @return
     */
    private String randomName() {
        return String.valueOf(Character.toChars(ThreadLocalRandom.current().nextInt(10) + 'a'))
                        .toUpperCase()
                + String.valueOf(Character.toChars(ThreadLocalRandom.current().nextInt(10) + 'a'))
                + String.valueOf(Character.toChars(ThreadLocalRandom.current().nextInt(10) + 'a'))
                + String.valueOf(Character.toChars(ThreadLocalRandom.current().nextInt(10) + 'a'))
                + String.valueOf(Character.toChars(ThreadLocalRandom.current().nextInt(10) + 'a'))
                + String.valueOf(Character.toChars(ThreadLocalRandom.current().nextInt(10) + 'a'));
    }
}

@Data
class UserDTO {
    private String name;
    @EqualsAndHashCode.Exclude private String payload;

    public UserDTO(String name) {
        this.name = name;
        this.payload =
                // 10 K
                IntStream.rangeClosed(1, 10_000)
                        .mapToObj(__ -> "a")
                        .collect(Collectors.joining(""));
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class UserEntity {
    private Long id;
    private String name;
}
