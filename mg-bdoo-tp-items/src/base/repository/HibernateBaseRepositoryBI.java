package base.repository;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public interface HibernateBaseRepositoryBI {
	
	Object findeById(Class<?> aClass, Long anId);

}
