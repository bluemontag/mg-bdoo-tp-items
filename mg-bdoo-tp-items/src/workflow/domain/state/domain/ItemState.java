/**
 * 
 */
package workflow.domain.state.domain;

import java.util.HashMap;

import workflow.domain.transition.domain.Transition;
import base.domain.BaseDomain;

/**
 * @author igallego
 *
 */
public class ItemState extends BaseDomain {

	private String name;
	private HashMap<String, ItemState> nextStates;


	public ItemState(String name) {
		super();
		this.name = name;
		this.nextStates = new HashMap<String, ItemState>();
	}
	
	/**
	 * Comprueba que la transicion se aplique al estado actual
	 * y devuelve el estado resultante de aplicar la transicion.
	 * En caso de que no se aplique , se levanta una excepcion.
	 * 
	 * @param t
	 * @throws Exception
	 */
	public ItemState executeTransition(Transition t) throws Exception {
		//si se aplica al estado propio:
		if (t.getInitialState().getName().equals(this.name)) {
			String finalState = t.getFinalState().getName();
			
			//si el estado final existe en la lista de estados finales del estado "this":
			if (finalState!=null &&
				this.nextStates.containsKey(finalState)) {
				return t.getFinalState();//retorno el estado final
			} else {
				throw new Exception("Final state not found in state:"+this.getName());
			}
		} else {
			throw new Exception("Initial state not found in state:"+this.getName());
		}
	}
	
	public Boolean deleted() {
		return false;
	}
	
	public Boolean finalized() {
		return false;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Adds some state to the transitions table. 
	 * The table is indexed by states names.
	 * 
	 */
	public void addNextState(ItemState s) {
		this.nextStates.put(s.getName(), s);
	}
}
