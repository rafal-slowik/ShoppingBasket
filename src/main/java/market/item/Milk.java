/**
 * 
 */
package market.item;

import static market.property.ConfigProperties.getConfigInstance;

import java.math.BigDecimal;

/**
 * @author Rafal Slowik
 * @date 18 Mar 2017
 *
 */
public class Milk extends Item {

	/**
	 * 
	 */
	public Milk() {
		super(getConfigInstance().findByKey("milk.name"));
		this.normalPrice = new BigDecimal(getConfigInstance().findByKey("milk.price"));
		this.id = Long.parseLong(getConfigInstance().findByKey("milk.id"));
		this.realPrice = normalPrice;
	}
}
