package step4;

import java.util.ArrayList;
import java.util.List;

public class SymbolList {
	private final List<Symbol> symbols;

	public SymbolList() {
		this.symbols = new ArrayList<>();
	}

	public Symbol get(String symbolName) {
		for (Symbol symbol : symbols) {
			if (symbol != null && symbol.getName().equals(symbolName)) {
				return symbol;
			}
		}
		return null;
	}

	public void add(Symbol symbol) {
		symbols.add(symbol);
	}
}
