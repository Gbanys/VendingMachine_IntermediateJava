package com.vd.DAO;

public interface VendingMachineAuditDao {

	public void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}
