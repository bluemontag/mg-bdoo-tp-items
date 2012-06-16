package project.dto;

import project.domain.Project;
import base.domain.BaseDomain;
import base.dto.AbstractDTO;
import base.dto.AbstractDTOForLists;
import base.dto.DTOFactory;

public class ProjectDTOFactory extends DTOFactory {

	private ProjectDTOFactory() {
	}

	public static ProjectDTOFactory getInstance() {
		return new ProjectDTOFactory();
	}

	@Override
	public AbstractDTO getDTO(BaseDomain aProyect) {
		return new ProjectDTO((Project) aProyect);
	}

	@Override
	public AbstractDTOForLists getDTOForLists(BaseDomain aBaseDomainObject) {
		return new ProjectDTOForLists(((Project) aBaseDomainObject));
	}

	@Override
	protected AbstractDTOForLists getDTOForListInstance(AbstractDTO aProyectDTO) {
		return new ProjectDTOForLists((ProjectDTO) aProyectDTO);
	}
}
