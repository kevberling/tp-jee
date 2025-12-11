package fr.ig2i.calculator;

public class CalculEntity implements java.io.Serializable {
	
	public enum TypeOperation {
		ADDITION,
		SOUSTRACTION
	}
	
	private TypeOperation operation;
	
	private String value1;
	
	private String value2;
	
	public CalculEntity(TypeOperation operation, String value1, String value2) {
		super();
		this.operation = operation;
		this.value1 = value1;
		this.value2 = value2;
	}

	public TypeOperation getOperation() {
		return operation;
	}

	public void setOperation(TypeOperation operation) {
		this.operation = operation;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

}
