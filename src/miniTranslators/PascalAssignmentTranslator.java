package miniTranslators;

import baseMiniTranslator.BaseMiniTranslator;
import java.util.HashMap;
import java.util.Map;
import token.Token;

public class PascalAssignmentTranslator extends BaseMiniTranslator {
    public PascalAssignmentTranslator(){
        super();
        this.type = "pascal_assignment";
    }

    public Token toPseudo(Token token){
        String value = (String)token.getValue();

        String[] parsedValue = value.split(" ");
        Map<String, String> pseudoTokenValue = new HashMap<String, String>();

        pseudoTokenValue.put("var name", parsedValue[0]);
        pseudoTokenValue.put("var value", parsedValue[2]);

        Token pseudoToken = new Token("pseudo_assignment", token.getText(), pseudoTokenValue);
        return pseudoToken;
    }

    public Token fromPseudo(Token token){
        Map<String, String> value = (Map<String, String>)token.getValue();

        String tokenValue = String.format("% := %", value.get("var name"), value.get("var value"));
        String tokenText = String.format("%;", tokenValue);

        Token javaToken = new Token("pascal_assignment", tokenText, tokenValue);
        return javaToken;
    }
}
