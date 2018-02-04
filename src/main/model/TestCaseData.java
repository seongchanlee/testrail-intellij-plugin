package main.model;

import java.util.ArrayList;
import java.util.List;

public class TestCaseData {
    private List<TestCase> testCases;

    public TestCaseData(){
        testCases = new ArrayList<>();

        TestCase case1 = new TestCase.Builder("1", "Test Case 1")
                .setSteps("<html><ol><li>Log in</li><li>Create User</li><li>Do something</li></ol></html>")
                .setExpectedResults("<html><ol><li>Something should happen</li></ol></html>")
                .build();

        TestCase case2 = new TestCase.Builder("2", "Test Case 2")
                .setPrerequisites("1. This test case has prerequisites")
                .setSteps("<html><ol><li>Log in</li><li>Step 2</li><li>Step 3</li></ol></html>")
                .setExpectedResults("<html><ol><li>Result 1</li><li>Result 2</li><li>Result 3</li></ol></html>")
                .build();

        testCases.add(case1);
        testCases.add(case2);
    }

    public List<TestCase> getTestCases(){
        return testCases;
    }

    public boolean testCaseExists(String id){
        for(TestCase testCase : testCases){
            System.out.println(testCase.getId());
            System.out.println(id);

            if(testCase.getId().equals(id)){
                System.out.println("Testcase found!");
                return true;
            }
        }
        System.out.println("No testcase match found");
        return false;
    }

    public TestCase getTestCase(String id){
        for(TestCase testCase : testCases){
            if(testCase.getId().equals(id)){
                System.out.println("Testcase found!");
                return testCase;
            }
        }
        System.out.println("No testcase match found");
        return null;
    }
}
