package com.bjtu.redis;
import com.bjtu.redis.JedisInstance;
import com.bjtu.redis.readjson;
import com.bjtu.redis.data;
import redis.clients.jedis.Jedis;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class json_op {
    private String uuserPath;
    public json_op() {
        this.uuserPath = readjson.class.getClassLoader().getResource("user.json").getPath();
    }

    public  Jedis connection() throws Exception{
        Jedis jedis = JedisInstance.getInstance().getResource();
        return jedis;
    }

    public void loading() throws Exception {
        Jedis jedis = connection();
        jedis.flushDB();

        System.out.println("开始加载json...");

        readjson reader = new readjson(this.uuserPath);
        JSONObject jo = reader.getJobj();

        for (String key : jo.keySet()) {
            JSONObject helper_obj = jo.getJSONObject(key);
            Count("NUM", helper_obj.getIntValue("num"));
            System.out.println("正在载入: " + key + " 次数: " + helper_obj.getIntValue("num") + " 当前访问总数:" + getInt());
        }
        System.out.println("成功载入json");
    }


    public void action(user u) throws Exception {
        Jedis jedis = JedisInstance.getInstance().getResource();

        int counter=u.getCounter();
        String ID=u.getId();
        String values=u.getValues();
        String action=u.getAction();
        String time=u.getTime();

        data d=new data();
        time=d.update(time);

        System.out.println("姓名: " + ID + " 操作: " + action + " 访问次数: "+counter + " 本次操作周期: " + d.OldTime(time));

    }

    public void Count(String NUM,int count) {
        Jedis jedis = JedisInstance.getInstance().getResource();
        for(int i = 0;i<count;i++) {
            jedis.incr(NUM);
        }
    }

    public String getInt() throws Exception {
        Jedis jedis = connection();
        return jedis.get("NUM");
    }

    public String getFreq(String key) throws Exception {
        Jedis jedis = connection();
        data d = new data();
        return key + "上次访问时间:" + d.NewTime(jedis.hget(key,"time")) ;

    }
}
