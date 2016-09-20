package com.gamasoft.hps.sab.service;

import java.util.List;

import com.gamasoft.hps.sab.dto.TransaccionDto;
import com.gamasoft.hps.sab.dto.UserDto;

public interface UserService {

	List<UserDto> getUsers(Long Usuario, Long Grupo);
	String addUser(UserDto user);
	String inactivateUser(long idUser, Long Usuario, Long Grupo);
	String activateUser(long idUser, Long Usuario, Long Grupo);
	List<TransaccionDto> getUserProfile(long idUser, Long Usuario, Long Grupo);
	UserDto DatallesUsuario(long idUser);
	List<UserDto> getUserInactivos(Long usuario, Long grupo);
	UserDto getuser(long idUser);
	String updateUser(Long idUser, UserDto user, Long cliente, Long Grupo);

	
}
