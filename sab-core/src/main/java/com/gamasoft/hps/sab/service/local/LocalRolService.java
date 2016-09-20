package com.gamasoft.hps.sab.service.local;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Rol;
import com.gamasoft.hps.sab.dto.RolDto;
import com.gamasoft.hps.sab.dto.TransaccionDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.exception.ServiceException;
import com.gamasoft.hps.sab.repository.RolRepository;
import com.gamasoft.hps.sab.service.RolService;
/**
*
* @author wospino
*/
@Service
public class LocalRolService implements RolService{

	 private RolRepository rolRepository;

	    @Autowired
	    public LocalRolService(RolRepository rolRepository) {
	        this.rolRepository = rolRepository;
	    }
	@Override
	public List<RolDto> getRoles() {
		 List<Rol> roles=rolRepository.getAll();
	        if(roles!=null && !roles.isEmpty()){
	            List<RolDto> rolesDto=new ArrayList<RolDto>();
	            for(Rol c:roles){
	                rolesDto.add(c.toDto());
	            }
	            return rolesDto;
	        }
	  
	        return new ArrayList<RolDto>();
	}

	@Override 
	public List<RolDto> getRolesByIdCliente(long rolId) {
		 List<Rol> roles=rolRepository.getAllbyIdcliente(rolId);
	        if(roles!=null && !roles.isEmpty()){
	            List<RolDto> rolesDto=new ArrayList<RolDto>();
	            for(Rol c:roles){
	                rolesDto.add(c.toDto());
	            }
	            return rolesDto;
	        }
	  
	        return new ArrayList<RolDto>();
	}
	
	
	@Override
	public RolDto getById(long rolId, Long idgrupo) {
		if(idgrupo==1){
		RolDto dto = new RolDto();
		Rol rol =this.rolRepository.getById(rolId);
		dto=rol.toDto();
		return	dto;
	}else{
		return null;
	}
		}

	@Override
	public String add(RolDto rolDto) throws ServiceException {
		Rol c = new Rol(rolDto);
		try{		
			this.rolRepository.add(c);
			return Long.toString(c.getId());
		}catch(Exception e){
			if(e.getLocalizedMessage()=="com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException"){
				throw new ServiceException("El usuario ya existe");}
			else{
				return "el rol existe";
			}
		}		
	}

	@Override
	public String modificar(RolDto rolDto, Long rolId, Long idgrupo) {
		if(idgrupo==1){
		Rol rol = this.rolRepository.getById(rolId);
		if(rolDto.getNombre()!=null){
			rol.setName(rolDto.getNombre());
			try {
				this.rolRepository.update(rol);
				return "modificado";
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				return "no modificado";}
			}
		//preparando para modificar roles por parte de usuarios del cliente
//		else{
//				List<Rol> rol1 = this.rolRepository.getById(rolId,idgrupo);
//				if(!rol1.isEmpty()){
//				Rol r = rol1.get(0);
//				if(rolDto.getNombre()!=null){
//					r.setName(rolDto.getNombre());
//					try {
//						this.rolRepository.update(r);
//						return "modificado";
//					} catch (RepositoryException e) {
//						// TODO Auto-generated catch block
//						return "no modificado";
//					}}
//			
//				}}
		}
		return "no modificado";
		}

	@Override
	public String inactivar(Long rolId, Long idgrupo) {
		if(idgrupo==1){
		Rol rol =this.rolRepository.getById(rolId);
		rol.setActivo(false);
		try {
			this.rolRepository.update(rol);
			return "Se ha inactivado el rol";
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		return "No se inactivo rol";
	}

	@Override
	public String activar(Long rolId) {
		Rol rol =this.rolRepository.getById(rolId);
		rol.setActivo(true);
		try {
			this.rolRepository.update(rol);
			return "Se ha activado el Rol";
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "No se activo el Rol";
	}
	@Override
	@Transactional
	public List<RolDto> inactivos() {
		 List<Rol> roles=rolRepository.getInactived();
	        if(roles!=null && !roles.isEmpty()){
	            List<RolDto> rolesDto=new ArrayList<RolDto>();
	            for(Rol c:roles){
	                rolesDto.add(c.toDto());
	            }
	            return rolesDto;
	        }
	  
	        return new ArrayList<RolDto>();
	}
	@Override
	@Transactional
	public List<RolDto> getRolPorNombre(String nombre,Long cliente, Long grupo) {
		// TODO Auto-generated method stub
		List<Rol> rol = rolRepository.getByname(nombre, cliente, grupo);
		if( rol!=null && !rol.isEmpty()){
			List<RolDto> dto = new ArrayList<RolDto>();
			for(Rol r : rol){
				dto.add(r.toDto());
			}
			return dto;
		}
		return new ArrayList<RolDto>();
	}
	@Override
	public String adicionaTrasaccionRol(Long idRol, List<TransaccionDto> transac) {
		// TODO Auto-generated method stub
		this.rolRepository.agregarTxRol(idRol,transac);
		return "Rol y Permisos Guardados";
	}



}
