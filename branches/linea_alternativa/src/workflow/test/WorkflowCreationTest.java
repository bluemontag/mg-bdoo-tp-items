/**
 * @author igallego 
 */
package workflow.test;

import workflow.dto.WorkflowDTO;
import workflow.dto.state.ItemStateDTO;
import workflow.exception.UnknownWorkflowException;
import workflow.exception.WorkflowAlreadyExistsException;
import workflow.exception.state.UnknownItemStateException;
import base.test.BaseTestCase;

/**
 * @author igallego ignaciogallego@gmail.com
 * 
 *         03/07/2012
 */
public class WorkflowCreationTest extends BaseTestCase {

	protected WorkflowDTO wfDTO;
	private ItemStateDTO pendiente;

	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

	public void testWorkflowCreation() {
		// intento crear el wf.
		try {
			this.wfDTO = this.getWorkflowService().createWorkflow(this.sessionToken, "WF 1");
		} catch (WorkflowAlreadyExistsException e) {
			try {
				this.wfDTO = this.getWorkflowService().getWorkflowByName(this.sessionToken, "WF 1");
			} catch (UnknownWorkflowException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				fail("No se pudo recuperar o crear el item");
			}
		}

		// veo si el wf se recupera
		WorkflowDTO otherWfDTO = null;
		try {
			otherWfDTO = this.getWorkflowService().getWorkflowByDTO(sessionToken, wfDTO);
			// veo si trajo descripcion
			assertTrue(otherWfDTO.getName().equals(this.wfDTO.getName()));
		} catch (UnknownWorkflowException e1) {
			e1.printStackTrace();
		}

		// agrego estados
		pendiente = this.createOrGetItemState("Pendiente");
		ItemStateDTO desa = this.createOrGetItemState("En desarrollo");
		ItemStateDTO finalizado = this.createOrGetItemState("Finalizado");

		this.addNextState(pendiente, desa);
		this.addNextState(desa, finalizado);
		this.addNextState(finalizado, pendiente);
		
		try {
			this.getWorkflowService().setInitialState(this.sessionToken, this.wfDTO, pendiente);
		} catch (UnknownWorkflowException e) {
			System.out.println("El workflow es desconocido.");
		} catch (UnknownItemStateException e) {
			System.out.println("El estado inicial es desconocido.");
		}

		System.out.println("El test finalizo exitosamente.");
	}

	@Override
	protected void tearDown() throws Exception {
		this.wfDTO = this.getWorkflowService().getWorkflowByName(this.sessionToken, "WF 1");
		this.getWorkflowService().removeWorkflow(this.sessionToken, this.wfDTO);

		super.tearDown();
	}
}
