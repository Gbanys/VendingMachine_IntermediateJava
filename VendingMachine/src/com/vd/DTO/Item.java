package com.vd.DTO;

import java.math.BigDecimal;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {

	private String itemName;
	private BigDecimal price;
	private int inventoryLevel;
	
	
	@Override
	public int hashCode() {
		return Objects.hash(inventoryLevel, itemName, price);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return inventoryLevel == other.inventoryLevel && Objects.equals(itemName, other.itemName)
				&& Objects.equals(price, other.price);
	}
	@Override
	public String toString() {
		return "Item [itemName=" + itemName + ", price=" + price + ", inventoryLevel=" + inventoryLevel + "]";
	}
	
	
}
