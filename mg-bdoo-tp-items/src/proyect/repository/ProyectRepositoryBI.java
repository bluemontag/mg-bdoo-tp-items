package proyect.repository;

import proyect.domain.Proyect;
import proyect.exception.UnknownProyectException;

public interface ProyectRepositoryBI {

	Proyect getProyectByName(String aProyectName) throws UnknownProyectException;

	Proyect getProyectByOid(String oid) throws UnknownProyectException;
	
}
