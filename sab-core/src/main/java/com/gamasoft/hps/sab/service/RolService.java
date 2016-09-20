package com.gamasoft.hps.sab.service;

import java.util.List;








import com.gamasoft.hps.sab.dto.RolDto;
import com.gamasoft.hps.sab.dto.TransaccionDto;
import com.gamasoft.hps.sab.exception.ServiceException;
/**
*
* @author wospino
*/
public interface RolService {
	List<RolDto> getRoles();
	RolDto getById(long rolId, Long idgrupo);
	String add(RolDto rolDto) throws ServiceException;
	String modificar(RolDto rolDto, Long rolID, Long idgrupo);
	String inactivar(Long rolId, Long idGrupo);
	String activar(Long rolId);
	List<RolDto> inactivos();
	List<RolDto> getRolPorNombre(String nombre, Long idUserRequestMapping,
	Long idUserRequestMapping2);
	List<RolDto> getRolesByIdCliente(long rolId);
	String adicionaTrasaccionRol(Long idRol, List<TransaccionDto> transac);
	
	
}
