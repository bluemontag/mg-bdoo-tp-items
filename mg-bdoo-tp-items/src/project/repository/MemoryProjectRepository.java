package project.repository;

import project.domain.Project;
import project.dto.ProjectDTO;
import project.exception.UnknownProjectException;

public class MemoryProjectRepository implements ProjectRepositoryBI {

	@Override
	public Project getProyectByName(String anUserName) throws UnknownProjectException {
		return null;
	}

	@Override
	public Project getProyectByOid(String oid) throws UnknownProjectException {
		return null;
	}

	@Override
	public Project getProyectByDTO(ProjectDTO aProyectDTO) throws UnknownProjectException {
		return this.getProyectByOid(aProyectDTO.getOid());
	}

}
