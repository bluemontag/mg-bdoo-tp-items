package base.repository;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public interface HibernateBaseRepositoryBI {
	
	Object findeByOid(Class<?> aClass, String anOid);

}
