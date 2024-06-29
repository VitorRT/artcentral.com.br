package br.com.artcentral.mvc.system.generics;

import java.util.UUID;

public interface GenericCrud<T, L, E> {
    T doCreate(Object payload);
    T doUpdateById(Object payload, UUID id);
    L getSimpleList();
    T detailsById(UUID id);
    void doDeleteById(UUID id);

    // métodos opcionais
    default T detailsByTag(String tag) { return null; }
    default T updateByTag(String tag) { return null; }
    default void doDeleteByTag(String tag) {  }

}