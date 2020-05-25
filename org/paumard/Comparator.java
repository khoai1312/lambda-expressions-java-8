package org.paumard;


import java.util.function.Function;

/**
 * @author José Paumard
 */

/**
 * Any interface with a single abstract method is a functional interface,
 * and its implementation can be treated as lambda expression
 */

@FunctionalInterface
public interface Comparator<T> {

    // Only one abstract method
    public int compare(T t1, T t2);

    /*
        public static Comparator<Person> comparing(Function<Person, Integer> f) {
            // Returns the implementation of Comparator<Person>, which means the implementation of
            // int compare(T t1, T t2), which takes in 2 objects and returns an integer by applying
            // function f to the 2 objects
            return (p1, p2) -> f.apply(p1) - f.apply(p2);

        }
        But this method can't be used for Comparator.comparing(Person::getLastName);
        because it returns integer, not string. So we must use Generics so that comparing()
        will return both String and Integer
     */

    public static <U> Comparator<U> comparing(Function<U, Comparable> f) {
        // Parameterize Comparator<Person> to Comparator<U>
        // Now this comparing() can be used to extract any field of any object
        // and use that result to compare objects

        return (p1, p2) -> f.apply(p1).compareTo(f.apply(p2)); // Use compareTo because both String and Integer are comparable, not minus which is only for Integer
    }

    public default Comparator<T> thenComparing(Comparator<T> cmp) {

        // If there's a tie in the first comparison, use cmp
        return (p1, p2) -> compare(p1, p2) == 0 ? cmp.compare(p1, p2) : compare(p1, p2);
    }

    public default Comparator<T> thenComparing(Function<T, Comparable> f) {
        /*
        Comparator<T> cmp = comparing(f);
        return thenComparing(cmp);
         */

        return thenComparing(comparing(f));
    }

}
