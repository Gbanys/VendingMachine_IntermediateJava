package com.vd.Service;

import java.math.BigDecimal;
import java.util.List;

import com.vd.DAO.VendingMachinePersistenceException;
import com.vd.DTO.Change;
import com.vd.DTO.Item;

public interface VendingMachineServiceLayer {

	Change buyItems(String name, BigDecimal cash) throws
		InsufficientFundsException,
		NoItemInventoryException,
		VendingMachinePersistenceException,
		DataValidationException,
		InvalidItemException;
		
	List<Item> getAllItems() throws
		VendingMachinePersistenceException;
	
	void addItem(Item item) throws DataValidationException, VendingMachinePersistenceException, InvalidItemException;
}
