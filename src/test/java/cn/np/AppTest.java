package cn.np;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }


    @Test
    public void yinyong(){
        StringBuffer stringBuffer=new StringBuffer("abc");
        System.out.println(stringBuffer);
        change(stringBuffer);
        System.out.println(stringBuffer);

    }


    private static void change(StringBuffer s){
        s=new StringBuffer("plm");
        s.append("123");
    }
}
