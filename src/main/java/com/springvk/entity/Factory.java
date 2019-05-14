package com.springvk.entity;

import java.sql.Timestamp;

public class Factory {
     private Long factoryId;
     private String factoryName;
     private Timestamp factoryOpenYear;

    public Factory() {
    }

    public Factory(String factoryName) {
        this.factoryName = factoryName;
    }

    public Factory(Long factoryId, String factoryName, Timestamp factoryOpenYear) {
        this.factoryId = factoryId;
        this.factoryName = factoryName;
        this.factoryOpenYear = factoryOpenYear;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public Timestamp getFactoryOpenYear() {
        return factoryOpenYear;
    }

    public void setFactoryOpenYear(Timestamp factoryOpenYear) {
        this.factoryOpenYear = factoryOpenYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((factoryName == null) ? 0 : factoryName.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Departament)) {
            return false;
        }
        Factory other = (Factory) obj;
        if (factoryName == null) {
            if (other.factoryName != null) {
                return false;
            }
        } else if (!factoryName.equals(other.factoryName)) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return String.format("[%s, %s, %s]", factoryId, factoryName, factoryOpenYear);
    }
}
