package org.todo;

import java.util.Scanner;

public class UI {
    private final TodoList todoList;
    private final Scanner scanner;

    public UI(TodoList todoList, Scanner scanner) {
        this.todoList = todoList;
        this.scanner = scanner;
    }

    public void start() {
        this.todoList.loadTasks();
        loop:
        while (true) {
            IO.println("\nWelcome to TodoList! What would you like to do? (\"a\" for add; \"d\" for delete; \"e\" for edit; \"q\" for quit; \"m\" for mark as completed \"c\" for clear\n);");
            printTasks();
            String action = scanner.nextLine();

            switch (action) {
                case "a":
                    addTask();
                    break;
                case "d":
                    deleteTask();
                    break;
                case "e":
                    editTask();
                    break;
                case "m":
                    markAsCompleted();
                    break;
                case "c":
                    clear();
                    break;
                case "q":
                    this.todoList.saveTasks();
                    break loop;
                default:
                    System.out.println("Invalid input. Please choose a valid option.");
                    break;
            }
        }
    }

    public void printTasks() {
        IO.println();
        if (this.todoList.isEmpty()) {
            IO.println("There are no tasks yet!\n");
            return;
        }
        this.todoList.printTasks();
        IO.println();
    }

    public void addTask() {
        while (true) {
            printTasks();
            IO.print("Add task(write \"\" in order to quit): ");
            String newTask = scanner.nextLine();
            if (newTask.isEmpty()) {
                break;
            }
            this.todoList.addTask(newTask);
        }
    }

    public void deleteTask() {
        while (true) {
            if (this.todoList.isEmpty()) break;

            printTasks();
            IO.print("Give the number of the task to delete(write \"-1\" in order to quit delete mode): ");
            try {
                int index = Integer.parseInt(scanner.nextLine());
                if (index == -1) break;
                this.todoList.deleteTask(index - 1);
            } catch (Exception e) {
                IO.println("Invalid input. Please give the number of the task to delete!.");
            }
        }
    }

    public void editTask() {
        while (true) {
            printTasks();
            IO.print("Give the number of the task to edit(write \"-1\" in order to quit edit mode): ");
            try {
                int index = Integer.parseInt(scanner.nextLine());
                if (index == -1) break;

                IO.print("Enter new task: ");

                String newTask = scanner.nextLine();
                if (newTask.isEmpty()) {
                    continue;
                }
                this.todoList.editTask((index - 1), newTask);
            } catch (Exception e) {
                IO.println("Invalid input. Please give the number of the task to edit.");
            }
        }
    }

    public void markAsCompleted() {
        while (true) {
            printTasks();
            IO.print("Mark as completed? (Give the number of the task): ");
            try {
                int index = Integer.parseInt(scanner.nextLine());
                if (index == -1) break;
                this.todoList.setAsCompleted(index - 1);
            } catch (Exception e) {
                IO.println("Invalid input. Please give the number of the task to mark as completed.");
            }
        }
    }

    public void clear() {
        IO.println("Are you sure you want to delete all your tasks? This action is irreversible!(y/n)");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("n")) {
            return;
        } else if (answer.equalsIgnoreCase("y")) {
            this.todoList.clear();
        }
    }
}