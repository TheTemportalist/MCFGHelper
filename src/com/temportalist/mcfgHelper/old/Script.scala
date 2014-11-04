package com.temportalist.mcfgHelper.old

import com.temportalist.mcfgHelper.HelperData

/**
 *
 * @param cmd The command to be run (with prefix specified in Script.prefix)
 * @param pars The parameters to run with the command and prefix command
 * @param priority The priority of the script. The lower the priority, the sooner it will run. If two have the same priority, the one that comes first in alphabet will run first
 * @param category The category of the script. If two scripts have the same category, the running of any script will fail. Usually used to offset priority issues
 *                 // todo when a script is selected, hide all others with same category
 * @author TheTemportalist
 */
class Script(private val cmd: String, private val pars: Array[String], val priority: Int,
		val category: Int) {

	def run(extraPars: Array[String]): Unit = {
		val cmdBuffer: StringBuffer = new StringBuffer(Script.prefix + " " + cmd)
		for (par <- pars) cmdBuffer.append(" " + par)
		for (par <- extraPars) cmdBuffer.append(" " + par)
		val command: String = cmdBuffer.toString
		// todo run in terminal
	}

	override def toString: String = {
		this.cmd
	}

}

object Script {

	final val prefix: String = "bash gradlew"
	final val clean: Script = new Script("clean", Array[String]("--refresh-dependencies"), 1, 1)
	final val dev: Script = new Script("setupDevWorkspace", Array[String](), 2, 2)
	final val decomp: Script = new Script("setupDecompWorkspace", Array[String](), 2, 2)
	final val idea: Script = new Script("idea", Array[String]("--refresh-dependencies"), 3, 3)
	final val eclipse: Script = new Script("eclipse", Array[String]("--refresh-dependencies"), 3, 3)
	final val build: Script = new Script("build", Array[String](), 4, 4)
	final val curse: Script = new Script("curse", Array[String](), 4, 4)

	def init(): Unit = {
		println("Adding Scripts")
		HelperData.addScript(Script.clean)
		HelperData.addScript(Script.dev)
		HelperData.addScript(Script.decomp)
		HelperData.addScript(Script.idea)
		HelperData.addScript(Script.eclipse)
		HelperData.addScript(Script.build)
		HelperData.addScript(Script.curse)
	}

}
