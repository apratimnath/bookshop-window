import datacon.*;
import java.io.*;
import java.util.Date;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class BookShop extends Frame implements WindowListener,ActionListener,ItemListener
{
Label l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23,l24,l25,l26,l27,l28,l29,l30;
TextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,t21,t22,t23,t24,t25,t26,t27,t28,t29,t30;
Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21,b22;
Choice ch1,ch2,ch3,ch4,ch5,ch6,ch7;
MenuBar mb;
Menu m1,m2,m3;
MenuItem mi1,mi2,mi3,mi4,mi5,mi6,mi7;
FileDialog fd;
Panel p1,p2,p3,p4,p5,p6,p7,p8;
DataSql d;
ResultSet rs1,rs2,rs3,rs4,rs5,rs6,rs7;
FileWriter fw = null;
String k = null;
String s1=null,s2=null,s3=null,s4=null,s5=null,s6=null,s7=null,s8=null,s9=null,s10=null;
int x = 0;
int a1=0,a2=0,a3=0,a4=0,a5=0,a6=0,a7=0,a8=0,a9=0,a10=0;
int count = 0;
static int flag = 0;
SimpleDateFormat dateformat;
Date date;

public BookShop ()
{
try
{
d = new DataSql ();
rs1 = d.show ("select * from bookdetails");
}
catch (Exception e)
{
System.out.println (e);
}

setLayout (new GridLayout (8,1));
setBackground (Color.cyan);
setSize (800,800);
setTitle ("Book Shop Management System");

addWindowListener (this);

mb = new MenuBar ();
setMenuBar (mb);

m1 = new Menu ("File Options");mb.add (m1);
m2 = new Menu ("Database Options");mb.add (m2);
m3 = new Menu ("Help");mb.add (m3);

mi1 = new MenuItem ("Open");m1.add (mi1);mi1.addActionListener (this);
mi2 = new MenuItem ("Save");m1.add (mi2);mi2.addActionListener (this);
mi3 = new MenuItem ("Save As");m1.add (mi3);mi3.addActionListener (this);
mi4 = new MenuItem ("Exit");m1.add (mi4);mi4.addActionListener (this);

mi5 = new MenuItem ("Database Connectivity");m2.add (mi5);mi5.addActionListener (this);
mi6 = new MenuItem ("Database Name");m2.add (mi6);mi6.addActionListener (this);
mi7 = new MenuItem ("View Entire Database");m2.add (mi7);mi7.addActionListener (this);

p1 = new Panel ();
p1.setVisible (true);
p1.setLayout (new FlowLayout());
p1.setBackground (Color.cyan);
b1 = new Button ("Enter new data");p1.add (b1);b1.addActionListener (this);
b2 = new Button ("Search the database");p1.add (b2);b2.addActionListener (this);
b3 = new Button ("Go for Billing");p1.add (b3);b3.addActionListener (this);
add (p1,BorderLayout.CENTER);

p2 = new Panel ();
p2.setVisible (false);
p2.setLayout (new FlowLayout());
p2.setBackground (Color.cyan);
l1 = new Label ("Book ID");p2.add (l1);
t1 = new TextField (20);p2.add (t1);
l2 = new Label ("Name");p2.add (l2);
t2 = new TextField (20);p2.add (t2);
l3 = new Label ("Price");p2.add (l3);
t3 = new TextField (20);p2.add (t3);
l4 = new Label ("Availability");p2.add (l4);
t4 = new TextField (20);p2.add (t4);
l5 = new Label ("Publisher");p2.add (l5);
t5 = new TextField (20);p2.add (t5);
ch1 = new Choice ();
ch1.add ("Ananda Publishers");
ch1.add ("Deys Publishers");
ch1.add ("Rupa Publications");
ch1.add ("Oxford Publications");
ch1.add ("Others");
p2.add (ch1);ch1.addItemListener (this);
l6 = new Label ("Language");p2.add (l6);
t6 = new TextField (20);p2.add (t6);
ch2 = new Choice ();
ch2.add ("Bengali");
ch2.add ("English");
ch2.add ("Others");
p2.add (ch2);ch2.addItemListener (this);
l7 = new Label ("Genre");p2.add (l7);
t7 = new TextField (20);p2.add (t7);
ch3 = new Choice ();
ch3.add ("Poetry");
ch3.add ("Novel");
ch3.add ("Drama");
ch3.add ("Ficiton");
ch3.add ("Autobiographical");
ch3.add ("Miscalleneous");
p2.add (ch3);ch3.addItemListener (this);
b4 = new Button ("Store");p2.add (b4);b4.addActionListener (this);
b5 = new Button ("Clear");p2.add (b5);b5.addActionListener (this);
b6 = new Button ("Return to the beginning");p2.add (b6);b6.addActionListener (this);
l8 = new Label ("Message");p2.add (l8);
t8 = new TextField (20);p2.add (t8);
add (p2,BorderLayout.CENTER);

p3 = new Panel ();
p3.setVisible (false);
p3.setLayout (new FlowLayout());
p3.setBackground (Color.cyan);
b7 = new Button ("Search by Book ID");p3.add (b7);b7.addActionListener (this);
b8 = new Button ("Search by Name");p3.add (b8);b8.addActionListener (this);
b9 = new Button ("Search by Publisher");p3.add (b9);b9.addActionListener (this);
b10 = new Button ("Return to the beginning");p3.add (b10);b10.addActionListener (this);
add (p3,BorderLayout.CENTER);

p4 = new Panel ();
p4.setVisible (false);
p4.setLayout (new FlowLayout ());
p4.setBackground (Color.cyan);
l9 = new Label ("Enter Book ID");p4.add (l9);
t9 = new TextField (20);p4.add (t9);
b11 = new Button ("Search book by Book ID");p4.add (b11);b11.addActionListener (this);
b12 = new Button ("Clear");p4.add (b12);b12.addActionListener (this);
l10 = new Label ("Message");p4.add (l10);
t10 = new TextField (20);p4.add (t10);
add (p4,BorderLayout.CENTER);

p5 = new Panel ();
p5.setVisible (false);
p5.setLayout (new FlowLayout ());
p5.setBackground (Color.cyan);
l11 = new Label ("Enter Name");p5.add (l11);
t11 = new TextField (20);p5.add (t11);
b13 = new Button ("Search book by Name");p5.add (b13);b13.addActionListener (this);
b14 = new Button ("Clear");p5.add (b14);b14.addActionListener (this);
l12 = new Label ("Message");p5.add (l12);
t12 = new TextField (20);p5.add (t12);
add (p5,BorderLayout.CENTER);

p6 = new Panel ();
p6.setVisible (false);
p6.setLayout (new FlowLayout ());
p6.setBackground (Color.cyan);
l13 = new Label ("Enter Publisher");p6.add (l13);
t13 = new TextField (20);p6.add (t13);
ch4 = new Choice ();
ch4.add ("Ananda Publishers");
ch4.add ("Deys Publishers");
ch4.add ("Rupa Publications");
ch4.add ("Oxford Publications");
ch4.add ("Others");
p6.add (ch4);ch4.addItemListener (this);
b15 = new Button ("Search book by Publisher");p6.add (b15);b15.addActionListener (this);
b16 = new Button ("Clear");p6.add (b16);b16.addActionListener (this);
l14 = new Label ("Message");p6.add (l14);
t14 = new TextField (20);p6.add (t14);
add (p6,BorderLayout.CENTER);

p7 = new Panel ();
p7.setVisible (false);
p7.setLayout (new FlowLayout());
p7.setBackground (Color.cyan);
l15 = new Label ("Book ID");p7.add (l15);
t15 = new TextField (20);p7.add (t15);
l16 = new Label ("Name");p7.add (l6);
t16 = new TextField (20);p7.add (t16);
l17 = new Label ("Price");p7.add (l17);
t17 = new TextField (20);p7.add (t17);
l18 = new Label ("Availability");p7.add (l18);
t18 = new TextField (20);p7.add (t18);
l19 = new Label ("Publisher");p7.add (l19);
t19 = new TextField (20);p7.add (t19);
l20 = new Label ("Language");p7.add (l20);
t20 = new TextField (20);p7.add (t20);
l21 = new Label ("Genre");p7.add (l21);
t21 = new TextField (20);p7.add (t21);
b17 = new Button ("Make bill");p7.add (b17);b17.addActionListener (this);
b18 = new Button ("Clear");p7.add (b18);b18.addActionListener (this);
b19 = new Button ("Return to the beginning");p7.add (b19);b19.addActionListener (this);
add (p7,BorderLayout.CENTER);

p8 = new Panel ();
p8.setVisible (false);
p8.setLayout (new FlowLayout());
p8.setBackground (Color.cyan);
l22 = new Label ("Customer name");p8.add (l22);
t22 = new TextField (20);p8.add (t22);
l23 = new Label ("No. of copies");p8.add (l23);
t23 = new TextField (20);p8.add (t23);
l24 = new Label ("Book ID");p8.add (l24);
t24 = new TextField (20);p8.add (t24);
l25 = new Label ("Name");p8.add (l25);
t25 = new TextField (20);p8.add (t25);
l26 = new Label ("Price");p8.add (l26);
t26 = new TextField (20);p8.add (t26);
l27 = new Label ("Publisher");p8.add (l27);
t27 = new TextField (20);p8.add (t27);
ch5 = new Choice ();
ch5.add ("Ananda Publishers");
ch5.add ("Deys Publishers");
ch5.add ("Rupa Publications");
ch5.add ("Oxford Publications");
ch5.add ("Others");
p8.add (ch5);ch5.addItemListener (this);
l28 = new Label ("Language");p8.add (l28);
t28 = new TextField (20);p8.add (t28);
ch6 = new Choice ();
ch6.add ("Bengali");
ch6.add ("English");
ch6.add ("Others");
p8.add (ch6);ch6.addItemListener (this);
l29 = new Label ("Genre");p8.add (l29);
t29 = new TextField (20);p8.add (t29);
ch7 = new Choice ();
ch7.add ("Poetry");
ch7.add ("Novel");
ch7.add ("Drama");
ch7.add ("Ficiton");
ch7.add ("Autobiographical");
ch7.add ("Miscalleneous");
p8.add (ch7);ch7.addItemListener (this);
b20 = new Button ("Generate");p8.add (b20);b20.addActionListener (this);
b21 = new Button ("Clear");p8.add (b21);b21.addActionListener (this);
b22 = new Button ("Return to the beginning");p8.add (b22);b22.addActionListener (this);
l30 = new Label ("Message");p8.add (l30);
t30 = new TextField (20);p8.add (t30);
add (p8,BorderLayout.CENTER);
}


public void windowOpened (WindowEvent we)
{
System.out.println ("Window Opened.");
}

public void windowClosed (WindowEvent we)
{
System.out.println ("Window Closed.");
}

public void windowClosing (WindowEvent we)
{
System.out.println ("Window Closing.");
System.exit (0);
}

public void windowActivated (WindowEvent we)
{
System.out.println ("Window Activated.");
}

public void windowDeactivated (WindowEvent we)
{
System.out.println ("Window Deactivated.");
}

public void windowIconified (WindowEvent we)
{
System.out.println ("Window Iconified.");
}

public void windowDeiconified (WindowEvent we)
{
System.out.println ("Window Deiconified.");
}

public void actionPerformed (ActionEvent ae)
{
k = ae.getActionCommand ();

if (k.equals ("Enter new data"))
{
p1.setVisible (false);
p2.setVisible (true);
p3.setVisible (false);
p4.setVisible (false);
p5.setVisible (false);
p6.setVisible (false);
p7.setVisible (false);
p8.setVisible (false);

try
{
rs6 = d.show ("select * from bookdetails");

rs6.last ();
a10 = rs6.getInt (1) + 1;
t1.setText ("" + a10);
}
catch (Exception e)
{
System.out.println (e);
}
}

if (k.equals ("Search the database"))
{
p1.setVisible (false);
p2.setVisible (false);
p3.setVisible (true);
p4.setVisible (false);
p5.setVisible (false);
p6.setVisible (false);
p7.setVisible (false);
p8.setVisible (false);
}

if (k.equals ("Go for Billing"))
{
p1.setVisible (false);
p2.setVisible (false);
p3.setVisible (false);
p4.setVisible (false);
p5.setVisible (false);
p6.setVisible (false);
p7.setVisible (false);
p8.setVisible (true);
}

if (k.equals ("Store"))
{
x = d.inupdel ("insert into bookdetails values ("+Integer.parseInt (t1.getText()) + ",'" + t2.getText ()+"',"+Integer.parseInt (t3.getText())+","+Integer.parseInt (t4.getText())+",'"+t5.getText()+"','"+t6.getText()+"','"+t7.getText()+"')");


if (x>0)
{
t8.setText ("Data Entered");
}
}

if (k.equals ("Search by Book ID"))
{
p1.setVisible (false);
p2.setVisible (false);
p3.setVisible (true);
p4.setVisible (true);
p5.setVisible (false);
p6.setVisible (false);
p7.setVisible (false);
p8.setVisible (false);
}

if (k.equals ("Search by Name"))
{
p1.setVisible (false);
p2.setVisible (false);
p3.setVisible (true);
p4.setVisible (false);
p5.setVisible (true);
p6.setVisible (false);
p7.setVisible (false);
p8.setVisible (false);
}

if (k.equals ("Search by Publisher"))
{
p1.setVisible (false);
p2.setVisible (false);
p3.setVisible (true);
p4.setVisible (false);
p5.setVisible (false);
p6.setVisible (true);
p7.setVisible (false);
p8.setVisible (false);
}

if (k.equals ("Search book by Book ID"))
{
p1.setVisible (false);
p2.setVisible (false);
p3.setVisible (false);
p4.setVisible (false);
p5.setVisible (false);
p6.setVisible (false);
p7.setVisible (true);
p8.setVisible (false);

try
{
rs2 = d.searchBookID (Integer.parseInt (t9.getText()));
rs2.next();

t15.setText (""+rs2.getInt (1));
t16.setText (rs2.getString (2));
t17.setText (""+rs2.getInt (3));
t18.setText (""+rs2.getInt (4));
t19.setText (rs2.getString (5));
t20.setText (rs2.getString (6));
t21.setText (rs2.getString (7));

a1 = rs2.getInt (4);
}
catch (Exception e)
{
System.out.println (e);
}
count = 1;
}

if (k.equals ("Search book by Name"))
{
p1.setVisible (false);
p2.setVisible (false);
p3.setVisible (false);
p4.setVisible (false);
p5.setVisible (false);
p6.setVisible (false);
p7.setVisible (true);
p8.setVisible (false);

try
{
rs3 = d.searchName (t11.getText());
rs3.next();

t15.setText (""+rs3.getInt (1));
t16.setText (rs3.getString (2));
t17.setText (""+rs3.getInt (3));
t18.setText (""+rs3.getInt (4));
t19.setText (rs3.getString (5));
t20.setText (rs3.getString (6));
t21.setText (rs3.getString (7));

a1 = rs3.getInt (4);
}
catch (Exception e)
{
System.out.println (e);
}
count = 1;
}

if (k.equals ("Search book by Publisher"))
{
p1.setVisible (false);
p2.setVisible (false);
p3.setVisible (false);
p4.setVisible (false);
p5.setVisible (false);
p6.setVisible (false);
p7.setVisible (true);
p8.setVisible (false);

try
{
rs4 = d.searchPublisher (t13.getText());
rs4.next();

t15.setText (""+rs4.getInt (1));
t16.setText (rs4.getString (2));
t17.setText (""+rs4.getInt (3));
t18.setText (""+rs4.getInt (4));
t19.setText (rs4.getString (5));
t20.setText (rs4.getString (6));
t21.setText (rs4.getString (7));

a1 = rs4.getInt (4);
}
catch (Exception e)
{
System.out.println (e);
}
count = 1;
}

if (k.equals ("Make bill"))
{
p1.setVisible (false);
p2.setVisible (false);
p3.setVisible (false);
p4.setVisible (false);
p5.setVisible (false);
p6.setVisible (false);
p7.setVisible (false);
p8.setVisible (true);

s1 = t15.getText ();
s2 = t16.getText ();
s3 = t17.getText ();
s4 = t19.getText ();
s5 = t20.getText ();
s6 = t21.getText ();

t24.setText (s1);
t25.setText (s2);
t26.setText (s3);
t27.setText (s4);
t28.setText (s5);
t29.setText (s6);
}

if (k.equals ("Generate"))
{
dateformat = new SimpleDateFormat ("E dd.MM.yyyy 'at' hh:mm:ss a zzz");
date = new Date( );

s1 = t22.getText();
s2 = t23.getText();
s3 = t24.getText();
s4 = t25.getText();
s5 = t26.getText();
s6 = t27.getText();
s7 = t28.getText();
s8 = t29.getText();

a2 = Integer.parseInt (s2);

a4 = Integer.parseInt (s5);
a6 = a2*a4;

if (count == 1)
{
if (a1>a2)
{
a3 = a1 - a2;

s9 = "update bookdetails set Availability=" + a3 + " where BookID=" + Integer.parseInt(t24.getText());
int k = d.inupdel (s9);

try
{
fw = new FileWriter ("E:\\"+s1+".txt");

fw.write (dateformat.format(date));
fw.write ("\r\n");
fw.write ("\r\n");
fw.write ("----------------------------------------------");
fw.write ("\r\n");
fw.write ("Customer Name : ");
fw.write (s1);
fw.write ("\r\n");
fw.write ("No. of copies : ");
fw.write (s2);
fw.write ("\r\n");
fw.write ("Book ID : ");
fw.write (s3);
fw.write ("\r\n");
fw.write ("Book Name : ");
fw.write (s4);
fw.write ("\r\n");
fw.write ("Price of each : ");
fw.write ("Rs." + s5 + "/-");
fw.write ("\r\n");
fw.write ("Publisher : ");
fw.write (s6);
fw.write ("\r\n");
fw.write ("Language : ");
fw.write (s7);
fw.write ("\r\n");
fw.write ("Genre : ");
fw.write (s8);
fw.write ("\r\n");
fw.write ("----------------------------------------------");
fw.write ("\r\n");
fw.write ("\r\n");
fw.write ("Total amount to be paid : ");
fw.write ("Rs." + a6 + "/-");
fw.write ("\r\n");
fw.write ("\r\n");
fw.write ("Authorized Signature -");
fw.write ("\r\n");
fw.write ("Stamp -");

fw.flush();
}
catch (Exception e)
{
System.out.println (e);
}

t30.setText ("Stock adjusted & Bill generated");
}

if (a1<a2)
{
t30.setText ("Demand out of stock");
}

}

if (count == 0)
{
try
{
rs7 = d.searchBookID (Integer.parseInt (t24.getText()));
rs7.next();

a7 = rs7.getInt (4);
}
catch (Exception e)
{
System.out.println (e);
}
if (a7>a2)
{
a3 = a7 - a2;

s9 = "update bookdetails set Availability=" + a3 + " where BookID=" + Integer.parseInt(t24.getText());
int k = d.inupdel (s9);

try
{
fw = new FileWriter ("E:\\"+s1+".txt");

fw.write (dateformat.format(date));
fw.write ("\r\n");
fw.write ("\r\n");
fw.write ("----------------------------------------------");
fw.write ("\r\n");
fw.write ("Customer Name : ");
fw.write (s1);
fw.write ("\r\n");
fw.write ("No. of copies : ");
fw.write (s2);
fw.write ("\r\n");
fw.write ("Book ID : ");
fw.write (s3);
fw.write ("\r\n");
fw.write ("Book Name : ");
fw.write (s4);
fw.write ("\r\n");
fw.write ("Price of each : ");
fw.write ("Rs." + s5 + "/-");
fw.write ("\r\n");
fw.write ("Publisher : ");
fw.write (s6);
fw.write ("\r\n");
fw.write ("Language : ");
fw.write (s7);
fw.write ("\r\n");
fw.write ("Genre : ");
fw.write (s8);
fw.write ("\r\n");
fw.write ("----------------------------------------------");
fw.write ("\r\n");
fw.write ("\r\n");
fw.write ("Total amount to be paid : ");
fw.write ("Rs." + a6 + "/-");
fw.write ("\r\n");
fw.write ("\r\n");
fw.write ("Authorized Signature -");
fw.write ("\r\n");
fw.write ("Stamp -");

fw.flush();
}
catch (Exception e)
{
System.out.println (e);
}

t30.setText ("Stock adjusted & Bill generated");
}

if (a7<a2)
{
t30.setText ("Demand out of stock");
}

}
}

if (k.equals ("Return to the beginning"))
{
p1.setVisible (true);
p2.setVisible (false);
p3.setVisible (false);
p4.setVisible (false);
p5.setVisible (false);
p6.setVisible (false);
p7.setVisible (false);
p8.setVisible (false);
}

if (k.equals ("Clear"))
{
t1.setText ("");
t2.setText ("");
t3.setText ("");
t4.setText ("");
t5.setText ("");
t6.setText ("");
t7.setText ("");
t8.setText ("");
t9.setText ("");
t10.setText ("");
t11.setText ("");
t12.setText ("");
t13.setText ("");
t14.setText ("");
t15.setText ("");
t16.setText ("");
t17.setText ("");
t18.setText ("");
t19.setText ("");
t20.setText ("");
t21.setText ("");
t22.setText ("");
t23.setText ("");
t24.setText ("");
t25.setText ("");
t26.setText ("");
t27.setText ("");
t28.setText ("");
t29.setText ("");
t30.setText ("");
}

if (k.equals ("Open"))
{
fd = new FileDialog (this,"Open",0);
fd.show ();
}

if (k.equals ("Save As"))
{
fd = new FileDialog (this,"Save",1);
fd.show();
}

if (k.equals ("Exit"))
{
System.exit (0);
}

if (k.equals ("Database Connectivity"))
{
}

if (k.equals ("Database Name"))
{
}

if (k.equals ("View Entire Database"))
{
}

}

public void itemStateChanged (ItemEvent ie)
{
t5.setText (ch1.getSelectedItem());
t6.setText (ch2.getSelectedItem());
t7.setText (ch3.getSelectedItem());

t13.setText (ch4.getSelectedItem());

t27.setText (ch5.getSelectedItem());
t28.setText (ch6.getSelectedItem());
t29.setText (ch7.getSelectedItem());
}

}

class BookShopTitle extends Frame
{
Label l31,l32,l33,l34,l35;
Panel p11,p12,p13;

public BookShopTitle ()
{
setSize (600,400);
setUndecorated (true);
setExtendedState (MAXIMIZED_BOTH);
setBackground (Color.pink);

p11 = new Panel ();
p11.setVisible (true);
p11.setLayout (new FlowLayout ());
p11.setBackground (Color.yellow);
l31 = new Label ("Welcome to Book Shop Management System.");p11.add (l31);
l31.setFont (new Font ("Times New Roman",Font.BOLD,50));
add (p11,BorderLayout.NORTH);

p12 = new Panel ();
p12.setVisible (true);
p12.setLayout (new FlowLayout ());
p12.setBackground (new Color (0,255,0));
l32 = new Label ("Made With Love by - Apratim,Madhurya,Debasmita and Debayan.");p12.add (l32);
l32.setFont (new Font ("Times New Roman",Font.ITALIC,20));
add (p12,BorderLayout.SOUTH);

p13 = new Panel ();
p13.setVisible (true);
p13.setLayout (new FlowLayout ());
p13.setBackground (new Color (120,118,145));
l33 = new Label ("A one stop solution for your complete Book Store management.");p13.add (l33);
l33.setFont (new Font ("Times New Roman",Font.BOLD,40));
l34 = new Label ("© Computer Society of India, Kolkata Chapter, Summer Training - 2016.");p13.add (l34);
l34.setFont (new Font ("Times New Roman",Font.BOLD,30));
l35 = new Label ("® GNU General License, All Rights Reserved.");p13.add (l35);
l35.setFont (new Font ("Times New Roman",Font.BOLD,25));
add (p13,BorderLayout.CENTER);
}

}


class MainBookShop
{
public static void main (String args[])
{
BookShopTitle bst = new BookShopTitle ();
bst.setVisible (true);

try
{
Thread.sleep (10000);
}
catch (Exception e)
{
System.out.println (e);
}
bst.setVisible (false);

BookShop bs = new BookShop();
bs.setVisible (true);
}
}