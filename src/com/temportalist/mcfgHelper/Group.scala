package com.temportalist.mcfgHelper

import java.util

/**
 *
 *
 * @author TheTemportalist
 */
class Group(private var name: String) {

	override def toString: String = {
		this.name
	}

	def getName(): String = {
		this.name
	}

	def setName(name: String): Unit = {
		this.name = name
	}

	private var modules: Array[String] = null

	def setModules(modules: Array[String]): Group = {
		this.modules = modules
		println ("~~~")
		for (i <- 0 until this.modules.length)
			println (this.modules(i))
		this
	}

	def getModules(): Array[String] = {
		this.modules
	}

	def addModule(name: String): Unit = {
		val list: util.ArrayList[String] = MCFGHelper.toList(this.modules)
		list.add(name)
		this.modules = MCFGHelper.toArray(list)
	}

	def removeModule(name: String): Unit = {
		val list: util.ArrayList[String] = MCFGHelper.toList(this.modules)
		list.remove(name)
		this.modules = MCFGHelper.toArray(list)
	}

	def update(): Unit = {
		val moduleNames: util.ArrayList[String] = new util.ArrayList[String]()
		for (i <- 0 until this.modules.length) {
			if (HelperData.containsModule(this.modules(i)))
				moduleNames.add(this.modules(i))
		}
		if (moduleNames.size() <= 0) {
			//HelperData.removeGroup
		}
		else {
			this.modules = new Array[String](moduleNames.size())
			for (i <- 0 until this.modules.length) {
				this.modules(i) = moduleNames.get(i)
			}
		}
	}

}
