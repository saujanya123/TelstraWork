package com.assign.telstra.Assignment.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.assign.telstra.Assignment.bean.ErrorDetails;
import com.assign.telstra.Assignment.bean.FabResponse;
import com.assign.telstra.Assignment.bean.NameTriangleType;
import com.assign.telstra.Assignment.bean.ReqList;
import com.assign.telstra.Assignment.bean.SortedListRes;
import com.assign.telstra.Assignment.commonUtils.CommonValidation;
import com.assign.telstra.Assignment.constant.ValidationConstant;
import com.assign.telstra.Assignment.exception.AssignmentServiceException;
import com.assign.telstra.Assignment.service.AssignmentServiceImpl;

@RestController
@RequestMapping("/api")
public class TaskController {

	@Autowired
	private AssignmentServiceImpl service;

	

	@RequestMapping(value = "/Fibonacci", method = RequestMethod.GET)
	public ResponseEntity<FabResponse> getURLValue(@RequestParam("n") String number) throws AssignmentServiceException {
		int sequenceNumber = inputValidate(number);
		long result = service.fibonacciNumber(Math.abs(sequenceNumber));
		FabResponse fabResponse = new FabResponse();
		fabResponse.setFibonacciNumber(Long.toString(result));
		return new ResponseEntity<>(fabResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/ReverseWords", method = RequestMethod.GET)
	public ResponseEntity<String> inputString(@RequestParam("sentence") String sentence)
			throws AssignmentServiceException {
		if (!CommonValidation.isNullOrEmpty(sentence)) {
			throw new AssignmentServiceException(ValidationConstant.SENTENCE_CANNOT_BE_NULL_OR_EMPTY);
		}
		String reverseWords = service.reverseWords(sentence);
		return new ResponseEntity<>(reverseWords, HttpStatus.OK);
	}

	@RequestMapping(value = "/TriangleType", method = RequestMethod.GET)
	public ResponseEntity<NameTriangleType> inputLength(@RequestParam("a") String lengthOfA,
			@RequestParam("b") String lengthOfB, @RequestParam("c") String lengthOfC)
			throws AssignmentServiceException {

		isNullAndIsNumeric(lengthOfA);
		isNullAndIsNumeric(lengthOfB);
		isNullAndIsNumeric(lengthOfC);

		NameTriangleType tringleType = service.checkTringleType(Integer.parseInt(lengthOfA),
				Integer.parseInt(lengthOfB), Integer.parseInt(lengthOfC));

		return new ResponseEntity<>(tringleType, HttpStatus.OK);
	}

	@RequestMapping(value = "/makeonearray", method = RequestMethod.POST)
	public ResponseEntity<SortedListRes> inputArrayList(@RequestBody ReqList listOfArray)
			throws AssignmentServiceException {
		isArrayListEmpty(listOfArray);
		List<Integer> sortList=service.sortedIntergerList(listOfArray);
		
		SortedListRes sortedlist=new SortedListRes();
		sortedlist.setArray(sortList);
		return new ResponseEntity<>(sortedlist, HttpStatus.OK);
	}

	private void isArrayListEmpty(ReqList listOfArray) throws AssignmentServiceException {
		if(CollectionUtils.isEmpty(listOfArray.getArray1()))
		{
			throw new AssignmentServiceException("Array1 of "+ValidationConstant.ARRAYLIST_CANNOT_BE_NULL_OR_EMPTY);
		}
		if(CollectionUtils.isEmpty(listOfArray.getArray2()))
		{
			throw new AssignmentServiceException("Array2 of "+ValidationConstant.ARRAYLIST_CANNOT_BE_NULL_OR_EMPTY);
		}
		if(CollectionUtils.isEmpty(listOfArray.getArray3()))
		{
			throw new AssignmentServiceException("Array3 of"+ValidationConstant.ARRAYLIST_CANNOT_BE_NULL_OR_EMPTY);
		}
	}

	private int inputValidate(String number) throws AssignmentServiceException {
		isNullAndIsNumeric(number);

		int sequenceNumber = Integer.parseInt(number);
		if (sequenceNumber <= 0) {
			throw new AssignmentServiceException(ValidationConstant.NUMBER_SHOULD_BE_POSITIVE);
		}
		return sequenceNumber;
	}

	private void isNullAndIsNumeric(String number) throws AssignmentServiceException {
		if (!CommonValidation.isNullOrEmpty(number)) {
			throw new AssignmentServiceException(ValidationConstant.NUMBER_CANNOT_BE_NULL_OR_EMPTY);
		}
		if (!CommonValidation.isNumeric(number)) {
			throw new AssignmentServiceException(ValidationConstant.NUMBER_SHOULD_BE_NUMERIC);
		}
	}

	@ExceptionHandler(AssignmentServiceException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(AssignmentServiceException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	public AssignmentServiceImpl getService() {
		return service;
	}

	public void setService(AssignmentServiceImpl service) {
		this.service = service;
	}
}
