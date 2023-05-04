package ru.user.d4ker;

import java.util.List;

public class LogicalTasksSolver {

    public static class Room {
        private boolean glowing;

        public Room(boolean glowing) {
            this.glowing = glowing;
        }

        public boolean isGlowing() {
            return glowing;
        }

        public void setGlowing(boolean glowing) {
            this.glowing = glowing;
        }
    }

    static public int solveInfRoomsTask(List<Room> roomList, int startIndex) {
        if (roomList.isEmpty()) {
            return 0;
        }

        final Room startRoom = roomList.get(startIndex);
        startRoom.setGlowing(true);

        final int roomListSize = roomList.size();
        int roomCount = 0;
        int index = startIndex;
        while (true) {
            index = index >= roomListSize - 1 ? 0 : index + 1;
            roomCount++;

            final Room nextRoom = roomList.get(index);
            if (nextRoom.isGlowing()) {
                nextRoom.setGlowing(false);

                for (int i = 0; i < roomCount; i++) {
                    index = index <= 0 ? roomListSize - 1 : index - 1;
                }

                if (!roomList.get(index).isGlowing()) {
                    return roomCount;
                }

                for (int i = 0; i < roomCount; i++) {
                    index = index >= roomListSize - 1 ? 0 : index + 1;
                }
            }
        }
    }
}
