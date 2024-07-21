package api.test;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker ;
	User userPayload;
	org.apache.logging.log4j.Logger logger;
	
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//
		logger = LogManager.getLogger(this.getClass());
		
		logger.debug("debugging.........");
	}
	

	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("******************* Creating User **********************");
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******************* User is created **********************");

	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("******************* Reading User info **********************");

		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("******************* User info is displayed **********************");

	}
	
	@Test(priority=3)
	public void testupdateUser()
	{
		
		logger.info("******************* Updating User **********************");

		//update data by payload updation
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("******************* User is updated **********************");

		//Checking data after update
		Response responseafterupdate = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(responseafterupdate.getStatusCode(), 200);
		
	
	}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("******************* Deleting User **********************");

		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("******************* User deleted **********************");

	}

}
