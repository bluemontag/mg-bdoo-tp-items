/**
 * @author igallego 
 */
package item.test;

import item.domain.itemType.ItemType;
import item.dto.ItemDTO;
import item.exception.ItemAlreadyExistsException;
import item.exception.UnknownItemException;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.springframework.context.support.AbstractApplicationContext;

import user.domain.User;
import user.domain.team.Team;
import user.dto.UserDTO;
import user.exception.UnknownUserException;
import workflow.domain.Workflow;
import workflow.domain.state.domain.ItemState;
import base.test.BaseTestCase;

/**
 * @author igallego ignaciogallego@gmail.com
 * 
 *         03/07/2012
 */
public class ItemTest extends BaseTestCase {

	protected ItemDTO itemDTO;
	private final static String CONTEXT = "applicationContext.xml";
	private AbstractApplicationContext ctx;

	public static Test suite() {
		/* Crea el test para ejecutarlo desde el IDE */
		return new TestSuite(ItemTest.class);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

	public void testItemChangeState() {

		// intento crear el item.
		try {
			// creo el item
			this.itemDTO = this.getItemService().createItem(this.sessionToken, new Long(1),
					"Desarrollo de aplicacion web", 1, this.createTESTItemType());
		} catch (ItemAlreadyExistsException e) {
			// fail("No se pudo crear el item: El item ya existe");
			try {
				this.itemDTO = this.getItemService().getItemByNum(this.sessionToken, new Long(1));
			} catch (UnknownItemException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				fail("No se pudo recuperar o crear el item");
			}
		}

		// veo si el item se recupera
		ItemDTO otherItemDTO = null;
		try {
			otherItemDTO = this.getItemService().getItem(sessionToken, itemDTO);

			// veo si trajo descripcion
			assertTrue(otherItemDTO.getDescription().equals(this.itemDTO.getDescription()));

		} catch (UnknownItemException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			this.itemDTO.setDescription("Otra descripcion");
			this.getItemService().updateItem(this.sessionToken, this.itemDTO);
		} catch (Exception e) {
			fail("No se pudo actualizar el item");
		}

		System.out.println("El test finalizo exitosamente.");
	}

	@Override
	protected void tearDown() throws Exception {
		this.itemService.removeItem(this.sessionToken, this.itemDTO);
		super.tearDown();
	}

	private Workflow createWorkflow() {
		/* TEST Workflow */
		ItemState p = new ItemState("Pendiente");
		ItemState d = new ItemState("En desarrollo");
		ItemState f = new ItemState("Finalizado");
		p.addNextState(d);
		d.addNextState(f);

		Workflow w = new Workflow();
		w.setInitialState(p);
		// pendiente -> en desarrollo -> finalizado (WORKFLOW DE PRUEBA)
		w.setName("Workflow basico");
		return w;
	}

	private Team createTeam() {
		List<User> users = new ArrayList<User>();
		UserDTO userDTO1 = null;
		UserDTO userDTO2 = null;
		try {
			userDTO1 = this.userService.getUserByUserName(sessionToken, "rodrigo");
			userDTO2 = this.userService.getUserByUserName(sessionToken, "ignacio");
		} catch (UnknownUserException e) {
		}

		User u1 = new User("rodrigo", "rodrigo");
		u1.setOid(userDTO1.getOid());
		u1.setVersion(userDTO1.getVersion());
		users.add(u1);

		User u2 = new User("ignacio", "ignacio");
		u2.setOid(userDTO2.getOid());
		u2.setVersion(userDTO2.getVersion());
		users.add(u2);

		Team t = new Team("A", users);
		return t;
	}

	private ItemType createTESTItemType() {
		Workflow w = this.createWorkflow();
		Team t = this.createTeam();
		ItemType it = new ItemType("ticket desarrollo web", w, t);

		return it;
	}
}
