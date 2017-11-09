package tools;

import java.io.File;

import tools.fileUtil.FileUtil;

public class Jtest
{

    /**
     * @Title: main
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author Dangzhang
     * @param args
     * @throws
     */
    public static void main(String[] args)
    {
        //号码
        Long [] phones = {
                13669722287l

        };
        File file = new File("E://test.sql");
        String phone = "";//
        System.out.println("-- 总条数:"+phones.length);
        for (Object l : phones) {
            phone = l.toString();
            String f20 = "fq9900004406";
            String z20 = "zq9922139617";
            
            yuer(file, f20, z20, phone, 20+"");
            
            String f30 = "fq9900004405";
            String z30 = "zq9922139616";
            
            yuer(file, f30, z30, phone, 30+"");
            
//            String f30 = "fq9900004405";  fq9900003807  zq9922017972  fq9900003804  zq9921937339
//            String z30 = "zq9922139616";
//            
//            yuer(file, f30, z30, phone, 30+"");
            String f50 = "fq9900004404";
            String z50 = "zq9922139615";
//            
            yuer(file, f50, z50, phone, 50+"");
            
            
            System.out.println("-- ******************----");
        }
    }
    
    
    public static void yuer(File file ,String fq, String zq, String phone, String zhi){
        //--插入奖品码表数据
        String sql1 = "insert into T_CCS_PRIZE(id,f_card_number, f_phonenum,F_GET_TIME,F_PRIZE_TYPE,F_PRIZE_PRICE,Default5,State) "+
                  "values "+
                   "( CC_PRIZE_SEQUENCE1.NEXTVAL,'"+zq+"','"+phone+"',to_char(sysdate, 'yyyymmddhh24miss'),'1',to_char(to_number("+zhi+") * 100),'1','2');";
        //--插入券使用记录
        String sql2 = "insert into T_CCS_USE_RECORD "+
                    "(f_card_number, "+
                     "F_USER_NUMBER, "+
                     "F_RECEIVE_TIME, "+
                     "F_PARENT_NUMBER, "+
                     "PRIZEID, "+
                     "BATCH) "+
                  "values "+
                    "('"+zq+"', "+
                     "'"+phone+"', "+
                    " to_char(sysdate, 'yyyymmddhh24miss'), "+
                    " '"+fq+"', "+
                     "CC_PRIZE_SEQUENCE1.CURRVAL, "+
                     "'"+fq+"' || '_' || '"+phone+"' || '_' || to_char(sysdate, 'yyyymmddhh24miss'));  ";
                     
        //--插入白名单
        String sql3 = "insert into T_CCS_WHITE_LIST( W_CARD_NUM, W_PHONENUM, W_END_NUM, W_CITY, W_STATE) "+
                  "values ('"+fq+"','"+phone+"',SUBSTR('"+phone+"', -1),'0871',0);";
        
        System.out.println("-- "+phone+"_start");
        FileUtil.writeInFileByPW(file, "-- "+phone+"_start"+"\r\n", "UTF-8", true);
        System.out.println(sql1);
        FileUtil.writeInFileByPW(file, sql1+"\r\n", "UTF-8", true);
        System.out.println(sql2);
        FileUtil.writeInFileByPW(file, sql2+"\r\n", "UTF-8", true);
        System.out.println(sql3);
        FileUtil.writeInFileByPW(file, sql3+"\r\n", "UTF-8", true);
        System.out.println("-- "+phone+"_end");
        FileUtil.writeInFileByPW(file, "-- "+phone+"_end"+"\r\n", "UTF-8", true);
        FileUtil.writeInFileByPW(file, "\r\n", "UTF-8", true);
        
        
    }

}
