package com.github.sionin.model;

public class A {

    private String value;

    public A(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("A{");
        sb.append("value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
