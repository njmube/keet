package mx.org.kaana.keet.catalogos.prototipos.beans;

import java.util.ArrayList;
import java.util.List;
import mx.org.kaana.keet.catalogos.prototipos.reglas.AdminSistemaConstructivo;
import mx.org.kaana.keet.db.dto.TcKeetPrototiposArchivosDto;
import mx.org.kaana.keet.db.dto.TcKeetPrototiposDto;
import mx.org.kaana.libs.pagina.UISelectEntity;

public class Prototipos extends TcKeetPrototiposDto {
  
  private UISelectEntity ikCliente;
  private AdminSistemaConstructivo ikSistemasConstructivos;
	private List<TcKeetPrototiposArchivosDto> docuemntos;

	public Prototipos() {
    super();
		this.ikSistemasConstructivos= new AdminSistemaConstructivo();
		this.docuemntos= new ArrayList<>();
  }

	public AdminSistemaConstructivo getIkSistemasConstructivos() {
		return ikSistemasConstructivos;
	}
	
	public void setIkSistemasConstructivos(AdminSistemaConstructivo ikSistemasConstructivos) {
		this.ikSistemasConstructivos = ikSistemasConstructivos;
	}
	
  public UISelectEntity getIkCliente() {
    return ikCliente;
  }

  public void setIkCliente(UISelectEntity ikCliente) {
    this.ikCliente = ikCliente;
		if(this.ikCliente!= null)
			this.setIdCliente(this.ikCliente.getKey());
  }

	public List<TcKeetPrototiposArchivosDto> getDocuemntos() {
		return docuemntos;
	}

	public void setDocuemntos(List<TcKeetPrototiposArchivosDto> docuemntos) {
		this.docuemntos = docuemntos;
	}
	
	

	@Override
	public Class toHbmClass() {
		return TcKeetPrototiposDto.class;
	}
  
}
