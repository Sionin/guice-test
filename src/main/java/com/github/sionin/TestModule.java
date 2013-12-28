package com.github.sionin;

import com.github.sionin.model.*;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.name.Names;

import javax.inject.Named;
import javax.naming.Name;

public class TestModule extends AbstractModule {



    @Override
    protected void configure() {
        bind(String.class).toInstance("injected");
        bind(A.class).toInstance(new A("a"));
        bind(A.class).annotatedWith(Names.named("NamedA")).toInstance(new A("NamedA"));
        bind(C.class).toProvider(ProviderC.class);
    }

    @Provides
    Combined provideCombine(@Named("NamedA") A a, B b, C c){
        return new Combined(a, b, c);
    }


    public static void main(String[] args) {
        final Injector injector = Guice.createInjector(new TestModule());
        final Combined combined = injector.getInstance(Combined.class);
        System.out.println(combined);
    }

}
