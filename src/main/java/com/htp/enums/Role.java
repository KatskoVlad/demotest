package com.htp.enums;

public enum Role {

    ROLE_USER("client"),
    ROLE_ADMIN("admin");

    private String representation;

    Role(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

}
