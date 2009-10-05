package aima.games;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class GameState extends Hashtable {

	public boolean equals(Object anotherState) {
		GameState another = (GameState) anotherState;
		Set keySet1 = keySet();
		Iterator i = keySet1.iterator();
		Iterator j = another.keySet().iterator();
		while (i.hasNext()) {
			String key = (String) i.next();
			boolean keymatched = false;
			boolean valueMatched = false;
			while (j.hasNext()) {
				String key2 = (String) j.next();
				if (key.equals(key2)) {
					keymatched = true;
					if (get(key).equals(another.get(key2))) {
						valueMatched = true;
					}
					break;
				}
			}
			if (!((keymatched) && valueMatched)) {
				return false;
			}
		}
		return true;
	}

}