package com.github.sionin.model;

public class C {

    private String value;

    public C(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("C{");
        sb.append("value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
