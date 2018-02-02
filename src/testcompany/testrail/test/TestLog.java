package testcompany.testrail.test;

public @interface TestLog {
    String id();
    String title();
    String notes1() default "1.13";
}
