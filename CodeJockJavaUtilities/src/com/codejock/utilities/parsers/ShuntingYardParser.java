package com.codejock.utilities.parsers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.lang3.math.NumberUtils;

public class ShuntingYardParser {
	private TreeSet<String> operatorSet = new TreeSet<String>();
	private ArrayList<String> operatorList = new ArrayList<String>();

	{
		operatorSet.add("+");
		operatorSet.add("-");
		operatorSet.add("*");
		operatorSet.add("/");

		operatorList.add("+");
		operatorList.add("-");
		operatorList.add("*");
		operatorList.add("/");


	}

	protected Queue<String> convertAlgebraicToRPN(String algerbraicNotation){
		Stack<String> operators = new Stack<String>();
		ArrayList<String> tokens = new ArrayList<String>();
		ConcurrentLinkedQueue<String> output = new ConcurrentLinkedQueue<String>();

		if(algerbraicNotation != null && !algerbraicNotation.trim().isEmpty()){
			algerbraicNotation = algerbraicNotation.replaceAll("\\(", "\\( ");
			algerbraicNotation = algerbraicNotation.replaceAll("\\)", " \\)");
			String[] tokensArray = algerbraicNotation.split("\\s");
			for (String token : tokensArray) {
				tokens.add(token);
			}
			for(String token: tokens){
				if(NumberUtils.isNumber(token)){
					output.add(token);

				}else if(operatorSet.contains(token)){
					//token is an operator
					while(operators.size() > 0){
						//if there are operators with greater precedence than the current operator
						//pop them off the stack put them on the queue
						String topOperator = operators.peek();
						if(operatorList.indexOf(topOperator) > operatorList.indexOf(token)){
							output.add(operators.pop());
						}else{

							break;
						}
					}
					operators.push(token);
				}else if("(".equals(token)){
					operators.push(token);
					output.add(token);
				}else if(")".equals(token)){
					String operator = null;
					while(operators.size() > 0 && !"(".equals(operator)){
						operator = operators.pop();
						if(!"(".equals(operator)){
							output.add(operator);
						}

					}
					output.add(")");
				}
			}
			while(!operators.isEmpty()){
				output.add(operators.pop());
			}

		}
		return output;
	}

	private void printQueue(Queue<String> queue){
		StringBuffer buffer = new StringBuffer();
		for(String item: queue){
			buffer.append(item);
			buffer.append(' ');
		}
		if(buffer.length() > 0){
			buffer.deleteCharAt(buffer.length() - 1);
		}
		System.out.println(buffer.toString());
	}

	public static void main(String argv[]){
		ShuntingYardParser parser = new ShuntingYardParser();
		Queue<String> queue = parser.convertAlgebraicToRPN("(1 + 2) * 3");
		parser.printQueue(queue);
		System.out.println( "Final Eval: " + parser.handleNotation(queue.iterator(), 0));
		//System.out.println(parser.evaluateRPN(queue));
		queue = parser.convertAlgebraicToRPN("9 + 24 / (7 - 3)");
		parser.printQueue(queue);
		System.out.println( "Final Eval: " + parser.handleNotation(queue.iterator(), 0));
		//System.out.println(parser.evaluateRPN(queue));
		queue = parser.convertAlgebraicToRPN("(1 + 2) / 5 + (7 * 2 - 5)");
		parser.printQueue(queue);
		System.out.println( "Final Eval: " + parser.handleNotation(queue.iterator(), 0));
		//System.out.println(parser.evaluateRPN(queue));

		queue = parser.convertAlgebraicToRPN("((1 + 2) * 3) + (3 * (1 + 2))");
		parser.printQueue(queue);
		System.out.println( "Final Eval: " + parser.handleNotation(queue.iterator(), 0));


		//System.out.println(parser.evaluate(new CharacterIterator("(1 + 2) / 5 + (7 * 2 - 5)")));
		System.out.println(parser.evaluate(new CharacterIterator("(5 + 2) * (3 - 1) + (10 / 2)")));

	}

	public double evaluateRPN(Queue<String> queue){
		double value;
		Stack<Double> evalStack = new Stack<Double>();
		for(String token: queue){
			if(NumberUtils.isNumber(token)){
				evalStack.push(Double.parseDouble(token));
			}else if(operatorSet.contains(token)){
				switch(token){
				case "-":
					doSubtraction(evalStack);
					break;

				case "+":
					doAddition(evalStack);
					break;

				case "*":
					doMultiplication(evalStack);
					break;

				case "/":
					doDivision(evalStack);
					break;
				}

			}
		}
		value = evalStack.pop();
		return value;
	}

	private void doSubtraction(Stack<Double>evalStack){
		double first, second;
		second = evalStack.pop();
		first = evalStack.pop();
		double value =  first - second;
		evalStack.push(value);
	}

	private void doAddition(Stack<Double>evalStack){
		double first, second;
		second = evalStack.pop();
		first = evalStack.pop();
		double value =  first + second;
		evalStack.push(value);
	}

	private void doMultiplication(Stack<Double>evalStack){
		double first, second;
		second = evalStack.pop();
		first = evalStack.pop();
		double value =  first * second;
		evalStack.push(value);
	}

	private void doDivision(Stack<Double>evalStack){
		double first, second;
		second = evalStack.pop();
		first = evalStack.pop();
		double value =  first / second;
		evalStack.push(value);
	}

	public double evaluate(Iterator<Character> expressionItr){
		double result = -1;
		Stack<String> operators = new Stack<String>();
		ArrayList<String> tokens = new ArrayList<String>();
		ConcurrentLinkedQueue<String> output = new ConcurrentLinkedQueue<String>();
		Character token;
		StringBuffer numberBuffer = null;
		while(expressionItr.hasNext()){
			token = expressionItr.next();
			if(NumberUtils.isNumber(token.toString())){
				if(numberBuffer == null){
					numberBuffer = new StringBuffer();
				}
				numberBuffer.append(token);
				//output.add(token.toString());

			}else if(operatorSet.contains(token.toString())){
				checkNumberBuffer(numberBuffer, output);
				numberBuffer = null;
				//token is an operator
				while(operators.size() > 0){
					//if there are operators with greater precedence than the current operator
					//pop them off the stack put them on the queue
					String topOperator = operators.peek();
					if(operatorList.indexOf(topOperator) > operatorList.indexOf(token.toString())){
						output.add(operators.pop());
					}else{

						break;
					}
				}
				System.out.println("Operator: " + token.toString());
				operators.push(token.toString());
			}else if("(".equals(token.toString())){
				checkNumberBuffer(numberBuffer, output);
				numberBuffer = null;

				operators.push(token.toString());
			}else if(")".equals(token.toString())){
				checkNumberBuffer(numberBuffer, output);
				numberBuffer = null;
				String operator = null;
				while(operators.size() > 0 && !"(".equals(operator)){
					operator = operators.pop();
					if(!"(".equals(operator.toString())){
						output.add(operator.toString());
					}
				}
			}else{
				checkNumberBuffer(numberBuffer, output);
				numberBuffer = null;
			}
		}
		while(!operators.isEmpty()){
			output.add(operators.pop());
		}
		printQueue(output);
		return evaluateRPN(output);
	}

	private void checkNumberBuffer(StringBuffer numBuffer, Queue<String> output){
		if(numBuffer != null){
			output.add(numBuffer.toString());
		}
	}

	private double handleNotation(Iterator<String> iter, int level){
		StringBuffer buffer = new StringBuffer();
		ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
		String prefix = "";
		for(int i = 0; i < level; i++){
			prefix += "\t";
			buffer.append('\t');
		}
		while(iter.hasNext()){
			String token = iter.next();
			if(")".equals(token)){
				double value = evaluateRPN(queue);
				System.out.println(buffer.toString() + " = " +  value);

				return value;
			}else if("(".equals(token)){
				double value = handleNotation(iter, level + 1);
				queue.add(value + "");
			}else{
				buffer.append(token);
				queue.add(token);
			}
		}
		System.out.println(buffer.toString());
		return evaluateRPN(queue);
	}
}

class CharacterIterator implements Iterator<Character> {

    private final String str;
    private int pos = 0;

    public CharacterIterator(String str) {
        this.str = str;
    }

    public boolean hasNext() {
        return pos < str.length();
    }

    public Character next() {
        return str.charAt(pos++);
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}

