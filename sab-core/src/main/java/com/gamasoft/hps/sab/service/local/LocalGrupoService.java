package com.gamasoft.hps.sab.service.local;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gamasoft.hps.sab.domain.Grupo;
import com.gamasoft.hps.sab.dto.ClienteDto;
import com.gamasoft.hps.sab.dto.GrupoDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.exception.ServiceException;
import com.gamasoft.hps.sab.repository.GrupoRepository;
import com.gamasoft.hps.sab.service.GrupoService;
@Service
public class LocalGrupoService implements GrupoService{

	
	private GrupoRepository grupoRepository;
	
	@Autowired
	public void setGrupoRepository(GrupoRepository grupoRepository) {
		this.grupoRepository = grupoRepository;
	}
	
	@Override
	public List<GrupoDto> getGrupos() {
		// TODO Auto-generated method stub
		List<Grupo> grupos = grupoRepository.getAll();
		if(grupos !=null && !grupos.isEmpty()){
			List<GrupoDto> dto = new ArrayList<GrupoDto>();
			for(Grupo g : grupos){
				dto.add(g.toDtoLess());
			}
			return dto;
		}
		return new ArrayList<GrupoDto>();
	}
	
	@Override
	public List<GrupoDto> getGruposinactivos() {
		// TODO Auto-generated method stub
		List<Grupo> grupos = grupoRepository.getAllInactivos();
		if(grupos !=null && !grupos.isEmpty()){
			List<GrupoDto> dto = new ArrayList<GrupoDto>();
			for(Grupo g : grupos){
				dto.add(g.toDtoLess());
			}
			return dto;
		}
		return new ArrayList<GrupoDto>();
	}

	@Override
	public String add(GrupoDto grupoDto) {
		Grupo g = new Grupo(grupoDto);
		this.grupoRepository.add(g);
		return Long.toString(g.getId());
	}

	@Override
	public GrupoDto getById(long id) {
		// TODO Auto-generated method stub
		GrupoDto grupo = new GrupoDto();
		Grupo g = this.grupoRepository.getById(id);
		try{
		grupo=g.toDto();
		return grupo;}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public String update(GrupoDto grupo, long clienteId) {
		// TODO Auto-generated method stub
		Grupo g = this.grupoRepository.getById(clienteId);
		
		if(grupo.getNombre()!=null && g.getId()!=1){
			g.setNombre(grupo.getNombre());
		try {
			this.grupoRepository.update(g);
			return "grupo modificado";
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			return "Este Grupo no es modificable";
		}
		}
		else{
			return "Este grupo no es Modificable";
		}
		
		
	}

	

	@Override
	public String adicionaCliente(List<ClienteDto> clienteDto, Long idGrupo) {
		// TODO Auto-generated method stub
		if(idGrupo!=1){
		this.grupoRepository.agregarClienteAGrupo(clienteDto,idGrupo);
		return "clientes adicionados";
	}else {
		return "este grupo no es modificable";
	}
		}

	@Override
	public GrupoDto getGrupoCliente(long grupoId) {
		// TODO Auto-generated method stub
		return this.grupoRepository.getGrupoCliente(grupoId);
	}

	@Override
	public String adicionaCliente(long grupoId, long clienteId) {
		// TODO Auto-generated method stub
		try {
			return grupoRepository.agregarClienteAGrupo(grupoId, clienteId);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.getMessage().concat("El cliente no existe");
			
		}
		return null;
		
	}

	@Override
	public String inactiveGrupo(long grupoId) {
		Grupo grupo =this.grupoRepository.getById(grupoId);
		grupo.setActivo(false);
		try {
			this.grupoRepository.update(grupo);
			return "Grupo Desactivado";
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			return "No se ha Desactivado";
		}
	}

	@Override
	public String activateGrupo(long grupoId) {
		Grupo grupo =this.grupoRepository.getById(grupoId);
		grupo.setActivo(true);
		try {
			this.grupoRepository.update(grupo);
			return "Grupo Reactivado";
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			return "No se Ractivo";
		}
	}

	@Override
	public void quitarCliente(long grupoId, long clienteId) {
		this.grupoRepository.quitarClienteAGrupo(grupoId, clienteId);
	}

	@Override
	public void eliminarClientes(List<ClienteDto> clienteDto, long grupoId) {
		// TODO Auto-generated method stub
		this.grupoRepository.eliminarClientesDeGrupo(clienteDto,grupoId);
	}

	@Override
	public List<GrupoDto> getGrupoPorNombre(String nombre, Long cliente, Long grupo) {
		// TODO Auto-generated method stub
		
		List<Grupo> grupos = grupoRepository.getByname(nombre, cliente, grupo);
		if(grupos !=null && !grupos.isEmpty()){
			List<GrupoDto> dto = new ArrayList<GrupoDto>();
			for(Grupo g : grupos){
				dto.add(g.toDtoLess());
			}
			return dto;
		}
		return new ArrayList<GrupoDto>();
	}

	

	@Override
	public List<GrupoDto> getGrupoIdCliente(long idCliente) {
		// TODO Auto-generated method stub
		List<Grupo> grupo =grupoRepository.getGrupoIdCliente(idCliente);
		if(grupo !=null && !grupo.isEmpty()){
			List<GrupoDto> dto = new ArrayList<GrupoDto>();
			for(Grupo g : grupo){
				dto.add(g.toDtoLess());
			}
			return dto;
		}
		return new ArrayList<GrupoDto>();
		
		
	}




}
