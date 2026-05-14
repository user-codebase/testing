package com.kodilla.testing2.crudapp;

import com.kodilla.testing2.config.WebDriverConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CrudAppTestSuite {

    private static final String BASE_URL = "https://user-codebase.github.io/";
    private WebDriver driver;
    private Random generator;

    @BeforeEach
    public void initTests() {
        driver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driver.get(BASE_URL);
        generator = new Random();
    }

    @AfterEach
    public void cleanUpAfterTest() {
        driver.quit();
    }

    private String createCrudAppTestTask() {
        final String XPATH_TASK_NAME = "//form[contains(@action,\"createTask\")]/fieldset[1]/input";
        final String XPATH_TASK_CONTENT = "//form[contains(@action,\"createTask\")]/fieldset[2]/textarea";
        final String XPATH_ADD_BUTTON = "//form[contains(@action,\"createTask\")]/fieldset[3]/button";
        String taskName = "Task number " + generator.nextInt(100000);
        String taskContent = taskName + " content";

        WebElement name = driver.findElement(By.xpath(XPATH_TASK_NAME));
        name.sendKeys(taskName);

        WebElement content = driver.findElement(By.xpath(XPATH_TASK_CONTENT));
        content.sendKeys(taskContent);

        WebElement addButton = driver.findElement(By.xpath(XPATH_ADD_BUTTON));
        addButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.xpath("//p[contains(.,'" + taskName + "')]"),
                        0
                ));

        return taskName;
    }


    private void deleteCrudAppTestTask(String taskName) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        WebElement row = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//form[contains(@class,'datatable__row')]"
                        + "[.//p[contains(.,'" + taskName + "')]]")
        ));


        WebElement deleteButton = row.findElement(
                By.xpath(".//button[@data-task-delete-button]")
        );


        deleteButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//p[contains(.,'" + taskName + "')]")
                ));

        System.out.println(
                driver.findElements(By.xpath("//p[contains(.,'" + taskName + "')]")).size()
        );

    }


    private void sendTestTaskToTrello(String taskName) {
        driver.navigate().refresh();


        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[1]")));

        driver.findElements(
                        By.xpath("//form[@class=\"datatable__row\"]")).stream()
                .filter(anyForm ->
                        anyForm.findElement(By.xpath(".//p[@class=\"datatable__field-value\"]"))
                                .getText().equals(taskName))
                .forEach(theForm -> {
                    WebElement selectElement = theForm.findElement(By.xpath(".//select[1]"));
                    Select select = new Select(selectElement);
                    select.selectByIndex(1);

                    WebElement buttonCreateCard =
                            theForm.findElement(By.xpath(".//button[contains(@class, \"card-creation\")]"));
                    buttonCreateCard.click();

                    try {
                        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5))
                                .until(ExpectedConditions.alertIsPresent());

                        System.out.println("Alert: " + alert.getText());
                        alert.accept();

                    } catch (TimeoutException e) {
                        System.out.println("No alert appeared");
                    }
                });
    }


    @Test
    public void shouldCreateTrelloCard() throws InterruptedException {
        String taskName = createCrudAppTestTask();
        try {
            sendTestTaskToTrello(taskName);
            assertTrue(checkTaskExistsInTrello(taskName));
        } finally {
            deleteCrudAppTestTask(taskName);
        }

    }

    private boolean checkTaskExistsInTrello(String taskName) throws InterruptedException {
        final String TRELLO_URL = "https://trello.com/login";
        boolean result = false;
        WebDriver driverTrello = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        WebDriverWait wait = new WebDriverWait(driverTrello, Duration.ofSeconds(10));

        driverTrello.get(TRELLO_URL);

        WebElement emailInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[data-testid='username']")
                )
        );
        emailInput.sendKeys("marcin.code.github@gmail.com");
        WebElement continueButton = driverTrello.findElement(By.id("login-submit"));
        continueButton.click();

        WebElement passwordInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[data-testid='password']")
                )
        );

        String password = System.getenv("TRELLO_PASSWORD");

        if (password == null || password.isEmpty()) {
            throw new IllegalStateException("TRELLO_PASSWORD env variable is missing");
        }

        passwordInput.sendKeys(password);

        WebElement el = driverTrello.findElement(By.id("login-submit"));
        el.submit();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));

        driverTrello.findElement(By.id("password")).sendKeys("Qaz!2qaz");
        driverTrello.findElement(By.id("login-submit")).submit();


        try {
            WebElement dismissButton = new WebDriverWait(driverTrello, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.id("mfa-promote-dismiss")));

            dismissButton.click();

        } catch (TimeoutException e) {
            System.out.println("MFA popup not displayed");
        }

        WebElement board = new WebDriverWait(driverTrello, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[text()='Kodilla Application']/ancestor::a")
                ));

        board.click();


        result = new WebDriverWait(driverTrello, Duration.ofSeconds(15))
                .until(driver -> driver.findElements(By.cssSelector("a[data-testid='card-name']"))
                        .stream()
                        .anyMatch(elem -> elem.getText().trim().equals(taskName)));

        driverTrello.quit();

        return result;

    }

}
