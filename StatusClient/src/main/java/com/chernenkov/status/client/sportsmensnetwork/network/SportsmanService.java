package com.chernenkov.status.client.sportsmensnetwork.network;

import com.chernenkov.status.client.sportsmensnetwork.util.Common;
import com.chernenkov.status.client.sportsmensnetwork.model.Sportsman;
import com.chernenkov.status.client.sportsmensnetwork.model.UserMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.*;

import static com.chernenkov.status.client.sportsmensnetwork.util.Common.REGISTER_PATH_POST;

/**
 * Created by Andrey on 07.04.2016.
 */
public class SportsmanService {
    static UserMapper sportsmanMapper = GWT.create(UserMapper.class);

    public static void fetchSportsmanData(String userId, SportsmanCallback sportsmanCallback) {
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, URL.encode(Common.PATH_GET + userId));
        requestBuilder.setHeader("Content-type", "application/json");
        try {
            requestBuilder.sendRequest(null, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    //TODO: add handling 200 code
                    Sportsman sportsman = sportsmanMapper.read(response.getText());
                    sportsmanCallback.onReceiveSportsman(sportsman);
                }

                @Override
                public void onError(Request request, Throwable throwable) {
                    sportsmanCallback.onError("Failed to send request");
                }
            });
        } catch (RequestException re) {
            sportsmanCallback.onError(re.getMessage());
        }
    }

    public static void pushSportsmanData(String beginValue, String endValue, String statusValue, String addressValue, InfoCallback infoCallback) {
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, URL.encode(REGISTER_PATH_POST));
//        If no request headers have been set, the header "Content-Type" will be used with a value of "text/plain; charset=utf-8"
        requestBuilder.setHeader("Content-type", "application/json");
        try {
            //TODO: separate in different method
            String jsonString = sportsmanMapper.write(new Sportsman(beginValue, endValue, statusValue, addressValue, null, null, null));
            requestBuilder.sendRequest(jsonString, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    infoCallback.onDataPushed();
                }

                @Override
                public void onError(Request request, Throwable throwable) {
                    infoCallback.onError("Submitting status failed");
                }
            });
        } catch (RequestException re) {
            infoCallback.onError(re.getMessage());
        }
    }


    public interface SportsmanCallback {
        void onReceiveSportsman(Sportsman sportsman);

        void onError(String message);
    }

    public interface InfoCallback {
        void onDataPushed();

        void onError(String message);
    }

}
