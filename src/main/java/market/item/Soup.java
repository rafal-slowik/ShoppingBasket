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
public class Soup extends Item {

	public Soup() {
		super(getConfigInstance().findByKey("soup.name"));
		this.normalPrice = new BigDecimal(getConfigInstance().findByKey("soup.price"));
		this.id = Long.parseLong(getConfigInstance().findByKey("soup.id"));
		this.realPrice = normalPrice;
	}
}
