package user.dto;

import base.domain.BaseDomain;
import base.dto.AbstractDTO;
import base.dto.AbstractDTOForLists;
import base.dto.DTOFactory;
import user.domain.User;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class UserDTOFactory extends DTOFactory{

	private UserDTOFactory(){}
	
	public static UserDTOFactory getInstance(){
		return new UserDTOFactory();
	}
	
	@Override
	public AbstractDTO getDTO(BaseDomain aUser){
		return new UserDTO((User) aUser);
	}
	
	@Override
	public AbstractDTOForLists getDTOForLists(BaseDomain aBaseDomainObject) {
		return new UserDTOForLists((User) aBaseDomainObject);
	}

}
