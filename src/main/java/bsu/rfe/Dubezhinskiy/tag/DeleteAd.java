package bsu.rfe.java.group8.lab9.Dubezhinskiy.varA4.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import bsu.rfe.java.group8.lab9.Dubezhinskiy.varA4.entity.Ad;
import bsu.rfe.java.group8.lab9.Dubezhinskiy.varA4.entity.AdList;
import bsu.rfe.java.group8.lab9.Dubezhinskiy.varA4.entity.AdListHelper;
import bsu.rfe.java.group8.lab9.Dubezhinskiy.varA4.entity.User;

public class DeleteAd extends SimpleTagSupport {

    private Ad ad;

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public void doTag() throws JspException, IOException {
        String errorMessage = null;
        AdList adList = (AdList) getJspContext().getAttribute("ads", PageContext.APPLICATION_SCOPE);
        User currentUser = (User) getJspContext().getAttribute("authUser", PageContext.SESSION_SCOPE);

        if (currentUser==null || (ad.getId()>0 && ad.getAuthorId()!=currentUser.getId())) {
            errorMessage = "You are trying to change a message that you do not have permission to access!";
        }

        if (errorMessage==null) {
            adList.deleteAd(ad);
            AdListHelper.saveAdList(adList);
        }

        getJspContext().setAttribute("errorMessage", errorMessage,PageContext.SESSION_SCOPE);
    }
}