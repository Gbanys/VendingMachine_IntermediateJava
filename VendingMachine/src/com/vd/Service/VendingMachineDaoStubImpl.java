package com.vd.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.vd.DAO.VendingMachineDao;
import com.vd.DAO.VendingMachinePersistenceException;
import com.vd.DTO.Change;
import com.vd.DTO.Item;

public class VendingMachineDaoStubImpl implements VendingMachineDao{

	public Item item;
	public Change change;
	
	public VendingMachineDaoStubImpl() {
		item = new Item();
		item.setItemName("Dairymilk");
		item.setPrice(new BigDecimal("2.50"));
		item.setInventoryLevel(10);
	}
	
	public VendingMachineDaoStubImpl(Item testItem, Change testChange) {
		this.item = testItem;
		this.change = testChange;
	}
	
	@Override
	public void addItem(Item item) {
		return;
	}
	
	@Override
	public Change buyItems(String name, BigDecimal cash) throws VendingMachinePersistenceException{
		if(name.equals(item.getItemName())) {
			return change;
		}
		else {
			return null;
		}
	}
	
	@Override
	public List<Item> getAllItems(){
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(item);
		return itemList;
	}
	
	@Override
	public Item getItem(String name) {
		if(name.equals(item.getItemName())) {
			return item;
		}
		else {
			return null;
		}
	}
	
	@Override
	public Change getChange(BigDecimal itemPrice, BigDecimal cash) {
		if(itemPrice.equals(item.getPrice())) {
			return change;
		}
		else {
			return null;
		}
	}
}
