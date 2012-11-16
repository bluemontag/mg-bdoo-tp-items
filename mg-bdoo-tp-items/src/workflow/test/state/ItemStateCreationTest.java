/**
 * @author igallego 
 */
package workflow.test.state;

import workflow.dto.state.ItemStateDTO;
import workflow.exception.UnknownWorkflowException;
import workflow.exception.state.ItemStateAlreadyExistsException;
import workflow.exception.state.UnknownItemStateException;
import base.exception.DTOConcurrencyException;
import base.test.TestConstants;

/**
 * @author igallego ignaciogallego@gmail.com
 * 
 *         03/07/2012
 */
public class ItemStateCreationTest extends ItemStateServiceTest {

	protected ItemStateDTO initialStateDTO;
	protected ItemStateDTO anItemPendingStateDTO;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		this.createWorkflow(TestConstants.WORKFLOW_NAME);

	}

	public void testItemStateCreation() {

		try {
			this.anItemPendingStateDTO = this.getItemStateService().createItemStateOnWorkflow(sessionToken,
					this.aWorkflowDTO, TestConstants.PENDING, true);
		} catch (ItemStateAlreadyExistsException e) {
			fail("El estado de nombre " + TestConstants.PENDING + " ya existe.");
		} catch (UnknownWorkflowException e) {
			fail("No existe el workflow de nombre " + this.aWorkflowDTO.getName());
		} catch (DTOConcurrencyException e) {
			fail("DTOConcurrencyException: no deberia tirar esta excepcion en un test controlado.");
		}
		// se recupera nuevamente el workflow para refrescar.
		try {
			this.aWorkflowDTO = this.getWorkflowService().getWorkflowByDTO(sessionToken, this.aWorkflowDTO);
		} catch (UnknownWorkflowException e) {
			fail("El workflow " + this.aWorkflowDTO.getName() + " no existe.");
		}

		assertEquals(this.aWorkflowDTO.getInitialState().getOid(), this.anItemPendingStateDTO.getOid());
		assertEquals(TestConstants.PENDING, this.anItemPendingStateDTO.getName());
	}

	@Override
	protected void tearDown() throws Exception {
		try {
			this.getItemStateService().removeItemStateFromWorkflow(sessionToken, this.aWorkflowDTO,
					this.anItemPendingStateDTO);
		} catch (UnknownItemStateException e) {
			fail("No se puede eliminar el estado" + this.anItemPendingStateDTO.getName() + " ya que no existe.");
		} catch (DTOConcurrencyException e) {
			fail("DTOConcurrencyException: no deberia tirar esta excepcion en un test controlado.");
		} catch (UnknownWorkflowException e) {
			fail("No existe el workflow de nombre " + this.aWorkflowDTO.getName());
		}
		super.tearDown();
	}
}
