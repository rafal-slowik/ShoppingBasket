/**
 * 
 */
package market;

import static market.item.ItemProducer.createItemByTypeName;
import static market.property.ConfigProperties.getConfigInstance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import market.item.Item;
import market.rule.IPriceRule;
import market.rule.impl.BuyNGetMPercentDiscount;
import market.rule.impl.BuyNGetMPercentDiscountForY;

/**
 * @author Rafal Slowik
 * @date 18 Mar 2017
 *
 */
public class PriceBasket {

	private List<Item> basket = new ArrayList<>();
	private List<IPriceRule> specialOffers = new ArrayList<>();
	private BigDecimal total = BigDecimal.ZERO;
	private BigDecimal subtotal = BigDecimal.ZERO;

	public PriceBasket(String[] items) {
		this();
		basket.addAll(Arrays.stream(Optional.ofNullable(items).orElse(new String[0])).map(s -> createItemByTypeName(s))
				.collect(Collectors.toCollection(ArrayList::new)));
	}

	public PriceBasket() {
		// add default rules
		specialOffers.add(new BuyNGetMPercentDiscount(getIdOf("apples.id"), 1, 10));
		specialOffers.add(new BuyNGetMPercentDiscountForY(getIdOf("soup.id"), 2, 50, getIdOf("bread.id")));
	}

	private void addToTotal(BigDecimal toAdd) {
		total = total.add(toAdd);
	}

	private long getIdOf(String id) {
		return Long.parseLong(getConfigInstance().findByKey(id));
	}

	private void addToSubtotal(BigDecimal addSubtotal) {
		subtotal = subtotal.add(addSubtotal);
	}

	/**
	 * Apply all price rules to the basket
	 * 
	 * @return recipe
	 */
	public void appyPriceRules() {
		Optional.ofNullable(specialOffers).orElse(new ArrayList<>()).stream()
				.forEach(rule -> rule.applyPriceRule(basket));
	}

	/**
	 * Generate recipe from current basket.
	 * 
	 * @return recipe
	 */
	public String printRecipe() {
		AtomicInteger line = new AtomicInteger(0);
		StringBuilder builder = new StringBuilder();
		StringBuilder offerBuilder = new StringBuilder();
		Optional.ofNullable(basket).orElse(new ArrayList<>()).stream().sorted(Comparator.comparing(Item::getItemName))
				.forEach(item -> {
					if (item.getDiscount().compareTo(BigDecimal.ZERO) != 0) {
						Utils.generateOfferLineToRecipe(item, offerBuilder, line.getAndIncrement());
					}
					addToSubtotal(item.getNormalPrice());
					addToTotal(item.getRealPrice());
				});
		Utils.getFinalRecipe(builder, offerBuilder, total, subtotal);
		return builder.toString();
	}

	/**
	 * Before print recipe all price rules will be applied.
	 * 
	 * @return recipe
	 */
	public String appyPriceRulesAndPrintRecipe() {
		appyPriceRules();
		return printRecipe();
	}

	/**
	 * @return the basket with the items
	 */
	public List<Item> getBasket() {
		return basket;
	}

	/**
	 * @return the specialOffers
	 */
	public List<IPriceRule> getSpecialOffers() {
		return specialOffers;
	}

	/**
	 * Add new price rule
	 * 
	 * @param rule
	 */
	public void addSpecialOffer(IPriceRule rule) {
		specialOffers.add(rule);
	}

	/**
	 * Add new {@link Item} to the basket.
	 * 
	 * @param item
	 *            - item to add
	 */
	public void addItemToBasket(Item item) {
		basket.add(item);
	}

	public static void main(String[] args) {
		PriceBasket basket = new PriceBasket(args);
		System.out.println(basket.appyPriceRulesAndPrintRecipe());
	}
}
