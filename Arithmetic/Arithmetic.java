
public class Arithmetic {
	public static void main(String[] args) {
		String thing = "( 2 + ( ( 3 * 5 ) / ( 11 - 6 ) ) )";
		System.out.println(evaluate(thing));
	}

	//Evaluates a String exp that has an arithmetic expression, written in classic notation
	public static int evaluate(String exp) {
		String converted = convertClassicToStout(exp);
		return evaluateStout(converted);
	}
	
	//Returns the result of doing operand1 operation operand2,
	//e.g. operate(5, 2, "-") should return 3
	public static int operate(int operand1, int operand2, String operation) {
		if (operation.equals("+")) {
			return operand1 + operand2;
		} else if (operation.equals("-")) {
			return operand1 - operand2;
		} else if (operation.equals("*")) {
			return operand1 * operand2;
		} else if (operation.equals("/")) {
			return operand1 / operand2;
		} else if (operation.equals("^")) {
			return (int) Math.pow((double) operand1, (double) operand2);
		} else if (operation.equals("%")) {
			return operand1 % operand2;
		} else {
			return 0;
		}
	}
	
	//Evaluates a String exp that has an arithmetic expression written in STOUT notation
	public static int evaluateStout(String exp) {
		String[] temp = exp.split("\\s+");
		MyStack<String> statement = new MyStack<>();
		for (String str : temp) {
			if (str.equals("+") 
				|| str.equals("-") 
				|| str.equals("*") 
				|| str.equals("/") 
				|| str.equals("%") 
				|| str.equals("^")) {
				int operand2 = Integer.parseInt(statement.pop());
				int operand1 = Integer.parseInt(statement.pop());
				statement.push(String.valueOf(operate(operand1, operand2, str)));
			} else {
				statement.push(str);
			}
		}

		return Integer.parseInt(statement.peek());
	}
	
	public static String convertClassicToStout(String exp) {
		MyStack<Character> opStack = new MyStack<>();
		opStack.push('!');
		StringBuilder converted = new StringBuilder();
		char[] expSequence = exp.toCharArray();
		for (char a : expSequence) {
			if (a == ' ') {
				converted.append(" ");
			} else if (a <= 57 && a >= 48) {
				converted.append(a);
			} else if (a == '(') {
				opStack.push(a);
			} else if (a == ')') {
				while (opStack.peek() != '(') {
					converted.append(opStack.pop() + " ");
				}
				opStack.pop();
			} else if (a == '^') {
				if (opStack.peek() == '^') {
					converted.append(opStack.pop() + " ");
					opStack.push(a);
				} else {
					opStack.push(a);
				}
			} else if (a == '/') {
				if (opStack.peek() == '/' || opStack.peek() == '*' || opStack.peek() == '%') {
					converted.append(opStack.pop() + " ");
					opStack.push(a);
				} else {
					opStack.push(a);
				}
			} else if (a == '*') {
				if (opStack.peek() == '/' || opStack.peek() == '*' || opStack.peek() == '%') {
					converted.append(opStack.pop() + " ");
					opStack.push(a);
				} else {
					opStack.push(a);
				}
			} else if (a == '%') {
				if (opStack.peek() == '/' || opStack.peek() == '*' || opStack.peek() == '%') {
					converted.append(opStack.pop() + " ");
					opStack.push(a);
				} else {
					opStack.push(a);
				}
			} else if (a == '+') {
				if (opStack.peek() == '+' 
					|| opStack.peek() == '-' 
					|| opStack.peek() == '/' 
					|| opStack.peek() == '*' 
					|| opStack.peek() == '%') {
					converted.append(opStack.pop() + " ");
					opStack.push(a);
				} else {
					opStack.push(a);
				}
			} else if (a == '-') {
				if (opStack.peek() == '+' 
					|| opStack.peek() == '-' 
					|| opStack.peek() == '/' 
					|| opStack.peek() == '*' 
					|| opStack.peek() == '%') {
					converted.append(opStack.pop() + " ");
					opStack.push(a);
				} else {
					opStack.push(a);
				}
			} else {
				throw new IllegalArgumentException();
			}
		}

		while (!opStack.empty()) {
			converted.append(opStack.pop() + " ");
		}

		String givemehell = converted.replace(converted.length() - 3, converted.length(), "").toString();
		givemehell = givemehell.replaceAll("\\s+", " ");
		System.out.println(givemehell);
		return givemehell.trim();
	}
	
	
}
