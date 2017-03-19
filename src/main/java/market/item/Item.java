package market.item;

import static market.property.ConfigProperties.getConfigInstance;

import java.math.BigDecimal;

import com.google.common.base.Preconditions;

/**
 * Superclass of all items in the supermarket.
 * 
 * @author Rafal Slowik
 * @date 18 Mar 2017
 *
 */
public abstract class Item {

	private String offerName;
	// unique id
	protected long id;
	protected BigDecimal realPrice;
	protected BigDecimal normalPrice;
	

	// item name
	protected String itemName;
	// marker if the item was already taken in special price rule
	protected boolean usedFlag;

	/**
	 * @param itemName
	 *            - the name of the item. If longer than 30 characters then will
	 *            be truncated to 30 characters.
	 * 
	 * @throws IllegalArgumentException
	 *             when {@code itemName} is {@code null}
	 */
	public Item(String itemName) {
		checkPreconditions(itemName);
		this.itemName = itemName.length() > 30 ? itemName.substring(0, 30) : itemName;
	}

	private void checkPreconditions(String itemName) {
		Preconditions.checkArgument(itemName != null,
				getConfigInstance().formatProperty("message.variable_must_not_be_null", "item name"));
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

	public BigDecimal getNormalPrice() {
		return normalPrice;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public boolean isUsedFlag() {
		return usedFlag;
	}

	public void setUsedFlag(boolean usedFlag) {
		this.usedFlag = usedFlag;
	}

	public BigDecimal getDiscount() {
		if (isUsedFlag()) {
			return realPrice.subtract(normalPrice);
		}
		return BigDecimal.ZERO;
	}
	
	/**
	 * @return the offerName
	 */
	public String getOfferName() {
		return offerName;
	}
	
	/**
	 * @param offerName the offerName to set
	 */
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((normalPrice == null) ? 0 : normalPrice.hashCode());
		result = prime * result + ((realPrice == null) ? 0 : realPrice.hashCode());
		result = prime * result + (usedFlag ? 1231 : 1237);
		return result;
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
		if (id != other.id)
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (normalPrice == null) {
			if (other.normalPrice != null)
				return false;
		} else if (!normalPrice.equals(other.normalPrice))
			return false;
		if (realPrice == null) {
			if (other.realPrice != null)
				return false;
		} else if (!realPrice.equals(other.realPrice))
			return false;
		if (usedFlag != other.usedFlag)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", realPrice=" + realPrice + ", normalPrice=" + normalPrice + ", itemName=" + itemName
				+ ", usedFlag=" + usedFlag + "]";
	}
}
