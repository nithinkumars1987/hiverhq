package com.Hiverhq.helpers;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class BrowserHelper {
	

	private WebDriver driver;
	private Logger Log = LoggerHelper.getLogger(BrowserHelper.class);

	public BrowserHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void goBack()   {
		driver.navigate().back();
		Log.info("Navigating back to : " +driver.getCurrentUrl());
	}

	public void goForward() {
		driver.navigate().forward();
		Log.info("Navigating forword to : " +driver.getCurrentUrl());
	}

	public void refresh() {
		driver.navigate().refresh();
		Log.info("Refreshing on same page title :" +driver.getTitle());
	}

	public Set<String> getWindowHandlens() {
		Set<String> handles = driver.getWindowHandles();
		Log.info("Windows handles size : " +handles.size());
		return handles;
	}

		
	
	public void SwitchToWindow(int index) {

		List<String> windows = new LinkedList<String>(getWindowHandlens());

		if (index < 0 || index > windows.size()){
			throw new IllegalArgumentException("Invalid Index : " + index);
		}
		driver.switchTo().window(windows.get(index));
		Log.info("Switching to window Id : "+index);
	}

	public void switchToParentWindow() {
		List<String> windows = new LinkedList<String>(getWindowHandlens());
		driver.switchTo().window(windows.get(0));
		Log.info("Switching to parent window " +driver.getTitle());
	}

	public void switchToParentWithChildClose() {
		List<String> windows = new LinkedList<String>(getWindowHandlens());

		for (int i = 1; i < windows.size(); i++) {
			Log.info(windows.get(i));
			driver.switchTo().window(windows.get(i));
			driver.close();
		}

		switchToParentWindow();
	}
		
	public void switchToFrame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
		Log.info("Switch to frame : "+driver.getTitle());
	}	


	public void clickTabAndEnter() {
			for(int i = 0; i <2; i++) {
				WaitHelper.sleep(8000);
				
				try {
					Robot r = new Robot();
					r.keyPress(KeyEvent.VK_TAB);
					r.keyPress(KeyEvent.VK_ENTER);
					r.keyRelease(KeyEvent.VK_ENTER);
					r.keyRelease(KeyEvent.VK_TAB);
				} catch (AWTException e) {
					e.printStackTrace();
				}
			}
	}
	
	public void clickEscape() {
		WaitHelper.sleep(5000);
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ESCAPE);
			r.keyRelease(KeyEvent.VK_ESCAPE);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public void pressF5Key() {
		WaitHelper.sleep(5000);
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_F5);
			r.keyRelease(KeyEvent.VK_F5);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public void openNewTab() {
		WaitHelper.sleep(5000);
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_T);
			r.keyRelease(KeyEvent.VK_T);
			r.keyRelease(KeyEvent.VK_CONTROL);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	public void openNewWindow() {
		WaitHelper.sleep(5000);
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_N);
			r.keyRelease(KeyEvent.VK_N);
			r.keyRelease(KeyEvent.VK_CONTROL);
			WaitHelper.sleep(5000);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
	}
	
	public void uploadStatement() {
		WaitHelper.sleep(5000);
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			}
		 catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public void uploadFileWithRobot (String imagePath) {
		WaitHelper.sleep(5000);
		Log.info("Waiting for 5 seconds to paste the copied File Path");
        StringSelection stringSelection = new StringSelection(imagePath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
 
        Robot robot = null;
 
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
 
        robot.delay(250);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(150);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }	
	

}
