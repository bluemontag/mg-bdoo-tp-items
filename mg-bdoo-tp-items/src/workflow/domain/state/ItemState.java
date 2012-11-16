package workflow.domain.state;

import item.domain.Item;

import java.util.ArrayList;
import java.util.Collection;

import workflow.domain.transition.Transition;
import workflow.exception.transition.BadTransitionException;
import base.domain.BaseDomain;

/**
 * @author igallego
 * 
 */
public class ItemState extends BaseDomain {

	private String name;
	private Collection<Transition> transitions;

	public ItemState() {

	}

	public ItemState(String name) {
		super();
		this.name = name;
		this.transitions = new ArrayList<Transition>();
	}

	public void setTransitions(Collection<Transition> transitions) {
		this.transitions = transitions;
	}

	public Collection<Transition> getTransitions() {
		return transitions;
	}

	public void addTransition(Transition transition) {
		this.transitions.add(transition);
	}

	public Boolean deleted() {
		return false;
	}

	public Boolean finalized() {
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Cambia de estado un Item y devuelve su estado actual.
	 */
	public void executeTransition(String aTransitionCode, Item anItem) throws BadTransitionException {

		if (aTransitionCode != null) {
			for (Transition transition : this.transitions) {
				if (transition.isThisTransition(aTransitionCode)) {
					this.changeState(transition, anItem);
					break;
				}
			}
		} else {
			throw new BadTransitionException("El codigo de traniscion: " + aTransitionCode
					+ " no se encontro en el estado" + this.name);
		}
	}

	public void changeState(Transition transition, Item anItem) {
		anItem.setCurrentState(transition.getNextState());
	}
}
