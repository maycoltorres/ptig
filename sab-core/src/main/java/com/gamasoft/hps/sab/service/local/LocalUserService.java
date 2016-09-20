package com.gamasoft.hps.sab.service.local;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gamasoft.hps.sab.domain.Punto;
import com.gamasoft.hps.sab.domain.Transaccion;
import com.gamasoft.hps.sab.domain.User;
import com.gamasoft.hps.sab.dto.TransaccionDto;
import com.gamasoft.hps.sab.dto.UserDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.PuntoRepository;
import com.gamasoft.hps.sab.repository.UserRepository;
import com.gamasoft.hps.sab.service.UserService;

/**
 *
 * @author wospino
 */
@Service
public class LocalUserService implements UserService {

	private UserRepository userRepository;

	@Autowired
	private PuntoRepository puntoRepository;

	@Autowired
	public LocalUserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<UserDto> getUsers(Long Usuario, Long Grupo) {
		if (Grupo == null) {
			List<User> users;
			if (Usuario == 1) {
				users = userRepository.getAll();
			} else {
				users = userRepository.getAllByCliente(Usuario);
			}

			if (users != null && !users.isEmpty()) {
				List<UserDto> usersDto = new ArrayList<UserDto>();
				for (User c : users) {
					usersDto.add(c.toDto());
				}
				return usersDto;
			}
		}
		if (Grupo != null) {
			List<User> users = userRepository.getAllByGrupo(Grupo);
			if (users != null && !users.isEmpty()) {
				List<UserDto> usersDto = new ArrayList<UserDto>();
				for (User c : users) {
					usersDto.add(c.toDto());
				}
				return usersDto;
			}
		}

		return new ArrayList<UserDto>();
	}

	@Override
	public String addUser(UserDto user) {
		User u = new User(user);
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String name = auth.getName();
		User currentUser = userRepository.getUserByUsername(name);
		u.setIdCliente(currentUser.getIdCliente());
		u.setActivo(true);
		try {
			this.userRepository.add(u);
			return Long.toString(u.getId());
		} catch (Exception e) {
			return "Usuario ya Existe";

		}
	}

	public String addUser(UserDto user, Long idCliente) {
		User u = new User(user);
		u.setIdCliente(idCliente);
		u.setActivo(true);
		u.setFullName(user.getEmail());

		Set<Punto> puntos = new HashSet<Punto>();
		for (Long idPunto : user.getPuntos()) {
			puntos.add(puntoRepository.getById(idPunto));
		}
		u.setPuntos(puntos);

		try {
			this.userRepository.add(u);
			return Long.toString(u.getId());
		} catch (Exception e) {
			return "Usuario ya existe";
		}
	}

	@Override
	public String updateUser(Long idUser, UserDto user, Long cliente, Long Grupo) {
		CustomUserDetailsService cud = new CustomUserDetailsService();

		User u = userRepository.getById(idUser);
		u.setIdCliente(user.getIdCliente());
		u.setEmail(user.getEmail());
		u.setIdGrupo(user.getIdGrupo());
		u.setFullName(user.getFullName());

		if(user.getPuntos() != null){
			Set<Punto> puntos = new HashSet<Punto>();
			for (Long idPunto : user.getPuntos()) {
				puntos.add(puntoRepository.getById(idPunto));
			}
			u.setPuntos(puntos);
		}

		if (user.getPassword() != null && user.getPassword() != "") {
			u.changePassword(user.getPassword());
		}

		try {
			this.userRepository.update(u);
			return "usuario actualizado";
		} catch (Exception e) {
			return "No se pudo actualizar";
		}
	}

	@Override
	public String inactivateUser(long idUser, Long Usuario, Long Grupo) {
		User usuario = this.userRepository.getById(idUser);
		usuario.setActivo(false);
		try {
			this.userRepository.update(usuario);
			return "Usuario Desactivado";
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			return "Usuario No MOdificado";
		}

	}

	@Override
	public String activateUser(long idUser, Long Usuario, Long Grupo) {
		User usuario = this.userRepository.getById(idUser);
		usuario.setActivo(true);
		try {
			this.userRepository.update(usuario);
			return "Usuario reactivado";
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "no fue posible activar el usuario";
		}
	}

	@Override
	public List<TransaccionDto> getUserProfile(long idUser, Long Usuario,
			Long Grupo) {
		List<Transaccion> txUser = userRepository.getTransaccionUser(idUser);
		if (txUser != null && !txUser.isEmpty()) {
			List<TransaccionDto> txDto = new ArrayList<TransaccionDto>();
			for (Transaccion c : txUser) {
				txDto.add(c.toDto());
			}
			return txDto;
		}

		return new ArrayList<TransaccionDto>();

	}

	@Override
	public UserDto DatallesUsuario(long idUser) {
		UserDto user = new UserDto();
		User u = this.userRepository.getById(idUser);
		user = u.toFullDto();
		return user;
	}

	@Override
	public List<UserDto> getUserInactivos(Long usuarios, Long grupo) {
		List<User> users = userRepository.getAllinactivos();
		if (users != null && !users.isEmpty()) {
			List<UserDto> usersDto = new ArrayList<UserDto>();
			for (User c : users) {
				usersDto.add(c.toDto());
			}
			return usersDto;
		}
		return new ArrayList<UserDto>();
	}

	@Override
	public UserDto getuser(long idUser) {
		UserDto user = new UserDto();
		User u = this.userRepository.getById(idUser);
		user = u.toFullDto();
		return user;
	}
}