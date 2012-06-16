package project.repository;

import project.domain.Project;
import project.dto.ProjectDTO;
import project.exception.UnknownProjectException;
import base.exception.BaseException;
import base.repository.HibernateBaseRepository;

public class HibernetProjectRepository extends HibernateBaseRepository implements ProjectRepositoryBI {

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return Project.class;
	}

	@Override
	public Project getProyectByOid(String anId) throws UnknownProjectException {
		Project aProyect = (Project) this.findeByOid(this.getEntityClass(), anId);
		if (aProyect == null) {
			throw new UnknownProjectException("El proyecto no existe.");
		}
		return aProyect;
	}

	@Override
	public Project getProyectByName(String aProyectName) throws UnknownProjectException {

		Project proyect = null;
		try {
			proyect = (Project) this.getEntityByUniqueField("getProyectByName", "aProyectName", aProyectName);
		} catch (BaseException e) {
			// TODO ver que hacer!!
		}
		if (proyect == null) {
			throw new UnknownProjectException("El proyecto " + aProyectName + " no existe.");
		}
		return proyect;
	}

	@Override
	public Project getProyectByDTO(ProjectDTO aProyectDTO) throws UnknownProjectException {
		return this.getProyectByOid(aProyectDTO.getOid());
	}

}
