/**
 * Project: a01001690Gis
 * File: ScoresListModel.java
 * Date: Mar 26, 2017
 * Time: 10:47:00 AM
 */
package a01001690.ui;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import a01001690.data.Score;

/**
 * @author chrisdean A01001690
 *
 */
public class ScoresListModel extends AbstractListModel {
	ArrayList<Score> scores;

	public ScoresListModel(ArrayList<Score> scores) {
		this.scores = scores;
	}

	@Override
	public int getSize() {
		return scores.size();
	}

	@Override
	public Object getElementAt(int index) {
		return scores.get(index);
	}
}
