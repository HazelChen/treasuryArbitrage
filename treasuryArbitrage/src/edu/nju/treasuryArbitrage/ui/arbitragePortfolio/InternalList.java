package edu.nju.treasuryArbitrage.ui.arbitragePortfolio;

import java.util.ArrayList;
import java.util.Iterator;

import edu.nju.treasuryArbitrage.model.ArbGroup;

public class InternalList {
	private ArrayList<ArbitragePortfolioInternal> internals = new ArrayList<>();
	
	public void clear() {
		internals.clear();
	}
	
	public ArbitragePortfolioInternal get(int i) {
		return internals.get(i);
	}
	
	public boolean containsInt(ArbGroup arbGroup) {
		for(ArbitragePortfolioInternal internal : internals) {
			if (internal.getGroup().equals(arbGroup)) {
				return true;
			}
		}
		return false;
	}
	
	public void addInt(ArbGroup arbGroup) {
		ArbitragePortfolioInternal internal = new ArbitragePortfolioInternal(arbGroup);
		internals.add(internal);
	}
	
	public void removeExcess(ArrayList<ArbGroup> readTimeGroups) {
		Iterator<ArbitragePortfolioInternal> iterator = internals.iterator();
		while (iterator.hasNext()) {
			ArbitragePortfolioInternal internal = iterator.next();
			ArbGroup group = internal.getGroup();
			if (!readTimeGroups.contains(group)) {
				internal.clearActive();
			}
		}
	}

	public void update() {
		for (ArbitragePortfolioInternal internal : internals) {
			internal.update();
		}
	}
	
	public Iterator<ArbitragePortfolioInternal> iterator() {
		return internals.iterator();
	}

	public ArbitragePortfolioInternal get(ArbGroup arbGroup) {
		for(ArbitragePortfolioInternal internal : internals) {
			if (internal.getGroup().equals(arbGroup)) {
				return internal;
			}
		}
		return null;
	}
}
