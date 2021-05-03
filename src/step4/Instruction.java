package step4;

public class Instruction {
	private final String opCode;
	private final Symbol operand1;
	private final Symbol operand2;
	private final Symbol result;
	private final String label;

	/**
	 * @param opCode ADDI / ADDF / SUBI / SUBF / MULTI / MULTF / DIVI / DIVF
	 */
	public Instruction(String opCode, Symbol operand1, Symbol operand2, Symbol result) {
		this(opCode, operand1, operand2, result, null);
	}

	/**
	 * @param opCode GT / GE / LT / LE / NE / EQ
	 */
	public Instruction(String opCode, Symbol operand1, Symbol operand2, String label) {
		this(opCode, operand1, operand2, null, label);
	}

	/**
	 * @param opCode JUMP / LABEL
	 */
	public Instruction(String opCode, String label) {
		this(opCode, null, null, null, label);
	}

	/**
	 * @param opCode READI / READF / WRITEI / WRITEF / WRITES
	 */
	public Instruction(String opCode, Symbol result) {
		this(opCode, null, null, result, null);
	}

	private Instruction(String opCode, Symbol operand1, Symbol operand2, Symbol result, String label) {
		this.opCode = opCode;
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.result = result;
		this.label = label;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(opCode).append(" ");
		if (operand1 != null) {
			stringBuilder.append(operand1.getName()).append(" ");
		}
		if (operand2 != null) {
			stringBuilder.append(operand2.getName()).append(" ");
		}
		if (result != null) {
			stringBuilder.append(result.getName()).append(" ");
		}
		if (label != null) {
			stringBuilder.append(label).append(" ");
		}
		return stringBuilder.toString().trim();
	}
}
