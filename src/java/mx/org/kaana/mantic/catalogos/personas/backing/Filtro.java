package mx.org.kaana.mantic.catalogos.personas.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import mx.org.kaana.kajool.db.comun.sql.Entity;
import mx.org.kaana.libs.formato.Error;
import mx.org.kaana.kajool.enums.EAccion;
import mx.org.kaana.kajool.enums.EFormatoDinamicos;
import mx.org.kaana.kajool.enums.ETipoMensaje;
import mx.org.kaana.kajool.reglas.comun.Columna;
import mx.org.kaana.kajool.reglas.comun.FormatCustomLazy;
import mx.org.kaana.libs.Constantes;
import mx.org.kaana.libs.formato.Cadena;
import mx.org.kaana.libs.pagina.IBaseFilter;
import mx.org.kaana.libs.pagina.JsfBase;
import mx.org.kaana.libs.pagina.UIBackingUtilities;
import mx.org.kaana.libs.pagina.UISelectEntity;
import mx.org.kaana.libs.pagina.UISelectItem;
import mx.org.kaana.libs.reflection.Methods;
import mx.org.kaana.mantic.catalogos.personas.beans.RegistroPersona;
import mx.org.kaana.mantic.catalogos.personas.reglas.Transaccion;
import mx.org.kaana.mantic.catalogos.personas.reglas.Gestor;
import mx.org.kaana.mantic.enums.ETipoPersona;

@Named(value = "manticCatalogosPersonasFiltro")
@ViewScoped
public class Filtro extends IBaseFilter implements Serializable {

  private static final long serialVersionUID = 8793667741599428879L;

  @PostConstruct
  @Override
  protected void init() {
    try {			
			this.attrs.put("nombres", "");
			this.attrs.put("paterno", "");
			this.attrs.put("materno", "");
			this.attrs.put("rfc", "");
			this.attrs.put("curp", "");
      this.attrs.put("sortOrder", "order by tc_mantic_personas.nombres");
      this.attrs.put("idEmpresa", JsfBase.getAutentifica().getEmpresa().getIdEmpresa());
      this.attrs.put("sucursales", JsfBase.getAutentifica().getEmpresa().getDependencias());
      loadTiposPersonas();			
    } // try
    catch (Exception e) {
      Error.mensaje(e);
      JsfBase.addMessageError(e);
    } // catch		
  } // init
  
  private void loadTiposPersonas() throws Exception {
    Gestor gestor = new Gestor();
    gestor.loadTiposPersonas();
    this.attrs.put("tiposPersonas", gestor.getTiposPersonas());
    this.attrs.put("tipoPersona", UIBackingUtilities.toFirstKeySelectEntity(gestor.getTiposPersonas()));
    this.attrs.put("puestos", gestor.loadPuestos());
    this.attrs.put("puesto", UIBackingUtilities.toFirstKeySelectItem((List<UISelectItem>)this.attrs.get("puestos")));
    this.attrs.put("departamentos", gestor.loadDepartamentos());
    this.attrs.put("departamento", UIBackingUtilities.toFirstKeySelectItem((List<UISelectItem>)this.attrs.get("departamentos")));
  } // loadTiposPersonas  
  
  @Override
  public void doLoad() {
    List<Columna> campos     = null;
		Map<String, Object>params= null;
    try {
			params= toPrepare();
      campos = new ArrayList<>();
      campos.add(new Columna("nombres", EFormatoDinamicos.MAYUSCULAS));
      campos.add(new Columna("materno", EFormatoDinamicos.MAYUSCULAS));
      campos.add(new Columna("paterno", EFormatoDinamicos.MAYUSCULAS));
      campos.add(new Columna("rfc", EFormatoDinamicos.MAYUSCULAS));
      campos.add(new Columna("curp", EFormatoDinamicos.MAYUSCULAS));
      campos.add(new Columna("sexo", EFormatoDinamicos.MAYUSCULAS));
      campos.add(new Columna("tipoPersona", EFormatoDinamicos.MAYUSCULAS));     
      this.attrs.put("idTipoPersona",((UISelectEntity)this.attrs.get("tipoPersona")).getKey().equals(-1L)?toAllTiposPersonas():((UISelectEntity)this.attrs.get("tipoPersona")).getKey());
      this.lazyModel = new FormatCustomLazy("VistaPersonasDto", "filter", params, campos);
      UIBackingUtilities.resetDataTable();
    } // try
    catch (Exception e) {
      Error.mensaje(e);
      JsfBase.addMessageError(e);
    } // catch
    finally {
      Methods.clean(campos);
      Methods.clean(params);
    } // finally		
  } // doLoad

	private Map<String, Object> toPrepare(){
		Map<String, Object> regresar= null;
		StringBuilder condicion     = null;
		try {
			condicion= new StringBuilder("");			
			if(!Cadena.isVacio(this.attrs.get("nombres")))
				condicion.append("tc_mantic_personas.nombres like '%").append(this.attrs.get("nombres")).append("%' and ");
			if(!Cadena.isVacio(this.attrs.get("paterno")))
				condicion.append("tc_mantic_personas.paterno like '%").append(this.attrs.get("paterno")).append("%' and ");
			if(!Cadena.isVacio(this.attrs.get("materno")))
				condicion.append("tc_mantic_personas.materno like '%").append(this.attrs.get("materno")).append("%' and ");
			if(!Cadena.isVacio(this.attrs.get("rfc")))
				condicion.append("tc_mantic_personas.rfc like '%").append(this.attrs.get("rfc")).append("%' and ");
			if(!Cadena.isVacio(this.attrs.get("curp")))
				condicion.append("tc_mantic_personas.curp like '%").append(this.attrs.get("curp")).append("%' and ");			
			condicion.append("tc_mantic_personas.id_tipo_persona in (").append(this.attrs.get("idTipoPersona")).append(") and ");
			condicion.append("tr_mantic_empresa_personal.id_empresa in (").append(this.attrs.get("sucursales")).append(") and ");
			condicion.append("tr_mantic_empresa_personal.id_activo in (").append(this.attrs.get("estatus")).append(") and ");			
			if(!Cadena.isVacio(this.attrs.get("departamento")) && Long.valueOf(this.attrs.get("departamento").toString())>=1)
				condicion.append("tr_mantic_empresa_personal.id_departamento=").append(this.attrs.get("departamento")).append(" and ");
			if(!Cadena.isVacio(this.attrs.get("puesto")) && Long.valueOf(this.attrs.get("puesto").toString())>=1)
				condicion.append("tc_mantic_puestos.id_puesto=").append(this.attrs.get("puesto")).append(" and ");
			regresar= new HashMap<>();
			regresar.put(Constantes.SQL_CONDICION, condicion.substring(0, condicion.length()-4));
		} // try
		catch (Exception e) {			
			throw e;
		} // catch		
		return regresar;
	} // toPrepare
	
	private String toAllTiposPersonas () {
    StringBuilder regresar= new StringBuilder();
    List<UISelectEntity> tiposPersonas= (List<UISelectEntity>) this.attrs.get("tiposPersonas");
    for (UISelectEntity tipoPersona: tiposPersonas)
      regresar.append(tipoPersona.getKey()).append(",");
    return regresar.substring(0,regresar.length()-1);
  } // toAllTiposPersonas
	
  public String doAccion(String accion) {
    EAccion eaccion= null;
		try {
			eaccion= EAccion.valueOf(accion.toUpperCase());
			JsfBase.setFlashAttribute("accion", eaccion);		
			JsfBase.setFlashAttribute("tipoPersona", ETipoPersona.EMPLEADO.getIdTipoPersona());		
			JsfBase.setFlashAttribute("retorno", "filtro");		
			JsfBase.setFlashAttribute("idPersona", (eaccion.equals(EAccion.MODIFICAR) || eaccion.equals(EAccion.CONSULTAR)) ? ((Entity)this.attrs.get("seleccionado")).getKey() : -1L);
		} // try
		catch (Exception e) {
			Error.mensaje(e);
			JsfBase.addMessageError(e);			
		} // catch
		return "accion".concat(Constantes.REDIRECIONAR);
  } // doAccion

  public void doEliminar() {
		Transaccion transaccion= null;
		Entity seleccionado    = null;
		RegistroPersona persona= null;
		try {
			seleccionado= (Entity) this.attrs.get("seleccionado");			
			persona= new RegistroPersona();
			persona.setIdPersona(seleccionado.getKey());
			transaccion= new Transaccion(persona);
			if(transaccion.ejecutar(EAccion.ELIMINAR))
				JsfBase.addMessage("Eliminar persona", "La persona se ha eliminado correctamente.", ETipoMensaje.INFORMACION);
			else
				JsfBase.addMessage("Eliminar persona", "Ocurri� un error al eliminar la persona seleccionada.", ETipoMensaje.ERROR);								
		} // try
		catch (Exception e) {
			Error.mensaje(e);
			JsfBase.addMessageError(e);			
		} // catch		
  } // doEliminar
}
