/**
 * 
 */
package item.dto.itemType;

import item.domain.itemType.ItemType;
import user.dto.team.TeamDTO;
import user.dto.team.TeamDTOFactory;
import workflow.dto.WorkflowDTO;
import workflow.dto.WorkflowDTOFactory;
import base.dto.AbstractDTO;

/**
 * @author ignacio
 * 
 *         20/06/2012
 */
public class ItemTypeDTO extends AbstractDTO {

	private String name;
	private final TeamDTO initialTeamDTO;
	private final WorkflowDTO workflowDTO;

	public ItemTypeDTO(ItemType anItemType) {
		super(anItemType);
		this.setName(anItemType.getTypeName());
		this.initialTeamDTO = (TeamDTO) TeamDTOFactory.getInstance().getDTO(anItemType.getInitialTeam());
		this.workflowDTO = (WorkflowDTO) WorkflowDTOFactory.getInstance().getDTO(anItemType.getWorkflow());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TeamDTO getInitialTeamDTO() {
		return initialTeamDTO;
	}

	public WorkflowDTO getWorkflowDTO() {
		return workflowDTO;
	}
}