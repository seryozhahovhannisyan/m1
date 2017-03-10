package com.connectto.wallet.merchant.web.action;

import com.connectto.wallet.merchant.web.util.PagingParameters;

public class BasePagingAction extends BaseAction implements PagingParameters {

    /**
     * Elements count
     */
    protected int count;

    /**
     * Max number of pages
     */
    protected int maxPage;

    /**
     * Current number of page
     */
    protected int page;

    /**
     * Returns current number of page
     * @return int
     */
    public int getPage() {
        return page + 1;
    }

    /**
     * Sets page
     * @param strPage
     */
    public void setPage(String strPage) {
        try {
            this.page = Integer.parseInt(strPage);
        } catch (Exception e) {}
    }

    /**
     * Returns max number of pages
     * @return int
     */
    public int getMaxPage() {
        return maxPage;
    }

    /**
     * Returns first link number of navigator
     * @return int
     */
    public int getLinkFirstNumber() {
        return (page / LINK_COUNT_FOR_NAVIGATION) * LINK_COUNT_FOR_NAVIGATION + 1;
    }

    /**
     * Returns last link number of navigator
     * @return int
     */
    public int getLinkLastNumber() {

        int lastLinkNumber = getLinkFirstNumber() - 1 + LINK_COUNT_FOR_NAVIGATION;
        if (lastLinkNumber > maxPage) {
            lastLinkNumber = maxPage;
        }
        return lastLinkNumber;
    }

    /**
     * Returns true if there is previous page, otherwise false
     * @return boolean
     */
    public boolean isHasPrevious() {
        return page > 0;
    }

    /**
     * Returns true if there is next page, otherwise false
     * @return boolean
     */
    public boolean isHasNext() {
        return page + 1 < maxPage;
    }
}
