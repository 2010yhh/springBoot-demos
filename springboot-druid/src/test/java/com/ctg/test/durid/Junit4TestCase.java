package com.ctg.test.durid;

/**
 * @Description: junit4单元测试
 * @Author: yanhonghai
 * @Date: 2019/3/12 9:30
 */
import org.junit.*;

public class Junit4TestCase {

    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println("Set up before class");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("Set up");
    }

    @Test
    public void testMathPow() {
        System.out.println("Test Math.pow");
        Assert.assertEquals(4.0, Math.pow(2.0, 2.0), 0.0);
    }

    @Test
    public void testMathMin() {
        System.out.println("Test Math.min");
        Assert.assertEquals(2.0, Math.min(2.0, 4.0), 0.0);
    }

    // 期望此方法抛出NullPointerException异常
    @Test(expected = NullPointerException.class)
    public void testException() {
        System.out.println("Test exception");
        Object obj = null;
        obj.toString();
    }

    // 忽略此测试方法
    @Ignore
    @Test
    public void testMathMax() {
        Assert.fail("没有实现");
    }
    // 使用“假设”来忽略测试方法
    @Test
    public void testAssume(){
        System.out.println("Test assume");
        // 当假设失败时，则会停止运行，但这并不会意味测试方法失败。
        Assume.assumeTrue(false);
        Assert.fail("没有实现");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Tear down");
    }

    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println("Tear down After class");
    }

}
