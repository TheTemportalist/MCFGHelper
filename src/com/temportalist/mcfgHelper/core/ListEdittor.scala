package com.temportalist.mcfgHelper.core

import java.awt.Point
import java.awt.event.{MouseEvent, MouseListener}
import java.util
import java.util.{Collections, Comparator}
import javax.swing._
import javax.swing.event.{ListSelectionEvent, ListSelectionListener}

/**
 *
 *
 * @author TheTemportalist
 */
abstract class ListEdittor[T](x: Int, y: Int, width: Int, height: Int)
		extends JScrollPane with ListSelectionListener {

	private val contentList: JList[T] = new JList[T]() {

		override def locationToIndex(location: Point): Int = {
			val index: Int = super.locationToIndex(location)
			if (index != -1 && !this.getCellBounds(index, index).contains(location)) {
				-1
			}
			else {
				cellClicked(index)
				index
			}
		}

	}
	this.contentList.addMouseListener(new MouseListener {
		override def mouseExited(e: MouseEvent): Unit = {}

		override def mouseClicked(e: MouseEvent): Unit = {
			val list: JList[T] = e.getSource.asInstanceOf[JList[T]]
			val index: Int = list.locationToIndex(e.getPoint)
			if (index == -1) {
				list.clearSelection()
			}
			cellClicked(index)
		}

		override def mouseEntered(e: MouseEvent): Unit = {}

		override def mousePressed(e: MouseEvent): Unit = {}

		override def mouseReleased(e: MouseEvent): Unit = {}

	})
	this.contentList.addListSelectionListener(this)
	this.setBounds(x, y, width, height)
	this.setViewportView(this.contentList)

	def updateData(): Unit = {
		if (this.contentList.getModel.getSize > 1) {
			val content: java.util.List[T] = new util.ArrayList[T]()
			for (i <- 0 until this.contentList.getModel.getSize) {
				content.add(this.contentList.getModel.getElementAt(i))
			}
			Collections.sort(content, new Comparator[T] {
				override def compare(o1: T, o2: T): Int = {
					o1.toString.compareTo(o2.toString)
				}
			})
			this.setData2(content)
		}
	}

	def setData(data: Array[T]): Unit = {
		this.contentList.setModel(new AbstractListModel[T] {
			override def getElementAt(index: Int): T = {
				data(index)
			}
			override def getSize: Int = {
				data.length
			}
		})
	}

	private def setData2(data: util.List[T]): Unit = {
		this.contentList.setModel(new AbstractListModel[T] {
			override def getElementAt(index: Int): T = {
				data.get(index)
			}
			override def getSize: Int = {
				data.size()
			}
		})
	}

	def getCurrent(): T = {
		if (this.contentList.getSelectedIndex >= 0) {
			this.contentList.getSelectedValue
		}
		else {
			null.asInstanceOf[T]
		}
	}

	def getAllSelected(): java.util.List[T] = {
		this.contentList.getSelectedValuesList
	}

	def clearSelections(): Unit = {
		this.contentList.clearSelection()
	}

	def setSelectionMode(mode: Int): Unit = {
		this.contentList.setSelectionMode(mode)
	}

	def setSelections(indices: Array[Int]): Unit = {
		this.contentList.setSelectedIndices(indices)
	}

	def getList(): JList[T] = {
		this.contentList
	}

	def cellClicked(index: Int): Unit = {}

	override def valueChanged(e: ListSelectionEvent): Unit = {}

	def getAllValues()(implicit manifest : Manifest[T]): Array[T] = {
		implicit val model: ListModel[T] = this.contentList.getModel
		val array: Array[T] = new Array[T](model.getSize)
		for (i <- 0 until model.getSize) array(i) = model.getElementAt(i)
		array
	}

}
