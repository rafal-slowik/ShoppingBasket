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
public class Bread extends Item {

	/**
	 * 
	 */
	public Bread() {
		super(getConfigInstance().findByKey("bread.name"));
		this.normalPrice = new BigDecimal(getConfigInstance().findByKey("bread.price"));
		this.id = Long.parseLong(getConfigInstance().findByKey("bread.id"));
		this.realPrice = normalPrice;
	}
}
