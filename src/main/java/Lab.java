

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.h2.jdbc.JdbcBlob;

import util.ConnectionUtil;
import util.H2Util;

/**
 * JDBC stands for Java DataBase Connectivity.  It is utilized to connect our java code with a database.
 * JDBC will allow us to execute SQL statements from java and retrieve the result set of that query to be utilized in java
 *
 * JDBC datatypes to know:
 *  - Connection: Creates an active connection to the database.
 *  - Statement: An object that represents a SQL statement to be executed.
 *  - ResultSet: An object that represents the virtual table return from a query (Only needed for executing DQL statements)
 *
 * Background:
 * Assume we have the following table:
 *      songs table
 *      |   id  |      title        |        artist         |
 *      -----------------------------------------------------
 *      |1      |'Let it be'        |'Beatles'              |
 *      |2      |'Hotel California' |'Eagles'               |
 *      |3      |'Kashmir'          |'Led Zeppelin'         |
 *
 * Assignment: Write JDBC logic in the methods below to achieve the following
 *                  - create a new song in our songs database table
 *                  - retrieve all songs from our database table
 *
 * If this is your first time working with JDBC, I recommend reading through the JDBCWalkthrough file that displays how to use JDBC for a similar scenario.
 */
public class Lab {

    /**
     * @param song
     * @throws SQLException
     */
    public void createSong(Song song) throws SQLException  {
        //write jdbc code here
        //H2Util.dropAllTables();
        //H2Util.generateTables();
        Connection con=ConnectionUtil.getConnection();
        String s="insert into songs(id,title,artist) values(?,?,?)";
        //String s="insert into songs(title,artist) values("+song.gettitle()+","+song.getArtist()+")";
        PreparedStatement st=con.prepareStatement(s);  
        st.setInt(1,song.getId());
        st.setString(2,song.gettitle());
        st.setString(3,song.getArtist());      
        //st.executeQuery(s);
    }

    public List<Song> getAllSongs() throws SQLException{
        List<Song> songs = new ArrayList<>();

        //write jdbc code here
        Connection con=ConnectionUtil.getConnection();
        Statement st=con.createStatement();
        String s="select * from songs";
        ResultSet rs=st.executeQuery(s);
        while(rs.next()){
            Song a=new Song(rs.getInt(1),rs.getString(2),rs.getString(3));
            songs.add(a);
        }
        return songs;
    }
}
