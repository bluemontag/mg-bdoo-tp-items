package project.repository;

import project.domain.Project;
import project.dto.ProjectDTO;
import project.exception.UnknownProjectException;
import base.exception.BaseException;
import base.repository.HibernateBaseRepository;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class HibernateProjectRepository extends HibernateBaseRepository implements ProjectRepositoryBI {

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return Project.class;
	}

	@Override
	public Project getProjectByOid(String anId) throws UnknownProjectException {
		Project aProject = (Project) this.findeByOid(this.getEntityClass(), anId);
		if (aProject == null) {
			throw new UnknownProjectException("El projecto no existe.");
		}
		return aProject;
	}

	@Override
	public Project getProjectByName(String aProjectName) throws UnknownProjectException {

		Project project = null;
		try {
			project = (Project) this.getEntityByUniqueField("getProjectByName", "aProjectName", aProjectName);
		} catch (BaseException e) {
			// TODO ver que hacer!!
		}
		if (project == null) {
			throw new UnknownProjectException("El projecto " + aProjectName + " no existe.");
		}
		return project;
	}

	@Override
	public Project getProjectByDTO(ProjectDTO aProjectDTO) throws UnknownProjectException {
		return this.getProjectByOid(aProjectDTO.getOid());
	}

}
