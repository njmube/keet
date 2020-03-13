package mx.org.kaana.keet.catalogos.prototipos.reglas;

import java.util.HashMap;
import java.util.Map;
import mx.org.kaana.kajool.db.comun.hibernate.DaoFactory;
import mx.org.kaana.kajool.enums.EAccion;
import mx.org.kaana.kajool.reglas.IBaseTnx;
import mx.org.kaana.keet.catalogos.prototipos.beans.Prototipos;
import mx.org.kaana.keet.catalogos.prototipos.beans.SistemaConstructivo;
import mx.org.kaana.keet.db.dto.TcKeetPrototiposDto;
import mx.org.kaana.libs.pagina.JsfBase;

import org.hibernate.Session;

public class Transaccion extends IBaseTnx {

	private Prototipos prototipo;	

	public Transaccion(Prototipos prototipo) {
		this.prototipo    = prototipo;	
	}

	@Override
	protected boolean ejecutar(Session sesion, EAccion accion) throws Exception {		
		boolean regresar          = false;
		Map<String, Object> params= new HashMap<>();
		try {
			switch(accion){
				case AGREGAR:
					regresar= DaoFactory.getInstance().insert(sesion, this.prototipo)>= 1L;
					for(SistemaConstructivo item: this.prototipo.getIkSistemasConstructivos().getRegistros()){
						item.setIdPrototipo(this.prototipo.getIdPrototipo());
						item.setIdUsuario(JsfBase.getIdUsuario());
						regresar= DaoFactory.getInstance().insert(sesion, item)>= 1L;
					} // for
					break;
				case MODIFICAR:
					regresar= DaoFactory.getInstance().update(sesion, this.prototipo)>= 1L;
					for(SistemaConstructivo item: this.prototipo.getIkSistemasConstructivos().getRegistros()){
						item.setIdPrototipo(this.prototipo.getIdPrototipo());
						actualizarConstructivo(sesion, item);
					} // for
					break;				
				case ELIMINAR:
					for(SistemaConstructivo item: this.prototipo.getIkSistemasConstructivos().getRegistros())
						DaoFactory.getInstance().delete(sesion, item);
					regresar= DaoFactory.getInstance().delete(sesion, this.prototipo)>= 1L;
					break;
			} // switch
		} // try
		catch (Exception e) {			
			throw new Exception(e);
		} // catch		
		return regresar;
	}	// ejecutar

	private void actualizarConstructivo(Session sesion, SistemaConstructivo item) throws Exception {
		try {
			switch(item.getAccion()){
				case INSERT:
					DaoFactory.getInstance().insert(sesion, item);
						break;	
				case UPDATE:
					DaoFactory.getInstance().update(sesion, item);
					break;
				case DELETE:
					DaoFactory.getInstance().delete(sesion, item);
					break;
			} // switch
		} // try
		catch (Exception e) {			
			throw new Exception(e);
		} // catch		
	}
	
}