package com.chernenkov.status.client;

import com.chernenkov.status.client.sportsmensnetwork.ui.MainPanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Created by Andrey on 25.02.2016.
 */
public class EntryPointClass implements EntryPoint {

    private MainPanel mainPanel;

    public void onModuleLoad() {
        startApp();
    }

    private void startApp() {
        mainPanel = new MainPanel();
        RootPanel.get("form").add(mainPanel);

    }

}
