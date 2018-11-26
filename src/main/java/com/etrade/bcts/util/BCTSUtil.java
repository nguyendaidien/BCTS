package com.etrade.bcts.util;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class BCTSUtil {
	public static String convertJsonArrToString(JSONArray jsonArr, char delimiter) {
		String[] arr = new String[jsonArr.length()];
		try {
			for (int i = 0; i < jsonArr.length(); i++) {
				arr[i] = jsonArr.getString(i);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return StringUtils.join(arr, delimiter);
	}
	
	public static JSONArray convertStringToJsonArr(String str, String delimiter) {
		String[] strArr = str.trim().split(delimiter);
		JSONArray arr = new JSONArray(Arrays.asList(strArr));
		return arr;
	}
	
	public static void main(String[] args) {
		JSONArray arr = convertStringToJsonArr("abc@gmail.com,bcd@gm.com", ",");
		System.out.println(arr.toString());
	}
}
