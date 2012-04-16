package base.dto;

import base.domain.BaseDomain;
import user.domain.User;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class AbstractDTO {
	protected String oid;
	protected Integer version;
	
	public AbstractDTO(BaseDomain aBaseDomainObject) {
		this.oid = aBaseDomainObject.getOid();
		if(aBaseDomainObject.getVersion() == null){
			this.version = 0;
		}else{
			this.version = aBaseDomainObject.getVersion();
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
}
