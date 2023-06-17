package ru.nikulin.api.constants;

import lombok.Getter;

public enum UserType {
    GUEST(null, null),
    AUTH("example@example.example", "examplePassw0rd");
    @Getter
    final String username;
    @Getter
    final String password;

    UserType(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
