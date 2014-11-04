package com.temportalist.mcfgHelper

import java.io.File

/**
 *
 *
 * @author TheTemportalist
 */
class Module(private var name: String) {

	override def toString: String = {
		this.name
	}

	private var directoryPath: String = ""
	private var buildFilePath: String = ""
	private var useGradle: Boolean = false
	private var gradlewPath: String = ""

	def getName(): String = {
		this.name
	}

	def setName(value: String): Unit = {
		this.name = value
	}

	def getDirectoryPath(): String = {
		this.directoryPath
	}

	def setDirectoryPath(value: String): Unit = {
		this.directoryPath = value
		if (this.getBuildFilePath().equals(""))
			this.detectBuildFile()
		if (this.getGradlewPath().equals(""))
			this.detectGradlew()
	}

	def getBuildFilePath(): String = {
		this.buildFilePath
	}

	def setBuildFilePath(value: String): Unit = {
		this.buildFilePath = value
	}

	def detectBuildFile(): Unit = {
		if (new File(new File(this.getDirectoryPath()), "build.gradle").exists()) {
			this.setBuildFilePath(this.getDirectoryPath() + "/build.gradle")
		}
	}

	def isUsingGradle(): Boolean = {
		this.useGradle
	}

	def setUsingGradle(value: Boolean): Unit = {
		this.useGradle = value
	}

	def getGradlewPath(): String = {
		this.gradlewPath
	}

	def setGradlewPath(value: String): Unit = {
		this.gradlewPath = value
	}

	def detectGradlew(): Unit = {
		if (new File(new File(this.getDirectoryPath()), "gradle").exists()) {
			this.setGradlewPath(this.getDirectoryPath() + "/gradle")
		}
	}

	def isValidPath(path: String): Boolean = {
		new File(path).exists()
	}

}
