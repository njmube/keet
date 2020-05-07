package mx.org.kaana.keet.nomina.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import mx.org.kaana.kajool.db.comun.sql.Entity;
import mx.org.kaana.kajool.db.comun.sql.Value;
import mx.org.kaana.libs.formato.Error;
import mx.org.kaana.kajool.enums.EFormatoDinamicos;
import mx.org.kaana.kajool.reglas.comun.Columna;
import mx.org.kaana.kajool.reglas.comun.FormatCustomLazy;
import mx.org.kaana.kajool.reglas.comun.FormatLazyModel;
import mx.org.kaana.keet.comun.Catalogos;
import mx.org.kaana.libs.Constantes;
import mx.org.kaana.libs.formato.Cadena;
import mx.org.kaana.libs.pagina.IBaseFilter;
import mx.org.kaana.libs.pagina.JsfBase;
import mx.org.kaana.libs.pagina.UIBackingUtilities;
import mx.org.kaana.libs.pagina.UIEntity;
import mx.org.kaana.libs.pagina.UISelectEntity;
import mx.org.kaana.libs.reflection.Methods;

@Named(value = "keetNominasPersonas")
@ViewScoped
public class Personas extends IBaseFilter implements Serializable {

	private static final long serialVersionUID = 6319984968937774153L;

	private FormatLazyModel lazyDetalle;
	private FormatLazyModel lazyDestajo;

	public FormatLazyModel getLazyDetalle() {
		return lazyDetalle;
	}

	public void setLazyDetalle(FormatLazyModel lazyDetalle) {
		this.lazyDetalle=lazyDetalle;
	}

	public FormatLazyModel getLazyDestajo() {
		return lazyDestajo;
	}

	public void setLazyDestajo(FormatLazyModel lazyDestajo) {
		this.lazyDestajo=lazyDestajo;
	}
	
	@PostConstruct
  @Override
  protected void init() {
    try {
			this.attrs.put("isMatriz", JsfBase.getAutentifica().getEmpresa().isMatriz());
			Long idNomina= (Long)JsfBase.getFlashAttribute("idNomina");
			this.loadCatalogs();
			if(!Cadena.isVacio(idNomina)) {
				Entity entity= new Entity(idNomina);
				entity.put("idNomina", new Value("idNomina", idNomina));
  			entity.put("nombreCompleto", new Value("nombreCompleto", (String)JsfBase.getFlashAttribute("nombreCompleto")));
  			entity.put("idEmpresaPersona", new Value("idEmpresaPersona", (Long)JsfBase.getFlashAttribute("idEmpresaPersona")));
				this.attrs.put("idNomina", new UISelectEntity(entity));
				this.attrs.put("idEmpresaPersona", new UISelectEntity((Long)JsfBase.getFlashAttribute("idEmpresaPersona")));
				this.attrs.put("seleccionado", entity);
				this.doLoad();
				if(!Cadena.isVacio(entity.toLong("idEmpresaPersona")))
					this.doLoadDetalle();
				this.attrs.put("idNomina", new UISelectEntity(-1L));
				this.attrs.put("idEmpresaPersona", new UISelectEntity(-1L));
				this.attrs.put("destajos", false);
		  }
    } // try
    catch (Exception e) {
      Error.mensaje(e);
      JsfBase.addMessageError(e);
    } // catch		
  } // init

  @Override
  public void doLoad() {
    List<Columna> columns    = null;
		Map<String, Object>params= null;
    try {
      params= this.toPrepare();	
			params.put("sortOrder", "order by nomina desc");
      columns= new ArrayList<>();
      columns.add(new Columna("percepciones", EFormatoDinamicos.MILES_SIN_DECIMALES));
      columns.add(new Columna("deducciones", EFormatoDinamicos.MILES_SIN_DECIMALES));
      columns.add(new Columna("neto", EFormatoDinamicos.MILES_SIN_DECIMALES));
      this.lazyModel = new FormatCustomLazy("VistaNominaConsultasDto", "personas", params, columns);
      UIBackingUtilities.resetDataTable();
    } // try
    catch (Exception e) {
      Error.mensaje(e);
      JsfBase.addMessageError(e);
    } // catch
    finally {
      Methods.clean(params);
      Methods.clean(columns);
    } // finally		
  } // doLoad

	private void loadEmpresas() {
		Map<String, Object>params= null;
		List<Columna> columns    = null;
		try {
			params = new HashMap<>();
			columns= new ArrayList<>();			
			params.put("sucursales", JsfBase.getAutentifica().getEmpresa().getSucursales());			
      columns.add(new Columna("clave", EFormatoDinamicos.MAYUSCULAS));
      columns.add(new Columna("nombre", EFormatoDinamicos.MAYUSCULAS));
      this.attrs.put("empresas", (List<UISelectEntity>) UIEntity.seleccione("TcManticEmpresasDto", "empresas", params, columns, "clave"));
			this.attrs.put("idEmpresa", this.toDefaultSucursal((List<UISelectEntity>)this.attrs.get("sucursales")));
		} // try
		catch (Exception e) {
			JsfBase.addMessageError(e);
			Error.mensaje(e);			
		} // catch		
    finally{
			Methods.clean(params);
		}	// finally	
	} // loadEmpresas

  public void doAccion() {
		
  } // doAccion

	private Map<String, Object> toPrepare() {
	  Map<String, Object> regresar  = new HashMap<>();	
		StringBuilder sb              = new StringBuilder();
		if(!Cadena.isVacio(this.attrs.get("idEmpresa")) && ((UISelectEntity)this.attrs.get("idEmpresa")).getKey()>= 1L)				
			sb.append("(tc_keet_nominas.id_empresa in (").append(((UISelectEntity)this.attrs.get("idEmpresa")).getKey()).append(")) and ");
		else
			sb.append("(tc_keet_nominas.id_empresa in (").append(JsfBase.getAutentifica().getEmpresa().getSucursales()).append(")) and ");
  	regresar.put("sucursales", JsfBase.getAutentifica().getEmpresa().getSucursales());
		if(!Cadena.isVacio(this.attrs.get("idNomina")) && ((UISelectEntity)this.attrs.get("idNomina")).getKey()>= 1L)
			sb.append("tc_keet_nominas.id_nomina=").append(this.attrs.get("idNomina")).append(" and ");
		if(!Cadena.isVacio(this.attrs.get("idEmpresaPersona")) && ((UISelectEntity)this.attrs.get("idEmpresaPersona")).getKey()>= 1L)
			sb.append("tr_mantic_empresa_personal.id_empresa_persona=").append(this.attrs.get("idEmpresaPersona")).append(" and ");
		if(!Cadena.isVacio(this.attrs.get("ejercicio")) && ((UISelectEntity)this.attrs.get("ejercicio")).getKey()>= 1L)				
			sb.append("tc_keet_nominas_periodos.ejercicio = ").append(((UISelectEntity)this.attrs.get("ejercicio")).getKey()).append(" and ");
		if(!Cadena.isVacio(this.attrs.get("semana")) && ((UISelectEntity)this.attrs.get("semana")).getKey()>= 1L)				
			sb.append("tc_keet_nominas_periodos.orden = ").append(((UISelectEntity)this.attrs.get("semana")).getKey()).append(" and ");
		if(!Cadena.isVacio(this.attrs.get("idTipoNomina")) && ((UISelectEntity)this.attrs.get("idTipoNomina")).getKey()>= 1L)				
			sb.append("tc_keet_nomina.id_tipo_nomina= ").append(((UISelectEntity)this.attrs.get("idTipoNomina")).getKey()).append(" and ");
		if(!Cadena.isVacio(this.attrs.get("estatus")) && ((UISelectEntity)this.attrs.get("estatus")).getKey()>= 1L)				
			sb.append("tc_keet_nomina.id_nomina_estatus = ").append(((UISelectEntity)this.attrs.get("estatus")).getKey()).append(" and ");
		if(this.attrs.get("idPuesto")!= null && !Cadena.isVacio(this.attrs.get("idPuesto")) && Long.valueOf(this.attrs.get("idPuesto").toString())>= 1L)
			sb.append("tc_mantic_puestos.id_puesto=").append(this.attrs.get("idPuesto")).append(" and ");
		if(this.attrs.get("idDepartamento")!= null && !Cadena.isVacio(this.attrs.get("idDepartamento")) && Long.valueOf(this.attrs.get("idDepartamento").toString())>= 1L)
			sb.append("tc_keet_departamentos.id_departamento=").append(this.attrs.get("idDepartamento")).append(" and ");
		if(this.attrs.get("idContratista")!= null && !Cadena.isVacio(this.attrs.get("idContratista")) && ((UISelectEntity)this.attrs.get("idContratista")).getKey() >= 1L)			
			if(((UISelectEntity)this.attrs.get("idContratista")).getKey()== 999L)		
				sb.append("tr_mantic_empresa_personal.id_contratista is null and ");
			else
				sb.append("tr_mantic_empresa_personal.id_contratista=").append(this.attrs.get("idContratista")).append(" and ");
		if(this.attrs.get("nombre")!= null && !Cadena.isVacio(this.attrs.get("nombre"))) {
			String nombre= ((String)this.attrs.get("nombre")).toUpperCase().replaceAll("(,| |\\t)+", ".*.*");
  		sb.append("(upper(concat(tc_mantic_personas.nombres, ' ', tc_mantic_personas.paterno, ' ', ifnull(tc_mantic_personas.materno, ' '), ' ', ifnull(tc_mantic_personas.apodo, ' '))) regexp '.*").append(nombre).append(".*') and ");
		} // if
		if(sb.length()== 0)
		  regresar.put(Constantes.SQL_CONDICION, Constantes.SQL_VERDADERO);
		else	
		  regresar.put(Constantes.SQL_CONDICION, sb.substring(0, sb.length()- 4));
		return regresar;		
	} // toPrepare

	private void loadCatalogs() {
		Map<String, Object>params= null;
		try {
			this.loadEmpresas();
			params= new HashMap<>();
		  params.put(Constantes.SQL_CONDICION, Constantes.SQL_VERDADERO);
      this.attrs.put("catalogo", UIEntity.seleccione("TcKeetNominasEstatusDto", "row", params, "nombre"));
      this.attrs.put("estatus", new UISelectEntity(-1L));
			Catalogos.toLoadNominas(this.attrs);
			Catalogos.toLoadEjercicios(this.attrs);
			Catalogos.toLoadSemanas(this.attrs);
			Catalogos.toLoadTiposNominas(this.attrs);
			Catalogos.toLoadDepartamentos(this.attrs);
			Catalogos.toLoadPuestos(this.attrs);
			Catalogos.toLoadContratistas(this.attrs);
		} // try
		catch (Exception e) {
			JsfBase.addMessageError(e);
			Error.mensaje(e);	
		} // catch				
    finally {
			Methods.clean(params);
		}	// finally
	} // loadCatalogs

  public void doExportar() {
	}	
	
	public void doReporte() {
	}
	
  public void doLoadDetalle() {
    List<Columna> columns    = null;
		Map<String, Object>params= new HashMap<>();
    try {
			Entity entity= (Entity)this.attrs.get("seleccionado");
			params.put("sortOrder", "order by tc_keet_nominas_detalles.id_nomina_persona, tc_keet_nominas_conceptos.id_tipo_concepto desc, tc_keet_nominas_conceptos.orden");
			params.put("idNomina", entity.toLong("idNomina"));
			params.put("idEmpresaPersona", entity.toLong("idEmpresaPersona"));
      columns= new ArrayList<>();
      columns.add(new Columna("valor", EFormatoDinamicos.MILES_SIN_DECIMALES));
      columns.add(new Columna("fecha", EFormatoDinamicos.FECHA_CORTA));
      this.lazyDetalle= new FormatCustomLazy("VistaNominaConsultasDto", "persona", params, columns);
      UIBackingUtilities.resetDataTable("detalle");
			Long puesto= entity.toLong("idPuesto");
			this.attrs.put("destajos", puesto== 6L);
			if(puesto== 6L)
			  this.doLoadDestajo();
    } // try
    catch (Exception e) {
      Error.mensaje(e);
      JsfBase.addMessageError(e);
    } // catch
    finally {
      Methods.clean(params);
      Methods.clean(columns);
    } // finally		
  } // doLoadDetalle
	
  public void doLoadDestajo() {
    List<Columna> columns    = null;
		Map<String, Object>params= new HashMap<>();
    try {
			Entity entity= (Entity)this.attrs.get("seleccionado");
			params.put("sortOrder", "order by tc_keet_contratos.etapa, tc_keet_contratos_lotes.manzana, tc_keet_contratos_lotes.lote");
			params.put("idNomina", entity.toLong("idNomina"));
			params.put("idEmpresaPersona", entity.toLong("idEmpresaPersona"));
      columns= new ArrayList<>();
      columns.add(new Columna("porcentaje", EFormatoDinamicos.MILES_SIN_DECIMALES));
      columns.add(new Columna("costo", EFormatoDinamicos.MILES_SIN_DECIMALES));
      columns.add(new Columna("registro", EFormatoDinamicos.FECHA_CORTA));
      this.lazyDestajo= new FormatCustomLazy("VistaNominaConsultasDto", "destajo", params, columns);
      UIBackingUtilities.resetDataTable("destajo");
    } // try
    catch (Exception e) {
      Error.mensaje(e);
      JsfBase.addMessageError(e);
    } // catch
    finally {
      Methods.clean(params);
      Methods.clean(columns);
    } // finally		
  } // doLoadDestajo
	
}