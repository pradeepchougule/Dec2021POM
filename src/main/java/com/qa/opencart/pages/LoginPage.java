package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage extends DriverFactory {

	// 1. declare private driver
	
	   private WebDriver driver;
	   private ElementUtil eleUtil;
	
	
	//2. create Constructor of the page class:
	
	public LoginPage(WebDriver driver) {
		this.driver= driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	// 3. Create By Locators: Object Repository (OR)
	
		private By emailId = By.id("input-email");
		private By password = By.id("input-password");
		private By loginBtn = By.xpath("//input[@value='Login' and @type='submit']");
		private By registerLink = By.linkText("Register");
	    private By forgotPwdLink = By.linkText("Forgotten Password");
	    private By loginErrorMesg =  By.cssSelector("div.alert.alert-danger.alert-dismissible");
	    
		
	
	//4. page actions: features (Behavior) of the page in the form of methods.
	
	@Step("getting login page title .........")
	public String getLoginPageTitle() {
		return eleUtil.doGetTitle(Constants.LOGIN_PAGE_TITLE,Constants.DEFAULT_TIME_OUT);
	}
	
	@Step("getting login page url .........")
	public Boolean getLoginPageUrl() {
		return eleUtil.waitForURLToContain(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	
	@Step("checking forgot pwd link exist or not .........")
	public boolean isforgoetPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
    }
	
	@Step("checking register pwd link exist or not .........")
	public boolean isRegisterLinkExist() {
		return eleUtil.doIsDisplayed(registerLink);
	}
	
	@Step("do login with username: {0} and password: {1}")
	public AccountsPage doLogin(String un,String pwd) {
		System.out.println("Login with : " + un + " and " + pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
		
	}
	
	@Step("do login with wrong username: {0} and wrong password: {1}")
	public boolean doLoginWithWrongCredentials(String un,String pwd) {
		System.out.println("try to login with wrong credentials : " + un + " and " + pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClear(loginBtn);
		
		String errorMsg = eleUtil.doGetText(loginErrorMesg);
		System.out.println(errorMsg);
		if(errorMsg.contains(Constants.LOGIN_ERROR_MESSAGE)) {
			System.out.println("login is not done ... ");
			return false;
		}
		return true;
	}
	
	@Step("navigating to registeration page ...")
	public RegistrationsPage goToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegistrationsPage(driver);
	}
	
	
}
