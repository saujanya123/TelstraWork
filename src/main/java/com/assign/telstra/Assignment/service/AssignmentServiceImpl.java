package com.assign.telstra.Assignment.service;

import java.util.List;

import com.assign.telstra.Assignment.bean.NameTriangleType;
import com.assign.telstra.Assignment.bean.ReqList;
import com.assign.telstra.Assignment.exception.AssignmentServiceException;

public interface AssignmentServiceImpl {

	long fibonacciNumber(int abs) throws AssignmentServiceException;

	String reverseWords(String sentence)  throws AssignmentServiceException;

	NameTriangleType checkTringleType(int i, int j, int k)  throws AssignmentServiceException;

	List<Integer> sortedIntergerList(ReqList listOfArray) throws AssignmentServiceException;

}
