package base.domain;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public interface IPersistentObject {
	public String getOid();

	public void setOid(String anOid);

	public Integer getVersion();

	public void setVersion(Integer version);
}
