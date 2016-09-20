/*
 * Las plantillas de HIbernate no se usan mas porque Spring no las recomienda y no seran soportadas en las nuevas versiones (wospino)
 * 
 */
package com.gamasoft.hps.sab.repository.hibernate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;

import org.apache.commons.lang.Validate;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Canal;
import com.gamasoft.hps.sab.domain.CanalImpuesto;
import com.gamasoft.hps.sab.domain.Cliente;
import com.gamasoft.hps.sab.domain.Grupo;
import com.gamasoft.hps.sab.domain.Impuesto;
import com.gamasoft.hps.sab.domain.Regimen;
import com.gamasoft.hps.sab.domain.Rol;
import com.gamasoft.hps.sab.domain.RolCliente;
import com.gamasoft.hps.sab.dto.CanalImpuestoDto;
import com.gamasoft.hps.sab.dto.RolDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.ClienteRepository;

/**
 *
 * @author wospino
 */

@Repository
public class HibernateClienteRepository extends HibernateRepository implements ClienteRepository {

    @Autowired
    public HibernateClienteRepository(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
    private boolean Activo=true;
    @Column(name ="Activo")
    @Transactional
    public void add(Cliente client) {
        Validate.notNull(client, "The client cannot be null");
        client.setActivo(Activo);
        try{
        	getCurrentSession().saveOrUpdate(client);
        
        	}
        catch(Exception e) {
             	
        	        	
        } ;
    }


    	
    @Transactional
    public String update1(Cliente client)  {
       Validate.notNull(client, "The client cannot be null");
      try{
    	 getCurrentSession().saveOrUpdate(client);
    	return "Cliente Actualizado"; 
      }
      catch(Exception e){
    	 return "Este Nit ya Existe ";
      }
    }
    
    @Transactional
    public void remove(long id) throws RepositoryException {
        Cliente client=(Cliente) getCurrentSession().get(Cliente.class, id);
        if(client!=null){
        	 getCurrentSession().delete(client);
        }
    }
    
    @Transactional
    public Cliente getById(long id) {
    	Cliente c =(Cliente)getCurrentSession().load(Cliente.class, id);
    	System.out.println("nit cliente"+c.getNit());
    	return c;		
    }
    
    @Override
	public List<Cliente> getByIdCliente(long id) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Cliente> clients =getCurrentSession().createQuery("from Cliente c join fetch c.grupos grupos join fetch grupos.clientes where c.id='"+id+"' and c.activo=1" ).list();
        return clients;
	}
    
    
	@Override
	public List<Cliente> getbyidAndGrupo(long id, Long idGrupo) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Cliente> clients =getCurrentSession().createQuery("from Cliente c join fetch c.grupos grupos where grupos.id='"+idGrupo+"' and c.id="+id+" and c.activo=1" ).list();
    	if(clients != null){
    	return clients;}
    	else {return null;}
	}


    @Transactional
    public List<Cliente> getAll(long idCliente) {
        @SuppressWarnings("unchecked")
		List<Cliente> clients =getCurrentSession().createQuery("from Cliente where activo=true" ).list();
        return clients;
    }
    
    @Override
	@Transactional
	@SuppressWarnings("unchecked")
    public List<Cliente> getAllByIdGrupo(Long idGrupo) {
    	Grupo g =(Grupo) getCurrentSession().load(Grupo.class, idGrupo);
    	List<Cliente> clients = new ArrayList<Cliente>();
    	for(Cliente c : g.getClientes()){
    		if(c.isActivo()){
    			clients.add(c);
    		}
    	}
        return clients;
	}
    

    @Transactional
    public void update(Cliente cliente, long id) {
		// TODO Auto-generated method stub
			
    	 getCurrentSession().saveOrUpdate(cliente);
		
    }
    @Transactional
    public List<Cliente> getByName(String name){
    	
    	@SuppressWarnings("unchecked")
		List<Cliente> client =getCurrentSession().createQuery("from Cliente where nombre='"+name+"'").list();
    	return client;
    }


	@Override
	@Transactional
	public List<Cliente> getAllInactivos(Long idCliente, Long idGrupouser) {
		@SuppressWarnings("unchecked")
		List<Cliente> clients =getCurrentSession().createQuery("select c from Cliente c, Grupo g where c.activo=0 and g.id='"+idGrupouser+"'").list();
		
        return clients;
	}




	@Override
	@Transactional
	public List<Cliente> getBYNIt(String nit) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Cliente> client =getCurrentSession().createQuery("from Cliente where nit='"+nit+"'").list();
    	return client;
	}



	@Override
	@Transactional
	public List<Cliente> getPorNombre(String nombre) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Cliente> client =getCurrentSession().createQuery("from Cliente where nombre like '"+nombre+"%"+"'"+"and activo=1").list();
		return client;
	}



	@Override
	@Transactional
	public List<Cliente> getClienteByAnyWord(String nombre, Long idcliente, Long idGrupo, Long idPunto) {
		// TODO Auto-generated method stub
				@SuppressWarnings("unchecked")
				List<Cliente> cliente =getCurrentSession().createQuery("select clientes from  Grupo g join g.clientes clientes where clientes.nombre like '"+nombre+"%"+"'"+"and g.id="+idGrupo+" and clientes.activo=1").list();

				return cliente;
	}



	@Override
	public void agregarRolaCliente(List<RolDto> clienteDto, long rolId) {
		RolCliente rol = (RolCliente) getCurrentSession().get(Rol.class, rolId);
		System.out.println("El clienteDto tiene un tamaÃ±o de   "+clienteDto.size());
		  for(RolDto c : clienteDto){
		          Cliente cliente = (Cliente)getCurrentSession().get(Cliente.class, c.getId());
		          System.out.println("El cleinte es  "+cliente.getNombre());
		          cliente.getRoles().add(rol); //revisar
		         
		  }
		  getCurrentSession().saveOrUpdate(rol);
	}
	
	@Override
	@Transactional
	public void agregarRegimenes(Set<Regimen> regimenes, long clienteId){
		Cliente c = getById(clienteId);
		if(c.getRegimenes() == null){
			c.setRegimenes(regimenes);
		}else{
			c.getRegimenes().addAll(regimenes);
		}
		
		getCurrentSession().saveOrUpdate(c);
	}



	@Override
	@Transactional
	public List<Rol> getRolesCliente(long clienteId) {
		@SuppressWarnings("unchecked")
		List<Rol> rol = getCurrentSession().createQuery("from Rol r join fetch r.rolcliente rolcliente join fetch rolcliente.cliente cliente where cliente.id = '" + clienteId + "'").list();
		System.out.println();
		return rol;
	}



	@Override
	public List<Cliente> getAll() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	@Transactional
	public List<Cliente> getAll(Long idCliente) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Cliente> client =getCurrentSession().createQuery("from Cliente where activo=true and id='"+idCliente+"'").list();
    	return client;
	}



	@Override
	@Transactional
	public Cliente getClienteIdgrupoIdclienteUser(long id, Long idClienteUser,Long idGrupo) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Cliente> client =getCurrentSession().createQuery("from Cliente c join fetch c.grupos grupos join fetch grupos.clientes where grupos.id='"+idGrupo+"' and c.id='"+id+"'").list();
		if(client!=null && !client.isEmpty()){
			Cliente c =(Cliente)getCurrentSession().load(Cliente.class, id);
			return c;
		}else{

		return null;
	}
		}



	@Override
	public void update(Cliente t) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}



	@Override
	@Transactional
	public List<Cliente> buscarporIdNit(String id, String nit) {
		@SuppressWarnings("unchecked")
		List<Cliente> c = getCurrentSession().createQuery("from Cliente where nit="+nit+" and id <>'+id+'").list();
		System.out.println("cliente esta vacio? "+c.isEmpty());
		return c;
	}



	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public Set<Regimen> getRegimenesByCliente(long clienteId) {
		Cliente c = getById(clienteId);
		Hibernate.initialize(c.getRegimenes());
		for(Regimen r : c.getRegimenes()){
			Hibernate.initialize(r.getImpuesto());
		}
		
		return c.getRegimenes();
	}



	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public String eliminarRegimen(long clienteId, long regimenId) {
		Cliente c = getById(clienteId);
		Hibernate.initialize(c.getRegimenes());
		
		for(Regimen r : c.getRegimenes()){
			if(r.getId().equals(regimenId)){
				c.getRegimenes().remove(r);
			}
		}
		getCurrentSession().saveOrUpdate(c);
		
		return "OK";
	}



	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public Set<CanalImpuesto> getCanalImpuestoByCliente(Long clienteId) {
		Cliente c = getById(clienteId);
		Hibernate.initialize(c.getCanalesImpuestos());
		for(CanalImpuesto canalImp : c.getCanalesImpuestos()){
			Hibernate.initialize(canalImp.getRegimen().getImpuesto());
		}
		
		return c.getCanalesImpuestos();
	}



	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public void definirCanalImpuesto(Long clienteId, Set<CanalImpuesto> canalesImpuestos) {
		Cliente c = getById(clienteId);
		c.setCanalesImpuestos(canalesImpuestos);
		getCurrentSession().saveOrUpdate(c);
	}
	
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public void agregarCanalImpuesto(Long clienteId, Set<CanalImpuesto> canalesImpuestos) {
		Cliente c = getById(clienteId);
		if(c.getCanalesImpuestos()==null){
			c.setCanalesImpuestos(canalesImpuestos);
		}else{
			c.getCanalesImpuestos().addAll(canalesImpuestos);
		}
		
		getCurrentSession().saveOrUpdate(c);
	}



	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public String eliminarCanalImpuesto(Long idCliente, Long idCanalImpuesto) {
		Cliente c = getById(idCliente);
		Set<CanalImpuesto> canalesImpuestos = c.getCanalesImpuestos();
		
		for(CanalImpuesto ci : canalesImpuestos){
			if(ci.getId().equals(idCanalImpuesto)){
				canalesImpuestos.remove(ci);
			}
		}
		getCurrentSession().saveOrUpdate(c);
		
		return "OK";
	}



	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public Set<Canal> getCanalesByCliente(Long idCliente) {
		Cliente c = getById(idCliente);
		Hibernate.initialize(c.getCanales());
		
		return c.getCanales();
	}



	@Override
	@Transactional
	public void agregarCanales(Long idCliente, Set<Canal> canales, boolean reemplazar) {
		Cliente c = getById(idCliente);
		if(reemplazar){
			c.setCanales(canales);
		}else{
			if(c.getCanales() == null){
				c.setCanales(canales);
			}else{
				c.getCanales().addAll(canales);
			}
		}
		
		getCurrentSession().saveOrUpdate(c);
	}



	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public void eliminarCanal(Long idCliente, Long idCanal) {
		Cliente c = getById(idCliente);
		for(Canal canal : c.getCanales()){
			if(canal.getId().equals(idCanal)){
				c.getCanales().remove(canal);
			}
		}
		getCurrentSession().saveOrUpdate(c);
	}



	
}
