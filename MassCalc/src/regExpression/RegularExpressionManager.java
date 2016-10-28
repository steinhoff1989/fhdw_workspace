package regExpression;


import java.util.HashMap;
import java.util.Map;

import model.Vervielfältiger;

public class RegularExpressionManager {

	private Map<RegularExpressionQuantity, Vervielfältiger> regularExpressionsQ;
	private static RegularExpressionManager instance;

	public static RegularExpressionManager getInstance() {
		if (instance == null) {
			instance = new RegularExpressionManager();
		}
		return instance;
	}

	private RegularExpressionManager() {
		regularExpressionsQ = new HashMap<RegularExpressionQuantity, Vervielfältiger>();
	}

	public void add(RegularExpression regExp) {
		// Iterator<RegularExpressionQuantity> i =
		// this.regularExpressions.iterator();
		// while(i.hasNext()){
		// RegularExpressionQuantity current = i.next();
		// if(current.)
		// }

		RegularExpressionQuantity regExpQuantity = new RegularExpressionQuantity(regExp);
		if (this.regularExpressionsQ.keySet().contains(regExpQuantity)) {
//			this.regularExpressionsQ.get(regExpQuantity).
//			regExpQuantityAvailable.increment();
		} else {
//			this.regularExpressionsQ.put(regExpQuantity, value);
		}
	}

	public RegularExpressionQuantity getRegularExpressionQuantity(RegularExpression regExp)
			throws RegularExpressionQuantityNotInListException {
		RegularExpressionQuantity regExpQuantity = new RegularExpressionQuantity(regExp);
//		if (this.regularExpressionsQ.contains(regExpQuantity)) {
//			return this.regularExpressionsQ.get(this.regularExpressionsQ.indexOf(regExpQuantity));
//		} else {
			throw new RegularExpressionQuantityNotInListException();
//		}
	}

}
