/**
 * @author igallego 
 */
package workflow.test;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.springframework.context.support.AbstractApplicationContext;

import workflow.dto.state.ItemStateDTO;
import workflow.exception.state.ItemStateAlreadyExistsException;
import workflow.exception.state.UnknownItemStateException;
import base.exception.DTOConcurrencyException;
import base.test.BaseTestCase;

/**
 * @author igallego ignaciogallego@gmail.com
 * 
 *         03/07/2012
 */
public class ItemStateTest extends BaseTestCase {

	protected ItemStateDTO initialState;
	private final static String CONTEXT = "applicationContext.xml";
	private AbstractApplicationContext ctx;

	public static Test suite() {
		/* Crea el test para ejecutarlo desde el IDE */
		return new TestSuite(ItemStateTest.class);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

	public void testItemStateCreation() {

		// ESTADO PENDIENTE
		ItemStateDTO pendiente = null;
		try {
			pendiente = this.getItemStateService().createItemState(this.sessionToken, "Pendiente");
		} catch (ItemStateAlreadyExistsException e) {
			try {
				pendiente = this.getItemStateService().getItemStateByName(this.sessionToken, "Pendiente");
			} catch (UnknownItemStateException e1) {
				e1.printStackTrace();
				fail("No se pudo recuperar o crear el item");
			}
		}

		// guardo estado inicial para borrar luego el grafo en el tearDown()
		initialState = pendiente;

		// ESTADO EN DESARROLLO
		ItemStateDTO desa = null;
		try {
			desa = this.getItemStateService().createItemState(this.sessionToken, "En desarrollo");
		} catch (ItemStateAlreadyExistsException e) {
			try {
				desa = this.getItemStateService().getItemStateByName(this.sessionToken, "En desarrollo");
			} catch (UnknownItemStateException e1) {
				e1.printStackTrace();
				fail("No se pudo recuperar o crear el item");
			}
		}

		// ESTADO FINALIZADO
		ItemStateDTO finalizado = null;
		try {
			finalizado = this.getItemStateService().createItemState(this.sessionToken, "Finalizado");
		} catch (ItemStateAlreadyExistsException e) {
			try {
				finalizado = this.getItemStateService().getItemStateByName(this.sessionToken, "Finalizado");
			} catch (UnknownItemStateException e1) {
				e1.printStackTrace();
				fail("No se pudo recuperar o crear el item");
			}
		}

		// Intento setear los proximos estados
		try {
			this.getItemStateService().addNextState(this.sessionToken, pendiente, desa);
		} catch (UnknownItemStateException e) {
			e.printStackTrace();
		} catch (DTOConcurrencyException e) {
			e.printStackTrace();
		}

		try {
			this.getItemStateService().addNextState(this.sessionToken, desa, finalizado);
		} catch (UnknownItemStateException e) {
			e.printStackTrace();
		} catch (DTOConcurrencyException e) {
			e.printStackTrace();
		}

		try {
			this.getItemStateService().addNextState(this.sessionToken, pendiente, finalizado);
		} catch (UnknownItemStateException e) {
			e.printStackTrace();
		} catch (DTOConcurrencyException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void tearDown() throws Exception {
		try {
			// leo de nuevo el estado inicial para que no me de
			// DTOConcurrencyException
			initialState = this.getItemStateService().getItemStateByDTO(this.sessionToken, initialState);
			this.getItemStateService().removeItemState(this.sessionToken, initialState);
		} catch (Exception e) {
			System.out.println("No se pudo eliminar el estado " + initialState.getName());
		}

		super.tearDown();
	}

}
