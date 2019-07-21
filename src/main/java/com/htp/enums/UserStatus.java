package com.htp.enums;

public enum UserStatus {

    ACTIVE("active"),
    BANNED("banned");

    private String representation;

    UserStatus(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

}
