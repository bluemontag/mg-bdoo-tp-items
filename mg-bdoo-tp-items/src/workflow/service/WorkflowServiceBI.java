package workflow.service;

import workflow.dto.WorkflowDTO;
import workflow.dto.state.ItemStateDTO;
import workflow.exception.UnknownWorkflowException;
import workflow.exception.WorkflowAlreadyExistsException;
import workflow.exception.state.UnknownItemStateException;
import base.exception.DTOConcurrencyException;

/**
 * @author Ignacio Gallego
 */
public interface WorkflowServiceBI {

	// Creats

	public WorkflowDTO createWorkflow(String sessionToken, String workflowName) throws WorkflowAlreadyExistsException;

	// Lists

	// Retrives
	public WorkflowDTO getWorkflowByName(String sessionToken, String workflowName) throws UnknownWorkflowException;

	public WorkflowDTO getWorkflowByOid(String sessionToken, String anOid) throws UnknownWorkflowException;

	public WorkflowDTO getWorkflowByDTO(String sessionToken, WorkflowDTO wfDTO) throws UnknownWorkflowException;

	// Updates
	public void setInitialState(String sessionToken, WorkflowDTO wfDTO, ItemStateDTO initialState)
			throws UnknownWorkflowException, UnknownItemStateException;

	// Removes
	// usado por los tests para dejar la base como estaba
	public void removeWorkflow(String sessionToken, WorkflowDTO workflowDTO) throws UnknownWorkflowException,
			DTOConcurrencyException;

	public void logicalRemoveWorkflow(String sessionToken, WorkflowDTO workflowDTO) throws UnknownWorkflowException;

}
