package graphite;

public class CreateToken {
	private Token[] tokens;
	private int p;
	private int i;
	public CreateToken(String arg) {
		char[] args = arg.toCharArray();
		Type flag;
		Token token;
		int n = args.length;
		this.p = 0;
		this.i = 0;
		this.tokens = new Token[n];
		
		char first = args[0];
		flag = initFlag(first);
		token = new Token(flag, first);
		for(int i=1;i<n;i++) {
			char curr = args[i];
			if(curr == ' ') {
				if(!token.text.equals("")) {
					addToken(token);
					token = new Token(flag);
					continue;
				}
				continue;
			}
			switch(flag) {
				case Identifier:
					if(isAlpha(curr) || isDigital(curr)) {
						token.text = token.text + curr;
					}else {
						addToken(token);
						flag = initFlag(args[i]);
						token = new Token(flag, curr);
					}
					break;
				case IntLiteral:
					if(isDigital(curr)) {
						token.text = token.text + curr;
					}else {
						addToken(token);
						flag = initFlag(args[i]);
						token = new Token(flag, curr);
					}
					break;
				case Operator:
					if(isOperator(curr)) {
						token.text = token.text + curr;
					}else {
						addToken(token);
						flag = initFlag(args[i]);
						token = new Token(flag, curr);
					}
					break;
			}
		}
		if(!token.text.equals("")) {
			addToken(token);
		}
		for(int i=0;i<p;i++) {
			Token tmp = tokens[i];
			if(tmp.type == Type.Identifier) {
				for(String s:Keywords.keywords) {
					if(tmp.text.equals(s)) tmp.type = Type.KeyWord;
				}
			}
		}
				
	}
	public static boolean isAlpha(char arg) {
		if(arg >=65 && arg<=90) {
			return true;
		}else if(arg >=97 && arg<=122){
			return true;
		}
		return false;
	}
	public static boolean isDigital(char arg) {
		if(arg>=48 && arg<=57) {
			return true;
		}
		return false;
	}
	public static boolean isOperator(char arg) {
		char[] tmp = {'+','-','*','/','<','>','='};
		for(char x:tmp) {
			if(arg == x) return true;
		}
		return false;
	}
	public static Type initFlag(char arg) {
		Type flag = Type.UnKnown;
		if(isAlpha(arg)) {
			flag = Type.Identifier;
		}else if(isDigital(arg)) {
			flag = Type.IntLiteral;
		}else if(isOperator(arg)) {
			flag = Type.Operator;
		}
		return flag;
		
	}
	public void addToken(Token t) {
		if(t.text != "") {
			tokens[p] = t;
			p+=1;
		}
	}
	public void print() {
		for(int i=0;i<p;i++) {
			System.out.print(tokens[i].type + " ");
			System.out.print(tokens[i].text + "\n");
		}
	}
	public Token getToken() {
		Token result = tokens[i];
		i+=1;
		return result;
	}
	public Token peep() {
		if(i<p)
			return tokens[i];
		else return null;
	}
	public void unGet() {
		i--;
	}
	public void setPosition(int i) {
		this.i = i;
	}
}
