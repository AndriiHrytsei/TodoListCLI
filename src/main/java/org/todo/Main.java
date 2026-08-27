package org.todo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        TodoList tasks = new TodoList();

        UI ui = new UI(tasks, scan);

        ui.start();
    }
}