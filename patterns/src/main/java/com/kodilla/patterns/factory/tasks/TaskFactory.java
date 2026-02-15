package com.kodilla.patterns.factory.tasks;

public class TaskFactory {

    public static final String SHOPPING = "SHOPPING";
    public static final String PAINTING = "PAINTING";
    public static final String DRIVING = "DRIVING";

    public Task createTask(final String taskType) {
        return switch (taskType) {
            case SHOPPING -> new ShoppingTask("Daily-shopping", "apples", 5);
            case PAINTING -> new PaintingTask("Kitchen-renovation", "blue", "wall");
            case DRIVING -> new DrivingTask("Go-to-school", "school", "car");
            default -> null;
        };
    }
}
