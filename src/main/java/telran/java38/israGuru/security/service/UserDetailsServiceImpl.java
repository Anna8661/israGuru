package telran.java38.israGuru.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import telran.java38.israGuru.user.dao.UserRepository;
import telran.java38.israGuru.user.dao.UserSecurityRepository;
import telran.java38.israGuru.user.model.User;
import telran.java38.israGuru.user.model.UserSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserSecurityRepository userSecurityRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findById(username).orElseThrow(()-> new UsernameNotFoundException(username));
		String[] roles = user.getRoles()
				.stream()
				.map(r -> "ROLE_" + r.toString().toUpperCase())
				.toArray(String[]:: new);
				
		UserSecurity userSecurity = userSecurityRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
		return new org.springframework.security.core.userdetails.User(username, userSecurity.getPassword(), AuthorityUtils.createAuthorityList(roles));

	}

}
