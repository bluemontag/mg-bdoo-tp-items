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
	public AbstractDTO getDTO(BaseDomain aProject) {
		return new ProjectDTO((Project) aProject);
	}

	@Override
	public AbstractDTOForLists getDTOForLists(BaseDomain aBaseDomainObject) {
		return new ProjectDTOForLists(((Project) aBaseDomainObject));
	}

	@Override
	protected AbstractDTOForLists getDTOForListInstance(AbstractDTO aProjectDTO) {
		return new ProjectDTOForLists((ProjectDTO) aProjectDTO);
	}
}
