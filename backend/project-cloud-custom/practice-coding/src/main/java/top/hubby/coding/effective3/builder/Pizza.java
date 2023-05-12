package top.hubby.coding.effective3.builder;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author T04856 <br>
 * @create 2023-03-31 10:54 AM <br>
 * @project project-cloud-custom <br>
 */
public abstract class Pizza {
    public enum Topping {
        HAM,
        MUSHROOM,
        ONION,
        PEPPER,
        SAUSAGE
    }

    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();
        // Subclasses must override this method to return "this"
        protected T self() {
            return (T) this;
        }
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone(); // See Item 50
    }
}
