package com.temportalist.mcfgHelper.core;

import javax.swing.*;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class GuiButton extends JButton {
	
	public GuiButton(int x, int y, int w, int h, String name, ActionListener listener) {
		super(name);

		this.setBounds(x, y, w, h);
		this.addActionListener(listener);
		this.setVisible(true);
	}
	
}
