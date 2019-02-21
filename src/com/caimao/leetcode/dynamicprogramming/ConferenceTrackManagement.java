package com.caimao.leetcode.dynamicprogramming;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConferenceTrackManagement {
    static int[] x = {0};
    static int[] w = {0};
    static int[] v = {0};
    static int[][] V =null;
    static List<Speak> resultList = null;
    public static void main(String[] args){
        ConferenceTrackManagement conferenceTrackManagement = new ConferenceTrackManagement();
        //读取输入文件的内容，返回演讲对象的集合；
        List<Speak> list = conferenceTrackManagement.getProperties("D:\\speak.txt");
        //分析全部演讲时长，给出最短安排时间段,返回结果数组内表示每一个时间段的发言总数
        int[] schedule  = conferenceTrackManagement.generateSchedule(list);
        //System.out.println(Arrays.toString(schedule));
        //具体安排会议
        for(int subSession : schedule){
            int[] result = conferenceTrackManagement.findConf(list,subSession);
            //输出安排结果 resultList
            for(int i =0;i<result.length;i++){
                if(x[i]==1){
                    System.out.println("第"+i+"个物品被选中，容量为"+w[i]+",价值为"+v[i]);
                }
            }
            for(Speak speak : resultList){
                System.out.println(speak.getName());
            }
        }
    }

    /**
     * 该方法主要用来计算总时长后先规划多少天，生成一个排期表
     *
     * 读取配置文件，获取输入，创建发言对象（name发言名称；time分钟数；order按读取顺序；flag默认否）分别设置值后放入list
     * 遍历list获取sum(time),与7*60求余为零，则分别交替初始化sum(time)/（7*60）个180  240的背包
     * 如果sum(time)%（7*60）不为零则计算sum(time)%（6*60），为零则初始化sum(time)/（6*60） *2个180的背包列表
     * 都不为零则先分别交替初始化sum(time)/（7*60）个180  240的背包再判断余数和3的关系，大于3则两个180，小于等于3则一个
     */
    public int[] generateSchedule(List<Speak> list){
        int[] schedule = null;
        int totalSpeakTimes = 0;
        int days = 0;
        for(Speak speak : list){
            totalSpeakTimes += speak.getTime();
        }
        if(totalSpeakTimes%420 == 0){
            days = (totalSpeakTimes/420)*2;
            schedule = new int[days];
            for(int i=0; i<days; i++){
                if(i==0||(i%2==0)){
                    schedule[i] = 180;
                }else{
                    schedule[i] = 240;
                }
            }
        }else if(totalSpeakTimes%360 == 0){
            days = (totalSpeakTimes/360)*2;
            schedule = new int[days];
            for(int i=0; i<days; i++){
                schedule[i] = 180;
            }
        }else{
            if(totalSpeakTimes%420 >3){
                days = (totalSpeakTimes/420 + 1)*2;
            }else{
                days = (totalSpeakTimes/420)*2 +1;
            }
            schedule = new int[days];
            for(int i=0; i<days; i++){
                if(i==0||(i%2==1)){
                    schedule[i] = 180;
                }else{
                    schedule[i] = 240;
                }
            }
        }
        return schedule;
    }

    /**
     * 发言列表按照背包01动态规划计算和标记，标记后将列表中发言删除；再计算第二个，依次类推；
     * 把第一个方法算出来的排期表依次标注最大值
     */
    public int[] findMax(){
        return null;
    }

    /**
     * 根据每一个包的最大值去找发言，并标记，直到包列表排完并输出
     */
    public int[] findConf(List<Speak> list,int  subSession){
        //将对象列表放入数组中，全部标记未选中，以及初始化一个w数组，标记发言时间；
        int number = list.size();//所有发言个数
        int  capacity = subSession;
        x = new int[list.size()+1];
        w = new int[list.size()+1];
        v = new int[list.size()+1];
        V = new int[number+1][capacity+1];
        //循环list初始化
        for(int i=0; i<list.size(); i++){
            //w[0],v[0]都是0
            w[i+1] = list.get(i).getTime();
            v[i+1] = list.get(i).getTime();
        }

        int i,j;//j表示包的容量，
        for(i=1;i<=number;i++){
            for(j=1; j<=subSession; j++){
                if(j<w[i]){//包装不进去
                    V[i][j] = V[i-1][j];
                }else{//能装进去
                    if(V[i-1][j] > V[i-1][j-w[i]]+v[i]){
                        V[i][j] = V[i-1][j];
                    }else{
                        V[i][j] = V[i-1][j-w[i]]+v[i];
                    }
                }
            }
        }
        System.out.println((V[number][capacity]));
        resultList = new ArrayList<>();
        findProducts(number,capacity,list);
        return x;

    }


    public static void findProducts(int i, int j,List<Speak> list){
        if(i>0){
            if(V[i][j] == V[i-1][j]){//相等说明没有装
                x[i] = 0;//标记未选中
                findProducts(i-1,j,list);
            }else if(j-w[i] >= 0 && V[i][j] == V[i-1][j-w[i]]+v[i] && x[i] == 0){
                x[i] = 1;//标记已选中
                resultList.add(list.get(i));
                list.remove(i);
                findProducts(i-1, j-w[i],list);
            }
        }
    }

    private  List<Speak> getProperties(String filePath){
        List<Speak> list = new ArrayList<Speak>();
        try{
            File file = new File(filePath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str;
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);
            int order = 1;
            while((str = br.readLine()) != null){
                Matcher m = p.matcher(str);
                Speak speak = new Speak();
                speak.setName(str);
                speak.setOrder(order++);
                speak.setTime(Integer.valueOf(m.replaceAll("").trim()));
                list.add(speak);
                System.out.println(str);
            }
            br.close();
            fr.close();

        }catch(IOException e){
            e.printStackTrace();
        }
        return list;
    }

    class Speak{
        String name;
        int time;
        int order;
        Speak(){ }
        Speak(String name,int time,int order){
            this.name = name;
            this.time = time;
            this.order = order;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getTime() {
            return time;
        }
        public void setTime(int time) {
            this.time = time;
        }
        public int getOrder() {
            return order;
        }
        public void setOrder(int order) {
            this.order = order;
        }
    }
}
