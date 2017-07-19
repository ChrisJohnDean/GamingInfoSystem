/**
 * Project: a01001690Gis
 * File: ListModel.java
 * Date: Mar 22, 2017
 * Time: 11:53:56 PM
 */
package a01001690.ui;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import a01001690.data.Player;

/**
 * @author chrisdean A01001690
 *
 */
public class PlayersListModel extends AbstractListModel {
	ArrayList<Player> players;

	public PlayersListModel(ArrayList<Player> players) {
		this.players = players;
	}

	@Override
	public int getSize() {
		return players.size();
	}

	@Override
	public Object getElementAt(int index) {
		return players.get(index);
	}
	// String[] strings = new String[5];
	//
	// public ListModel(ArrayList<Player> players) {
	// int i = 0;
	// for (Player p : players) {
	// strings[i] = p.getId() + " " + p.getFirstName() + " " + p.getLastName() + " " + p.getEmail() + " " + p.getGamerTag() + " "
	// + p.getFormattedDate();
	// i++;
	// }
	// }
	//
	// @Override
	// public int getSize() {
	// return strings.length;
	// }
	//
	// @Override
	// public Object getElementAt(int index) {
	// return strings[index];
	// }

}
