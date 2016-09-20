package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.gamasoft.hps.sab.dto.TransaccionDto;

public class HibernateRepository {
	
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    protected Session getCurrentSession(){
    	return getSessionFactory().getCurrentSession();
    }

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void agregarTrasaccionRol(Long idRol, List<TransaccionDto> transac) {
		// TODO Auto-generated method stub
		
	}

    
}
