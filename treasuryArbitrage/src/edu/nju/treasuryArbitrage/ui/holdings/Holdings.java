package edu.nju.treasuryArbitrage.ui.holdings;

import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.ui.common.ComponentPanel;

public abstract class Holdings extends JPanel implements ComponentPanel{
	private static final long serialVersionUID = -7670729735591684976L;
	protected abstract void updateHistory();
	protected abstract void updateRepoList();
}
