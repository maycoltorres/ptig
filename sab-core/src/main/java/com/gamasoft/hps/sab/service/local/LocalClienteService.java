/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.service.local;
/**
*
* @author wospino
*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Canal;
import com.gamasoft.hps.sab.domain.CanalImpuesto;
import com.gamasoft.hps.sab.domain.ClaseArticulo;
import com.gamasoft.hps.sab.domain.Cliente;
import com.gamasoft.hps.sab.domain.ConfiguracionCliente;
import com.gamasoft.hps.sab.domain.GrupoArticulo;
import com.gamasoft.hps.sab.domain.GrupoEmpaque;
import com.gamasoft.hps.sab.domain.Impuesto;
import com.gamasoft.hps.sab.domain.ListaPrecios;
import com.gamasoft.hps.sab.domain.Regimen;
import com.gamasoft.hps.sab.domain.Rol;
import com.gamasoft.hps.sab.domain.Tarifa;
import com.gamasoft.hps.sab.domain.User;
import com.gamasoft.hps.sab.dto.CanalDto;
import com.gamasoft.hps.sab.dto.CanalImpuestoDto;
import com.gamasoft.hps.sab.dto.ClaseArticuloDto;
import com.gamasoft.hps.sab.dto.ClienteDto;
import com.gamasoft.hps.sab.dto.ConfiguracionClienteDto;
import com.gamasoft.hps.sab.dto.GrupoArticuloDto;
import com.gamasoft.hps.sab.dto.GrupoDto;
import com.gamasoft.hps.sab.dto.ImpuestoDto;
import com.gamasoft.hps.sab.dto.ListaPreciosDto;
import com.gamasoft.hps.sab.dto.RegimenDto;
import com.gamasoft.hps.sab.dto.RolDto;
import com.gamasoft.hps.sab.dto.UserDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.exception.ServiceException;
import com.gamasoft.hps.sab.repository.CanalImpuestoRepository;
import com.gamasoft.hps.sab.repository.CanalRepository;
import com.gamasoft.hps.sab.repository.ClaseArticuloRepository;
import com.gamasoft.hps.sab.repository.ClienteRepository;
import com.gamasoft.hps.sab.repository.ConfiguracionClienteRepository;
import com.gamasoft.hps.sab.repository.GrupoArticuloRepository;
import com.gamasoft.hps.sab.repository.GrupoRepository;
import com.gamasoft.hps.sab.repository.ImpuestoRepository;
import com.gamasoft.hps.sab.repository.ListaPreciosRepository;
import com.gamasoft.hps.sab.repository.PaisRepository;
import com.gamasoft.hps.sab.repository.RegimenRepository;
import com.gamasoft.hps.sab.repository.RolclienteRepository;
import com.gamasoft.hps.sab.repository.TarifaRepository;
import com.gamasoft.hps.sab.repository.UserRepository;
import com.gamasoft.hps.sab.service.ClienteService;
import com.gamasoft.hps.sab.utils.ListUtils;

@Service
public class LocalClienteService implements ClienteService{

 
    
    private ClienteRepository clientRepository;
    private GrupoRepository gruporepository;
    private UserRepository userRepository;
    private RolclienteRepository rolclienterepository;
    
    @Autowired
    private ListaPreciosRepository listaPreciosRepository;
    
    @Autowired
    private ClaseArticuloRepository claseArticuloRepository;
    
    @Autowired
    private GrupoArticuloRepository grupoArticuloRepository;
    
    @Autowired
    private RegimenRepository regimenRepository;
    
    @Autowired
    private CanalRepository canalRepository;
    
    @Autowired
    private CanalImpuestoRepository canalImpuestoRepository;
    
    @Autowired
    private TarifaRepository tarifaRepository;
    
    @Autowired
    private ConfiguracionClienteRepository configuracionClienteRepository;
    
    @Autowired
    private ImpuestoRepository impuestoRepository;
    
    @Autowired
    private PaisRepository paisRepository;
    
    @Autowired
    public LocalClienteService(ClienteRepository clientRepository, GrupoRepository gruporepository, UserRepository userRepository,RolclienteRepository rolclienterepository ) {
        this.clientRepository = clientRepository;
        this.gruporepository = gruporepository;
        this.userRepository=userRepository;
        this.rolclienterepository= rolclienterepository;
      
    }
    
    @Override
    public List<ClienteDto> getClients(Long idCliente, Long idGrupo) {
    	List<Cliente> clients;
    	  	if(idGrupo == null){
    		System.out.println("se consultara clientes por Cliente");
    		clients=clientRepository.getAll(idCliente);
    			return procesaListaclientes(clients);
    		}
					
    		if(idGrupo!=null){
    			System.out.println("se consultara clientes por grupo");
    			clients=clientRepository.getAllByIdGrupo(idGrupo);
    			return procesaListaclientes(clients);
    			}
    		 return new ArrayList<ClienteDto>();
            }
            
    

	@Override
	@Transactional
	public String add(ClienteDto clientDto) throws ServiceException {
		clientDto.setActivo(true);
		Cliente c = new Cliente(clientDto);
		UserDto user = new UserDto();
		
		if(userRepository.verificarcorreo(c.getEmail())){
		try{		
			this.clientRepository.add(c);
			/***agregando el cliente creado al grupo Gamasoft*/
			this.gruporepository.agregarClienteAGrupo(1, c.getId());
			/*Seteando valores de usuario a crear basandose en datos del cliente creado*/
			user.setEmail(c.getEmail());
			user.setUsername(c.getEmail());
			user.setFullName(c.getEmail());
			user.setIdCliente(c.getId());
			user.setPassword(c.getTelefono());
			user.setActivo(true);
			User u = new User(user);
			/***se crea el usuario***/
			System.out.println("se inserta en usuario username "+c.getEmail()+" contrasena "+c.getTelefono());
			userRepository.add(u);
			/***Agregando rol a cliente ***/
			Long idrolcliente= rolclienterepository.agregarRolCliente(c.getId(), (long) 1 );
			/***se adiciona el rol al cliente creado para que tenga los permisos respectivos***/
			rolclienterepository.agregarUserRolCliente(u.getId(), idrolcliente);
			/***Retorna el id del cliente creado para ser usado en la creacion de los puntos del cliente por el front***/
			return Long.toString(c.getId());
				
			}catch(Exception e){
			return e.getMessage();
			}}else {
				return "Este Email ya esta registrado";
			}
		
		}		
	

	@Override
	public String delete(Long clientId) {
		try {
			this.clientRepository.remove(clientId);
			return "Cliente modificado";
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			return "Cliente no se ha modificado";
		}
		
	}

	
	@Override
	public String update(ClienteDto clienteDto, long id, Long idClienteUser, Long idGrupo) { /*Permite la actualizacion del cliente por el id del mismo*/
		// TODO Auto-generated method stub
		Cliente cliente=this.clientRepository.getById(id);
	
		System.out.println("id a modificar "+id+" idclienteuser "+idClienteUser+" id del grupo "+idGrupo);
		if(idGrupo==0){
		try {
			cliente=validarcambioscliente(clienteDto,cliente);
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			this.clientRepository.update1(cliente);
			return "Cliente Modificado";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "Cliente no Modificado";
		}}
		if(idGrupo!=0){
		cliente=this.clientRepository.getClienteIdgrupoIdclienteUser(id,idClienteUser,idGrupo);
		try {
			cliente=validarcambioscliente(clienteDto,cliente);
			
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			System.out.println("la excepcion es "+e1.getMessage());
		}
//		String continuar =getByName(clienteDto.getNombre());
//		String nit=GetByNit(clienteDto.getNit());
		
		try {
			if(buscarporid(clienteDto.getNit(),Long.toString(id))==""){
			this.clientRepository.update1(cliente);
			return "Cliente Modificado";}else{
			return "El nit ya existe en la base de datos";
			}
//			this.clientRepository.update1(cliente);
//			return "Cliente Modificado";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(e.getCause().getCause().getLocalizedMessage().contains("nombre")){
				return "El Nombre YA Existe En la Base de Datos";
			}
			if(e.getCause().getCause().getLocalizedMessage().contains("nit")){
				return "El Nit YA Existe En la Base de Datos";
			}			}
			}else{
				
			}		
			return "No se Actualizo";
			}
		
	@Override
	public String inactiveCliente(long id,Long idClienteUser) {
		Cliente cliente =this.clientRepository.getById(id);
		cliente.setActivo(false);
		try {
			this.clientRepository.update1(cliente);
			return "Cliente Inactivado";
		} catch (Exception e) {
			return "No se Inactivo";
		}
	}

	@Override
	public String activateCliente(long clienteId) {
		// TODO Auto-generated method stub
		Cliente cliente =this.clientRepository.getById(clienteId);
		cliente.setActivo(true);
		try {
			this.clientRepository.update1(cliente);
			return "Cliente Reactivado";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "No se pudo Reactivar";
		}
	}

	  @Override
	    public List<ClienteDto> getClientsInactivos(Long idCliente, Long Idgrupouser) {
	        List<Cliente> clients=clientRepository.getAllInactivos(idCliente, Idgrupouser);
	        if(clients!=null && !clients.isEmpty()){
	            List<ClienteDto> clientsDto=new ArrayList<ClienteDto>();
	            for(Cliente c:clients){
	                clientsDto.add(c.toDto());
	            }
	            return clientsDto;
	        }
	  
	        return new ArrayList<ClienteDto>();
	    }

	public String GetByNit(String id){
		List<Cliente> cliente =this.clientRepository.getBYNIt(id);
		if(cliente!=null && !cliente.isEmpty()){
		return "El Nit existe en la base de datos";
		}else{
			return "Continuar";
		}
		}
	
	public String buscarporid(String id, String nit){
		List<Cliente> c = clientRepository.buscarporIdNit(id,nit);
		if(c.isEmpty()){
			return "";
		}
		else{
			return "Cliente ya Existe en La Base de Datos";
		}
		
	}
	public ClienteDto getByName(String name){
		List<Cliente> cliente= this.clientRepository.getByName(name);
		if(cliente != null && !cliente.isEmpty()){
			return cliente.get(0).toDto();
		}else{
			return null;
		}
	}

	@Override
	public List<ClienteDto> getClientesProNombre(String nombre) {
		// TODO Auto-generated method stub
		List<Cliente> clients=clientRepository.getPorNombre(nombre);
        if(clients!=null && !clients.isEmpty()){
            List<ClienteDto> clientsDto=new ArrayList<ClienteDto>();
            for(Cliente c:clients){
                clientsDto.add(c.toDto());
            }
            return clientsDto;
        }
  
        return new ArrayList<ClienteDto>();
	}

	@Override
	public List<ClienteDto> getClienteNombreAnyWord(String nombre, Long idclienteuser, Long idgrupo, Long idPunto) {
		// TODO Auto-generated method stub
				List<Cliente> clientes = clientRepository.getClienteByAnyWord(nombre, idclienteuser, idgrupo, idPunto);
				if(clientes !=null && !clientes.isEmpty()){
					List<ClienteDto> dto = new ArrayList<ClienteDto>();
					for(Cliente c : clientes){
						dto.add(c.toDto());
					}
					return dto;
				}
				return new ArrayList<ClienteDto>();
	}

	
	
	
	@Override
	public String adicionaRol(List<RolDto> clienteDto, long clienteid) {
		try{
		this.clientRepository.agregarRolaCliente(clienteDto,clienteid);
		return "rol agregado";}
		catch (Exception E){
			return "No se pudo agregar el rol";
		}
	}

	@Override
	public List<RolDto> getRolesCliente(long clienteId) {
		// TODO Auto-generated method stub
		List<Rol> roles=clientRepository.getRolesCliente(clienteId);
        if(roles!=null && !roles.isEmpty()){
            List<RolDto> rolesDto=new ArrayList<RolDto>();
            for(Rol c:roles){
                rolesDto.add(c.toDto());
            }
            return rolesDto;
        }
  
        return new ArrayList<RolDto>();
	}

	public List<ClienteDto> procesaListaclientes(List<Cliente> clients){
		List<ClienteDto> clientsDto;	
		if(clients!=null && !clients.isEmpty()){
			clientsDto=new ArrayList<ClienteDto>();
				for(Cliente c:clients){
					clientsDto.add(c.toDto());			
					}
				return clientsDto;
		}
			return new ArrayList<ClienteDto>();
	}

	

	@Override
	@Transactional
	public ClienteDto getById(long id,Long idClienteuser, Long idGrupo) {
		// TODO Auto-generated method stub
		if(idGrupo==0){
		try{if(idClienteuser==id){
			List<Cliente> cliente =this.clientRepository.getByIdCliente(id);
			List<ClienteDto> dto = procesaListaclientes(cliente);
			return dto.get(0); }
		}catch(Exception e){
			return null;
		}}if(idGrupo!=0){
			try{List<Cliente> cliente = this.clientRepository.getbyidAndGrupo(id,idGrupo);
			List<ClienteDto> dto = procesaListaclientes(cliente);
			return dto.get(0);
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
		return null;
	}
	
	public Cliente validarcambioscliente(ClienteDto clienteDto, Cliente cliente ) throws ServiceException{
		
		//validaciones de campos
		
		if(clienteDto.getNit().length()<5&&clienteDto.getNit().length()>15){
			throw new ServiceException ("El campo Nit debe tener entre 5 y 15");
		}
		if(Integer.toString(clienteDto.getDigitoChequeo()).length()>1||clienteDto.getDigitoChequeo()==0){
			throw new ServiceException ("El digito de chequeo es de un Caracter y no puede estar vacio");
		}
		
		if(Integer.toString(clienteDto.getExtension()).length()<1 || Integer.toString(clienteDto.getExtension()).length()>4){
			throw new ServiceException ("La extension debe tener entre 1 y 4 caracteres");
		}
		if(clienteDto.getEmail().length()==0||clienteDto.getEmail()==null){
			throw new ServiceException ("El email debe tener entre 5 y 50 caracteres");
		}
	
		
		if(clienteDto.getDocumentoRepresentante().length()<5||clienteDto.getDocumentoRepresentante().length()>15){
			throw new ServiceException ("El Documento del representanrte debe tener entre 5 y 15 caracteres");
			
		}
		
		if(clienteDto.getNombreRepresentante().length()<3|| clienteDto.getNombreRepresentante().length()>100){
			throw new ServiceException ("El nombre de representante debe tener entre 3 y 100 caracteres");
		}
		
		if(clienteDto.getNombreDueno().length()<3||clienteDto.getNombreDueno().length()>100){
			throw new ServiceException ("El nombre del dueño debe tener entre 3 y 100 caracteres");
		}
		
		if(clienteDto.getEmail().length()<5||clienteDto.getEmail().length()>50){
			throw new ServiceException ("El email debe tener entre 5 y 50 caracteres");
		}
		
		
		
		//asignacion de valores del dto a la base de datos
		
		cliente.setCelularDueno(clienteDto.getCelularDueno());
		cliente.setNombreDueno(clienteDto.getNombreDueno());
		cliente.setExtension(clienteDto.getExtension());
		cliente.setDigitoChequeo(clienteDto.getDigitoChequeo());
		if(clienteDto.getEmail().length()>0&&clienteDto.getEmail().length()<51){
			cliente.setEmail(clienteDto.getEmail());
		}
		
		if (clienteDto.getNombre()!= null) {
			cliente.setNombre(clienteDto.getNombre());
		}
		
		if(clienteDto.getNit() != null ){
			cliente.setNit(clienteDto.getNit());
		}
		
		
		if (clienteDto.getDigitoChequeo() != 0){
			cliente.setDigitoChequeo(clienteDto.getDigitoChequeo());
		}
		
		if(clienteDto.getDireccion() != null){
			cliente.setDireccion(clienteDto.getDireccion());
		}
		if(clienteDto.getMunicipio()!=0){
			cliente.setMunicipio(clienteDto.getMunicipio());
		}
		
		if (clienteDto.getTelefono().length() != 0){
			cliente.setTelefono(clienteDto.getTelefono());
		}
		if(clienteDto.getExtension() != 0){
			cliente.setExtension(clienteDto.getExtension());
		}
		
//		if (clienteDto.getTelefono2() != 0){
//			cliente.setTelefono2(clienteDto.getTelefono2());
//		}
//		if(clienteDto.getExtension2() != 0){
//			cliente.setTelefono2(clienteDto.getExtension2());
//		}
		
		
		if(clienteDto.getCelular() !=null){
			cliente.setCelular(clienteDto.getCelular());
		}
		if(clienteDto.getRegimen_id() != null){
			cliente.setRegimen_id(clienteDto.getRegimen_id());
		}
		
//		if(clienteDto.getContactoDnegocio()!=null){
//			cliente.setContactoDnegocio(clienteDto.getContactoDnegocio());
//		}
//		if(clienteDto.getContactoCargo()!=null){
//			cliente.setContactoCargo(clienteDto.getContactoCargo());
//		}
//		if(clienteDto.getContactoMail()!=null){
//			cliente.setContactoMail(clienteDto.getContactoMail());
//		}
//		if(clienteDto.getContactoCelular()!=null){
//			cliente.setContactoCelular(clienteDto.getContactoCelular());
//		}
//		
		
		if(clienteDto.getNombreRepresentante()!=null){
			cliente.setNombreRepresentante(clienteDto.getNombreRepresentante());
		}
		if(clienteDto.getDocumentoRepresentante()!=null&&clienteDto.getDocumentoRepresentante().length()>=5&&clienteDto.getDocumentoRepresentante().length()<=15){
			cliente.setDocumentoRepresentante(clienteDto.getDocumentoRepresentante());
		}
		if(clienteDto.getNombreDueno()!=null){
			cliente.setNombreDueno(clienteDto.getNombreDueno());
		}
		
		
		
		
		if(clienteDto.getCelularDueno()!=null){
			cliente.setCelularDueno(clienteDto.getCelularDueno());
		}
		if(clienteDto.getMailDueno()!=null){
			cliente.setMailDueno(clienteDto.getMailDueno());
		}
//		
//		if(clienteDto.getNombreDueno1()!=null){
//			cliente.setNombreDueno1(clienteDto.getNombreDueno1());
//		}
//		if(clienteDto.getCelularDueno1()!=null){
//			cliente.setCelularDueno1(clienteDto.getCelularDueno1());
//		}
//		if(clienteDto.getMailDueno1()!=null){
//			cliente.setMailDueno1(clienteDto.getMailDueno1());
//		}
//		
//		if(clienteDto.getNombreDueno2()!=null){
//			cliente.setNombreDueno2(clienteDto.getNombreDueno2());
//		}
//		if(clienteDto.getCelularDueno2()!=null){
//			cliente.setCelularDueno2(clienteDto.getCelularDueno2());
//		}
//		if(clienteDto.getMailDueno2()!=null){
//			cliente.setMailDueno2(clienteDto.getMailDueno2());
//		}
//		
		return cliente;
	}

	@Override
	public Cliente getbyid(Long id) {
			return clientRepository.getById(id);
	}
	
	@Override
	@Transactional
	public List<RegimenDto> getRegimenes(Long idCliente) {
		List<RegimenDto> regimenesDto = new ArrayList<RegimenDto>();
		Set<Regimen> regimenes = clientRepository.getRegimenesByCliente(idCliente);
		
		for(Regimen r : regimenes){
			RegimenDto rDto = r.toDto();
			
			if(r.getImpuesto() != null){
				rDto.setIdImpuesto(r.getImpuesto().getId());
				rDto.setImpuesto(r.getImpuesto().getNombre());
				
				Long idPais = impuestoRepository.getById(r.getImpuesto().getId()).getIdPais();
				
				rDto.setIdPais(idPais);
				rDto.setPais(paisRepository.getById(idPais).getNombre());
			}
			
			regimenesDto.add(rDto);
		}
		
		return regimenesDto;
	}

	@Override
	@Transactional
	public String adicionaRegimen(List<RegimenDto> regimenesDto, long idCliente) {
		Set<Regimen> regimenes = new HashSet<Regimen>();
		
		for(RegimenDto r : regimenesDto){
			Regimen regimen = regimenRepository.getById(r.getId());
			regimenes.add(regimen);
		}
		clientRepository.agregarRegimenes(regimenes, idCliente);
		
		return "OK";
	}

	@Override
	@Transactional
	public String eliminaRegimen(Long idCliente, Long idRegimen) {
		clientRepository.eliminarRegimen(idCliente, idRegimen);
		return "OK";
	}

	@Override
	public List<CanalImpuestoDto> getCanalImpuesto(Long idCliente) {
		List<CanalImpuestoDto> canalesImpuestosDto = new ArrayList<CanalImpuestoDto>();
		Set<CanalImpuesto> canalesImpuestos = clientRepository.getCanalImpuestoByCliente(idCliente);
		
		List<Impuesto> impuestos = impuestoRepository.getAll();
		
		for(CanalImpuesto i : canalesImpuestos){
			CanalImpuestoDto dto = i.toDto();
			canalesImpuestosDto.add(dto);
		}
		
		return canalesImpuestosDto;
	}

	@Override
	public String adicionarCanalImpuesto(Long idCliente, CanalImpuestoDto canalImpuestoDto) {
		Cliente cliente = clientRepository.getById(idCliente);
		
		CanalImpuesto canalImpuesto = new CanalImpuesto(canalImpuestoDto);
		canalImpuesto.setCliente(cliente);
		
		if(canalImpuestoDto.getIdCanal() != null){
			Canal canal = canalRepository.getById(canalImpuestoDto.getIdCanal());
			canalImpuesto.setCanal(canal);
		}
		
		if(canalImpuestoDto.getIdRegimen() != null){
			Regimen regimen = regimenRepository.getById(canalImpuestoDto.getIdRegimen());
			canalImpuesto.setRegimen(regimen);
		}
		
		if(canalImpuestoDto.getIdTarifa() != null){
			Tarifa tarifa = tarifaRepository.getById(canalImpuestoDto.getIdTarifa());
			if(tarifa != null){
				canalImpuesto.setTarifa(tarifa);
			}
		}
		
		canalImpuestoRepository.add(canalImpuesto);

		return "OK";
	}
	
	@Override
	public String actualizarCanalImpuesto(CanalImpuestoDto canalImpuestoDto) {
		CanalImpuesto canalImpuesto = canalImpuestoRepository.getById(canalImpuestoDto.getId());
		if(canalImpuesto == null){
			return "No existe el canal impuesto con id " + canalImpuestoDto.getId();
		}
		
		if(canalImpuestoDto.getIdCanal() != null){
			Canal canal = canalRepository.getById(canalImpuestoDto.getIdCanal());
			canalImpuesto.setCanal(canal);
		}
		
		if(canalImpuestoDto.getIdRegimen() != null){
			Regimen regimen = regimenRepository.getById(canalImpuestoDto.getIdRegimen());
			canalImpuesto.setRegimen(regimen);
		}
		
		
		if(canalImpuestoDto.getIdTarifa() != null){
			Tarifa tarifa = tarifaRepository.getById(canalImpuestoDto.getIdTarifa());
			if(tarifa != null){
				canalImpuesto.setTarifa(tarifa);
			}
		}else{
			canalImpuesto.setTarifa(null);
		}
		
		try {
			canalImpuestoRepository.update(canalImpuesto);
		} catch (RepositoryException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String eliminarCanalImpuesto(Long idCanalImpuesto) {
		
		try {
			canalImpuestoRepository.remove(idCanalImpuesto);
		} catch (RepositoryException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		return "OK";
	}

	@Override
	public List<ListaPreciosDto> getListasPreciosPorCliente(Long idCliente, Boolean activo) {
		List<ListaPreciosDto> listaDto = new ArrayList<ListaPreciosDto>();
		List<ListaPrecios> lista = listaPreciosRepository.getByCliente(idCliente, activo);
		
		for(ListaPrecios l : lista){
			listaDto.add(l.toDto());
		}
		
		return listaDto;
	}

	@Override
	public Set<CanalDto> getCanalesPorCliente(Long idCliente) {
		Set<Canal> canales = clientRepository.getCanalesByCliente(idCliente);
		Set<CanalDto> canalesDto = new HashSet<CanalDto>();
		
		for(Canal c : canales){
			CanalDto cDto = c.toDto();
			canalesDto.add(cDto);
		}
		
		return canalesDto;
	}

	@Override
	public String definirCanales(Long idCliente, List<CanalDto> canalesDto) {
		Set<Canal> canales = new HashSet<Canal>();
		for(CanalDto c : canalesDto){
			Canal canal = canalRepository.getById(c.getId());
			canales.add(canal);
		}
		
		clientRepository.agregarCanales(idCliente, canales, true);
		
		return "OK";
	}
	
	@Override
	public String adicionarCanales(Long idCliente, List<CanalDto> canalesDto) {
		Set<Canal> canales = new HashSet<Canal>();
		for(CanalDto c : canalesDto){
			Canal canal = canalRepository.getById(c.getId());
			canales.add(canal);
		}
		
		clientRepository.agregarCanales(idCliente, canales, false);
		
		return "OK";
	}

	@Override
	public String eliminarCanal(Long idCliente, Long idCanal) {
		clientRepository.eliminarCanal(idCliente, idCanal);
		return "OK";
	}

	@Override
	public ConfiguracionClienteDto getConfiguracionCliente(Long idCliente) {
		ConfiguracionCliente conf = configuracionClienteRepository.getByCliente(idCliente);
		
		if(conf != null){
			return conf.toDto();
		}
		
		return null;
	}

	@Override
	public String definirConfiguracionCliente(Long idCliente,
			ConfiguracionClienteDto confClienteDto) {
		ConfiguracionCliente conf = configuracionClienteRepository.getByCliente(idCliente);
		if(conf == null){
			Cliente c = clientRepository.getById(idCliente);
			conf = new ConfiguracionCliente(confClienteDto);
			conf.setCliente(c);
			configuracionClienteRepository.add(conf);
		}else{
			conf.setRecetaDiferenciaXPunto(confClienteDto.getRecetaDiferencialXPunto());
			conf.setRecetaDiferencialXCanal(confClienteDto.getRecetaDiferencialXCanal());
			conf.setEmpaqueDiferencialXPunto(confClienteDto.getEmpaqueDiferencialXPunto());
			conf.setEmpaqueDiferencialXCanal(confClienteDto.getEmpaqueDiferencialXCanal());
			conf.setUnicoImpuestoXCanales(confClienteDto.getUnicoImpuestoXCanales());
			
			try {
				configuracionClienteRepository.update(conf);
			} catch (RepositoryException e) {
				e.printStackTrace();
				return "La configuracion no pudo ser actualizada";
			}
		}
		return "OK";
	}

	@Override
	public String adicionarListaPrecios(Long idCliente, ListaPreciosDto lista) {
		ListaPrecios l = listaPreciosRepository.getByClienteYNombre(idCliente, lista.getNombre());
		if(l!=null){
			return "Ya existe una lista de precios con el nombre " + lista.getNombre();
		}
		
		ListaPrecios nuevaLista = new ListaPrecios();
		nuevaLista.setCliente(clientRepository.getById(idCliente));
		nuevaLista.setNombre(lista.getNombre());
		nuevaLista.setActivo(true);
		
		listaPreciosRepository.add(nuevaLista);
		
		return "OK";
	}

	@Override
	public String actualizarListaPrecios(ListaPreciosDto lista) {
		ListaPrecios l = listaPreciosRepository.getById(lista.getId());
		if(l == null){
			return "No existe la lista de precios a modificar";
		} else{
			ListaPrecios listaPorNombre = listaPreciosRepository.getByClienteYNombre(lista.getIdCliente(), lista.getNombre());
			if(listaPorNombre != null && !listaPorNombre.getId().equals(l.getId())){
				return "Ya existe una lista de precios con el nombre " + lista.getNombre();
			} else{
				l.setNombre(lista.getNombre());
				try {
					listaPreciosRepository.update(l);
				} catch (RepositoryException e) {
					e.printStackTrace();
					return "No fue posible actualizar la lista";
				}
			}
		}
		
		return "Lista de precios actualizada";
	}

	@Override
	public String eliminarListaPrecios(ListaPreciosDto lista) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inactivarListaPrecios(Long id) {
		ListaPrecios lista = listaPreciosRepository.getById(id);
		lista.setActivo(false);
		try {
			listaPreciosRepository.update(lista);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void activarListaPrecios(Long id) {
		ListaPrecios lista = listaPreciosRepository.getById(id);
		lista.setActivo(true);
		try {
			listaPreciosRepository.update(lista);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<ClaseArticuloDto> getClaseArticuloByCliente(Long idCliente, Boolean venta, Boolean inventario, Boolean activo) {
		List<ClaseArticuloDto> result = new ArrayList<ClaseArticuloDto>();
		List<ClaseArticulo> clases = claseArticuloRepository.getAllByCliente(idCliente, venta, inventario, activo);
		
		for(ClaseArticulo clase : clases){
			ClaseArticuloDto dto = clase.toDto();
			result.add(dto);
		}
		
		return result;
	}

	@Override
	public List<GrupoArticuloDto> getGrupoArticuloByClase(Long idClase) {
		List<GrupoArticuloDto> result = new ArrayList<GrupoArticuloDto>();
		List<GrupoArticulo> grupos = grupoArticuloRepository.getAllByClase(idClase);
		
		for(GrupoArticulo grupo : grupos){
			GrupoArticuloDto dto = grupo.toDto();
			result.add(dto);
		}
		
		return result;
	}

	
	@Override
	public String agregarClaseArticulo(ClaseArticuloDto claseDto) {
		ClaseArticulo claseExistente = claseArticuloRepository.getByClienteYNombre(claseDto.getIdCliente(), claseDto.getNombre());
		if(claseExistente != null){
			return "Error, el nombre de la clase ya existe";
		}
		
		ClaseArticulo clase = new ClaseArticulo(claseDto);
		clase.setActivo(true);
		clase.setIdCliente(claseDto.getIdCliente());
		
		Set<GrupoArticulo> grupos = new HashSet<GrupoArticulo>();
		for(GrupoArticuloDto grupoDto : claseDto.getGrupos()){
			GrupoArticulo grupo = new GrupoArticulo(grupoDto);
			grupo.setClaseArticulo(clase);
			grupos.add(grupo);
		}
		clase.setGrupos(grupos);
		
		claseArticuloRepository.add(clase);
		return "OK";
	}

	@Override
	public String actualizarClaseArticulo(ClaseArticuloDto claseDto) {
		ClaseArticulo claseExistente = claseArticuloRepository.getByClienteYNombre(claseDto.getIdCliente(), claseDto.getNombre());
		if(claseExistente != null && claseExistente.getId() != claseDto.getId()){
			return "Error, el nombre de la clase ya existe";
		}
		
		if(claseDto.getGrupos()!= null && ListUtils.hasDuplicate(claseDto.getGrupos())){
			return "Error, el nombre del grupo debe ser unico";
		}
		
		ClaseArticulo clase = claseArticuloRepository.getById(claseDto.getId());
		clase.setNombre(claseDto.getNombre());
		clase.setInventario(claseDto.getInventario());
		clase.setVenta(claseDto.getVenta());
		
		if(claseDto.getGrupos() != null){
			Map<Long, GrupoArticuloDto> map = new HashMap<Long, GrupoArticuloDto>();
			Set<GrupoArticulo> nuevosGrupos = new HashSet<GrupoArticulo>();
			
			for(GrupoArticuloDto grupoDto : claseDto.getGrupos()){
				if(grupoDto.getId() == null || grupoDto.getId() == 0){
					GrupoArticulo nuevoGrupo = new GrupoArticulo(grupoDto);
					nuevoGrupo.setClaseArticulo(clase);
					nuevosGrupos.add(nuevoGrupo);
				}else{
					map.put(grupoDto.getId(), grupoDto);
				}
			}
			
			for(Iterator<GrupoArticulo> grupoIt = clase.getGrupos().iterator(); grupoIt.hasNext();){
				GrupoArticulo grupo = grupoIt.next();
				GrupoArticuloDto grupoDto = map.get(grupo.getId());
				
				if(grupoDto == null){
					grupoIt.remove();
				}else{
					grupo.setNombre(grupoDto.getNombre());
				}
			}
			
			clase.getGrupos().addAll(nuevosGrupos);
			
		}
		
		try {
			claseArticuloRepository.update(clase);
		} catch (RepositoryException e) {
			e.printStackTrace();
			return "Error";
		}
		return "OK";
	}

	@Override
	public String activarClaseArticulo(Long idClaseArticulo, Boolean activo) {
		ClaseArticulo clase = claseArticuloRepository.getById(idClaseArticulo);
		clase.setActivo(activo);

		try {
			claseArticuloRepository.update(clase);
		} catch (RepositoryException e) {
			e.printStackTrace();
			return "Error";
		}
		return "OK";
	}

	@Override
	public ClaseArticuloDto getClaseArticuloById(Long idClase) {
		ClaseArticulo clase = claseArticuloRepository.getById(idClase);
		if(clase!=null){
			return clase.toDto();
		}
		return null;
	}
		
}
