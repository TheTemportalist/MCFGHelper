package com.temportalist.mcfgHelper

import java.awt.Component
import java.util
import javax.swing.{JTextField, ListSelectionModel}

import com.temportalist.mcfgHelper.core.{GuiButton, ListEdittor}

/**
 *
 *
 * @author TheTemportalist
 */
class EditGroup(private var groupSelected: String) extends GenericGui with ISendBack {

	this.add(MCFGHelper.newLabel(5, 5, "Name:"))
	this.add(MCFGHelper.newLabel(
		5, 25, "Modules:"
	))
	this.add(MCFGHelper.newLabel(
		MCFGHelper.centerX + 35, 25, "Active:"
	))

	val nameField: JTextField = new JTextField(HelperData.getGroup(this.groupSelected).getName())
	val nameFieldX: Int = 45
	this.nameField.setBounds(nameFieldX, 5, MCFGHelper.width - nameFieldX - 5, 20)
	this.add(this.nameField)

	val moduleList: ListEdittor[String] = new ListEdittor[String](
		5, 43, 210, 210
	) {
		override def updateData(): Unit = {
			this.setData(getNonSelectedModules())
			super.updateData()
		}
	}
	this.moduleList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION)
	this.add(this.moduleList)
	this.moduleList.updateData()

	val activeList: ListEdittor[String] = new ListEdittor[String](
		MCFGHelper.centerX + 35, 43, 210, 210
	) {
		override def updateData(): Unit = {
			this.setData(HelperData.getGroup(groupSelected).getModules())
			super.updateData()
		}
	}
	this.activeList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION)
	this.add(this.activeList)
	this.activeList.updateData()

	val addModule: GuiButton = new GuiButton(
		MCFGHelper.centerX - 30,
		MCFGHelper.centerY - 50,
		60, 45, "", this
	)
	this.addModule.setIcon(MCFGHelper.getImageIcon("arrow_add.png"))
	this.addModule.setName("add")
	this.add(this.addModule)
	val removeModule: GuiButton = new GuiButton(
		MCFGHelper.centerX - 30,
		MCFGHelper.centerY,
		60, 45, "", this
	)
	this.removeModule.setIcon(MCFGHelper.getImageIcon("arrow_remove.png"))
	this.removeModule.setName("remove")
	this.add(this.removeModule)

	def getNonSelectedModules(): Array[String] = {
		val modules: Array[String] = HelperData.getModules()
		val activeModules: util.ArrayList[String] = MCFGHelper.toList(
			HelperData.getGroup(this.groupSelected).getModules()
		)
		val nonSelected: util.ArrayList[String] = new util.ArrayList[String]()
		for (i <- 0 until modules.length) {
			if (!activeModules.contains(modules(i)))
				nonSelected.add(modules(i))
		}

		MCFGHelper.toArray(nonSelected)
	}

	override def buttonPress(button: GuiButton): Unit = {
		button.getName match {
			case "add" =>
				HelperData.getGroup(this.groupSelected).addModule(this.moduleList.getCurrent())
			case "remove" =>
				HelperData.getGroup(this.groupSelected).removeModule(this.activeList.getCurrent())
			case _ =>
				this.press(button)
		}
		this.moduleList.updateData()
		this.activeList.updateData()
		MCFGHelper.updateGUI()
	}

	override protected def getOwner(): GenericGui = {
		new MainGui()
	}

	override protected def save(): Unit = {
		HelperData.getGroup(this.groupSelected).setModules(this.activeList.getAllValues())
		HelperData.renameGroup(this.groupSelected, this.nameField.getText())
		this.groupSelected = this.nameField.getText
	}

	override protected def addPart(comp: Component): Unit = {
		this.add(comp)
	}

	override protected def getThis(): GenericGui = {
		this
	}

}
