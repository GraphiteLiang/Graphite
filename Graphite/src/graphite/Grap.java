package graphite;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Grap {
	
	public static void main(String args[]) throws Exception {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));
		while(true) {
			System.out.print("->");
			String script = reader.readLine().trim();
			if(script.equals("exit")) {
				break;
			}
			CreateToken tokens = new CreateToken(script);
			tokens.print();
			Node x = Calculate.statement(tokens);
			if(x != null) {
				int result = Calculate.cal(x);
				System.out.println(result);
			}
			
		}
		
	}
}
