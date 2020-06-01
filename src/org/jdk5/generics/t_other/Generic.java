package org.jdk5.generics.t_other;

class Generic<T> {
    private T key;

    public T getKey() {
        return key;
    }

    public Generic(T key) {
        this.key = key;
    }

    public void showKeyValue1(Generic<Number> obj) {
        System.out.println("泛型测试 == > " + obj.getKey());
    }

    public void showKeyValue2(Generic<?> obj) {
        System.out.println("泛型测试 == > " + obj.getKey());
    }
}