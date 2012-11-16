/**
 * 
 */
package item.domain.itemType;

import user.domain.team.Team;
import workflow.domain.Workflow;
import base.domain.BaseDomain;

/**
 * @author igallego
 * 
 *         This is a Type Object for item type.
 */
public class ItemType extends BaseDomain {

	private String typeName;
	private Team initialTeam;
	private Workflow workflow;

	public ItemType() { // para que funcione la NamedQuery al traer de la base
		this.typeName = "";
		this.initialTeam = null;
		this.workflow = null;
	}

	public ItemType(String name, Workflow w, Team t) {
		super();
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

	public Workflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	public Team getInitialTeam() {
		return initialTeam;
	}

	public void setInitialTeam(Team initialTeam) {
		this.initialTeam = initialTeam;
	}

}
