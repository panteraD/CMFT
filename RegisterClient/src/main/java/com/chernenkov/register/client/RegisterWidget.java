package com.chernenkov.register.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.vaadin.polymer.iron.widget.IronForm;
import com.vaadin.polymer.paper.widget.PaperButton;

/**
 * Created by Andrey on 25.02.2016.
 */
public class RegisterWidget extends Composite {
    @UiField
    IronForm registrationForm;

    @UiField
    PaperButton submitButton;

    interface RegisterWidgetUiBinder extends UiBinder<HTMLPanel, RegisterWidget> {
    }

    private static RegisterWidgetUiBinder registerWidgetUiBinder =
            GWT.create(RegisterWidgetUiBinder.class);

    public RegisterWidget(){

        initWidget(registerWidgetUiBinder.createAndBindUi(this));
        //see Polymer docs
        registrationForm.setContentType("application/x-www-form-urlencoded");
        registrationForm.setHeaders("Content-Type: application/x-www-form-urlencoded");
        registrationForm.setRequest("/auth/saving/add");

    }

    @UiHandler("submitButton")
    protected void setSubmitButtonClicked(ClickEvent e){
//        if(registrationForm.validate()==true){
//            Window.alert("true");
//        }else{
//            Window.alert("false");
//        }
        registrationForm.submit();

    }



}
