package com.company.rest;

public class Const {
    public static final String Table="rentdata";
    public static final String IDTable="id";
    public static final String SIZETable="Size";
    public static final String MONTHTable="Month";
    public static final String YEARSTable="Year";
    public static final String MONEYTable="Money";
    public static final String Create = "CREATE TABLE rentdata "
            + "(id INTEGER not NULL AUTO_INCREMENT, "
//                    + " first VARCHAR(255), "
//                    + " Month VARCHAR(255), "
            + " Year INTEGER, "
            + " Month INTEGER, "
            + " Size INTEGER, "
            + " Money INTEGER, "
            + " PRIMARY KEY ( id ))";
}
