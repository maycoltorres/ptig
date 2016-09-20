/*
 * La plantillas de Hibernate no se usan mas porque Sporing ya no las recomienda y dejaran de ser soportadas (wospino)
 * 
 */
package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import javax.persistence.Column;

import org.apache.commons.lang.Validate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Bodega;
import com.gamasoft.hps.sab.domain.Marca;
import com.gamasoft.hps.sab.domain.Punto;
import com.gamasoft.hps.sab.dto.BodegaDto;
import com.gamasoft.hps.sab.dto.MarcaDto;
import com.gamasoft.hps.sab.dto.PuntoDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.PuntoRepository;

/**
 *
 * @author vascordoba
 */
@Repository
public class HibernatePuntoRepository extends HibernateRepository implements PuntoRepository {

    @Autowired
    public HibernatePuntoRepository(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
    
    private boolean Activo=true;
    @Column(name ="Activo")
    @Transactional
    public String addpunto(Punto punto) {
        Validate.notNull(punto, "El punto no puede estar nulo");
        punto.setActivo(Activo);
        getCurrentSession().saveOrUpdate(punto);
        
        for(Bodega b : punto.getBodegas()){
        	b.setPunto(punto);
        	getCurrentSession().save(b);
        }
        
        return Long.toString(punto.getId());
		
    }
    
    @Transactional
    public void update(Punto Punto) throws RepositoryException {
        Validate.notNull(Punto, "The Punto cannot be null");
        getCurrentSession().saveOrUpdate(Punto);
    }
    
    @Transactional
    public void remove(long id) throws RepositoryException {
        Punto Punto=(com.gamasoft.hps.sab.domain.Punto) getCurrentSession().get(Punto.class, id);
        if(Punto!=null){
        	getCurrentSession().delete(Punto);
        }
    }
    
    @Transactional
    public Punto getById(long id) {
    	
    	Punto p =(Punto)getCurrentSession().load(Punto.class, id);
    	try{
    	System.out.println("el numero de marcas en este punto es "+p.getMarcas().size());
    	return p;}
    	catch(Exception e){
    		return p;
    	}
    
    }
    
	@Override
	@Transactional
	public List<Punto> getPuntoByIdGrupoCliente(Long idCliente,Long idClienteUser, Long idGrupo, Long idPunto) {
		System.out.println("consultando getPuntoByIdGrupoCliente");
		@SuppressWarnings("unchecked")
		List<Punto> Puntos = getCurrentSession().createQuery("select p from Punto p, Grupo g join g.clientes clientes  where  p.activo=1 and clientes.id="+idCliente+" and g.id="+idGrupo+" and p.cliente_id="+idCliente+" ").list();
		if(!Puntos.isEmpty()){
		System.out.println("punto tiene "+Puntos.get(0).getMarcas()+" marcas");
		}
		return Puntos;
	}

    @Transactional
    public List<Punto> getAll() {
       	@SuppressWarnings("unchecked")
		List<Punto> Puntos = getCurrentSession().createQuery("from Punto where Activo=1").list();
        return Puntos;
    }

	@Transactional
	public void update(PuntoDto punto, long id) {
		getCurrentSession().saveOrUpdate(punto);
	}

	@Override
	@Transactional
	public List<Punto> getByIdCliente(Long clienteId) {
		@SuppressWarnings("unchecked")
		List<Punto> puntos = getCurrentSession().createQuery("from Punto where cliente_id="+clienteId+" and Activo=1").list();
		return puntos;
	}

	@Override
	@Transactional
	public List<Punto> getByIdClienteInactivo(Long idCliente) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Punto> puntos = getCurrentSession().createQuery("from Punto where cliente_id="+idCliente+" and Activo=0").list();
		System.out.println("esta vacia la lista? "+puntos.isEmpty());
		return puntos;
	}

	@Override
	@Transactional
	public void agregarMarcasAPunto(List<MarcaDto> marcaDto, Long idpunto) {
		Punto punto = (Punto) getCurrentSession().get(Punto.class, idpunto);
		System.out.println("La marca dto tiene un tamño de  "+marcaDto.size());
		punto.getMarcas().clear();
		  for(MarcaDto c : marcaDto){
		          Marca marca = (Marca)getCurrentSession().get(Marca.class, c.getId());
		          System.out.println("la marca es "+marca.getNombre());
		          punto.getMarcas().add(marca); //revisar
		         
		  }
		  getCurrentSession().saveOrUpdate(punto);
	}
	
	@Override
	@Transactional
	public void agregarBodegasAPunto(List<BodegaDto> bodegas, Long idpunto) {
		Punto punto = (Punto) getCurrentSession().get(Punto.class, idpunto);
		punto.getBodegas().clear();
		for(BodegaDto b : bodegas){
			Bodega bodega = new Bodega(b);
			bodega.setPunto(punto);
			getCurrentSession().saveOrUpdate(bodega);
		}
	}
	
	@Override
	@Transactional
	public void actualizarBodegas(List<BodegaDto> bodegas) {
		for(BodegaDto b : bodegas){
			Bodega bodega = (Bodega) getCurrentSession().get(Bodega.class, b.getId());
			bodega.setNombre(b.getNombre());
			bodega.setBodegaInterna(b.getBodegaInterna());
			getCurrentSession().saveOrUpdate(bodega);
		}		
	}

	@Override
	@Transactional
	public List<Punto> getByName(String nombre, Long Cliente, Long Grupo) {
		@SuppressWarnings("unchecked")
		List<Punto> punto =getCurrentSession().createQuery("from Punto p, Cliente clientes join fetch clientes.grupos grupo  where p.nombre like '"+nombre+"%"+"' and grupo.id='"+Grupo+"' and clientes.id='"+Cliente+"' and p.activo=1"  ).list();
		return punto;
	}

	@Override
	@Transactional
	public List<Punto> getByName(String nombre) {
		@SuppressWarnings("unchecked")
		List<Punto> punto =getCurrentSession().createQuery("from Punto where nombre like '"+nombre+"%"+"' and Activo=1").list();
		return punto;
	}

	@Override
	@Transactional
	public List<Punto> getPuntoByIdpunto(Long idPunto) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Punto> punto =getCurrentSession().createQuery("select p from Punto p where p.id="+idPunto+"" ).list();
		return punto;
	}

	@Override
	@Transactional
	public List<Punto> verificarPuntoCliente(String nombre, Long Clienteid) {
		@SuppressWarnings("unchecked")
		List<Punto> punto =getCurrentSession().createQuery("select p from Punto p where  p.cliente_id="+Clienteid+" and p.nombre='"+nombre+"'" ).list();
		return punto;
	}

	@Override
	public void add(Punto t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Punto> getByArticulo(Long idArticulo) {
		List<Punto> puntos = getCurrentSession().createQuery("select p from Punto p inner join p.articulos a where p.activo = 1 and a.id = " + idArticulo).list();
		return puntos;
	}


		
}
