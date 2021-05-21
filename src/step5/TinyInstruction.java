package step5;

import java.util.regex.Pattern;

public class TinyInstruction {
	private static final Pattern registerPattern = Pattern.compile("r[0-9]+$");
	private static int registerCount = 0;
	private final String opcode;
	private final String operand1;
	private final String operand2;

	public TinyInstruction(String opcode, String operand1, String operand2) {
		this.opcode = opcode;
		this.operand1 = operand1;
		this.operand2 = operand2;
		updateRegisterCount(operand1);
		updateRegisterCount(operand2);
	}

	public TinyInstruction(String opcode, String operand1) {
		this(opcode, operand1, null);
	}

	public static int getRegisterCount() {
		return registerCount;
	}

	private void updateRegisterCount(String operand) {
		if (operand == null) {
			return;
		}
		if (registerPattern.matcher(operand).matches()) {
			int number = Integer.parseInt(operand.replace("r", ""));
			registerCount = Math.max(registerCount, number);
		}
	}

	@Override
	public String toString() {
		return opcode + " " + operand1 + (operand2 != null ? " " + operand2 : "");
	}
}
