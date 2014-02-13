package com.google.inject.servlet;

import com.google.inject.Injector;

import javax.inject.Inject;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import java.lang.reflect.Field;


/**
 * Наследует {@link com.google.inject.servlet.GuiceFilter}
 *
 * GuiceFilter ипользует статическую инициализацию для некоторых полей,
 * {@link com.google.inject.servlet.GuiceFilter#pipeline}
 * {@link com.google.inject.servlet.GuiceFilter#setPipeline(FilterPipeline)}
 * Если guice лежит в разделяемых библиотеках модуля это приводит к тому,
 * что каждый последующий фильтр перезатирает предыдущие.
 *
 * Данный класс решает эту проблему запоминая injector, который иницализирует класс
 * перед созданием нового экземпляра и выставляе в локальное поле
 * {@link com.google.inject.servlet.GuiceFilter#injectedPipeline}
 * менеджер, порожденный данным injector-ом
 *
 *
 * Более простое решени было бы возможно, если бы
 * {@link com.google.inject.servlet.FilterPipeline} и
 * {@link com.google.inject.servlet.ManagedFilterPipeline}
 * не были приватными классами
 * Обсуждение:
 * https://code.google.com/p/google-guice/issues/detail?id=618
 *
 */
public class MyGuiceFilter extends GuiceFilter {

    private static Injector lastCreatedInjector = null;

    /**
     * Будет вызван перед создание экземпляра класса
     * Для вызова нужно использовать {@link com.google.inject.AbstractModule#requestStaticInjection(Class[])}
     * Документация https://code.google.com/p/google-guice/wiki/Injections
     *
     * Сохраняет экземпляр в статическое поле
     *
     * @param injector - {@link com.github.sionin.servlet.guice.MyGuiceContextListener#getInjector()}
     */
    @Inject
    static void preInit(Injector injector) {
            lastCreatedInjector = injector;
    }

    /**
     * Ининциализации фильтра {@link javax.servlet.Filter}
     * Вызывается сервером приложений
     *
     * Используя Injector определенный когда Guice конфигурировал класс
     * выставляет поле {@link com.google.inject.servlet.GuiceFilter#injectedPipeline}
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
            if (lastCreatedInjector != null) {
                setLocalPipeline(lastCreatedInjector.getInstance(FilterPipeline.class));
                lastCreatedInjector = null;
            } else {
                System.err.println(
                        this.getClass().getCanonicalName() +
                        ": Injector not found. FilterPipeline injection skipped!");
            }

        super.init(filterConfig);
    }

    private void setLocalPipeline(FilterPipeline pipeline) {
        try {
            Field injectedPipeline = GuiceFilter.class.getDeclaredField("injectedPipeline");
            injectedPipeline.setAccessible(true);
            injectedPipeline.set(this, pipeline);
            injectedPipeline.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
