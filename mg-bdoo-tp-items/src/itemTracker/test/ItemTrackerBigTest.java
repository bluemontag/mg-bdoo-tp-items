package itemTracker.test;

import item.dto.ItemDTO;
import item.dto.historicItem.HistoricItemDTOForLists;
import item.dto.itemType.ItemTypeDTO;
import item.exception.UnknownItemException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import user.dto.team.TeamDTO;
import workflow.dto.WorkflowDTO;
import workflow.dto.state.ItemStateDTO;
import workflow.dto.transition.TransitionDTO;
import workflow.exception.transition.BadTransitionException;
import base.exception.DTOConcurrencyException;
import base.exception.UserNotLoggedException;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ItemTrackerBigTest extends ItemTrackerTest {

	private ItemStateDTO anItemCreatedStateDTO;
	private ItemStateDTO anItemInDevelopmentStateDTO;
	private ItemStateDTO anItemInValidationStateDTO;
	private ItemStateDTO anItemFixedStateDTO;
	private TransitionDTO aTransitionFromCreatedToInDevelopmentDTO;
	private TransitionDTO aTransitionFromInDevelopmentToInValidationDTO;
	private TransitionDTO aTransitionFromInValidationToInDevelopmentDTO;
	private TransitionDTO aTransitionFromInValidationToFixedDTO;
	private TeamDTO aTeamDTO;
	private WorkflowDTO aWorkflowDTO;
	private ItemTypeDTO anItemTypeDTO;
	private ItemDTO anItemDTO;
	private String testCode;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		testCode = " " + ItemTrackerBigTest.class.getSimpleName();
		this.aUserDTOForListCollection = this.createAUserCollection(testCode);
		this.aTeamDTO = this.createTeam(this.aUserDTOForListCollection, TestConstants.TEAM_NAME + testCode);
		this.aWorkflowDTO = this.createWorkflow(TestConstants.WORKFLOW_NAME + testCode);
		this.anItemTypeDTO = this.createItemType(TestConstants.ITEM_TYPE_NAME + testCode, this.aTeamDTO,
				this.aWorkflowDTO);
		this.createAllItemStates();
		this.createAllTransitions();
		this.anItemTypeDTO = this.getItemTypeDTO(this.anItemTypeDTO);
		this.anItemDTO = this.createItem(TestConstants.ITEM_DESCRIPTION + testCode, TestConstants.PRIORITY,
				this.anItemTypeDTO);
	}

	@Override
	public void tearDown() throws Exception {
		this.anItemDTO = this.getItemDTO(this.anItemDTO);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.anItemCreatedStateDTO = this.getItemStateDTO(this.anItemCreatedStateDTO);
		this.anItemInDevelopmentStateDTO = this.getItemStateDTO(this.anItemInDevelopmentStateDTO);
		this.anItemInValidationStateDTO = this.getItemStateDTO(this.anItemInValidationStateDTO);
		this.anItemFixedStateDTO = this.getItemStateDTO(this.anItemFixedStateDTO);

		this.removeAllTransactions();
		this.removeItem(this.anItemDTO);
		this.removeAllItemStates();

		this.removeItemType(this.anItemTypeDTO);
		this.removeWorkflow(this.aWorkflowDTO);
		this.removeTeam(this.aTeamDTO);
		this.removeTheUserCollection(testCode);
	}

	public void testExecuteTransition() throws InterruptedException {
		Thread.sleep(1000); // para ver el orden de la historia

		// De creado a en desarrollo
		executeTransition(this.anItemDTO, this.aTransitionFromCreatedToInDevelopmentDTO);
		this.anItemDTO = this.getItemDTO(this.anItemDTO);
		assertEquals(this.anItemDTO.getCurrentState(), this.anItemInDevelopmentStateDTO.getName());
		Thread.sleep(1000); // para ver el orden de la historia

		// De en desarrollo a en validacion
		executeTransition(this.anItemDTO, this.aTransitionFromInDevelopmentToInValidationDTO);
		this.anItemDTO = this.getItemDTO(this.anItemDTO);
		assertEquals(this.anItemDTO.getCurrentState(), this.anItemInValidationStateDTO.getName());
		Thread.sleep(1000); // para ver el orden de la historia

		// De en validacion a en desarrollo (rechazar)
		executeTransition(this.anItemDTO, this.aTransitionFromInValidationToInDevelopmentDTO);
		this.anItemDTO = this.getItemDTO(this.anItemDTO);
		assertEquals(this.anItemDTO.getCurrentState(), this.anItemInDevelopmentStateDTO.getName());
		Thread.sleep(1000); // para ver el orden de la historia

		// De en desarrollo a en validacion
		executeTransition(this.anItemDTO, this.aTransitionFromInDevelopmentToInValidationDTO);
		this.anItemDTO = this.getItemDTO(this.anItemDTO);
		assertEquals(this.anItemDTO.getCurrentState(), this.anItemInValidationStateDTO.getName());
		Thread.sleep(1000); // para ver el orden de la historia

		// De en validacion a arreglado (aceptar)
		executeTransition(this.anItemDTO, this.aTransitionFromInValidationToFixedDTO);
		this.anItemDTO = this.getItemDTO(this.anItemDTO);
		assertEquals(this.anItemDTO.getCurrentState(), this.anItemFixedStateDTO.getName());

		Collection<? extends HistoricItemDTOForLists> historicItems = this.listHistoricItem(this.anItemDTO);
		ArrayList<HistoricItemDTOForLists> historicsItemsAsArrayList = this.sortHistoricItemCollection(historicItems);
		this.assertHistoricItems(historicsItemsAsArrayList);
	}

	protected void executeTransition(ItemDTO anItemDTO, TransitionDTO aTransitionDTO) {
		try {
			this.itemService.executeTransition(sessionToken, anItemDTO, aTransitionDTO.getTransitionCode());
			this.anItemDTO = this.getItemDTO(this.anItemDTO);
		} catch (BadTransitionException e) {
			fail("La transicion de codigo " + aTransitionDTO.getTransitionCode()
					+ "existe para el estado actual del item.");
		} catch (UnknownItemException e) {
			fail("El item que intenta modificar no existe.");
		} catch (DTOConcurrencyException e) {
			fail("DTOConcurrencyException: no deberia pasar.");
		} catch (UserNotLoggedException e) {
			fail("Algo muy malo paso!");
		}
	}

	protected void assertHistoricItems(ArrayList<HistoricItemDTOForLists> historicsItems) {

		assertEquals(6, historicsItems.size());
		HistoricItemDTOForLists historicItem0 = historicsItems.get(0);
		assertEquals(this.anItemCreatedStateDTO.getName(), historicItem0.getItemState().getName());
		HistoricItemDTOForLists historicItem1 = historicsItems.get(1);
		assertEquals(this.anItemInDevelopmentStateDTO.getName(), historicItem1.getItemState().getName());
		HistoricItemDTOForLists historicItem2 = historicsItems.get(2);
		assertEquals(this.anItemInValidationStateDTO.getName(), historicItem2.getItemState().getName());
		HistoricItemDTOForLists historicItem3 = historicsItems.get(3);
		assertEquals(this.anItemInDevelopmentStateDTO.getName(), historicItem3.getItemState().getName());
		HistoricItemDTOForLists historicItem4 = historicsItems.get(4);
		assertEquals(this.anItemInValidationStateDTO.getName(), historicItem4.getItemState().getName());
		HistoricItemDTOForLists historicItem5 = historicsItems.get(5);
		assertEquals(this.anItemFixedStateDTO.getName(), historicItem5.getItemState().getName());
	}

	protected void createAllTransitions() {
		this.aTransitionFromCreatedToInDevelopmentDTO = this.createTransition(this.anItemCreatedStateDTO,
				this.anItemInDevelopmentStateDTO, TestConstants.TRANSITION_IN_DEVELOPMENT,
				TestConstants.TRANSITION_IN_DEVELOPMENT);

		this.anItemCreatedStateDTO = this.getItemStateDTO(this.anItemCreatedStateDTO);

		this.aTransitionFromInDevelopmentToInValidationDTO = this.createTransition(this.anItemInDevelopmentStateDTO,
				this.anItemInValidationStateDTO, TestConstants.TRANSITION_IN_VALIDATION,
				TestConstants.TRANSITION_IN_VALIDATION);

		this.anItemInDevelopmentStateDTO = this.getItemStateDTO(this.anItemInDevelopmentStateDTO);

		this.aTransitionFromInValidationToInDevelopmentDTO = this.createTransition(this.anItemInValidationStateDTO,
				this.anItemInDevelopmentStateDTO, TestConstants.TRANSITION_REJECT, TestConstants.TRANSITION_REJECT);

		this.anItemInValidationStateDTO = this.getItemStateDTO(this.anItemInValidationStateDTO);

		this.aTransitionFromInValidationToFixedDTO = this.createTransition(this.anItemInValidationStateDTO,
				this.anItemFixedStateDTO, TestConstants.TRANSITION_ACCEPT, TestConstants.TRANSITION_ACCEPT);

		this.anItemInValidationStateDTO = this.getItemStateDTO(this.anItemInValidationStateDTO);
	}

	protected void removeAllTransactions() {
		this.anItemInValidationStateDTO = this.getItemStateDTO(this.anItemInValidationStateDTO);
		this.removeTransition(this.anItemInValidationStateDTO, this.aTransitionFromInValidationToFixedDTO);
		this.anItemInValidationStateDTO = this.getItemStateDTO(this.anItemInValidationStateDTO);
		this.removeTransition(this.anItemInValidationStateDTO, this.aTransitionFromInValidationToInDevelopmentDTO);
		this.anItemInValidationStateDTO = this.getItemStateDTO(this.anItemInValidationStateDTO);
		this.removeTransition(this.anItemInDevelopmentStateDTO, this.aTransitionFromInDevelopmentToInValidationDTO);
		this.anItemInDevelopmentStateDTO = this.getItemStateDTO(this.anItemInDevelopmentStateDTO);
		this.removeTransition(this.anItemCreatedStateDTO, this.aTransitionFromCreatedToInDevelopmentDTO);
		this.anItemCreatedStateDTO = this.getItemStateDTO(this.anItemCreatedStateDTO);
	}

	protected void createAllItemStates() {
		this.anItemCreatedStateDTO = this.createItemStateOnWorkflow(this.aWorkflowDTO, TestConstants.CREATED_STATE,
				true);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.anItemInDevelopmentStateDTO = this.createItemStateOnWorkflow(this.aWorkflowDTO,
				TestConstants.IN_DEVELOPMENT_STATE, false);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.anItemInValidationStateDTO = this.createItemStateOnWorkflow(this.aWorkflowDTO,
				TestConstants.IN_VALIDATION_STATE, false);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.anItemFixedStateDTO = this.createItemStateOnWorkflow(this.aWorkflowDTO, TestConstants.FIXED_STATE, false);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
	}

	protected void removeAllItemStates() {
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.removeItemStateFromWorkflow(this.aWorkflowDTO, this.anItemCreatedStateDTO);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.removeItemStateFromWorkflow(this.aWorkflowDTO, this.anItemInDevelopmentStateDTO);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.removeItemStateFromWorkflow(this.aWorkflowDTO, this.anItemInValidationStateDTO);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.removeItemStateFromWorkflow(this.aWorkflowDTO, this.anItemFixedStateDTO);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
	}

	private ArrayList<HistoricItemDTOForLists> sortHistoricItemCollection(
			Collection<? extends HistoricItemDTOForLists> historicItems) {
		ArrayList<HistoricItemDTOForLists> historicItemsAsArray = new ArrayList<HistoricItemDTOForLists>(historicItems);
		Collections.sort(historicItemsAsArray, new HistoricItemCompare());
		return historicItemsAsArray;
	}

	protected class HistoricItemCompare implements Comparator<HistoricItemDTOForLists> {

		@Override
		public int compare(HistoricItemDTOForLists historicItemA, HistoricItemDTOForLists historicItemB) {
			Date dateA = historicItemA.getDate();
			Date dateB = historicItemB.getDate();
			return dateA.compareTo(dateB);
		}
	}
}
