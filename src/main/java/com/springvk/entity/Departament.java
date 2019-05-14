package com.springvk.entity;

public class Departament {

    private Long depId;
    private String depName;
    private int depCapacity;
    private Long factoryId;

    public Departament() {
    }

    public Departament(int depCapacity) {
        this.depCapacity = depCapacity;
    }

    public Departament(Long depId, String depName, int depCapacity, Long factoryId) {
        super();
        this.depId = depId;
        this.depName = depName;
        this.depCapacity = depCapacity;
        this.factoryId = factoryId;
    }

    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public int getDepCapacity() {
        return depCapacity;
    }

    public void setDepCapacity(int depCapacity) {
        this.depCapacity = depCapacity;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((depName == null) ? 0 : depName.hashCode());
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
        Departament other = (Departament) obj;
        if (depName == null) {
            if (other.depName != null) {
                return false;
            }
        } else if (!depName.equals(other.depName)) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return String.format("[%s, %s, %s]", depCapacity, depName);
    }

    public Object[] toArray() {
        return new Object[]{depCapacity, depName};
    }
    public String toCombo(){
        return String.format("ComboStr ", depName);
    }

}
