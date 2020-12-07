package com.bjtu.redis;

import com.bjtu.redis.readjson;

import java.util.List;

public class json_test {
    public static void main(String args[]) throws Exception {
        //获取json文件
        readjson reader = new readjson(readjson.class.getClassLoader().getResource("user.json").getPath());
        json_op test = new json_op();

        test.loading();

        //对读取json文件内容操作
        List<user>users = reader.getusers();

        for (user s:users){
            test.action(s);
        }
        //Count测试
        System.out.println("当前访问次数:" + test.getInt());
        test.Count("NUM",5);
        System.out.println("当前访问次数:" + test.getInt());

        //用户访问周期
        System.out.println(test.getFreq("wmy"));
        System.out.println(test.getFreq("wl"));



    }
}

