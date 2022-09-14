package com.vd.Main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vd.Controller.VendingMachineController;
import com.vd.DAO.VendingMachineAuditDao;
import com.vd.DAO.VendingMachineAuditLogFileImpl;
import com.vd.DAO.VendingMachineDao;
import com.vd.DAO.VendingMachineDaoImpl;
import com.vd.DAO.VendingMachinePersistenceException;
import com.vd.Service.DataValidationException;
import com.vd.Service.InsufficientFundsException;
import com.vd.Service.NoItemInventoryException;
import com.vd.Service.VendingMachineServiceLayer;
import com.vd.Service.VendingMachineServiceLayerImpl;
import com.vd.View.UserIO;
import com.vd.View.UserIOImpl;
import com.vd.View.VendingMachineView;

public class Main {

	public static void main(String[] args) throws Exception {

		//Use the XML file for configuration and inject objects into classes
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);
		controller.run();
	}

}
