package step4;

public class Symbol {
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
