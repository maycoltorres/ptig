/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 
 * @author wospino
 */
@Service
public class ServiceManager {
    @Autowired
    private SecurityService securityService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
	private PuntoService puntoService;
    @Autowired
    private PaisService paisService;
    @Autowired
    private DepartamentoService departamentoService;
    @Autowired
    private MunicipioService municipioService;
    @Autowired 
    private MarcaService marcaService;
	@Autowired
	private GrupoService grupoService;
	@Autowired
	private UserService userservice;
	@Autowired
	private RolService rolservice;
    @Autowired
    private RolClienteService rolclienteservice;
    @Autowired
    private ArticuloService articuloservice;
    @Autowired
    private CommonService commonService;
    @Autowired
    private UnidadService unidadService;
    @Autowired
    private ConfiguracionArticulosService configuracionArticulosService;
    @Autowired
    private GrupoSeleccionService grupoSeleccionService;
    @Autowired
    private GrupoEmpaqueService grupoEmpaqueService;
    @Autowired
    private BodegaService bodegaService;
    
    
	public ConfiguracionArticulosService getConfiguracionArticulosService() {
		return configuracionArticulosService;
	}
	public void setConfiguracionArticulosService(
			ConfiguracionArticulosService configuracionArticulosService) {
		this.configuracionArticulosService = configuracionArticulosService;
	}
	public UnidadService getUnidadService() {
		return unidadService;
	}
	public void setUnidadService(UnidadService unidadService) {
		this.unidadService = unidadService;
	}
	public CommonService getCommonService() {
		return commonService;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	public ArticuloService getArticuloservice() {
		return articuloservice;
	}
	public void setArticuloservice(ArticuloService articuloservice) {
		this.articuloservice = articuloservice;
	}
	public SecurityService getSecurityService() {
		return securityService;
	}
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	public ClienteService getClienteService() {
		return clienteService;
	}
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	public PuntoService getPuntoService() {
		return puntoService;
	}
	public void setPuntoService(PuntoService puntoService) {
		this.puntoService = puntoService;
	}
	public PaisService getPaisService() {
		return paisService;
	}
	public void setPaisService(PaisService paisService) {
		this.paisService = paisService;
	}
	public DepartamentoService getDepartamentoService() {
		return departamentoService;
	}
	public void setDepartamentoService(DepartamentoService departamentoService) {
		this.departamentoService = departamentoService;
	}
	public MunicipioService getMunicipioService() {
		return municipioService;
	}
	public void setMunicipioService(MunicipioService municipioService) {
		this.municipioService = municipioService;
	}
	public MarcaService getMarcaService() {
		return marcaService;
	}
	public void setMarcaService(MarcaService marcaService) {
		this.marcaService = marcaService;
	}
	public GrupoService getGrupoService() {
		return grupoService;
	}
	public void setGrupoService(GrupoService grupoService) {
		this.grupoService = grupoService;
	}
	public UserService getUserservice() {
		return userservice;
	}
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}
	public RolService getRolservice() {
		return rolservice;
	}
	public void setRolservice(RolService rolservice) {
		this.rolservice = rolservice;
	}
	public RolClienteService getRolclienteservice() {
		return rolclienteservice;
	}
	public void setRolclienteservice(RolClienteService rolclienteservice) {
		this.rolclienteservice = rolclienteservice;
	}
	public GrupoSeleccionService getGrupoSeleccionService() {
		return grupoSeleccionService;
	}
	public void setGrupoSeleccionService(GrupoSeleccionService grupoSeleccionService) {
		this.grupoSeleccionService = grupoSeleccionService;
	}
	public GrupoEmpaqueService getGrupoEmpaqueService() {
		return grupoEmpaqueService;
	}
	public void setGrupoEmpaqueService(GrupoEmpaqueService grupoEmpaqueService) {
		this.grupoEmpaqueService = grupoEmpaqueService;
	}
	public BodegaService getBodegaService() {
		return bodegaService;
	}
	public void setBodegaService(BodegaService bodegaService) {
		this.bodegaService = bodegaService;
	}
	
}
    
    

	



