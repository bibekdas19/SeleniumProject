package np.com.moco.seleniumproject.utils;

import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class screenshots {
    private static final String SCREENSHOT_DIR = "target/screenshots";
    public static void takeScreenshot(WebDriver driver, String fileName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dir = new File(SCREENSHOT_DIR);
        if (!dir.exists()) {
            dir.mkdirs(); // create folder
        }
        
        File dest = new File("screenshots/" + fileName + ".png");
        try {
            FileUtils.copyFile(src, dest);
            System.out.println("Screenshot saved: " + dest.getAbsolutePath());
        } catch (IOException e) {
        }
        
    
    }
}
