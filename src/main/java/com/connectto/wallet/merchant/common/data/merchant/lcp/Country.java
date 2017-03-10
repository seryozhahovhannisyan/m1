package com.connectto.wallet.merchant.common.data.merchant.lcp;

import java.util.Locale;
import java.util.Set;

public enum Country {

    USA(1, "USA" ,"us", "1" ),
    ARMENIA(2, "Armenia","am", "374" );


    Country(int id, String name, String abr, String phoneCode) {
        this.id = id;
        this.name = name;
        this.abr = abr;
        this.phoneCode = phoneCode;
        this.regions =  CountryRegion.countryOf(id);
    }

    public static synchronized Country valueOf(final int id) {
        for (Country e : Country.values()) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAbr() {
        return abr;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public Set<CountryRegion> getRegions() {
        return regions;
    }

    private final  int id;
    private final String name;
    private final String abr;
    private final String phoneCode;
    private final Set<CountryRegion> regions;

}
