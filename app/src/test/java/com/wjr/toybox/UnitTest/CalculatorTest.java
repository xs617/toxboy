package com.wjr.toybox.UnitTest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Administrator on 2017/3/24.
 */
public class CalculatorTest {

    private Calculator mCalculator;
    @Before
    public void setUp() throws Exception {
        mCalculator = new Calculator();
    }

    @Test
    public void sum() throws Exception {
        assertEquals(6d,mCalculator.sum(1d,5d),0);
    }

    @Test
    public void substract() throws Exception {
        assertEquals(4d,mCalculator.substract(5d,1d),0);
    }

    @Test
    public void divide() throws Exception {
        assertEquals(4d,mCalculator.divide(20d,5d),0);
    }

    @Test
    public void multiply() throws Exception {
        assertEquals(10d,mCalculator.multiply(2d,5d),0);
    }

}