package base.dto;

// TODO: ver el tema de las versiones de los DTOs..
/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class AbstractDTO {
	protected Long id;
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
