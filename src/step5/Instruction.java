package step5;

import java.util.ArrayList;
import java.util.List;

public class Instruction {
	private final String opcode;
	private final Symbol operand1;
	private final Symbol operand2;
	private final Symbol result;
	private final String label;
	private final List<TinyInstruction> tinyInstructions;

	/**
	 * @param opcode ADDI / ADDF / SUBI / SUBF / MULTI / MULTF / DIVI / DIVF
	 */
	public Instruction(String opcode, Symbol operand1, Symbol operand2, Symbol result) {
		this(opcode, operand1, operand2, result, null);
		switch (opcode) {
			case "ADDI":
				tinyInstructions.add(new TinyInstruction("move", operand1.getTinyName(), result.getTinyName()));
				tinyInstructions.add(new TinyInstruction("addi", operand2.getTinyName(), result.getTinyName()));
				break;
			case "ADDF":
				tinyInstructions.add(new TinyInstruction("move", operand1.getTinyName(), result.getTinyName()));
				tinyInstructions.add(new TinyInstruction("addr", operand2.getTinyName(), result.getTinyName()));
				break;
			case "SUBI":
				tinyInstructions.add(new TinyInstruction("move", operand1.getTinyName(), result.getTinyName()));
				tinyInstructions.add(new TinyInstruction("subi", operand2.getTinyName(), result.getTinyName()));
				break;
			case "SUBF":
				tinyInstructions.add(new TinyInstruction("move", operand1.getTinyName(), result.getTinyName()));
				tinyInstructions.add(new TinyInstruction("subf", operand2.getTinyName(), result.getTinyName()));
				break;
			case "MULTI":
				tinyInstructions.add(new TinyInstruction("move", operand1.getTinyName(), result.getTinyName()));
				tinyInstructions.add(new TinyInstruction("muli", operand2.getTinyName(), result.getTinyName()));
				break;
			case "MULTF":
				tinyInstructions.add(new TinyInstruction("move", operand1.getTinyName(), result.getTinyName()));
				tinyInstructions.add(new TinyInstruction("mulr", operand2.getTinyName(), result.getTinyName()));
				break;
			case "DIVI":
				tinyInstructions.add(new TinyInstruction("move", operand1.getTinyName(), result.getTinyName()));
				tinyInstructions.add(new TinyInstruction("divi", operand2.getTinyName(), result.getTinyName()));
				break;
			case "DIVF":
				tinyInstructions.add(new TinyInstruction("move", operand1.getTinyName(), result.getTinyName()));
				tinyInstructions.add(new TinyInstruction("divr", operand2.getTinyName(), result.getTinyName()));
				break;
		}
	}

	/**
	 * @param opcode GT / GE / LT / LE / NE / EQ
	 */
	public Instruction(String opcode, Symbol operand1, Symbol operand2, String label) {
		this(opcode, operand1, operand2, null, label);
		String operand2TinyName = operand2.getTinyName();
		if (operand2.isConstant() || !operand2.isRegister()) {
			operand2TinyName = "r" + (TinyInstruction.getRegisterCount() + 1);
			tinyInstructions.add(new TinyInstruction("move", operand2.getName(), operand2TinyName));
		}
		if (operand1.getType().equals("INT")) {
			tinyInstructions.add(new TinyInstruction("cmpi", operand1.getTinyName(), operand2TinyName));
		} else {
			tinyInstructions.add(new TinyInstruction("cmpr", operand1.getTinyName(), operand2TinyName));
		}
		tinyInstructions.add(new TinyInstruction("j" + opcode.toLowerCase(), label));
	}

	/**
	 * @param opcode JUMP / LABEL
	 */
	public Instruction(String opcode, String label) {
		this(opcode, null, null, null, label);
		switch (opcode) {
			case "JUMP":
				tinyInstructions.add(new TinyInstruction("jmp", label));
				break;
			case "LABEL":
				tinyInstructions.add(new TinyInstruction("label", label));
				break;
		}
	}

	/**
	 * @param opcode READI / READF / WRITEI / WRITEF / WRITES / RETURN / DECLV / DECLS
	 */
	public Instruction(String opcode, Symbol result) {
		this(opcode, null, null, result, null);
		switch (opcode) {
			case "READI":
				tinyInstructions.add(new TinyInstruction("sys readi", result.getTinyName()));
				break;
			case "READF":
				tinyInstructions.add(new TinyInstruction("sys readr", result.getTinyName()));
				break;
			case "WRITEI":
				tinyInstructions.add(new TinyInstruction("sys writei", result.getTinyName()));
				break;
			case "WRITEF":
				tinyInstructions.add(new TinyInstruction("sys writer", result.getTinyName()));
				break;
			case "WRITES":
				tinyInstructions.add(new TinyInstruction("sys writes", result.getTinyName()));
				break;
			case "DECLV":
				tinyInstructions.add(new TinyInstruction("var", result.getTinyName()));
				break;
			case "DECLS":
				tinyInstructions.add(new TinyInstruction("str", result.getTinyName(), result.getValue()));
				break;
		}
	}

	/**
	 * @param opcode STOREI / STOREF
	 */
	public Instruction(String opcode, Symbol operand1, Symbol result) {
		this(opcode, operand1, null, result, null);
		tinyInstructions.add(new TinyInstruction("move", operand1.getTinyName(), result.getTinyName()));
	}

	private Instruction(String opcode, Symbol operand1, Symbol operand2, Symbol result, String label) {
		this.opcode = opcode;
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.result = result;
		this.label = label;
		this.tinyInstructions = new ArrayList<>();
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

	public List<TinyInstruction> getTinyInstructions() {
		return tinyInstructions;
	}
}
