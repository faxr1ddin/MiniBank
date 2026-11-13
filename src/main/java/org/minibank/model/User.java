package org.minibank.model;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
public class User {

    private final String id;
    private final String fullName;
    private final String email;
    private final String phone;

    @ToString.Exclude
    private final String password;
    private final LocalDateTime createdAt;

    private User(Builder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.password = builder.password;
        this.createdAt = builder.createdAt;
    }

    public static class Builder {
        private String id;
        private String fullName;
        private String email;
        private String phone;
        private String password;
        private LocalDateTime createdAt;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public User build() {
            Objects.requireNonNull(fullName, "Full name is required");
            Objects.requireNonNull(email, "Email is required");
            Objects.requireNonNull(password, "Password is required");

            return new User(this);
        }
    }
}
