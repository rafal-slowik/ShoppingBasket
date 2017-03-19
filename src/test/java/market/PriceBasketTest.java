/**
 * 
 */
package market;

import static market.item.ItemProducer.APPLES;
import static market.item.ItemProducer.BREAD;
import static market.item.ItemProducer.MILK;
import static market.item.ItemProducer.SOUP;

import org.junit.Before;
import org.junit.Test;


/**
 * @author Rafal Slowik
 * @date 17 Mar 2017
 *
 */
public class PriceBasketTest {

	private PriceBasket basket = null;

	@Before
	public void init() {
		basket = new PriceBasket();
	}

	/**
	 * It is not true test method, it is more like demonstration how to use
	 * {@link PriceBasket}
	 */
	@Test
	public void test1() {
		basket.addItemToBasket(APPLES.create());
		basket.addItemToBasket(BREAD.create());
		basket.addItemToBasket(MILK.create());
		basket.addItemToBasket(SOUP.create());
		basket.addItemToBasket(SOUP.create());
		
		basket.appyPriceRules();
		System.out.println(basket.printRecipe());
	}
	
	/**
	 * It is not true test method, it is more like demonstration how to use
	 * {@link PriceBasket}
	 */
	@Test
	public void test2() {
		basket.addItemToBasket(BREAD.create());
		basket.addItemToBasket(MILK.create());
		basket.addItemToBasket(SOUP.create());
		
		basket.appyPriceRules();
		System.out.println(basket.printRecipe());
	}
}
