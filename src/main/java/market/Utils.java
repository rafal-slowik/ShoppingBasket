/**
 * 
 */
package market;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import market.item.Item;

/**
 * @author Rafal Slowik
 * @date 18 Mar 2017
 *
 */
public class Utils {

	private static NumberFormat format = NumberFormat.getCurrencyInstance(Locale.UK);
	private static String spaces30 = "                              ";
	private static String separator30 = "-----------------------------" + System.lineSeparator();
	private static String subtotalLabel = "Subtotal:      ";
	private static String totalLabel = "Total:         ";
	private static String noOffersLabel = "(No offers available)";

	public static void generateOfferLineToRecipe(Item item, StringBuilder builder, int lineNumber) {
		int start = lineNumber * 30;
		builder.append(spaces30);

		String idAndName = item.getItemName();
		builder.replace(start, start + idAndName.length(), idAndName);
		start += 15;
		String offer = item.getOfferName() + getFormated(item.getDiscount());
		builder.replace(start, start + offer.length(), offer);
		start += 14;
		builder.replace(start, start + System.lineSeparator().length(), System.lineSeparator());
	}

	public static void getFinalRecipe(StringBuilder builder, StringBuilder offersBuilder, BigDecimal total,
			BigDecimal subtotal) {
		builder.append(subtotalLabel).append(format.format(subtotal)).append(System.lineSeparator());
		builder.append(separator30);
		if (subtotal.compareTo(total) == 0) {
			builder.append(noOffersLabel).append(System.lineSeparator());
		} else {
			builder.append(offersBuilder);
		}
		builder.append(separator30);
		builder.append(totalLabel).append(format.format(total)).append(System.lineSeparator());
	}

	private static String getFormated(BigDecimal decimal) {
		return format.format(decimal);
	}
}
