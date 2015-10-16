package com.heineck.myhttprequestsample.MyRest;

/**
 * Created by vheineck on 16/10/15.
 */
public interface OnRestTaskListener {

    public void onPreExecute();
    public void onSuccess(String result);
    public void onError(int responseCode, String message);

}
