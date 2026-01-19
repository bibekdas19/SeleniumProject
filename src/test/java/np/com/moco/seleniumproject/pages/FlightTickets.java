/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package np.com.moco.seleniumproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author sandesh
 */
public class FlightTickets {
    
    private final WebDriver driver;
    private final By flightTicketLoc= By.xpath("//a[contains(@href,'flighttickets')]");
    private final By mocoId = By.xpath("//*[@placeholder=\"MOCO ID\" and @id=\"searchBar\"]");
    
    
    public FlightTickets(WebDriver driver){
    this.driver = driver;
    }
    
    public void searchTickets(String mocoId){
        
    
    }
            
    
}
