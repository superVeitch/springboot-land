import com.mybatis.test.ExceptionTypeEnum;
import com.mybatis.test.Tewst;

import java.util.Random;

public class Test {
    Tewst t = null;
    @org.junit.Test
    public void test(){
        if (ExceptionTypeEnum.NONE_EMERGENCY.equals(t.test(12))){
            System.out.println(1);
        }
    }

    @org.junit.Test
    public void random(){
        int i = new Random().nextInt(5);
        System.out.println(i);
    }
}
