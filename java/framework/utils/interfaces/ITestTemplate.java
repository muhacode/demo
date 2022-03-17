package framework.utils.interfaces;

import org.testng.annotations.*;

/**
 * Test Template determines the structure of the project.tests
 */
public interface ITestTemplate {

    void preconditions() throws Throwable;


    void test() throws Throwable;

    void postconditions() throws Throwable;
}