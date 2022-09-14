package com.vd.Controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vd.DAO.VendingMachinePersistenceException;
import com.vd.DTO.Change;
import com.vd.DTO.Item;
import com.vd.Service.DataValidationException;
import com.vd.Service.InsufficientFundsException;
import com.vd.Service.NoItemInventoryException;
import com.vd.Service.VendingMachineServiceLayer;
import com.vd.View.VendingMachineView;

@Component
public class VendingMachineController {

	
	@Autowired
	private VendingMachineServiceLayer serviceLayer;
	private VendingMachineView view;
	
	//Use constructor based dependency injection to connect service layer and view to the controller
	public VendingMachineController(VendingMachineServiceLayer serviceLayer, VendingMachineView view) {
		this.serviceLayer = serviceLayer;
		this.view = view;
	}
	
	//Run the main program
	public void run() throws Exception {
		
		returnAllItems();
		BigDecimal userFunds = view.insertFunds();
		String userSelection = view.selectItem();
		Change change = serviceLayer.buyItems(userSelection, userFunds);
		view.displayChange(change.getCoins());
		
		
	}
	
	//Return all items from the vending machine
	public void returnAllItems() throws VendingMachinePersistenceException {
		
		List<Item> items = serviceLayer.getAllItems();
		view.displayAllItems(items);
	}
	
}
