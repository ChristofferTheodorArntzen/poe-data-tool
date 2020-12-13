package com.carnnjoh.poedatatool.api;

import com.carnnjoh.poedatatool.db.dao.UserDAO;
import com.carnnjoh.poedatatool.db.model.User;
import com.carnnjoh.poedatatool.db.utils.CreateSuccessResult;
import com.carnnjoh.poedatatool.db.utils.Result;
import com.carnnjoh.poedatatool.db.utils.SuccessResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping( path = "/user")
public class UserController {

	@Autowired
	ObjectMapper mapper;

	@Autowired
	private UserDAO userDAO;


	private final RestTemplate template  = new RestTemplate();

	@GetMapping("/{pk}")
	public ResponseEntity<User> get(@PathVariable Integer pk) {
		if(pk != null && pk > 0) {
			User user = userDAO.fetch(pk);

			if(user != null) {
				return new ResponseEntity<>(user, HttpStatus.OK);
			}
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	@GetMapping()
	public ResponseEntity<List<User>> getAll() {
		List<User> users = userDAO.fetchAll();

		if(users.size() > 0) {
			return new ResponseEntity<>(users, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	@DeleteMapping("/{pk}")
	public ResponseEntity<User> delete(@PathVariable int pk) {
		Result deleteResult = userDAO.deleteByPk(pk);

		if(deleteResult instanceof SuccessResult) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping()
	public ResponseEntity<User> post(@RequestBody User user) {

		if(user != null){
			Result postResult = userDAO.save(user);
			if(postResult instanceof CreateSuccessResult){
				user.setPk(((CreateSuccessResult) postResult).getPk());
				return new ResponseEntity<>(user, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@PutMapping("/{pk}")
	public ResponseEntity<User> put(@PathVariable Integer pk, @RequestBody User userDetails) {
		User user = userDAO.fetch(pk);

		if(user == null){
			user = new User();
		}

		user.setLeague(userDetails.getLeague());
		user.setAccountName(userDetails.getAccountName());
		user.setRealm(userDetails.getRealm());
		user.setSessionId(userDetails.getSessionId());

		Result putResult = userDAO.save(user);

		if(putResult instanceof CreateSuccessResult){
			user.setPk(((CreateSuccessResult) putResult).getPk());
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}