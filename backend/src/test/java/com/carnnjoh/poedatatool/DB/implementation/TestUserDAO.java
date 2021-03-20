package com.carnnjoh.poedatatool.DB.implementation;

import com.carnnjoh.poedatatool.db.dao.UserDAO;
import com.carnnjoh.poedatatool.db.model.User;
import com.carnnjoh.poedatatool.db.utils.CreateSuccessResult;
import com.carnnjoh.poedatatool.db.utils.DeleteSuccessResult;
import com.carnnjoh.poedatatool.db.utils.Result;
import com.carnnjoh.poedatatool.db.utils.UpdateSuccessResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestUserDAO {

	@Autowired
	UserDAO userDAO;

	User testUser;

	@Before
	public void setup() {
		testUser = new User(1, "Standard", "testUser", "pc", "test");
	}

	@Test
	public void testSave() {

		Result saveResult = userDAO.save(testUser);

		Assert.assertTrue(saveResult instanceof CreateSuccessResult);

		User fetchedUser = userDAO.fetch(testUser.getPk());

		validateTwoUserObjects(testUser, fetchedUser);
	}

	@Test
	public void testDeleteUser() {

		saveInitialData();

		User fetchedUser = userDAO.fetch(testUser.getPk());

		Result deleteResult = userDAO.deleteByPk(fetchedUser.getPk());

		Assert.assertTrue(deleteResult instanceof DeleteSuccessResult);

		fetchedUser = userDAO.fetch(fetchedUser.getPk());

		Assert.assertNull(fetchedUser);
	}

	@Test
	public void testUpdateUser() {
		User initialUser = saveInitialData();

		initialUser.setAccountName("Testesen");

		Result updateResult = userDAO.update(initialUser);

		Assert.assertTrue(updateResult instanceof UpdateSuccessResult);

		User fetchedUser = userDAO.fetch(initialUser.getPk());

		Assert.assertNotEquals(fetchedUser.getAccountName(), testUser.getAccountName());
		Assert.assertEquals(fetchedUser.getLeague(), testUser.getLeague());
		Assert.assertEquals(fetchedUser.getRealm(), testUser.getRealm());
		Assert.assertEquals(fetchedUser.getSessionId(), testUser.getSessionId());
	}

	@Test
	public void testFetchAll() {

		generateUsers();

		List<User> users = userDAO.fetchAll();

		Assert.assertFalse(users.isEmpty());

	}

	private void generateUsers() {
		generateUsers(100);
	}

	private void generateUsers(int numberOfUsers) {

		List<User> users = new ArrayList<>();

		for(int i = 0; i <= numberOfUsers; i++) {
			users.add(new User(i, "Standard", "testUser", "pc", "qweook1w2524"));
		}

		for(User user : users) {
			userDAO.save(user);
		}
	}

	private User saveInitialData() {
		Result saveResult = userDAO.save(testUser);
		Assert.assertTrue(saveResult instanceof CreateSuccessResult);
		return userDAO.fetch(((CreateSuccessResult) saveResult).getPk());
	}

	private void validateTwoUserObjects(User expectedResultObject, User actualResultObject) {
		Assert.assertEquals(expectedResultObject.getPk(), actualResultObject.getPk());
		Assert.assertEquals(expectedResultObject.getAccountName(), actualResultObject.getAccountName());
		Assert.assertEquals(expectedResultObject.getLeague(), actualResultObject.getLeague());
		Assert.assertEquals(expectedResultObject.getRealm(), actualResultObject.getRealm());
		Assert.assertEquals(expectedResultObject.getSessionId(), actualResultObject.getSessionId());
	}

}
