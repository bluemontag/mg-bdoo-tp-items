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
	private Map<String, ItemState> nextStates;
	private Map<String, ItemState> parentStates;

	public ItemState() {
		this("");
	}
	
	public ItemState(String name) {
		super();
		this.name = name;
		this.nextStates = new HashMap<String, ItemState>();
		this.parentStates = new HashMap<String, ItemState>();
	}
	
	/**
	 * Comprueba que la transicion se aplique al estado actual
	 * y devuelve el estado resultante de aplicar la transicion.
	 * En caso de que no se aplique , se levanta una excepcion.
	 * 
	 * El string "t" debe ser por ejemplo "aDesarrollo", si el existe un estado final llamado "Desarrollo"
	 * 
	 * @param t
	 * @throws Exception
	 */
	public ItemState executeTransition(String t) throws BadTransitionException {
		if (t.toUpperCase().equals("A"+this.name.toUpperCase())) {
			return this;//no se aplica la transicion, es decir, queda en el mismo estado
		} else {
			String finalState = null;
			try {
				finalState = t.substring(1);//quito la "a"
			} catch (StringIndexOutOfBoundsException e) {
				//controlo que el string empiece con "a"
				throw new BadTransitionException("Bad transition name: "+t);
			}
			
			//si el estado final existe en la lista de estados finales del estado "this":
			if (finalState!=null &&	this.nextStates.containsKey(finalState)) {
				return this.nextStates.get(finalState);//retorno el estado final
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
		this.nextStates.put(s.getName(), s);
		s.addParentState(this);
	}

	public void addParentState(ItemState s) {
		this.parentStates.put(s.getName(), s);
	}
	
	public Map<String, ItemState> getChildStates() {
		return nextStates;
	}

	public void setChildStates(Map<String, ItemState> nextStates) {
		this.nextStates = nextStates;
	}
	
	public Map<String, ItemState> getParentStates() {
		return parentStates;
	}

	public void setParentStates(Map<String, ItemState> nextStates) {
		this.parentStates = nextStates;
	}
}
