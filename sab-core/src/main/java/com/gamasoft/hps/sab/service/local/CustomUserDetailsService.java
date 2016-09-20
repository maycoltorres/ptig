/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.service.local;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.RolCliente;
import com.gamasoft.hps.sab.domain.Transaccion;
import com.gamasoft.hps.sab.repository.UserRepository;

/**
 *
 * @author vascordoba
 */
@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

   
    private UserRepository userRepository;

    /**
     * Sets the userRepository. This was added for testing purposes, since Spring doesn't need it.
     *
     * @param userRepository
     */
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        try {
            com.gamasoft.hps.sab.domain.User domainUser = userRepository.getUserByEmail(email);

            boolean enabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;

            return new org.springframework.security.core.userdetails.User(
                    domainUser.getUsername(),
                    domainUser.getPassword().toLowerCase(),
                    enabled,
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    getAuthorities(domainUser.getRolCliente())
                    );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
		
    }

    /**
     * Retrieves a collection of {@link GrantedAuthority} based on a numerical role
     *
     * @param set Set containing the user roles assigned
     * @return a collection of {@link GrantedAuthority
     */
    public Collection<? extends GrantedAuthority> getAuthorities(Set<RolCliente> set) {
        return getGrantedAuthorities(getRoles(set));
    }

    /**
     * Converts a numerical role to an equivalent list of roles
     *
     * @param role Set containing the user roles assigned
     * @return list of roles as as a list of {@link String}
     */
    public List<String> getRoles(Set<RolCliente> set) {
        List<String> roles = new ArrayList<String>();

        Iterator<RolCliente> it = set.iterator();

        while (it.hasNext()) {
            RolCliente r = it.next();
            roles.add(r.getName());
        }

        return roles;
    }

    /**
     * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
     *
     * @param roles {@link String} of roles
     * @return list of granted authorities
     */
    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
    
    public String Encripcion(String password){
    	
    	 MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			 md.update(password.getBytes());
			  
	         byte byteData[] = md.digest();
	       //convert the byte to hex format method 1
	         StringBuffer sb = new StringBuffer();
	         for (int i = 0; i < byteData.length; i++) {
	          sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	         }
	  
	         System.out.println("Hex format : " + sb.toString());
	  
	         //convert the byte to hex format method 2
	         StringBuffer hexString = new StringBuffer();
	     	for (int i=0;i<byteData.length;i++) {
	     		String hex=Integer.toHexString(0xff & byteData[i]);
	    	     	if(hex.length()==1) hexString.append('0');
	    	     	hexString.append(hex);}
	     	return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
          
    	
    }
    
    public int validaPermisos(String filterInvocation, String httpServletRequest, long idUser){
    		List<Transaccion> txUser = userRepository.getTransaccionUser(idUser);
			if(txUser!=null && !txUser.isEmpty()){
				for ( ListIterator<Transaccion> iterador = txUser.listIterator(); iterador.hasNext(); ) {
				     Transaccion t = (Transaccion) iterador.next();
				     String rutaDB=t.getUrl().toString();
				        if( Pattern.matches(rutaDB,filterInvocation)&& t.getOperacion().equals(httpServletRequest)){
				        	System.out.println("permiso concedido");
				        	return 1;
				        }
				}				
			}
			System.out.println("permiso denegado");
		return 1;
		
    }
    
    

    
    
}
