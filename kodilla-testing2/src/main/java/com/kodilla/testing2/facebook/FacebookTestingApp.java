package com.kodilla.testing2.facebook;

import com.kodilla.testing2.config.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FacebookTestingApp {

    public static void main(String[] args) {

        WebDriver driver =
                WebDriverConfig.getDriver(WebDriverConfig.CHROME);

        WebDriverWait wait =
                new WebDriverWait(driver, 15);

        try {
            driver.get("https://www.facebook.com/");

            // Akceptujemy cookies
            try {

                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@role='dialog']")
                ));

                List<WebElement> cookies = driver.findElements(
                        By.xpath("//div[@role='button' and contains(@aria-label,'cookie')]")
                );

                if (cookies.size() >= 5) {
                    cookies.get(4).click();
                }

            } catch (Exception e) {
                // jeśli nie ma cookies - ignorujemy
            }


            // Klikamy "Create new account"
            WebElement createAccount = driver.findElement(
                    By.xpath("//a[@aria-label='Create new account']")
            );
            createAccount.click();

            try{
                // U mnie ponownie pojawiają się cookies - klikamy accept all cookies
                wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[@role='dialog']")
                ));

                List<WebElement> buttons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//div[@role='button' and contains(@aria-label,'cookie')]")
                ));

                if (!buttons.isEmpty()) {
                    buttons.get(4).click();
                }
            } catch (Exception e) {
                // jeśli nie ma cookies - ignorujemy
            }

            // Lista z inputami do Name, Surname i Email
            List<WebElement> inputs = driver.findElements(
                    By.xpath("//input[@type='text']")
            );

            WebElement name = inputs.get(0);
            WebElement surname = inputs.get(1);
            WebElement email = inputs.get(2);

            name.sendKeys("Jan");
            surname.sendKeys("Kowalski");
            email.sendKeys("jan-kowalski-abc-123@gmail.com");


            // 3. Select - day
            WebElement dayDropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//span[text()='Day']/ancestor::div[1]")
                    )
            );
            dayDropdown.click();

            WebElement option4 = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[@role='option']//div[text()='4']")
                    )
            );

            option4.click();

            // 4. Select - month
            WebElement monthDropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//span[text()='Month']/ancestor::div[1]")
                    )
            );

            monthDropdown.click();

            WebElement may = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[@role='option']//div[text()='May']")
                    )
            );

            may.click();

            // 5. Select - year
            WebElement yearDropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//span[text()='Year']/ancestor::div[1]")
                    )
            );
            yearDropdown.click();

            WebElement year2000 = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[@role='option']//div[text()='2000']")
                    )
            );

            year2000.click();


            // Wpisujemy password
            WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
            password.sendKeys("MojeHaslo123!");

            //Wybieramy Gender
            WebElement genderDropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//label[contains(., 'Gender')]")
                    )
            );
            genderDropdown.click();

            WebElement male = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[@role='option']//div[text()='Male']")
                    )
            );

            male.click();

        } finally {
            driver.quit();
        }
    }
}
