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

import a01001690.data.Persona;
import a01001690.data.Score;

public class ScoresDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JList<Persona> list = new JList<Persona>();

	/**
	 * Create the dialog.
	 */
	public ScoresDialog(ArrayList<Score> scores) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		{
			ScoresListModel plm = new ScoresListModel(scores);
			list.setModel(plm);
			contentPanel.add(list);
		}
		this.getContentPane().add(new JScrollPane(list));

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
						dispose();
						// System.exit(0);
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
						dispose();
						// System.exit(0);
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

}
