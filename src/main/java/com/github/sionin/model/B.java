package com.github.sionin.model;

import com.google.inject.Inject;

public class B {

    private String value;

    @Inject
    public B(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("B{");
        sb.append("value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
