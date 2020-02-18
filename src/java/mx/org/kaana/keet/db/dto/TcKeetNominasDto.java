package mx.org.kaana.keet.db.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
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
@Table(name="tc_keet_nominas")
public class TcKeetNominasDto implements IBaseDto, Serializable {
		
  private static final long serialVersionUID=1L;
  @Column (name="fecha_dispersion")
  private Date fechaDispersion;
  @Column (name="id_nomina_periodo")
  private Long idNominaPeriodo;
  @Column (name="id_usuario")
  private Long idUsuario;
  @Column (name="id_tipo_nomina")
  private Long idTipoNomina;
  @Id
  @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column (name="id_nomina")
  private Long idNomina;
  @Column (name="fecha_pago")
  private Date fechaPago;
  @Column (name="registro")
  private Timestamp registro;

  public TcKeetNominasDto() {
    this(new Long(-1L));
  }

  public TcKeetNominasDto(Long key) {
    this(new Date(Calendar.getInstance().getTimeInMillis()), null, null, null, new Long(-1L), new Date(Calendar.getInstance().getTimeInMillis()));
    setKey(key);
  }

  public TcKeetNominasDto(Date fechaDispersion, Long idNominaPeriodo, Long idUsuario, Long idTipoNomina, Long idNomina, Date fechaPago) {
    setFechaDispersion(fechaDispersion);
    setIdNominaPeriodo(idNominaPeriodo);
    setIdUsuario(idUsuario);
    setIdTipoNomina(idTipoNomina);
    setIdNomina(idNomina);
    setFechaPago(fechaPago);
    setRegistro(new Timestamp(Calendar.getInstance().getTimeInMillis()));
  }
	
  public void setFechaDispersion(Date fechaDispersion) {
    this.fechaDispersion = fechaDispersion;
  }

  public Date getFechaDispersion() {
    return fechaDispersion;
  }

  public void setIdNominaPeriodo(Long idNominaPeriodo) {
    this.idNominaPeriodo = idNominaPeriodo;
  }

  public Long getIdNominaPeriodo() {
    return idNominaPeriodo;
  }

  public void setIdUsuario(Long idUsuario) {
    this.idUsuario = idUsuario;
  }

  public Long getIdUsuario() {
    return idUsuario;
  }

  public void setIdTipoNomina(Long idTipoNomina) {
    this.idTipoNomina = idTipoNomina;
  }

  public Long getIdTipoNomina() {
    return idTipoNomina;
  }

  public void setIdNomina(Long idNomina) {
    this.idNomina = idNomina;
  }

  public Long getIdNomina() {
    return idNomina;
  }

  public void setFechaPago(Date fechaPago) {
    this.fechaPago = fechaPago;
  }

  public Date getFechaPago() {
    return fechaPago;
  }

  public void setRegistro(Timestamp registro) {
    this.registro = registro;
  }

  public Timestamp getRegistro() {
    return registro;
  }

  @Transient
  @Override
  public Long getKey() {
  	return getIdNomina();
  }

  @Override
  public void setKey(Long key) {
  	this.idNomina = key;
  }

  @Override
  public String toString() {
    StringBuilder regresar= new StringBuilder();
    regresar.append("[");
		regresar.append(getFechaDispersion());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getIdNominaPeriodo());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getIdUsuario());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getIdTipoNomina());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getIdNomina());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getFechaPago());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getRegistro());
    regresar.append("]");
  	return regresar.toString();
  }

  @Override
  public Map toMap() {
    Map regresar = new HashMap();
		regresar.put("fechaDispersion", getFechaDispersion());
		regresar.put("idNominaPeriodo", getIdNominaPeriodo());
		regresar.put("idUsuario", getIdUsuario());
		regresar.put("idTipoNomina", getIdTipoNomina());
		regresar.put("idNomina", getIdNomina());
		regresar.put("fechaPago", getFechaPago());
		regresar.put("registro", getRegistro());
  	return regresar;
  }

  @Override
  public Object[] toArray() {
    Object[] regresar = new Object[]{
    getFechaDispersion(), getIdNominaPeriodo(), getIdUsuario(), getIdTipoNomina(), getIdNomina(), getFechaPago(), getRegistro()
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
    regresar.append("idNomina~");
    regresar.append(getIdNomina());
    regresar.append("|");
    return regresar.toString();
  }

  @Override
  public String toKeys() {
    StringBuilder regresar= new StringBuilder();
    regresar.append(getIdNomina());
    return regresar.toString();
  }

  @Override
  public Class toHbmClass() {
    return TcKeetNominasDto.class;
  }

  @Override
  public boolean isValid() {
  	return getIdNomina()!= null && getIdNomina()!=-1L;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final TcKeetNominasDto other = (TcKeetNominasDto) obj;
    if (getIdNomina() != other.idNomina && (getIdNomina() == null || !getIdNomina().equals(other.idNomina))) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 67 * hash + (getIdNomina() != null ? getIdNomina().hashCode() : 0);
    return hash;
  }
}