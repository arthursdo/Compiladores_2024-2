/**
 * The Compilador class serves as the main entry point for a compiler program.
 * It performs lexical analysis, parsing, and code generation for a given input file.
 *
 * The program takes the path to a source file as a command-line argument, builds
 * an abstract syntax tree (AST), and generates the corresponding code representation.
 *
 * Main functionalities:
 * - Initializes the lexical analyzer (AnaliseLexica) to process the input file.
 * - Parses the input using the Parser to generate an abstract syntax tree (AST).
 * - Utilizes the CodeGen class to generate the corresponding code based on the AST.
 * - Outputs the generated code to the console.
 *
 * If any errors occur in the lexical analysis, parsing, or code generation phases,
 * an error message will be printed to the console.
 */
class Compilador{

	public static void main(String[]args)
	{	
		ArvoreSintatica arv=null;
	
		try{

			AnaliseLexica al = new AnaliseLexica(args[0]);
			Parser as = new Parser(al);
		
			arv = as.parseProg();
		
			
			CodeGen backend = new CodeGen();
			String codigo = backend.geraCodigo(arv);
			System.out.println(codigo);

		}catch(Exception e)
		{			
			System.out.println("Erro de compilação:\n" + e);
		}



	}
}
