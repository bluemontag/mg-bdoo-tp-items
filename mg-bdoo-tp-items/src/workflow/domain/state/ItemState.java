package workflow.domain.state;

import item.domain.Item;

import java.util.ArrayList;
import java.util.Collection;

import workflow.domain.transition.Transition;
import workflow.exception.transition.BadTransitionException;
import workflow.exception.transition.UnknownTransitionException;
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

		boolean transicionExecuted = false;
		if (aTransitionCode != null) {
			for (Transition transition : this.transitions) {
				if (transition.isThisTransition(aTransitionCode)) {
					transicionExecuted = this.changeState(transition, anItem);
					break;
				}
			}
		}
		if (!transicionExecuted) {
			throw new BadTransitionException("El codigo de traniscion: " + aTransitionCode
					+ " no se encontro en el estado" + this.name);
		}
	}

	public boolean changeState(Transition transition, Item anItem) {
		anItem.setCurrentState(transition.getNextState());
		return true;
	}

	public Transition getTransitionByTransitionCode(String aTransitionCode) {
		Collection<Transition> transitions = this.getTransitions();
		for (Transition transition : transitions) {
			if (transition.isThisTransition(aTransitionCode)) {
				return transition;
			}
		}
		return null;
	}

	public void removeTransition(Transition aTransition) throws UnknownTransitionException {
		boolean removed = this.transitions.remove(aTransition);
		if (!removed) {
			throw new UnknownTransitionException("La transicion no se puedo eliminar.");
		}
		aTransition.setNextState(null);
	}

	public void removeAllTransitions() {
		this.transitions.clear();
	}
}
