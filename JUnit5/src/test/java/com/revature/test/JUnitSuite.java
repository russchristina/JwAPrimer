package com.revature.test;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

/**
 * 
 * Sometimes we want to group several different test classes together as a single
 * suite. This is possible with JUnit 5 via the @RunWith annotation. We will
 * combine this with @SuiteClasses to specify which classes will belong to this
 * suite.
 * 
 * NOTE: For running classes in a test suite, the understanding is that the runner
 * will be default expect the classes in the test packages to end with the word
 * "Test".
 */
@Suite
@SelectPackages("com.revature.test")
public class JUnitSuite {

}
