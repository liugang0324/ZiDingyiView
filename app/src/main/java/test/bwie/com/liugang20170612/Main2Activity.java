package test.bwie.com.liugang20170612;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    private ArrayList<Bean> list;
    private static List<Bean> sRetStr=new ArrayList<Bean>();
    private static List<Integer> s=new ArrayList<Integer>();
    private List<Bean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //initData();
        initDat();
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

       /* for (int i = 0; i <list.size() ; i++) {
            map.put(list.get(i).getDate(),i);
        }
         Log.d("ddd",list.toString());*/





    }
    public  static LinkedHashMap<String, Person> getOrder(Map<String, Person>  map){
        List<Map.Entry<String, Person>> infoIds =new ArrayList<Map.Entry<String, Person>>(map.entrySet());

        //排序
        Collections.sort(infoIds, new Comparator<Map.Entry<String, Person>>() {
            public int compare(Map.Entry<String, Person> o1, Map.Entry<String, Person> o2) {
                Person p1 = (Person) o1.getValue();
                Person p2 = (Person) o2.getValue();;

                if (p1.getAge() == p2.getAge()){
                    return -1;
                }else{
                    return  1;
                }
            }
        });

/*转换成新map输出*/
        LinkedHashMap<String, Person> newMap = new LinkedHashMap <String, Person>();

        for(Map.Entry<String,Person> entity : infoIds){
            newMap.put(entity.getKey(), entity.getValue());
        }

        return newMap;
    }
    private void initDat() {
        Map<String, Person> map = new HashMap<String, Person>();
        map.put("Json", new Person(1,"Json", 20));
        map.put("Peter", new Person( 2,"Peter",22));
        map.put("Divid", new Person(1,"Divid", 25));
        map.put("Aglia", new Person(3,"Aglia", 27));
        map.put("Alex", new Person(3,"Alex", 23));
        map.put("Molic", new Person(3,"Molic", 22));


        LinkedHashMap<String, Person> newMap = getOrder(map);

        Iterator<Map.Entry<String, Person>> iter = newMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Person> entry = (Map.Entry<String, Person>) iter.next();



            System.out.println( entry.getValue().getName() + "|" + entry.getValue().getLevel() + "|"
                    + entry.getValue().getAge()+ "|");
        }
    }

    private void initData() {
        list = new ArrayList<>();
        Bean b = new Bean();
        b.setDate("2017-06-12");
        b.setPrice(32.1f);
        list.add(b);
        Bean b1 = new Bean();
        b1.setDate("2017-06-12");
        b1.setPrice(30.1f);
        list.add(b1);
        Bean b2 = new Bean();
        b2.setDate("2017-06-16");
        b2.setPrice(32.5f);
        list.add(b2);
        Bean b3 = new Bean();
        b3.setDate("2017-06-17");
        b3.setPrice(32.5f);
        list.add(b3);
        Bean b4 = new Bean();
        b4.setDate("2017-06-12");
        b4.setPrice(132.1f);
        list.add(b4);
        Collections.sort(list, new Comparator<Bean>() {
            @Override
            public int compare(Bean o1, Bean o2) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    long l = sdf.parse(o1.getDate()).getTime();
                    long l1 = sdf.parse(o2.getDate()).getTime();
                    if(l>l1){
                        return -1;
                    }else if (l<l1){
                        return 1;
                    }else {
                        //第二排序
                        if (o1.getPrice()>o2.getPrice()){
                            return -1;
                        }else if(o1.getPrice()<o2.getPrice()){
                            return 1;
                        }else{
                            return 0;
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return 0;
            }
        });


    }

}
