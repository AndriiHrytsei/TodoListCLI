public class Task {
    private String task;
    private boolean done;

    public Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    public String getTask() {
        return this.task;
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone() {
        this.done = !this.done;
    }

    public void setTask(String newTask) {
        this.task = newTask;
    }

    @Override
    public String toString() {
        return "[" + (isDone() ? "X] " : " ] ") + this.task;
    }
}
