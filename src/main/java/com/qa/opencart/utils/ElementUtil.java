package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {
	
	private WebDriver driver;  // Maintain own webdriver & default value of driver is null/driver refernce is pointing to null.// class driver
	private JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver){  // create the constructor  // local driver
		this.driver = driver;  // passing local driver to class driver
		jsUtil = new JavaScriptUtil(driver);
	}
	
	 public static By getBy(String locatorType,String locatorValue) {
		   By locator = null;
		    switch (locatorType.toLowerCase()) {
		    case "id":
			locator = By.id(locatorValue);
			break;
			
		    case "linkText":
			locator = By.linkText(locatorValue);
			break;
			
		    case "name":
		    locator = By.name(locatorValue);
		    break;
				
		    case "class":
			locator = By.className(locatorValue);
			break;
			
		    case "cssSelector":
			locator = By.cssSelector(locatorValue);
			break;

		    default:
		    System.out.println("please pass the right locator type and value ...");
			break;
		} 
		   return locator;
		 }
	   
	   public WebElement getElement(By locator) {
		   WebElement element = driver.findElement(locator);
		   if(Boolean.parseBoolean(DriverFactory.highlight));{
		   jsUtil.flash(element);
	   }
		   return element;
	   }
	   public WebElement getElement(By locator,int timeOut) {
	        return doPresenceOfElementLocated(locator, timeOut);
	   }
	   
	   
	   public WebElement getElement(String locatorType, String locatorValue) {
		   return driver.findElement(getBy(locatorType, locatorValue));
	   }
	   
	   public void doClear(By locator) {
		   getElement(locator).clear();
	   }
	   public void doSendKeys(By locator,String value) {
		   doClear(locator);
		   getElement(locator).sendKeys(value);; 
	   }

	   public void doSendKeys(String locatorType, String locatorValue,String value) {
		   getElement(locatorType,locatorValue).sendKeys(value);
	   }
	   
	   public void doSendkeys(By locator,String value,int timeOut) {
			doPresenceOfElementLocated(locator, timeOut).sendKeys("Pradeep");
		}
	   
	   public void doClick(By locator) {
			getElement(locator).click();
	   }
	   
	   public void doClick(String locatorType, String locatorValue) {
		   getElement(locatorType,locatorValue).click();
	   }
	   
	   public void doClick(By locator, int timeOut) {
		   doPresenceOfElementLocated(locator, timeOut).click();
	   }
	   
	   public  String doGetText(By locator) {
			return getElement(locator).getText();
		}
	   
	   public String getAttributeValue(By locator,String attrName) {
			String attrVal = getElement(locator).getAttribute(attrName);
			System.out.println(attrVal);
			return attrVal;
		}
	   
	   public  boolean doIsDisplayed(By locator) {
			return getElement(locator).isDisplayed();
		}
	   
	   public boolean isElementExist(By locator) {
		   int elementCount = getElementsCount(locator);
		   System.out.println("total elements found: " + elementCount);
		   if(elementCount >= 1) {
			   System.out.println("element is found..." + locator);
			   return true;
		   }else {
			   System.out.println("element is not found..." + locator);
			   return false;
		   }
	   }
	   
	   public List<String> getElementsTextList(By locator) {
	    	
	    	List<WebElement> eleList  = getElements(locator);
	    	List<String> eleTextList = new ArrayList<String>();
	    	for(WebElement e : eleList) {
	    		String eleText =e.getText();
	    		if(!eleText.isEmpty()) {
	    			eleTextList.add(eleText);
	    		}
	    	}
	    	return eleTextList;
	    }
	    
	    public int getElementsCount(By locator) {
	    	return getElements(locator).size();
	    }
		
	    public List<WebElement> getElements(By locator) {
	    	return driver.findElements(locator);
	    }
	    
	    /***** Drop Down Utils  ********************/
	    
	    
		public void doDropDownSelectByIndex(By locator,int index) {
			Select select = new Select(getElement(locator)); 
			select.selectByIndex(index);
		}
		
		public void doDropDownSelectByVisibleText(By locator,String text) {
			Select select = new Select(getElement(locator)); 
			select.selectByVisibleText(text);
		}
		
		public void doDropDownSelectByValueText(By locator,String value) {
			Select select = new Select(getElement(locator)); 
			select.selectByValue(value);
		}
		
		public  void doSelectDropDownValue(By locator,String value) {
			Select select = new Select(getElement(locator));
			List<WebElement> countryList = select.getOptions();
			
			for(WebElement e : countryList) {
				String text =e.getText();
				System.out.println(text);
				if(text.equals(value)) {
					e.click();
					break;
				}
			}
			
		}
		
		public void selectDropDownValueWithoutSelect(By locator, String value) {
			List<WebElement> countryList =getElements(locator);
		    System.out.println(countryList.size());
		    
		    for(WebElement e : countryList) {
		    	String text = e.getText();
		    	if(text.equals(value)) {
		    		e.click();
		    		break;
		    	}
		    }
		}
		
		
		/**************************** Links Utils *************************************/
		
		public List<String> getLinksTextList(By locator) {
			List<WebElement> linksList = getElements(locator);
			List<String> linksTextList = new ArrayList<String>();
			System.out.println(linksList.size());
			
			for(WebElement e :linksList) {
				String text = e.getText().trim();
				linksTextList.add(text);
			}
		
			return linksTextList;
		}
		public void clickOnElementFromSection(By locator,String linkText) {
			List<WebElement> langList = getElements(locator);
			System.out.println(langList.size());
			
			for(WebElement e :langList) {
				String text = e.getText().trim();
				System.out.println(text);
				if(text.equals(linkText)) {
					e.click();
					break;
				}
			}
		}
		
		
		/***********************WebTable Util ***************************/

		public void printTable(By rowLocator,By colLocator,String breforXpath,String afterXpath) {
			
			 int rowCount = getElements(rowLocator).size();
			 int colCount = getElements(colLocator).size();
			 
			 for(int row=2; row<=rowCount; row++) {
				 for(int col=1; col<=colCount; col++) {
					String xpath = breforXpath+ row + afterXpath + col + "]";
					String text = doGetText(By.xpath(xpath));
					System.out.println(text+" | ");
				 }
				 System.out.println();
			 }
		}
		
		/***************************** Actions Util ********************//////////////////////////////
		
		public void doMoveToElement(By locator) {
			Actions action = new Actions(driver);
			action.moveToElement(getElement(locator)).perform();
		}


		public void doClickOnChildMenu(By parenMenuLocator,By childMenuLocator) throws InterruptedException {
			doMoveToElement(parenMenuLocator);
			Thread.sleep(3000);
			doClick(childMenuLocator);
			
		}
		
		
		public void doActionsSendKyes(By locator,String value) {
			 Actions action = new Actions(driver);
			 action.sendKeys(getElement(locator),value).build().perform();
		}
		
		public void doActionsSendKyesOnActiveElement(By locator,String value) {
			 Actions action = new Actions(driver);
			 action.click(getElement(locator)).sendKeys(value).build().perform();
			 
		}
		
		public  void doActionClick(By locator) {
			 Actions action = new Actions(driver);
			 action.click(getElement(locator)).build().perform();
		}

		public  void doActionMoveToElementClick(By locator) {
			 Actions action = new Actions(driver);
			 action.moveToElement(getElement(locator)).click().build().perform();
		}
		
		/******************* Wait Utils **************************/
		
		/*
		 * presenceOfElementLocated  means
		 * 
		 * An expectation for checking that an element is present on the DOM of a page. 
		 * 
		 * This does notnecessarily mean that the element is visible.
		 * 
		 */
		
		public WebElement doPresenceOfElementLocated(By locator,int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		
		public WebElement doPresenceOfElementLocated(By locator,int timeOut,long intervalTime) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		
		
		public WebElement isElementVisible(By locator,int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		public WebElement isElementVisible(By locator,int timeOut,long intervalTime) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		public WebElement waitForElementToBeVisible(By locator,int timeOut,long intervalTime) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		public WebElement waitForElementToBeVisibleWithWebElement(By locator,int timeOut,long intervalTime) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
			return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
		}
		
		public List<WebElement> waitForElementsToBeVisible(By locator,int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		
		public  List<WebElement> waitForElementsToBeVisible(By locator,int timeOut,long intervalTime) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		
		public List<String> getElementsTextListWithWait(By locator,int timeOut) {
			List<WebElement> eleList = waitForElementsToBeVisible(locator, timeOut);
			List<String> eleTextList = new ArrayList<String>();
			for(WebElement e: eleList) {
				String text = e.getText();
				eleTextList.add(text);
			}
			return eleTextList;
			
		}
		
		/***************** Wait Utils for Non-WebElements ************************/
		
		public Boolean waitForURLToContain(String urlFraction,int timeOut) {
		    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		    return wait.until(ExpectedConditions.urlContains(urlFraction));

	}
	
	    public Boolean waitForURLToBe(String url,int timeOut) {
	    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	    return wait.until(ExpectedConditions.urlContains(url));

}
	    
	    
	    public String doGetTitleWithFraction(String titleFraction,int timeOut) {
			if(waitForTitleContains(titleFraction, timeOut)) {
				return driver.getTitle();
			}
			return null;
		}
		
		public String doGetTitle(String title,int timeOut) {
			if(waitForTitleToBe(title, timeOut)) {
				return driver.getTitle();
			}
			return null;
		}
		
		public Boolean waitForTitleContains(String titleFraction,int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		    return wait.until(ExpectedConditions.titleContains(titleFraction));
		}
		
		public Boolean waitForTitleToBe(String title,int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		    return wait.until(ExpectedConditions.titleContains(title));
		}
		
		public Alert waitForAlert(int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
			 return wait.until(ExpectedConditions.alertIsPresent());
		}
		
		public String  getAlertText(int timeOut) {
			return waitForAlert(timeOut).getText();
		}
		
		public void  doAlertAccept(int timeOut) {
			waitForAlert(timeOut).accept();
		}

		public void  doAlertDismiss(int timeOut) {
			waitForAlert(timeOut).dismiss();
		}

		public void  enterAlertText(String value,int timeOut) {
			waitForAlert(timeOut).sendKeys(value);
		}
		
		
		public void waitForFrameByNameOrId(String nameOrID,int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrID));
		}
		
		public void waitForFrameByIndex(int frameIndex,int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
		}
		
		public void waitForFrameByLocator(int frameLocator,int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		}
		
		public void waitForFrameByElement(int frameElement,int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
		}
		
		public void clickElementWhenReady(By locator,int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		}
		
		public void clickElementWhenReady(By locator,int timeOut,long intervalTime) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		}
		
		public WebElement waitForElementPresentUsingFluentWait(By locator,int timeOut,int poolingTime) {
			
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	                .withTimeout(Duration.ofSeconds(timeOut))
	                .pollingEvery(Duration.ofSeconds(poolingTime))
	                .ignoring(NoSuchElementException.class)
	                .ignoring(StaleElementReferenceException.class)
	                .withMessage(Errors.ELEMENT_NOT_FOUND_ERROR_MESSG);
	             return wait.until(ExpectedConditions.presenceOfElementLocated(locator));      
		}
		
		public WebElement waitForElementUsingWebDirverWait(By locator,int timeOut,int poolingTime) {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			wait
			    .pollingEvery(Duration.ofSeconds(poolingTime))
			    .ignoring(NoSuchElementException.class)
	            .ignoring(StaleElementReferenceException.class)
	            .withMessage(Errors.ELEMENT_NOT_FOUND_ERROR_MESSG);
	        
			 return wait.until(ExpectedConditions.presenceOfElementLocated(locator));      
			    
		}
		
		/***************  Custom Wait **************************/
		
		
	public WebElement reTryingElement(By locator,int timeOut) {
		WebElement element = null;
		
		int attempts =0;
		
		while(attempts < timeOut) {
			try {
			element = getElement(locator);
			break;
			}catch (NoSuchElementException e) {
				System.out.println("element is not found in attempt:" + attempts + " : " + locator);
				try {
					Thread.sleep(500);
				}catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempts ++;
		}
		
		if(element == null) {
			try {
				throw new Exception();
			}catch(Exception e) {
				System.out.println("element is not found exception ....  tried for :"  + timeOut + "with the interval of : " + 500 + "ms");
			}
		
			}
	
		return element;
	
	}
	
	
		
} 



