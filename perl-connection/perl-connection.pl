#!/usr/bin/perl

#   $Id: $
#   =======================================================================================
#
    use strict;
    use warnings;
    use DBD::Oracle qw(:ora_types :ora_fetch_orient :ora_exe_modes);

    ###############################################################
    #   This script tries to connect to our local oracle database.
    #
    #   @author     Christopher Stock
    #   @version    0.0.1
    ###############################################################
   
    # acclaim 
    print( "\nThis script tries to connect to the local oracle DBMS.\n\n" );

    # specify the db login credentials
    my $JDBC_URL    = "dbi:Oracle:host=localhost;sid=xe"; # "jdbc:oracle:thin:\@localhost:1521:xe";
    my $USER_ID     = "chris";
    my $PASSWORD    = "test";

    # connect
	my $dbConnection = DBI->connect( $JDBC_URL, $USER_ID, $PASSWORD );
	
	# notify a success connection 
	print( " Connection succeeded.\n" );
	
	# perform a query
    my $sql = "SELECT * FROM DEMO_USERS";
    my $statement = $dbConnection->prepare( $sql,{ora_exe_mode=>OCI_STMT_SCROLLABLE_READONLY} );
    $statement->execute();
    print( " Performing the query succeeded!\n\n" );
    
    # specify the variable that holds the result set
    my $value;
    
    # pick the 1st (next) result set
    $value = $statement->ora_fetch_scroll( OCI_FETCH_NEXT, 0 );
    print " name [" . $value->[ 1 ] . "]\n";
    
    # pick the next result set
    $value = $statement->ora_fetch_scroll( OCI_FETCH_NEXT, 0 );
    print " name [" . $value->[ 1 ] . "]\n";

    # close resources
    $statement->finish();
    $dbConnection->disconnect();
    print "\n Connection to DB successfully closed.\n";
