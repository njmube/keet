package mx.org.kaana.keet.nomina.reglas;

import java.io.Serializable;
import java.util.List;
import mx.org.kaana.kajool.db.comun.hibernate.DaoFactory;
import mx.org.kaana.keet.db.dto.TcKeetNominasDto;
import mx.org.kaana.keet.db.dto.TcKeetNominasProveedoresDto;
import mx.org.kaana.keet.nomina.beans.Rubro;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

/**
 *@company KAANA
 *@project KAJOOL (Control system polls)
 *@date 29/04/2020
 *@time 09:14:48 PM 
 *@author Team Developer 2016 <team.developer@kaana.org.mx>
 */

public class Factura implements Serializable {

	private static final long serialVersionUID=237030342701981082L;
  private static final Log LOG=LogFactory.getLog(Nomina.class);
	
	private Session sesion;
	private TcKeetNominasDto nomina;

	public Factura(Session sesion, TcKeetNominasDto nomina) {
		this.sesion=sesion;
		this.nomina=nomina;
	}
	
	public void process(TcKeetNominasProveedoresDto proveedor) throws Exception {
		List<Rubro> rubros= null;
		
		DaoFactory.getInstance().insert(this.sesion, proveedor);
		// ALMACENAR EL DETALLE DE CALCULO DE LA NOMINA DEL PROVEEDOR
    for (Rubro rubro: rubros) {
			rubro.setIdNominaProveedor(proveedor.getIdNominaProveedor());
  		DaoFactory.getInstance().insert(this.sesion, rubro);
		} // for		
	}
	
}
