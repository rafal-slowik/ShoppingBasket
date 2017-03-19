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
public class Apples extends Item {

	/**
	 * 
	 */
	public Apples() {
		super(getConfigInstance().findByKey("apples.name"));
		this.normalPrice = new BigDecimal(getConfigInstance().findByKey("apples.price"));
		this.id = Long.parseLong(getConfigInstance().findByKey("apples.id"));
		this.realPrice = normalPrice;
	}
}
