package com.chernenkov.status.client.sportsmensnetwork.network;

/**
 * Created by Andrey on 05.04.2016.
 */

import com.chernenkov.status.client.sportsmensnetwork.util.Common;
import com.chernenkov.status.client.sportsmensnetwork.model.Sportsman;
import com.chernenkov.status.client.sportsmensnetwork.model.SportsmenMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.*;

import java.util.Collection;


public class SportsmenRepository {

    static SportsmenMapper sportsmenMapper = GWT.create(SportsmenMapper.class);

    public static void findUsersReadyForSportActivityWithYou(UsersCallback usersCallback) {
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, URL.encode(Common.PATH_GET_USERS_INTERSECTIONS));
        requestBuilder.setHeader("Content-type", "application/json");

        try {
            requestBuilder.sendRequest(null, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    Collection<Sportsman> sportsmen = sportsmenMapper.read(response.getText());
                    usersCallback.onReceiveUsers(sportsmen);
                }

                @Override
                public void onError(Request request, Throwable throwable) {
                    usersCallback.onError(throwable.getMessage());
                }
            });
        } catch (RequestException e) {
            usersCallback.onError(e.getMessage());
        }

    }

    public interface UsersCallback {
        void onReceiveUsers(Collection<Sportsman> sportsmen);

        void onError(String message);
    }
}
