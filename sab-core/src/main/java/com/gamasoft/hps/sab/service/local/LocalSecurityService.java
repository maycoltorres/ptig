/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.service.local;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamasoft.hps.sab.domain.User;
import com.gamasoft.hps.sab.dto.UserDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.exception.ServiceException;
import com.gamasoft.hps.sab.repository.UserRepository;
import com.gamasoft.hps.sab.service.SecurityService;
/**
 *
 * @author vascordoba
 */
@Service
public class LocalSecurityService implements SecurityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalSecurityService.class);
    
    private UserRepository userRepository;

    @Autowired
    public LocalSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getUsers() throws ServiceException {
        List<User> users=this.userRepository.getAll();
        List<UserDto> dtos=new ArrayList<UserDto>();
        if(users!=null && !users.isEmpty()){
            for(User user:users){
                dtos.add(user.toDto());
            }
        }
        
        return dtos;
    }

    @Override
    public UserDto getUser(final long id) throws ServiceException {
        return this.userRepository.getById(id).toDto();
    }

    @Override
    public UserDto getUser(final String username) throws ServiceException {
        return this.userRepository.getUserByUsername(username).toDto();
    }

    @Override
    public UserDto createUser(final UserDto userDto) throws ServiceException {
        User user = new User(userDto);
        this.userRepository.add(user);
        return user.toDto();
    }

    @Override
    public UserDto updateUser(final UserDto userDto) throws ServiceException {
        if (userDto == null) {
		    throw new ServiceException("User cannot be null.");
		}
		// we need to explicitly set the id to the UserDto because there's no User field  
		User user = new User(userDto);
		user.setId(userDto.getId());
		try {
			this.userRepository.update(user);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (user.toDto());
    }

    @Override
    public void deleteUser(long userId) throws ServiceException {
        try {
            this.userRepository.remove(userId);
        } catch (RepositoryException e) {
            LOGGER.error(e.getStackTrace().toString());
            throw new ServiceException(e.getMessage());
        }
    }
}
