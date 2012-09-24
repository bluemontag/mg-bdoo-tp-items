/**
 * @author igallego 
 */
package workflow.test;


import junit.framework.Test;
import junit.framework.TestSuite;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import workflow.dto.WorkflowDTO;
import workflow.dto.state.ItemStateDTO;
import workflow.exception.UnknownWorkflowException;
import workflow.exception.WorkflowAlreadyExistsException;
import workflow.exception.state.ItemStateAlreadyExistsException;
import workflow.exception.state.UnknownItemStateException;
import workflow.service.WorkflowServiceBI;
import workflow.service.state.ItemStateServiceBI;
import base.exception.DTOConcurrencyException;
import base.test.BaseTestCase;

/**
 * @author igallego ignaciogallego@gmail.com
 *  
 * 03/07/2012
 */
public class WorkflowTest extends BaseTestCase {

	protected WorkflowDTO wfDTO;
	private final static String CONTEXT = "applicationContext.xml";
	private AbstractApplicationContext ctx;
	private ItemStateDTO pendiente; 
	
	public static Test suite(){
		/* Crea el test para ejecutarlo desde el IDE */
		return new TestSuite(WorkflowTest.class);
	}
	
	@Override
	public void setUp() throws Exception {
		/* Setea todo para correr el test */
		
		//levanto el contexto del xml
		String[] contextPaths = new String[] { WorkflowTest.CONTEXT };
		ctx = new ClassPathXmlApplicationContext(contextPaths);

		//seteo el item service para todo el test
		WorkflowServiceBI wfService = (WorkflowServiceBI) ctx.getBean("workflowService");
		this.setWorkflowService(wfService);

		ItemStateServiceBI itemStateService = (ItemStateServiceBI) ctx.getBean("itemStateService");
		this.setItemStateService(itemStateService);
		
		super.setUp(); //crea y loguea  usuario
	}

	public void testWorkflowCreation() {
		//intento crear el wf.
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
		
		//veo si el wf se recupera
		WorkflowDTO otherWfDTO = null;
		try {
			otherWfDTO = this.getWorkflowService().getWorkflowByDTO(sessionToken, wfDTO);
			//veo si trajo descripcion
			assertTrue(otherWfDTO.getName().equals(this.wfDTO.getName()));
		} catch (UnknownWorkflowException e1) {
			e1.printStackTrace();
		}
		
		//agrego estados
		pendiente = null;
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

		//ESTADO EN DESARROLLO
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
		
		//ESTADO FINALIZADO
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

		//Intento setear los proximos estados
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
