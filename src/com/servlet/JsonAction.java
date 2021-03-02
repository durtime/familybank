package com.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;



import com.bean.Bill;
import com.bean.User;
import com.google.gson.Gson;


import net.sf.json.JSONObject;

public class JsonAction {
	private int page = 1;
	public void setPage(int page) {
	    this.page = page;
	}
	public int getPage() {
	    return page;
	}
	private int limit = 2;
	public int getLimit() {
	    return limit;
	}
	public void setLimit(int limit) {
	    this.limit = limit;
	}
	 
	public boolean findAll(int count,ArrayList<Bill> billlist){
	    Gson gson = new Gson();
	    String json = gson.toJson(billlist);
	    System.out.println(json+"---");
	    String string = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+json+"}";
	    File file = new File("C:\\Users\\kai\\eclipse-workspace\\familybank\\WebContent\\layuimini\\api\\info.json");
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(string.getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	    return true;
	}

    public JSONObject findAll2(int count,ArrayList<Bill> billlist){
        Map<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("code", 0);
        result.put("msg", "");
        result.put("count",count);
        result.put("data", billlist);
        JSONObject json = JSONObject.fromObject(result);
        return json;
    }

    public JSONObject find3(int count,User user){
        Map<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("code", 0);
        result.put("msg", "");
        result.put("count",1);
        result.put("data", user);
        JSONObject json = JSONObject.fromObject(result);
        return json;
    }
}
