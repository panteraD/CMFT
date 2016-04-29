package com.chernenkov.status.client.sportsmensnetwork.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * Created by Andrey on 02.04.2016.
 */
public class Item extends Composite {
    private static ItemUiBinder ourUiBinder = GWT.create(ItemUiBinder.class);
    @UiField
    Element title;
    @UiField
    Element description;

    public Item() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public String getTitle() {
        return title.getInnerText();
    }

    public void setTitle(String text) {
        this.title.setInnerText(text);
    }

    public String getDescription() {
        return description.getInnerText();
    }

    public void setDescription(String text) {
        this.description.setInnerText(text);
    }

    interface ItemUiBinder extends UiBinder<HTMLPanel, Item> {
    }
}
