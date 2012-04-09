package base.domain;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class BaseDomain implements IPersistentObject{

	// se genera un UUID para cada objeto del dominio.
	protected String oid=OidGenerator.createId();
	protected Integer version;
	
	public void setOid(String anOid) {
		this.oid = anOid;
	}

	public String getOid() {
		return oid;
	}

	public void setVersion(Integer aVersion) {
		this.version = aVersion;
	}

	public Integer getVersion() {
		return version;
	}
	
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null ||
            !(obj instanceof IPersistentObject)) {
            return false;
        }

        IPersistentObject other
            = (IPersistentObject)obj;
        
        if (oid == null) return false;
        return oid.equals(other.getOid());
    }

    public int hashCode() {
        if (oid != null) {
            return oid.hashCode();
        } else {
            return super.hashCode();
        }
    }

    public String toString() {
        return this.getClass().getName()
            + " [id=" + oid + "]";
    }
}
