/*
 * Copyright 2000-2015 Namics AG. All rights reserved.
 */

package com.namics.oss.spring.support.jpa;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * Strategy enforces namics style of table names in JPA mappings.
 * Override Method  <code>getTablePrefix</code> to set specific table prefix.
 *
 * @author bhelfenberger, Namics AG
 * @since 31.03.2015
 */
public class DatabaseNamingStrategy extends ImprovedNamingStrategy implements PhysicalNamingStrategy {

	private static final String TABLE_PREFIX = "";

	/**
	 * Method to be able to override the table name prefix.
	 *
	 * @return table name prefix, default is ""
	 */
	protected String getTablePrefix() {
		return TABLE_PREFIX;
	}


	/**
	 * Return the unqualified class name, mixed case converted to
	 * underscores
	 */
	@Override
	public String classToTableName(String className) {
		return getTablePrefix() + super.classToTableName(className);
	}


	/**
	 * Returns either the table name if explicit or
	 * if there is an associated table, the concatenation of owner entity table and associated table
	 * otherwise the concatenation of owner entity table and the unqualified property name
	 */
	@Override
	public String logicalCollectionTableName(String tableName, String ownerEntityTable, String associatedEntityTable, String propertyName) {
		return getTablePrefix() + super.logicalCollectionTableName(tableName, ownerEntityTable, associatedEntityTable, propertyName);
	}

	@Override
	public String collectionTableName(String ownerEntity, String ownerEntityTable, String associatedEntity, String associatedEntityTable,
	                                  String propertyName) {
		return getTablePrefix() + super.collectionTableName(ownerEntity, ownerEntityTable, associatedEntity, associatedEntityTable, propertyName);
	}

	@Override
	public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return name;
	}

	@Override
	public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return name;
	}

	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return new Identifier(getTablePrefix() + super.classToTableName(name.getText()), false);
	}

	@Override
	public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return name;
	}

	@Override
	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return new Identifier(super.propertyToColumnName(name.getText()), false);
	}
}
