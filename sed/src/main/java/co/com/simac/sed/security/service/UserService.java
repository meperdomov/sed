package co.com.simac.sed.security.service;

import co.com.simac.sed.security.dto.UserDTO;
import co.com.simac.sed.security.model.User;
import co.com.simac.sed.util.PageDTO;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
public interface UserService {

	public PageDTO<UserDTO> getUserPageDTO(int page, int size, String... camposIgnorados);

	public UserDTO getUserById(Long id, String... camposIgnorados);

	public UserDTO getUserByUserName(String userName, String... camposIgnorados);

	public User save(UserDTO indicador);

	public void delete(Long id);

	public int updateUserPassword(String password, Long id);

}
