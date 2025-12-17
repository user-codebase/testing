package com.kodilla.testing.forum.statistics;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class ForumStatisticsTestSuite {

    private static int testNumber;

    @Mock
    private Statistics statisticsMock;

    @BeforeAll
    static void beforeAll() {
        testNumber = 0;
        System.out.println("Tests started");
    }

    @BeforeEach
    void beforeEach() {
        testNumber++;
        System.out.println("Test number: " + testNumber + " started");
    }

    @AfterEach
    void afterEach() {
        System.out.println("Test number: " + testNumber + " finished");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Tests finished");
    }


    // support function that generate list with names to facilitate tests
    private List<String> generateListWithUserNames(int numberOfUsers) {

        List<String> listOfNames = new ArrayList<>();

        if (numberOfUsers == 0) {
            return listOfNames;
        }else{
            for (int i = 0; i < numberOfUsers; i++) {
                listOfNames.add("User " + i);
            }
            return listOfNames;
        }
    }


    @Nested
    class PostsTests {


        @Test
        void calculateStatisticsWhenNumberOfPostsIsZERO() {

            // Given
            StatisticsManager statisticsManager = new StatisticsManager(statisticsMock);
            List<String> usersNames = generateListWithUserNames(50);
            when(statisticsMock.usersNames()).thenReturn(usersNames);
            when(statisticsMock.postsCount()).thenReturn(0);
            when(statisticsMock.commentsCount()).thenReturn(10);

            // When
            statisticsManager.calculateAdvStatistics(statisticsMock);
            int returnedNumberOfUsers = statisticsManager.getNumberOfUsers();
            int returnedNumberOfComments = statisticsManager.getNumberOfComments();
            int returnedNumberOfPosts = statisticsManager.getNumberOfPosts();
            double returnedAverageNumberOfCommentsPerUser = statisticsManager.getAverageNumberOfCommentsPerUser();
            double returnedAverageNumberOfPostsPerUser = statisticsManager.getAverageNumberOfPostsPerUser();
            double returnedAverageNumberOfCommentsPerPost = statisticsManager.getAverageNumberOfCommentsPerPost();


            // Then
            Assertions.assertEquals(50, returnedNumberOfUsers);
            Assertions.assertEquals(10, returnedNumberOfComments);
            Assertions.assertEquals(0, returnedNumberOfPosts);
            Assertions.assertEquals(0.2, returnedAverageNumberOfCommentsPerUser);
            Assertions.assertEquals(0, returnedAverageNumberOfPostsPerUser);
            Assertions.assertEquals(0, returnedAverageNumberOfCommentsPerPost);
            System.out.println(statisticsManager);
        }

        @Test
        void calculateStatisticsWhenNumberOfPostsIs1000() {

            // Given
            StatisticsManager statisticsManager = new StatisticsManager(statisticsMock);
            List<String> usersNames = generateListWithUserNames(50);
            when(statisticsMock.usersNames()).thenReturn(usersNames);
            when(statisticsMock.postsCount()).thenReturn(1000);
            when(statisticsMock.commentsCount()).thenReturn(1000);

            // When
            statisticsManager.calculateAdvStatistics(statisticsMock);
            int returnedNumberOfUsers = statisticsManager.getNumberOfUsers();
            int returnedNumberOfComments = statisticsManager.getNumberOfComments();
            int returnedNumberOfPosts = statisticsManager.getNumberOfPosts();
            double returnedAverageNumberOfCommentsPerUser = statisticsManager.getAverageNumberOfCommentsPerUser();
            double returnedAverageNumberOfPostsPerUser = statisticsManager.getAverageNumberOfPostsPerUser();
            double returnedAverageNumberOfCommentsPerPost = statisticsManager.getAverageNumberOfCommentsPerPost();


            // Then
            Assertions.assertEquals(50, returnedNumberOfUsers);
            Assertions.assertEquals(1000, returnedNumberOfComments);
            Assertions.assertEquals(1000, returnedNumberOfPosts);
            Assertions.assertEquals(20, returnedAverageNumberOfCommentsPerUser);
            Assertions.assertEquals(20, returnedAverageNumberOfPostsPerUser);
            Assertions.assertEquals(1, returnedAverageNumberOfCommentsPerPost);
            System.out.println(statisticsManager);

        }
    }

    @Nested
    class CommentsTests {
        @Test
        void calculateStatisticsWhenNumberOfCommentsIsZERO() {
            // Given
            StatisticsManager statisticsManager = new StatisticsManager(statisticsMock);
            List<String> listWith20Users = generateListWithUserNames(20);
            when(statisticsMock.commentsCount()).thenReturn(0);
            when(statisticsMock.usersNames()).thenReturn(listWith20Users);

            // When
            statisticsManager.calculateAdvStatistics(statisticsMock);
            int returnedNumberOfUsers = statisticsManager.getNumberOfUsers();
            int returnedNumberOfComments = statisticsManager.getNumberOfComments();
            double returnedAverageNumberOfCommentsPerUser = statisticsManager.getAverageNumberOfCommentsPerUser();
            double  returnedAverageNumberOfCommentsPerPost = statisticsManager.getAverageNumberOfCommentsPerPost();


            // Then
            Assertions.assertEquals(20, returnedNumberOfUsers);
            Assertions.assertEquals(0, returnedNumberOfComments);
            Assertions.assertEquals(0, returnedAverageNumberOfCommentsPerUser);
            Assertions.assertEquals(0, returnedAverageNumberOfCommentsPerPost);
            System.out.println(statisticsManager);
        }

        @Test
        void calculateStatisticsWhenNumberOfCommentsIsLessThanNumberOfPosts() {
            // Given
            StatisticsManager statisticsManager = new StatisticsManager(statisticsMock);
            List<String> listWith20Users = generateListWithUserNames(20);
            when(statisticsMock.usersNames()).thenReturn(listWith20Users);
            when(statisticsMock.commentsCount()).thenReturn(40);
            when(statisticsMock.postsCount()).thenReturn(90);

            // When
            statisticsManager.calculateAdvStatistics(statisticsMock);
            int returnedNumberOfUsers = statisticsManager.getNumberOfUsers();
            int returnedNumberOfComments = statisticsManager.getNumberOfComments();
            int returnedNumberOfPosts = statisticsManager.getNumberOfPosts();
            double returnedAverageNumberOfCommentsPerUser = statisticsManager.getAverageNumberOfCommentsPerUser();
            double returnedAverageNumberOfPostsPerUser = statisticsManager.getAverageNumberOfPostsPerUser();
            double returnedAverageNumberOfCommentsPerPost = statisticsManager.getAverageNumberOfCommentsPerPost();

            // Then
            Assertions.assertEquals(20, returnedNumberOfUsers);
            Assertions.assertEquals(40, returnedNumberOfComments);
            Assertions.assertEquals(90, returnedNumberOfPosts);
            Assertions.assertEquals(2, returnedAverageNumberOfCommentsPerUser);
            Assertions.assertEquals(4.5, returnedAverageNumberOfPostsPerUser);
            Assertions.assertEquals(0.44, returnedAverageNumberOfCommentsPerPost);
            System.out.println(statisticsManager);
        }

        @Test
        void calculateStatisticsWhenNumberOfCommentsIsGreaterThanNumberOfPosts() {
            // Given
            StatisticsManager statisticsManager = new StatisticsManager(statisticsMock);
            List<String> listWith20Users = generateListWithUserNames(30);
            when(statisticsMock.usersNames()).thenReturn(listWith20Users);
            when(statisticsMock.commentsCount()).thenReturn(80);
            when(statisticsMock.postsCount()).thenReturn(25);

            // When
            statisticsManager.calculateAdvStatistics(statisticsMock);
            int returnedNumberOfUsers = statisticsManager.getNumberOfUsers();
            int returnedNumberOfComments = statisticsManager.getNumberOfComments();
            int returnedNumberOfPosts = statisticsManager.getNumberOfPosts();
            double returnedAverageNumberOfCommentsPerUser = statisticsManager.getAverageNumberOfCommentsPerUser();
            double returnedAverageNumberOfPostsPerUser = statisticsManager.getAverageNumberOfPostsPerUser();
            double returnedAverageNumberOfCommentsPerPost = statisticsManager.getAverageNumberOfCommentsPerPost();

            // Then
            Assertions.assertEquals(30, returnedNumberOfUsers);
            Assertions.assertEquals(80, returnedNumberOfComments);
            Assertions.assertEquals(25, returnedNumberOfPosts);
            Assertions.assertEquals(2.67, returnedAverageNumberOfCommentsPerUser);
            Assertions.assertEquals(0.83, returnedAverageNumberOfPostsPerUser);
            Assertions.assertEquals(3.2, returnedAverageNumberOfCommentsPerPost);
            System.out.println(statisticsManager);
        }
    }

    @Nested
    class UsersTests {

        @Test
        void calculateStatisticsWhenNumberOfUsersIsZERO() {

            // Given
            StatisticsManager statisticsManager = new StatisticsManager(statisticsMock);

            List<String> listWithUsers = generateListWithUserNames(0);
            when(statisticsMock.usersNames()).thenReturn(listWithUsers);

            // When
            statisticsManager.calculateAdvStatistics(statisticsMock);
            int returnedNumberOfUsers = statisticsManager.getNumberOfUsers();
            int returnedNumberOfPosts = statisticsManager.getNumberOfPosts();
            int returnedNumberOfComments = statisticsManager.getNumberOfComments();
            double returnedAverageNumberOfPostsPerUser = statisticsManager.getAverageNumberOfPostsPerUser();
            double returnedAverageNumberOfCommentsPerUser = statisticsManager.getAverageNumberOfCommentsPerUser();
            double returnedAverageNumberOfCommentsPerPost = statisticsManager.getAverageNumberOfCommentsPerPost();

            // Then
            Assertions.assertEquals(0, statisticsManager.getNumberOfUsers());
            Assertions.assertEquals(0, returnedNumberOfUsers);
            Assertions.assertEquals(0, returnedNumberOfPosts);
            Assertions.assertEquals(0, returnedNumberOfComments);
            Assertions.assertEquals(0, returnedAverageNumberOfPostsPerUser);
            Assertions.assertEquals(0, returnedAverageNumberOfCommentsPerUser);
            Assertions.assertEquals(0, returnedAverageNumberOfCommentsPerPost);
            statisticsManager.showStatistics();

        }

        @Test
        void calculateStatisticsWhenNumberOfUsersIs100() {

            // Given
            StatisticsManager statisticsManager = new StatisticsManager(statisticsMock);
            List<String> listWithUserNames = generateListWithUserNames(100);
            when(statisticsMock.usersNames()).thenReturn(listWithUserNames);
            when(statisticsMock.commentsCount()).thenReturn(100);
            when(statisticsMock.postsCount()).thenReturn(100);

            // When
            statisticsManager.calculateAdvStatistics(statisticsMock);
            int returnedNumberOfUsers = statisticsManager.getNumberOfUsers();
            int returnedNumberOfPosts = statisticsManager.getNumberOfPosts();
            int returnedNumberOfComments = statisticsManager.getNumberOfComments();
            double returnedAverageNumberOfPostsPerUser = statisticsManager.getAverageNumberOfPostsPerUser();
            double returnedAverageNumberOfCommentsPerUser = statisticsManager.getAverageNumberOfCommentsPerUser();
            double returnedAverageNumberOfCommentsPerPost = statisticsManager.getAverageNumberOfCommentsPerPost();

            // Then
            Assertions.assertEquals(100, statisticsManager.getNumberOfUsers());
            Assertions.assertEquals(100, returnedNumberOfPosts);
            Assertions.assertEquals(100, returnedNumberOfComments);
            Assertions.assertEquals(1, returnedAverageNumberOfPostsPerUser);
            Assertions.assertEquals(1, returnedAverageNumberOfCommentsPerUser);
            Assertions.assertEquals(1, returnedAverageNumberOfCommentsPerPost);
            statisticsManager.showStatistics();
        }
    }
}
