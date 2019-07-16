package co.com.simac.sed.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.simac.sed.security.dao.UserRepository;
import co.com.simac.sed.security.dto.UserDTO;
import co.com.simac.sed.security.model.Permission;
import co.com.simac.sed.security.model.Role;
import co.com.simac.sed.security.model.User;
import co.com.simac.sed.security.service.UserService;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.Utilities;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByUserNameEquals(userName);
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(userName, user.getPassword(),
					getAuthorities(user.getRole()));
		}
		throw new UsernameNotFoundException("Nombre de usuario " + userName + " no encontrado");
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
		return getGrantedAuthorities(getPrivileges(role));
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

	private List<String> getPrivileges(Role role) {
		List<String> privileges = new ArrayList<>();
		List<Permission> permissions = role.getPermissions();

		for (Permission permission : permissions) {
			privileges.add(permission.getCode());
		}
		return privileges;
	}

	@Override
	public PageDTO<UserDTO> getUserPageDTO(int page, int size, String... camposIgnorados) {
		Pageable pageable = new PageRequest(page - 1, size);
		Page<User> userPage = userRepository.findAll(pageable);
		return Utilities.toPageDTO(userPage, UserDTO.class, camposIgnorados);
	}

	@Override
	public UserDTO getUserById(Long id, String... camposIgnorados) {
		User user = userRepository.findOne(id);
		return user.getDTO(camposIgnorados);
	}

	@Override
	public User save(UserDTO userDTO) {
		User user = new User();
		if (userDTO.getId() != null) {
			user = userRepository.findOne(userDTO.getId());
			userDTO.setPassword(user.getPassword());
		}
		Utilities.toEntity(user, userDTO);
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.delete(id);
	}

	@Override
	public UserDTO getUserByUserName(String userName, String... camposIgnorados) {
		User user = userRepository.findByUserNameEquals(userName);
		if (user != null) {
			return user.getDTO(camposIgnorados);
		}
		return null;
	}

	@Override
	public int updateUserPassword(String password, Long id) {
		return userRepository.updateUserPassword(password, id);
	}
}
