package ru.user.d4ker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwitchExpressionTest {

    private enum Animal {
        DOG, CAT, HORSE, ELEPHANT, LION
    }

    private String getAndPrintAnimalNamesWithOldSwitch(Animal animal) {
        final String animalNames;
        switch (animal) {
            case DOG:
            case CAT:
            case HORSE:
                System.out.println("DOG");
                System.out.println("CAT");
                System.out.println("HORSE");
                animalNames = "DOG, CAT, HORSE";
                break;
            case ELEPHANT:
                System.out.println("ELEPHANT");
                animalNames = "ELEPHANT";
                break;
            default:
                System.out.println("Default animal is LION");
                animalNames = "LION";
        }
        return animalNames;
    }

    private String getAndPrintAnimalNamesWithNewSwitch(Animal animal) {
        final String animalNames;
        switch (animal) {
            case DOG, CAT, HORSE -> {
                System.out.println("DOG");
                System.out.println("CAT");
                System.out.println("HORSE");
                animalNames = "DOG, CAT, HORSE";
            }
            case ELEPHANT -> {
                System.out.println("ELEPHANT");
                animalNames = "ELEPHANT";
            }
            default -> {
                System.out.println("Default animal is LION");
                animalNames = "LION";
            }
        }
        return animalNames;
    }

    private List<String> getAndPrintAnimalListWithoutYield(Animal animal) {
        final List<String> animals = switch (animal) {
            case DOG, CAT, HORSE -> List.of("DOG", "CAT", "HORSE");
            case ELEPHANT -> List.of("ELEPHANT");
            default -> List.of("LION");
        };
        animals.forEach(System.out::println);
        return animals;
    }

    private List<String> getAndPrintAnimalListWithYield(Animal animal) {
        final List<String> animalsLocal = new ArrayList<>();
        final List<String> animals = switch (animal) {
            case DOG, CAT, HORSE -> {
                animalsLocal.add("DOG");
                animalsLocal.add("CAT");
                animalsLocal.add("HORSE");
                yield animalsLocal;
            }
            case ELEPHANT -> {
                animalsLocal.add("ELEPHANT");
                yield animalsLocal;
            }
            default -> {
                animalsLocal.add("LION");
                yield animalsLocal;
            }
        };
        animals.forEach(System.out::println);
        return animals;
    }

    @Test
    public void testSwitchExpression() {
        assertEquals("DOG, CAT, HORSE", getAndPrintAnimalNamesWithOldSwitch(Animal.CAT));
        assertEquals("DOG, CAT, HORSE", getAndPrintAnimalNamesWithNewSwitch(Animal.CAT));
        assertEquals(List.of("ELEPHANT"), getAndPrintAnimalListWithoutYield(Animal.ELEPHANT));
        assertEquals(List.of("DOG", "CAT", "HORSE"), getAndPrintAnimalListWithYield(Animal.CAT));
    }
}
