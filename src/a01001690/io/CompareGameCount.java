/**
 * Project: a01001690Gis
 * File: CompareGameCount.java
 * Date: Feb 26, 2017
 * Time: 5:13:36 PM
 */
package a01001690.io;

import java.util.Comparator;

import a01001690.data.Entry;

/**
 * @author chrisdean A01001690
 *
 */
public class CompareGameCount implements Comparator<Entry> {

	@Override
	public int compare(Entry e1, Entry e2) {
		return (e1.getLose() + e1.getWin()) - (e2.getLose() + e2.getWin());
	}
}
