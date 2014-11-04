package com.temportalist.mcfgHelper

import java.awt.Font
import java.util
import javax.swing._

import com.temportalist.mcfgHelper.core.{GuiButton, ListEdittor}

/**
 *
 *
 * @author TheTemportalist
 */
class MainGui() extends GenericGui() {

	private final val screenW: Int = MCFGHelper.width
	private final val screenH: Int = MCFGHelper.height

	val logoLabel: JLabel = MCFGHelper.getLabelImage(0, 5, "big_logo.png")
	val logoW: Int = logoLabel.getWidth
	val logoH: Int = logoLabel.getHeight
	this.add(logoLabel)

	private final val titleLabel1: JLabel = new JLabel("Minecraft Forge")
	private final val titleLabel2: JLabel = new JLabel("Gradle Helper")
	this.titleLabel1.setBounds(
		logoW + 15, 5, (screenW - 15) - (logoW + 10), logoH / 2
	)
	this.titleLabel2.setBounds(
		logoW + 45, 5 + (logoH / 2), (screenW - 15) - (logoW + 10), logoH / 2
	)
	this.titleLabel1.setFont(new Font(this.titleLabel1.getFont.getName, Font.PLAIN, 30))
	this.titleLabel2.setFont(new Font(this.titleLabel2.getFont.getName, Font.PLAIN, 30))
	this.add(this.titleLabel1)
	this.add(this.titleLabel2)

	private final val columnW: Int = (this.screenW - 15) / 2
	private final val columnH: Int = this.screenH - 50 - (30 + logoH)
	private final val groupsLabel: JLabel = new JLabel("Groups")
	this.groupsLabel.setBounds(5, 5 + logoH + 5, columnW, 20)
	this.groupsLabel.setHorizontalAlignment(SwingConstants.CENTER)
	this.add(this.groupsLabel)
	private final val groupsList: ListEdittor[String] = new ListEdittor[String](
		5, 30 + logoH, columnW, columnH
	) {
		override def updateData(): Unit = {
			this.setData(HelperData.getGroups())
			super.updateData()
		}

		override def cellClicked(index: Int): Unit = {
			if (this.getCurrent() != null) {
				HelperData.getGroup(this.getCurrent()).update()

				val selectionList: util.ArrayList[Int] = new util.ArrayList[Int]()
				for (module <- HelperData.getGroup(this.getCurrent()).getModules()) {
					if (HelperData.containsModule(module))
						selectionList.add(HelperData.getModulesAsList().indexOf(module))
				}
				val selections: Array[Int] = new Array[Int](selectionList.size())
				for (i <- 0 until selectionList.size()) {
					selections(i) = selectionList.get(i)
				}
				moduleList.setSelections(selections)
			}
			updateButtons()
			MCFGHelper.updateGUI()
		}
	}
	this.groupsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION)
	this.groupsList.updateData()
	this.add(this.groupsList)

	private final val modulesLabel: JLabel = new JLabel("Modules")
	this.modulesLabel.setBounds(10 + columnW, 5 + logoH + 5, columnW, 20)
	this.modulesLabel.setHorizontalAlignment(SwingConstants.CENTER)
	this.add(this.modulesLabel)
	private final val moduleList: ListEdittor[String] = new ListEdittor[String](
		10 + columnW, 30 + logoH, columnW, columnH
	) {
		override def updateData(): Unit = {
			this.setData(HelperData.getModules())
			super.updateData()
		}

		override def cellClicked(index: Int): Unit = {
			groupsList.clearSelections()
			updateButtons()
			MCFGHelper.updateGUI()
		}
	}
	this.moduleList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION)
	this.moduleList.updateData()
	this.add(this.moduleList)

	private final val buttonsNames: Array[String] = Array[String](
		"+ Group", "Edit Group", "- Group", "+ Module", "Edit Module(s)", "- Module"
	)
	private final val buttons: util.HashMap[String, GuiButton] =
		new util.HashMap[String, GuiButton]()
	private final val buttonW: Int =
		(this.screenW - ((this.buttonsNames.length + 1) * 5)) / this.buttonsNames.length
	var buttonfont: Font = null
	for (i <- 0 until buttonsNames.length) {
		this.buttons.put(this.buttonsNames(i), new GuiButton(
			5 + (buttonW * i) + (5 * i), screenH - 45, buttonW, 20, this.buttonsNames(i), this
		))
		if (buttonfont == null) buttonfont = new Font(
			this.buttons.get(this.buttonsNames(i)).getFont.getFontName, Font.PLAIN, 10
		)
		this.buttons.get(this.buttonsNames(i)).setFont(buttonfont)
		this.add(this.buttons.get(this.buttonsNames(i)))
	}

	this.load()

	private def load(): Unit = {
		this.updateButtons()
	}

	def updateButtons(): Unit = {
		val hasModulesSelected: Boolean = !this.moduleList.getAllSelected().isEmpty
		this.buttons.get("+ Group").setEnabled(hasModulesSelected)
		this.buttons.get("Edit Group").setEnabled(this.groupsList.getCurrent() != null)
		this.buttons.get("- Group").setEnabled(this.groupsList.getCurrent() != null)
		this.buttons.get("Edit Module(s)").setEnabled(hasModulesSelected)
		this.buttons.get("Edit Module(s)").setEnabled(hasModulesSelected)
		this.buttons.get("- Module").setEnabled(hasModulesSelected)
	}

	override def buttonPress(button: GuiButton): Unit = {
		button.getText match {
			case "+ Group" =>
				val modules: Array[String] = new Array[String](
					this.moduleList.getAllSelected().size()
				)
				for (i <- 0 until this.moduleList.getAllSelected().size()) {
					modules(i) = this.moduleList.getAllSelected().get(i)
				}

				var groupID: Int = HelperData.getGroups().length
				while (HelperData.containsGroup("Group " + groupID)) {
					groupID += 1
				}
				HelperData.addGroup(new Group("Group " + groupID).setModules(modules))

				this.groupsList.updateData()
			case "+ Module" =>

				var moduleID: Int = HelperData.getModules().length
				while (HelperData.containsModule("Module " + moduleID)) {
					moduleID += 1
				}
				HelperData.addModule(new Module("Module " + moduleID))

				this.moduleList.updateData()
			case "Edit Group" =>
				this.open(new EditGroup(this.groupsList.getCurrent()))
			case "Edit Module(s)" =>
				this.open(new EditModule(this.moduleList.getAllSelected()))
			case "- Group" =>
				HelperData.removeGroups(this.groupsList.getAllSelected())
				this.groupsList.updateData()
			case "- Module" =>
				HelperData.removeModules(this.moduleList.getAllSelected())
				this.moduleList.updateData()
			case _ =>
		}
		MCFGHelper.updateGUI()
	}

}
