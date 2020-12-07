package com.bjtu.redis;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class readjson {
    private String path;
    private String JsonStr;
    private JSONObject jo;

    public readjson(String path) throws IOException {
        File jsonFile = new File(path);
        FileReader fileReader = new FileReader(jsonFile);
        Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
        int s = 0;
        StringBuffer sbuffer = new StringBuffer();
        while((s = reader.read()) != -1) {
            sbuffer.append((char) s);
        }
        fileReader.close();
        reader.close();
        this.JsonStr = sbuffer.toString();
        this.jo = JSON.parseObject(this.JsonStr);
        this.path=path;
    }

    public String getStr(){
        return this.JsonStr;
    }

    public JSONObject getJobj(){
        return this.jo;
    }

    public List<user> getusers() {
        ArrayList<user> u = new ArrayList<user>();
        Iterator iterator = this.jo.keySet().iterator();
        while(iterator.hasNext()) {
            user user1 = new user();
            String key = (String) iterator.next();
            JSONObject helper = this.jo.getJSONObject(key);
            user1.setId(key);
            user1.setCounter(helper.getIntValue("num"));
            user1.setValues(helper.getString("say"));
            user1.setAction(helper.getString("action"));
            user1.setTime("");

            u.add(user1);
        }

        return u;
    }
}

