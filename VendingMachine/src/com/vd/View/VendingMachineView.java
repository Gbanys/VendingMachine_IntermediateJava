package com.vd.View;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vd.DTO.Change;
import com.vd.DTO.Change.Coin;
import com.vd.DTO.Item;

import lombok.var;

@Component
public class VendingMachineView {
	
	@Autowired
	private UserIO io;
	
	public VendingMachineView(UserIO io) {
		this.io = io;
	}
	
	//Display all items in the vending machine
	public void displayAllItems(List<Item> items) {
		
		io.print("This vending machine contains the following items:");
		
		for(Item item : items) {
			io.print("Item name: " + item.getItemName());
			io.print("Price: " + item.getPrice());
			io.print("Items remaining: " + item.getInventoryLevel());
			io.print("");
		}
	}
	
	//Returns the change as number of coins of each category
	public void displayChange(List<Change.Coin> coins) {
		
		int penny_counter = 0;
		int nickel_counter = 0;
		int dime_counter = 0;
		int quarter_counter = 0;
		
		for(Change.Coin coin : coins) {
			if (coin == Coin.PENNY) {
				penny_counter += 1;
			}
			else if(coin == Coin.NICKEL) {
				nickel_counter += 1;
			}
			else if(coin == Coin.DIME) {
				dime_counter += 1;
			}
			else if(coin == Coin.QUARTER) {
				quarter_counter += 1;
			}
		}
		io.print("Your change contains the following:");
		io.print(penny_counter + " Pennies");
		io.print(nickel_counter + " Nickels");
		io.print(dime_counter + " Dimes");
		io.print(quarter_counter + " Quarters");
		
	}
	
	//Insert money into vending machine
	public BigDecimal insertFunds() {
		
		String userInput = io.readString("Please insert some money into the vending machine");
		io.print("");
		BigDecimal userFunds = new BigDecimal(userInput);
		userFunds.setScale(2, RoundingMode.HALF_UP);
		return userFunds;
	}
	
	//Select an item from a vending machine
	public String selectItem() {
		
		String userInput = io.readString("Please select the item that you want to buy");
		io.print("");
		return userInput;
	}
}
