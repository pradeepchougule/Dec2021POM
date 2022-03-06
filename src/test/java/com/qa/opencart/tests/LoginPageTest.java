	package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100 : Design Open Cart App - Login Page")
@Story("US 101: Open Cart Login Design with multiple features ...")
public class LoginPageTest extends BaseTest {

	@Description("login Page Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("page title: " + actTitle);
		Assert.assertEquals(actTitle,Constants.LOGIN_PAGE_TITLE,".... login page title is not correct ...");
	}
	
	@Description("login Page URL Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		Assert.assertTrue(loginPage.getLoginPageUrl());
	}
	
	@Description("forgot pwd link Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isforgoetPwdLinkExist());
	}
	
	@Description("Registeration link Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4,enabled = false)
	public void registerLinkTest() {
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}
	
	@Description("login Test with correct credentials")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 5)
	public void loginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password").trim());
		Assert.assertEquals(accountsPage.getAccountPageTitle(),Constants.Account_PAGE_TITLE);
	}
}
