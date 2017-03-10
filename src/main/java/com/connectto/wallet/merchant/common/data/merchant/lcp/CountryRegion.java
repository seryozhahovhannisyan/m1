package com.connectto.wallet.merchant.common.data.merchant.lcp;

import java.util.HashSet;
import java.util.Set;

public enum CountryRegion {

    ALABAMA(1, "ALABAMA", "AL", 1),
    ALASKA(2, "ALASKA", "AK", 1),
    ARIZONA(3, "ARIZONA", "AR", 1),
    ARKANSAS(4, "ARKANSAS", "AZ", 1),
    CALIFORNIA(5, "CALIFORNIA", "CA", 1),
    COLORADO(6, "COLORADO", "CO", 1),
    CONNECTICUT(7, "CONNECTICUT", "CT", 1),
    DELAWARE(8, "DELAWARE", "DE", 1),
    FLORIDA(9, "FLORIDA", "FL", 1),
    GEORGIA(10, "GEORGIA", "GA", 1),
    HAWAII(11, "HAWAII", "HI", 1),
    IDAHO(12, "IDAHO", "ID", 1),
    ILLINOIS(13, "ILLINOIS", "IL", 1),
    INDIANA(14, "INDIANA", "IN", 1),
    IOWA(15, "IOWA", "IA", 1),
    KANSAS(16, "KANSAS", "KS", 1),
    KENTUCKY(17, "KENTUCKY", "KY", 1),
    LOUISIANA(18, "LOUISIANA", "LA", 1),
    MAINE(19, "MAINE", "ME", 1),
    MARYLAND(20, "MARYLAND", "MD", 1),
    MASSACHUSETTS(21, "MASSACHUSETTS", "MA", 1),
    MICHIGAN(22, "MICHIGAN", "MI", 1),
    MINNESOTA(23, "MINNESOTA", "MN", 1),
    MISSISSIPPI(24, "MISSISSIPPI", "MO", 1),
    MISSOURI(25, "MISSOURI", "MS", 1),
    MONTANA(26, "MONTANA", "MT", 1),
    NEBRASKA(27, "NEBRASKA", "NE", 1),
    NEVADA(28, "NEVADA", "NV", 1),
    NEW_HAMPSHIRE(29, "NEW HAMPSHIRE", "NH", 1),
    NEW_JERSEY(30, "NEW JERSEY", "NJ", 1),
    NEW_MEXICO(31, "NEW MEXICO", "NM", 1),
    NEW_YORK(32, "NEW YORK", "NY", 1),
    NORTH_CAROLINA(33, "NORTH CAROLINA", "NC", 1),
    NORTH_DAKOTA(34, "NORTH DAKOTA", "ND", 1),
    OHIO(35, "OHIO", "OH", 1),
    OKLAHOMA(36, "OKLAHOMA", "OK", 1),
    OREGON(37, "OREGON", "OR", 1),
    PENNSYLVANIA(38, "PENNSYLVANIA", "PA", 1),
    RHODE_ISLAND(39, "RHODE ISLAND", "RI", 1),
    SOUTH_CAROLINA(40, "SOUTH CAROLINA", "SC", 1),
    SOUTH_DAKOTA(41, "SOUTH DAKOTA", "SD", 1),
    TENNESSEE(42, "TENNESSEE", "TN", 1),
    TEXAS(43, "TEXAS", "TX", 1),
    UTAH(44, "UTAH", "UT", 1),
    VERMONT(45, "VERMONT", "VT", 1),
    VIRGINIA(46, "VIRGINIA", "VA", 1),
    WASHINGTON(47, "WASHINGTON", "WA", 1),
    WASHINGTON_D_C(48, "WASHINGTON D.C.", "DC", 1),
    WEST_VIRGINIA(49, "WEST VIRGINIA", "WV", 1),
    WISCONSIN(50, "WISCONSIN", "WI", 1),
    WYOMING(51, "WYOMING", "WI", 1),
    ARAGATSOTN(52, "Aragatsotn", "", 2),
    ARARAT(53, "Ararat", "", 2),
    ARMAVIR(54, "Armavir", "", 2),
    GEXARQUNIK(55, "Gegharkunik", "", 2),
    KOTAYK(56, "Kotayk", "", 2),
    LORI(57, "Lori", "", 2),
    SHIRAK(58, "Shirak", "", 2),
    SYUNIK(59, "Syunik", "", 2),
    TAVUSH(60, "Tavush", "", 2),
    VAYOTS_DZOR(61, "Vayots Dzor", "", 2),
    YEREVAN(62, "Yerevan", "", 2);

    CountryRegion(int id, String name, String abr, int countryId) {
        this.id = id;
        this.name = name;
        this.abr = abr;
        this.countryId = countryId;
    }

    public static synchronized CountryRegion valueOf(final int id) {
        for (CountryRegion e : CountryRegion.values()) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }

    public static synchronized Set<CountryRegion> countryOf(final int countryId) {
        final Set<CountryRegion> countryRegions = new HashSet<CountryRegion>();
        for (CountryRegion e : CountryRegion.values()) {
            if (e.countryId == countryId) {
                countryRegions.add(e);
            }
        }
        return countryRegions;
    }

    private final int id;
    private final String name;
    private final String abr;
    private final int countryId;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAbr() {
        return abr;
    }

    public int getCountryId() {
        return countryId;
    }
}
