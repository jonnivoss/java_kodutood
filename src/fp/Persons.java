package fp;

import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Persons {

    private List<Person> persons = List.of(
            new Person(1, "Alice", 22),
            new Person(2, "Bob", 20),
            new Person(3, "Carol", 21));

    @Test
    public void findsPersonById() {
        Optional<Person> first = persons.stream()
                .filter(p -> p.getId().equals(2))
                .findFirst();
        System.out.println(first);
    }

    @Test
    public void removePersonById() {
        List<Person> list = persons.stream()
                .filter(p -> !p.getId().equals(2))
                .toList();
        System.out.println(list);
    }

    @Test
    public void findsPersonNames() {
         String names = persons.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", "));
        System.out.println(names);
    }

    @Test
    public void reverseSortedByAge() {
        List<Person> names = persons.stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(p -> p.getAge())))
                .toList();
        System.out.println(names);
    }

}
