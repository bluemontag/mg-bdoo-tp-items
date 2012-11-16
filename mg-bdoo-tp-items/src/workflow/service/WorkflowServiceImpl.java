/**
 * 
 */
package workflow.service;

import itemTracker.domain.ItemTracker;
import workflow.domain.Workflow;
import workflow.domain.state.ItemState;
import workflow.dto.WorkflowDTO;
import workflow.dto.WorkflowDTOFactory;
import workflow.dto.state.ItemStateDTO;
import workflow.exception.UnknownWorkflowException;
import workflow.exception.WorkflowAlreadyExistsException;
import workflow.exception.state.UnknownItemStateException;
import base.exception.DTOConcurrencyException;
import base.service.AbstractServiceImpl;

/**
 * @author Ignacio Gallego
 */
public class WorkflowServiceImpl extends AbstractServiceImpl implements WorkflowServiceBI {

	@Override
	public WorkflowDTO createWorkflow(String sessionToken, String workflowName) throws WorkflowAlreadyExistsException {
		try {
			this.getWorkflowRepository().getWorkflowByName(workflowName);
		} catch (UnknownWorkflowException unknownWorkflowException) {
			ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();

			Workflow wf = new Workflow(workflowName);
			theItemTracker.addWorkflow(wf);

			WorkflowDTO wfDTO = (WorkflowDTO) WorkflowDTOFactory.getInstance().getDTO(wf);
			return wfDTO;
		}
		throw new WorkflowAlreadyExistsException("El workflow " + workflowName + " ya existe.");

	}

	@Override
	public WorkflowDTO getWorkflowByName(String sessionToken, String workflowName) throws UnknownWorkflowException {
		Workflow wf = null;
		wf = this.getWorkflowRepository().getWorkflowByName(workflowName);
		WorkflowDTO wfDTO = (WorkflowDTO) WorkflowDTOFactory.getInstance().getDTO(wf);
		return wfDTO;
	}

	@Override
	public WorkflowDTO getWorkflowByOid(String sessionToken, String anOid) throws UnknownWorkflowException {
		Workflow wf = null;
		wf = this.getWorkflowRepository().getWorkflowByOid(anOid);
		WorkflowDTO wfDTO = (WorkflowDTO) WorkflowDTOFactory.getInstance().getDTO(wf);
		return wfDTO;
	}

	@Override
	public WorkflowDTO getWorkflowByDTO(String sessionToken, WorkflowDTO wfDTO) throws UnknownWorkflowException {
		Workflow wf = null;
		wf = this.getWorkflowRepository().getWorkflowByDTO(wfDTO);
		WorkflowDTO wfDTO2 = (WorkflowDTO) WorkflowDTOFactory.getInstance().getDTO(wf);
		return wfDTO2;
	}

	public void updateWorkflow(String sessionToken, WorkflowDTO wfDTO) throws UnknownWorkflowException,
			DTOConcurrencyException {
		// TODO: Hacer si se encesita
		/*
		 * User userToUpdate =
		 * this.getUserRepository().getUserByOid(userToUpdateDTO.getOid());
		 * this.checkDTOConcurrency(userToUpdateDTO, userToUpdate);
		 * userToUpdate.setPassword(userToUpdateDTO.getPassword());
		 */
	}

	@Override
	public void setInitialState(String sessionToken, WorkflowDTO aWorkflowDTO, ItemStateDTO itemStateDTO)
			throws UnknownWorkflowException, UnknownItemStateException {
		Workflow wf = this.getWorkflowRepository().getWorkflowByDTO(aWorkflowDTO);
		ItemState itemState = null;
		itemState = this.getItemStateRepository().getItemStateByOid(itemStateDTO.getOid());
		wf.setInitialState(itemState);

	}

	@Override
	public void logicalRemoveWorkflow(String sessionToken, WorkflowDTO aWorkflowDTO) throws UnknownWorkflowException {
		Workflow aWorkflow = this.getWorkflowRepository().getWorkflowByDTO(aWorkflowDTO);
		ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();
		theItemTracker.logicalRemoveWorkflow(aWorkflow);
	}

	// usado solo por los tests para dejar la base como estaba
	@Deprecated
	@Override
	public void removeWorkflow(String sessionToken, WorkflowDTO aWorkflowDTO) throws UnknownWorkflowException,
			DTOConcurrencyException {
		Workflow aWorkflow = this.getWorkflowRepository().getWorkflowByDTO(aWorkflowDTO);
		this.checkDTOConcurrency(aWorkflowDTO, aWorkflow);
		ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();
		theItemTracker.removeWorkflow(aWorkflow);
	}
}
