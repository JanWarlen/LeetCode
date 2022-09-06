package com.janwarlen.ac.basic;

public class Generics<T> {

    public static <T extends Number> T upperLimit(Info<T> info) {
        return info.get();
    }

    public static Object lowerLimit(Info<? super String> info) {
        return info.get();
    }

    public static class Info<T> {

        T T;

        public void set(T t) {
            this.T = t;
        }

        public T get() {
            return T;
        }
    }

    //https://www.lintcode.com/problem/2904
    public Containers<T> initContainers(T t){
        // write your code here
        Containers<T> containers = new Containers<>();
        containers.set(t);
        return containers;
    }

    public static class Containers<T> {

        private T t;

        public void set(T t){
            this.t = t;
        }

        public T get(){
            return t;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Info<Number> numberInfo = new Info<>();
        numberInfo.set(1234);
        System.out.println(Generics.upperLimit(numberInfo));
        Info<String> stringInfo = new Info<>();
        stringInfo.set("123333");
        System.out.println(Generics.lowerLimit(stringInfo));
    }

}
