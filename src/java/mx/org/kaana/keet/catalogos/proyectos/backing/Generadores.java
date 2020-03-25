package mx.org.kaana.keet.catalogos.proyectos.backing;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import mx.org.kaana.keet.catalogos.proyectos.beans.Generador;
import mx.org.kaana.libs.formato.Error;
import mx.org.kaana.libs.pagina.JsfBase;
import mx.org.kaana.libs.recurso.Configuracion;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import mx.org.kaana.keet.catalogos.proyectos.comun.IBaseImportacion;
import mx.org.kaana.keet.enums.EArchivosProyectos;
import mx.org.kaana.libs.pagina.UISelectEntity;

/**
 *@company KAANA
 *@project KAJOOL (Control system polls)
 *@date 7/05/2018
 *@time 03:29:13 PM
 *@author Team Developer 2016 <team.developer@kaana.org.mx>
 */

@Named(value= "keetCatalogosProyectosGeneradores")
@ViewScoped
public class Generadores extends IBaseImportacion implements Serializable {

	private static final Log LOG              = LogFactory.getLog(Generadores.class);
	private static final long serialVersionUID= 2672741451185244787L;  
	
	@PostConstruct
  @Override
  protected void init() {		
    try {      			
			super.initBase(EArchivosProyectos.GENERADORES);
    } // try
    catch (Exception e) {
      Error.mensaje(e);
      JsfBase.addMessageError(e);
    } // catch		
  } // init	
		
	@Override
	public Generador toArchivo(){
		EArchivosProyectos tipoArchivo= (EArchivosProyectos) this.attrs.get("tipoArchivo");
		Generador regresar= new Generador(		
			getDataCombo("tipoGenerador","tiposGeneradores").toString("nombre"),
			((UISelectEntity)this.attrs.get("tipoGenerador")).getKey(),
			this.getFile().getName(), 			
			this.getFile().getRuta(), 			
			this.getFile().getFileSize(), 			
			JsfBase.getIdUsuario(), 			
			this.registroProyecto.getProyecto().getIdProyecto(),
			this.getFile().getFormat().getIdTipoArchivo()< 0L ? 1L : this.getFile().getFormat().getIdTipoArchivo(), 			
			-1L,
			(String)this.attrs.get("observaciones"), 
			Configuracion.getInstance().getPropiedadSistemaServidor(tipoArchivo.getPath()).concat(this.getFile().getRuta()).concat(this.getFile().getName()),						
			this.getFile().getOriginal()	
		);				
		return regresar;
	} // toPrototipoArchivo	
}