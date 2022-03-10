/*
 * Copyright 2015-2021 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package com.example.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.NoSuchElementException;
import java.util.Random;

class TqsStackTests {

	TqsStack stack;

	@BeforeEach
	void init(){
		stack = new TqsStack();
	}

	@Test
	@DisplayName("Stack is empty on construction")
	void testIsEmptyInit(){
		assertTrue(stack.isEmpty());
	}

	@Test
	@DisplayName("Stack is empty on construction")
	void testSizeInit(){
		assertEquals(0, stack.size());
	}

	@Test
	@DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
	void testIsEmptyAfterPush(){
		Random rand = new Random();
		Integer n  = rand.nextInt(10)+1;

		for (int i=0; i < n; i++){
			stack.push("Hello");
		}

		assertFalse(stack.isEmpty());
		assertEquals(n, stack.size());
	}

	@Test
	@DisplayName("If one pushes x then pops, the value popped is x.")
	void testPopAfterPush(){
		Random rand = new Random();
		Integer n  = rand.nextInt(10) + 1;
		Integer element = null;
		for (int i=0; i < n; i++){
			element = rand.nextInt();
			stack.push(element);
		} 

		assertEquals(element, stack.pop());
		assertEquals(n - 1, stack.size());
	}

	@Test
	@DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
	void testPeekAfterPush(){
		Random rand = new Random();
		Integer n  = rand.nextInt(10) + 1;
		Integer element = null;
		for (int i=0; i < n; i++){
			element = rand.nextInt();
			stack.push(element);
		} 

		assertEquals(element, stack.peek());
		assertEquals(n, stack.size());

	}

	@Test
	@DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
	void testEmptyAfterPops(){
		Random rand = new Random();
		Integer n  = rand.nextInt(10) + 1;

		for (int i=0; i < n; i++){
			stack.push(1);
		}
		
		for (int i=0; i < n; i++){
			stack.pop();
		}

		assertTrue(stack.isEmpty());
	}

	@Test
	@DisplayName("Popping from an empty stack does throw a NoSuchElementException")
	void testPoppingEmptyStack(){
		assertTrue(stack.isEmpty());
		assertThrows(NoSuchElementException.class, () -> {stack.pop();});
	}

	@Test
	@DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
	void testPeekingEmptyStack(){
		assertTrue(stack.isEmpty());
		assertThrows(NoSuchElementException.class, () -> {stack.peek();});
	}

	@Test
	@DisplayName("For bounded stacks only:pushing onto a full stack does throw an IllegalStateException")
	void testPushFullStack(){
		TqsStack<Integer> fullstack = new TqsStack<>(0);
		assertThrows(IllegalStateException.class, () -> { fullstack.push(1);});
	}
	
}
