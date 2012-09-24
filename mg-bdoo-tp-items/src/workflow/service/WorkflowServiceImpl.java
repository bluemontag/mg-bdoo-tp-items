/**
 * 
 */
package workflow.service;

import itemTracker.domain.ItemTracker;
import workflow.domain.Workflow;
import workflow.domain.state.domain.ItemState;
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
			
			Workflow wf = new Workflow();
			wf.setName(workflowName);
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
	
	public void updateWorkflow(String sessionToken, WorkflowDTO wfDTO) throws UnknownWorkflowException,	DTOConcurrencyException {
		//TODO: Hacer si se encesita
		/*		
		 * 		User userToUpdate = this.getUserRepository().getUserByOid(userToUpdateDTO.getOid());
				this.checkDTOConcurrency(userToUpdateDTO, userToUpdate);
				userToUpdate.setPassword(userToUpdateDTO.getPassword());
		 */
	}

	public void setInitialState(String sessionToken, 
									   WorkflowDTO wfDTO, 
									   ItemStateDTO itemStateDTO) throws UnknownWorkflowException,
									   									 UnknownItemStateException {
		Workflow wf = this.getWorkflowRepository().getWorkflowByDTO(wfDTO);
		ItemState itemState = null;
		itemState = this.getItemStateRepository().getItemStateByName(itemStateDTO.getName());
		wf.setInitialState(itemState);
		
	}
	
	@Override
	public void logicalRemoveWorkflow(String sessionToken, WorkflowDTO wfDTO) throws UnknownWorkflowException {
		Workflow wf = this.getWorkflowRepository().getWorkflowByDTO(wfDTO);
		ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();
		theItemTracker.logicalRemoveWorkflow(wf);
	}

	// usado solo por los tests para dejar la base como estaba
	@Deprecated
	@Override
	public void removeWorkflow(String sessionToken, WorkflowDTO aWorkflowDTO) throws UnknownWorkflowException, 
																					DTOConcurrencyException, 
																					UnknownItemStateException {
			Workflow wf = this.getWorkflowRepository().getWorkflowByDTO(aWorkflowDTO);
			this.checkDTOConcurrency(aWorkflowDTO, wf);
			ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();
			//borrando el estado inicial, se borran todos los estados
			theItemTracker.removeItemState(wf.getInitialState());
			theItemTracker.removeWorkflow(wf);
		}
}
