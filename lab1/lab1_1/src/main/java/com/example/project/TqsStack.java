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

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class TqsStack<E> {

	private ArrayList<E> stack = new ArrayList<>();
	Integer cap = -1;

	public TqsStack(){
	}

	public TqsStack(int cap){
		this.cap = cap;
	}


	public void push(E element){
		if (stack.size() == cap){
			throw new IllegalStateException();
		}
		else{
			stack.add(element);
		}
	}

	public E pop(){
		if (stack.isEmpty()){
			throw new NoSuchElementException();
		}
		else{
			E element = stack.get(stack.size() - 1);
			stack.remove(stack.size() - 1);
			return element;
		}
	}

	public E peek(){
		if (stack.isEmpty()){
			throw new NoSuchElementException();
		}
		else{
			E element = stack.get(stack.size() - 1);
			return element;
		}
	}


	public int size(){
		return stack.size();
	}

	public boolean isEmpty(){
		return stack.isEmpty();
	}

}
