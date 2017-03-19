/**
 * 
 */
package market.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import market.item.Item;
import market.item.ItemProducer;

/**
 * @author Rafal Slowik
 * @date 17 Mar 2017
 *
 */
public class TestUtils {

	protected List<Item> prepareInputData(ItemTypeNumberPair... itemsToProduce) {
		Optional.ofNullable(itemsToProduce).orElseThrow(() -> new IllegalArgumentException("Parameter cannot be null!"));
		List<Item> items = new ArrayList<>();
		for (ItemTypeNumberPair pair : itemsToProduce) {
			for (int i = 0; i < pair.getNumberOfItems(); i++) {
				items.add(generateItem(pair.getProducer()));
			}
		}
		return items;
	}

	protected Item generateItem(ItemProducer type) {
		Optional.ofNullable(type).orElseThrow(() -> new IllegalArgumentException("Type of prodcer cannot be null!"));
		return type.create();
	}

	protected class ItemTypeNumberPair {
		private final ItemProducer producer;
		private final int numberOfItems;

		ItemTypeNumberPair(ItemProducer producer, int numberOfItems) {
			Optional.ofNullable(producer).orElseThrow(() -> new IllegalArgumentException("Type cannot be null"));
			if (numberOfItems < 0) {
				throw new IllegalArgumentException("Number of items cannot be negative");
			}
			this.producer = producer;
			this.numberOfItems = numberOfItems;
		}

		ItemProducer getProducer() {
			return producer;
		}

		int getNumberOfItems() {
			return numberOfItems;
		}
	}
}
