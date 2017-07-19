package a01001690.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import a01001690.data.Persona;

public class PersonasDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JList<Persona> list = new JList<Persona>();

	/**
	 * Create the dialog.
	 */
	public PersonasDialog(ArrayList<Persona> personas) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		{
			PersonasListModel plm = new PersonasListModel(personas);
			list.setModel(plm);
			contentPanel.add(list);
			list.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent arg0) {
					if (!arg0.getValueIsAdjusting()) {
						Persona persona = list.getSelectedValue();
						PersonaDialog dialog = new PersonaDialog(persona);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
						// label.setText(dataList.getSelectedValue().toString());
					}
				}
			});

			this.getContentPane().add(new JScrollPane(list));
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ev) {
						// System.exit(0);
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ev) {
						// System.exit(0);
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

}
