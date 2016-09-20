package com.gamasoft.hps.sab.service.local;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamasoft.hps.sab.domain.Rol;
import com.gamasoft.hps.sab.domain.RolCliente;
import com.gamasoft.hps.sab.domain.Transaccion;
import com.gamasoft.hps.sab.dto.RolClienteDto;
import com.gamasoft.hps.sab.dto.RolDto;
import com.gamasoft.hps.sab.dto.TransaccionDto;
import com.gamasoft.hps.sab.repository.ClienteRepository;
import com.gamasoft.hps.sab.repository.RolclienteRepository;
import com.gamasoft.hps.sab.repository.UserRepository;
import com.gamasoft.hps.sab.service.RolClienteService;

@Service
public class LocalRolclienteService implements RolClienteService{

		@Autowired
	    private RolclienteRepository rolclienteRepository;
		
		@Autowired
		private UserRepository userRepository;
		
		@Autowired
		private ClienteRepository clienteRepository;
		
		public RolclienteRepository getRolclienteRepository() {
			return rolclienteRepository;
		}

		public void setRolclienteRepository(RolclienteRepository rolclienteRepository, UserRepository userRepository) {
			this.rolclienteRepository = rolclienteRepository;
			this.userRepository=userRepository;
		}

	
		@Override
		public void adicionaRolCliente(Long clienteId, Long rolId, Long IdGrupo) {
			// TODO Auto-generated method stub
			if(IdGrupo!=1){
				
			this.rolclienteRepository.agregarRolCliente(clienteId,rolId);
		}else{
			this.rolclienteRepository.agregarRolCliente0(clienteId,rolId);
		}
			
		}

		
		@Override
		public void adicionaTxCliente(Long transac, Long idrolcliente) {
			this.rolclienteRepository.agregarTxcliente(transac,idrolcliente);
		}

		@Override
		public void adicionarUserRolcliente(Long id_user, Long id_rolcliente) {
			this.rolclienteRepository.agregarUserRolCliente(id_user,id_rolcliente);
		}

		@Override
		public String crearRol(RolClienteDto rolDto) {
			RolCliente r = new RolCliente(rolDto);
			r.setCliente(clienteRepository.getById(rolDto.getId_cliente()));
			try{
				this.rolclienteRepository.add(r);
				return r.getId().toString();
			}catch(Exception e){
				e.printStackTrace();
				return "no se ha podido crear el rol";
			}			
		}


		@Override
		public List<TransaccionDto> getTransacciones() {
			// TODO Auto-generated method stub
			List<Transaccion> t = this.rolclienteRepository.getTransacciones();
		        if(t!=null && !t.isEmpty()){
		            List<TransaccionDto> transacDto=new ArrayList<TransaccionDto>();
		            for(Transaccion c:t){
		                transacDto.add(c.toDto());
		            }
		            return transacDto;
		        }
		  
		        return new ArrayList<TransaccionDto>();

		}

		@Override
		public List<TransaccionDto> getTransaccionesDelRol(long idrol) {
			// TODO Auto-generated method stub
			List<Transaccion> t = this.rolclienteRepository.getTransaccionesIdRol(idrol);
			 List<TransaccionDto> transacDto=new ArrayList<TransaccionDto>();
			       for(Transaccion c:t){
	                transacDto.add(c.toDto());
	            }
	            return transacDto;
	         
		}

		@Override
		public String elegirRolUser(Long idUser, List<RolClienteDto> rol) {
			// TODO Auto-generated method stub
			try{this.rolclienteRepository.elegirRolUser(idUser, rol);
			return "Usuario y Roles Guardados";
			}
			catch(Exception e){
				return "No se Han Guardado los Cambios";
			}
		}

		@Override
		public String adicionaRolesCliente(Long idCliente, List<RolDto> roldto) {
			// TODO Auto-generated method stub
			this.rolclienteRepository.adicionaRolesCliente(idCliente, roldto);
			return "Roles Asignados Correctamente";
		}

		@Override
		public String modificarrol(Long idRolcliente,RolClienteDto rolclientedto) {
			RolCliente rc =this.rolclienteRepository.getById(idRolcliente);
			if(rolclientedto.getNombre()!=""||rolclientedto.getNombre()!=null){
			rc.setNombre(rolclientedto.getNombre());
			this.rolclienteRepository.modificarrol(rc);
			return "rol Modificado";}
			return "rol no modificado";
		}

		@Override
		public RolClienteDto getRolClienteXid(long idrol) {
			// TODO Auto-generated method stub
			try{
			RolCliente rc= this.rolclienteRepository.getById(idrol);
			RolClienteDto dto = rc.toDto();
			return dto;}catch(Exception e){
				return null;
			}
		}

		@Override
		public List<RolClienteDto> getAllinactivos(Long idCliente,Long idgrupo) {
			// TODO Auto-generated method stub
			List<RolCliente> rc = this.rolclienteRepository.getAllinactivos(idCliente);
			if(rc!=null && !rc.isEmpty()){
	            List<RolClienteDto> rcdto=new ArrayList<RolClienteDto>();
	            for(RolCliente c:rc){
	            	rcdto.add(c.toDto());
	            }
	            return rcdto;
	        }
	  
	        return new ArrayList<RolClienteDto>();
		}
		
		@Override
		public List<RolClienteDto> getAll(Long idCliente, Long idgrupo) {
			// TODO Auto-generated method stub
			List<RolCliente> rc = this.rolclienteRepository.getAllactivos(idCliente,idgrupo);
			if(rc!=null && !rc.isEmpty()){
	            List<RolClienteDto> rcdto=new ArrayList<RolClienteDto>();
	            for(RolCliente c:rc){
	            	rcdto.add(c.toDto());
	            }
	            return rcdto;
	        }
	  
	        return new ArrayList<RolClienteDto>();
		}

		@Override
		public String elegirRolesUser(Long idUser, List<RolDto> rol) {
			// TODO Auto-generated method stub
			
			return this.rolclienteRepository.elegirRolesUser(idUser, rol);
			
		}

		@Override
		public String adicionarolesusuario(long idUser, List<RolClienteDto> rol) {
			// TODO Auto-generated method stub
			
			rolclienteRepository.adicionarolesusuario(idUser,rol);
			return "Roles asignados";
		}

		@Override
		public String inactivarRolCliente(long idrol) {
			// TODO Auto-generated method stub
			try{
			RolCliente rc=this.rolclienteRepository.getById(idrol);
			rc.setActivo(false);
			this.rolclienteRepository.update(rc);
			return "Rol Inactivado";
			}catch(Exception e){
				return "No se ha Inactivado el ROl";
			}
		}

		@Override
		public String activarRolCliente(long idrol) {
			// TODO Auto-generated method stub
			try{
				RolCliente rc=this.rolclienteRepository.getById(idrol);
				rc.setActivo(true);
				this.rolclienteRepository.update(rc);
				return "Rol Activado";
				}catch(Exception e){
					return "No se ha Activado el ROl";
				}
		}

		@Override
		public String adicionaTrasaccionRol(Long idRol, List<TransaccionDto> transac) {
			// TODO Auto-generated method stub
			this.rolclienteRepository.agregarTrasaccionRol(idRol,transac);
			return "Rol y Permisos Guardados";
		}
		
		@Override
		public String adicionaTrasaccionRolUser(Long idRol,	List<TransaccionDto> transac) {
			this.rolclienteRepository.agregarTrasaccionRolUser(idRol,transac);
			return "Rol y Permisos Guardados";
		}

		@Override
		public List<TransaccionDto> getTransaccionesDelRolCliente(long idrol) {
			List<Transaccion> t = this.rolclienteRepository.getTransaccionesIdRolCliente(idrol);
	        if(t!=null && !t.isEmpty()){
	            List<TransaccionDto> transacDto=new ArrayList<TransaccionDto>();
	            for(Transaccion c:t){
	                transacDto.add(c.toDto());
	            }
	            return transacDto;
	        }
	  
	        return new ArrayList<TransaccionDto>();
		}

		@Override
		public List<RolClienteDto> getRolesUsuario(long idUser) {
			// TODO Auto-generated method stub
			List<RolCliente> roles =  this.rolclienteRepository.getRolesByID(idUser);
			if(roles!=null && !roles.isEmpty()){
	            List<RolClienteDto> rcdto=new ArrayList<RolClienteDto>();
	            for(RolCliente c:roles){
	            	rcdto.add(c.toDto());
	            }
	            return rcdto;
	        }
	  
	        return new ArrayList<RolClienteDto>();
			
		}

		

	
}
