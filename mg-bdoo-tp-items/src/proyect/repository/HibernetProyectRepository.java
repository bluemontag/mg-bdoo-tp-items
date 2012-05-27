package proyect.repository;

import base.exception.BaseException;
import base.repository.HibernateBaseRepository;
import proyect.domain.Proyect;
import proyect.dto.ProyectDTO;
import proyect.exception.UnknownProyectException;

public class HibernetProyectRepository extends HibernateBaseRepository implements ProyectRepositoryBI {

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return Proyect.class;
	}
	
	@Override
	public Proyect getProyectByOid(String anId) throws UnknownProyectException {
		Proyect aProyect = (Proyect) this.findeByOid(this.getEntityClass(), anId);
		if(aProyect == null){
			throw new UnknownProyectException("El proyecto no existe.");
		}
		return aProyect;
	} 
	
	@Override
	public Proyect getProyectByName(String aProyectName) throws UnknownProyectException {
		
		Proyect proyect = null;
		try {
			proyect = (Proyect) this.getEntityByUniqueField("getProyectByName", "aProyectName", aProyectName);
		} catch (BaseException e) {
			// TODO ver que hacer!!
		}
		if (proyect == null) {
				throw new UnknownProyectException("El proyecto "+ aProyectName +" no existe.");
		}
		return proyect;
	}

	@Override
	public Proyect getProyectByDTO(ProyectDTO aProyectDTO) throws UnknownProyectException {
		return this.getProyectByOid(aProyectDTO.getOid());
	}

}
