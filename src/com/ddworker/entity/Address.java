package com.ddworker.entity;

import java.util.Objects;

public class Address implements Cloneable{
    public String addr;
    public String type;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "address{" +
                "addr='" + addr + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(addr, address.addr) &&
                Objects.equals(type, address.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addr, type);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
