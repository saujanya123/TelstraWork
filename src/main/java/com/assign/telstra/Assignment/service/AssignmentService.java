package com.assign.telstra.Assignment.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.assign.telstra.Assignment.aop.logging.MyLogger;
import com.assign.telstra.Assignment.bean.NameTriangleType;
import com.assign.telstra.Assignment.bean.ReqList;
import com.assign.telstra.Assignment.constant.ErrorConstant;
import com.assign.telstra.Assignment.constant.ValidationConstant;
import com.assign.telstra.Assignment.exception.AssignmentServiceException;

@Service
@Component
public class AssignmentService implements AssignmentServiceImpl {

	@Override
	public long fibonacciNumber(int sequenceNumber) throws AssignmentServiceException {
		if (sequenceNumber >= ValidationConstant.MIN_FIBONACCI_INDEX_THAT_CAUSE_OVERFLOW) {
			throw new AssignmentServiceException(ErrorConstant.SEQUENCE_NUMBER_IS_TOO_BIG);
		}

		long firstNumber = 0;

		long secondNumer = 1;
		for (int i = 0; i < sequenceNumber; i++) {
			long next = firstNumber + secondNumer;
			firstNumber = secondNumer;
			secondNumer = next;
		}
		return firstNumber;

	}

	@Override
	public String reverseWords(String sentence) throws AssignmentServiceException {
		try {
			StringBuilder sb = new StringBuilder(sentence.length());
			Stack<Character> word = new Stack<>();
			for (Character c : sentence.toCharArray()) {
				if (Character.isWhitespace(c)) {
					pushWordToStringBuilder(sb, word);
					word.clear();

					sb.append(c);
				} else
					word.push(c);
			}

			pushWordToStringBuilder(sb, word);

			return sb.toString();
		} catch (Exception ex) {
			throw new AssignmentServiceException(ex.getMessage());
		}
	}

	private void pushWordToStringBuilder(StringBuilder sb, Stack<Character> word) {
		while (!word.empty())
			sb.append(word.pop());
	}

	@Override
	public NameTriangleType checkTringleType(int lengthA, int lengthB, int lengthC) throws AssignmentServiceException {

		try {

			if (lengthA <= 0 || lengthB <= 0 || lengthC <= 0)

				return NameTriangleType.ERROR;

			if (lengthA == lengthB) {
				return lengthB == lengthC ? NameTriangleType.EQUILATERAL : NameTriangleType.ISOSCELES;
			} else if (lengthB == lengthC) {
				return NameTriangleType.ISOSCELES;
			}

			return NameTriangleType.SCALENE;
		} catch (Exception ex) {
			throw new AssignmentServiceException(ex.getMessage());
		}
	}

	@Override
	public List<Integer> sortedIntergerList(ReqList listOfArray) throws AssignmentServiceException {
		try {
		Set<Integer> numberSet = new HashSet<>();
		numberSet.addAll(listOfArray.getArray1().stream().collect(Collectors.toSet()));
		numberSet.addAll(listOfArray.getArray2().stream().collect(Collectors.toSet()));
		numberSet.addAll(listOfArray.getArray3().stream().collect(Collectors.toSet()));
		List<Integer> sortedList = new ArrayList<>(numberSet);
		Collections.sort(sortedList);

		return sortedList;
		}
		catch(Exception ex)
		{
			throw new AssignmentServiceException(ex.getMessage());
		}
	}

}
