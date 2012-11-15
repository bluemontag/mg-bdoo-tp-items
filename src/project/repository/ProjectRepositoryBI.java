package project.repository;

import project.domain.Project;
import project.dto.ProjectDTO;
import project.exception.UnknownProjectException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public interface ProjectRepositoryBI {

	Project getProjectByName(String aProjectName) throws UnknownProjectException;

	Project getProjectByOid(String oid) throws UnknownProjectException;

	Project getProjectByDTO(ProjectDTO aProjectDTO) throws UnknownProjectException;

}
