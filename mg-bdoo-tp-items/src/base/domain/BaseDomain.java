package base.domain;

import base.dto.AbstractDTO;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class BaseDomain implements IPersistentObject {

	// llamar al metodo "generarOid()" en cada constructor base.
	protected String oid;
	protected Integer version;

	// se utiliza para saber si un objeto base fue eliminado.
	// Solo lo utilizan objetos que puedan ser elminados logicamente.
	private boolean removed = false;

	@Override
	public void setOid(String anOid) {
		this.oid = anOid;
	}

	@Override
	public String getOid() {
		return oid;
	}

	@Override
	public void setVersion(Integer aVersion) {
		this.version = aVersion;
	}

	@Override
	public Integer getVersion() {
		return version;
	}

	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

	public boolean isRemoved() {
		return removed;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || !(this.isAPersistenObject(obj))) {
			return false;
		}

		IPersistentObject other = (IPersistentObject) obj;

		if (oid == null)
			return false;
		return oid.equals(other.getOid());
	}

	private boolean isAPersistenObject(Object obj) {
		return obj instanceof IPersistentObject;
	}

	public boolean equalsToDTO(AbstractDTO aBaseDTO) {
		return oid.equals(aBaseDTO.getOid());
	}

	@Override
	public int hashCode() {
		if (oid != null) {
			return oid.hashCode();
		} else {
			return super.hashCode();
		}
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " [id=" + oid + "]";
	}

	protected void generarOid() {
		this.oid = OidGenerator.createId();
	}
}
