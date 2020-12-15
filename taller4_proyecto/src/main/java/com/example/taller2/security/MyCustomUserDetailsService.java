package com.example.taller2.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.taller2.model.Userr;
import com.example.taller2.repository.UserrRepository;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {

	private UserrRepository userrRepo;
	// private PasswordEncoder passwordEncoder;

	public MyCustomUserDetailsService(UserrRepository userrRepo) {
		this.userrRepo = userrRepo;
		// this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Userr userr = userrRepo.findByUserName(userName);

		if (userr != null) {
			System.out.print("user found \n");
			System.out.print("" + userr.getUserPassword() + "\n");
			User.UserBuilder builder = User.withUsername(userName).password(userr.getUserPassword()).roles("");
			return builder.build();
		} else {
			System.out.print("user not found \n");
			throw new UsernameNotFoundException("User not found.");
		}
	}
}