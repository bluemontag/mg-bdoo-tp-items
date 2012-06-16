package project.repository;

import project.domain.Project;
import project.dto.ProjectDTO;
import project.exception.UnknownProjectException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class MemoryProjectRepository implements ProjectRepositoryBI {

	@Override
	public Project getProjectByName(String anUserName) throws UnknownProjectException {
		return null;
	}

	@Override
	public Project getProjectByOid(String oid) throws UnknownProjectException {
		return null;
	}

	@Override
	public Project getProjectByDTO(ProjectDTO aProjectDTO) throws UnknownProjectException {
		return this.getProjectByOid(aProjectDTO.getOid());
	}

}
