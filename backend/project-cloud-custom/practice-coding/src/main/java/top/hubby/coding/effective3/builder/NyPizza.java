package top.hubby.coding.effective3.builder;

import cn.hutool.core.util.StrUtil;

import java.util.Objects;

/**
 * @author T04856 <br>
 * @create 2023-03-31 10:59 AM <br>
 * @project project-cloud-custom <br>
 */
public class NyPizza extends Pizza {
    public enum Size {
        SMALL,
        MEDIUM,
        LARGE
    }

    private final Size size;

    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        public NyPizza build() {
            return new NyPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private NyPizza(Builder builder) {
        super(builder);
        size = builder.size;
    }
}
