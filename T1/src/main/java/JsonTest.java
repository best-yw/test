import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @description:
 * @author: yangwei
 * @date: 2020/12/10 19:01
 * @blame smart security project Team
 * @version: 1.0
 **/
public class JsonTest {
    //读取json文件
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        UUID uuid=UUID.randomUUID();
//        String path = JsonTest.class.getClassLoader().getResource("openapi.json").getPath();
        String path=JsonTest.class.getClassLoader().getResource("openapi.json").getFile();
        String s = readJsonFile(path);
        JSONObject jobj = JSON.parseObject(s);

//        JSONArray arr=jobj.getJSONArray("paths");
//        for(int i=0;i<arr.size();i++){
//            JSONObject jsonObject=arr.getJSONObject(i);
//            System.out.println(jsonObject);
//        }


//        JSONObject pathObject=jobj.getJSONObject("paths");
//        JSONArray feedsArray = pathObject.getJSONArray("parameters");
//        for (int i = 0; i < feedsArray.size(); i++) {
//            JSONObject sonObject = feedsArray.getJSONObject(i);
//            System.out.println(sonObject.getString("name"));
//            JSONObject dataObject = sonObject.getJSONObject("schema");
//            String subjectStr = dataObject.getString("type");
//
//        }

        String object=jobj.getJSONObject("paths").getJSONObject("/accident/bulletin/info/{accidentId}").getJSONObject("get").getString("summary");
        String idJson=jobj.getJSONObject("paths").getJSONObject("/accident/bulletin/info/{accidentId}").getJSONObject("get").getString("operationId");
        System.out.println(jobj.getJSONObject("paths").getJSONObject("/accident/bulletin/info/{accidentId}").getJSONObject("get").getJSONArray("parameters").getString(0));
        String id=uuid.toString();
        String method=idJson;
        String path1="";
        String summary=object;
        String sql="INSERT INTO operations (id,method,path,summary) VALUES "+"("+id+","+method+","+path1+","+summary+")";

        List<String> sqlList=new ArrayList<>();
        sqlList.add(sql);
        for(String str : sqlList){
            System.out.println(str);
        }


    }
}
