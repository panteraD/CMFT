package com.chernenkov.status.client.sportsmensnetwork.ui;

import com.vaadin.polymer.paper.widget.PaperToast;

/**
 * Created by Andrey on 05.04.2016.
 */
public class ToastShower {
    PaperToast toast;

    public ToastShower(PaperToast toast) {
        this.toast = toast;
    }

    public void showToast(String message) {
        toast.setText(message);
        toast.show();
    }

}
