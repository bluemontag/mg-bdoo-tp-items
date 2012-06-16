/**
 * 
 */
package item.domain;

import user.domain.team.Team;
import workflow.domain.Workflow;
import base.domain.BaseDomain;

/**
 * @author igallego
 * 
 *         This is a Type Object for item type.
 * 
 *         Este es un "type object" para el tipo de Item.
 */
public class ItemType extends BaseDomain {

	private String typeName;
	private Team initialTeam;
	private Workflow workflow;

	public ItemType(String name, Workflow w, Team t) {
		this.typeName = name;
		this.initialTeam = t;
		this.workflow = w;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the workflow
	 */
	public Workflow getWorkflow() {
		return workflow;
	}

	/**
	 * @param workflow
	 *            the workflow to set
	 */
	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	/**
	 * @return the initialTeam
	 */
	public Team getInitialTeam() {
		return initialTeam;
	}

	/**
	 * @param initialTeam
	 *            the initialTeam to set
	 */
	public void setInitialTeam(Team initialTeam) {
		this.initialTeam = initialTeam;
	}

}
