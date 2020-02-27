package graphite;

public enum Type {
	IntLiteral,// 常量
	Identifier,// 标识符
	Operator,Alpha,Digital,// 运算符号 字母 数字？
	GT,GE,ST,SE,EE,// 大于，大于等于，小于，小于等于，等于
	KeyWord ,// 关键字
	Plus,Minus,Star,Slash,Mod, // 加减乘除取余
	UnKnown
}
