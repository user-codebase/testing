package com.kodilla.spring.portfolio;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTestSuite {

    @Test
    void testTaskAdd() {
        //Given
        ApplicationContext context = new AnnotationConfigApplicationContext(BoardConfig.class);
        Board board = context.getBean(Board.class);

        //When
        board.getToDoList().addTask("Task - toDoList");
        board.getInProgressList().addTask("Task - inProgressList");
        board.getDoneList().addTask("Task - doneList");

        //Then
        assertEquals("Task - toDoList", board.getToDoList().getTasks().get(0));
        assertEquals("Task - inProgressList", board.getInProgressList().getTasks().get(0));
        assertEquals("Task - doneList", board.getDoneList().getTasks().get(0));
    }

}
