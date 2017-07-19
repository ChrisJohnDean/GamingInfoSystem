package a01001690.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import a01001690.data.Persona;
import net.miginfocom.swing.MigLayout;

public class PersonaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public PersonaDialog(Persona persona) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[grow][grow][grow][grow][grow][grow]"));
		{
			JLabel lblId = new JLabel("ID");
			contentPanel.add(lblId, "cell 0 0,alignx right,aligny center");
		}
		{
			JTextArea textArea = new JTextArea(persona.getId());
			textArea.setEditable(false);
			contentPanel.add(textArea, "cell 1 0,growx,aligny center");
		}
		{
			JLabel lblFirstName = new JLabel("Player id");
			contentPanel.add(lblFirstName, "cell 0 1,alignx right,aligny center");
		}
		{
			JTextArea textArea = new JTextArea(persona.getPlayerId());
			contentPanel.add(textArea, "cell 1 1,growx,aligny center");
		}
		{
			JLabel lblLastName = new JLabel("Gamertag");
			contentPanel.add(lblLastName, "cell 0 2,alignx right,aligny center");
		}
		{
			JTextArea textArea = new JTextArea(persona.getGamerTag());
			contentPanel.add(textArea, "cell 1 2,growx,aligny center");
		}
		{
			JLabel lblEmailAddress = new JLabel("Platform");
			contentPanel.add(lblEmailAddress, "cell 0 3,alignx right,aligny center");
		}
		{
			JTextArea textArea = new JTextArea(persona.getPlatform());
			contentPanel.add(textArea, "cell 1 3,growx,aligny center");
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
