package np.com.moco.seleniumproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class OperatorPage {
    private final WebDriver driver;
    private final By addbutton = By.xpath("//*[@id=\"app\"]/div/div[2]/div[2]/div/div[1]/div/div/div[2]/a/svg/path");
    private final By fullName = By.xpath("//input[@placeholder=\"Full Name\"]");
    private final By emailAddress = By.xpath("//input[@placeholder=\"Email Address\"]");
    private final By contactNumber = By.xpath("//input[@placeholder=\"Contact Number\"]");
    private final By organization = By.xpath("//select[@class=\"form-select\"]");
    
    public OperatorPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void AddOperator(String name, String email,String contact,String contract,String organization){
        
    }
    
    public void ViewOperator(String email){
        
    }
    
    public void disableOperator(String email){
        
    }
    
    public void ResetPassword(String email){
        
    }
    
    
    
    
    
    
}
