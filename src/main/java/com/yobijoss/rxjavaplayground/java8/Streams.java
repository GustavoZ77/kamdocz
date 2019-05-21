package com.yobijoss.rxjavaplayground.java8;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {

    public static void main(String[] args) {
        //predicateExample();
        //mapExample();
        //reduceExample();
        collectExample();

    }

    private static void collectExample() {
        List<Person> people = Arrays.asList(new Person("Jose", 25),
                new Person("Ale", 30),
                new Person("Pablito", 44),
                new Person("Martita", 26),
                new Person("Sandy", 26),
                new Person("Ray", 28),
                new Person("Pepe", 25),
                new Person("Pepe", 27),
                new Person("Pepe", 29),
                new Person("Sac", 20),
                new Person("Nicole", 14),
                new Person("Ame", 14)
        );

        Optional<Person> min = people.stream()
                .filter(p -> p.getAge() >= 23)
                .min(Comparator.comparing(Person::getAge));

        System.out.println("Min " + min);

        Optional<Person> max = people.stream()
                .max(Comparator.comparing(Person::getAge));

        System.out.println("Max " + max);

        Map<Integer, List<Person>> map = people.stream()
                .collect(
                        Collectors.groupingBy(Person::getAge)
                );

        System.out.println(map);

        Map<Integer, Long> longMap = people.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getAge,
                                Collectors.counting()
                        )
                );

        System.out.println(longMap);


        Map<Integer, List<String>> listMap = people.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getAge,
                                Collectors.mapping(
                                        Person::getName,
                                        Collectors.toList()
                                )
                        )
                );

        System.out.println(listMap);


        Map<Integer, Set<String>> setMap = people.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getAge,
                                Collectors.mapping(
                                        Person::getName,
                                        //los pone en orden a los nombres jua jua
                                        Collectors.toCollection(TreeSet::new)
                                )
                        )
                );

        System.out.println(setMap);


        Map<Integer, String> ageNames = people.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getAge,
                                Collectors.mapping(
                                        Person::getName,
                                        //los pone en orden a los nombres jua jua
                                        Collectors.joining(", ")
                                )
                        )
                );

        System.out.println(ageNames);


    }

    private static void reduceExample() {

        Stream<Integer> stream = Stream.empty();

        Integer reduce = stream.reduce(0, (i1, i2) -> i1 + i2);

        System.out.println(reduce);

        stream = Stream.of(1);

        reduce = stream.reduce(0, (i1, i2) -> i1 + i2);

        System.out.println(reduce);

        stream = Stream.of(1, 2, 3);

        reduce = stream.reduce(0, (i1, i2) -> i1 + i2);

        System.out.println(reduce);
    }

    private static void mapExample() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        List<Integer> list2 = Arrays.asList(2, 4, 6);
        List<Integer> list3 = Arrays.asList(3, 5, 7);

        List<List<Integer>> list = Arrays.asList(list1, list2, list3);

        System.out.println(list);

        Function<List<?>, Integer> listSize = l -> l.size();
        Function<List<?>, Stream<?>> flatMapper = l -> l.stream();

        System.out.println("Map");

        list.stream()
                .map(listSize)
                .forEach(System.out::println);

        System.out.println("Flat Map");

        list.stream()
                .flatMap(flatMapper)
                .forEach(System.out::println);
    }

    private static void predicateExample() {
        Stream<String> stream = Stream.of("uno", "dos", "tres", "cuatro", "cinco", "seis");

        Predicate<String> p1 = s -> s.length() > 3;
        Predicate<String> p2 = Predicate.isEqual("dos");

        //stream.filter(p1.or(p2))
        //      .forEach(System.out::println);

        List<String> list = new ArrayList<>();

        stream.peek(System.out::println)
                .filter(p1.or(p2))
                .forEach(list::add);

        System.out.println("Done " + list.size());
    }


}
