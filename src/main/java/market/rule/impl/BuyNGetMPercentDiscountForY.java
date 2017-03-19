/**
 * 
 */
package market.rule.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import market.item.Item;
import market.rule.BuyNEqualItems;

/**
 * @author Rafal Slowik
 * @date 18 Mar 2017
 *
 */
public class BuyNGetMPercentDiscountForY extends BuyNEqualItems {

	private int precentDiscount;
	private long discountItemId;

	/**
	 * 
	 * @param itemId
	 * @param buy
	 * @param percentDiscount
	 * @param discountItemId
	 */
	public BuyNGetMPercentDiscountForY(long itemId, int buy, int percentDiscount, long discountItemId) {
		super(itemId, buy);
		this.precentDiscount = percentDiscount;
		this.discountItemId = discountItemId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see market.rule.BuyNEqualItems#selectionFilterPredicate()
	 */
	@Override
	public Predicate<Item> selectionFilterPredicate() {
		return super.selectionFilterPredicate().or(item -> item.getId() == discountItemId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see market.rule.AbstractPriceRule#priceRuleConsumer(java.util.List)
	 */
	@Override
	public void processFilteredItems(List<Item> filteredItems) {
		List<Item> listToOperate = Optional.of(filteredItems).orElse(new ArrayList<>());
		int countOfBoughtItems = (int) listToOperate.stream().filter(it -> it.getId() == itemId).count();
		int countDiscountedItems = (int) listToOperate.stream().filter(it -> it.getId() == discountItemId).count();
		if (countOfBoughtItems < buy || countDiscountedItems == 0) {
			return;
		}
		int discountedItemsToUpdate = Math.min((countOfBoughtItems / buy), countDiscountedItems);
		int toUpdate = discountedItemsToUpdate * buy;
		listToOperate.stream().filter(it -> it.getId() == itemId).collect(Collectors.toCollection(ArrayList::new))
				.subList(0, toUpdate).forEach(it -> it.setUsedFlag(true));

		listToOperate.stream().filter(it -> it.getId() == discountItemId)
				.collect(Collectors.toCollection(ArrayList::new)).subList(0, discountedItemsToUpdate).forEach(it -> {
					it.setUsedFlag(true);
					it.setRealPrice(it.getNormalPrice().multiply(BigDecimal.valueOf((100 - precentDiscount) / 100d)));
					it.setOfferName(precentDiscount + "% off: ");
				});
	}
}
