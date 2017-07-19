/**
 * Project: a01001690Gis
 * File: ScoresListModel.java
 * Date: Mar 26, 2017
 * Time: 10:47:00 AM
 */
package a01001690.ui;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

/**
 * @author chrisdean A01001690
 *
 */
public class ReportsListModel extends AbstractListModel {
	ArrayList<String> games;

	public ReportsListModel(ArrayList<String> report) {
		this.games = report;
	}

	@Override
	public int getSize() {
		return games.size();
	}

	@Override
	public Object getElementAt(int index) {
		return games.get(index);
	}
}
