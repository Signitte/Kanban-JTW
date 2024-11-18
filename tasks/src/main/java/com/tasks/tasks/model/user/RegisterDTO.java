package com.tasks.tasks.model.user;

public record RegisterDTO(String login, String password, UserRole role) {
}