import org.openqa.selenium.By;

public class Selector {
    public static By cookie = By.cssSelector("a[aria-label=\"dismiss cookie message\"]");
    public static By username = By.cssSelector("input[data-placeholder=\"Username\"]");
    public static By password = By.cssSelector("input[data-placeholder=\"Password\"]");
    public static By loginbutton = By.cssSelector("button[aria-label=\"LOGIN\"]");
    public static By studentmenu =By.cssSelector(".group-items > :nth-child(4)");
    public static By studentsecond =By.cssSelector(".group-items > :nth-child(4)>div>:nth-child(1) >a>span");
    public static By firstplus =By.cssSelector("ms-table-toolbar ms-add-button");

    public static By firstname = By.cssSelector("ms-text-field[formcontrolname='firstName']>input");
    public static By lastname = By.cssSelector("ms-text-field[formcontrolname=\"lastName\"]>input");
    public static By genderselect =By.cssSelector("[formcontrolname=\"gender\"]");
    public static By gender1 =By.cssSelector("[role=\"option\"]:nth-child(1)");
    public static By gender2 =By.cssSelector("[role=\"option\"]:nth-child(2)");
    public static By gradeselect =By.xpath("//mat-select//span[text()='Grade Level']");
    public static By grade1 =By.cssSelector("mat-option:nth-child(2)");
    public static By grade2 =By.cssSelector("mat-option:nth-child(10)");
    public static By schooldepselect =By.cssSelector("[formgroupname=\"department\"]");
    public static By schooldep1 =By.cssSelector("mat-option:nth-child(1)");
    public static By sectionselect =By.cssSelector("[formcontrolname=\"section\"] span");
    public static By section1 =By.cssSelector("mat-option:nth-child(1)");
    public static By section2 =By.cssSelector("mat-option:nth-child(2)");
    public static By citizenshipselect =By.cssSelector("[formgroupname=\"citizenship\"] mat-select span");
    public static By citizen1 =By.cssSelector("mat-option:nth-child(100)");
    public static By doctype =By.cssSelector("[formgroupname=\"documentInfo\"]>mat-form-field:nth-child(1)");
    public static By docelement1 =By.cssSelector("mat-option:nth-child(1)");
    public static By docnumber =By.cssSelector("input[formcontrolname=\"documentNumber\"]");
    public static By save =By.cssSelector("ms-save-button > button");
    public static By backarrow =By.cssSelector("toolbar [data-icon=\"arrow-left\"]");

    public static By repmainmenu =By.xpath("//*[text()='Representatives']");
    public static By representativeselect =By.xpath("//mat-select//span[text()='Representative']");
    public static By mother =By.cssSelector("mat-option:nth-child(1)");
    public static By father =By.cssSelector("mat-option:nth-child(2)");
    public static By guardian =By.cssSelector("mat-option:nth-child(3)");
    public static By self =By.cssSelector("mat-option:nth-child(4)");

    public static By reprfirstname =By.cssSelector("[data-placeholder=\"First Name\"]");
    public static By reprlastname =By.cssSelector("[data-placeholder=\"Last Name\"]");
    public static By repmobile =By.cssSelector("mat-dialog-content [data-placeholder=\"Mobile Phone\"] ");
    public static By repcountryselect =By.cssSelector("[formgroupname=\"country\"]  .mat-select-value>span");
    public static By repcountry1 =By.cssSelector("mat-option:nth-child(2)");
    public static By repadd =By.xpath("//span[text()=' Add ']");
    public static By confirmYes = By.cssSelector("button[type='submit']");
    public static By repcontain =By.cssSelector("[role=\"rowgroup\"] td.mat-column-fullName");
    public static By repclose =By.xpath("//*[text()=' Close ']");
    public static By reptrash=By.cssSelector("tbody ms-delete-button>button");

    public static By newaccouninanouther =By.xpath("//span[text()='New']");
    public static By newrep =By.cssSelector("[tooltip=\"GENERAL.BUTTON.ADD_REPRESENTATIVE\"]");
    public static By scrollby =By.cssSelector(".mat-paginator-container mat-form-field");
    public static By popup =By.id("toast-container");
    public static By editbutton = By.cssSelector("[data-icon=\"edit\"]");

}

