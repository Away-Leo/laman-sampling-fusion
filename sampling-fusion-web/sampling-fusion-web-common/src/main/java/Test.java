import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args){
        String testStr="大家<span code=\"{circle}\"></span><input code=\"{}\" type=\"checkBox\"></input>";
        Document document=Jsoup.parse(testStr);
        Elements elements=document.getElementsByAttribute("code");
        for(int i=0;i<elements.size();i++){
            Element element=elements.get(i);
            element.text("eeer");
        }
        System.out.println(document);

        List<String> tempList=new ArrayList<>();
        tempList.add("11");
        tempList.add("22");
        tempList.add("33");
        tempList.add("44");
        tempList.add("55");
        tempList.add("66");
        tempList.add("77");
        tempList.add("88");
        System.out.println(JSON.toJSONString(tempList));

    }
}
