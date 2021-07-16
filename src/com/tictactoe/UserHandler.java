package com.tictactoe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserHandler {
    private static ArrayList<Player> users = new ArrayList<>();

    UserHandler() throws IOException {
        this(new File("users.txt"));
    }

    UserHandler(File userStorage) throws IOException {
        if (userStorage.exists() && (userStorage.length() > 0)) {
            getUsersFromFile(userStorage);
        }
    }

    private static boolean isUsernameUnique(String username) {
        return findUser(username) == -1;
    }

    public static int findUser(String username) {
        if (users != null) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getName().equalsIgnoreCase(username)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void saveUsersIntoFile(File filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Player u : users) {
                writer.write(u.getName() + " " + u.getScore());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getUsersFromFile(File userStorage) {
        String[] splitLine;
        try {
            List<String> allLines = Files.readAllLines(Paths.get(String.valueOf(userStorage)));
            for (String line : allLines) {
                splitLine = line.split(" ");
                users.add(new Player(splitLine[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
