package com.temportalist.mcfgHelper

import java.awt.Component

import com.temportalist.mcfgHelper.core.GuiButton

/**
 *
 *
 * @author TheTemportalist
 */
trait ISendBack {

	val saveRight: Int = MCFGHelper.width - 55
	this.addPart(new GuiButton(
		saveRight - 55, MCFGHelper.height - 45, 50, 20, "Cancel", this.getThis())
	)
	this.addPart(new GuiButton(
		saveRight, MCFGHelper.height - 45, 50, 20, "Save", this.getThis())
	)

	protected def press(button: GuiButton): Unit = {
		button.getText match {
			case "Save" =>
				this.save()
				this.getThis().open(this.getOwner())
			case "Cancel" =>
				this.getThis().open(this.getOwner())
			case _ =>
		}
	}

	protected def getOwner(): GenericGui

	protected def save(): Unit

	protected def addPart(comp: Component): Unit

	protected def getThis(): GenericGui

}
