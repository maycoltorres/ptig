package com.gamasoft.hps.sab.repository.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Marca;
import com.gamasoft.hps.sab.domain.Punto;
import com.gamasoft.hps.sab.dto.MarcaDto;
import com.gamasoft.hps.sab.dto.PuntoDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.MarcaRepository;

@Repository
public class HibernateMarcaRepository extends HibernateRepository implements MarcaRepository{

	  @Autowired
	    public HibernateMarcaRepository(SessionFactory sessionFactory) {
	        setSessionFactory(sessionFactory);
	    }
	
	
	@Override
	@Transactional
	public void add(Marca m) {
			// TODO Auto-generated method stub
			m.setActivo(true);
			try{
			getCurrentSession().saveOrUpdate(m);
			}catch(Exception e){
				
				
			}
	}  
	  
	  
	@Override
	@Transactional
	public List<Marca> getAll() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Marca> marca = getCurrentSession().createQuery("from Marca where activo=true").list();
		return marca;
	}

	

	@Override
	@Transactional
	public void update(Marca t) throws RepositoryException {
		// TODO Auto-generated method stub
		getCurrentSession().saveOrUpdate(t);
	}

	@Override
	@Transactional
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		Marca marca = (Marca)getCurrentSession().get(Marca.class, id);
		marca.setActivo(false);
        if(marca!=null){
        	getCurrentSession().update(marca);
	}
	}

	@Override
	@Transactional
	public Marca getById(long id) {
		Marca marca = (Marca) getCurrentSession().load(Marca.class, id);
		System.out.println("El numero de clientes en este grupo es "+marca.getPuntos().size());
		return marca;
	}


	@Override
	@Transactional
	public void agregarMarcaPunto(List<MarcaDto> marcaDto, Long idPunto) {
		// TODO Auto-generated method stub
		 Punto punto = (Punto) getCurrentSession().get(Punto.class, idPunto);
		 punto.getMarcas().clear();
		  for(MarcaDto c : marcaDto){
		          Marca marca = (Marca)getCurrentSession().get(Marca.class, c.getId());
		          punto.getMarcas().add(marca);
		          getCurrentSession().saveOrUpdate(punto);
		  }
	}


	@Override
	@Transactional
	public List<MarcaDto> getMarcasPorPunto(long idPunto) {
		Punto punto = (Punto) getCurrentSession().get(Punto.class, idPunto);
		List<MarcaDto> marcas = new ArrayList<MarcaDto>();
		for(Marca m : punto.getMarcas()){
			marcas.add(m.toDto());
		}
		return marcas;
	}


	@Override
	@Transactional
	public String agregarPuntoAMarca(long marcaId, long PuntoId)  {
		// TODO Auto-generated method stub
		Marca marca = (Marca)getCurrentSession().get(Marca.class, marcaId);
		 Punto c=(Punto) getCurrentSession().get(Punto.class, PuntoId);
		 if(marca!=null){
			 if(c!=null){
		 c.getMarcas().add(marca);
		 this.getCurrentSession().saveOrUpdate(c);
		 }else {
			return "La marca no existe";
		 }}else{
			 return "el cliente no existe";
		 }
		return null;
	}


	@Override
	@Transactional
	public void quitarPuntoAMarca(long marcaId, long clienteId) {
		// TODO Auto-generated method stub
		Marca marca = (Marca)getCurrentSession().get(Marca.class, marcaId);
		 Punto c=(Punto) getCurrentSession().get(Punto.class, clienteId);
		 if(c!=null){
			 if(marca!=null){
		 marca.getPuntos().remove(c);
		 this.getCurrentSession().saveOrUpdate(marca);
		 }else {
			 System.out.println("La marca no existe");
		 }}else{
			 System.out.println("el cliente no existe");
		 }
	}


	@Override
	@Transactional
	public List<Marca> getAllInactivos() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Marca> marca = getCurrentSession().createQuery("from Marca where activo=false").list();
		return marca;
	}


	@Override
	@Transactional
	public void eliminarPuntosDeMarca(List<PuntoDto> puntoDto, long marcaId) {
		// TODO Auto-generated method stub
		 Marca marca = (Marca) getCurrentSession().get(Marca.class, marcaId);
		 marca.getPuntos().clear();
		  for(PuntoDto c : puntoDto){
		          Punto punto = (Punto)getCurrentSession().get(Punto.class, c.getId());
		          marca.getPuntos().add(punto);
		          getCurrentSession().saveOrUpdate(marca);
		  }
	}


	@Override
	@Transactional
	public List<Marca> getPorNombre(String nombre) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Marca> marca =getCurrentSession().createQuery("from Marca where nombre like '"+nombre+"%"+"'"+"and Activo=1").list();

		return marca;
	}


	


	

}
