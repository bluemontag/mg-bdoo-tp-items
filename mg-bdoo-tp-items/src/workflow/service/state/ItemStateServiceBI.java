package workflow.service.state;

import workflow.dto.WorkflowDTO;
import workflow.dto.state.ItemStateDTO;
import workflow.dto.transition.TransitionDTO;
import workflow.exception.UnknownWorkflowException;
import workflow.exception.state.ItemStateAlreadyExistsException;
import workflow.exception.state.UnknownItemStateException;
import workflow.exception.transition.UnknownTransitionException;
import base.exception.DTOConcurrencyException;

/**
 * @author Ignacio Gallego
 */
public interface ItemStateServiceBI {

	// Creats

	public ItemStateDTO createItemStateOnWorkflow(String sessionToken, WorkflowDTO aWorkflowDTO, String itemStateName,
			boolean firstSatate) throws ItemStateAlreadyExistsException, UnknownWorkflowException,
			DTOConcurrencyException;

	public TransitionDTO createTransitionOnItemState(String sessionToken, ItemStateDTO anInitialItemStateDTO,
			ItemStateDTO aFinalItemStateDTO, String aTransitionName, String aTransitionCode)
			throws UnknownItemStateException, DTOConcurrencyException;

	// Lists

	// Retrives
	public ItemStateDTO getItemStateByNameAndWorkflow(String sessionToken, WorkflowDTO aWorkflowDTO,
			String itemStateName) throws UnknownItemStateException;

	public ItemStateDTO getItemStateByOid(String sessionToken, String anOid) throws UnknownItemStateException;

	public ItemStateDTO getItemStateByDTO(String sessionToken, ItemStateDTO itemStateDTO)
			throws UnknownItemStateException;

	public void removeTransition(ItemStateDTO itemStateDTO, TransitionDTO aTransitionDTO)
			throws UnknownItemStateException, DTOConcurrencyException, UnknownTransitionException;

	// Updates

	// Removes
	public void removeItemStateFromWorkflow(String sessionToken, WorkflowDTO aWorkflowDTO, ItemStateDTO itemStateDTO)
			throws UnknownItemStateException, DTOConcurrencyException, UnknownWorkflowException;

}
