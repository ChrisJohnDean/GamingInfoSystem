/**
 * Project: a01001690Gis
 * File: Compare.java
 * Date: Feb 26, 2017
 * Time: 4:38:45 PM
 */
package a01001690.io;

import java.util.Comparator;

import a01001690.data.Entry;

/**
 * @author chrisdean A01001690
 *
 */
public class CompareGameName implements Comparator<Entry> {
	/*
	 * @Override
	 * public int compare(Player pl1, Player pl2) {
	 * return pl1.getBirthDate() - pl2.getBirthDate();
	 * }
	 */
	@Override
	public int compare(Entry g1, Entry g2) {
		return g1.getGameName().compareTo(g2.getGameName());
	}
}
