package proyect.dto;

import proyect.domain.Proyect;
import base.domain.BaseDomain;
import base.dto.AbstractDTO;
import base.dto.AbstractDTOForLists;
import base.dto.DTOFactory;

public class ProyectDTOFactory extends DTOFactory {

	private ProyectDTOFactory() {
	}

	public static ProyectDTOFactory getInstance() {
		return new ProyectDTOFactory();
	}

	@Override
	public AbstractDTO getDTO(BaseDomain aProyect) {
		return new ProyectDTO((Proyect) aProyect);
	}

	@Override
	public AbstractDTOForLists getDTOForLists(BaseDomain aBaseDomainObject) {
		return new ProyectDTOForLists(((Proyect) aBaseDomainObject));
	}

	@Override
	protected AbstractDTOForLists getDTOForListInstance(AbstractDTO aProyectDTO) {
		return new ProyectDTOForLists((ProyectDTO) aProyectDTO);
	}
}
