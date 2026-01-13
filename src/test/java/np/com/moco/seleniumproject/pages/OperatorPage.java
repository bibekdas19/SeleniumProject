package np.com.moco.seleniumproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;



public class OperatorPage {
    private final WebDriver driver;
    private final By addbutton = By.xpath("//*[@id=\"app\"]/div/div[2]/div[2]/div/div[1]/div/div/div[2]/a/svg/path");
    private final By fullName = By.xpath("//input[@placeholder=\"Full Name\"]");
    private final By emailAddress = By.xpath("//input[@placeholder=\"Email Address\"]");
    private final By contactNumber = By.xpath("//input[@placeholder=\"Contact Number\"]");
    private final By organi = By.xpath("//select[@class=\"form-select\"]");
    
    public OperatorPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void AddOperator(String name, String email,String contact){
        driver.findElement(addbutton).click();
        driver.findElement(fullName).sendKeys(name);
        driver.findElement(emailAddress).sendKeys(email);
        driver.findElement(contactNumber).sendKeys(contact);
        WebElement ddown = driver.findElement(organi);
        Select select = new Select(ddown);
        select.selectByValue("MOCO");
        // checklist as default user. clicking all the checkboxes
        driver.findElement(By.xpath("//input[@class=\"form-check-input\"and @type=\"checkbox\"]")).click();
        
        //clicking the add button
        driver.findElement(By.xpath("//button[@class=\"btn\"]")).click();
        
    }
    
   public void viewOperator(String email) {
    driver.findElement(By.xpath("//a[@href='#/operator/view/" + email + "']")).click();
}
    
    public void disableOperator(String email){

    }
    
    public void ResetPassword(String email){
        
    }
    
    
    
    
    
    
}
