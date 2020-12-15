package com.example.taller2.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.taller2.model.Userr;
import com.example.taller2.repository.UserrRepository;
import com.example.taller2.services.interfaces.UserrService;

@Service
public class UserrServiceImp implements UserrService {

	private UserrRepository userRepository;
	// private PasswordEncoder passwordEncoder;

	@Autowired
	public UserrServiceImp(UserrRepository userRepository) {
		this.userRepository = userRepository;
		// this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Userr saveUserr(Userr user) {

		if (userAllCorrect(user)) {
//			String password = user.getUserPassword();
//			user.setUserPassword(passwordEncoder().encode(password));
			return userRepository.save(user);
		} else {
			throw new RuntimeException();
		}

	}

	@Override
	public void cleanUp() {
		userRepository.deleteAll();
	}

	@Override
	public Userr findById(Long id) {
		return userRepository.findById(id).get();
	}

	private boolean userAllCorrect(Userr user) {
		if (user.getUserName() == null | user.getUserName().equals("") | user.getUserPassword() == null
				| user.getUserPassword().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Iterable<Userr> findAll() {
		return userRepository.findAll();
	}

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
