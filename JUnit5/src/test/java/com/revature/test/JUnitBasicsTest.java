package com.revature.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/*
 * JUnit is arguably the most popular testing framework for Java. We are using
 * version 5 as it is the latest stable version, though it is not uncommon to see
 * JUnit 4 being used in projects. 
 * 
 * JUnit is popular, so it often is supported out of the box by many platforms and
 * IDEs. For instance, Eclipse will automatically add the JAR files for both JUnit 4
 * and JUnit 5 if needed. In addition, popular frameworks (e.g. Spring) also have
 * built-in support for JUnit.
 * 
 * JUnit, like TestNG, is annotation-driven. In order to build out a test suite,
 * you can simply create a class and annotate the class's methods.
 */

/*
 * This feature was added in JUnit 5 to give developers some control over how
 * JUnit executed tests. The old default (the PER_METHOD strategy) created a 
 * new instance of the test class (JUnitBasics) for every single test method.
 * This mandated that your @BeforeAll and @AfterAll were statically called. 
 * That said, you can opt to use a PER_CLASS life cycle in which only instance
 * of the test class is used.
 */
@TestInstance(Lifecycle.PER_CLASS)
public class JUnitBasicsTest {
	
	/*
	 * Typically, you don't just have tests in a test class. When building out a
	 * test suite (collection of tests), you also want to do some setup and/or
	 * teardown. "Setup" typically refers to the steps we take before running
	 * tests to ensure that the test environment is configured properly for
	 * the tests to be able to run (e.g. starting a test DB, opening a file to
	 * read from, etc.). "Teardown", on the other hand, usually refers to the
	 * steps you take after your tests are done running (e.g. closing your test
	 * DB connections, closing the file you were reading from, etc.). 
	 * 
	 * JUnit 5 allows you to fine tune your setup using @BeforeAll or @BeforeEach.
	 * 
	 * JUnit 5 allows you to fine tune your teardown using @AfterAll or @AfterEach. 
	 */
	
	/**
	 * This method will run ONCE at the beginning of the test suite.
	 * 
	 * Traditionally, your @BeforeAll and @AfterAll are static as JUnit tries
	 * to call methods marked with these annotations statically. As such, it 
	 * is common to see your @BeforeAll and @AfterAll attached to static
	 * methods.
	 * 
	 * That said, JUnit 5 introduced a way for developers to forgo making the
	 * @AfterAll and @BeforeAll methods static.
	 */
	@BeforeAll
	public void setup() {
		System.out.println("this runs ONCE at teh beginning of the test suite");
	}
	
	/**
	 * This method will run once per test. In other words, if there are 5 tests
	 * in the suite, this method will run once before every single test for a 
	 * total of 5 times.
	 */
	@BeforeEach
	public void granularSetup() {
		System.out.println("I run once before every test.");
	}
	
	/*
	 * The @Test annotation is self-explanatory in that it simply marks a method as a
	 * JUnit test. @Order simply allows us more control over the order in which the tests
	 * run.
	 */
	@Test
	@Order(1)
	public void test() {
		/**
		 * Tests without assertions are not tests. A test should compare an
		 * expected outcome to an actual outcome. 
		 * 
		 * Expected outcome = what I expect to happen
		 * Actual outcome = what actually happened
		 * 
		 * There is a built-in way of comparing these two: Assertions.
		 */
		Assertions.fail();
		Assertions.assertEquals(2, Math.addExact(1, 1));
		//Executable
		System.out.println("test is running...");
	}
	
	/**
	 * This test does not run as we've disabled it.
	 */
	@Test
	@Disabled
	public void disabledTest() {
		
	}
	
	/** This method will run once per test. In other words, if there are 5 tests
	 * in the suite, this method will run once after every single test for a 
	 * total of 5 times.
	 */
	@AfterEach
	public void granularTeardown() {
		System.out.println("I run once after each test.");
	}
	
	/**
	 * This method will run ONCE at the end of the test suite.
	 */
	@AfterAll
	public void teardown() {
		System.out.println("I run ONCE at the end of the test suite");
	}

}
