package com.stockflow.model.role;

public enum Role {

    ADMIN("admin"),
    COMMON_USER("common_user"),
    COMPANY_OWNER("company_owner"),
    COMPANY_EMPLOYEE("company_employee"),
    TEAM_LEADER("team_leader"),
    TEAM_COLLABORATOR("team_collaborator");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
