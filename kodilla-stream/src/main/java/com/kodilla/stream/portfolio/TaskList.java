package com.kodilla.stream.portfolio;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class TaskList {

    private final List<Task> tasks = new LinkedList<>();
    private final String name;

    public TaskList(final String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return new LinkedList<>(tasks);
    }

    public String getName() {
        return name;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean removeTask(Task task) {
        return tasks.remove(task);
    }

    @Override
    public String toString() {
        return "TaskList{" +
                "tasks=" + tasks +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TaskList taskList)) return false;
        return Objects.equals(name, taskList.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
