package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonRondomness
{

    /**
     * @Title: main
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author jhb
     * @param args
     * @throws
     */
    public static void main(String[] args)
    {
        List<Integer> personList = new ArrayList<Integer>();
        Map<Integer, Integer> personMap = new HashMap<Integer, Integer>();
        // 生产一万个人,每个人有1块钱
        int a = 0;
        while (true)
        {
            personMap.put(++a, 1);
            if (a == 10000)
            {
                break;
            }
        }
        Integer random;
        int count = 0;
        while(true){
            count ++;
         // 每天，每个人随机给一个人一块钱
            for (Map.Entry<Integer, Integer> entry : personMap.entrySet())
            {
                while(true){
                    random = RandomUtils.getRandomInt(1, 10000);
                    if(random != entry.getKey()){
                        break;
                    }
                }
                personMap.put(entry.getKey(),entry.getValue()-1);
                personMap.put(random, personMap.get(random)+1);
//                System.out.println("key=1,,,value="+personMap.get(1));
            }
            if(count == 10000){
                break;
            }
        }
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : personMap.entrySet()){
            sum += entry.getValue();
            System.out.println(entry.getKey()+"---"+entry.getValue());
//            System.out.println(sum);
        }

    }
}
