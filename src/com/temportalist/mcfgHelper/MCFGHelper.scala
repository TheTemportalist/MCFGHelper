package com.temportalist.mcfgHelper

import java.awt.image.BufferedImage
import java.util
import javax.imageio.ImageIO
import javax.swing._

import com.temportalist.mcfgHelper.old.Script

/**
 *
 *
 * @author TheTemportalist
 */
object MCFGHelper {

	val name: String = "Minecraft Forge Gradle Helper"
	val frame: JFrame = new JFrame(name)
	var panel: JPanel = null

	val width: Int = 500
	val height: Int = 300
	val centerX: Int = MCFGHelper.width / 2
	val centerY: Int = (MCFGHelper.height + 5) / 2

	def init(): Unit = {
		println ("Starting 'MCForgeGui Helper'")

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
		println ("Setting up gui's")
		// Removes JList selected cell border
		UIManager.put("List.focusCellHighlightBorder", BorderFactory.createEmptyBorder())
		this.panel = new MainGui()
		println ("Adding gui and opening window")
		this.setPanel(this.panel)
		frame.setSize(width, height)
		frame.setResizable(false)
		frame.setLocationRelativeTo(null)
		frame.setVisible(true)

		Script.init()

	}

	def setPanel(panel: JPanel): Unit = {
		this.frame.setContentPane(panel)
		this.updateGUI()
	}

	def updateGUI(): Unit = {
		this.frame.getRootPane.updateUI()
	}

	def getImageBuffered(path: String): BufferedImage = {
		var image: BufferedImage = null
		try {
			image = ImageIO.read(MCFGHelper.getClass.getResourceAsStream(path))
		}
		catch {
			case e: Exception =>
				e.printStackTrace()
		}
		image
	}

	def getImageIcon(path: String): ImageIcon = {
		new ImageIcon(this.getImageBuffered(path))
	}

	def getLabelImage(x: Int, y: Int, path: String): JLabel = {
		val icon: ImageIcon = this.getImageIcon(path)
		val logoLabel: JLabel = new JLabel(icon)
		logoLabel.setBounds(x, y, icon.getIconWidth, icon.getIconHeight)
		logoLabel
	}

	def newLabel(x: Int, y: Int, text: String): JLabel = {
		val nameLabel: JLabel = new JLabel(text)
		nameLabel.setBounds(x, y, nameLabel.getFontMetrics(nameLabel.getFont).stringWidth(text), 20)
		nameLabel
	}

	def toList[T](array: Array[T]): util.ArrayList[T] = {
		val list: util.ArrayList[T] = new util.ArrayList[T]()
		for (i <- 0 until array.length)
			list.add(array(i))
		list
	}

	def toArray[T](list: util.List[T])(implicit manifest : Manifest[T]): Array[T] = {
		implicit val array: Array[T] = new Array[T](list.size())
		for (i <- 0 until list.size()) {
			array(i) = list.get(i)
		}
		array
	}

}
