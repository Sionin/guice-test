package com.github.sionin.model;

import com.google.inject.Inject;

import javax.inject.Named;

public class Singeltone {

    public static int counter = 0;
    String value;

//    public Singeltone() {
//        counter++;
//    }

    public Singeltone(String value) {
        this.value = value;
        counter++;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Singeltone{");
        sb.append("counter=").append(counter);
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }

}
