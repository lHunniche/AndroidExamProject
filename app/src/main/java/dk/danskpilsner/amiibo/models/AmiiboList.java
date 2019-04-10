package dk.danskpilsner.amiibo.models;

import java.util.List;

/**
 * The API encapsulates list results into an amiibo list
 */
public class AmiiboList {
    private List<Amiibo> amiibo;

    public List<Amiibo> getAmiibo() {
        return amiibo;
    }

    public void setAmiibo(List<Amiibo> amiibo) {
        this.amiibo = amiibo;
    }
}
