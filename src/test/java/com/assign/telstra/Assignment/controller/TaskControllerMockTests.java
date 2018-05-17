package com.assign.telstra.Assignment.controller;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.assign.telstra.Assignment.bean.FabResponse;
import com.assign.telstra.Assignment.bean.NameTriangleType;
import com.assign.telstra.Assignment.bean.ReqList;
import com.assign.telstra.Assignment.bean.SortedListRes;
import com.assign.telstra.Assignment.exception.AssignmentServiceException;
import com.assign.telstra.Assignment.service.AssignmentService;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerMockTests {
	@Mock
	AssignmentService assignmentService;

	TaskController controller;

	@Before
	public void setUp() {
		controller = new TaskController();
		controller.setService(assignmentService);

	}
	@Test
	public void testGetURLValue() throws AssignmentServiceException {
		Mockito.when(assignmentService.fibonacciNumber(12)).thenReturn((long) 1225);
		ResponseEntity<FabResponse> entity = controller.getURLValue("12");
		assertEquals(entity.getBody().getFibonacciNumber(), "1225");

	}

	@Test
	public void testinputLength() throws NumberFormatException, AssignmentServiceException {
		NameTriangleType tringleType = null;
		Mockito.when(
				assignmentService.checkTringleType(Integer.parseInt("1"), Integer.parseInt("1"), Integer.parseInt("1")))
				.thenReturn(tringleType.valueOf("SCALENE"));
		ResponseEntity<NameTriangleType> entity = controller.inputLength("1", "1", "1");
		assertEquals(entity.getBody().name().toString(), "SCALENE");

	}
	@Test
	public void testinputString() throws NumberFormatException, AssignmentServiceException {
		String sentence = "how are you";
		Mockito.when(assignmentService.reverseWords(sentence)).thenReturn("woh era uoy");
		ResponseEntity<String> entity = controller.inputString(sentence);
		assertEquals(entity.getBody().toString(), "woh era uoy");
	}

	@Test
	public void testinputArrayList() throws NumberFormatException, AssignmentServiceException {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 8);
		ReqList listOfArray = new ReqList();
		listOfArray.setArray1(Arrays.asList(1, 2, 3));
		listOfArray.setArray2(Arrays.asList(4, 5, 6));
		listOfArray.setArray3(Arrays.asList(4, 8, 2));
		Mockito.when(assignmentService.sortedIntergerList(listOfArray)).thenReturn(list);
		ResponseEntity<SortedListRes> entity = controller.inputArrayList(listOfArray);
		assertEquals(entity.getBody().getArray(), list);

	}

	@Test(expected = AssignmentServiceException.class)
	public void testinputArrayListIsNullArrayList() throws NumberFormatException, AssignmentServiceException {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 8);
		ReqList listOfArray = new ReqList();
		listOfArray.setArray1(Arrays.asList(1, 2, 3));
		controller.inputArrayList(listOfArray);

	}
	@Test(expected = AssignmentServiceException.class)
	public void testinputArrayListIsNullArrayList2() throws NumberFormatException, AssignmentServiceException {
		ReqList listOfArray = new ReqList();
		listOfArray.setArray1(Arrays.asList());
		controller.inputArrayList(listOfArray);

	}

	@Test(expected = AssignmentServiceException.class)
	public void testinputArrayListIsNullArrayList3() throws NumberFormatException, AssignmentServiceException {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 8);
		ReqList listOfArray = new ReqList();
		listOfArray.setArray1(Arrays.asList(1, 2, 3));
		listOfArray.setArray2(Arrays.asList(4, 5, 6));
		controller.inputArrayList(listOfArray);

	}

	@Test(expected = AssignmentServiceException.class)
	public void testInputValidate() throws AssignmentServiceException {
		controller.getURLValue("");
	}
	@Test(expected = AssignmentServiceException.class)
	public void testInputValidateNegative() throws AssignmentServiceException {
		controller.getURLValue("-25");
	}
	@Test(expected = AssignmentServiceException.class)
	public void testInputValidateNumeric() throws AssignmentServiceException {
		controller.getURLValue("abc");
	}
	
	@Test(expected = AssignmentServiceException.class)
	public void testinputLengthInputValidate() throws NumberFormatException, AssignmentServiceException {
		controller.inputLength("", "1", "1");
	}
	@Test(expected = AssignmentServiceException.class)
	public void testinputLengthInputValidateNumeric() throws NumberFormatException, AssignmentServiceException {
		controller.inputLength("abc", "1", "1");

	}
	@Test(expected = AssignmentServiceException.class)
	public void testinputStringValidate() throws NumberFormatException, AssignmentServiceException {
		controller.inputString("");

	}
}