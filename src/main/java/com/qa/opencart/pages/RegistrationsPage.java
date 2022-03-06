package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationsPage {
	
	   private WebDriver driver;
	   private ElementUtil eleUtil;
	   
	   public RegistrationsPage(WebDriver driver) {
			this.driver= driver;
			eleUtil = new ElementUtil(driver);
		}

	   private By firstName = By.id("input-firstname");
	   private By lastName = By.id("input-lastname");
	   private By emailId = By.id("input-email");
	   private By telePhone = By.id("input-telephone");
	   private By password = By.id("input-password");
	   private By confirmPassword = By.id("input-confirm");
	   
	   private By subscriberYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	   private By subscriberNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");
	   
	   private By agreeCheckBox = By.name("agree");
	   private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	   private By successMessg = By.cssSelector("div#content h1");
	   
	   private By logOutLink = By.linkText("Logout");
	   private By registerLink = By.linkText("Register");
	   

      public boolean accountRegistration(String firstName,String lastName,String email,String telephone,String password,String subscribe) {
    	  eleUtil.doSendKeys(this.firstName, firstName);
    	  eleUtil.doSendKeys(this.lastName, lastName);
    	  eleUtil.doSendKeys(this.emailId, email);
    	  eleUtil.doSendKeys(this.telePhone, telephone);
    	  eleUtil.doSendKeys(this.password, password);
    	  eleUtil.doSendKeys(this.subscriberYes, subscribe);
    	  eleUtil.doSendKeys(this.subscriberNo, subscribe);
    	  
    	  
    	  if(subscribe.equals("yes")) {
    		  eleUtil.doClear(subscriberYes);
    	  }else {
    		  eleUtil.doClear(subscriberNo);
    	  }
    	  
    	  eleUtil.doClick(agreeCheckBox);
    	  eleUtil.doClick(continueButton);
    	  String mesge = eleUtil.waitForElementToBeVisible(successMessg, 5, 1000).getText();
    	  
    	  if(mesge.contains(Constants.REGISTER_SUCCESS_MESSG)) {
    		  eleUtil.doClick(logOutLink);
    		  eleUtil.doClear(registerLink);
    		  return true;
    	  }
    	  return false;
      }
      

}    