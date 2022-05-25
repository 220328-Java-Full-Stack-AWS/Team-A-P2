package com.revature.ECommerce.entities;

/**
 * This Class simply holds the name of the schema for testing purposes
 * For the schema to be added to, the schema with the specified name must exist in the database.
 */
public class _SchemaName {
    public final static String jarodSchema = "jh";
    public final static String publicSchema = "public";
    public final static String devopsSchema = "devops";
    public final static String stanSchema = "sr";

    /** This is the variable that holds the current schema. Changing it here will change it in all entities. */
    public final static String schemaName = devopsSchema;
//    public final static String schemaName = publicSchema;
}
