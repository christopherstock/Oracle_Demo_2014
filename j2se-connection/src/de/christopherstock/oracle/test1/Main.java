/*  $Id: JARDesktop.java 194 2014-03-16 15:38:18Z jenetic.bytemare@googlemail.com $
 *  =======================================================================================
 */
    package de.christopherstock.oracle.test1;

    import  java.sql.*;
    import  oracle.jdbc.pool.*;

    /***********************************************************************************************
    *   The main class for the j2SE platform.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    ***********************************************************************************************/
    public class Main
    {
        private         static          final           String          JDBC_URL            = "jdbc:oracle:thin:@localhost:1521:xe";
        private         static          final           String          USER_ID             = "chris";
        private         static          final           String          PASSWORD            = "test";

        /***********************************************************************************************
        *   The entry point of the J2SE app.
        ***********************************************************************************************/
        public static void main( String[] args ) throws Throwable
        {
            System.out.println( "Welcome to the j2se-connection test for ORACLE." );

            System.out.println( "Trying to connect to the ORACLE EXPRESS DBMS.." );

            //specify the connection and the oracle data source
            Connection          conn    = null;
            OracleDataSource    ds      = new OracleDataSource();

            ds.setURL( JDBC_URL );

            conn = ds.getConnection( USER_ID, PASSWORD );

            //create a statement and execute a query on it
            Statement   s   = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE );
            ResultSet   rs  = s.executeQuery( "SELECT * FROM DEMO_USERS" );

            //browse all entries of the ResultSet
            while ( rs.next() )
            {
                //pick the value of the column 'user_name'
                String userName = rs.getString( "USER_NAME" );

                System.out.println( "Read column 'userName': [" + userName + "]" );
            }

            //release resources
            conn.close();
            System.out.println( "Connection to DB successfully closed." );
        }
    }
