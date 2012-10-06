/**
 * @author igallego 
 */
package workflow.test.state;

import workflow.dto.state.ItemStateDTO;

/**
 * @author igallego ignaciogallego@gmail.com
 * 
 *         03/07/2012
 */
public class ItemStateCreationTest extends ItemStateServiceTest {

	protected ItemStateDTO initialState;

	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

	public void testItemStateCreation() {

		// ESTADO PENDIENTE
		ItemStateDTO pendiente = this.createOrGetItemState("Pendiente");
		ItemStateDTO desa = this.createOrGetItemState("En desarrollo");
		ItemStateDTO finalizado = this.createOrGetItemState("Finalizado");

		// guardo estado inicial para borrar luego el grafo en el tearDown()
		initialState = pendiente;

		this.addNextState(pendiente, desa);
		this.addNextState(desa, finalizado);
		this.addNextState(finalizado, pendiente);
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
