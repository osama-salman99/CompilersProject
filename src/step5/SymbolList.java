package step5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SymbolList implements Iterable<Symbol> {
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

	@Override
	public Iterator<Symbol> iterator() {
		return symbols.iterator();
	}

	@Override
	public void forEach(Consumer<? super Symbol> action) {
		symbols.forEach(action);
	}

	@Override
	public Spliterator<Symbol> spliterator() {
		return symbols.spliterator();
	}
}
