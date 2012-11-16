package workflow.dto.transition;

import workflow.domain.Workflow;
import workflow.domain.transition.Transition;
import base.domain.BaseDomain;
import base.dto.AbstractDTO;
import base.dto.AbstractDTOForLists;
import base.dto.DTOFactory;

/**
 * @author Ignacio Gallego
 */
public class TransitionDTOFactory extends DTOFactory {

	private TransitionDTOFactory() {
	}

	public static DTOFactory getInstance() {
		return new TransitionDTOFactory();
	}

	@Override
	public AbstractDTO getDTO(BaseDomain aWorkflow) {
		return new TransitionDTO((Workflow) aWorkflow);
	}

	@Override
	public AbstractDTOForLists getDTOForLists(BaseDomain aBaseDomainObject) {
		return new TransitionDTOForLists((Transition) aBaseDomainObject);
	}

	@Override
	protected AbstractDTOForLists getDTOForListInstance(AbstractDTO anTransitionDTO) {
		return new TransitionDTOForLists((TransitionDTO) anTransitionDTO);
	}

}
