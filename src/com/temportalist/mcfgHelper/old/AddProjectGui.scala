package com.temportalist.mcfgHelper.old

import javax.swing.{JLabel, JTextField}

import com.temportalist.mcfgHelper.core.GuiButton
import com.temportalist.mcfgHelper.{GenericGui, MCFGHelper}

/**
 *
 *
 * @author TheTemportalist
 */
class AddProjectGui() extends GenericGui() {

	private val name: JLabel = new JLabel("Name:")
	name.setBounds(5, 15, 50, 15)
	this.add(name)

	private val nameField: JTextField = new JTextField()
	this.nameField.setBounds(140, 15, 200, 25)
	this.add(this.nameField)

	private val gradle: JLabel = new JLabel("Gradle(w) Directory:")
	gradle.setBounds(5, 50, 135, 15)
	this.add(gradle)

	private val gradleField: JTextField = new JTextField()
	this.gradleField.setBounds(140, 45, 200, 25)
	this.add(this.gradleField)

	private val warning: JLabel = new JLabel("")
	warning.setBounds(5, 75, 400, 15)
	this.add(this.warning)

	this.add(new GuiButton(5, 100, 190, 20, "Cancel", this))
	this.add(new GuiButton(200, 100, 190, 20, "Add Project", this))

	override def buttonPress(button: GuiButton): Unit = {
		/*
		button.getText match {
			case "Cancel" =>
				MCFGHelper.setPanel(MCFGHelper.selectProject)
			case "Add Project" =>
				if (this.nameField.getText.equals("") || this.gradleField.getText.equals("")) {
					this.warning.setText("Both the Name and Gradle Directory fields must be filled in")
				}
				else {
					HelperData.addProject(new Project(this.nameField.getText, this.gradleField.getText))
					MCFGHelper.setPanel(MCFGHelper.selectProject)
				}
			case _ =>
		}
		*/
		MCFGHelper.updateGUI()

	}

}
