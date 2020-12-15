package com.carnnjoh.poedatatool.api;

import com.carnnjoh.poedatatool.db.dao.UserDAO;
import com.carnnjoh.poedatatool.db.model.User;
import com.carnnjoh.poedatatool.db.utils.CreateSuccessResult;
import com.carnnjoh.poedatatool.db.utils.Result;
import com.carnnjoh.poedatatool.db.utils.SuccessResult;
import com.sun.org.apache.bcel.internal.generic.FSUB;
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
	private UserDAO userDAO;

	private final RestTemplate template  = new RestTemplate();

	@GetMapping("/{pk}")
	public ResponseEntity<User> get(@PathVariable Integer pk) {
		if(pk < 0)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		User user = userDAO.fetch(pk);

		if(user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
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
		if(pk < 0)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		Result deleteResult = userDAO.deleteByPk(pk);

		if(deleteResult instanceof SuccessResult)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping()
	public ResponseEntity<User> post(@RequestBody User user) {

		if(user == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		Result postResult = userDAO.save(user);

		if(postResult instanceof CreateSuccessResult){
			user.setPk(((CreateSuccessResult) postResult).getPk());
			return new ResponseEntity<>(user, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/{pk}")
	public ResponseEntity<User> put(@PathVariable Integer pk, @RequestBody User userDetails) {
		if(pk < 0)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		User user = userDAO.fetch(pk);

		if(user == null){
			user = new User();
		}

		user.setLeague(userDetails.getLeague());
		user.setAccountName(userDetails.getAccountName());
		user.setRealm(userDetails.getRealm());
		user.setSessionId(userDetails.getSessionId());

		Result putResult = (user.getPk() == null)
			? userDAO.save(user)
			: userDAO.update(user);

		if(putResult instanceof CreateSuccessResult){
			user.setPk(((CreateSuccessResult) putResult).getPk());
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		}

		return (putResult instanceof SuccessResult)
			? new ResponseEntity<>(user, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}