package com.carnnjoh.poedatatool.DB.integration;

import com.carnnjoh.poedatatool.db.dao.UserDAO;
import com.carnnjoh.poedatatool.db.model.User;
import com.carnnjoh.poedatatool.db.utils.CreateSuccessResult;
import com.carnnjoh.poedatatool.db.utils.DeleteSuccessResult;
import com.carnnjoh.poedatatool.db.utils.Result;
import com.carnnjoh.poedatatool.db.utils.UpdateSuccessResult;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles("test")
public class TestUserDAOIntegration {

	@Autowired
	UserDAO userDAO;

	User testUser;

	@BeforeEach
	public void setup() {
		testUser = new User(1, "Standard", "testUser", "pc", "test");

		Result saveResult = userDAO.save(testUser);
		testUser.setPk(((CreateSuccessResult)saveResult).getPk());
	}

	@AfterAll
	public void flush() {
		List<User> users = userDAO.fetchAll();

		for (User user : users) {
			userDAO.deleteByPk(user.getPk());
		}
	}

	@Test
	public void testSave() {

		Result saveResult = userDAO.save(testUser);

		assertThat(saveResult).isInstanceOf(CreateSuccessResult.class);

		User fetchedUser = userDAO.fetch(testUser.getPk());

		validateTwoUserObjects(testUser, fetchedUser);
	}

	@Test
	public void testDeleteUser() {
		User fetchedUser = userDAO.fetch(testUser.getPk());

		Result deleteResult = userDAO.deleteByPk(fetchedUser.getPk());
		assertThat(deleteResult).isInstanceOf(DeleteSuccessResult.class);

		fetchedUser = userDAO.fetch(fetchedUser.getPk());
		assertThat(fetchedUser).isNull();
	}

	@Test
	public void testUpdateUser() {
		User initialUser = userDAO.fetch(testUser.getPk());
		initialUser.setAccountName("Testesen");

		Result updateResult = userDAO.update(initialUser);
		assertThat(updateResult).isInstanceOf(UpdateSuccessResult.class);

		User fetchedUser = userDAO.fetch(initialUser.getPk());
		assertThat(fetchedUser.getAccountName()).isNotEqualTo(testUser.getAccountName());

		assertThat(fetchedUser.getRealm()).isEqualTo(testUser.getRealm());
		assertThat(fetchedUser.getLeague()).isEqualTo(testUser.getLeague());
		assertThat(fetchedUser.getSessionId()).isEqualTo(testUser.getSessionId());
	}

	@Test
	public void testFetchAll() {
		generateUsers();
		List<User> users = userDAO.fetchAll();
		assertThat(users.size() > 0).isTrue();
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

	private void validateTwoUserObjects(User expectedResultObject, User actualResultObject) {
		assertThat(actualResultObject.getPk()).isEqualTo(expectedResultObject.getPk());
		assertThat(actualResultObject.getAccountName()).isEqualTo(expectedResultObject.getAccountName());
		assertThat(actualResultObject.getLeague()).isEqualTo(expectedResultObject.getLeague());
		assertThat(actualResultObject.getRealm()).isEqualTo(expectedResultObject.getRealm());
		assertThat(actualResultObject.getSessionId()).isEqualTo(expectedResultObject.getSessionId());
	}

}
