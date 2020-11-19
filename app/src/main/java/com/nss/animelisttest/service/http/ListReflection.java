package com.nss.animelisttest.service.http;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by mac on 2/27/2017 AD.
 */

public class ListReflection<T> implements ParameterizedType {

    private Class<T> wrapped;

    public ListReflection(Class<T> wrapper) {
        this.wrapped = wrapper;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return new Type[] { wrapped };
    }

    @Override
    public Type getRawType() {
        return List.class;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }

}
