package user.dto;

import user.domain.User;
import base.domain.BaseDomain;
import base.dto.AbstractDTO;
import base.dto.AbstractDTOForLists;
import base.dto.DTOFactory;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class UserDTOFactory extends DTOFactory {

	private UserDTOFactory() {
	}

	public static DTOFactory getInstance() {
		return new UserDTOFactory();
	}

	@Override
	public AbstractDTO getDTO(BaseDomain aUser) {
		return new UserDTO((User) aUser);
	}

	@Override
	public AbstractDTOForLists getDTOForLists(BaseDomain aBaseDomainObject) {
		return new UserDTOForLists((User) aBaseDomainObject);
	}

	@Override
	protected AbstractDTOForLists getDTOForListInstance(AbstractDTO anUserDTO) {
		return new UserDTOForLists((UserDTO) anUserDTO);
	}

}
