package item.test;

import item.exception.UnknownItemException;
import workflow.dto.state.ItemStateDTO;
import workflow.dto.transition.TransitionDTO;
import workflow.exception.state.UnknownItemStateException;
import workflow.exception.transition.BadTransitionException;
import base.exception.DTOConcurrencyException;
import base.exception.UserNotLoggedException;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ItemTransitionTest extends ItemServiceTest {

	protected ItemStateDTO anItemPendingStateDTO;
	protected ItemStateDTO anItemInDevelopmentStateDTO;
	protected TransitionDTO aTransitionDTO;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		this.createAUserCollection();
		this.createTeam();
		this.createWorkflow();
		this.createItemType();
		this.createItemStateOnWorkflow(TestConstants.PENDING, true);
		this.anItemPendingStateDTO = this.anItemStateDTO;
		this.refreshWorkflowDTO();
		this.createItemStateOnWorkflow(TestConstants.IN_DEVELOPMENT, false);
		this.anItemInDevelopmentStateDTO = this.anItemStateDTO;
		this.aTransitionDTO = this.createTransition(this.anItemPendingStateDTO, this.anItemInDevelopmentStateDTO,
				TestConstants.TRANSITION_IN_DEVELOPMENT, TestConstants.TRANSITION_IN_DEVELOPMENT);
		this.refreshItemTypeDTO();
		this.createItem();
	}

	@Override
	public void tearDown() throws Exception {
		this.refreshItemDTO();
		this.refreshWorkflowDTO();
		this.refreshItemStateDTO();

		this.removeTransition(this.anItemPendingStateDTO, this.aTransitionDTO);
		this.removeItem();
		this.removeItemStateFromWorkflow(anItemInDevelopmentStateDTO);
		this.refreshWorkflowDTO();
		this.removeItemStateFromWorkflow(anItemPendingStateDTO);
		this.refreshWorkflowDTO();
		this.removeItemType();
		this.removeWorkflow();
		this.removeTeam();
		this.removeTheUserCollection();
	}

	public void testCreateTransition() {

		try {
			this.itemService.executeTransition(sessionToken, this.anItemDTO, this.aTransitionDTO.getTransitionCode());
		} catch (BadTransitionException e) {
			fail("La transicion de codigo " + this.aTransitionDTO.getTransitionCode()
					+ "existe para el estado actual del item.");
		} catch (UnknownItemException e) {
			fail("El item que intenta modificar no existe.");
		} catch (DTOConcurrencyException e) {
			fail("DTOConcurrencyException: no deberia pasar.");
		} catch (UserNotLoggedException e) {
			fail("Algo muy malo paso!");
		}
		this.refreshItemDTO();
		assertEquals(this.anItemDTO.getCurrentState(), this.anItemInDevelopmentStateDTO.getName());
	}

	@Override
	protected void refreshItemStateDTO() {
		try {
			this.anItemInDevelopmentStateDTO = this.itemStateService.getItemStateByDTO(sessionToken,
					this.anItemInDevelopmentStateDTO);
			this.anItemPendingStateDTO = this.itemStateService.getItemStateByDTO(sessionToken,
					this.anItemPendingStateDTO);
		} catch (UnknownItemStateException e) {
			fail("Error al actualizar la instancia del itemState.");
		}
	}
}