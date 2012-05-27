package proyect.repository;

import proyect.domain.Proyect;
import proyect.dto.ProyectDTO;
import proyect.exception.UnknownProyectException;

public class MemoryProyectRepository implements ProyectRepositoryBI{

	@Override
	public Proyect getProyectByName(String anUserName) throws UnknownProyectException {
		return null;
	}

	@Override
	public Proyect getProyectByOid(String oid) throws UnknownProyectException {
		return null;
	}

	@Override
	public Proyect getProyectByDTO(ProyectDTO aProyectDTO) throws UnknownProyectException {
		return this.getProyectByOid(aProyectDTO.getOid());
	}

}
