package com.gamasoft.hps.sab.webservices.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gamasoft.hps.sab.domain.Departamento;
import com.gamasoft.hps.sab.domain.Municipio;
import com.gamasoft.hps.sab.domain.Pais;
import com.gamasoft.hps.sab.dto.CanalDto;
import com.gamasoft.hps.sab.dto.DepartamentoDto;
import com.gamasoft.hps.sab.dto.GrupoArticuloDto;
import com.gamasoft.hps.sab.dto.GrupoVentaDto;
import com.gamasoft.hps.sab.dto.ImpuestoDto;
import com.gamasoft.hps.sab.dto.MunicipioDto;
import com.gamasoft.hps.sab.dto.PaisDto;
import com.gamasoft.hps.sab.dto.RegimenDto;
import com.gamasoft.hps.sab.dto.TarifaDto;
import com.gamasoft.hps.sab.service.ServiceManager;


@Controller
@RequestMapping("/rest/common")
public class CommonRestController {
	
	private ServiceManager serviceManager;
	
	@Autowired
	public CommonRestController(ServiceManager servicemanager ){
		this.serviceManager=servicemanager;
	}

	@RequestMapping(value = {"/paises"}, method = RequestMethod.GET)
    public @ResponseBody List<PaisDto> getPaises() throws Exception {
        return serviceManager.getPaisService().getPaises();
    }

	 @RequestMapping(value = "/paises/{paisId}", method = RequestMethod.GET)
	    public @ResponseBody Pais GetPaisId(@PathVariable("paisId") long paisId, HttpServletResponse response) throws Exception {
	        
	    	Pais p =serviceManager.getPaisService().getById(paisId);
	        response.setStatus(HttpStatus.OK.value());
			return p;
	    }
	 
	 @RequestMapping(value ="/dptos/{dptosId}", method = RequestMethod.GET)
	 public @ResponseBody Departamento getDepartamento(@PathVariable("dptosId") long dptosId, HttpServletResponse response) throws Exception{
		 Departamento d=serviceManager.getDepartamentoService().getById(dptosId);
		 response.setStatus(HttpStatus.OK.value());
		 return d;
		 
	 }
	 
	 @RequestMapping(value ="/municipios/{municipioId}", method = RequestMethod.GET)
	 public @ResponseBody Municipio getMunicipio(@PathVariable("municipioId") long municipioId, HttpServletResponse response) throws Exception{
		 Municipio d=serviceManager.getMunicipioService().getById(municipioId);
		 response.setStatus(HttpStatus.OK.value());
		 return d;
		 
	 }
	 
	 
	 
	 @RequestMapping(value ="/dptos/", method = RequestMethod.GET)
	 public @ResponseBody List<DepartamentoDto> getDepartamentos(@RequestParam(value="idPais", required=false) Long idPais, HttpServletResponse response) throws Exception{
		
		 if(idPais==null){
		 response.setStatus(HttpStatus.NO_CONTENT.value());
		 return null;
		 }else {
			return serviceManager.getDepartamentoService().getByidPais(idPais);
		 }
	 }
	 
	 
	 @RequestMapping(value ="/municipios/", method = RequestMethod.GET)
	 public @ResponseBody List<MunicipioDto> getMunicipioIddpto(@RequestParam(value="idDepto", required=false) Long idDepto, HttpServletResponse response) throws Exception{
		 if(idDepto==null){
			 response.setStatus(HttpStatus.NO_CONTENT.value());
			 return null;}
		 else{
			 response.setStatus(HttpStatus.OK.value());
			 return serviceManager.getMunicipioService().getByIdDepto(idDepto);
		
		 }}
	
		 @RequestMapping(value ="/departamentos/", method = RequestMethod.GET)
		 public @ResponseBody List<DepartamentoDto> GetdepartamenttoIdmunicipio(@RequestParam(value="idMunicipio", required=false) Long idMunicipio, HttpServletResponse response) throws Exception{
			 if(idMunicipio==null){
				 response.setStatus(HttpStatus.NO_CONTENT.value());
				 return null;}
			 else{
				 response.setStatus(HttpStatus.OK.value());
				 return serviceManager.getDepartamentoService().getdepartamentoidMunicipio(idMunicipio);
			
			 }
			 
		 }
	
		 @RequestMapping(value ="/paises/", method = RequestMethod.GET)
		 public @ResponseBody List<PaisDto> GetpaisIdDepartamento(@RequestParam(value="idDepartamento", required=false) Long idDepartamento, HttpServletResponse response) throws Exception{
			 if(idDepartamento==null){
				 response.setStatus(HttpStatus.NO_CONTENT.value());
				 return null;}
			 else{
				 response.setStatus(HttpStatus.OK.value());
				 return serviceManager.getPaisService().getpaisIdDepartamento(idDepartamento);
			
			 }
			 
		 }
		 
		 @RequestMapping(value="/canales", method = RequestMethod.GET)
		 public @ResponseBody List<CanalDto> getCanal(HttpServletResponse response) throws Exception{
			 response.setStatus(HttpStatus.OK.value());
			 return serviceManager.getCommonService().getCanales();
		 }
		 
		 @RequestMapping(value="/impuestos", method = RequestMethod.GET)
		 public @ResponseBody List<ImpuestoDto> getImpuestos(
				 @RequestParam(value="idPais", required=false) Long idPais,
				 HttpServletResponse response) throws Exception{
			 
			 response.setStatus(HttpStatus.OK.value());
			 return serviceManager.getCommonService().getImpuestosByPais(idPais);
		 }
		 
		 @RequestMapping(value="/regimenes", method = RequestMethod.GET)
		 public @ResponseBody List<RegimenDto> getRegimenes(
				 @RequestParam(value="idImpuesto", required=false) Long idImpuesto,
				 HttpServletResponse response) throws Exception{
			 
			 response.setStatus(HttpStatus.OK.value());
			 return serviceManager.getCommonService().getRegimenesByImpuesto(idImpuesto);
		 }
		 
		 @RequestMapping(value="/tarifas", method = RequestMethod.GET)
		 public @ResponseBody List<TarifaDto> getTarifas(
				 @RequestParam(value="idRegimen", required=false) Long idRegimen,
				 HttpServletResponse response) throws Exception{
			 
			 response.setStatus(HttpStatus.OK.value());
			 return serviceManager.getCommonService().getTarifasByRegimen(idRegimen);
		 }
}