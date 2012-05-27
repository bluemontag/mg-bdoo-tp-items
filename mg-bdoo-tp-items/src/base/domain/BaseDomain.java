package base.domain;

import base.dto.AbstractDTO;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class BaseDomain implements IPersistentObject{

	// se genera un UUID para cada objeto del dominio.
	protected String oid=OidGenerator.createId();
	protected Integer version;
	
	// se utiliza para saber si un objeto base fue eliminado.
	// Solo lo utilizan objetos que puedan ser elminados logicamente.
	private boolean removed = false;
	
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
	
	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

	public boolean isRemoved() {
		return removed;
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
    
    public boolean equalsToDTO(AbstractDTO aBaseDTO) {
        return oid.equals(aBaseDTO.getOid());
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
