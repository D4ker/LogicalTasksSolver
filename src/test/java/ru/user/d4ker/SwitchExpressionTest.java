package ru.user.d4ker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwitchExpressionTest {

    private enum Animal {
        DOG, CAT, HORSE, ELEPHANT, LION
    }

    private void testSwitchOld(Animal animal) {
        switch (animal) {
            case DOG:
            case CAT:
            case HORSE:
                System.out.println("DOG");
                System.out.println("CAT");
                System.out.println("HORSE");
                break;
            case ELEPHANT:
                System.out.println("ELEPHANT");
                break;
            default:
                System.out.println("Default animal is LION");
        }
    }

    private void testSwitchNew(Animal animal) {
        switch (animal) {
            case DOG, CAT, HORSE -> {
                System.out.println("DOG");
                System.out.println("CAT");
                System.out.println("HORSE");
            }
            case ELEPHANT -> System.out.println("ELEPHANT");
            default -> System.out.println("Default animal is LION");
        }
    }

    private void testSwitchWithoutYield(Animal animal) {
        final List<String> animals = switch (animal) {
            case DOG, CAT, HORSE -> List.of("DOG", "CAT", "HORSE");
            case ELEPHANT -> List.of("ELEPHANT");
            default -> List.of("LION");
        };
        animals.forEach(System.out::println);
    }

    private void testSwitchWithYield(Animal animal) {
        final List<String> animals = switch (animal) {
            case DOG, CAT, HORSE -> {
                final List<String> animalsLocal = new ArrayList<>();
                animalsLocal.add("DOG");
                animalsLocal.add("CAT");
                animalsLocal.add("HORSE");
                yield animalsLocal;
            }
            case ELEPHANT -> Arrays.asList("ELEPHANT");
            default -> Arrays.asList("LION");
        };
        animals.forEach(System.out::println);
    }

    @Test
    public void test() {
        testSwitchOld(Animal.CAT);
        testSwitchNew(Animal.CAT);
        testSwitchWithoutYield(Animal.ELEPHANT);
        testSwitchWithYield(Animal.CAT);
    }
}
