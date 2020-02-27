package graphite;

import java.util.HashMap;
import java.util.Map;

public class Calculate {
	public static Map<String, Integer> variables = 
			new HashMap<String, Integer>();
	public static int cal(Node ast) throws Exception {
		int result = 0;
		Node p = ast;
		if(p.value.text.equals("+")) {
			if(p.left == null || p.right == null) {
				throw new Exception("illeagle expression");
			}
			int left = cal(p.left);
			int right = cal(p.right);
			return left + right;
		}else if(p.value.text.equals("*")) {
			if(p.left == null || p.right == null) {
				throw new Exception("illeagle expression");
			}
			int left = cal(p.left);
			int right = cal(p.right);
			return left * right;
		}else if(p.value.type == Type.IntLiteral) {
			String x = p.value.text;
			int y = Integer.parseInt(x);
			return y;
		}else if(p.value.type == Type.Identifier) {
			String x = p.value.text;
			int y = variables.get(x);
			return y;
		}
		return 0;
		
	}
	public static Node mutiplitive(CreateToken tokens){
		Token f = tokens.getToken();
		Token tmp = tokens.peep();
		Node result = null;
		if(tmp != null && tmp.text.equals("*")) {
			tmp = tokens.getToken();
			result = new Node(tmp);
			result.left = new Node(f);
			result.right = new Node(tokens.getToken());
		}else {
			result = new Node(f);
		}
		return result;
	}
	public static Node additive(CreateToken tokens){
		if(tokens.peep() == null) 
			return null;
		Node mt = mutiplitive(tokens);
		Token t = tokens.peep();
		Node result = mt;
		if(t!=null && t.text.equals("+")) {
			t = tokens.getToken();
			result = new Node(t);
			result.left = mt;
			result.right = additive(tokens);
		}
		return result;
	}

	public static Node statement(CreateToken tokens) throws Exception {// matching all expressions
		Token first = tokens.peep();
		Node result = null;
		if(first.type == Type.KeyWord) {
			result = intDeclaration(tokens);
		}else if(first.type == Type.Identifier || first.type == Type.IntLiteral) {
			result = expression(tokens);
			if(result == null) {
				result = assignment(tokens);
			}
		}
		return result;
	}
	public static Node intDeclaration(CreateToken tokens) throws Exception {// match declaration statement only int
		Token first = tokens.peep();
		Node result = null;
		if(first.text.equals("int")) {
			tokens.getToken();
			Token variable = tokens.getToken();
			if(variable.type != Type.Identifier) {
				throw new Exception("missing variable");
			}
			Token equal = tokens.peep();
			if(equal == null) {
				variables.put(variable.text, 0);
			}else if(!equal.text.equals("=")) {
				throw new Exception("missing equal sign");
			}
			equal = tokens.getToken();
			Node value = expression(tokens);
			//result = new Node(first);
			//result.left = new Node(variable);
			//result.right = value;
			variables.put(variable.text, cal(value));
		}
		return result;
	}
	public static Node expression(CreateToken tokens){// ∆•≈‰±Ì¥Ô Ω
		Token first = tokens.peep();
		if(first == null) {
			return null;
		}
		Node result = null;
		result = additive(tokens);
		return result;
	}
	public static Node assignment(CreateToken tokens) throws Exception {// ∆•≈‰∏≥÷µ”Ôæ‰
		Token first = tokens.peep();
		Node result = null;
		String x = first.text;
		if(variables.get(x) == null) {
			throw new Exception("an illeagle variable");
		}
		first = tokens.getToken();
		Token equal = tokens.getToken();
		if(!equal.text.equals("=")) {
			throw new Exception("missing equal sign");
		}
		int y = cal(expression(tokens));
		variables.replace(x, y);
		return result;
	}
}
