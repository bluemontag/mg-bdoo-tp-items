/**
 * 
 */
package workflow.service.state;

import workflow.domain.Workflow;
import workflow.domain.state.ItemState;
import workflow.domain.transition.Transition;
import workflow.dto.WorkflowDTO;
import workflow.dto.state.ItemStateDTO;
import workflow.dto.state.ItemStateDTOFactory;
import workflow.dto.transition.TransitionDTO;
import workflow.dto.transition.TransitionDTOFactory;
import workflow.exception.UnknownWorkflowException;
import workflow.exception.state.ItemStateAlreadyExistsException;
import workflow.exception.state.UnknownItemStateException;
import workflow.exception.transition.UnknownTransitionException;
import base.exception.DTOConcurrencyException;
import base.service.AbstractServiceImpl;

/**
 * @author Rodrigo Itursarry
 */
public class ItemStateServiceImpl extends AbstractServiceImpl implements ItemStateServiceBI {

	@Override
	public ItemStateDTO createItemStateOnWorkflow(String sessionToken, WorkflowDTO aWorkflowDTO, String itemStateName,
			boolean initialState) throws ItemStateAlreadyExistsException, UnknownWorkflowException,
			DTOConcurrencyException {

		try {
			Workflow aWorkflow = this.getWorkflowRepository().getWorkflowByDTO(aWorkflowDTO);
			aWorkflow.getItemStateByName(itemStateName);

		} catch (UnknownItemStateException unknownItemStateException) {
			Workflow aWorkflow = this.getWorkflowRepository().getWorkflowByDTO(aWorkflowDTO);
			this.checkDTOConcurrency(aWorkflowDTO, aWorkflow);
			ItemState itemState = new ItemState(itemStateName);
			aWorkflow.addItemState(itemState);
			if (initialState) {
				aWorkflow.setInitialState(itemState);
			}
			ItemStateDTO itemStateDTO = (ItemStateDTO) ItemStateDTOFactory.getInstance().getDTO(itemState);
			return itemStateDTO;
		}
		throw new ItemStateAlreadyExistsException("El itemState " + itemStateName + " ya existe.");
	}

	@Override
	public ItemStateDTO getItemStateByNameAndWorkflow(String sessionToken, WorkflowDTO aWorkflowDTO,
			String itemStateName) throws UnknownItemStateException {
		ItemState itemState = null;
		itemState = this.getItemStateRepository().getItemStateByNameAndWorkflow(aWorkflowDTO, itemStateName);
		ItemStateDTO itemStateDTO = (ItemStateDTO) ItemStateDTOFactory.getInstance().getDTO(itemState);
		return itemStateDTO;
	}

	@Override
	public ItemStateDTO getItemStateByOid(String sessionToken, String anOid) throws UnknownItemStateException {
		ItemState itemState = null;
		itemState = this.getItemStateRepository().getItemStateByOid(anOid);
		ItemStateDTO itemStateDTO = (ItemStateDTO) ItemStateDTOFactory.getInstance().getDTO(itemState);
		return itemStateDTO;
	}

	@Override
	public ItemStateDTO getItemStateByDTO(String sessionToken, ItemStateDTO itemStateDTO)
			throws UnknownItemStateException {
		ItemState itemState = null;
		itemState = this.getItemStateRepository().getItemStateByDTO(itemStateDTO);
		ItemStateDTO itemStateDTO2 = (ItemStateDTO) ItemStateDTOFactory.getInstance().getDTO(itemState);
		return itemStateDTO2;
	}

	// usado solo por los tests para dejar la base como estaba
	@Deprecated
	@Override
	public void removeItemStateFromWorkflow(String sessionToken, WorkflowDTO aWorkflowDTO, ItemStateDTO anItemStateDTO)
			throws UnknownItemStateException, DTOConcurrencyException, UnknownWorkflowException {
		Workflow aWorkflow = this.getWorkflowRepository().getWorkflowByDTO(aWorkflowDTO);
		ItemState anItemState = this.getItemStateRepository().getItemStateByDTO(anItemStateDTO);
		this.checkDTOConcurrency(aWorkflowDTO, aWorkflow);
		this.checkDTOConcurrency(anItemStateDTO, anItemState);
		anItemState.removeAllTransitions();
		aWorkflow.removeItemState(anItemState);
	}

	@Override
	public TransitionDTO createTransitionOnItemState(String sessionToken, ItemStateDTO anInitialItemStateDTO,
			ItemStateDTO aFinalItemStateDTO, String aTransitionName, String aTransitionCode)
			throws UnknownItemStateException, DTOConcurrencyException {
		ItemState anInitialItemState = this.getItemStateRepository().getItemStateByDTO(anInitialItemStateDTO);
		this.checkDTOConcurrency(anInitialItemStateDTO, anInitialItemState);
		ItemState aFinalItemState = this.getItemStateRepository().getItemStateByDTO(aFinalItemStateDTO);
		Transition aTransition = new Transition(aTransitionName, aTransitionCode, aFinalItemState);
		anInitialItemState.addTransition(aTransition);
		TransitionDTO aTransitionDTO = (TransitionDTO) TransitionDTOFactory.getInstance().getDTO(aTransition);
		return aTransitionDTO;
	}

	@Override
	public void removeTransition(String sessionToken, ItemStateDTO anItemStateDTO, TransitionDTO aTransitionDTO)
			throws UnknownItemStateException, DTOConcurrencyException, UnknownTransitionException {
		ItemState anItemState = this.getItemStateRepository().getItemStateByDTO(anItemStateDTO);
		this.checkDTOConcurrency(anItemStateDTO, anItemState);
		Transition aTransition = anItemState.getTransitionByTransitionCode(aTransitionDTO.getTransitionCode());
		anItemState.removeTransition(aTransition);
	}
}
