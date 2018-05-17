package com.assign.telstra.Assignment.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.assign.telstra.Assignment.bean.NameTriangleType;
import com.assign.telstra.Assignment.bean.ReqList;
import com.assign.telstra.Assignment.exception.AssignmentServiceException;

@RunWith(MockitoJUnitRunner.class)
public class AssignmentServiceMocKTests {
	
	
	AssignmentService service;
	@Before
	public void setUp() {
		service = new AssignmentService();
	}
	
	@Test
	public void fibonacciNumberTest() throws AssignmentServiceException {
		long value=service.fibonacciNumber(11);
		assertEquals(value,89);
	}
	@Test(expected = AssignmentServiceException.class)
	public void fibonacciNumberTestOverflowValue() throws AssignmentServiceException {
	service.fibonacciNumber(93);
	}
	
	@Test
	public void reverseWordsTest() throws AssignmentServiceException {
		String value=service.reverseWords("how are you");
		assertEquals(value,"woh era uoy");
	}
	
	
	@Test
	public void checkTringleTypeTestScaleneTriangle() throws AssignmentServiceException {
		NameTriangleType value=service.checkTringleType(1,2,3);
		assertEquals(value.name(),"SCALENE");
	}
	@Test
	public void checkTringleTypeTestEquilateral() throws AssignmentServiceException {
		NameTriangleType value=service.checkTringleType(1,1,1);
		assertEquals(value.name(),"EQUILATERAL");
	}
	@Test
	public void checkTringleTypeTestIsoceles() throws AssignmentServiceException {
		NameTriangleType value=service.checkTringleType(6,1,1);
		assertEquals(value.name(),"ISOSCELES");
	}

	@Test
	public void checkTringleTypeTestError() throws AssignmentServiceException {
		NameTriangleType value=service.checkTringleType(1,1,0);
		assertEquals(value.name(),"ERROR");
	}
	@Test
	public void sortedIntergerListTest() throws AssignmentServiceException {
		
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5,6, 8);
		ReqList listOfArray = new ReqList();
		listOfArray.setArray1(Arrays.asList(1, 2, 3));
		listOfArray.setArray2(Arrays.asList(4, 5, 6));
		listOfArray.setArray3(Arrays.asList(4, 8, 2));
		 List<Integer> value=service.sortedIntergerList(listOfArray);
		assertEquals(value,list);
	}
	
	
	
	
	
	
	

}
