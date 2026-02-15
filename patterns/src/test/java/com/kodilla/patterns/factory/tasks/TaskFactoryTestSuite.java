package com.kodilla.patterns.factory.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskFactoryTestSuite {

    @Test
    public void testFactoryCreatesTasks() {
        //Given
        TaskFactory factory = new TaskFactory();

        //When
        Task shoppingTask = factory.createTask(TaskFactory.SHOPPING);
        Task paintingTask = factory.createTask(TaskFactory.PAINTING);
        Task drivingTask = factory.createTask(TaskFactory.DRIVING);

        assertEquals("Daily-shopping", shoppingTask.getTaskName());
        assertEquals("Kitchen-renovation", paintingTask.getTaskName());
        assertEquals("Go-to-school", drivingTask.getTaskName());
    }

    @Test
    public void testTaskExecution() {
        //Given
        TaskFactory factory = new TaskFactory();

        //When
        Task shoppingTask = factory.createTask(TaskFactory.SHOPPING);
        Task paintingTask = factory.createTask(TaskFactory.PAINTING);
        Task drivingTask = factory.createTask(TaskFactory.DRIVING);
        boolean isExecutedShoppingTask = shoppingTask.isTaskExecuted();
        boolean isExecutedPaintingTask = paintingTask.isTaskExecuted();
        boolean isExecutedDrivingTask = drivingTask.isTaskExecuted();

        //Then
        assertFalse(isExecutedShoppingTask);
        shoppingTask.executeTask();
        assertTrue(shoppingTask.isTaskExecuted());

        assertFalse(isExecutedPaintingTask);
        paintingTask.executeTask();
        assertTrue(paintingTask.isTaskExecuted());

        assertFalse(isExecutedDrivingTask);
        drivingTask.executeTask();
        assertTrue(drivingTask.isTaskExecuted());
    }
}