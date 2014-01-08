package com.github.sionin;

import com.github.sionin.model.*;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.name.Names;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

public class TestModule extends AbstractModule {


    @Override
    protected void configure() {
        bind(String.class).toInstance("injected string");
        bind(A.class).toInstance(new A("a"));
        bind(A.class).annotatedWith(Names.named("NamedA")).toInstance(new A("NamedA"));
        bind(C.class).toProvider(ProviderC.class);
    }

    @Provides
    Combined provideCombine(@Named("NamedA") A a, B b, C c) {
        return new Combined(a, b, c);
    }

    @Provides
    @Singleton
    Singeltone provideSingeltone(String value) {
        return new Singeltone(value);
    }

    @Provides
    Integer provideInteger() throws Exception {
        throw new Exception("Some error");
    }

    @Provides
    List provideList(Integer integer){
        List<Integer> ints = new ArrayList<Integer>();
        ints.add(integer);
        return ints;
    }

    public static void main(String[] args) {
        final Injector injector = Guice.createInjector(new TestModule());

        System.out.println(injector.getInstance(A.class));
        System.out.println(injector.getInstance(B.class));
        System.out.println(injector.getInstance(C.class));

        System.out.println(injector.getInstance(Combined.class));

        for (int i = 0; i < 10; i++) {
            System.out.println(injector.getInstance(Singeltone.class));
        }

        try {
            System.out.println(injector.getInstance(List.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
