package project.repository;

import project.domain.Project;
import project.dto.ProjectDTO;
import project.exception.UnknownProjectException;

public interface ProjectRepositoryBI {

	Project getProyectByName(String aProyectName) throws UnknownProjectException;

	Project getProyectByOid(String oid) throws UnknownProjectException;

	Project getProyectByDTO(ProjectDTO aProyectDTO) throws UnknownProjectException;

}
