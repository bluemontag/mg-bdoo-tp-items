/**
 * @author igallego 
 */
package item.test;

import item.domain.Item;
import item.domain.itemType.ItemType;
import item.dto.ItemDTO;
import item.exception.ItemAlreadyExistsException;
import item.service.ItemServiceBI;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import user.domain.User;
import user.domain.team.Team;
import workflow.domain.Workflow;
import workflow.domain.state.domain.ItemState;
import workflow.exception.transition.BadTransitionException;
import base.test.BaseTestCase;

/**
 * @author igallego ignaciogallego@gmail.com
 *  
 * 03/07/2012
 */
public class ItemTest extends BaseTestCase {

	protected Item anItem;
	private final static String CONTEXT = "applicationContext.xml";
	private static AbstractApplicationContext ctx;
	
	
	public static Test suite(){
		/* Crea el test para ejecutarlo desde el IDE */
		return new TestSuite(ItemTest.class);
	}
	
	@Override
	public void setUp() throws Exception {
		/* Setea todo para correr el test */
		
		String[] contextPaths = new String[] { CONTEXT };
		ctx = new ClassPathXmlApplicationContext(contextPaths);
		//sessionToken = login("rodrigo", "rodrigo");
		super.setUp(); //crea y loguea  usuario
		
		//Creo un WORKFLOW
		ItemState p = new ItemState("Pendiente");
		ItemState d = new ItemState("En desarrollo");
		ItemState f = new ItemState("Finalizado");
		p.addNextState(d);
		d.addNextState(f);
		
		Workflow w = new Workflow();
		w.setInitialState(p);
		//pendiente -> en desarrollo -> finalizado (WORKFLOW DE PRUEBA)
		w.setName("Workflow basico");

		//Creo un TEAM
		List<User> users = new ArrayList<User>();
		User u1 = new User("Ignacio", "ignacio");
		User u2 = new User("Rodrigo", "rodrigo");
		users.add(u1);
		users.add(u2);
		Team t = new Team("A", users);
		
		//ITEM
		ItemType it = new ItemType("ticket desarrollo web", w, t);
		this.anItem = new Item(new Long(1), "Desarrollo de aplicacion web", 1, it);
		this.anItem.setCurrentState(p);
	}

	@Deprecated
	public void testItemChangeStateInMemory() {
		/* testea para un item que se encuentra en memoria, si se puede 
		 * ejecutar una transaccion simple.
		 */
		try {
			this.anItem.executeTransition("aEn desarrollo");
		} catch (BadTransitionException e) {
			fail("La transaccion es incorrecta");
		}

		assertTrue(this.anItem.getCurrentState().getName().equals("En desarrollo"));
		System.out.println("El item cambio de estado como se esperaba");
	}
	
	public void testItemChangeState() {
		ItemServiceBI itemService = (ItemServiceBI) ctx.getBean("itemService");
		this.setItemService(itemService);
		ItemDTO iDTO = null;
		try {
			iDTO = this.getItemService().createItem(this.sessionToken, new Long(1), "Desarrollo de aplicacion web", 1, this.anItem.getType());
		} catch (ItemAlreadyExistsException e) {
			fail("No se pudo crear el item: El item ya existe");
		}
		try {
			assertEquals(iDTO.getDescription(), this.anItem.getDescription());
			this.getItemService().updateItem(this.sessionToken, iDTO);
			System.out.println("El test finalizo exitosamente.");
		} catch (Exception e) {
			fail("No se pudo obtener la descripcion del item");
		}
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
