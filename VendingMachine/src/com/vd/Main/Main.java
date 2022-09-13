package com.vd.Main;

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

		UserIO io = new UserIOImpl();
		VendingMachineDao dao = new VendingMachineDaoImpl();
		VendingMachineAuditDao auditDao = new VendingMachineAuditLogFileImpl();
		VendingMachineView view = new VendingMachineView(io);
		VendingMachineServiceLayer serviceLayer = new VendingMachineServiceLayerImpl(dao, auditDao);
		VendingMachineController Controller = new VendingMachineController(serviceLayer, view);
		Controller.run();
	}

}
