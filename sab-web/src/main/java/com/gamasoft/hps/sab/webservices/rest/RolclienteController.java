package com.gamasoft.hps.sab.webservices.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gamasoft.hps.sab.domain.Transaccion;
import com.gamasoft.hps.sab.domain.User;
import com.gamasoft.hps.sab.dto.RolClienteDto;
import com.gamasoft.hps.sab.dto.RolDto;
import com.gamasoft.hps.sab.dto.TransaccionDto;
import com.gamasoft.hps.sab.exception.ServiceException;
import com.gamasoft.hps.sab.repository.UserRepository;
import com.gamasoft.hps.sab.service.ServiceManager;

@Controller
@RequestMapping("/rest/rolcliente")
public class RolclienteController {

	private ServiceManager serviceManager;

    @Autowired
    public RolclienteController(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }
   
    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    
    //Listar roles en la tabla rol_cliente activos
    @RequestMapping(value= "/", method = RequestMethod.GET)
    public@ResponseBody List<RolClienteDto> listaRoles(HttpServletResponse response) throws ServiceException{
    	
    	return serviceManager.getRolclienteservice().getAll(IdUserRequestMapping("cliente"), IdUserRequestMapping("grupo"));
    }
    
    @RequestMapping(value= {"/"}, method = RequestMethod.POST)
    public @ResponseBody String crearRol(@RequestBody RolClienteDto rolDto, HttpSession session, HttpServletResponse response){
	 return serviceManager.getRolclienteservice().crearRol(rolDto);
	
    }
        
   
    //Inactiva un un rol especifico
    @RequestMapping(value= "/inactivar/{idRolcliente}", method = RequestMethod.DELETE)
    public @ResponseBody String inactivar(@PathVariable("idRolcliente") long Idrol,HttpSession session, HttpServletResponse response){
	 return serviceManager.getRolclienteservice().inactivarRolCliente(Idrol);
	
    }
    
    //Activa un rol especifico
    @RequestMapping(value= "/activar/{idRolcliente}", method = RequestMethod.PUT)
    public @ResponseBody String activar(@PathVariable("idRolcliente") long Idrol,HttpSession session, HttpServletResponse response){
	 return serviceManager.getRolclienteservice().activarRolCliente(Idrol);
	
    }
    
    //Lista los roles inactivos del cliente 
    @RequestMapping(value= "/inactivos", method = RequestMethod.GET)
    public @ResponseBody List<RolClienteDto> listaRolesInactivos(HttpServletResponse response) throws ServiceException{
	// System.out.println("rolId "+idRol + " clienteId "+idCliente);
	return  serviceManager.getRolclienteservice().getAllinactivos(IdUserRequestMapping("cliente"), IdUserRequestMapping("grupo"));
	
    }
    
    //consultar un Rol en la tabla rol_cliente
    @RequestMapping(value= "/{idrolCliente}", method = RequestMethod.GET)
    public @ResponseBody RolClienteDto consultarrol(@PathVariable("idrolCliente") long Idrol,HttpSession session, HttpServletResponse response) throws ServiceException{
	// System.out.println("rolId "+idRol + " clienteId "+idCliente);
	return serviceManager.getRolclienteservice().getRolClienteXid(Idrol);
	
    }
    
    
    //modificar los roles en la tabla rol_cliente
    @RequestMapping(value= "/{idrolCliente}", method = RequestMethod.PUT)
    public  @ResponseBody String MOdificarRol(@PathVariable("idrolCliente") long Idrol, @RequestBody RolClienteDto rolclientedto, HttpServletResponse response) throws ServiceException{
	// System.out.println("rolId "+idRol + " clienteId "+idCliente);
	return serviceManager.getRolclienteservice().modificarrol(Idrol,rolclientedto);
	
    }
    
    
    //adicionar roles a cliente (usundo una lista de roles y el id de cliente)
    @RequestMapping(value= "/eligeroles/", method = RequestMethod.POST)
    public @ResponseBody String elegirRolesCLiente(@RequestParam(value="idCliente", required=true, defaultValue="0") Long idUser, @RequestBody List<RolDto> rol, HttpServletResponse response) throws ServiceException{
	 return serviceManager.getRolclienteservice().elegirRolesUser(idUser, rol );	
    }
    
    //agrega roles a usuario
    @RequestMapping(value= "/rolesusuario/{idUser}", method = RequestMethod.POST)
    public void agregarUsersaRolcliente(@PathVariable("idUser") long idUser,HttpSession session, @RequestBody List<RolClienteDto> rol, HttpServletResponse response) throws ServiceException{
	// System.out.println("rolId "+idRol + " clienteId "+idCliente);
	 serviceManager.getRolclienteservice().adicionarolesusuario(idUser,rol);
    }
    
    //Adiciona un rol a un usuario
    @RequestMapping(value= "/addrolesuser/", method = RequestMethod.POST)
    public @ResponseBody String elegirRolCLiente(@RequestParam(value="idUser", required=true, defaultValue="0") Long idUser, @RequestBody List<RolClienteDto> rol, HttpServletResponse response) throws ServiceException{
	 return serviceManager.getRolclienteservice().elegirRolUser(idUser, rol );	
    }
     
    //agrega un rol a un usuario, enviando el id de rolCliente y el id de usuario
    @RequestMapping(value= "/agregarol", method = RequestMethod.POST)
	    public void agregarRolACliente(@RequestParam(value="idCliente", required=true, defaultValue="0") Long idCliente, @RequestParam(value="idRolCliente", required=true, defaultValue="0") Long idRol, HttpServletResponse response) throws ServiceException{
		// System.out.println("rolId "+idRol + " clienteId "+idCliente);
		 serviceManager.getRolclienteservice().adicionaRolCliente(idCliente,idRol,IdUserRequestMapping("grupo") );
		
	}
    
//    @RequestMapping(value= "/agregaroles", method = RequestMethod.POST)
//    public @ResponseBody String agregarRolesACliente(@RequestParam(value="idCliente", required=true, defaultValue="0") Long idCliente,@RequestBody List<RolDto> roldto, HttpServletResponse response) throws ServiceException{
//	// System.out.println("rolId "+idRol + " clienteId "+idCliente);
//	return  serviceManager.getRolclienteservice().adicionaRolesCliente(idCliente,roldto );
//	
//    }----Deprecared....!!!
 
    //Adiciona un usuario a un rol de un cliente
	 @RequestMapping(value= "/userrol", method = RequestMethod.POST)
	    public void agregarUseraRolcliente(@RequestParam(value="id_user", required=true, defaultValue="0") Long id_user, @RequestParam(value="id_rolcliente", required=true, defaultValue="0") Long id_rolcliente, HttpServletResponse response) throws ServiceException{
		// System.out.println("rolId "+idRol + " clienteId "+idCliente);
		 serviceManager.getRolclienteservice().adicionarUserRolcliente(id_user,id_rolcliente);
	}
	 
	 
	 
	////////////////REVISAR....///////////////// 
	 @RequestMapping(value= "/roldetails", method = RequestMethod.POST)
	    public List<Transaccion> permisosDelRol(@RequestParam(value="idRol", required=true, defaultValue="0") Long idCliente, @RequestParam(value="idRol", required=true, defaultValue="0") Long idRol, HttpServletResponse response) throws ServiceException{
		// System.out.println("rolId "+idRol + " clienteId "+idCliente);
		// serviceManager.getRolclienteservice().adicionaRolCliente(idCliente,idRol);
		return null;
	}
	
	 //Adiciona transacciones a un rol predefinido
	 @RequestMapping(value= "/addtransac", method = RequestMethod.POST)
	    public @ResponseBody String agregartransaccionesRol(@RequestParam(value="idRol", required=true, defaultValue="0")Long idRol,@RequestBody List<TransaccionDto> transac, HttpServletResponse response) throws ServiceException{
		// System.out.println("rolId "+idRol + " clienteId "+idCliente);
		 return serviceManager.getRolclienteservice().adicionaTrasaccionRol(idRol, transac);
		
	}
	 
	 //Adiciona transacciones a un rol de un cliente 
	 @RequestMapping(value= "/addtransacuser", method = RequestMethod.POST)
	    public @ResponseBody String agregartransaccionesRolUser(@RequestParam(value="idRolCliente", required=true, defaultValue="0")Long idRol,@RequestBody List<TransaccionDto> transac, HttpServletResponse response) throws ServiceException{
		// System.out.println("rolId "+idRol + " clienteId "+idCliente);
		 return serviceManager.getRolclienteservice().adicionaTrasaccionRolUser(idRol, transac);
			}
	 
	 @RequestMapping(value= "/rolusuario/{idUser}", method = RequestMethod.GET)
	    public @ResponseBody List<RolClienteDto> getRolesUsuario(@PathVariable("idUser") long idUser,HttpSession session, HttpServletResponse response){
		
		 return serviceManager.getRolclienteservice().getRolesUsuario(idUser);
		
	}
	 
	 //No esta en uso
	 @RequestMapping(value= "/transacciones", method = RequestMethod.GET)
	    public @ResponseBody List<TransaccionDto> getTransacciones() throws ServiceException{
		 return serviceManager.getRolclienteservice().getTransacciones();
		
	}
	 //Lista las transaccion es de un rol de un cliente por el id de rolCliente
	 @RequestMapping(value= "/transacciones/{idrol}", method = RequestMethod.GET)
	    public @ResponseBody List<TransaccionDto> getTransaccionesDelRol(@PathVariable("idrol") long Idrol,HttpSession session, HttpServletResponse response) throws ServiceException{
		 return serviceManager.getRolclienteservice().getTransaccionesDelRol(Idrol);
		
	}
	 
	 //Lista las transacciones de un rol del cliente 
	 @RequestMapping(value= "/transacuser/{idrolcliente}", method = RequestMethod.GET)
	    public @ResponseBody List<TransaccionDto> getTransaccionesDeRolcliente(@PathVariable("idrolcliente") long Idrol,HttpSession session, HttpServletResponse response) throws ServiceException{
		 return serviceManager.getRolclienteservice().getTransaccionesDelRolCliente(Idrol);
			}

	 
	 
	 public Long IdUserRequestMapping(String tipoid){
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();	
		 String name = auth.getName();
			User currentUser = userRepository.getUserByUsername(name);
			if(tipoid.equals("cliente")){
			return currentUser.getIdCliente();
				}	
				if(tipoid.equals("grupo")){
				return currentUser.getIdGrupo();
				}if(tipoid.equals("punto")){
						return 0L;
			}
					return null;
	    }
	 
}
