package com.example.thewitcher.utils;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.List;
import java.util.function.Consumer;

public class Utilitaire {
    public static <T> void observeEntityList(LifecycleOwner owner, LiveData<List<T>> entityListLiveData, Consumer<T> entityConsumer) {
        entityListLiveData.observe(owner, new Observer<List<T>>() {
            @Override
            public void onChanged(List<T> entities) {
                if (!entities.isEmpty()) {
                    T entity = entities.get(0);
                    entityConsumer.accept(entity);
                }
            }
        });
    }
}
