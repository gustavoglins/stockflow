package com.stockflow.shared.enums;

public enum AuthRoles {

    ADMIN("ADMIN"),
    COMMON_USER("COMMON_USER");

    private String role;

    AuthRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
