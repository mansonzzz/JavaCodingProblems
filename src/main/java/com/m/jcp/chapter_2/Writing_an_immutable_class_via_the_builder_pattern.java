package com.m.jcp.chapter_2;

import java.util.Date;

/**
 * @author zhangtian1
 * 当一个类有太多字段时，它需要一个具有许多参数的构造函数。
 * 当其中一些字段是必须而其他字段是可选时，这个类将需要多个构造器来满足所有的可能。
 * 此时可以使用建造者模式来解决这个问题。
 */
public class Writing_an_immutable_class_via_the_builder_pattern {
    public static void main(String[] args) {
        User user1 = User.getBuilder("zhangtian1", "123456").build();
        System.out.println(user1);
        User user2 = User.getBuilder("zhangtian2", "123456")
                .firstName("zhang")
                .lastName("tian")
                .email("test@gmail.com").build();
        System.out.println(user2);
    }
}

final class User {
    private final String nickname;
    private final String password;
    private final String firstname;
    private final String lastname;
    private final String email;
    private final Date created;

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", created=" + created +
                '}';
    }

    private User(UserBuilder builder) {
        this.nickname = builder.nickname;
        this.password = builder.password;
        this.created = builder.created;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.email = builder.email;
    }

    public static UserBuilder getBuilder(
            String nickname, String password) {
        return new User.UserBuilder(nickname, password);
    }

    public static final class UserBuilder {
        private final String nickname;
        private final String password;
        private final Date created;
        private String email;
        private String firstname;
        private String lastname;

        public UserBuilder(String nickname, String password) {
            this.nickname = nickname;
            this.password = password;
            this.created = new Date();
        }

        public UserBuilder firstName(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public UserBuilder lastName(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }

        public String getNickname() {
            return nickname;
        }

        public String getPassword() {
            return password;
        }

        public Date getCreated() {
            return created;
        }

        public String getEmail() {
            return email;
        }

        public String getFirstname() {
            return firstname;
        }

        public String getLastname() {
            return lastname;
        }
    }
}
