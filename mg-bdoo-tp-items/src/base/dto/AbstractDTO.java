package base.dto;

import base.domain.BaseDomain;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class AbstractDTO implements Cloneable {
	protected String oid;
	protected Integer version;

	public AbstractDTO(BaseDomain aBaseDomainObject) {
		this.oid = aBaseDomainObject.getOid();
		if (aBaseDomainObject.getVersion() == null) {
			this.version = 0;
		} else {
			this.version = aBaseDomainObject.getVersion();
		}
	}

	public AbstractDTO(AbstractDTO anAbstractDTO) {
		this.oid = anAbstractDTO.getOid();
		if (anAbstractDTO.getVersion() == null) {
			this.version = 0;
		} else {
			this.version = anAbstractDTO.getVersion();
		}
	}

	public void setOid(String anOid) {
		this.oid = anOid;
	}

	public String getOid() {
		return oid;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer aVersion) {
		this.version = aVersion;
	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || !(obj instanceof AbstractDTO)) {
			return false;
		}

		AbstractDTO other = (AbstractDTO) obj;

		if (oid == null)
			return false;
		return oid.equals(other.getOid());
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " [id=" + oid + "]";
	}
}
