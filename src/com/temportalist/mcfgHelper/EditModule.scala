package com.temportalist.mcfgHelper

import java.awt.Component
import java.io.File
import javax.swing.{JCheckBox, JFileChooser, JTextField}

import com.temportalist.mcfgHelper.core.GuiButton

/**
 *
 *
 * @author TheTemportalist
 */
class EditModule(private val modules: java.util.List[String]) extends GenericGui() with ISendBack {

	var nameField: JTextField = null
	var directoryField: JTextField = null
	var useGradlewBox: JCheckBox = null
	var gradlewField: JTextField = null

	if (modules.size() == 1) {

		this.add(MCFGHelper.newLabel(5, 5, "Name:"))
		val nameFieldX: Int = 45
		this.nameField = new JTextField(
			HelperData.getModule(this.modules.get(0)).getName()
		)
		this.nameField.setBounds(nameFieldX, 5, MCFGHelper.width - nameFieldX - 5, 20)
		this.add(this.nameField)

		this.add(MCFGHelper.newLabel(5, 25, "Main Directory:"))
		this.directoryField = new JTextField(
			HelperData.getModule(this.modules.get(0)).getDirectoryPath()
		)
		val dirFieldX: Int = 15
		this.directoryField.setBounds(dirFieldX, 45, MCFGHelper.width - dirFieldX - 25, 20)
		this.add(this.directoryField)
		val dirSelect: GuiButton = new GuiButton(MCFGHelper.width - 25, 45, 20, 20, "...", this)
		dirSelect.setName("dirSelect")
		this.add(dirSelect)

		this.add(MCFGHelper.newLabel(5, 65, "Gradle(w):"))
		this.useGradlewBox = new JCheckBox("Use 'gradlew'", false)
		this.useGradlewBox.setBounds(70, 65, 200, 20)
		this.add(this.useGradlewBox)

		this.add(MCFGHelper.newLabel(5, 105, "Build File:"))

	}
	else {

	}

	override protected def getOwner(): GenericGui = {
		new MainGui()
	}

	override protected def save(): Unit = {

	}

	override protected def addPart(comp: Component): Unit = {
		this.add(comp)
	}

	override protected def getThis(): GenericGui = {
		this
	}

	override def buttonPress(button: GuiButton): Unit = {
		button.getName match {
			case "dirSelect" =>
				this.directoryField.setText(this.makeChooser(
					"Choose the module directory", JFileChooser.DIRECTORIES_ONLY,
					this.directoryField.getText
				))
			case _ =>
				this.press(button)
		}
	}

	def makeChooser(title: String, dirFileIndex: Int, default: String): String = {
		val chooser: JFileChooser = new JFileChooser()
		chooser.setCurrentDirectory(new File("."))
		chooser.setDialogTitle(title)
		chooser.setFileSelectionMode(dirFileIndex)
		chooser.setAcceptAllFileFilterUsed(false)
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			chooser.getSelectedFile.getAbsolutePath
		}
		else {
			default
		}
	}

}
