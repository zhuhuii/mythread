package org.jdk5.generics.t_other;

/**
 * 泛型通配符:？
 */
class Wildcard {
    public static void main(String[] args) {
        Generic<Number> numberGeneric = new Generic<>(123);
        numberGeneric.showKeyValue1(numberGeneric);

        Generic<Integer> integerGeneric = new Generic<>(666);
        // cannot be applied to Generic<java.lang.Number>
        // integerMyGeneric.showKeyValue(integerMyGeneric);
        integerGeneric.showKeyValue2(integerGeneric);
    }
}




