package com.revature.ECommerce.beans.controllers;

public class _CROSS_ORIGINS {
    private final String ALL = "*";
    public static final String LOCAL = "http://localhost:4200";
    public static final String BEANSTALK = "http://revtechteamap2-env-1.eba-x9awq5nc.us-west-1.elasticbeanstalk.com";
    public static final String BUCKET = "http://revtech-ui.s3-website-us-west-1.amazonaws.com/";

    public final static String ORIGINS = _CROSS_ORIGINS.LOCAL + ", " + _CROSS_ORIGINS.BUCKET;
}
