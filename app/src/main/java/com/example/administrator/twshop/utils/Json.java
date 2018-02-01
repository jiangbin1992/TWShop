package com.example.administrator.twshop.utils;


import com.example.administrator.twshop.contants.Result;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bj on 2017/3/27.
 */

public class Json {
    /**
     * 解析返回值状态
     *
     * @param json
     * @return
     */
    public static Result parse_message(String json) {
        Result message = new Result();
        try {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            message.setState(jsonObject.getString("ret").equals("ok") ? true : false);
            message.setMessage(jsonObject.getString("msg"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return message;
    }
}
