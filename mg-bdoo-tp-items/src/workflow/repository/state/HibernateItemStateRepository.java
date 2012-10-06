package workflow.repository.state;

import workflow.domain.state.ItemState;
import workflow.dto.state.ItemStateDTO;
import workflow.exception.state.UnknownItemStateException;
import base.exception.BaseException;
import base.repository.HibernateBaseRepository;

/**
 * @author Ignacio Gallego
 */
public class HibernateItemStateRepository extends HibernateBaseRepository implements ItemStateRepositoryBI {

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return ItemState.class;
	}

	@Override
	public ItemState getItemStateByOid(String anOid) throws UnknownItemStateException {
		ItemState aItemState = (ItemState) this.findByOid(this.getEntityClass(), anOid);
		if (aItemState == null) {
			throw new UnknownItemStateException("No se encuentra el estado buscado.");
		}
		return aItemState;
	}

	@Override
	public ItemState getItemStateByName(String stateName) throws UnknownItemStateException {
		ItemState state = null;
		try {
			state = (ItemState) this.getEntityByUniqueField("getItemStateByName", "aStateName", stateName);
		} catch (BaseException aBaseException) {
			// TODO ver que hacer!!
		}
		if (state == null) {
			throw new UnknownItemStateException("El estado " + stateName + " no existe.");
		}
		return state;		
	}
	
	@Override
	public ItemState getItemStateByDTO(ItemStateDTO aItemStateDTO) throws UnknownItemStateException {
		return this.getItemStateByOid(aItemStateDTO.getOid());
	}
}
