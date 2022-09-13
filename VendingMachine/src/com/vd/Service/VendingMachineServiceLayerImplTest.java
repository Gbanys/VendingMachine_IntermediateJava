package com.vd.Service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.vd.DAO.VendingMachineAuditDao;
import com.vd.DAO.VendingMachineDao;
import com.vd.DAO.VendingMachinePersistenceException;
import com.vd.DTO.Item;

class VendingMachineServiceLayerImplTest {

	private VendingMachineServiceLayer service;
	
	public VendingMachineServiceLayerImplTest() {
		VendingMachineDao dao = new VendingMachineDaoStubImpl();
		VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
		
		service = new VendingMachineServiceLayerImpl(dao, auditDao);
	}
	
	@BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws Exception {
    	Item item = new Item();
    	item.setItemName("Milkyway");
    	item.setPrice(new BigDecimal("2.00"));
    	item.setInventoryLevel(10);
    	service.addItem(item);
    }

    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void addValidItem() throws Exception{
    	
    	Item item = new Item();
    	item.setItemName("Milkyway");
    	item.setPrice(new BigDecimal("2.00"));
    	item.setInventoryLevel(10);
    	
    	try {
    		service.addItem(item);
    		return;
    	}
    	catch(Exception e) {
    		fail("Could not add an item");
    	}
    	
    }

    @Test
    public void buyValidItem() throws Exception{
    	
    	String itemName = "Milkyway";
    	BigDecimal cash = new BigDecimal("3.00");
    	
    	try {
    		service.buyItems(itemName, cash);
    	}
    	catch (InsufficientFundsException | NoItemInventoryException | VendingMachinePersistenceException | DataValidationException e){
    		fail("Item and money were valid, no exception should've occured here");
    	}
    }
    
    @Test
    public void testNotEnoughMoney() throws Exception{
    	String itemName = "Milkyway";
    	BigDecimal cash = new BigDecimal("1.00");
    	
    	try {
    		service.buyItems(itemName, cash);
    		fail("InsufficientFundsException was not thrown");
    	}
    	catch (NoItemInventoryException | VendingMachinePersistenceException | DataValidationException | InvalidItemException e) {
    		fail("Incorrect exception was thrown");
    	}
    	catch(InsufficientFundsException e) {
    		return;
    	}
    }
    
    @Test
    public void testNoItemsLeft() throws InsufficientFundsException, NoItemInventoryException, 
    VendingMachinePersistenceException, DataValidationException, InvalidItemException {
    	String itemName = "Milkyway";
    	BigDecimal cash = new BigDecimal("3.00");
    	
    	for(int i = 0; i < 10; i++) {
    		service.buyItems(itemName, cash);
    	}
    	
    	try {
    		service.buyItems(itemName, cash);
    		fail("NoItemInventoryException was not thrown");
    	}
    	catch (InsufficientFundsException | VendingMachinePersistenceException | DataValidationException | InvalidItemException e) {
    		fail("Incorrect exception was thrown");
    	}
    	catch(NoItemInventoryException e) {
    		return;
    	}
    }
    
    @Test
    public void testInvalidItem() throws Exception{
    	
    	String itemName = "Dairymilk";
    	BigDecimal cash = new BigDecimal("3.00");
    	
    	try {
    		service.buyItems(itemName, cash);
    		fail("InvalidItemException was not thrown");
    	}
    	catch(InsufficientFundsException | VendingMachinePersistenceException | DataValidationException | NoItemInventoryException e) {
    		fail("Incorrect exception was thrown");
    	}
    	catch(InvalidItemException e) {
    		return;
    	}
    }

}
