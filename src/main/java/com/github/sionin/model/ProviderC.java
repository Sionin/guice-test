package com.github.sionin.model;

import javax.inject.Inject;
import javax.inject.Provider;

public class ProviderC implements Provider<C> {

    @Inject
    private A a;

    @Override
    public C get() {
        return new C("Factory: injected " + a );
    }


}
