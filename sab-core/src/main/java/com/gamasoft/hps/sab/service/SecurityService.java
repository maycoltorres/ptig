/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.service;

import java.util.List;

import com.gamasoft.hps.sab.dto.UserDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.exception.ServiceException;

/**
 *
 * @author vascordoba
 */
public interface SecurityService {
    /**
     * Returns all users in a list.
     * 
     * @return             the users list
     */
    List<UserDto> getUsers() throws ServiceException;
    
    /**
     * Returns the user with the given Id.
     * 
     * @param id           the user id
     * @return             the corresponding user
     */
    UserDto getUser(final long id) throws ServiceException;
    
    /**
     * Returns the user with the give username.
     * 
     * @param username     the username
     * @return             the corresponding user
     */
    UserDto getUser(final String username) throws ServiceException;
    
    /**
     * Creates a new user with the given data.
     * 
     * @param userDto the user to be included
     * @throws {@link UserServiceException} if there is a problem
     */
    UserDto createUser(final UserDto userDto) throws ServiceException;
    
    /**
     * Updates the user give the given information.
     * 
     * @param userDto the updated user information
     * @throws RepositoryException 
     * @throws {@link UserServiceException} if there is a problem
     */
    UserDto updateUser(final UserDto userDto) throws ServiceException, RepositoryException;
    
    /**
     * Deletes the user with the given data.
     * 
     * @param userId the id of the user to delete
     * @throws {@link UserServiceException} if there is a problem
     */
    void deleteUser(final long userId) throws ServiceException;
}
