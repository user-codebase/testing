package com.kodilla.hibernate.tasklist.dao;

import com.kodilla.hibernate.tasklist.TaskList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskListDaoTestSuite {

    @Autowired
    private TaskListDao taskListDao;
    private static final String TASK_LIST_NAME = "Test: List Name ABC";
    private static final String TASK_LIST_DESCRIPTION = "Test: List Description ABC";


    @Test
    void testFindByListName(){
        //Given
        TaskList taskList = new TaskList(TASK_LIST_NAME, TASK_LIST_DESCRIPTION);
        String taskListName = taskList.getListName();
        System.out.println(taskListName);
        taskListDao.save(taskList);

        //When
        List<TaskList> listWithTaskLists = taskListDao.findByListName(taskListName);

        //Then
        assertEquals(1, listWithTaskLists.size());
        assertEquals(TASK_LIST_NAME, listWithTaskLists.get(0).getListName());

        //CleanUp
        int id = listWithTaskLists.get(0).getId();
        taskListDao.deleteById(id);
    }
}
