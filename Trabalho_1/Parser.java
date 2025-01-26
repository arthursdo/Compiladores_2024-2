class Parser{

	/**
	 * The scanner variable is an instance of the AnaliseLexica class, which is used to
	 * perform lexical analysis on the input source code. It provides the Parser
	 * with tokens sequentially as it processes the input.
	 *
	 * This variable plays a critical role in connecting the lexical analysis phase
	 * with the parsing phase of a compiler. It ensures that the Parser receives valid
	 * tokens for constructing the syntax tree.
	 */
	AnaliseLexica scanner;
	

	/**
	 * Constructs a Parser object and initializes it with the given lexical analyzer.
	 * The lexical analyzer is used to extract tokens from the input source during the parsing process.
	 *
	 * @param s An instance of AnaliseLexica, which provides tokens for the parser to process.
	 */
	Parser(AnaliseLexica s)
	{
		scanner = s;
	}

	/**
	 * Parses the input source code to generate the corresponding syntax tree.
	 *
	 * This method initiates the parsing process using a recursive descent approach.
	 * It constructs a syntax tree by evaluating expressions and expects that the input
	 * ends with the EOF (end-of-file) token. If the EOF token is not found after
	 * parsing the expression, an exception is thrown.
	 *
	 * @return An instance of ArvoreSintatica representing the parsed syntax tree of the input.
	 * @throws Exception If the input does not conform to the expected structure or does not end with EOF.
	 */
	ArvoreSintatica parseProg() throws Exception
	{

		ArvoreSintatica resultado = Exp();
		Token tokenCorrente = scanner.getNextToken();
		if(tokenCorrente.token != TokenType.EOF) {
			//System.out.println("ERROR :" + tokenCorrente.token);
			throw (new Exception("Estava esperando: EOF"));
		}
		return resultado;
	 
	}

	/**
	 * Analyzes and parses an expression from the input source using recursive descent.
	 * This method determines whether the input represents a valid numerical expression or
	 * a compound expression enclosed in parentheses with an operator.
	 *
	 * If the token is a number, it directly parses and returns a `Num` object.
	 * If the token starts with an opening parenthesis, it sequentially:
	 * - Parses the first sub-expression.
	 * - Identifies an operator.
	 * - Parses a second sub-expression.
	 * - Verifies that the closing parenthesis exists.
	 * - Constructs and returns an `Operador` object with the parsed components.
	 *
	 * @return An instance of `Exp`, either a `Num` for a simple number or an `Operador` for a compound expression.
	 * @throws Exception If the input does not match the expected grammar structure or an expected token is missing.
	 */
	Exp Exp() throws Exception
	{
		Exp exp1, exp2;
		Token tokenCorrente =  scanner.getNextToken();

		//System.out.println(tokenCorrente.token);

		if(tokenCorrente.token == TokenType.NUM){
			String lexema = tokenCorrente.lexema.replace(',', '.');
			return new Num(Double.parseDouble(lexema));
		}

		if(tokenCorrente.token == TokenType.APar)
			{
				exp1 = Exp();
				if(exp1 == null)
					throw (new Exception("Não encontrei expressão!"));
				
				Operador op = Op ();

				if (op == null)
					throw (new Exception("Não encontrei operador!"));

				exp2 = Exp();
				if(exp2 == null)
					throw (new Exception("Não enconrtrei expressão!"));	
				
				op.arg1 = exp1;
				op.arg2 = exp2;
				tokenCorrente =  scanner.getNextToken();
				if(tokenCorrente.token != TokenType.FPar)
					throw (new Exception("Estava esperando:)"));
				return op;
								
			} else throw (new Exception ("Estava esperando: ( ou <NUM>"));

		//return null;
		
	}

	/**
	 * Parses the current token from the scanner to recognize and return an operator.
	 *
	 * This method retrieves the next token using the scanner and attempts to match
	 * it to a known operator type (e.g., addition or multiplication). If the token
	 * corresponds to the `+` operator, a `Soma` object is created. If it corresponds
	 * to the `*` operator, a `Mult` object is created. If no matching operator is found,
	 * the method returns null.
	 *
	 * @return An instance of `Operador`, specifically either `Soma` or `Mult`, if a valid operator token is identified.
	 *         Returns null if no valid operator token is found.
	 * @throws Exception If there is an issue retrieving the next token from the scanner or
	 *                   if the token sequence is invalid.
	 */
	Operador Op () throws Exception
		{
		
		Token tokenCorrente = scanner.getNextToken();
		switch(tokenCorrente.token){
			case SOMA:
				return new Soma(null,null);
			case SUB:
				return new Sub(null,null);
			case MULT:
				return new Mult(null,null);
			case DIV:
				return new Div(null,null);
			default: 
		}
		return null;
			

		}

}
