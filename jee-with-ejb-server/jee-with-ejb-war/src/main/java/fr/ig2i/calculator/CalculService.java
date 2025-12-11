package fr.ig2i.calculator;

import fr.ig2i.calculator.CalculEntity.TypeOperation;
import fr.ig2i.calculator.exceptions.MissingInputException;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class CalculService {
	
	public int calculOperation(TypeOperation operande, String param1, String param2) throws MissingInputException {

		if (param1 == null || param1.isEmpty() || param2 == null || param2.isEmpty()) {
			throw new MissingInputException();
		}

		int int1 = Integer.parseInt(param1);
		int int2 = Integer.parseInt(param2);

        return switch (operande) {
            case ADDITION -> CalculUtils.addition(int1, int2);
            case SOUSTRACTION -> CalculUtils.soustraction(int1, int2);
        };
	}

}
