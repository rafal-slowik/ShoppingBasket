/**
 * 
 */
package market.item;

import java.util.Arrays;

import market.property.ConfigProperties;

/**
 * Factory for {@link Item} family objects.
 * 
 * @author Rafal Slowik
 * @date 18 Mar 2017
 *
 */
public enum ItemProducer {

	APPLES(Apples.class.getSimpleName()) {
		@Override
		public Item create() {
			return new Apples();
		}
	},
	BREAD(Bread.class.getSimpleName()) {
		@Override
		public Item create() {
			return new Bread();
		}
	},
	MILK(Milk.class.getSimpleName()) {
		@Override
		public Item create() {
			return new Milk();
		}
	},
	SOUP(Soup.class.getSimpleName()) {
		@Override
		public Item create() {
			return new Soup();
		}
	};

	private String className;

	private ItemProducer(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	public abstract Item create();

	/**
	 * Produce new instance of the corresponding {@link Item} type. The object
	 * type is case insensitive.
	 * 
	 * @param type
	 *            - type name of the concrete Item instance.
	 * @return new {@link Item} instance
	 * 
	 * @throws IllegalArgumentException
	 *             if such type of object does not exist.
	 * 
	 */
	public static Item createItemByTypeName(String type) {
		return Arrays.stream(ItemProducer.values()).filter(t -> t.getClassName().equalsIgnoreCase(type)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException(
						ConfigProperties.getConfigInstance().formatProperty("message.wrong.item.type", type)))
				.create();
	}
}
