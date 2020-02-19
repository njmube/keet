package mx.org.kaana.mantic.db.dto;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Lob;
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
@Table(name="tc_mantic_servicios_estatus")
public class TcManticServiciosEstatusDto implements IBaseDto, Serializable {
		
  private static final long serialVersionUID=1L;
  @Id
  @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column (name="id_servicio_estatus")
  private Long idServicioEstatus;
  @Column (name="id_justificacion")
  private Long idJustificacion;
  @Column (name="estatus_asociados")
  private String estatusAsociados;
  @Column (name="decripcion")
  private String decripcion;
  @Column (name="nombre")
  private String nombre;
  @Column (name="registro")
  private LocalDateTime registro;

  public TcManticServiciosEstatusDto() {
    this(new Long(-1L));
  }

  public TcManticServiciosEstatusDto(Long key) {
    this(new Long(-1L), null, null, null, null);
    setKey(key);
  }

  public TcManticServiciosEstatusDto(Long idServicioEstatus, Long idJustificacion, String estatusAsociados, String decripcion, String nombre) {
    setIdServicioEstatus(idServicioEstatus);
    setIdJustificacion(idJustificacion);
    setEstatusAsociados(estatusAsociados);
    setDecripcion(decripcion);
    setNombre(nombre);
    setRegistro(LocalDateTime.now());
  }
	
  public void setIdServicioEstatus(Long idServicioEstatus) {
    this.idServicioEstatus = idServicioEstatus;
  }

  public Long getIdServicioEstatus() {
    return idServicioEstatus;
  }

  public void setIdJustificacion(Long idJustificacion) {
    this.idJustificacion = idJustificacion;
  }

  public Long getIdJustificacion() {
    return idJustificacion;
  }

  public void setEstatusAsociados(String estatusAsociados) {
    this.estatusAsociados = estatusAsociados;
  }

  public String getEstatusAsociados() {
    return estatusAsociados;
  }

  public void setDecripcion(String decripcion) {
    this.decripcion = decripcion;
  }

  public String getDecripcion() {
    return decripcion;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public void setRegistro(LocalDateTime registro) {
    this.registro = registro;
  }

  public LocalDateTime getRegistro() {
    return registro;
  }

  @Transient
  @Override
  public Long getKey() {
  	return getIdServicioEstatus();
  }

  @Override
  public void setKey(Long key) {
  	this.idServicioEstatus = key;
  }

  @Override
  public String toString() {
    StringBuilder regresar= new StringBuilder();
    regresar.append("[");
		regresar.append(getIdServicioEstatus());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getIdJustificacion());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getEstatusAsociados());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getDecripcion());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getNombre());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getRegistro());
    regresar.append("]");
  	return regresar.toString();
  }

  @Override
  public Map toMap() {
    Map regresar = new HashMap();
		regresar.put("idServicioEstatus", getIdServicioEstatus());
		regresar.put("idJustificacion", getIdJustificacion());
		regresar.put("estatusAsociados", getEstatusAsociados());
		regresar.put("decripcion", getDecripcion());
		regresar.put("nombre", getNombre());
		regresar.put("registro", getRegistro());
  	return regresar;
  }

  @Override
  public Object[] toArray() {
    Object[] regresar = new Object[]{
    getIdServicioEstatus(), getIdJustificacion(), getEstatusAsociados(), getDecripcion(), getNombre(), getRegistro()
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
    regresar.append("idServicioEstatus~");
    regresar.append(getIdServicioEstatus());
    regresar.append("|");
    return regresar.toString();
  }

  @Override
  public String toKeys() {
    StringBuilder regresar= new StringBuilder();
    regresar.append(getIdServicioEstatus());
    return regresar.toString();
  }

  @Override
  public Class toHbmClass() {
    return TcManticServiciosEstatusDto.class;
  }

  @Override
  public boolean isValid() {
  	return getIdServicioEstatus()!= null && getIdServicioEstatus()!=-1L;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final TcManticServiciosEstatusDto other = (TcManticServiciosEstatusDto) obj;
    if (getIdServicioEstatus() != other.idServicioEstatus && (getIdServicioEstatus() == null || !getIdServicioEstatus().equals(other.idServicioEstatus))) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 67 * hash + (getIdServicioEstatus() != null ? getIdServicioEstatus().hashCode() : 0);
    return hash;
  }

}


