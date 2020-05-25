package org.paumard;


import java.util.function.Function;

/**
 * @author José Paumard
 */
public class MainComparator {

    public static void main(String... args) {

        // Write a comparator to compare 2 people using their age
        Comparator<Person> cmpAge = (p1, p2) -> p2.getAge() - p1.getAge();
        Comparator<Person> cmpFirstName = (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName());  // String is comparable so can use compareTo()
        Comparator<Person> cmpLastName = (p1, p2) -> p1.getLastName().compareTo(p2.getLastName());

        // Refactor the comparators above, all of which take 2 Person objects and compare them using age, firstName and lastName
        // Write a function that takes a Person and returns one of the fields of that object, then compare that field of 2 Persons
        Function<Person, Integer> f1 = p -> p.getAge(); // a function that takes in a Person and returns an integer (age)
        Function<Person, String> f2 = p -> p.getLastName();
        Function<Person, String> f3 = p -> p.getFirstName();

        // Build a comparator that takes this function as a parameter and compare the result to the person object
        Comparator<Person> cmpPersonAge = Comparator.comparing(Person::getAge); // Because comparing takes a function as a parameter
        /*
         Another way to write this:
         Comparator<Person> cmpPersonAge = Comparator.comparing(f1);
         or:
         Comparator<Person> cmpPersonAge = Comparator.comparing(p -> p.getAge());
         */

        Comparator<Person> cmpPersonLastName = Comparator.comparing(Person::getLastName);
        /*
         Chaining comparators with the default thenComparing method:
            Comparator<Person> cmp = cmpPersonAge.thenComparing(cmpLastName);
         Means if 2 ages is the same, then compare last names

         */


        Comparator<Person> cmp = Comparator.comparing(Person::getLastName)
                .thenComparing(Person::getFirstName)
                .thenComparing(Person::getAge);
    }
}
