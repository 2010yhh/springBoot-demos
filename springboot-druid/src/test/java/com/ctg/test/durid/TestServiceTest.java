package com.ctg.test.durid;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2019/3/16 17:08
 */

import com.ctg.test.durid.service.TestService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

public class TestServiceTest {

//    @Mock
//    TestService sevice1;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMock() {
        TestService sevice1=  Mockito.mock(TestService.class);
        // 执行mock，而不是真正部分，所以没有打印任何信息
        sevice1.fun("testMock 1:");

        // doCallRealMethod声明后，执行真正部分
        // 但是Mock只能对public（fun1）和protected函数进行mock
        // 对private函数（fun2）仍执行真正部分
        // 所以输出fun和fun2
        doCallRealMethod().when(sevice1).fun(anyString());
        sevice1.fun("testMock 2");

        // 执行mock，输出int的默认值0，而不是5
        System.out.println("val: " + sevice1.getVal());
        // when声明后，既不走真正部分，也不走mock，直接返回thenReturn()中定义的值
        // 注意：该值的类型需要和when中函数返回值类型一致
        when(sevice1.getVal()).thenReturn(10);
        System.out.println("val: " + sevice1.getVal());
    }
    @Test
    public void testSpy() {
        TestService sevice2=  Mockito.spy(TestService.class);
        // 执行真正部分
        sevice2.fun("testSpy 1:");

        // 执行真正部分
        System.out.println("val: " + sevice2.getVal());
        // 自定义返回值
        when(sevice2.getVal()).thenReturn(10);
        System.out.println("val: " + sevice2.getVal());
    }

}
