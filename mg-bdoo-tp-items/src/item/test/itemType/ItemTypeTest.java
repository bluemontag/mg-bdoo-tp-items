/**
 * @author igallego 
 */
package item.test.itemType;

import item.dto.itemType.ItemTypeDTO;
import item.exception.itemType.ItemTypeAlreadyExistsException;
import junit.framework.Test;
import junit.framework.TestSuite;
import user.dto.team.TeamDTO;
import user.exception.UnknownUserException;
import user.exception.team.TeamAlreadyExistsException;
import user.exception.team.UnknownTeamException;
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
public class ItemTypeTest extends BaseTestCase {

	private ItemStateDTO pendiente;
	private TeamDTO tDTO;
	private WorkflowDTO wDTO;
	private ItemTypeDTO itemTypeDTO;

	public static Test suite() {
		/* Crea el test para ejecutarlo desde el IDE */
		return new TestSuite(ItemTypeTest.class);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp(); // crea y loguea usuario
		this.createAUserCollection();
	}

	public void testItemTypeCreation() throws UnknownUserException, TeamAlreadyExistsException,
			WorkflowAlreadyExistsException {

		tDTO = this.teamService.createTeam(this.sessionToken, "Equipo Ignacio", this.aUserDTOForListCollection);

		wDTO = this.workflowService.createWorkflow(this.sessionToken, "WF Ignacio");

		// agrego estados
		pendiente = this.getItemState("Pendiente");
		ItemStateDTO desa = this.getItemState("En desarrollo");
		ItemStateDTO finalizado = this.getItemState("Finalizado");

		// Intento setear los proximos estados
		this.addNextState(pendiente, desa);
		this.addNextState(desa, finalizado);
		this.addNextState(pendiente, finalizado);

		// Seteo el estado inicial al WF
		try {
			this.getWorkflowService().setInitialState(this.sessionToken, wDTO, pendiente);
		} catch (UnknownWorkflowException e) {
			System.out.println("El workflow es desconocido.");
		} catch (UnknownItemStateException e) {
			System.out.println("El estado inicial es desconocido.");
		}

		// intento crear el IT
		try {
			itemTypeDTO = this.getItemTypeService().createItemType(this.sessionToken, "Tipo Basico", tDTO, wDTO);
		} catch (UnknownWorkflowException e) {
			e.printStackTrace();
			fail("UnknownWorkflow");
		} catch (ItemTypeAlreadyExistsException e) {
			e.printStackTrace();
			fail("Item existe");
		} catch (UnknownTeamException e) {
			e.printStackTrace();
			fail("Team desconocido");
		}
	}

	@Override
	protected void tearDown() throws Exception {
		// leo el DTO antes de borrar para evitar el DTOConcurrencyException
		itemTypeDTO = this.getItemTypeService().getItemType(this.sessionToken, itemTypeDTO);
		this.itemTypeService.removeItemType(this.sessionToken, itemTypeDTO);

		this.wDTO = this.getWorkflowService().getWorkflowByDTO(this.sessionToken, wDTO);
		this.getWorkflowService().removeWorkflow(this.sessionToken, this.wDTO);

		this.tDTO = this.getTeamService().getTeam(this.sessionToken, tDTO);
		this.teamService.removeTeam(this.sessionToken, tDTO);

		// no es necesario la siguiente linea, porque borro en cascada en la
		// coleccion de usuarios
		// this.deleteTheUserCollection();
		super.tearDown();
	}
}
