package mx.org.kaana.keet.catalogos.puntoscontrol.beans;

import mx.org.kaana.keet.db.dto.TcKeetPuntosGruposDto;
import mx.org.kaana.libs.pagina.UISelectEntity;

public class PuntoGrupo extends TcKeetPuntosGruposDto{

	private UISelectEntity ikDepartamento;

	public PuntoGrupo() {
		this(new UISelectEntity(-1L));
	}

	public PuntoGrupo(UISelectEntity ikDepartamento) {
		super();
		this.ikDepartamento= ikDepartamento;
	}

	public UISelectEntity getIkDepartamento() {
		return ikDepartamento;
	}

	public void setIkDepartamento(UISelectEntity ikDepartamento) {
		this.ikDepartamento = ikDepartamento;
		if(this.ikDepartamento!= null)
			setIdDepartamento(this.ikDepartamento.getKey());
	}	

}