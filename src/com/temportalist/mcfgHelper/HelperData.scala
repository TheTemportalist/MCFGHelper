package com.temportalist.mcfgHelper

import java.util

import com.temportalist.mcfgHelper.old.Script

/**
 *
 *
 * @author TheTemportalist
 */
object HelperData {

	private val modules: util.LinkedHashMap[String, Module] = new util.LinkedHashMap[String, Module]()

	def addModule(module: Module): Unit = {
		this.modules.put(module.getName(), module)
	}

	def getModules(): Array[String] = {
		this.modules.keySet().toArray(new Array[String](0))
	}

	def getModule(name: String): Module = {
		this.modules.get(name)
	}

	def getModulesAsList(): util.ArrayList[String] = {
		val list: util.ArrayList[String] = new util.ArrayList[String]()
		val iterator: util.Iterator[String] = this.modules.keySet().iterator()
		while (iterator.hasNext) {
			list.add(iterator.next())
		}
		list
	}

	def removeModules(dataToRemove: util.List[String]): Unit = {
		val currentModules: util.ArrayList[Module] = new util.ArrayList[Module](this.modules.values())
		val selectedModules: util.ArrayList[String] = new util.ArrayList[String](dataToRemove)
		this.modules.clear()
		for (i <- 0 until currentModules.size()) {
			if (!selectedModules.contains(currentModules.get(i).getName())) {
				this.modules.put(currentModules.get(i).getName(), currentModules.get(i))
			}
		}
	}

	def renameModule(prev: String, newName: String): Unit = {
		val module: Module = this.modules.remove(prev)
		module.setName(newName)
		this.addModule(module)
	}

	def containsModule(moduleName: String): Boolean = {
		this.modules.containsKey(moduleName)
	}

	private val groups: util.LinkedHashMap[String, Group] = new util.LinkedHashMap[String, Group]()

	def addGroup(group: Group): Unit = {
		this.groups.put(group.getName(), group)
	}

	def getGroups(): Array[String] = {
		this.groups.keySet().toArray(new Array[String](0))
	}

	def getGroup(name: String): Group = {
		this.groups.get(name)
	}

	def removeGroups(dataToRemove: util.List[String]): Unit = {
		val currentGroups: util.ArrayList[Group] = new util.ArrayList[Group](this.groups.values())
		val selectedGroups: util.ArrayList[String] = new util.ArrayList[String](dataToRemove)
		this.groups.clear()
		for (i <- 0 until currentGroups.size()) {
			if (!selectedGroups.contains(currentGroups.get(i).getName())) {
				this.groups.put(currentGroups.get(i).getName(), currentGroups.get(i))
			}
		}
	}

	def renameGroup(prev: String, newName: String): Unit = {
		val group: Group = this.groups.remove(prev)
		group.setName(newName)
		this.addGroup(group)
	}

	def containsGroup(name: String): Boolean = {
		this.groups.containsKey(name)
	}



	private val activeModules: util.ArrayList[String] = new util.ArrayList[String]()

	def selectModules(modules: util.List[String]): Unit = {
		this.clearActiveModules()
		this.selectModules(modules)
	}

	def clearActiveModules(): Unit = {
		this.activeModules.clear()
	}

	def getActiveModules(): util.ArrayList[String] = {
		this.activeModules
	}





	private val scripts: util.ArrayList[Script] = new util.ArrayList[Script]()
	private val scriptFavorites: util.ArrayList[Script] = new util.ArrayList[Script]()

	def addScript(script: Script): Unit = {
		this.scripts.add(script)
		this.sortScripts()
	}

	def getScripts(): Array[Script] = {
		this.scripts.toArray(new Array[Script](0))
	}

	def markFavorite(index: Int): Unit = {
		if (!this.scriptFavorites.contains(this.scripts.get(index))) {
			this.scriptFavorites.add(this.scripts.get(index))
		}
	}

	def unmarkFavorite(index: Int): Unit = {
		this.scriptFavorites.remove(this.scripts.get(index))
	}

	def sortScripts(): Unit = {
		val originalScripts: util.ArrayList[Script] = new util.ArrayList[Script](this.scripts)
		this.scripts.clear()

		// todo sort favorites alphally
		// todo sort originals alphally

		for (i <- 0 until this.scriptFavorites.size()) {
			this.scripts.add(this.scriptFavorites.get(i))
			originalScripts.remove(this.scriptFavorites.get(i))
		}
		for (i <- 0 until originalScripts.size()) {
			this.scripts.add(originalScripts.get(i))
		}

	}

}
