/**
 * Project: a01001690Gis
 * File: PersonasListModel.java
 * Date: Mar 26, 2017
 * Time: 9:02:01 AM
 */
package a01001690.ui;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import a01001690.data.Persona;

/**
 * @author chrisdean A01001690
 *
 */
public class PersonasListModel extends AbstractListModel {
	ArrayList<Persona> personas;

	public PersonasListModel(ArrayList<Persona> personas) {
		this.personas = personas;
	}

	@Override
	public int getSize() {
		return personas.size();
	}

	@Override
	public Object getElementAt(int index) {
		return personas.get(index);
	}
}
