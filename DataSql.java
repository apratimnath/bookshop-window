package datacon;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DataSql
{
Connection con;
Statement st;
PreparedStatement st1,st2;
ResultSet rs,rs1,rs2;
int port = 0;
int userchoice = 0;
String kds = null;
String schemaname = null;
String username = null;
String password = null;

public DataSql ()throws IOException
{
BufferedReader br = new BufferedReader (new InputStreamReader (System.in));

System.out.printf ("Hey user!\nAre you new to this system or are you willing to modify an existing one?\nPress 1 to add a new system.\nPress 2 to modify existing system.\n\nPlease enter your choice : ");
kds = br.readLine ();
userchoice = Integer.parseInt (kds);

if (userchoice == 1)
{
System.out.print ("Enter Port number : ");
kds = br.readLine ();
port = Integer.parseInt (kds);

System.out.print ("Enter Schema Name : ");
schemaname = br.readLine ();

System.out.print ("Enter User Name : ");
username = br.readLine ();

System.out.print ("Enter Password : ");
password = br.readLine ();

try
{
Class.forName ("com.mysql.jdbc.Driver");
con = DriverManager.getConnection ("jdbc:mysql://localhost:" + port + "/" + schemaname + "?user=" + username + "&password=" + password);

st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

System.out.println ("Database connectivity successfully completed.");
System.out.println ();
}

catch (Exception e)
{
System.out.println (e);
}
}

if (userchoice ==2 )
{
try
{
Class.forName ("com.mysql.jdbc.Driver");
con = DriverManager.getConnection ("jdbc:mysql://localhost:3306/bookdata?user=root&password=apratim");

st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

System.out.println ("Database connectivity successfully completed.");
System.out.println ();
}

catch (Exception e)
{
System.out.println (e);
}
}

}

public ResultSet show (String s1)
{
try
{
rs = st.executeQuery (s1);
}
catch (Exception e)
{
System.out.println (e);
}

return (rs);

}

public int inupdel (String s2)
{
int i = 0;

try
{
i = st.executeUpdate (s2);
}
catch (Exception e)
{
System.out.println (e);
} 

return (i);

}

public ResultSet searchBookID (int bid)
{
try
{
st1 = con.prepareStatement ("select * from bookdetails where BookID=?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

st1.setInt (1,bid);
rs1 = st1.executeQuery();
}
catch (Exception e)
{
System.out.println (e);
}

return (rs1);
}

public ResultSet searchName (String name)
{
try
{
st2 = con.prepareStatement ("select * from bookdetails where Name = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

st2.setString (1,name);
rs2 = st2.executeQuery();

}
catch (Exception e)
{
System.out.println (e);
}

return (rs2);
}

public ResultSet searchPublisher (String publisher)
{
try
{
st1 = con.prepareStatement ("select * from bookdetails where Publisher=?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

st1.setString (1,publisher);
rs = st1.executeQuery();
}
catch (Exception e)
{
System.out.println (e);
}

return (rs);
}

}
