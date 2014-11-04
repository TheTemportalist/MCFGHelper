package com.temportalist.mcfgHelper

import java.awt.EventQueue
import javax.swing.UIManager

/**
 *
 *
 * @author TheTemportalist
 */
object Start {

	def main(args: Array[String]): Unit = {
		EventQueue.invokeLater(new Runnable() {
			override def run(): Unit = {
				try {
					UIManager.setLookAndFeel(
						UIManager.getSystemLookAndFeelClassName())
				} catch {
					case e: Exception =>
						e.printStackTrace()
				}

				MCFGHelper.init()

			}
		})
	}

}
