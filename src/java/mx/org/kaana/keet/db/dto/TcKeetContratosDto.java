package mx.org.kaana.keet.db.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import mx.org.kaana.libs.Constantes;
import mx.org.kaana.libs.reflection.Methods;
import mx.org.kaana.kajool.db.comun.dto.IBaseDto;

/**
 *@company KAANA
 *@project KAJOOL (Control system polls)
 *@date 10/10/2016
 *@time 11:58:22 PM
 *@author Team Developer 2016 <team.developer@kaana.org.mx>
 */

@Entity
@Table(name="tc_keet_contratos")
public class TcKeetContratosDto implements IBaseDto, Serializable {
		
  private static final long serialVersionUID=1L;
  @Column (name="clave")
  private String clave;
  @Column (name="id_proyecto")
  private Long idProyecto;
  @Column (name="etapa")
  private String etapa;
  @Column (name="recepcion")
  private LocalDate recepcion;
  @Column (name="aceptacion")
  private LocalDate aceptacion;
  @Column (name="ejercicio")
  private Long ejercicio;
  @Column (name="registro")
  private LocalDateTime registro;
  @Column (name="consecutivo")
  private String consecutivo;
  @Column (name="id_contrato_estatus")
  private Long idContratoEstatus;
  @Column (name="id_usuario")
  private Long idUsuario;
  @Id
  @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column (name="id_contrato")
  private Long idContrato;
  @Column (name="arranque")
  private LocalDate arranque;
  @Column (name="observaciones")
  private String observaciones;
  @Column (name="id_empresa")
  private Long idEmpresa;
  @Column (name="orden")
  private Long orden;
  @Column (name="no_viviendas")
  private Long noViviendas;
	@Column (name="costo")
  private Double costo;

  public TcKeetContratosDto() {
    this(new Long(-1L));
  }

  public TcKeetContratosDto(Long key) {
    this(null, null, null, LocalDate.now(), LocalDate.now(), null, null, null, null, new Long(-1L), LocalDate.now(), null, null, null, null, null);
    setKey(key);
  }

  public TcKeetContratosDto(String clave, Long idProyecto, String etapa, LocalDate recepcion, LocalDate aceptacion, Long ejercicio, String consecutivo, Long idContratoEstatus, Long idUsuario, Long idContrato, LocalDate arranque, String observaciones, Long idEmpresa, Long orden, Long noViviendas, Double costo) {
    setClave(clave);
    setIdProyecto(idProyecto);
    setEtapa(etapa);
    setRecepcion(recepcion);
    setAceptacion(aceptacion);
    setEjercicio(ejercicio);
    setRegistro(LocalDateTime.now());
    setConsecutivo(consecutivo);
    setIdContratoEstatus(idContratoEstatus);
    setIdUsuario(idUsuario);
    setIdContrato(idContrato);
    setArranque(arranque);
    setObservaciones(observaciones);
    setIdEmpresa(idEmpresa);
    setOrden(orden);
    setNoViviendas(noViviendas);
		setCosto(costo);
  }
	
  public void setClave(String clave) {
    this.clave = clave;
  }

  public String getClave() {
    return clave;
  }

  public void setIdProyecto(Long idProyecto) {
    this.idProyecto = idProyecto;
  }

  public Long getIdProyecto() {
    return idProyecto;
  }

  public void setEtapa(String etapa) {
    this.etapa = etapa;
  }

  public String getEtapa() {
    return etapa;
  }

  public void setRecepcion(LocalDate recepcion) {
    this.recepcion = recepcion;
  }

  public LocalDate getRecepcion() {
    return recepcion;
  }

  public void setAceptacion(LocalDate aceptacion) {
    this.aceptacion = aceptacion;
  }

  public LocalDate getAceptacion() {
    return aceptacion;
  }

  public void setEjercicio(Long ejercicio) {
    this.ejercicio = ejercicio;
  }

  public Long getEjercicio() {
    return ejercicio;
  }

  public void setRegistro(LocalDateTime registro) {
    this.registro = registro;
  }

  public LocalDateTime getRegistro() {
    return registro;
  }

  public void setConsecutivo(String consecutivo) {
    this.consecutivo = consecutivo;
  }

  public String getConsecutivo() {
    return consecutivo;
  }

  public void setIdContratoEstatus(Long idContratoEstatus) {
    this.idContratoEstatus = idContratoEstatus;
  }

  public Long getIdContratoEstatus() {
    return idContratoEstatus;
  }

  public void setIdUsuario(Long idUsuario) {
    this.idUsuario = idUsuario;
  }

  public Long getIdUsuario() {
    return idUsuario;
  }

  public void setIdContrato(Long idContrato) {
    this.idContrato = idContrato;
  }

  public Long getIdContrato() {
    return idContrato;
  }

  public void setArranque(LocalDate arranque) {
    this.arranque = arranque;
  }

  public LocalDate getArranque() {
    return arranque;
  }

  public void setObservaciones(String observaciones) {
    this.observaciones = observaciones;
  }

  public String getObservaciones() {
    return observaciones;
  }

  public void setIdEmpresa(Long idEmpresa) {
    this.idEmpresa = idEmpresa;
  }

  public Long getIdEmpresa() {
    return idEmpresa;
  }

  public void setOrden(Long orden) {
    this.orden = orden;
  }

  public Long getOrden() {
    return orden;
  }

  public void setNoViviendas(Long noViviendas) {
    this.noViviendas = noViviendas;
  }

  public Long getNoViviendas() {
    return noViviendas;
  }

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}	
	
  @Transient
  @Override
  public Long getKey() {
  	return getIdContrato();
  }

  @Override
  public void setKey(Long key) {
  	this.idContrato = key;
  }

  @Override
  public String toString() {
    StringBuilder regresar= new StringBuilder();
    regresar.append("[");
		regresar.append(getClave());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getIdProyecto());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getEtapa());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getRecepcion());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getAceptacion());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getEjercicio());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getRegistro());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getConsecutivo());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getIdContratoEstatus());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getIdUsuario());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getIdContrato());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getArranque());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getObservaciones());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getIdEmpresa());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getOrden());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getNoViviendas());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getCosto());		
    regresar.append("]");
  	return regresar.toString();
  }

  @Override
  public Map toMap() {
    Map regresar = new HashMap();
		regresar.put("clave", getClave());
		regresar.put("idProyecto", getIdProyecto());
		regresar.put("etapa", getEtapa());
		regresar.put("recepcion", getRecepcion());
		regresar.put("aceptacion", getAceptacion());
		regresar.put("ejercicio", getEjercicio());
		regresar.put("registro", getRegistro());
		regresar.put("consecutivo", getConsecutivo());
		regresar.put("idContratoEstatus", getIdContratoEstatus());
		regresar.put("idUsuario", getIdUsuario());
		regresar.put("idContrato", getIdContrato());
		regresar.put("arranque", getArranque());
		regresar.put("observaciones", getObservaciones());
		regresar.put("idEmpresa", getIdEmpresa());
		regresar.put("orden", getOrden());
		regresar.put("noViviendas", getNoViviendas());
		regresar.put("costo", getCosto());
  	return regresar;
  }

  @Override
  public Object[] toArray() {
    Object[] regresar = new Object[]{
			getClave(), getIdProyecto(), getEtapa(), getRecepcion(), getAceptacion(), getEjercicio(), getRegistro(), getConsecutivo(), getIdContratoEstatus(), getIdUsuario(), getIdContrato(), getArranque(), getObservaciones(), getIdEmpresa(), getOrden(), getNoViviendas(), getCosto()
    };
    return regresar;
  }

  @Override
  public Object toValue(String name) {
    return Methods.getValue(this, name);
  }

  @Override
  public String toAllKeys() {
    StringBuilder regresar= new StringBuilder();
    regresar.append("|");
    regresar.append("idContrato~");
    regresar.append(getIdContrato());
    regresar.append("|");
    return regresar.toString();
  }

  @Override
  public String toKeys() {
    StringBuilder regresar= new StringBuilder();
    regresar.append(getIdContrato());
    return regresar.toString();
  }

  @Override
  public Class toHbmClass() {
    return TcKeetContratosDto.class;
  }

  @Override
  public boolean isValid() {
  	return getIdContrato()!= null && getIdContrato()!=-1L;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final TcKeetContratosDto other = (TcKeetContratosDto) obj;
    if (getIdContrato() != other.idContrato && (getIdContrato() == null || !getIdContrato().equals(other.idContrato))) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 67 * hash + (getIdContrato() != null ? getIdContrato().hashCode() : 0);
    return hash;
  }
}