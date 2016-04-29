package com.chernenkov.register.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.polymer.Polymer;
import com.vaadin.polymer.elemental.Function;

/**
 * Created by Andrey on 25.02.2016.
 */
public class EntryPointClass implements EntryPoint {


    @Override
    public void onModuleLoad() {
        startApp();
    }

    private void startApp(){
        RootPanel.get("polymer").add(new RegisterWidget());
    }
}
