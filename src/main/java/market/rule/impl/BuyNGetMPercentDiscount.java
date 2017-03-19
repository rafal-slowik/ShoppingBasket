/**
 * 
 */
package market.rule.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import market.item.Item;
import market.rule.BuyNEqualItems;

/**
 * @author Rafal Slowik
 * @date 18 Mar 2017
 *
 */
public class BuyNGetMPercentDiscount extends BuyNEqualItems {

	private int precentDiscount;

	/**
	 * @param itemId
	 * @param buy
	 */
	public BuyNGetMPercentDiscount(long itemId, int buy, int percentDiscount) {
		super(itemId, buy);
		this.precentDiscount = percentDiscount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see market.rule.AbstractPriceRule#priceRuleConsumer(java.util.List)
	 */
	@Override
	public void processFilteredItems(List<Item> filteredItems) {
		List<Item> listToOperate = Optional.of(filteredItems).orElse(new ArrayList<>());
		listToOperate.subList(0, (listToOperate.size() / buy) * buy).stream().forEach(item -> {
			item.setUsedFlag(true);
			item.setRealPrice(item.getNormalPrice().multiply(BigDecimal.valueOf((100 - precentDiscount) / 100d)));
			item.setOfferName(precentDiscount + "% off: ");
		});
	}
}
