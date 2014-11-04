package com.temportalist.mcfgHelper.old

import com.temportalist.mcfgHelper.core.{GuiButton, ListEdittor}
import com.temportalist.mcfgHelper.GenericGui

/**
 *
 *
 * @author TheTemportalist
 */
class ScriptGui() extends GenericGui() {

	println ("Creating the script list")
	var scriptList: ListEdittor[Script] = null
	/*
	new ListEdittor[Script](5, 10, 200, 250) {
		override def updateData(): Unit = {
			this.setData(HelperData.getScripts())
		}
	}
	this.setListData(HelperData.getScripts())
	this.add(this.scriptList)
	*/

	this.add(new GuiButton(210, 40, 100, 40, "Run", this))
	this.add(new GuiButton(210, 80, 100, 40, "Debug", this))

	def setListData(data: Array[Script]): Unit = {
		this.scriptList.setData(data)
	}
	
	override def buttonPress(button: GuiButton): Unit = {

		if (this.scriptList.getCurrent() != null) {
			button.getText match {
				case "Run" =>
					this.runScripts(Array[String]())
				case "Debug" =>
					println ("Running script " + this.scriptList.getCurrent().toString + " in debug mode")
					// todo
					//this.scriptList.getCurrent().run({"--debug"})
				case _ =>
			}
		}

	}

	def runScripts(extraPars: Array[String]): Unit = {
		/*
		for (script <- this.scriptList.getAllSelected()) {
			script.run(extraPars)
		}
		*/
	}

}
