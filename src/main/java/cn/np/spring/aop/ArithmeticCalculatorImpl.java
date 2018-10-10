package cn.np.spring.aop;

import org.springframework.stereotype.Component;

/**
 * @author np
 * @date 2018/10/10
 */
@Component
public class ArithmeticCalculatorImpl implements ArithmeticCalculator {

    @Override
    public int add(int x, int y) {
        return x + y;
    }

    @Override
    public int sub(int x, int y) {
        return x - y;
    }

    @Override
    public int multi(int x, int y) {
        return x * y;
    }

    @Override
    public int div(int x, int y) {
        if (y == 0) {
            return 0;
        }
        return x / y;
    }
}
