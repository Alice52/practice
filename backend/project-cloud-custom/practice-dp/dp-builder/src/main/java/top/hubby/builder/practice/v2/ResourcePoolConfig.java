package top.hubby.builder.practice.v2;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 *
 * <pre>
 *    1. 使用 Builder: 可以直接在 build() 内做最后的参数校验{必填项}
 *    2. ResourcePoolConfig 私有构造函数, 且不提供 setter{不允许修改}
 *    3. 参数太多: 得到解决{构造函数的参数是 Builder}
 *    4. 参数彼此间依赖校验: 得到解决
 * </pre>
 *
 * Usage :
 *
 * <pre>
 *     // 这段代码会抛出IllegalArgumentException，因为minIdle>maxIdle
 *     ResourcePoolConfig config = new ResourcePoolConfig.Builder()
 *             .setName("dbconnectionpool")
 *             .setMaxTotal(16)
 *             .setMaxIdle(10)
 *             .setMinIdle(12)
 *             .build();
 * </pre>
 *
 * @author asd <br>
 * @create 2022-01-07 4:45 PM <br>
 * @project pattern <br>
 */
@Slf4j
@Getter
@NoArgsConstructor
public class ResourcePoolConfig {
    private String name;
    private int maxTotal;
    private int maxIdle;
    private int minIdle;

    /**
     * This is private.
     *
     * @param builder
     */
    private ResourcePoolConfig(Builder builder) {
        this.name = builder.name;
        this.maxTotal = builder.maxTotal;
        this.maxIdle = builder.maxIdle;
        this.minIdle = builder.minIdle;
    }

    public static void main(String[] args) {
        ResourcePoolConfig config =
                new ResourcePoolConfig.Builder()
                        .setName("dbconnectionpool")
                        .setMaxTotal(16)
                        .setMaxIdle(10)
                        .setMinIdle(12)
                        .build();
    }

    // 我们将 Builder 类设计成了 ResourcePoolConfig 的内部类
    // 我们也可以将 Builder 类设计成独立的非内部类 ResourcePoolConfigBuilder
    public static class Builder {
        private static final int DEFAULT_MAX_TOTAL = 8;
        private static final int DEFAULT_MAX_IDLE = 8;
        private static final int DEFAULT_MIN_IDLE = 0;

        private String name;
        private int maxTotal = DEFAULT_MAX_TOTAL;
        private int maxIdle = DEFAULT_MAX_IDLE;
        private int minIdle = DEFAULT_MIN_IDLE;

        public ResourcePoolConfig build() {
            // 校验逻辑放到这里来做，包括必填项校验、依赖关系校验、约束条件校验等
            if (StrUtil.isBlank(name)) {
                throw new IllegalArgumentException("...");
            }
            if (maxIdle > maxTotal) {
                throw new IllegalArgumentException("...");
            }
            if (minIdle > maxTotal || minIdle > maxIdle) {
                throw new IllegalArgumentException("...");
            }

            return new ResourcePoolConfig(this);
        }

        public Builder setName(String name) {
            if (StrUtil.isBlank(name)) {
                throw new IllegalArgumentException("...");
            }
            this.name = name;
            return this;
        }

        public Builder setMaxTotal(int maxTotal) {
            if (maxTotal <= 0) {
                throw new IllegalArgumentException("...");
            }
            this.maxTotal = maxTotal;
            return this;
        }

        public Builder setMaxIdle(int maxIdle) {
            if (maxIdle < 0) {
                throw new IllegalArgumentException("...");
            }
            this.maxIdle = maxIdle;
            return this;
        }

        public Builder setMinIdle(int minIdle) {
            if (minIdle < 0) {
                throw new IllegalArgumentException("...");
            }
            this.minIdle = minIdle;
            return this;
        }
    }
}
