package com.stockflow.model.user;

/**
 * Enum representing the roles a user can have in the system.
 */
public enum UserRole {

    ADMIN("ADMIN"),
    USER("USER");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
