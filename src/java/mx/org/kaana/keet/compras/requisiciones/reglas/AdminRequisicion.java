package mx.org.kaana.keet.compras.requisiciones.reglas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.org.kaana.kajool.db.comun.dto.IBaseDto;
import mx.org.kaana.kajool.db.comun.hibernate.DaoFactory;
import mx.org.kaana.kajool.db.comun.sql.Entity;
import mx.org.kaana.kajool.db.comun.sql.Value;
import mx.org.kaana.kajool.enums.EFormatoDinamicos;
import mx.org.kaana.kajool.reglas.comun.Columna;
import mx.org.kaana.keet.enums.ETiposPrecios;
import mx.org.kaana.libs.formato.Error;
import mx.org.kaana.libs.pagina.JsfBase;
import mx.org.kaana.libs.pagina.UIBackingUtilities;
import mx.org.kaana.libs.pagina.UISelectEntity;
import mx.org.kaana.libs.reflection.Methods;
import mx.org.kaana.mantic.compras.ordenes.beans.Articulo;
import mx.org.kaana.mantic.compras.requisiciones.beans.TicketRequisicion;
import mx.org.kaana.mantic.comun.IAdminArticulos;
import mx.org.kaana.mantic.ventas.beans.ArticuloVenta;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *@company KAANA
 *@project KAJOOL (Control system polls)
 *@date 8/05/2018
 *@time 03:09:42 PM 
 *@author Team Developer 2016 <team.developer@kaana.org.mx>
 */

public final class AdminRequisicion extends IAdminArticulos implements Serializable {

	private static final long serialVersionUID= 8594649943986572245L;
	private static final Log LOG              = LogFactory.getLog(AdminRequisicion.class);

	private TicketRequisicion orden;		

	public AdminRequisicion(TicketRequisicion orden) throws Exception {
		this(orden, true);
	}
	
	public AdminRequisicion(TicketRequisicion orden, boolean loadDefault) throws Exception {
		this.orden  = orden;
		if(this.orden.isValid()) {
  	  this.setArticulos((List<ArticuloVenta>)DaoFactory.getInstance().toEntitySet(ArticuloVenta.class, "TcManticRequisicionesDetallesDto", "detalle", orden.toMap()));      
			loadListaPrecios();
		}	// if
		else	{
		  this.setArticulos(new ArrayList<>());
			this.orden.setConsecutivo("1");
			this.orden.setIdUsuario(JsfBase.getAutentifica().getPersona().getIdUsuario());
			this.orden.setIdEmpresa(JsfBase.getAutentifica().getEmpresa().getIdEmpresa());			
		} // else	
		if(loadDefault)
			this.getArticulos().add(new ArticuloVenta(-1L));
		this.toCalculate();
		cleanPrecioDescuentoArticulo();
	} // AdminRequisicion

	public void loadListaPrecios(){
		ArticuloVenta articuloVenta= null;
		try {
			for(Articulo articulo: this.getArticulos()){
				if(articulo.isValid()){
					articuloVenta= (ArticuloVenta) articulo;
					articuloVenta.setListaPrecios(preciosArticulo(articuloVenta.getIdArticulo()));
					articuloVenta.setPrecioLista(UIBackingUtilities.toFirstKeySelectEntity(articuloVenta.getListaPrecios()));
				} // if
			} // for
		} // try
		catch (Exception e) {			
			Error.mensaje(e);			
		} // catch		
	} // loadListaPrecios
	
	private List<UISelectEntity> preciosArticulo(Long idArticulo){
		List<UISelectEntity> regresar= null;
		List<Columna> campos         = null;
		Entity articulo              = null;
		Map<String, Object> params   = null;
		try {
			params= new HashMap<>();
			params.put("idArticulo", idArticulo);
			params.put("idProveedor", this.orden.getIdProveedor());
			articulo= (Entity) DaoFactory.getInstance().toEntity("VistaRequisicionesDesarrolloDto", "preciosArticulo", params);
			regresar= new ArrayList<>();
			if(articulo!= null){
				if(articulo.toDouble("precioCompra")> 0D)
					regresar.add(new UISelectEntity(toItemArticulo(ETiposPrecios.COMPRA.getKey(), "Ultima compra", articulo.toDouble("precioCompra"))));				
				regresar.add(new UISelectEntity(toItemArticulo(ETiposPrecios.LIBRE.getKey(), "Libre", 0D)));
				if(articulo.toDouble("precioConvenio")> 0D)
					regresar.add(new UISelectEntity(toItemArticulo(ETiposPrecios.CONVENIO.getKey(), "Convenio", articulo.toDouble("precioConvenio"))));
				if(articulo.toDouble("precioBase")> 0D)
					regresar.add(new UISelectEntity(toItemArticulo(ETiposPrecios.BASE.getKey(), "Base", articulo.toDouble("precioBase"))));
				if(articulo.toDouble("precioLista")> 0D)
					regresar.add(new UISelectEntity(toItemArticulo(ETiposPrecios.LISTA.getKey(), "Lista", articulo.toDouble("precioLista"))));
				if(articulo.toDouble("precioEspecial")> 0D)
					regresar.add(new UISelectEntity(toItemArticulo(ETiposPrecios.ESPECIAL.getKey(), "Especial", articulo.toDouble("precioEspecial"))));
			} // if
			else
				regresar.add(new UISelectEntity(toItemArticulo(ETiposPrecios.LIBRE.getKey(), "Libre", 0D)));			
			campos= new ArrayList<>();
			campos.add(new Columna("costo", EFormatoDinamicos.NUMERO_CON_DECIMALES));
			UIBackingUtilities.toFormatUIEntitySet(regresar, campos);
		} // try
		catch (Exception e) {			
			Error.mensaje(e);			
		} // catch		
		finally {
			Methods.clean(params);
			Methods.clean(campos);
		} // finally
		return regresar;
	} // preciosArticulo
	
	private Entity toItemArticulo(Long key, String descripcion, Double costo){
		Entity regresar= null;
		try {
			regresar= new Entity(key);
			regresar.put("descripcion", new Value("descripcion", descripcion));
			regresar.put("costo", new Value("costo", costo));
		} // try
		catch (Exception e) {			
			Error.mensaje(e);			
		} // catch		
		return regresar;
	} // toItemArticulo
	
	@Override
	public Long getIdAlmacen() {
		return -1L;
	}

	public Long getIdCliente() {
		return -1L;
	}
	
	@Override
	public IBaseDto getOrden() {
		return orden;
	}

	@Override
	public void setOrden(IBaseDto orden) {
		this.orden= (TicketRequisicion)orden;
	}

	@Override
	public Double getTipoDeCambio() {
		return 1D;
	}
	
	@Override
	public String getDescuento() {
		return this.orden.getDescuento();
	}
	
	@Override
	public String getExtras() {
		return "0";		
	}
	
	@Override
	public Long getIdSinIva() {
		return 1L;
	}
	
	@Override
	public void setIdSinIva(Long idSinIva) {
		//this.orden.setIdSinIva(idSinIva);
	}

	@Override
	public Long getIdProveedor() {
		return -1L;
	}

	@Override
	public void setDescuento(String descuento){
		this.orden.setDescuento(descuento);
	}
}
