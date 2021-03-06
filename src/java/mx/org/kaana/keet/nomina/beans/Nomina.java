package mx.org.kaana.keet.nomina.beans;

import mx.org.kaana.keet.db.dto.TcKeetNominasPeriodosDto;

/**
 *@company KAANA
 *@project KAJOOL (Control system polls)
 *@date 27/04/2020
 *@time 07:38:52 PM 
 *@author Team Developer 2016 <team.developer@kaana.org.mx>
 */

public class Nomina extends TcKeetNominasPeriodosDto {

	private Long idNomina;
	private Long idTipoNomina;
	private Long idNominaEstatus;
	private Long proveedores;
	private Long personas;
	private Double total;
	private Double neto;
	private Double global;
	private String estatus;
	private String observaciones;

	public Nomina() {
	  this.idNomina= -1L;
		this.idTipoNomina= 2L;
		this.idNominaEstatus= 1L;
		this.estatus= "";
		this.proveedores= 0L;
		this.personas= 0L;
		this.total= 0D;
		this.neto= 0D;
		this.global= 0D;
	}
	
	public Long getIdNomina() {
		return idNomina;
	}

	public void setIdNomina(Long idNomina) {
		this.idNomina=idNomina;
	}

	public Long getIdTipoNomina() {
		return idTipoNomina;
	}

	public void setIdTipoNomina(Long idTipoNomina) {
		this.idTipoNomina=idTipoNomina;
	}
	public Long getIdNominaEstatus() {
		return idNominaEstatus;
	}

	public void setIdNominaEstatus(Long idNominaEstatus) {
		this.idNominaEstatus=idNominaEstatus;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus=estatus;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones=observaciones;
	}

	public Long getProveedores() {
		return proveedores;
	}

	public void setProveedores(Long proveedores) {
		this.proveedores=proveedores;
	}

	public Long getPersonas() {
		return personas;
	}

	public void setPersonas(Long personas) {
		this.personas=personas;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total=total;
	}

	public Double getNeto() {
		return neto;
	}

	public void setNeto(Double neto) {
		this.neto=neto;
	}

	public Double getGlobal() {
		return global;
	}

	public void setGlobal(Double global) {
		this.global=global;
	}
	
	@Override
	public Class toHbmClass() {
		return TcKeetNominasPeriodosDto.class;
	}
	
}
