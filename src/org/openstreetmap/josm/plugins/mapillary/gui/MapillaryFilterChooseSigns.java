package org.openstreetmap.josm.plugins.mapillary.gui;

import static org.openstreetmap.josm.tools.I18n.tr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.openstreetmap.josm.tools.ImageProvider;

public class MapillaryFilterChooseSigns extends JPanel implements
		ActionListener {

	public final JCheckBox maxspeed = new JCheckBox();
	public final JCheckBox stop = new JCheckBox();
	public final JCheckBox giveWay = new JCheckBox();
	public final JCheckBox roundabout = new JCheckBox();
	public final JCheckBox access = new JCheckBox();
	public final JCheckBox intersection = new JCheckBox();
	public final JCheckBox direction = new JCheckBox();

	private static MapillaryFilterChooseSigns INSTANCE;

	public MapillaryFilterChooseSigns() {
		maxspeed.setSelected(true);
		stop.setSelected(true);
		giveWay.setSelected(true);
		roundabout.setSelected(true);
		access.setSelected(true);
		intersection.setSelected(true);
		direction.setSelected(true);

		// Max speed sign
		JPanel maxspeedPanel = new JPanel();
		JLabel maxspeedLabel = new JLabel(tr("Speed limit"));
		maxspeedLabel.setIcon(new ImageProvider("signs/speed.png").get());
		maxspeedPanel.add(maxspeedLabel);
		maxspeedPanel.add(maxspeed);
		this.add(maxspeedPanel);

		// Stop sign
		JPanel stopPanel = new JPanel();
		JLabel stopLabel = new JLabel(tr("Stop"));
		stopLabel.setIcon(new ImageProvider("signs/stop.png").get());
		stopPanel.add(stopLabel);
		stopPanel.add(stop);
		this.add(stopPanel);

		// Give way sign
		JPanel giveWayPanel = new JPanel();
		JLabel giveWayLabel = new JLabel(tr("Give way"));
		giveWayLabel.setIcon(new ImageProvider("signs/right_of_way.png").get());
		giveWayPanel.add(giveWayLabel);
		giveWayPanel.add(giveWay);
		this.add(giveWayPanel);

		// Roundabout sign
		JPanel roundaboutPanel = new JPanel();
		JLabel roundaboutLabel = new JLabel(tr("Give way"));
		roundaboutLabel.setIcon(new ImageProvider("signs/roundabout_right.png")
				.get());
		roundaboutPanel.add(roundaboutLabel);
		roundaboutPanel.add(roundabout);
		this.add(roundaboutPanel);

		// No entry sign
		JPanel noEntryPanel = new JPanel();
		JLabel noEntryLabel = new JLabel(tr("No entry"));
		noEntryLabel.setIcon(new ImageProvider("signs/no_entry.png").get());
		noEntryPanel.add(noEntryLabel);
		noEntryPanel.add(access);
		this.add(noEntryPanel);

		// Danger intersection
		JPanel intersectionPanel = new JPanel();
		JLabel intersectionLabel = new JLabel(tr("Intersection danger"));
		intersectionLabel.setIcon(new ImageProvider(
				"signs/intersection_danger.png").get());
		intersectionPanel.add(intersectionLabel);
		intersectionPanel.add(intersection);
		this.add(intersectionPanel);

		// Mandatory direction
		JPanel directionPanel = new JPanel();
		JLabel directionLabel = new JLabel(tr("Mandatory direction (any)"));
		directionLabel.setIcon(new ImageProvider("signs/only_straight_on.png")
				.get());
		directionPanel.add(directionLabel);
		directionPanel.add(direction);
		this.add(directionPanel);
	}

	public static MapillaryFilterChooseSigns getInstance() {
		if (INSTANCE == null)
			INSTANCE = new MapillaryFilterChooseSigns();
		return INSTANCE;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}