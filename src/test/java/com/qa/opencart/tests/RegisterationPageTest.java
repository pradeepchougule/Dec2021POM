package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterationPageTest extends BaseTest {

	@BeforeClass
	public void setupRegistration() {
		registrationsPage = loginPage.goToRegisterPage();
		
	}
	
	
	@DataProvider
	public Object[][] getRegisterData() {
		return ExcelUtil.getTestData(Constants.REGISTRATION_SHEET_NAME);
	}
	
	@Test(dataProvider ="getRegisterData")
	public void userRegisterationTest(String firstName,String lastName,String email,String telephone,String password,String subscribe) {
		Assert.assertTrue(registrationsPage.accountRegistration(firstName, lastName, email, telephone, password, subscribe));
	}
}
