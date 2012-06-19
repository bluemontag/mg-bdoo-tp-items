package item.repository;

import item.domain.Item;
import item.dto.ItemDTO;
import item.dto.ItemDTOForLists;
import item.exception.UnknownItemException;

import java.util.Collection;
import java.util.HashSet;

import org.hibernate.Query;

import base.exception.BaseException;
import base.repository.HibernateBaseRepository;

/**
 * @author Ignacio Gallego (ignaciogallego@gmail.com)
 */
public class HibernateItemRepository extends HibernateBaseRepository implements ItemRepositoryBI {

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return Item.class;
	}

	@Override
	public Item getItemByOid(String anOid) throws UnknownItemException {
		Item anItem = (Item) this.findeByOid(this.getEntityClass(), anOid);
		if (anItem == null) {
			throw new UnknownItemException();
		}
		return anItem;
	}

	@Override
	public Item getItemByNum(Long itemNum) throws UnknownItemException {
		Item item = null;
		try {
			item = (Item) this.getEntityByUniqueField("getItemByNum", "itemNum", itemNum);
		} catch (BaseException aBaseException) {
			// TODO ver que hacer!!
		}
		if (item == null) {
			throw new UnknownItemException("El item " + itemNum + " no existe.");
		}
		return item;
	}

	@Override
	public Item getItemByDTO(ItemDTO aItemDTO) throws UnknownItemException {
		return this.getItemByOid(aItemDTO.getOid());
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<Item> getItemsByDTOsList(Collection<ItemDTOForLists> itemsDTOs) throws UnknownItemException {

		Collection<String> oids = this.getOidsFromColleccionOfDTOs(itemsDTOs);

		Query aQuery = this.getNamedQuery("getItemsByOids");

		aQuery.setParameterList("oids", oids);
		aQuery.setParameter("isRemoved", false);

		Collection<Item> items = aQuery.list();

		if (items == null) {
			throw new UnknownItemException("No se encontro ningun item de la lista.");
		}
		if (items.size() != itemsDTOs.size()) {
			throw new UnknownItemException("Alguno de los items de la lista no se encontró o fue eliminado.");
		}
		return new HashSet<Item>(items);

	}

}
