package workflow.dto.transition;

import workflow.domain.transition.Transition;
import base.domain.BaseDomain;
import base.dto.AbstractDTO;
import base.dto.AbstractDTOForLists;
import base.dto.DTOFactory;

/**
 * @author Rodrigo Itursarry itursarry@gmail.com
 */
public class TransitionDTOFactory extends DTOFactory {

	private TransitionDTOFactory() {
	}

	public static DTOFactory getInstance() {
		return new TransitionDTOFactory();
	}

	@Override
	public AbstractDTO getDTO(BaseDomain aTransition) {
		return new TransitionDTO((Transition) aTransition);
	}

	@Override
	public AbstractDTOForLists getDTOForLists(BaseDomain aTransition) {
		return new TransitionDTOForLists((Transition) aTransition);
	}

	@Override
	protected AbstractDTOForLists getDTOForListInstance(AbstractDTO aTransitionDTO) {
		return new TransitionDTOForLists((TransitionDTO) aTransitionDTO);
	}

}
