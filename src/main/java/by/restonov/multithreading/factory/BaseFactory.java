package by.restonov.multithreading.factory;

public interface BaseFactory<T,K> {
    T create(K data);
}
