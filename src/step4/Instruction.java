package step4;

public class Instruction {
	private final String opcode;
	private final Symbol operand1;
	private final Symbol operand2;
	private final Symbol result;
	private final String label;

	/**
	 * @param opcode ADDI / ADDF / SUBI / SUBF / MULTI / MULTF / DIVI / DIVF
	 */
	public Instruction(String opcode, Symbol operand1, Symbol operand2, Symbol result) {
		this(opcode, operand1, operand2, result, null);
	}

	/**
	 * @param opcode GT / GE / LT / LE / NE / EQ
	 */
	public Instruction(String opcode, Symbol operand1, Symbol operand2, String label) {
		this(opcode, operand1, operand2, null, label);
	}

	/**
	 * @param opcode JUMP / LABEL
	 */
	public Instruction(String opcode, String label) {
		this(opcode, null, null, null, label);
	}

	/**
	 * @param opcode READI / READF / WRITEI / WRITEF / WRITES / RETURN
	 */
	public Instruction(String opcode, Symbol result) {
		this(opcode, null, null, result, null);
	}

	/**
	 * @param opcode STOREI / STOREF
	 */
	public Instruction(String opcode, Symbol operand1, Symbol result) {
		this(opcode, operand1, null, result, null);
	}

	private Instruction(String opcode, Symbol operand1, Symbol operand2, Symbol result, String label) {
		this.opcode = opcode;
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.result = result;
		this.label = label;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(opcode).append("\t");
		if (opcode.length() < 4) {
			stringBuilder.append("\t");
		}
		if (operand1 != null) {
			String toAppend;
			if (operand1.isConstant()) {
				toAppend = operand1.getValue();
			} else {
				toAppend = operand1.getName();
			}
			stringBuilder.append(toAppend).append("\t");
			if (toAppend.length() < 4) {
				stringBuilder.append("\t");
			}
		}
		if (operand2 != null) {
			String toAppend;
			if (operand2.isConstant()) {
				toAppend = operand2.getValue();
			} else {
				toAppend = operand2.getName();
			}
			stringBuilder.append(toAppend).append("\t");
			if (toAppend.length() < 4) {
				stringBuilder.append("\t");
			}
		}
		if (result != null) {
			stringBuilder.append(result.getName()).append("\t");
		}
		if (label != null) {
			stringBuilder.append(label).append("\t");
		}
		return stringBuilder.toString().trim();
	}
}
