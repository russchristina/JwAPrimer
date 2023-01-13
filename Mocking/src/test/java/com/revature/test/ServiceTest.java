package com.revature.test;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.times;

import com.revature.Repository;
import com.revature.Service;

@TestInstance(Lifecycle.PER_CLASS)
public class ServiceTest {

	/*
	 * If we want to test the "Service" class, we will need an instance of it.
	 * This is referred to as our "Object Under Test".
	 * 
	 * We have to tell Mockito that there is a relationship between our mock(s)
	 * and our oject under test.
	 * As such, we can use the @InjectMocks annotation.
	 */
	@InjectMocks
	public Service service;
	
	/*
	 * The first step to mocking is determining what exactly needs to be mocked.
	 * In general, we want to mock our object under test's dependencies. In this
	 * case, our object under test only has one dependency: a dependency of type
	 * Repository. Note that Mockito can inject this mock for us if we instruct
	 * it to.
	 */
	@Mock
	public Repository repository;
	
	
	/*
	 * I'm using @BeforeAll because the Object Under Test is not stateful, which
	 * means I'm not concerned about a brand new instance for every test. I can
	 * use the same instance for each test without any side effects affecting
	 * the results of other tests.
	 */
	@BeforeAll
	public void setup() {
		this.service = new Service();
		/*
		 * While Mockito is mostly annotation-driven, we do have to initialize
		 * the mocks by calling "openMocks".
		 */
		MockitoAnnotations.openMocks(this);
	}
	
	//Not a unit test. This is an integration test.
	/**
	 * This test passed before we mocked our Repository dependency. Why does it
	 * fail now? By just annotating this class's fields and calling openMocks(),
	 * we've already succeeded in mocking. This means that the Repository's
	 * findAll() method, which is transitively called via the sort() method,
	 * is not truly called. This results in an empty ArrayList being returned.
	 */
	@Test
	public void testSort() {
		List<String> sortedNames = this.service.sort();
		Assertions.assertEquals("Rory", sortedNames.get(4));
	}
	
	/*
	 * We want to write a "true" unit test. This means that we want to better
	 * isolates a defect so that we can more easily ascertain where a defect is.
	 * In order to do this in this case, we need to mock out our dependency.
	 * We will mock our dependency using a mocking framework like framework, but
	 * it's possible to do this on your own as well. The standard implementation
	 * for mocking frameworks entails subclassing the type that is being mocked
	 * and overriding the methods so that they return stubs. For this reason,
	 * you can't mock final classes with Mockito.
	 * 
	 * Recall that Mockito's default mocking will prevent the actual implementation
	 * of the Repository's methods from being used. This results in findAll() returning
	 * an empty List. Is there some way to control this output? Yes. Mockito has built-in
	 * functionality that allows for easy stubbing. Stubbing simply entails providing a
	 * "canned" or "predetermined" answer. We control what this output is.
	 */
	@Test
	public void unitTestSort() {
		/*
		 * Before we call the sort() method, perhaps we should define some dummy
		 * data (stub) that will be returned when findAll is called. We can do
		 * this using the "Mockito" utility which allows for easy stubbing and
		 * behavior verification (verifying that the code behaves as expected meaning
		 * that methods are called when they should be or the number of times that they
		 * should be).
		 */
		
		Mockito.when(this.repository.findAll()).thenReturn(
				Arrays.asList("christina", "mark", "adam", "alec"));
		List<String> sortedNames = this.service.sort();
		//Example of behavior verification with Mockito.
		Mockito.verify(this.repository, times(1)).findAll();
		Assertions.assertEquals("adam", sortedNames.get(0));
		/*
		 * If you want soft assertions, you can use assertAll, though the
		 * syntax is not the friendliest. The recommendation would instead be
		 * to AssertJ, which unfortunately is a 3rd party library. 
		 * Link: https://assertj.github.io/doc/
		 */
		Assertions.assertAll("A group of assertions",
				() -> Assertions.assertEquals("adam", sortedNames.get(0), "adam test"),
				() -> Assertions.assertEquals("fadfsafa", sortedNames.get(1), "jibberish test")
				);
	}
	
	
}
