package com.connectto.wallet.merchant.common.data.merchant.lcp;

/**
 * Created by htdev001 on 3/7/14.
 */
public enum Privilege {

    ALL                 (1, "Super admin", "All", "Chairman of the Supervisory Council"),
    CRUD_COMPANY        (2, "Company admin", "crud company", "Allow to create branches and cashiers allowed manage company and branch's data"),
    CRUD_BRANCH         (3, "Branch admin",  "crud branch",  "Allow to create cashiers allowed manage branch's data"),
    CRUD_CASHIER        (4, "Cashier admin", "crud cashier", "Allow to create cashiers allowed manage others cashier's data"),
    CASHIER             (5, "Simple cashier","cashier", "Allow to make transactions");

    Privilege(int id, String name, String privilege, String comment) {
        this.id = id;
        this.name = name;
        this.privilege = privilege;
        this.comment = comment;
    }

    public static Privilege getDefault() {
        return null;
    }

    public static synchronized Privilege valueOf(final int id) {
        for (Privilege privilege : Privilege.values()) {
            if (privilege.getId() == id) {
                return privilege;
            }
        }
        return getDefault();
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrivilege() {
        return privilege;
    }

    public String getComment() {
        return comment;
    }

    private int id;
    private String name;
    private String privilege;
    private String comment;

}
