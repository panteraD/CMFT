package com.chernenkov.status.client.sportsmensnetwork.ui;

import com.chernenkov.status.client.sportsmensnetwork.util.Common;
import com.chernenkov.status.client.sportsmensnetwork.model.Sportsman;
import com.chernenkov.status.client.sportsmensnetwork.network.SportsmanService;
import com.chernenkov.status.client.sportsmensnetwork.network.SportsmenRepository;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.vaadin.polymer.elemental.Function;
import com.vaadin.polymer.paper.widget.PaperButton;
import com.vaadin.polymer.paper.widget.PaperInput;
import com.vaadin.polymer.paper.widget.PaperToast;

import java.util.Collection;

import static com.chernenkov.status.client.sportsmensnetwork.util.Common.USER_URL_PARAM;

/**
 * Created by Andrey on 16.03.2016.
 */
public class MainPanel extends Composite {
    private static WidgetUiBinder uiBinder =
            GWT.create(WidgetUiBinder.class);
    final ToastShower toastShower;
    @UiField
    PaperInput beginInput;
    @UiField
    PaperInput endInput;
    @UiField
    PaperInput statusInput;
    @UiField
    PaperInput addressInput;
    @UiField
    PaperInput testInput;
    @UiField
    PaperButton submitButton;
    @UiField
    PaperButton logoutButton;
    @UiField
    PaperButton getUsersButton;
    @UiField
    PaperButton reloadUserButton;
    @UiField
    Label label;
    @UiField
    HTMLPanel content;
    @UiField
    PaperToast usersFoundToast;

    public MainPanel() {
        //TODO: redirect to login page if there is no param
        //TODO: get user data ONLY if user has been authenticated
        initWidget(uiBinder.createAndBindUi(this));
        label.setText("Hello, " + Window.Location.getParameter(USER_URL_PARAM));
        fetchUserData();
        toastShower = new ToastShower(usersFoundToast);
    }

    protected void fetchUserData() {
        SportsmanService.fetchSportsmanData(Window.Location.getParameter(USER_URL_PARAM), new SportsmanService.SportsmanCallback() {
            @Override
            public void onReceiveSportsman(Sportsman sportsman) {
                setSportsmanInputFields(sportsman);
            }

            @Override
            public void onError(String message) {
                toastShower.showToast("Failed to get user data, reason:" + message);
                Common.console(message);
            }
        });
    }

    @UiHandler("submitButton")
    protected void onSubmitButtonClick(ClickEvent e) {
        SportsmanService.pushSportsmanData(beginInput.getValue(), endInput.getValue(), addressInput.getValue(), statusInput.getValue(), new SportsmanService.InfoCallback() {
            @Override
            public void onDataPushed() {
                toastShower.showToast("Status has been updated successfully");
            }

            @Override
            public void onError(String message) {
                toastShower.showToast("Failed to sent data, reason:" + message);
                Common.console(message);
            }
        });
    }

    @UiHandler("logoutButton")
    protected void onLogoutButtonClick(ClickEvent e) {
        Window.Location.assign(Common.LOGOUT_PATH);
    }

    @UiHandler("getUsersButton")
    protected void onGetUsersButtonClick(ClickEvent e) {
        SportsmenRepository.findUsersReadyForSportActivityWithYou(new SportsmenRepository.UsersCallback() {
            @Override
            public void onReceiveUsers(Collection<Sportsman> sportsmen) {
                setListBoxWithItems(sportsmen);
            }

            @Override
            public void onError(String message) {
                toastShower.showToast("Failed to get Sportsmen, reason:" + message);
                Common.console(message);
            }
        });
    }

    @UiHandler("reloadUserButton")
    protected void onReloadUserButtonClick(ClickEvent e) {
        fetchUserData();
    }

    private void setSportsmanInputFields(Sportsman sportsman) {

        beginInput.ready(new Function() {
            @Override
            public Object call(Object o) {
                beginInput.setValue(sportsman.getBegin());
                return beginInput;
            }
        });
        endInput.ready(new Function() {
            @Override
            public Object call(Object o) {
                endInput.setValue(sportsman.getEnd());
                return endInput;
            }
        });

        statusInput.ready(new Function() {
            @Override
            public Object call(Object o) {
                statusInput.setValue(sportsman.getStatus());
                return statusInput;
            }
        });

        addressInput.ready(new Function() {
            @Override
            public Object call(Object o) {
                addressInput.setValue(sportsman.getAddress());
                return addressInput;
            }
        });
    }

    private void setListBoxWithItems(Collection<Sportsman> sportsmen) {
        content.clear();
        for (Sportsman sportsman : sportsmen) {
            Item item = new Item();
            item.setTitle(sportsman.getFullName());
            item.setDescription(sportsman.getBegin() + "-" + sportsman.getEnd() + " location:" + sportsman.getAddress());
            content.add(item);
        }
        toastShower.showToast(sportsmen.isEmpty() ? "No sportsmen were found" : ("Found " + sportsmen.size() + " sportsmen"));
    }

    interface WidgetUiBinder extends UiBinder<HTMLPanel, MainPanel> {
    }
}
