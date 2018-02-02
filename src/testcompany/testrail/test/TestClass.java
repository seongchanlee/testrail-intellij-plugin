package testcompany.testrail.test;

import org.junit.Test;

public class TestClass {

    /**
     * Title
     * Steps
     * Results
     */
    @TestLog(title = "Test1", id = "1")
    @Test
    public void test1() {

    }

    /**
     * Title
     * Steps
     * Results
     */
    @Test
    @TestLog(id = "2", title = "Test2", notes1 = "1.11")
    public void test2() {

    }

    @Test
    @TestLog(title = "Test3", id = "444")
    public void test3() {
        // This test is not in the database.
    }

}
