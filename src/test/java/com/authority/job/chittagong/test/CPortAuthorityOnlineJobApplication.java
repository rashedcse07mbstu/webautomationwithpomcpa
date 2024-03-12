package com.authority.job.chittagong.test;

import com.authority.job.chittagong.util.GeneralUtil;
import com.thedeanda.lorem.LoremIpsum;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CPortAuthorityOnlineJobApplication extends BaseTest {
    public static WebDriver driver; //Instance Variable
    String fatherName = LoremIpsum.getInstance().getTitle(2);
    String motherName = LoremIpsum.getInstance().getTitle(2);

    @Test
    public void fillOnlineJobApplicationCompleteProcess() throws InterruptedException {
        //Open Browser
        WebDriverManager.firefoxdriver().clearDriverCache().setup();
        driver = new FirefoxDriver();

        // Open Application URL
        driver.get(url);
        driver.manage().window().maximize();

        //implicit wait
        GeneralUtil.implicitWait();

        List<WebElement> elementList = driver.findElements(By.cssSelector(" table#circularMst tbody tr"));
        System.out.println(elementList.size());

        if (elementList.size() > 0) {
            elementList.get(0).findElement(By.linkText("DETAILS")).click();
        }

        //Get handles of the windows
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();

        // Here we will check if child window has other child windows and will fetch the heading of the child window
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                //Click on Action link on Post Action Page
                driver.findElement(By.cssSelector("table#circularDtl tbody a i.bx-show")).click();
            }
        }

        GeneralUtil.waitForDomStable();
        driver.findElement(By.cssSelector("a.btn-success strong")).click();

        GeneralUtil.waitForDomStable();
        driver.findElement(By.cssSelector(" a.btn-secondary")).click();

        //Input National ID
        GeneralUtil.waitForDomStable();
        driver.findElement(By.cssSelector("input#national_id")).sendKeys("8231771135");

        //Upload NID File
        File uploadFile = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\NID_Rashedul.jpg");
        WebElement fileInput = driver.findElement(By.cssSelector("input#national_id_attachment"));
        fileInput.sendKeys(uploadFile.getAbsolutePath());

        //select date of birth
        driver.findElement(By.cssSelector("input#date_of_birth")).click();
        driver.findElement(By.cssSelector("div.top")).isDisplayed();
        driver.findElement(By.cssSelector("th.picker-switch[title='Select Month']")).click();
        driver.findElement(By.cssSelector("th.picker-switch[title='Select Year']")).click();
        GeneralUtil.waitForDomStable();
        driver.findElement(By.cssSelector("span.bx-chevron-left[title='Previous Decade']")).click();
        GeneralUtil.waitForDomStable();
        driver.findElement(By.cssSelector("span.bx-chevron-left[title='Previous Decade']")).click();
        GeneralUtil.waitForDomStable();
        driver.findElement(By.cssSelector("span.bx-chevron-left[title='Previous Decade']")).click();
        GeneralUtil.waitForDomStable();

        driver.findElements(By.cssSelector("span[data-action='selectYear'].year")).stream()
                .filter(element -> element.getText().trim().contains("1995"))
                .findFirst().get().click();

        GeneralUtil.waitForDomStable();
        driver.findElements(By.cssSelector("span[data-action='selectMonth'].month")).stream()
                .filter(element -> element.getText().trim().contains("Aug"))
                .findFirst().get().click();

        GeneralUtil.waitForDomStable();
        driver.findElements(By.cssSelector("td[data-action='selectDay'][class='day']")).stream()
                .filter(element -> element.getText().trim().contains("23"))
                .findFirst().get().click();
        GeneralUtil.waitForDomStable();

        //Verify NID
        GeneralUtil.waitForDomStable();
        driver.findElement(By.cssSelector("button#nid_verification")).click();
        GeneralUtil.waitForDomStable();

        //Input Father Name
        driver.findElement(By.cssSelector("input#father_name")).sendKeys(fatherName);
        GeneralUtil.waitForDomStable();

        //Input Mother Name
        driver.findElement(By.cssSelector("input#mother_name")).sendKeys(motherName);

        //Input Mobile No
        GeneralUtil.waitForDomStable();
        driver.findElement(By.cssSelector("input#mobile")).sendKeys("01729415699");

        //verify OTP Password
        GeneralUtil.waitForDomStable();
        Alert alert = driver.switchTo().alert();
        String alertPassword = alert.getText();
        String password = alertPassword.replaceAll("[^0-9]", "");
        alert.accept();

        //Enter OTP
        GeneralUtil.waitForDomStable();
        driver.findElement(By.cssSelector("input#otp")).sendKeys(password);
        GeneralUtil.waitForDomStable();
        alert.accept();

        // Input E-mail Address
        driver.findElement(By.cssSelector(" input#email")).sendKeys("rasel.cse07@gmail.com");
        GeneralUtil.waitForDomStable();

        //Select Religion
        Select selectReligion = new Select(driver.findElement(By.cssSelector("select#religion")));
        selectReligion.selectByIndex(1);
        GeneralUtil.waitForDomStable();

        //Scroll Down
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)", "");

        //Attach Photo
        GeneralUtil.waitForDomStable();

        File photo = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Photo_Rashed.jpg");
        WebElement photoAttachment = driver.findElement(By.cssSelector("input#photo"));
        photoAttachment.sendKeys(photo.getAbsolutePath());
        GeneralUtil.waitForDomStable();

        //Attach Signature
        File signature = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Signature_Rashed.jpg");
        WebElement signatureAttachment = driver.findElement(By.cssSelector("input#signature"));
        signatureAttachment.sendKeys(signature.getAbsolutePath());
        GeneralUtil.waitForDomStable();

        //Scroll Down
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("window.scrollBy(0,200)", "");

        //Click on Next button
        GeneralUtil.waitForDomStable();
        driver.findElement(By.cssSelector("button.nextBtn[value='first']")).click();
        GeneralUtil.waitForDomStable();

        //Address Section
        //Select DIVISION
        Select selectDivision = new Select(driver.findElement(By.cssSelector("select#permanent_division")));
        selectDivision.selectByIndex(1);
        GeneralUtil.waitForDomStable();

        //Select District
        Select selectDistrict = new Select(driver.findElement(By.cssSelector("select#permanent_district")));
        selectDistrict.selectByIndex(1);
        GeneralUtil.waitForDomStable();

        //Select Thana
        driver.findElement(By.cssSelector("span#select2-permanent_thana-container")).click();
        GeneralUtil.waitForDomStable();

        driver.findElements(By.cssSelector("ul.select2-results__options > li[id^='select2-permanent_thana-result']")).get(1).click();
        GeneralUtil.waitForDomStable();

        // Input Post Office
        driver.findElement(By.cssSelector("input#permanent_post_office_name")).sendKeys("Dhaka");
        GeneralUtil.waitForDomStable();

        // Input Post Code
        driver.findElement(By.cssSelector("input#permanent_post_code")).sendKeys("1250");
        GeneralUtil.waitForDomStable();

        // Input Permanent Address
        driver.findElement(By.cssSelector(" input#permanent_address")).sendKeys("Banasree R 9");
        GeneralUtil.waitForDomStable();

        //Scroll Down
        JavascriptExecutor js3 = (JavascriptExecutor) driver;
        js3.executeScript("window.scrollBy(0,250)", "");

        // Check same_as_permanent_address checkbox
        driver.findElement(By.cssSelector("div fieldset.form-group div.custom-checkbox")).click();
        GeneralUtil.waitForDomStable();

        //Click on Next button
        driver.findElement(By.cssSelector("div#enable_after_district_verification button.nextBtn[type='button']")).click();
        GeneralUtil.waitForDomStable();

        //Education Section
        //Select Exam
        Select selectExam = new Select(driver.findElement(By.cssSelector("select#education_0_exam")));
        selectExam.selectByIndex(1);
        GeneralUtil.waitForDomStable();

        //Select Subject
        driver.findElement(By.cssSelector("span#select2-education_0_subject-container")).click();
        GeneralUtil.waitForDomStable();
        driver.findElement(By.cssSelector("ul.select2-results__options > li[id^='select2-education_']")).click();

        //Select Board
        driver.findElement(By.cssSelector("span#select2-education_0_exam_body-container")).click();
        GeneralUtil.waitForDomStable();
        driver.findElement(By.cssSelector("ul.select2-results__options > li[id^='select2-education_']")).click();
        GeneralUtil.waitForDomStable();

        //Select Passing year
        driver.findElement(By.cssSelector("input#education_0_passing_year")).sendKeys("2024");
        GeneralUtil.waitForDomStable();

        //Select Result with CGPA
        driver.findElement(By.cssSelector("span#select2-education_0_result_type-container")).click();
        GeneralUtil.waitForDomStable();

        driver.findElement(By.cssSelector("ul.select2-results__options > li[id^='select2-education_']")).click();
        GeneralUtil.waitForDomStable();
        driver.findElement(By.cssSelector("input#education_0_result")).sendKeys("5");

        //Select SSC Exam
        Select selectSscExam = new Select(driver.findElement(By.cssSelector("select#education_1_exam")));
        selectSscExam.selectByIndex(1);
        GeneralUtil.waitForDomStable();

        //Select Subject
        driver.findElement(By.cssSelector("span#select2-education_1_subject-container")).click();
        GeneralUtil.waitForDomStable();
        driver.findElement(By.cssSelector("ul.select2-results__options > li[id^='select2-education_']")).click();
        GeneralUtil.waitForDomStable();

        //Select Board
        driver.findElement(By.cssSelector("span#select2-education_1_exam_body-container")).click();
        GeneralUtil.waitForDomStable();
        driver.findElement(By.cssSelector("ul.select2-results__options > li[id^='select2-education_1_exam_body-result']")).click();
        GeneralUtil.waitForDomStable();

        //Select SSC passing year
        driver.findElement(By.cssSelector("input#education_1_passing_year")).sendKeys("2024");
        GeneralUtil.waitForDomStable();

        //Select SSC Result with CGPA
        driver.findElement(By.cssSelector("span#select2-education_1_result_type-container")).click();
        GeneralUtil.waitForDomStable();

        driver.findElement(By.cssSelector("ul.select2-results__options > li[id^='select2-education_1']")).click();
        GeneralUtil.waitForDomStable();
        driver.findElement(By.cssSelector("input#education_1_result")).sendKeys("5");

        //Click on Next button
        driver.findElement(By.cssSelector("div#nextButtonContainer button.nextBtn[type='button']")).click();
        GeneralUtil.waitForDomStable();

        //Job Experience Section
        //Input Organization
        driver.findElement(By.cssSelector("input#jobexperience_0_organization")).sendKeys("BRAC IT");
        GeneralUtil.waitForDomStable();

    }

}

