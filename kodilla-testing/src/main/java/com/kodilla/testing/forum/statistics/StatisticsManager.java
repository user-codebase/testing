package com.kodilla.testing.forum.statistics;

public class StatisticsManager {

    private Statistics statistics;
    private int numberOfUsers;
    private int numberOfPosts;
    private int numberOfComments;
    private double averageNumberOfPostsPerUser;
    private double averageNumberOfCommentsPerUser;
    private double averageNumberOfCommentsPerPost;


    public StatisticsManager(Statistics statistics) {
        this.statistics = statistics;
    }

    public void calculateAdvStatistics(Statistics statistics){
        numberOfUsers = statistics.usersNames().size();
        numberOfPosts = statistics.postsCount();
        numberOfComments = statistics.commentsCount();

        if (numberOfUsers > 0) {
            averageNumberOfPostsPerUser = Math.round(100.0 * numberOfPosts / numberOfUsers) / 100.0;
            averageNumberOfCommentsPerUser = Math.round(100.0 * numberOfComments / numberOfUsers) / 100.0;
        }else{
            averageNumberOfPostsPerUser = 0;
            averageNumberOfCommentsPerUser = 0;
        }
        if (numberOfPosts > 0) {
            averageNumberOfCommentsPerPost = Math.round(100.0 * numberOfComments/numberOfPosts) / 100.0;
        }else{
            averageNumberOfCommentsPerPost = 0;
        }
    }

    public void showStatistics(){
        System.out.println(this);
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public int getNumberOfPosts() {
        return numberOfPosts;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public double getAverageNumberOfPostsPerUser() {
        return averageNumberOfPostsPerUser;
    }

    public double getAverageNumberOfCommentsPerUser() {
        return averageNumberOfCommentsPerUser;
    }

    public double getAverageNumberOfCommentsPerPost() {
        return averageNumberOfCommentsPerPost;
    }

    @Override
    public String toString() {
        return "StatisticsManager{" +
                "numberOfUsers=" + numberOfUsers +
                ", numberOfPosts=" + numberOfPosts +
                ", numberOfComments=" + numberOfComments +
                ", averageNumberOfPostsPerUser=" + averageNumberOfPostsPerUser +
                ", averageNumberOfCommentsPerUser=" + averageNumberOfCommentsPerUser +
                ", averageNumberOfCommentsPerPost=" + averageNumberOfCommentsPerPost +
                '}';
    }
}
