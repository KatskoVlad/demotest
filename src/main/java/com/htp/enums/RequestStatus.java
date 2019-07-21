package com.htp.enums;

public enum RequestStatus {

    PENDING("pending"),
    ACTIVE("active"),
    CLOSED("closed");

    private String representation;

    RequestStatus(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

}
