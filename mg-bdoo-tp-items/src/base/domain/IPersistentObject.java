package base.domain;

public interface IPersistentObject {
	public String getOid();

	public void setOid(String anOid);

	public Integer getVersion();

	public void setVersion(Integer version);
}
