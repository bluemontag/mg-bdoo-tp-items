/**
 * 
 */
package workflow.domain.state;

import java.util.HashMap;
import java.util.Map;

import workflow.exception.transition.BadTransitionException;
import base.domain.BaseDomain;

/**
 * @author igallego
 *
 */
public class ItemState extends BaseDomain {

	private String name;
	private Map<String, ItemState> childStates;
	private Map<String, ItemState> parentStates;

	public ItemState() {
		this("");
	}
	
	public ItemState(String name) {
		super();
		this.name = name;
		this.childStates = new HashMap<String, ItemState>();
		this.parentStates = new HashMap<String, ItemState>();
	}
	
	/**
	 * Comprueba que la transicion se aplique al estado actual
	 * y devuelve el estado resultante de aplicar la transicion.
	 * En caso de que no se aplique , se levanta una excepcion.
	 * 
	 * El string "t" debe ser por ejemplo "aDesarrollo", si el existe un estado final llamado "Desarrollo"
	 * 
	 * @param finalState
	 * @throws Exception
	 */
	public ItemState executeTransition(String finalState) throws BadTransitionException {
		if (finalState!=null && finalState.toUpperCase().equals(this.name.toUpperCase())) {
			//si estoy ya en ese estado, no hago nada
			return this;//no se aplica la transicion, es decir, queda en el mismo estado
		} else {
			//si el estado final existe en la lista de estados finales del estado "this":
			if (finalState!=null &&	this.childStates.containsKey(finalState.toUpperCase())) {
				ItemState next = this.getChildStates().get(finalState.toUpperCase());
				return next;//retorno el estado final
			} else {
				throw new BadTransitionException("Final state not found in state:"+this.getName());
			}
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
		this.childStates.put(s.getName().toUpperCase(), s);
		s.addParentState(this);
	}

	public void addParentState(ItemState s) {
		this.parentStates.put(s.getName().toUpperCase(), s);
	}
	
	public Map<String, ItemState> getChildStates() {
		return childStates;
	}

	public void setChildStates(Map<String, ItemState> childStates) {
		this.childStates = childStates;
	}
	
	public Map<String, ItemState> getParentStates() {
		return parentStates;
	}

	public void setParentStates(Map<String, ItemState> parentStates) {
		this.parentStates = parentStates;
	}
}
