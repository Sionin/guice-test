package com.github.sionin.model;

public class Combined {

    A a;
    B b;
    C c;

    public Combined(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Combined{");
        sb.append("a=").append(a);
        sb.append(", b=").append(b);
        sb.append(", c=").append(c);
        sb.append('}');
        return sb.toString();
    }
}
