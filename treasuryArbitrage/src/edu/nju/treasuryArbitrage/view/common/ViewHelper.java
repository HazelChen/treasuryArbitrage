package edu.nju.treasuryArbitrage.view.common;

/**
 * Created by Hazel on 2015/3/16.
 */
public class ViewHelper {

    private static ViewHelper self;

    private ViewHelper(){}

    public static ViewHelper getInstance() {
        if (self == null) {
            self = new ViewHelper();
            self.init();
        }
        return self;
    }

    /**
     * when views started, 'canViewUpdate' will be set true.
     */
    private boolean canViewUpdate = false;
    private boolean canHoldingsUpdate = false;

    private ViewFactory viewFactory;

    public void init() {
        viewFactory = ViewFactory.getInstance();
        viewFactory.init();
        viewFactory.getFuturesMarket().initPoint();
        viewFactory.getArbitragePortfolio().initPoint();
        System.out.println("View helper ready!");
    }

    public void updateViews() {
        if (!canViewUpdate) {
            return;
        }

        viewFactory.getFuturesMarket().updatePage();
        viewFactory.getArbitragePortfolio().updatePage();
        if (canHoldingsUpdate) {
            viewFactory.getHoldings().liveUpdate();
        }
    }

    public void startUpdate() {
        this.canViewUpdate = true;
    }

    public void startUpdateHoldings() {
        this.canHoldingsUpdate = true;
    }
}
