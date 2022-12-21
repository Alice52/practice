package top.hubby.builder.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;

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
@Builder
@AllArgsConstructor
public class Person {

    private int age;
    private String name;
    private boolean gender;

    public static void main(String[] args) {

        PersonBuilder builder = Person.builder().name("zack");
        Person zack = builder.build();
    }
}

class PersonExplain {
    private int age;
    private String name;
    private boolean gender;

    private PersonExplain(final int age, final String name, final boolean gender) {
        this.age = age;
        this.name = name;
        this.gender = gender;
    }

    public static PersonExplain.PersonBuilder builder() {
        return new PersonExplain.PersonBuilder();
    }

    public static class PersonBuilder {
        private int age;
        private String name;
        private boolean gender;

        PersonBuilder() {}

        public PersonExplain.PersonBuilder age(final int age) {
            this.age = age;
            return this;
        }

        public PersonExplain.PersonBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public PersonExplain.PersonBuilder gender(final boolean gender) {
            this.gender = gender;
            return this;
        }

        public PersonExplain build() {
            return new PersonExplain(this.age, this.name, this.gender);
        }
    }
}
