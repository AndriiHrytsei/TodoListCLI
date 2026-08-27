import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private final List<Task> todoList;

    public TodoList() {
        this.todoList = new ArrayList<>();
    }

    public void printTasks() {
        for (int i = 0; i < this.todoList.size(); i++) {
            IO.println((i + 1) + ") " + this.todoList.get(i));
        }
    }

    public void addTask(String task){
        Task taskToAdd = new Task(task, false);
        this.todoList.add(taskToAdd);
    }

    public void deleteTask(int index) {
        this.todoList.remove(index);
    }

    public void editTask(int index, String task) {
        this.todoList.get(index).setTask(task);
    }

    public void saveTasks() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter("tasks.csv"));
            for (Task task: this.todoList) {
                out.println(task.getTask() + "," + task.isDone());
            }
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTasks() {
        try(BufferedReader reader = new BufferedReader(new FileReader("tasks.csv"))) {
            String row;
            while ((row = reader.readLine()) != null) {
                String[] info = row.split(",");
                String task = info[0];
                boolean isDone = Boolean.parseBoolean(info[1]);
                this.todoList.add(new Task(task, isDone));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void clear() {
        this.todoList.clear();
    }

    public void setAsCompleted(int index) {
        this.todoList.get(index).setDone();
    }

    public boolean isEmpty() {
        return this.todoList.isEmpty();
    }

}