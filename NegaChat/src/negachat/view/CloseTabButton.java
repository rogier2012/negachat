package negachat.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CloseTabButton extends JPanel implements ActionListener {
	private JTabbedPane pane;
	private WhoIsOnlineController onlineController;

	public CloseTabButton(JTabbedPane pane, int index, WhoIsOnlineController onlineController) {
		this.pane = pane;
		this.onlineController = onlineController;
		setOpaque(false);
		add(new JLabel(pane.getTitleAt(index), pane.getIconAt(index),
				SwingConstants.LEFT));
		Icon closeIcon = new CloseIcon();
		JButton btClose = new JButton(closeIcon);
		btClose.setPreferredSize(new Dimension(closeIcon.getIconWidth(),
				closeIcon.getIconHeight()));
		add(btClose);
		btClose.addActionListener(this);
		pane.setTabComponentAt(index, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int i = pane.indexOfTabComponent(this);
		if (i != -1) {
			pane.remove(i);
			this.onlineController.removeClient(pane.getTitleAt(i));
		}
	}
}
