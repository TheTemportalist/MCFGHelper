package com.temportalist.mcfgHelper.old

import com.temportalist.mcfgHelper._
import com.temportalist.mcfgHelper.core.GuiButton

/**
 *
 *
 * @author TheTemportalist
 */
class SelectionGui() extends GenericGui() {
	/*
	println ("Creating the project list")
	var projectList: ListEdittor[Project] = new ListEdittor[Project](145, 10, 250, 210)
	this.setProjectListData(HelperData.getProjects())
	this.add(this.projectList)

	println ("Creating buttons")
	this.add(new GuiButton(5, 85, 135, 40, "Add", this))
	this.add(new GuiButton(5, 130, 135, 40, "Edit (noop)", this))
	this.add(new GuiButton(5, 175, 135, 40, "Remove", this))
	this.add(new GuiButton(5, 230, 390, 40, "Select", this))

	def setProjectListData(data: Array[Project]): Unit = {
		this.projectList.setData(data)
	}
	*/
	override def buttonPress(button: GuiButton): Unit = {
		button.getText match {
			case "Add" =>
				println ("Opening gui for adding projects")
				this.open(new AddProjectGui())
			case "Edit" =>
				// todo
			case "Remove" =>
				println ("Removing current index in project list")
				//this.projectList.removeCurrent()
				//this.setProjectListData(HelperData.getProjects())
				MCFGHelper.updateGUI()
			case "Select" =>
				println ("Opting gui for project setup and modification")
				this.open(new ScriptGui())
			case _ =>
		}
	}


}
