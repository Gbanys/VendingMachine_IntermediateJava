package com.vd.DAO;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.vd.DTO.Change;
import com.vd.DTO.Change.Coin;
import com.vd.DTO.Item;

class VendingMachineDaoImplTest {

	VendingMachineDao testDao;
	
	public VendingMachineDaoImplTest() {
		
	}
	
	@BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws IOException {
    	String testFile = "TestItems.txt";
    	new FileWriter(testFile);
    	testDao = new VendingMachineDaoImpl(testFile);
    }

    @AfterEach
    public void tearDown() {
    }

	
	@Test
	public void testAddBuyItem() throws VendingMachinePersistenceException {
		
		Item item = new Item();
		item.setItemName("Kitkat");
		item.setPrice(new BigDecimal("0.80"));
		item.setInventoryLevel(10);
		
		testDao.addItem(item);
		Item receivedAddedItem = testDao.getItem(item.getItemName());
		
		assertEquals(receivedAddedItem.getItemName(), item.getItemName(), "Checking item names");
		assertEquals(receivedAddedItem.getPrice(), item.getPrice(), "Checking item prices");
		assertEquals(receivedAddedItem.getInventoryLevel(), item.getInventoryLevel(), "Check inventory levels");
		
		BigDecimal cash = new BigDecimal("3.00");
		
		Change receivedChange = testDao.buyItems(item.getItemName(), cash);
		BigDecimal price = item.getPrice();
		Item receivedItem = testDao.getItem(item.getItemName());
		
		assertEquals(receivedItem.getInventoryLevel() + 1, item.getInventoryLevel(), "Inventory level must be reduced by 1");
		assertEquals(cash.subtract(price), receivedChange.getTotalChange(), "Change returned must be correct");
	}
	
	@Test
	public void testAddBuyAllItems() throws VendingMachinePersistenceException {
		
		Item firstItem = new Item();
		firstItem.setItemName("Pringles");
		firstItem.setPrice(new BigDecimal("1.80"));
		firstItem.setInventoryLevel(15);
		
		Item secondItem = new Item();
		secondItem.setItemName("Magnum");
		secondItem.setPrice(new BigDecimal("3.00"));
		secondItem.setInventoryLevel(7);
		
		testDao.addItem(firstItem);
		testDao.addItem(secondItem);
		
		List<Item> allItems = testDao.getAllItems();
		
		assertNotNull(allItems, "The all items list must not be null");
		assertEquals(2, allItems.size(), "There should only be two items in the list");
		assertTrue(allItems.contains(firstItem), "The list must have Pringles");
		assertTrue(allItems.contains(secondItem), "The list must have Magnum ice cream");
		
		BigDecimal cash = new BigDecimal("10.00");
		
		Change firstChange = testDao.buyItems(firstItem.getItemName(), cash);
		Change secondChange = testDao.buyItems(secondItem.getItemName(), cash);
		
		BigDecimal firstItemPrice = firstItem.getPrice();
		BigDecimal secondItemPrice = secondItem.getPrice();
		Item receivedFirstItem = testDao.getItem(firstItem.getItemName());
		Item receivedSecondItem = testDao.getItem(secondItem.getItemName());
		
		assertEquals(receivedFirstItem.getInventoryLevel() + 1, firstItem.getInventoryLevel(), "Inventory level must be reduced by 1");
		assertEquals(receivedSecondItem.getInventoryLevel() + 1, secondItem.getInventoryLevel(), "Inventory level must be reduced by 1");
		assertEquals(cash.subtract(firstItemPrice), firstChange.getTotalChange(), "Change returned from first item must be correct");
		assertEquals(cash.subtract(secondItemPrice), secondChange.getTotalChange(), "Change returned from second item must be correct");
		
	}

}
