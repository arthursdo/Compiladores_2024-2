import java.io.*;

/**
 * The TokenType enum defines the types of tokens utilized during the lexical analysis phase.
 * Each token type represents a specific syntactic element that can appear in the input source code.
 *
 * The supported token types are:
 * - NUM: Represents numeric literals (e.g., single digit numbers).
 * - SOMA: Represents the addition operator ('+').
 * - MULT: Represents the multiplication operator ('*').
 * - APar: Represents the opening parenthesis ('(').
 * - FPar: Represents the closing parenthesis (')').
 * - EOF: Represents the end of the file/input stream.
 *
 * This enum is primarily used to classify tokens during the analysis of the input and
 * facilitates the handling of different syntactic components in parsing and code generation.
 */
enum TokenType{ NUM, SOMA, MULT, APar, FPar, EOF}

class Token{
  /**
   * Represents the character lexeme corresponding to a token.
   * This field stores the specific character from the input source code
   * that the token instance is associated with, such as a numeric digit,
   * an operator, or a parenthesis.
   *
   * It is primarily used to retain the raw character information
   * that forms part of the lexical token and can be utilized in subsequent
   * parsing and code generation processes.
   */
  String lexema;
  /**
   * Specifies the type of the token as part of lexical analysis.
   *
   * This variable represents one of the enumerated values from the TokenType enum,
   * which classifies the syntactic purpose of the token (e.g., NUM for numeric literals,
   * SOMA for addition operators, MULT for multiplication operators, APar and FPar for
   * parentheses, or EOF for the end of the input).
   *
   * The value of this variable is assigned during the creation of a Token object to
   * indicate the specific role the token plays in the parsing process.
   */
  TokenType token;

 /**
  * Constructs a new Token instance with the specified character and token type.
  *
  * @param l The character representing the lexeme of the token.
  * @param t The type of the token (e.g., NUM, SOMA, MULT, APar, FPar, EOF).
  */
 Token (char l, TokenType t)
 { lexema=Character.toString(l);token = t;}

 Token (String l, TokenType t)
 	{ lexema=l;token = t;}

}

class AnaliseLexica {

	BufferedReader arquivo;

	char frac = '.';

	/**
	 * Constructs an AnaliseLexica object and initializes it with the file specified by the given path.
	 * Creates a BufferedReader to read the contents of the file.
	 *
	 * @param a The path to the file that needs to be processed for lexical analysis.
	 * @throws Exception If an error occurs while opening or reading the file.
	 */
	AnaliseLexica(String a) throws Exception
	{
		
	 	this.arquivo = new BufferedReader(new FileReader(a));
		
	}

	/**
	 * Retrieves the next valid token from the input source, skipping whitespace and control characters.
	 * This method performs lexical analysis to identify tokens such as numbers, operators, and parentheses.
	 * If an invalid character is encountered, an exception is thrown.
	 * If the end of the file is reached, a token of type EOF is returned.
	 *
	 * @return A Token object representing the next valid token from the input source.
	 * @throws Exception If an invalid character is encountered or an input/output error occurs.
	 */
	Token getNextToken() throws Exception
	{	
		Token token;
		int eof = -1;
		char currchar;
		int currchar1;

			do{
				currchar1 =  arquivo.read();
				currchar = (char) currchar1;
			} while (currchar == '\n' || currchar == ' ' || currchar =='\t' || currchar == '\r');
			
			if(currchar1 != eof && currchar1 !=10)
			{
				if (currchar >= '0' && currchar <= '9') {
					String num = "";

					do{
						num += currchar;
						arquivo.mark(1);
						currchar1 = arquivo.read();
						currchar = (char) currchar1;
					}while ((currchar >= '0' && currchar <= '9') || currchar == frac);

					arquivo.reset();

					if(num.endsWith(String.valueOf(frac)))
						num += "0";
					//System.out.println("num: " + num);
					//System.out.println("currchar: " + currchar);

					return (new Token(num, TokenType.NUM));
				}else
					switch (currchar){
						case '(':
							return (new Token (currchar,TokenType.APar));
						case ')':
							return (new Token (currchar,TokenType.FPar));
						case '+':
							return (new Token (currchar,TokenType.SOMA));
						case '*':
							return (new Token (currchar,TokenType.MULT));
						
						default: throw (new Exception("Caractere inválido: " + ((int) currchar)));
					}
			}

			arquivo.close();
			
		return (new Token(currchar,TokenType.EOF));
		
	}
}
