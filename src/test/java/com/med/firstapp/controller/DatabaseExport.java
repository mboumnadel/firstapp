package com.med.firstapp.controller;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class DatabaseExport {

    public static void main(String[] args) throws Exception
    {
        // database connection
        Class driverClass = Class.forName("org.mariadb.jdbc.Driver");

        Connection jdbcConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/classicmodels", "root", "");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        // partial database export
        QueryDataSet partialDataSet = new QueryDataSet(connection);
        partialDataSet.addTable("vehicle", "SELECT * FROM vehicle LIMIT 10 ");
        //partialDataSet.addTable("vehicle");
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream("vehicle-first-10.xml"));

        // full database export
        /*
        IDataSet fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("full.xml"));
        */

        // dependent tables database export: export table X and all tables that
        // have a PK which is a FK on X, in the right order for insertion
        /*
        String[] depTableNames = TablesDependencyHelper.getAllDependentTables( connection, "X" );
        IDataSet depDataset = connection.createDataSet( depTableNames );
        FlatXmlDataSet.write(depDataset, new FileOutputStream("dependents.xml"));
        */


        // write DTD file
        //FlatDtdDataSet.write(partialDataSet, new FileOutputStream("partial.dtd"));

    }
}