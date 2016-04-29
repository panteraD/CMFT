package com.chernenkov.status.client.sportsmensnetwork.util;

import com.google.gwt.user.client.ui.RootPanel;

/**
 * Created by Andrey on 18.03.2016.
 */
public class Common {
    public static final String PATH_GET = "/status/data/";
    public static final String PATH_GET_USERS_INTERSECTIONS = "/status/data/intersection/";
    public static final String USER_URL_PARAM = "id";
    public static final String REGISTER_PATH_POST = "/status/data/send";
    public static final String LOGOUT_PATH = "/status/logout";

    public static native void console(String text)
/*-{
    console.log(text);
}-*/;
}
