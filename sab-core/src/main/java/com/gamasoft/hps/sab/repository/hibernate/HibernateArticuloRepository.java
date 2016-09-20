package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;





import com.gamasoft.hps.sab.domain.Articulo;
import com.gamasoft.hps.sab.domain.Bodega;
import com.gamasoft.hps.sab.domain.MaximosMinimos;
import com.gamasoft.hps.sab.domain.Unidad;
import com.gamasoft.hps.sab.domain.VentaCanalImpuesto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.ArticuloRepository;


@Repository
public class HibernateArticuloRepository extends HibernateRepository implements ArticuloRepository {
	
	@Autowired
	public HibernateArticuloRepository(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	@Override
	@Transactional
	public void add(Articulo t) {
		t.setActivo(true);
		getCurrentSession().saveOrUpdate(t);
	}

	@Override
	@Transactional
	public void update(Articulo t) throws RepositoryException {
		getCurrentSession().saveOrUpdate(t);
		
		if(t.getInventarioEnt() != null ){
			getCurrentSession().saveOrUpdate(t.getInventarioEnt());
			if(t.getInventarioEnt().getMaximosYMinimos() != null){
				for(MaximosMinimos mm : t.getInventarioEnt().getMaximosYMinimos()){
					mm.setInventario(t.getInventarioEnt());
					getCurrentSession().saveOrUpdate(mm);
				}
			}
		}
		
		if(t.getVentaEnt() != null){
			getCurrentSession().saveOrUpdate(t.getVentaEnt());
		}
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
	}

	@Override
	@Transactional
	public Articulo getById(long id) {
		Articulo art = (Articulo) getCurrentSession().get(Articulo.class, id);
		art.getInventarioEnt();
		art.getVentaEnt();
		
		Hibernate.initialize(art.getRecetas());
		Hibernate.initialize(art.getPuntos());
		if(art.getInventarioEnt()!=null){
			Hibernate.initialize(art.getInventarioEnt().getBodegas());
		}
		
		if(art.getInventarioEnt()!= null  && art.getInventarioEnt().getGrupoArticulo()!=null){
			Hibernate.initialize(art.getInventarioEnt().getGrupoArticulo().getClaseArticulo());
		}
		
		if(art.getVentaEnt()!= null  && art.getVentaEnt().getGrupoArticulo()!=null){
			Hibernate.initialize(art.getVentaEnt().getGrupoArticulo().getClaseArticulo());
		}
		
		if(art.getVentaEnt()!= null  && art.getVentaEnt().getGruposSeleccion()!=null){
			Hibernate.initialize(art.getVentaEnt().getGruposSeleccion());
		}
		
		if(art.getVentaEnt()!= null  && art.getVentaEnt().getCanalesImpuesto()!=null){
			Hibernate.initialize(art.getVentaEnt().getCanalesImpuesto());
			for(VentaCanalImpuesto ventaCanalImp : art.getVentaEnt().getCanalesImpuesto()){
				Hibernate.initialize(ventaCanalImp.getTarifa().getRegimen().getImpuesto());
			}
			
		}
		
		if(art.getVentaEnt()!= null  && art.getVentaEnt().getUnidadesVenta()!=null){
			Hibernate.initialize(art.getVentaEnt().getUnidadesVenta());
		}
		
		return art;
	}

	@Override
	@Transactional
	public List<Articulo> getAll() {
		@SuppressWarnings("unchecked")
		List<Articulo> art = getCurrentSession().createQuery("from Articulo where activo=1").list();
		
		return art;
	}
	
	@Override
	@Transactional
	public List<Articulo> getInactivos(){
		@SuppressWarnings("unchecked")
		List<Articulo> art = getCurrentSession().createQuery("from Articulo where activo=0").list();
		return art;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Articulo> getByClienteYSeleccion(Long idCliente,
			Boolean seleccion) {
		List<Articulo> art;
		if(seleccion){
			art = getCurrentSession().createQuery("from Articulo where activo=1 and seleccion = 1 "
					+ "and id_cliente=" + idCliente ).list();
		}else {
			art = getCurrentSession().createQuery("from Articulo where activo=1 and seleccion = 0 "
					+ "and id_cliente=" + idCliente ).list();
		}
		
		return art;
		
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Bodega> getByArticulo(Long idArticulo, Boolean showAll) {
		List<Bodega> bodegas = getCurrentSession().createQuery("select b from Bodega b join b.punto p join p.articulos a where a.id=" + idArticulo).list();
		return bodegas;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public Set<Unidad> getUnidadesCompraByArticulo(Long idArticulo) {
		Articulo a = getById(idArticulo);
		if (a.getInventarioEnt() != null){
			return a.getInventarioEnt().getUnidadesCompra();
		}
		return null;

	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Articulo> getArticulosByParams(Long idCliente,
			Boolean seleccion, Boolean empaque, Boolean inventario, 
			Boolean venta, Boolean receta, Boolean unidadAlternaKardex, 
			Long idUnidadKardex, String mostrar) {
		StringBuilder query = new StringBuilder("select a from Articulo a ");
		String operador = " where ";
		if(idCliente != null){
			query.append(operador);
			query.append(" id_cliente = ");
			query.append(idCliente);
			operador = " and ";
		}
		
		if(seleccion != null){
			query.append(operador);
			query.append(" seleccion = ");
			query.append(seleccion);
			operador = " and ";
		}
		
		if(empaque != null){
			query.append(operador);
			query.append(" empaque = ");
			query.append(empaque);
			operador = " and ";
		}
		
		if(inventario != null){
			query.append(operador);
			if(inventario == true){
				StringBuilder queryInventario = new StringBuilder("exists (from Inventario i where i.articulo.id = a ");
				if(unidadAlternaKardex != null){
					if(unidadAlternaKardex == true){
						queryInventario.append(" and i.unidadAlterna is not null");
					}else{
						queryInventario.append(" and i.unidadAlterna is null) ");
					}
				}
				if(idUnidadKardex != null){
					queryInventario.append(" and i.unidadKardex.id = " + idUnidadKardex);
				}
				
				queryInventario.append(")");
				query.append(queryInventario);
			}else{
				query.append("not exists (from Inventario i where i.articulo.id = a.id) ");
			}
			operador = " and ";
		}
		
		if(venta != null){
			query.append(operador);
			if(venta == true){
				query.append("exists (from Venta v where v.articulo.id = a.id) ");
			}else{
				query.append("not exists (from Venta v where v.articulo.id = a.id) ");
			}
			operador = " and ";
		}
		
		if(receta != null){
			query.append(operador);
			if(receta == true){
				query.append("exists (from Receta r where r.articulo.id = a.id) ");
			}else{
				query.append("not exists (from Receta r where r.articulo.id = a.id) ");
			}
			operador = " and ";
		}
		
		if(mostrar != null){
			if(mostrar.equalsIgnoreCase("activos")){
				query.append(operador);
				query.append(" a.activo = 1");
			} else if (mostrar.equalsIgnoreCase("inactivos")){
				query.append(operador);
				query.append(" a.activo = 0");
			}
		}	
		
		List<Articulo> articulos = getCurrentSession().createQuery(query.toString()).list();
		
		return articulos;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public Set<VentaCanalImpuesto> getCanalImpuestoByArticulo(Long idArticulo) {
		Articulo a = getById(idArticulo);
		Set<VentaCanalImpuesto> canalesImpuestos = a.getVentaEnt().getCanalesImpuesto();
		
		for(VentaCanalImpuesto c : canalesImpuestos){
			Hibernate.initialize(c.getTarifa().getRegimen().getImpuesto());
		}
		
		return canalesImpuestos;
	}

	@Override
	@Transactional
	public void definirCanalImpuesto(Long idArticulo,
			Set<VentaCanalImpuesto> canalesImpuestos) {
		Articulo a = getById(idArticulo);
		a.getVentaEnt().setCanalesImpuesto(canalesImpuestos);
		getCurrentSession().saveOrUpdate(a.getVentaEnt());
	}

	@Override
	@Transactional
	public void agregarCanalImpuesto(Long idArticulo,
			Set<VentaCanalImpuesto> canalesImpuestos) {
		Articulo a = getById(idArticulo);
		if(a.getVentaEnt().getCanalesImpuesto() == null){
			a.getVentaEnt().setCanalesImpuesto(canalesImpuestos);
		}else{
			a.getVentaEnt().getCanalesImpuesto().addAll(canalesImpuestos);
		}
		
		getCurrentSession().saveOrUpdate(a.getVentaEnt());
	}

	@Override
	@Transactional
	public String eliminarCanalImpuesto(Long idArticulo, Long idCanalImpuesto) {
		Articulo a = getById(idArticulo);
		Set<VentaCanalImpuesto> canalesImpuestos = a.getVentaEnt().getCanalesImpuesto();
		for(VentaCanalImpuesto v : canalesImpuestos){
			if(v.getId().equals(idCanalImpuesto)){
				canalesImpuestos.remove(v);
			}
		}
		
		getCurrentSession().saveOrUpdate(a.getVentaEnt());
		return "OK";
	}

	@Override
	@Transactional
	public Articulo getByClienteYNombre(Long idCliente, String nombre) {
		List<Articulo> articulos = (List<Articulo>) getCurrentSession().createQuery("from Articulo where id_cliente = " + idCliente + 
				" and lower(nombre) = '" + nombre + "'").list();
		
		if(articulos != null && articulos.size()>0){
			return articulos.get(0);
		}
		
		return null;
	}
}
