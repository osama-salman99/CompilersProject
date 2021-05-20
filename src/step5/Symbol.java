package step5;

public class Symbol {
	private final String name;
	private final String type;
	private final String value;
	private boolean constant;

	public Symbol(String type, String name) {
		this(type, name, null);
	}

	public Symbol(String type, String name, String value) {
		this.name = name;
		this.type = type;
		this.value = value;
		this.constant = false;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public boolean isConstant() {
		return constant;
	}

	public void setConstant(boolean constant) {
		this.constant = constant;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("name").append(" ").append(name).append(" ");
		stringBuilder.append("type").append(" ").append(type).append(" ");
		if (value != null) {
			stringBuilder.append("value").append(" ").append(value).append(" ");
		}
		return stringBuilder.toString().trim();
	}
}
