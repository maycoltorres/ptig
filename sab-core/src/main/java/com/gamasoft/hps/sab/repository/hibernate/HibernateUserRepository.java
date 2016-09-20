/*
 * La s templates de HIbernate se deshabilitaron porque spring ya no las recomienda... wospino
 * 
 */
package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Transaccion;
import com.gamasoft.hps.sab.domain.User;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.UserRepository;

/**
 *
 * @author vascordoba
 */
@Repository
public class HibernateUserRepository extends HibernateRepository implements UserRepository {

    @Autowired
    public HibernateUserRepository(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Transactional
    public User getUserByEmail(String email) {
        @SuppressWarnings("unchecked")
		List<User> users = getCurrentSession().createQuery("from User u where u.email = '"+email+"' and activo=1").list();
        if (users.isEmpty()) {
            return null;
        } else {
        	Hibernate.initialize(users.get(0).getPuntos());
            return users.get(0);
        }
    }
    @Transactional
    public User getUserByUsername(String username) {
        @SuppressWarnings("unchecked")
		List<User> users = getCurrentSession().createQuery("from User u where u.username = '"+username+"'").list();
        if (users.isEmpty()) {
            return null;
        } else {
        	Hibernate.initialize(users.get(0).getPuntos());
            return users.get(0);
        }
    }
    
    @Transactional
    public void add(User user) {
        Validate.notNull(user, "The user cannot be null");
        getCurrentSession().saveOrUpdate(user);
		
    }
    @Transactional
    public void update(User user) throws RepositoryException {
        Validate.notNull(user, "The user cannot be null");
        getCurrentSession().saveOrUpdate(user);
    }
    
    @Transactional
    public void remove(long id) throws RepositoryException {
        User user=(User) getCurrentSession().get(User.class, id);
        if(user!=null){
        	getCurrentSession().delete(user);
        }
    }
    @Transactional
    public User getById(long id) {
    	User u = (User) getCurrentSession().get(User.class, id); 
    	Hibernate.initialize(u.getPuntos());
        return u; 
        		
    }
    
    @Transactional
    public List<User> getAll() {
        @SuppressWarnings("unchecked")
		List<User> users = getCurrentSession().createQuery("from User where activo=1").list();
        return users;
    }
    

	@Override
	@Transactional
	public List<User> getAllByGrupo(Long idGrupo) {
		@SuppressWarnings("unchecked")
		List<User> users = getCurrentSession().createQuery("select u from User u, Cliente c join c.grupos g where u.idCliente = c.id and u.activo = 1 and g.id=" + idGrupo).list();
        return users;
	}

    

	@Override
	@Transactional
	public List<Transaccion> getTransaccionUser(long idUser) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Transaccion> txs = getCurrentSession().createQuery("from Transaccion t join fetch t.Rolcliente rolCliente join fetch rolCliente.user user where user.id = '" + idUser + "'").list();
		System.out.println();
		return txs;
	}

	@Override
	@Transactional
	public List<User> getAllinactivos() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<User> users = getCurrentSession().createQuery("from User where Activo=0").list();
        return users;
	}

	@Override
	@Transactional
	public boolean verificarcorreo(String mail) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<User> existe = getCurrentSession().createQuery("select u from User u where u.email='"+mail+"'").list();
		System.out.println("el email esta vacio? "+existe.isEmpty());
		return existe.isEmpty();
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<User> getAllByCliente(Long idCliente) {
		List<User> users = getCurrentSession().createQuery("from User where activo=1 and id_cliente = " + idCliente).list();
        return users;
	}

	
	
}
