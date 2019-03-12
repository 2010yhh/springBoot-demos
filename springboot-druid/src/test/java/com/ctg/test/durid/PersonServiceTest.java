package com.ctg.test.durid;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2019/3/11 18:30
 */

import com.alibaba.fastjson.JSON;
import com.ctg.test.durid.mock.Person;
import com.ctg.test.durid.mock.PersonDao;
import com.ctg.test.durid.mock.PersonService;
import org.junit.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PersonServiceTest {

    @Mock
    private PersonDao personDAO;  // 模拟对象
    private PersonService personService;  // 被测类

    public PersonServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    // 在@Test标注的测试方法之前运行
    /**
     * 在需要Mock的属性上标记@Mock注解，然后@RunWith(MockitoJUnitRunner.class)
     * 或者在setUp()方法中显示调用MockitoAnnotations.initMocks(this);生成Mock类即可。
     */
    @Before
    public void setUp() throws Exception {
        // 初始化测试用例类中由Mockito的注解标注的所有模拟对象
        MockitoAnnotations.initMocks(this);
        // 用模拟对象创建被测类对象
        personService = new PersonService(personDAO);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void shouldUpdatePersonName() {
        Person person = new Person(1, "Phillip");
        // 设置模拟对象的返回预期值
        when(personDAO.fetchPerson(1)).thenReturn(person);
        // 执行测试
        boolean updated = personService.update(1, "David");
        // 验证更新是否成功
        assertTrue(updated);
        // 验证模拟对象的fetchPerson(1)方法是否被调用了一次
        verify(personDAO).fetchPerson(1);
        // 得到一个抓取器
        ArgumentCaptor<Person> personCaptor = ArgumentCaptor.forClass(Person.class);
        // 验证模拟对象的update()是否被调用一次，并抓取调用时传入的参数值
        verify(personDAO).update(personCaptor.capture());
        // 获取抓取到的参数值
        Person updatePerson = personCaptor.getValue();
        // 验证调用时的参数值
        assertEquals("David", updatePerson.getPersonName());
        System.out.print(JSON.toJSONString(updatePerson));
    }
}