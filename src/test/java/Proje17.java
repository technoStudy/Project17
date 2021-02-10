import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExcelReader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class Proje17 {
//    Setup for your test cases: (Use @BeforeClass method for setup)
//    Open a browser and navigate to https://test.campus.techno.study/
//    Dismiss the cookie message by clicking on "Got it!" button
//    Login by the credentials (username = "daulet2030@gmail.com" and password = "TechnoStudy123@")

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Robot robot = new Robot();
    private By gradeLevel;

    public Proje17() throws AWTException {
    }

    @Test
    public void login() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://test.campus.techno.study/");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 7);

        driver.findElement(Selector.cookie).click();
        driver.findElement(Selector.username).sendKeys("daulet2030@gmail.com");
        driver.findElement(Selector.password).sendKeys("TechnoStudy123@");
        driver.findElement(Selector.loginbutton).click();

    }

    // Navigate to Student > Students through the left menu
    @Test(dependsOnMethods = "login")
    public void navigate() {
        waitFor(ExpectedConditions.visibilityOfElementLocated(Selector.studentmenu), "Studentmenu not visible");
        driver.findElement(Selector.studentmenu).click();

        waitFor(ExpectedConditions.visibilityOfElementLocated(Selector.studentsecond), "Student menu not visible");
        driver.findElement(Selector.studentsecond).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Selector.firstplus));
        driver.findElement(Selector.firstplus).click();

    }


    @Test(dependsOnMethods = {"navigate", "login"}, dataProvider = "studentsExcel")
    public void fillInStudentData(Map<String, String> map) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(Selector.firstname));
        driver.findElement(Selector.firstname).clear();
        driver.findElement(Selector.firstname).sendKeys(map.get("first_name"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(Selector.lastname));
        driver.findElement(Selector.lastname).clear();
        driver.findElement(Selector.lastname).sendKeys(map.get("last_name"));

        driver.findElement(Selector.genderselect).click();
        By genderLocator = map.get("gender").equals("Male") ? Selector.gender1 : Selector.gender2;
        wait.until(ExpectedConditions.visibilityOfElementLocated(genderLocator));
        driver.findElement(genderLocator).click();

        driver.findElement(Selector.gradeselect).click();
        gradeLevel = map.get("grade_level").equals("1") ? Selector.grade1 : Selector.grade2;
        wait.until(ExpectedConditions.visibilityOfElementLocated(gradeLevel));
        driver.findElement(gradeLevel).click();

        driver.findElement(Selector.schooldepselect).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Selector.schooldep1));
        driver.findElement(Selector.schooldep1).click();

        driver.findElement(Selector.sectionselect).click();
        By sectionLocator = map.get("grade_level").equals("1") ? Selector.section1 : Selector.section2;
        wait.until(ExpectedConditions.visibilityOfElementLocated(sectionLocator));
        driver.findElement(sectionLocator).click();

        driver.findElement(Selector.citizenshipselect).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Selector.citizen1));
        driver.findElement(Selector.citizen1).click();
        js = (JavascriptExecutor) driver;
        WebElement form = driver.findElement(By.cssSelector("[formgroupname=\"documentInfo\"]>mat-form-field:nth-child(1)"));
        js.executeScript("arguments[0].scrollIntoView();", form);

        wait.until(ExpectedConditions.visibilityOfElementLocated(Selector.doctype));
        driver.findElement(Selector.doctype).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Selector.docelement1));
        driver.findElement(Selector.docelement1).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(Selector.docnumber));
        driver.findElement(Selector.docnumber).sendKeys("9009");
    }

    @DataProvider(name = "studentsExcel")
    public Object[][] studentsExcel() throws IOException {
        ExcelReader reader = new ExcelReader("src/test/resources/students.xlsx");
        List<Map<String, String>> listOfMaps = reader.getListOfMaps();
        Object[][] data = new Object[listOfMaps.size()][1];
        int index = 0;
        for (Map<String, String> map : listOfMaps) {
            data[index++] = new Object[]{map};
        }
        return data;
    }

/*
   Click on + icon for adding a student
   Select "Representatives" from the bottom tabs
   Click on + icon to add a new representative

   Verify that you can add a new representative of Father, Mother, Guardian and Self
   Write only one test case for verification of creation of representatives
   Use a single Data Provider in order to use the same test case for creation of each type of representative.
 */

    @DataProvider(name = "DataProvider")
    public Object[][] dataProvider1() {
        Object[][] testData = {
                {"Tarana", "Mammadova", "mat-option:nth-child(1)", "6145689531"},
                {"Somename", "Somesurname", "mat-option:nth-child(2)", "8945786543"},
                {"name", "surname", "mat-option:nth-child(3)", "8945786543"},
                {"Turkana", "Mammadova", "mat-option:nth-child(4)", "8945786543"},
        };
        return testData;
    }


    @Test(dependsOnMethods = "fillInStudentData", dataProvider = "DataProvider")
    public void test1_creating(String Repname, String Repsurname, String Representative, String Repmobil) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Selector.repmainmenu));
        driver.findElement(Selector.repmainmenu).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(Selector.firstplus));
        driver.findElement(Selector.firstplus).click();

        waitFor(ExpectedConditions.visibilityOfElementLocated(Selector.representativeselect), "Representative is not visible");
        driver.findElement(Selector.reprfirstname).clear();
        driver.findElement(Selector.reprfirstname).sendKeys(Repname);
        driver.findElement(Selector.reprlastname).clear();
        driver.findElement(Selector.reprlastname).sendKeys(Repsurname);

        driver.findElement(Selector.representativeselect).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Selector.mother));
        driver.findElement(By.cssSelector(Representative)).click();


        driver.findElement(Selector.repmobile).sendKeys(Repmobil);

        waitFor(ExpectedConditions.visibilityOfElementLocated(Selector.repcountryselect), "Countury is not visible");
        driver.findElement(Selector.repcountryselect).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(Selector.repcountry1));
        driver.findElement(Selector.repcountry1).click();
        wait.until(ExpectedConditions.elementToBeClickable(Selector.repadd));
        driver.findElement(Selector.repadd).click();

        js = (JavascriptExecutor) driver;
        WebElement form2 = driver.findElement(By.cssSelector(".mat-paginator-container mat-form-field"));
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        js.executeScript("arguments[0].scrollIntoView();", form2);


        List<WebElement> rows = driver.findElements(Selector.repcontain);
        for (WebElement row : rows) {
            if (!(row.getText().contains(Repname) && row.getText().contains(Repsurname))) {
                continue;
            }
            Assert.assertTrue(row.getText().contains(Repname));
        }

    }

    /*
      Verify you can delete an existing representative.
      (Optional: Use only one test case to delete the four representative you have created about.
        You may use data provider or run your test many times.)
      (For automated testing/verification, deleting one representative will be enough but
       please do not forget to delete other representative you have created above, at least manually)
    */
    @Test(dependsOnMethods = "test1_creating", dataProvider = "DataProvider")
    public void test2_deleting(String Repname, String Repsurname, String Representative, String Repmobil) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(Selector.repcontain));
        List<WebElement> rows = driver.findElements(Selector.repcontain);
        boolean res = false;
        for (WebElement row : rows) {
            if (row.getText().contains(Repname) && row.getText().contains(Repsurname)) {
                System.out.println(row.getText());
                res = true;
                driver.findElement(Selector.reptrash).click();
                wait.until(ExpectedConditions.elementToBeClickable(Selector.confirmYes));
                driver.findElement(Selector.confirmYes).click();
            }
            Assert.assertTrue(res);

        }

    }
/*
   Verify that a representative cannot be created (or add button will not be active)
   if one of the following data is missing: Representative, First Name, Last Name, Mobile Phone or Country
   Write a single test case and use a single data provider to test each case with a missing information each time
*/

    @DataProvider(name = "MissingDataProvider")
    public Object[][] dataProvider2() {
        Object[][] testData = {
                {"", "Mammadova", "mat-option:nth-child(1)", "6145689531", "mat-option:nth-child(2)"},
                {"Somename", "", "mat-option:nth-child(2)", "8945786543", "mat-option:nth-child(24)"},
                {"name", "surname", "", "8945786543", "mat-option:nth-child(18)"},
                {"Turkana", "Mammadova", "mat-option:nth-child(4)", "8945786543", ""},
        };
        return testData;
    }

    @Test(dependsOnMethods = "test2_deleting", dataProvider = "MissingDataProvider")
    public void test3_missingdata(String Repname, String Repsurname, String Representative, String Repmobil, String Repcountry) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(Selector.firstplus));
        driver.findElement(Selector.firstplus).click();
        waitFor(ExpectedConditions.visibilityOfElementLocated(Selector.representativeselect), "Representative is not visible");
        driver.findElement(Selector.reprfirstname).sendKeys(Repname);
        driver.findElement(Selector.reprlastname).sendKeys(Repsurname);

        driver.findElement(Selector.representativeselect).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Selector.mother));
        try {
            driver.findElement(By.cssSelector(Representative)).click();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            robot.keyPress(KeyEvent.VK_TAB);
        }

        driver.findElement(Selector.repcountryselect).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(Selector.repcountry1));
        try {
            driver.findElement(By.cssSelector(Repcountry)).click();

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            robot.keyPress(KeyEvent.VK_TAB);
        }

        driver.findElement(Selector.repmobile).sendKeys(Repmobil);
        waitFor(ExpectedConditions.elementToBeClickable(Selector.repadd), "Representative can't be add without any required parameter");

        wait.until(ExpectedConditions.visibilityOfElementLocated(Selector.repclose));
        driver.findElement(Selector.repclose).click();

        List<WebElement> rows = driver.findElements(Selector.repcontain);
        if (rows.size() > 0) {
            Assert.fail();
        } else {
            Assert.assertEquals(0, rows.size());
        }

    }

/*
   (The following tests must fail. Because you are not supposed to create a representative as stated but in fact you can!)
   Verify that you cannot create a representative (add button should not be active) with the following data:
      with a First Name like "*#@%/"
      with a Last Name like "125436"
      with a Mobile Phone like "abcdef"
   Write a single test case for this test and pass parameters from TestNG.xml file to test the three cases.
*/

    @Parameters({"Repname", "Repsurname", "Repmobil"})
    @Test(dependsOnMethods = "test3_missingdata")
    public void test4_creatingWithRandomChar(String Repname, String Repsurname, String Repmobil) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Selector.firstplus));
        driver.findElement(Selector.firstplus).click();

        waitFor(ExpectedConditions.visibilityOfElementLocated(Selector.representativeselect), "Representative is not visible");
        driver.findElement(Selector.reprfirstname).clear();
        driver.findElement(Selector.reprfirstname).sendKeys(Repname);
        driver.findElement(Selector.reprlastname).clear();
        driver.findElement(Selector.reprlastname).sendKeys(Repsurname);

        driver.findElement(Selector.representativeselect).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Selector.mother));
        driver.findElement(Selector.father).click();

        driver.findElement(Selector.repmobile).sendKeys(Repmobil);
        waitFor(ExpectedConditions.visibilityOfElementLocated(Selector.repcountryselect), "Countury is not visible");
        driver.findElement(Selector.repcountryselect).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(Selector.repcountry1));
        driver.findElement(Selector.repcountry1).click();
        wait.until(ExpectedConditions.elementToBeClickable(Selector.repadd));
        driver.findElement(Selector.repadd).click();

        Thread.sleep(1000);
        js = (JavascriptExecutor) driver;
        WebElement form2 = driver.findElement(By.cssSelector(".mat-paginator-container mat-form-field"));
        js.executeScript("arguments[0].scrollIntoView();", form2);

        List<WebElement> rows = driver.findElements(Selector.repcontain);
        for (WebElement row : rows) {
            if (!(row.getText().contains(Repname) && row.getText().contains(Repsurname))) {
                continue;
            }
            Assert.assertFalse(row.getText().contains(Repname));
        }

    }

    /*
        Create a folder called "screenshot" (suggestively in the project package, not somewhere else in your computer.)
        Create an @AfterMethod to take a screenshot when the test fails.
        Save the screenshots in the folder called "screenshots"
        Create @AfterClass to close the browser
    */
    @AfterMethod
    public void takeScreenshotWhenTastFails(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-nn");
            String time = LocalDateTime.now().format(formatter);

            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String folderPath = "C:\\Users\\ememm\\Intell\\Testng_B3\\test-output\\Screenshouts";
            String fileName = result.getMethod().getMethodName() + "_" + time + ".png";

            File destinationFile = new File(folderPath + fileName);

            try {
                FileUtils.copyFile(sourceFile, destinationFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @AfterClass
    public void closewindows() {
        driver.quit();
    }



/*
    Add a listener to your xml file for getting well-prepared, good-looking and e-mailable test reports (like html reports)

    OPTIONAL: Run your project in parallel (say, running three tests at a time)
      It is strongly suggested that before trying parallel execution, make sure they run perfectly one after other.
    CHALLENGE: Without writing any more test cases (you may have to change your code a little bit),
     run your test in two other browsers (say, Firefox and Edge, or else)
     Before trying challenge part, make sure your test cases work perfectly with one browser.
*/


    private <T> void waitFor(ExpectedCondition<T> condition) {
        waitFor(condition, condition.toString());
    }

    private <T> void waitFor(ExpectedCondition<T> condition, String errorMessage) {
        try {
            wait.until(condition);
        } catch (TimeoutException e) {
            Assert.fail(errorMessage);
        }
    }

}

