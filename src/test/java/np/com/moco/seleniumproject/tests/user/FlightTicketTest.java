/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package np.com.moco.seleniumproject.tests.user;

import np.com.moco.seleniumproject.base.baseAuthenticatedTest;
import np.com.moco.seleniumproject.pages.FlightTickets;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author sandesh
 */
public class FlightTicketTest extends baseAuthenticatedTest{
    
    private FlightTickets flightTickets;
    
    @BeforeMethod
    public void setuptest(){
        flightTickets = new FlightTickets(driver);
    }

   
    @Test
    public void searchFlightWthAllValidCase(){
        flightTickets.searchTickets("9860933918", "SUCHIT BANIYA");
        String acutalErrorMessage = flightTickets.getErrorPopMessage();
        System.out.println(acutalErrorMessage);
        String expectedErrorMessage ="Invalid contact name data found.";
        Assert.assertEquals(acutalErrorMessage,expectedErrorMessage,"Error message mismatch! ");
    }
    
    
    @Test
    public void searchFlightwithInvaNumber(){
       flightTickets.searchTickets("982243", "SUCHIT BANIYA");
       String actualErrorMessge = flightTickets.getErrorPopMessage();
        System.out.println(actualErrorMessge);
        String expectedMessage = "Invalid Contact number";
        Assert.assertEquals(actualErrorMessge,expectedMessage,"Should match");
    }
}
