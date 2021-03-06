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
@Table(name="tc_mantic_ventas_bitacora")
public class TcManticVentasBitacoraDto implements IBaseDto, Serializable {
		
  private static final long serialVersionUID=1L;
  @Id
  @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column (name="id_venta_bitacora")
  private Long idVentaBitacora;
  @Column (name="justificacion")
  private String justificacion;
  @Column (name="id_usuario")
  private Long idUsuario;
  @Column (name="id_venta")
  private Long idVenta;
  @Column (name="id_venta_estatus")
  private Long idVentaEstatus;
  @Column (name="registro")
  private LocalDateTime registro;
  @Column (name="consecutivo")
  private Long consecutivo;
  @Column (name="importe")
  private Double importe;

  public TcManticVentasBitacoraDto() {
    this(new Long(-1L));
  }

  public TcManticVentasBitacoraDto(Long key) {
    this(new Long(-1L), null, null, null, null, 0L, 0D);
    setKey(key);
  }

  public TcManticVentasBitacoraDto(Long idVentaBitacora, String justificacion, Long idUsuario, Long idVenta, Long idVentaEstatus, Long consecutivo, Double importe) {
    setIdVentaBitacora(idVentaBitacora);
    setJustificacion(justificacion);
    setIdUsuario(idUsuario);
    setIdVenta(idVenta);
    setIdVentaEstatus(idVentaEstatus);
    setRegistro(LocalDateTime.now());
    setConsecutivo(consecutivo);
    setImporte(importe);
  }
	
  public void setIdVentaBitacora(Long idVentaBitacora) {
    this.idVentaBitacora = idVentaBitacora;
  }

  public Long getIdVentaBitacora() {
    return idVentaBitacora;
  }

  public void setJustificacion(String justificacion) {
    this.justificacion = justificacion;
  }

  public String getJustificacion() {
    return justificacion;
  }

  public void setIdUsuario(Long idUsuario) {
    this.idUsuario = idUsuario;
  }

  public Long getIdUsuario() {
    return idUsuario;
  }

  public void setIdVenta(Long idVenta) {
    this.idVenta = idVenta;
  }

  public Long getIdVenta() {
    return idVenta;
  }

  public void setIdVentaEstatus(Long idVentaEstatus) {
    this.idVentaEstatus = idVentaEstatus;
  }

  public Long getIdVentaEstatus() {
    return idVentaEstatus;
  }

  public void setRegistro(LocalDateTime registro) {
    this.registro = registro;
  }

  public LocalDateTime getRegistro() {
    return registro;
  }

	public Long getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo=consecutivo;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe=importe;
	}

  @Transient
  @Override
  public Long getKey() {
  	return getIdVentaBitacora();
  }

  @Override
  public void setKey(Long key) {
  	this.idVentaBitacora = key;
  }

  @Override
  public String toString() {
    StringBuilder regresar= new StringBuilder();
    regresar.append("[");
		regresar.append(getIdVentaBitacora());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getJustificacion());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getIdUsuario());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getIdVenta());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getIdVentaEstatus());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getRegistro());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getConsecutivo());
		regresar.append(Constantes.SEPARADOR);
		regresar.append(getImporte());
    regresar.append("]");
  	return regresar.toString();
  }

  @Override
  public Map toMap() {
    Map regresar = new HashMap();
		regresar.put("idVentaBitacora", getIdVentaBitacora());
		regresar.put("justificacion", getJustificacion());
		regresar.put("idUsuario", getIdUsuario());
		regresar.put("idVenta", getIdVenta());
		regresar.put("idVentaEstatus", getIdVentaEstatus());
		regresar.put("registro", getRegistro());
		regresar.put("consecutivo", getConsecutivo());
		regresar.put("importe", getImporte());
  	return regresar;
  }

  @Override
  public Object[] toArray() {
    Object[] regresar = new Object[]{
    getIdVentaBitacora(), getJustificacion(), getIdUsuario(), getIdVenta(), getIdVentaEstatus(), getRegistro(), getConsecutivo(), getImporte()
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
    regresar.append("idVentaBitacora~");
    regresar.append(getIdVentaBitacora());
    regresar.append("|");
    return regresar.toString();
  }

  @Override
  public String toKeys() {
    StringBuilder regresar= new StringBuilder();
    regresar.append(getIdVentaBitacora());
    return regresar.toString();
  }

  @Override
  public Class toHbmClass() {
    return TcManticVentasBitacoraDto.class;
  }

  @Override
  public boolean isValid() {
  	return getIdVentaBitacora()!= null && getIdVentaBitacora()!=-1L;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final TcManticVentasBitacoraDto other = (TcManticVentasBitacoraDto) obj;
    if (getIdVentaBitacora() != other.idVentaBitacora && (getIdVentaBitacora() == null || !getIdVentaBitacora().equals(other.idVentaBitacora))) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 67 * hash + (getIdVentaBitacora() != null ? getIdVentaBitacora().hashCode() : 0);
    return hash;
  }

}


