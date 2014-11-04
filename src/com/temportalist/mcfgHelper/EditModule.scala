package com.temportalist.mcfgHelper

import java.awt.Component
import java.awt.event.{ItemEvent, ItemListener}
import java.io.File
import javax.swing.{JCheckBox, JFileChooser, JTextField}

import com.temportalist.mcfgHelper.core.GuiButton

/**
 *
 *
 * @author TheTemportalist
 */
class EditModule(private val modules: java.util.List[String])
		extends GenericGui() with ISendBack with ItemListener {

	var nameField: JTextField = null
	var directoryField: JTextField = null
	var useGradlewBox: JCheckBox = null
	var gradlewField: JTextField = null
	var gradlewSelect: GuiButton = null
	var buildFileField: JTextField = null

	if (modules.size() == 1) {
		val module: Module = HelperData.getModule(this.modules.get(0))

		this.add(MCFGHelper.newLabel(5, 5, "Name:"))
		val nameFieldX: Int = 45
		this.nameField = new JTextField(module.getName())
		this.nameField.setBounds(nameFieldX, 5, MCFGHelper.width - nameFieldX - 5, 20)
		this.add(this.nameField)

		this.add(MCFGHelper.newLabel(5, 25, "Main Directory:"))
		this.directoryField = new JTextField(module.getDirectoryPath())
		val dirFieldX: Int = 15
		val dirFieldW: Int = MCFGHelper.width - dirFieldX - 25
		this.directoryField.setBounds(dirFieldX, 45, dirFieldW, 20)
		this.add(this.directoryField)
		val dirSelect: GuiButton = new GuiButton(MCFGHelper.width - 25, 45, 20, 20, "...", this)
		dirSelect.setName("dirSelect")
		this.add(dirSelect)

		this.add(MCFGHelper.newLabel(5, 65, "Gradle(w):"))
		this.useGradlewBox = new JCheckBox("Use 'gradlew'", true)
		this.useGradlewBox.setBounds(70, 65, 200, 20)
		this.useGradlewBox.setName("useGradlew")
		this.useGradlewBox.addItemListener(this)
		this.add(this.useGradlewBox)
		this.gradlewField = new JTextField(module.getBuildFilePath())
		this.gradlewField.setBounds(dirFieldX, 85, dirFieldW, 20)
		this.add(this.gradlewField)
		this.gradlewSelect = new GuiButton(MCFGHelper.width - 25, 85, 20, 20, "...", this)
		this.gradlewSelect.setName("gradlewSelect")
		this.add(this.gradlewSelect)
		this.updateGradlew()

		this.add(MCFGHelper.newLabel(5, 105, "Build File:"))
		this.buildFileField = new JTextField(module.getBuildFilePath())
		this.buildFileField.setBounds(dirFieldX, 125, dirFieldW, 20)
		this.add(this.buildFileField)
		val buildSelect: GuiButton = new GuiButton(MCFGHelper.width - 25, 125, 20, 20, "...", this)
		buildSelect.setName("buildSelect")
		this.add(buildSelect)

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
					this.directoryField.getText, getParent = false
				))
				this.directoryField.updateUI()
			case "gradlewSelect" =>
				this.gradlewField.setText(this.makeChooser(
					"Choose the gradlew directory", JFileChooser.DIRECTORIES_ONLY,
					this.gradlewField.getText, getParent = true
				))
				this.gradlewField.updateUI()
			case "buildSelect" =>
				this.buildFileField.setText(this.makeChooser(
					"Choose the build.gradle file", JFileChooser.FILES_ONLY,
					this.buildFileField.getText, getParent = false
				))
				this.buildFileField.updateUI()
			case _ =>
				this.press(button)
		}
		MCFGHelper.updateGUI()
	}

	def makeChooser(title: String, dirFileIndex: Int, default: String,
			getParent: Boolean): String = {
		val chooser: JFileChooser = new JFileChooser()
		chooser.setCurrentDirectory(new File(""))
		chooser.setDialogTitle(title)
		chooser.setFileSelectionMode(dirFileIndex)
		chooser.setAcceptAllFileFilterUsed(false)
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			if (getParent)
				chooser.getCurrentDirectory.getAbsolutePath
			else
				chooser.getSelectedFile.getAbsolutePath
		}
		else {
			default
		}
	}

	override def itemStateChanged(e: ItemEvent): Unit = {
		e.getSource match {
			case check: JCheckBox =>
				check.getName match {
					case "useGradlew" =>
						this.updateGradlew()
						// todo if not gradlew, check for gradle

						MCFGHelper.updateGUI()
					case _ =>
				}
			case _ =>
		}
	}

	def updateGradlew(): Unit = {
		val useGradlew: Boolean = this.useGradlewBox.isSelected
		this.gradlewField.setVisible(useGradlew)
		this.gradlewSelect.setVisible(useGradlew)
	}

}
