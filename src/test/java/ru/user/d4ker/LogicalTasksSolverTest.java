package ru.user.d4ker;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogicalTasksSolverTest {

    @Test
    public void testSolveInfRoomsTask() {
        List<LogicalTasksSolver.Room> roomList = List.of();
        assertEquals(0, LogicalTasksSolver.solveInfRoomsTask(roomList, 0));

        roomList = List.of(new LogicalTasksSolver.Room(true));
        assertEquals(1, LogicalTasksSolver.solveInfRoomsTask(roomList, 0));

        roomList = List.of(new LogicalTasksSolver.Room(false));
        assertEquals(1, LogicalTasksSolver.solveInfRoomsTask(roomList, 0));

        roomList = IntStream.range(0, 3)
                .mapToObj(value -> new LogicalTasksSolver.Room(true))
                .toList();
        assertEquals(3, LogicalTasksSolver.solveInfRoomsTask(roomList, 1));

        roomList = IntStream.range(0, 3)
                .mapToObj(value -> new LogicalTasksSolver.Room(false))
                .toList();
        assertEquals(3, LogicalTasksSolver.solveInfRoomsTask(roomList, 2));
    }

    @Test
    public void testSolveInfRoomsTaskRandom() {
        for (int i = 0; i < 100; i++) {
            final Random random = new Random();
            final List<LogicalTasksSolver.Room> roomList = IntStream.range(0, random.nextInt(1000))
                    .mapToObj(value -> new LogicalTasksSolver.Room(random.nextBoolean()))
                    .toList();
            if (roomList.isEmpty()) {
                continue;
            }
            assertEquals(
                    roomList.size(),
                    LogicalTasksSolver.solveInfRoomsTask(roomList, random.nextInt(roomList.size()))
            );
        }
    }
}
