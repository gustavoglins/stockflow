package com.stockflow.model.roles;

public enum Role {

    ADMIN("ADMIN"),
    COMMON_USER("COMMON_USER"),
    COMPANY_OWNER("COMPANY_OWNER"),
    COMPANY_EMPLOYEE("COMPANY_EMPLOYEE"),
    TEAM_LEADER("TEAM_LEADER"),
    TEAM_COLLABORATOR("TEAM_COLLABORATOR");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
