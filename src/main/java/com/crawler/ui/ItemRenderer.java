package com.crawler.ui;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import com.crawler.entity.Item;

public class ItemRenderer extends BasicComboBoxRenderer {
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);

		if (value != null) {
			Item item = (Item) value;
			setText(" "+item.getName());
		}
 

		return this;
	}
}
