import com.mybatis.test.ExceptionTypeEnum;
import com.mybatis.test.Tewst;

public class Test {
    Tewst t = null;
    @org.junit.Test
    public void test(){
        if (ExceptionTypeEnum.NONE_EMERGENCY.equals(t.test(12))){
            System.out.println(1);
        }
    }
}
