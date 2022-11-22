package top.hubby.builder.app.model;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 *
 *
 * <pre>
 *
 * </pre>
 *
 * @author asd <br>
 * @create 2021-09-15 5:00 PM <br>
 * @project pattern <br>
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Person {

    private int age;
    private String name;
    private boolean gender;

    public static void main(String[] args) {

        PersonBuilder builder = Person.builder().name("zack");
        Person zack = builder.build();
    }
}

/*
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Person {
    private static final Logger log = LoggerFactory.getLogger(top.hubby.builder.app.model.Person.class);
    private int age;
    private String name;
    private boolean gender;

    public static void main(String[] args) {
        top.hubby.builder.app.model.Person.PersonBuilder builder = builder().name("zack");
        top.hubby.builder.app.model.Person zack = builder.build();
    }

    public static top.hubby.builder.app.model.Person.PersonBuilder builder() {
        return new top.hubby.builder.app.model.Person.PersonBuilder();
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

    public boolean isGender() {
        return this.gender;
    }

    public top.hubby.builder.app.model.Person setAge(int age) {
        this.age = age;
        return this;
    }

    public top.hubby.builder.app.model.Person setName(String name) {
        this.name = name;
        return this;
    }

    public top.hubby.builder.app.model.Person setGender(boolean gender) {
        this.gender = gender;
        return this;
    }

    public Person(int age, String name, boolean gender) {
        this.age = age;
        this.name = name;
        this.gender = gender;
    }

    public Person() {
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof top.hubby.builder.app.model.Person)) {
            return false;
        } else {
            top.hubby.builder.app.model.Person other = (top.hubby.builder.app.model.Person)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getAge() != other.getAge()) {
                return false;
            } else {
                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name == null) {
                        return this.isGender() == other.isGender();
                    }
                } else if (this$name.equals(other$name)) {
                    return this.isGender() == other.isGender();
                }

                return false;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof top.hubby.builder.app.model.Person;
    }

    public int hashCode() {
        int PRIME = true;
        int result = 1;
        int result = result * 59 + this.getAge();
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        result = result * 59 + (this.isGender() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "Person(age=" + this.getAge() + ", name=" + this.getName() + ", gender=" + this.isGender() + ")";
    }

    public static class PersonBuilder {
        private int age;
        private String name;
        private boolean gender;

        PersonBuilder() {
        }

        public top.hubby.builder.app.model.Person.PersonBuilder age(int age) {
            this.age = age;
            return this;
        }

        public top.hubby.builder.app.model.Person.PersonBuilder name(String name) {
            this.name = name;
            return this;
        }

        public top.hubby.builder.app.model.Person.PersonBuilder gender(boolean gender) {
            this.gender = gender;
            return this;
        }

        public top.hubby.builder.app.model.Person build() {
            return new top.hubby.builder.app.model.Person(this.age, this.name, this.gender);
        }

        public String toString() {
            return "Person.PersonBuilder(age=" + this.age + ", name=" + this.name + ", gender=" + this.gender + ")";
        }
    }
}
*/
