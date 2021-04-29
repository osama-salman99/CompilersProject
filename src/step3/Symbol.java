package step3;

public class Symbol {
	public static final String STRING = "STRING";
	private final String name;
	private final String type;
	private final String value;

	public Symbol(String type, String name) {
		this(type, name, null);
	}

	public Symbol(String type, String name, String value) {
		this.name = name;
		this.type = type;
		this.value = value;
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
}
