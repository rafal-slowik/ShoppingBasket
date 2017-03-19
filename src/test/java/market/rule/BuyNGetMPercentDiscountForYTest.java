/**
 * 
 */
package market.rule;

import static market.property.ConfigProperties.getConfigInstance;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import market.item.Item;
import market.item.ItemProducer;
import market.rule.impl.BuyNGetMPercentDiscountForY;

/**
 * @author Rafal Slowik
 * @date 17 Mar 2017
 *
 */
public class BuyNGetMPercentDiscountForYTest extends TestUtils {

	private IPriceRule each2Soup10PercentCheaperForBread = null;
	private IPriceRule each3Soup60PercentCheaperForBread = null;

	@Before
	public void init() {
		each2Soup10PercentCheaperForBread = new BuyNGetMPercentDiscountForY(
				Long.parseLong(getConfigInstance().findByKey("soup.id")), 2, 10,
				Long.parseLong(getConfigInstance().findByKey("bread.id")));
		each3Soup60PercentCheaperForBread = new BuyNGetMPercentDiscountForY(
				Long.parseLong(getConfigInstance().findByKey("soup.id")), 3, 60,
				Long.parseLong(getConfigInstance().findByKey("bread.id")));
	}

	@Test
	public void each2Soup10PercentCheaperForBread1Test() {
		BigDecimal itemPrice = generateItem(ItemProducer.BREAD).getNormalPrice();
		BigDecimal realPriceAfterDiscount = itemPrice.multiply(BigDecimal.valueOf(90 / (double) 100));

		int applesNbr = 2;
		int milkNbr = 2;
		int soupNbr = 2;
		int breadNbr = 2;

		int expectedSize = applesNbr + milkNbr + soupNbr + breadNbr;

		int expectedNumberOfBreadInOffer = 1;
		int numberOfBreadInOffer = 0;
		int expectedNumberOfItemsInOffer = 1;
		int numberOfItemsInOffer = 0;

		ItemTypeNumberPair apples = new ItemTypeNumberPair(ItemProducer.APPLES, applesNbr);
		ItemTypeNumberPair milk = new ItemTypeNumberPair(ItemProducer.MILK, milkNbr);
		ItemTypeNumberPair soup = new ItemTypeNumberPair(ItemProducer.SOUP, soupNbr);
		ItemTypeNumberPair bread = new ItemTypeNumberPair(ItemProducer.BREAD, breadNbr);

		List<Item> items = prepareInputData(apples, milk, soup, bread);
		each2Soup10PercentCheaperForBread.applyPriceRule(items);

		assertEquals(expectedSize, items.size());
		for (Item item : items) {
			if (item.getDiscount().compareTo(BigDecimal.ZERO) != 0) {
				numberOfItemsInOffer++;
				if (realPriceAfterDiscount.equals(item.getRealPrice())) {
					numberOfBreadInOffer++;
				}
			}
		}
		assertEquals(expectedNumberOfItemsInOffer, numberOfItemsInOffer);
		assertEquals(expectedNumberOfBreadInOffer, numberOfBreadInOffer);
	}

	@Test
	public void each2Soup10PercentCheaperForBread2Test() {
		BigDecimal itemPrice = generateItem(ItemProducer.BREAD).getNormalPrice();
		BigDecimal realPriceAfterDiscount = itemPrice.multiply(BigDecimal.valueOf(90 / (double) 100));

		int applesNbr = 0;
		int milkNbr = 2;
		int soupNbr = 10;
		int breadNbr = 2;

		int expectedSize = applesNbr + milkNbr + soupNbr + breadNbr;

		int expectedNumberOfBreadInOffer = 2;
		int numberOfBreadInOffer = 0;
		int expectedNumberOfItemsInOffer = 2;
		int numberOfItemsInOffer = 0;

		ItemTypeNumberPair apples = new ItemTypeNumberPair(ItemProducer.APPLES, applesNbr);
		ItemTypeNumberPair milk = new ItemTypeNumberPair(ItemProducer.MILK, milkNbr);
		ItemTypeNumberPair soup = new ItemTypeNumberPair(ItemProducer.SOUP, soupNbr);
		ItemTypeNumberPair bread = new ItemTypeNumberPair(ItemProducer.BREAD, breadNbr);

		List<Item> items = prepareInputData(apples, milk, soup, bread);
		each2Soup10PercentCheaperForBread.applyPriceRule(items);

		assertEquals(expectedSize, items.size());
		for (Item item : items) {
			if (item.getDiscount().compareTo(BigDecimal.ZERO) != 0) {
				numberOfItemsInOffer++;
				if (realPriceAfterDiscount.equals(item.getRealPrice())) {
					numberOfBreadInOffer++;
				}
			}
		}
		assertEquals(expectedNumberOfItemsInOffer, numberOfItemsInOffer);
		assertEquals(expectedNumberOfBreadInOffer, numberOfBreadInOffer);
	}

	@Test
	public void each2Soup10PercentCheaperForBread3Test() {
		BigDecimal itemPrice = generateItem(ItemProducer.BREAD).getNormalPrice();
		BigDecimal realPriceAfterDiscount = itemPrice.multiply(BigDecimal.valueOf(90 / (double) 100));

		int applesNbr = 1;
		int milkNbr = 2;
		int soupNbr = 0;
		int breadNbr = 2;

		int expectedSize = applesNbr + milkNbr + soupNbr + breadNbr;

		int expectedNumberOfBreadInOffer = 0;
		int numberOfBreadInOffer = 0;
		int expectedNumberOfItemsInOffer = 0;
		int numberOfItemsInOffer = 0;

		ItemTypeNumberPair apples = new ItemTypeNumberPair(ItemProducer.APPLES, applesNbr);
		ItemTypeNumberPair milk = new ItemTypeNumberPair(ItemProducer.MILK, milkNbr);
		ItemTypeNumberPair soup = new ItemTypeNumberPair(ItemProducer.SOUP, soupNbr);
		ItemTypeNumberPair bread = new ItemTypeNumberPair(ItemProducer.BREAD, breadNbr);

		List<Item> items = prepareInputData(apples, milk, soup, bread);
		each2Soup10PercentCheaperForBread.applyPriceRule(items);

		assertEquals(expectedSize, items.size());
		for (Item item : items) {
			if (item.getDiscount().compareTo(BigDecimal.ZERO) != 0) {
				numberOfItemsInOffer++;
				if (realPriceAfterDiscount.equals(item.getRealPrice())) {
					numberOfBreadInOffer++;
				}
			}
		}
		assertEquals(expectedNumberOfItemsInOffer, numberOfItemsInOffer);
		assertEquals(expectedNumberOfBreadInOffer, numberOfBreadInOffer);
	}

	@Test
	public void each3Soup60PercentCheaperForBread1Test() {
		BigDecimal itemPrice = generateItem(ItemProducer.BREAD).getNormalPrice();
		BigDecimal realPriceAfterDiscount = itemPrice.multiply(BigDecimal.valueOf(40 / (double) 100));

		int applesNbr = 2;
		int milkNbr = 2;
		int soupNbr = 2;
		int breadNbr = 2;

		int expectedSize = applesNbr + milkNbr + soupNbr + breadNbr;

		int expectedNumberOfBreadInOffer = 0;
		int numberOfBreadInOffer = 0;
		int expectedNumberOfItemsInOffer = 0;
		int numberOfItemsInOffer = 0;

		ItemTypeNumberPair apples = new ItemTypeNumberPair(ItemProducer.APPLES, applesNbr);
		ItemTypeNumberPair milk = new ItemTypeNumberPair(ItemProducer.MILK, milkNbr);
		ItemTypeNumberPair soup = new ItemTypeNumberPair(ItemProducer.SOUP, soupNbr);
		ItemTypeNumberPair bread = new ItemTypeNumberPair(ItemProducer.BREAD, breadNbr);

		List<Item> items = prepareInputData(apples, milk, soup, bread);
		each3Soup60PercentCheaperForBread.applyPriceRule(items);

		assertEquals(expectedSize, items.size());
		for (Item item : items) {
			if (item.getDiscount().compareTo(BigDecimal.ZERO) != 0) {
				numberOfItemsInOffer++;
				if (realPriceAfterDiscount.equals(item.getRealPrice())) {
					numberOfBreadInOffer++;
				}
			}
		}
		assertEquals(expectedNumberOfItemsInOffer, numberOfItemsInOffer);
		assertEquals(expectedNumberOfBreadInOffer, numberOfBreadInOffer);
	}

	@Test
	public void each3Soup60PercentCheaperForBread2Test() {
		BigDecimal itemPrice = generateItem(ItemProducer.BREAD).getNormalPrice();
		BigDecimal realPriceAfterDiscount = itemPrice.multiply(BigDecimal.valueOf(40 / (double) 100));

		int applesNbr = 2;
		int milkNbr = 2;
		int soupNbr = 3;
		int breadNbr = 2;

		int expectedSize = applesNbr + milkNbr + soupNbr + breadNbr;

		int expectedNumberOfBreadInOffer = 1;
		int numberOfBreadInOffer = 0;
		int expectedNumberOfItemsInOffer = 1;
		int numberOfItemsInOffer = 0;

		ItemTypeNumberPair apples = new ItemTypeNumberPair(ItemProducer.APPLES, applesNbr);
		ItemTypeNumberPair milk = new ItemTypeNumberPair(ItemProducer.MILK, milkNbr);
		ItemTypeNumberPair soup = new ItemTypeNumberPair(ItemProducer.SOUP, soupNbr);
		ItemTypeNumberPair bread = new ItemTypeNumberPair(ItemProducer.BREAD, breadNbr);

		List<Item> items = prepareInputData(apples, milk, soup, bread);
		each3Soup60PercentCheaperForBread.applyPriceRule(items);

		assertEquals(expectedSize, items.size());
		for (Item item : items) {
			if (item.getDiscount().compareTo(BigDecimal.ZERO) != 0) {
				numberOfItemsInOffer++;
				if (realPriceAfterDiscount.equals(item.getRealPrice())) {
					numberOfBreadInOffer++;
				}
			}
		}
		assertEquals(expectedNumberOfItemsInOffer, numberOfItemsInOffer);
		assertEquals(expectedNumberOfBreadInOffer, numberOfBreadInOffer);
	}

	@Test
	public void each3Soup60PercentCheaperForBread3Test() {
		BigDecimal itemPrice = generateItem(ItemProducer.BREAD).getNormalPrice();
		BigDecimal realPriceAfterDiscount = itemPrice.multiply(BigDecimal.valueOf(40 / (double) 100));

		int applesNbr = 7;
		int milkNbr = 2;
		int soupNbr = 7;
		int breadNbr = 3;

		int expectedSize = applesNbr + milkNbr + soupNbr + breadNbr;

		int expectedNumberOfBreadInOffer = 2;
		int numberOfBreadInOffer = 0;
		int expectedNumberOfItemsInOffer = 2;
		int numberOfItemsInOffer = 0;

		ItemTypeNumberPair apples = new ItemTypeNumberPair(ItemProducer.APPLES, applesNbr);
		ItemTypeNumberPair milk = new ItemTypeNumberPair(ItemProducer.MILK, milkNbr);
		ItemTypeNumberPair soup = new ItemTypeNumberPair(ItemProducer.SOUP, soupNbr);
		ItemTypeNumberPair bread = new ItemTypeNumberPair(ItemProducer.BREAD, breadNbr);

		List<Item> items = prepareInputData(apples, milk, soup, bread);
		each3Soup60PercentCheaperForBread.applyPriceRule(items);

		assertEquals(expectedSize, items.size());
		for (Item item : items) {
			if (item.getDiscount().compareTo(BigDecimal.ZERO) != 0) {
				numberOfItemsInOffer++;
				if (realPriceAfterDiscount.equals(item.getRealPrice())) {
					numberOfBreadInOffer++;
				}
			}
		}
		assertEquals(expectedNumberOfItemsInOffer, numberOfItemsInOffer);
		assertEquals(expectedNumberOfBreadInOffer, numberOfBreadInOffer);
	}
}
