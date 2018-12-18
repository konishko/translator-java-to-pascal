package readers;

import baseReader.BaseReader;
import lexer.Lexer;
import token.Token;

public class JavaNewVarReader extends BaseReader {
    public JavaNewVarReader(){
        super();
        this.setStates(4);
        this.setState(0);
        this.setType("java_new_var_reader");
    }

    protected Token correctType(String string){
        return new Token("java_new_var_reader", string);
    }

    public Token tryReadToken(String string){
        String potentialToken = string;
        Token[] childTokens = new Token[0];
        int tokenLength = 0;
        while(this.getState() != this.getStates()){
            if(this.getState() == 0 & Character.isLetterOrDigit(potentialToken.charAt(0))) {
                this.setState(1);
            }

            else if(this.getState() == 1) {
                if(Character.isLetterOrDigit(potentialToken.charAt(0)))
                    this.setState(1);

                else if(Character.isWhitespace(potentialToken.charAt(0))) {
                    this.setState(2);
                }
            }

            else if(this.getState() == 2 & Character.isLetterOrDigit(potentialToken.charAt(0))){
                this.setState(3);
            }

            else if(this.getState() == 3) {
                if(Character.isLetterOrDigit(potentialToken.charAt(0)))
                    this.setState(3);

                else if(potentialToken.startsWith(" = ")) {
                    potentialToken = potentialToken.substring(3);
                    tokenLength += 3;
                    this.setState(3);
                    continue;
                }
                else if(potentialToken.charAt(0) == ';')
                    this.setState(4);
            }

            else
                return null;

            tokenLength++;
            potentialToken = potentialToken.substring(1);
        }
        this.setState(0);
        String result = string.substring(0, tokenLength);
        Token token = this.correctType(result);
        token.setChilds(childTokens);
        return token;
    }

}
