package com.temportalist.mcfgHelper

import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.JPanel

import com.temportalist.mcfgHelper.core.GuiButton

/**
 *
 *
 * @author TheTemportalist
 */
abstract class GenericGui() extends JPanel(null) with ActionListener {

	println ("Adding components")

	override def actionPerformed(e: ActionEvent): Unit = {
		e.getSource match {
			case button: GuiButton =>
				this.buttonPress(button)
			case _ =>
		}
	}

	def buttonPress(button: GuiButton): Unit

	def open(gui: GenericGui): Unit = {
		MCFGHelper.setPanel(gui)
	}

}
