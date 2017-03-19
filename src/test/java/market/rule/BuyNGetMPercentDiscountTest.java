package market.rule;

import static market.property.ConfigProperties.getConfigInstance;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import market.item.Item;
import market.item.ItemProducer;
import market.rule.impl.BuyNGetMPercentDiscount;

/**
 * @author Rafal Slowik
 * @date 17 Mar 2017
 *
 */
public class BuyNGetMPercentDiscountTest extends TestUtils {

	private IPriceRule eachApplesBag10PercentCheaper = null;
	private IPriceRule each2ApplesBag60PercentCheaper = null;

	@Before
	public void init() {
		eachApplesBag10PercentCheaper = new BuyNGetMPercentDiscount(
				Long.parseLong(getConfigInstance().findByKey("apples.id")), 1, 10);
		each2ApplesBag60PercentCheaper = new BuyNGetMPercentDiscount(
				Long.parseLong(getConfigInstance().findByKey("apples.id")), 2, 60);
	}

	@Test
	public void buy2Apples10PercentCheaperForEachTest() {
		BigDecimal applePrice = generateItem(ItemProducer.APPLES).getNormalPrice();
		BigDecimal realPriceAfterDiscount = applePrice.multiply(BigDecimal.valueOf(90 / (double) 100));

		int applesNbr = 2;
		int milkNbr = 2;
		int soupNbr = 2;
		int breadNbr = 2;

		int expectedSize = applesNbr + milkNbr + soupNbr + breadNbr;

		int expectedNumberOfApplesInOffer = 2;
		int numberOfApplesInOffer = 0;
		int expectedNumberOfItemsInOffer = 2;
		int numberOfItemsInOffer = 0;

		ItemTypeNumberPair apples = new ItemTypeNumberPair(ItemProducer.APPLES, applesNbr);
		ItemTypeNumberPair milk = new ItemTypeNumberPair(ItemProducer.MILK, milkNbr);
		ItemTypeNumberPair soup = new ItemTypeNumberPair(ItemProducer.SOUP, soupNbr);
		ItemTypeNumberPair bread = new ItemTypeNumberPair(ItemProducer.BREAD, breadNbr);

		List<Item> items = prepareInputData(apples, milk, soup, bread);
		eachApplesBag10PercentCheaper.applyPriceRule(items);

		assertEquals(expectedSize, items.size());
		for (Item item : items) {
			if (item.getDiscount().compareTo(BigDecimal.ZERO) != 0) {
				numberOfItemsInOffer++;
				if (realPriceAfterDiscount.equals(item.getRealPrice())) {
					numberOfApplesInOffer++;
				}
			}
		}
		assertEquals(expectedNumberOfItemsInOffer, numberOfItemsInOffer);
		assertEquals(expectedNumberOfApplesInOffer, numberOfApplesInOffer);
	}
	
	@Test
	public void buy10Apples10PercentCheaperForEachTest() {
		BigDecimal applePrice = generateItem(ItemProducer.APPLES).getNormalPrice();
		BigDecimal realPriceAfterDiscount = applePrice.multiply(BigDecimal.valueOf(90 / (double) 100));

		int applesNbr = 10;
		int milkNbr = 2;
		int soupNbr = 2;
		int breadNbr = 2;

		int expectedSize = applesNbr + milkNbr + soupNbr + breadNbr;

		int expectedNumberOfApplesInOffer = 10;
		int numberOfApplesInOffer = 0;
		int expectedNumberOfItemsInOffer = 10;
		int numberOfItemsInOffer = 0;

		ItemTypeNumberPair apples = new ItemTypeNumberPair(ItemProducer.APPLES, applesNbr);
		ItemTypeNumberPair milk = new ItemTypeNumberPair(ItemProducer.MILK, milkNbr);
		ItemTypeNumberPair soup = new ItemTypeNumberPair(ItemProducer.SOUP, soupNbr);
		ItemTypeNumberPair bread = new ItemTypeNumberPair(ItemProducer.BREAD, breadNbr);

		List<Item> items = prepareInputData(apples, milk, soup, bread);
		eachApplesBag10PercentCheaper.applyPriceRule(items);

		assertEquals(expectedSize, items.size());
		for (Item item : items) {
			if (item.getDiscount().compareTo(BigDecimal.ZERO) != 0) {
				numberOfItemsInOffer++;
				if (realPriceAfterDiscount.equals(item.getRealPrice())) {
					numberOfApplesInOffer++;
				}
			}
		}
		assertEquals(expectedNumberOfItemsInOffer, numberOfItemsInOffer);
		assertEquals(expectedNumberOfApplesInOffer, numberOfApplesInOffer);
	}
	
	@Test
	public void buy0Apples10PercentCheaperForEachTest() {
		BigDecimal applePrice = generateItem(ItemProducer.APPLES).getNormalPrice();
		BigDecimal realPriceAfterDiscount = applePrice.multiply(BigDecimal.valueOf(90 / (double) 100));

		int applesNbr = 0;
		int milkNbr = 2;
		int soupNbr = 2;
		int breadNbr = 2;

		int expectedSize = applesNbr + milkNbr + soupNbr + breadNbr;

		int expectedNumberOfApplesInOffer = 0;
		int numberOfApplesInOffer = 0;
		int expectedNumberOfItemsInOffer = 0;
		int numberOfItemsInOffer = 0;

		ItemTypeNumberPair apples = new ItemTypeNumberPair(ItemProducer.APPLES, applesNbr);
		ItemTypeNumberPair milk = new ItemTypeNumberPair(ItemProducer.MILK, milkNbr);
		ItemTypeNumberPair soup = new ItemTypeNumberPair(ItemProducer.SOUP, soupNbr);
		ItemTypeNumberPair bread = new ItemTypeNumberPair(ItemProducer.BREAD, breadNbr);

		List<Item> items = prepareInputData(apples, milk, soup, bread);
		eachApplesBag10PercentCheaper.applyPriceRule(items);

		assertEquals(expectedSize, items.size());
		for (Item item : items) {
			if (item.getDiscount().compareTo(BigDecimal.ZERO) != 0) {
				numberOfItemsInOffer++;
				if (realPriceAfterDiscount.equals(item.getRealPrice())) {
					numberOfApplesInOffer++;
				}
			}
		}
		assertEquals(expectedNumberOfItemsInOffer, numberOfItemsInOffer);
		assertEquals(expectedNumberOfApplesInOffer, numberOfApplesInOffer);
	}
	
	@Test
	public void buy2Apples60PercentCheaperForEach2Test() {
		BigDecimal applePrice = generateItem(ItemProducer.APPLES).getNormalPrice();
		BigDecimal realPriceAfterDiscount = applePrice.multiply(BigDecimal.valueOf(40 / (double) 100));

		int applesNbr = 2;
		int milkNbr = 2;
		int soupNbr = 2;
		int breadNbr = 2;

		int expectedSize = applesNbr + milkNbr + soupNbr + breadNbr;

		int expectedNumberOfApplesInOffer = 2;
		int numberOfApplesInOffer = 0;
		int expectedNumberOfItemsInOffer = 2;
		int numberOfItemsInOffer = 0;

		ItemTypeNumberPair apples = new ItemTypeNumberPair(ItemProducer.APPLES, applesNbr);
		ItemTypeNumberPair milk = new ItemTypeNumberPair(ItemProducer.MILK, milkNbr);
		ItemTypeNumberPair soup = new ItemTypeNumberPair(ItemProducer.SOUP, soupNbr);
		ItemTypeNumberPair bread = new ItemTypeNumberPair(ItemProducer.BREAD, breadNbr);

		List<Item> items = prepareInputData(apples, milk, soup, bread);
		each2ApplesBag60PercentCheaper.applyPriceRule(items);

		assertEquals(expectedSize, items.size());
		for (Item item : items) {
			if (item.getDiscount().compareTo(BigDecimal.ZERO) != 0) {
				numberOfItemsInOffer++;
				if (realPriceAfterDiscount.equals(item.getRealPrice())) {
					numberOfApplesInOffer++;
				}
			}
		}
		assertEquals(expectedNumberOfItemsInOffer, numberOfItemsInOffer);
		assertEquals(expectedNumberOfApplesInOffer, numberOfApplesInOffer);
	}
	
	@Test
	public void buy3Apples60PercentCheaperForEach2Test() {
		BigDecimal applePrice = generateItem(ItemProducer.APPLES).getNormalPrice();
		BigDecimal realPriceAfterDiscount = applePrice.multiply(BigDecimal.valueOf(40 / (double) 100));

		int applesNbr = 2;
		int milkNbr = 2;
		int soupNbr = 2;
		int breadNbr = 2;

		int expectedSize = applesNbr + milkNbr + soupNbr + breadNbr;

		int expectedNumberOfApplesInOffer = 2;
		int numberOfApplesInOffer = 0;
		int expectedNumberOfItemsInOffer = 2;
		int numberOfItemsInOffer = 0;

		ItemTypeNumberPair apples = new ItemTypeNumberPair(ItemProducer.APPLES, applesNbr);
		ItemTypeNumberPair milk = new ItemTypeNumberPair(ItemProducer.MILK, milkNbr);
		ItemTypeNumberPair soup = new ItemTypeNumberPair(ItemProducer.SOUP, soupNbr);
		ItemTypeNumberPair bread = new ItemTypeNumberPair(ItemProducer.BREAD, breadNbr);

		List<Item> items = prepareInputData(apples, milk, soup, bread);
		each2ApplesBag60PercentCheaper.applyPriceRule(items);

		assertEquals(expectedSize, items.size());
		for (Item item : items) {
			if (item.getDiscount().compareTo(BigDecimal.ZERO) != 0) {
				numberOfItemsInOffer++;
				if (realPriceAfterDiscount.equals(item.getRealPrice())) {
					numberOfApplesInOffer++;
				}
			}
		}
		assertEquals(expectedNumberOfItemsInOffer, numberOfItemsInOffer);
		assertEquals(expectedNumberOfApplesInOffer, numberOfApplesInOffer);
	}
	
	@Test
	public void buy7Apples60PercentCheaperForEach2Test() {
		BigDecimal applePrice = generateItem(ItemProducer.APPLES).getNormalPrice();
		BigDecimal realPriceAfterDiscount = applePrice.multiply(BigDecimal.valueOf(40 / (double) 100));

		int applesNbr = 7;
		int milkNbr = 2;
		int soupNbr = 2;
		int breadNbr = 2;

		int expectedSize = applesNbr + milkNbr + soupNbr + breadNbr;

		int expectedNumberOfApplesInOffer = 6;
		int numberOfApplesInOffer = 0;
		int expectedNumberOfItemsInOffer = 6;
		int numberOfItemsInOffer = 0;

		ItemTypeNumberPair apples = new ItemTypeNumberPair(ItemProducer.APPLES, applesNbr);
		ItemTypeNumberPair milk = new ItemTypeNumberPair(ItemProducer.MILK, milkNbr);
		ItemTypeNumberPair soup = new ItemTypeNumberPair(ItemProducer.SOUP, soupNbr);
		ItemTypeNumberPair bread = new ItemTypeNumberPair(ItemProducer.BREAD, breadNbr);

		List<Item> items = prepareInputData(apples, milk, soup, bread);
		each2ApplesBag60PercentCheaper.applyPriceRule(items);

		assertEquals(expectedSize, items.size());
		for (Item item : items) {
			if (item.getDiscount().compareTo(BigDecimal.ZERO) != 0) {
				numberOfItemsInOffer++;
				if (realPriceAfterDiscount.equals(item.getRealPrice())) {
					numberOfApplesInOffer++;
				}
			}
		}
		assertEquals(expectedNumberOfItemsInOffer, numberOfItemsInOffer);
		assertEquals(expectedNumberOfApplesInOffer, numberOfApplesInOffer);
	}
}