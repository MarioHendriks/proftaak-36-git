package com.PTS3S36G3.UserService;

import com.PTS3S36G3.UserService.Logic.Resources.UserResource;
import com.PTS3S36G3.UserService.Logic.UserLogic;
import com.PTS3S36G3.UserService.Models.User;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes= com.PTS3S36G3.UserService.Logic.UserServiceApplication.class)
class UserServiceApplicationTests
{
	UserResource userResource = new UserResource();

	@BeforeAll
	static void RemoveTestUser(){
		User user = new User();
		UserLogic userLogic = new UserLogic();
		userLogic.DeleteTestUser();
	}

	@Test
	void CreateTestUser() {
		//UserResource userResource = new UserResource();
		//userResource.AddUser("{'headers':{'Authorization':'Bearer'},'data':{'username':'ewoxRxHtXSTXMMotKBhBqcQjoLQh3P4HM7fMkI3btiPjTVipnnTmwyErJYcbYR0fPbF73iCn9AQAcSq3rADnpIroUSf4qto5baNK','email':'0i2mXBnispaiKYzZGkMe6bRIshJJkG29A3uSUpGaqa0ouPuAgHgQSvoKOZvjyoKFsMpzue6FfPC9gJ2c0LOkBIc7lwGL5QoQls9E'}}");
		assertTrue( 1 == 1);
	}

	@Test
	void GetUserByIdSuccesTest() {
		User testUser = userResource.GetUserByEmail("0i2mXBnispaiKYzZGkMe6bRIshJJkG29A3uSUpGaqa0ouPuAgHgQSvoKOZvjyoKFsMpzue6FfPC9gJ2c0LOkBIc7lwGL5QoQls9E");

		User mockUser = new User("ewoxRxHtXSTXMMotKBhBqcQjoLQh3P4HM7fMkI3btiPjTVipnnTmwyErJYcbYR0fPbF73iCn9AQAcSq3rADnpIroUSf4qto5baNK", "0i2mXBnispaiKYzZGkMe6bRIshJJkG29A3uSUpGaqa0ouPuAgHgQSvoKOZvjyoKFsMpzue6FfPC9gJ2c0LOkBIc7lwGL5QoQls9E");
		User expectedUser = userResource.GetUserById(testUser.getId());

		System.out.println(expectedUser.toString());
		System.out.println(mockUser.toString());

		assertTrue(mockUser.getEmail().equals(expectedUser.getEmail()));
	}

	@Test
	void GetUserByIdFailureTest() {
		//Make a user with NULL ID to test exception.
		User mockUser = new User();
		User expectedUser = userResource.GetUserById(mockUser.getId());

		assertNull(expectedUser.getName());
	}

	@Test
	void GetUserByEmailSuccesTest()
	{
		User mockUser = new User(1,"Freek", "freek@gmail.com");
		User expectedUser = userResource.GetUserByEmail(mockUser.getEmail());

		assertEquals(mockUser.toString(), expectedUser.toString());
	}

	@Test
	void GetUserByEmailFailureTest()
	{
		User mockUser = new User();
		User expectedUser = userResource.GetUserByEmail(mockUser.getEmail());

		assertNull(expectedUser.getName());
	}
}