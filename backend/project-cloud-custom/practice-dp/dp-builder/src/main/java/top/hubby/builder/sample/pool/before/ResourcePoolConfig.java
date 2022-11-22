package top.hubby.builder.sample.pool.before;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * issue list
 *
 * <pre>
 *    1. 构造函数的逻辑太复杂: 构造函数不应该有太多的逻辑{清晰}
 *    2. 构造函数的参数太多
 *    3. 参数如果彼此之间有依赖: 校验不好做
 *    4. 构造函数是公开的: 别人可以随便创建
 *    5. 创建出来的对象属性可能会被误修改
 * </pre>
 *
 * @author asd <br>
 * @create 2022-01-07 4:45 PM <br>
 * @project pattern <br>
 */
@Slf4j
@Data
public class ResourcePoolConfig {
    private static final int DEFAULT_MAX_TOTAL = 8;
    private static final int DEFAULT_MAX_IDLE = 8;
    private static final int DEFAULT_MIN_IDLE = 0;

    private String name;
    private int maxTotal = DEFAULT_MAX_TOTAL;
    private int maxIdle = DEFAULT_MAX_IDLE;
    private int minIdle = DEFAULT_MIN_IDLE;

    public ResourcePoolConfig(String name, Integer maxTotal, Integer maxIdle, Integer minIdle) {
        if (StrUtil.isBlank(name)) {
            throw new IllegalArgumentException("name should not be empty.");
        }
        this.name = name;
    }

    public void setMaxTotal(int maxTotal) {
        if (maxTotal <= 0) {
            throw new IllegalArgumentException("maxTotal should be positive.");
        }
        this.maxTotal = maxTotal;
    }

    public void setMaxIdle(int maxIdle) {
        if (maxIdle < 0) {
            throw new IllegalArgumentException("maxIdle should not be negative.");
        }
        this.maxIdle = maxIdle;
    }

    public void setMinIdle(int minIdle) {
        if (minIdle < 0) {
            throw new IllegalArgumentException("minIdle should not be negative.");
        }
        this.minIdle = minIdle;
    }
}
