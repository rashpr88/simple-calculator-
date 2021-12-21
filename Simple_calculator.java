package assignment_3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.fathzer.soft.javaluator.DoubleEvaluator;

import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Simple_calculator {
	static String num ;
	static String oper ;
	static String exp ;
	static String input="Enter here....";
	static String invalid="Invalid input";
	static String zerodivi="Can't divide by zero";

	private JFrame frame;
	private static JTextArea txt = new JTextArea();
	
	private int mouseX , mouseY;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Simple_calculator window = new Simple_calculator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Simple_calculator() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				frame.setLocation(frame.getX()+e.getX()-mouseX,frame.getY()+e.getY()-mouseY);
			}
		});
		frame.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		Border raisedborder = BorderFactory.createRaisedBevelBorder();
		frame.getContentPane().setBackground(Color.YELLOW);
		frame.setBounds(100, 100, 350, 430);
		
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 120, 350, 310);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(5, 4, 0,0));
		
		JButton ac = new JButton("AC");
		ac.setBackground(Color.WHITE);
		ac.setForeground(Color.DARK_GRAY);
		ac.setFont(new Font("Tahoma", Font.BOLD, 14));
		ac.setBorder(raisedborder);
		ac.setFocusable(false);
		ac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.setText("");
			}
		});
		panel.add(ac);
	
		JButton sign = new JButton(" +/-");
		sign.setFocusable(false);
		// (+) or (-) sign button
		sign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.setForeground(Color.WHITE);
				String str = txt.getText();

				int n = str.length();
	
				if (txt.getText().isEmpty() || txt.getText().equals(input)|| str.contains(zerodivi) || str.contains(invalid)) {
					
					txt.setText("(-") ;
				}
				else if (str.contains("=")) {
					int i = str.indexOf("=");
					if (str.charAt(i+2) == '-') { 
						String str2= str.substring(i+3,n);
						txt.setText(str2) ;	
					}
					
					else {
						String str2= str.substring(i+2,n);
						txt.setText("(-"+str2) ;
					}
				}
				else if (str.charAt(0)=='-') {
					txt.setText("(-"+str.substring(2,n)) ;
					
				}
				else if (txt.getText().equals("(-")) {
					txt.setText("") ;
					
				}
				else if (txt.getText().equals("0")) {
					txt.setText("0") ;
					
				}
					
				
				else {
					
					if(n ==1) {
						if (str.charAt(0)=='(') {
							
							txt.setText(str+"(-") ;
						}
						else {
								txt.setText("(-"+str) ;
						}
					}
					else if (n>30) {
						txt.setText(str);
						JOptionPane.showMessageDialog(null, "No more inputs are accepted","Input limit exceeded!",JOptionPane.INFORMATION_MESSAGE);
					}
			
					
					else {
						char first = str.charAt(0) ;
						char last = str.charAt(n-1) ;
						char second = str.charAt(1) ;
						
						
						if ( str.charAt(n-2)=='(' && last=='-'){
						    
						    String str3 = str.substring(0,n-2);
						    txt.setText(str3) ;
						
					    }
						
					    else {
							if (last == '+' || last == '-' || last == '÷' || last == 'x' ) {
								txt.setText(str+"(-") ;
							}
							else if( last == ')'  ) {
								txt.setText(str+"x(-") ;
							}
							else if( last == '('  ) {
								txt.setText(str+"(-") ;
							}
							else if( last == ')'  ) {
								txt.setText(str+"x(-") ;
							}
						    else if (first == '(' && second == '-' && (str.contains("+")==false && str.contains("x")==false && str.contains("÷")==false && str.lastIndexOf("-")==1) ){
							    	String str2 = str.substring(2,n);
								    txt.setText(str2) ;
								
							}
						    else if (first == '('  && (str.contains("+")==false && str.contains("x")==false && str.contains("÷")==false && str.contains("-")==false) ){
						    	String str2 = str.substring(1,n);
							    txt.setText("((-"+str2) ;
							
						    }
						    else if (str.contains("((-")==true && (str.contains("+")==false && str.contains("x")==false && str.contains("÷")==false ) ){
						    	String str2 = str.substring(3,n);
							    txt.setText("("+str2) ;
							
						    }
							
							     
							 else  {
								 if (str.contains("+")||str.contains("x")||str.contains("÷") || str.lastIndexOf("-")>1 || str.lastIndexOf("(")>0 ) {
									 int a = str.lastIndexOf("+");
									 int d = str.lastIndexOf("-");
									 int di = str.lastIndexOf("÷");
									 int m = str.lastIndexOf("x");
									 int b = str.lastIndexOf("(");
									 if((b>a  && b> m && b> di && b+1>d) && (str.charAt(b-1)!=('(') && str.charAt(b+1)!=('-') )) {
										 String str2 = str.substring(0,b+1);
										 String str3 = str.substring(b+1,n);
										 txt.setText(str2+"(-"+str3) ;

									 }
									 else if ((b+1==d && b>a  && b> m && b> di) && (str.charAt(b-1)==('(') && str.charAt(b+1)==('-')) ){
										 String str2 = str.substring(0,b);
										 String str3 = str.substring(b+2,n);
										 txt.setText(str2+str3) ;
									 }
										 
										 
									 else  {
										 if (str.charAt(a+1)=='(' && str.charAt(a+2)==('-') && a+3 >d && a>m && a>di  ) {
											 String str2 = str.substring(0,a+1);
											 String str3 = str.substring(a+3,n);
											 txt.setText(str2+str3) ;
		
										 }
										 
										 else if (str.charAt(di+1)=='(' && str.charAt(di+2)==('-') && di >a && di>m && di+3>d  ) {
											 String str2 = str.substring(0,di+1);
											 String str3 = str.substring(di+3,n);
											 txt.setText(str2+str3) ;
		
										 }
										 else if (str.charAt(m+1)=='(' && str.charAt(m+2)==('-') && m >a && m+3>d && m>di ) {	
											 String str2 = str.substring(0,m+1);
											 String str3 = str.substring(m+3,n);
											 txt.setText(str2+str3) ;
											 
										 }
										
										 else if (str.charAt(m+1)!='(' && str.charAt(m+2)!=('-') && m >a && m>d && m>di ) {
											 String str2 = str.substring(0,m+1);
											 String str3 = str.substring(m+1,n);
											 txt.setText(str2+"(-"+str3) ;
											 
										 }
										 else if (str.charAt(di+1)!='(' && str.charAt(di+2)!=('-') && di >a && di>d && di>m ) {
											 String str2 = str.substring(0,di+1);
											 String str3 = str.substring(di+1,n);
											 txt.setText(str2+"(-"+str3) ;
											 
										 }
										 else if (str.charAt(a+1)!='(' && str.charAt(a+2)!=('-') && a >m && a>d && a>di ) {
											 String str2 = str.substring(0,a+1);
											 String str3 = str.substring(a+1,n);
											 txt.setText(str2+"(-"+str3) ;
											 
										 }
										 else if (str.charAt(d+1)!='(' && str.charAt(d+2)!=('-') && d >a && d>m && d>di ) {
											 String str2 = str.substring(0,d+1);
											 String str3 = str.substring(d+1,n);
											 txt.setText(str2+"(-"+str3) ;
											 
										 }
										 else if (str.charAt(d-1)=='(' && str.charAt(d)==('-') && d >a && d>m && d>di  ) {
											 String str2 = str.substring(0,d-1);
											 String str3 = str.substring(d+1,n);
											 txt.setText(str2+str3) ;
		
										 }
										
								      }
										 
									 
										 
							     }
								 
									 
								 else {
									 txt.setText("(-"+str) ;
								 }
						     } 
					}
				}
				
			}
	    }
			
		});
		sign.setBackground(Color.WHITE);
		sign.setForeground(Color.DARK_GRAY);
		sign.setFont(new Font("Tahoma", Font.BOLD, 14));
		sign.setBorder(raisedborder);
		panel.add(sign);
		
		JButton ce = new JButton("CE");
		// clear one by one
		ce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = txt.getText();
				int n = str.length();
				
			
				if (txt.getText().equals(input)|| txt.getText().isEmpty()) {
					txt.setText("") ;
				}
				else {
					String str2 = str.substring(0,n-1); 
					txt.setText(str2) ;
					
				
			} 
				
			}
		});
		ce.setForeground(Color.DARK_GRAY);
		ce.setBackground(Color.WHITE);
		ce.setFont(new Font("Tahoma", Font.BOLD, 14));
		ce.setBorder(raisedborder);
		ce.setFocusable(false);
		panel.add(ce);
		
		JButton divi = new JButton("\u00F7");
		divi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oper = "÷";
				operators();
			}
		});
		divi.setBackground(Color.WHITE);
		divi.setForeground(Color.DARK_GRAY);
		divi.setFont(new Font("Tahoma", Font.BOLD, 14));
		divi.setBorder(raisedborder);
		divi.setFocusable(false);
		panel.add(divi);
		
		JButton b7 = new JButton("7");
		b7.setForeground(Color.BLACK);
		b7.setBackground(Color.WHITE);
		b7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		b7.setBorder(raisedborder);
		b7.setFocusable(false);
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num = "7";
				number();
				
			}
			
		});
		panel.add(b7);
		
		JButton b8 = new JButton("8");
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num = "8";
				number();
			}
		});
		b8.setBackground(Color.WHITE);
		b8.setForeground(Color.BLACK);
		b8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		b8.setBorder(raisedborder);
		b8.setFocusable(false);
		panel.add(b8);
		
		JButton b9 = new JButton("9");
		b9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num = "9";
				number();
			}
		});
		b9.setForeground(Color.BLACK);
		b9.setBackground(Color.WHITE);
	    b9.setFocusable(false);
		b9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		b9.setBorder(raisedborder);
		panel.add(b9);
		
		JButton mul = new JButton("x");
		mul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oper = "x";
				operators();
			}
		});
		mul.setBackground(Color.WHITE);
		mul.setForeground(Color.DARK_GRAY);
		mul.setFont(new Font("Tahoma", Font.BOLD, 14));
		mul.setBorder(raisedborder);
		mul.setFocusable(false);
		panel.add(mul);
		
		JButton b4 = new JButton("4");
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num = "4";
				number();
			}
		});
		b4.setForeground(Color.BLACK);
		b4.setBackground(Color.WHITE);
		b4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		b4.setBorder(raisedborder);
		b4.setFocusable(false);
		panel.add(b4);
		
		JButton b5 = new JButton("5");
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num = "5";
				number();
			}
		});
		b5.setForeground(Color.BLACK);
		b5.setBackground(Color.WHITE);
		b5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		b5.setBorder(raisedborder);
		b5.setFocusable(false);
		panel.add(b5);
		
		JButton b6 = new JButton("6");
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num = "6";
				number();
			}
		});
		b6.setForeground(Color.BLACK);
		b6.setBackground(Color.WHITE);
		b6.setBorder(raisedborder);
		b6.setFocusable(false);
		b6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(b6);
		
		JButton ded = new JButton("-");
		ded.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oper = "-";
				operators();
			}
		});
		ded.setBackground(new Color(255, 255, 255));
		ded.setForeground(Color.DARK_GRAY);
		ded.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		ded.setFont(new Font("Tahoma", Font.BOLD, 14));
		ded.setFocusable(false);
		panel.add(ded);
		
		JButton b1 = new JButton("1");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num = "1";
				number();
			}
		});
		b1.setForeground(Color.BLACK);
		b1.setBackground(Color.WHITE);
		b1.setBorder(raisedborder);
		b1.setFocusable(false);
		b1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(b1);
		
		JButton b2 = new JButton("2");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num = "2";
				number();
			}
		});
		b2.setBackground(Color.WHITE);
		b2.setForeground(Color.BLACK);
		b2.setBorder(raisedborder);
		b2.setFocusable(false);
		b2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(b2);
		
		JButton b3 = new JButton("3");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num = "3";
				number();
			}
		});
		b3.setForeground(Color.BLACK);
		b3.setBackground(Color.WHITE);
		b3.setBorder(raisedborder);
		b3.setFocusable(false);
		b3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(b3);
		
		JButton add = new JButton("+");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oper = "+";
				operators();
			}
			
		});
		add.setForeground(Color.DARK_GRAY);
		add.setBackground(Color.WHITE);
		add.setBorder(raisedborder);
		add.setFocusable(false);
		add.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(add);
// button brackets		
		JButton brac = new JButton("( )");
		brac.setFont(new Font("Tahoma", Font.BOLD, 14));
		brac.setForeground(Color.DARK_GRAY);
		brac.setBackground(Color.WHITE);
		brac.setBorder(raisedborder);
		brac.setFocusable(false);
		brac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.setForeground(Color.WHITE);
				String str = txt.getText();
				int n = str.length();
				
			
				if (txt.getText().equals(input)|| txt.getText().isEmpty()|| str.contains(zerodivi) || str.contains(invalid)) {
					txt.setText("(") ;
				}
				else if (n==1) {
					char last = str.charAt(n-1) ;
					if (last == '(') {
						txt.setText(str+"(") ;
					}
					else {
						txt.setText(str+"x(") ;
					}
					
				}
				
				else if(str.contains("=")) {
					int i = str.indexOf("="); 
					String str2= str.substring(i+1,n);
					txt.setText(str2+"x(") ;
					
				}
				else if (n>30) {
					txt.setText(str);
					JOptionPane.showMessageDialog(null, "No more inputs are accepted","Input limit exceeded!",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					char first = str.charAt(0) ;
					char last = str.charAt(n-1) ;
					
					char second = str.charAt(1) ;
					if (last =='(') {
						txt.setText(str+"(") ;
					}
					else if (last == '+' || last == '-' || last == '÷' || last == 'x') {
						txt.setText(str+"(") ;
						
					} 
						
					else if(last == '.') {
						txt.setText(str) ;
						
					}
							
					else {

						if (str.contains("(") == false) {
							txt.setText(str+"x(");
						
						
					    }
						else {
							
							if (str.contains(")") ) {
							int c1 =0;
							int c2=0;
							for (int i=0; i<n;i++) {
								if (str.charAt(i)=='(') {
									c1++;
								}
							}
							for (int i=0; i<n;i++) {
								if (str.charAt(i)==')') {
									c2++;
								}
							}
							
							if (c1>c2) {
								txt.setText(str+")") ;
							}
							else if (c1 == c2) {
								txt.setText(str+"x(") ;
							}
						}
							else {
								txt.setText(str+")") ;
							}
						}
					}
			}
		
			}
		});
		panel.add(brac);
		
		JButton zero = new JButton("0");
		zero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = txt.getText();
				int n = str.length();
				if (n==2 && str.charAt(0)=='(' && str.charAt(1)=='-') {
					txt.setText("0");
				}
				num = "0";
				
				number();
			}
		});
		zero.setForeground(Color.BLACK);
		zero.setBackground(Color.WHITE);
		zero.setBorder(raisedborder);
		zero.setFocusable(false);
		zero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(zero);
// decimal dot button		
		JButton deli = new JButton(".");
		deli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.setForeground(Color.WHITE);
				String str = txt.getText();
				if (txt.getText().equals(input) || str.contains(zerodivi) || str.contains(invalid)) {
					txt.setText("0.");
				}
				else {
					
					int n = str.length();
					
					if(n==0) {
						txt.setText("0.") ;
					}
					else if(n==1 && txt.getText().equals("(")==false){
						txt.setText(str+".") ;
					}
					else if(n==1 && txt.getText().equals("(")==true){
						txt.setText(str+"0.") ;
					}
					
					else if(str.contains("=")) {
						int i = str.indexOf("="); 
						String str2= str.substring(i+1,n);
						if(str2.contains(".")) {
						txt.setText(str2+"x0.") ;
						}
						else {
							txt.setText(str2+".") ;
						}
					}
					else if (n>30) {
						txt.setText(str);
						JOptionPane.showMessageDialog(null, "No more inputs are accepted","Input limit exceeded!",JOptionPane.INFORMATION_MESSAGE);
					}
					
					else {
						
						char last = str.charAt(n-1);
						char second = str.charAt(1);
						
						//(str.contains(".")
						if (last =='+' || last =='-' || last =='x' || last =='÷' || last =='('  ) {
							txt.setText(str+"0.");
							
							
						}
						
						else if ( last ==')') {
							txt.setText(str+"x0.");
							
						}
						else {
							int i = str.lastIndexOf(".");
							int ad = str.lastIndexOf("+");
							int ded= str.lastIndexOf("-");
							int mul = str.lastIndexOf("x");
							int divi = str.lastIndexOf("÷");
							if (i<ad || i<ded || i<mul ||i<divi ) {
								txt.setText(str+".");
								
							}
	
						else if ((str.contains("+")==false || str.contains("-")==false || str.contains("÷")==false || str.contains("x")==false) && str.contains(".")) {
								
								txt.setText(str);
							}
						else {
							txt.setText(str+".");
						}

						}
	
					}
					
				}
			}
		});
		deli.setBackground(Color.WHITE);
		deli.setForeground(Color.DARK_GRAY);
		deli.setBorder(raisedborder);
		deli.setFocusable(false);
		deli.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(deli);
		
		JButton equal = new JButton("=");
		equal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = txt.getText();
				if (txt.getText().isEmpty() || txt.getText().equals(input)) {
					txt.setText("");
				}
				else if (exp.contains("=") || exp.contains(invalid) || exp.contains(zerodivi)) {
					txt.setText(exp);
				}
				
				else  {
					
					char[] chars = exp.toCharArray();
					StringBuilder sb = new StringBuilder();
				      for(char c : chars){
				         if(Character.isDigit(c)){
				            sb.append(c);
				         }
				     }
				     if (sb.length() == 0) {
				    	 txt.setForeground(Color.red);
				    	 txt.setText(exp+"\n Invalid input");
				     }
				     
				     else {
				    	 if(exp.contains("÷") || exp.contains("x")) {
								exp= exp.replaceAll("÷", "/"); 
								exp= exp.replaceAll("x", "*");
								result();
				    	 }
						 else {
								
								result();				
								
						 }
				     }
				}
			
			}
		});
		equal.setBackground(Color.GREEN);
		equal.setForeground(Color.DARK_GRAY);
		equal.setBorder(raisedborder);
		equal.setFocusable(false);
		equal.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(equal);
		
		JLabel lbl1 = new JLabel("  Simple Calculator");
		lbl1.setForeground(Color.BLACK);
		lbl1.setFont(new Font("Calibri", Font.PLAIN, 13));
		lbl1.setBackground(Color.YELLOW);
		lbl1.setBounds(0, 0, 110, 23);
		lbl1.setOpaque(true);
		frame.getContentPane().add(lbl1);
		
		JButton close = new JButton("\u00D7");
		close.setForeground(new Color(255, 255, 255));
		close.setFont(new Font("Tahoma", Font.PLAIN, 14));
		close.setBackground(Color.RED);
		close.setFocusable(false);
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		close.setBounds(301, 0, 49, 32);
		frame.getContentPane().add(close);
// light mode		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setBackground(Color.WHITE);
				ac.setBackground(Color.WHITE);
				ac.setForeground(Color.GRAY);
				sign.setBackground(Color.WHITE);
				sign.setForeground(Color.GRAY);
				ce.setBackground(Color.WHITE);
				ce.setForeground(Color.GRAY);
				add.setBackground(Color.WHITE);
				add.setForeground(Color.GRAY);
				divi.setBackground(Color.WHITE);
				divi.setForeground(Color.GRAY);
				ded.setBackground(Color.WHITE);
				ded.setForeground(Color.GRAY);
				mul.setBackground(Color.WHITE);
				mul.setForeground(Color.GRAY);
				brac.setBackground(Color.WHITE);
				brac.setForeground(Color.GRAY);
				deli.setBackground(Color.WHITE);
				deli.setForeground(Color.GRAY);
				zero.setForeground(Color.BLACK);
				zero.setBackground(Color.WHITE);
				b1.setForeground(Color.BLACK);
				b1.setBackground(Color.WHITE);
				b2.setForeground(Color.BLACK);
				b2.setBackground(Color.WHITE);
				b3.setForeground(Color.BLACK);
				b3.setBackground(Color.WHITE);
				b4.setForeground(Color.BLACK);
				b4.setBackground(Color.WHITE);
				b5.setForeground(Color.BLACK);
				b5.setBackground(Color.WHITE);
				b6.setForeground(Color.BLACK);
				b6.setBackground(Color.WHITE);
				b7.setForeground(Color.BLACK);
				b7.setBackground(Color.WHITE);
				b8.setForeground(Color.BLACK);
				b8.setBackground(Color.WHITE);
				b9.setForeground(Color.BLACK);
				b9.setBackground(Color.WHITE);
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(184, 8, 17, 15);
		frame.getContentPane().add(btnNewButton);
// dark mode		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setBackground(Color.BLACK);
				ac.setBackground(Color.BLACK);
				brac.setBackground(Color.BLACK);
				ce.setBackground(Color.BLACK);
				add.setBackground(Color.BLACK);
				divi.setBackground(Color.BLACK);
				ded.setBackground(Color.BLACK);
				mul.setBackground(Color.BLACK);
				sign.setBackground(Color.BLACK);
				deli.setBackground(Color.BLACK);
				
				
				
				
				zero.setForeground(Color.WHITE);
				zero.setBackground(Color.BLACK);
				b1.setForeground(Color.WHITE);
				b1.setBackground(Color.BLACK);
				b2.setForeground(Color.WHITE);
				b2.setBackground(Color.BLACK);
				b3.setForeground(Color.WHITE);
				b3.setBackground(Color.BLACK);
				b4.setForeground(Color.WHITE);
				b4.setBackground(Color.BLACK);
				b5.setForeground(Color.WHITE);
				b5.setBackground(Color.BLACK);
				b6.setForeground(Color.WHITE);
				b6.setBackground(Color.BLACK);
				b7.setForeground(Color.WHITE);
				b7.setBackground(Color.BLACK);
				b8.setForeground(Color.WHITE);
				b8.setBackground(Color.BLACK);
				b9.setForeground(Color.WHITE);
				b9.setBackground(Color.BLACK);
			}
		});
		btnNewButton_1.setBackground(Color.BLACK);
		btnNewButton_1.setBounds(211, 8, 17, 15);
		frame.getContentPane().add(btnNewButton_1);
		
		
		txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				txt.setForeground(Color.WHITE);
				String str = txt.getText();
				int l = str.length();
				try {
					if (l>30) {
						e.consume();
						JOptionPane.showMessageDialog(null, "No more inputs are accepted","Input limit exceeded!",JOptionPane.INFORMATION_MESSAGE);
						
					}
				
				}catch(Exception et) {
					
				}
			}   
		});
		txt.setLineWrap(true);
		txt.setRows(3);
		txt.setText(input);
		txt.setForeground(Color.WHITE);
		txt.setFont(new Font("Arial", Font.BOLD, 20));
		txt.setBackground(Color.BLACK);
		txt.setBounds(0, 31, 350, 89);
		frame.getContentPane().add(txt);
		
		JButton minim = new JButton("-");
		minim.setFocusable(false);
		minim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}
		});
		minim.setBackground(Color.LIGHT_GRAY);
		minim.setForeground(new Color(0, 0, 0));
		minim.setFont(new Font("Tahoma", Font.PLAIN, 12));
		minim.setBounds(252, -1, 49, 33);
		frame.getContentPane().add(minim);
	}
	
// number buttons
	public static void number() {
		txt.setForeground(Color.WHITE);
		String str = txt.getText();
		int n = str.length();
		if (txt.getText().isEmpty() || txt.getText().equals(input)|| txt.getText().equals("0") || str.contains(zerodivi) || str.contains(invalid)|| str.contains("=")) {

			
			txt.setText(num) ;
		}
		else if (n==1) {
			txt.setText(str+num) ;
			
		}
		else if (n>30) {
			txt.setText(str);
			JOptionPane.showMessageDialog(null, "No more inputs are accepted","Input limit exceeded!",JOptionPane.INFORMATION_MESSAGE);
		}
		
		else {
			
			char last = str.charAt(n-1);
			char bl = str.charAt(n-2);
			if (last == '0' && (bl == '+' || bl == '-' || bl == '÷' || bl == 'x')) {
				String str2 = str.substring(0, n-1);
				txt.setText(str2+num) ;
				
				
			}
			else if (last == ')') {
				txt.setText(str+"x"+num) ;
			}
			else {
				txt.setText(str+num) ;
			}
		
		}
	}
// operator buttons	
	public static void operators() {
		
		txt.setForeground(Color.WHITE);
		String str = txt.getText();
		int n = str.length();
		if (txt.getText().isEmpty() || txt.getText().equals(input)|| str.contains(zerodivi) || str.contains(invalid)) {

			
			txt.setText("") ;
		}
		else if (n==1) {
			txt.setText(str+oper) ;
		}
		else if(str.contains("=")) {
			int i = str.indexOf("="); 
			String str2= str.substring(i+1,n);
			txt.setText(str2+oper) ;
			
		}
		else if (n>30) {
			txt.setText(str);
			JOptionPane.showMessageDialog(null, "No more inputs are accepted","Input limit exceeded!",JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			char last = str.charAt(n-1);
			char bl = str.charAt(n-2);
			if (last == '+' || last == '-' || last == '÷' || last == 'x') {
				String str2 = str.substring(0, n-1);
				txt.setText(str2+oper) ;
				
				
			}
			else {
				txt.setText(str+oper) ;
			}
			
		}
		
	}
// get the result	
	public static void result() {
		try {
			double result = new DoubleEvaluator().evaluate(exp);
			String s=String.valueOf(result);
			char[] chars = s.toCharArray();
			StringBuilder sb = new StringBuilder();
		      for(char c : chars){
		         if(Character.isDigit(c)){
		            sb.append(c);
		         }
		     }
		     if ((sb.length() == 0) && (s=="Infinity" || s=="NaN")) {
		    	 txt.setForeground(Color.red);
					if ((exp.contains("/") ||  exp.contains("*"))) {
						
					    exp=exp.replaceAll("/", "÷");
						exp=exp.replaceAll("\\*", "x");
					}
					else {
						exp=exp;
					
					}
					txt.setText(exp+"\n Can't divide by zero");
					
		    	 
		    	 
		    	 
		     }
		     else {
		    	 int f=(int)result;
					if (result - f != 0) {
						txt.setForeground(Color.GREEN);
						if ((exp.contains("/") ||  exp.contains("*"))) {
							
						    exp=exp.replaceAll("/", "÷");
							exp=exp.replaceAll("\\*", "x");
						}
						else {
							exp=exp;
						
						}
						txt.setText(exp+"\n= "+s);
						
						
					}
					else {
						
						String in = Integer.toString(f);
						txt.setForeground(Color.GREEN);
						
		                if ((exp.contains("/") ||  exp.contains("*"))) {
							
						    exp=exp.replaceAll("/", "÷");
							exp=exp.replaceAll("\\*", "x");
						}
						else {
							exp=exp;
						
						}
						txt.setText(exp+"\n= "+in);
						
					}
		     }
			
		}

		catch(Exception e) {
			if ((exp.contains("/") ||  exp.contains("*"))) {
				
			    exp=exp.replaceAll("/", "÷");
				exp=exp.replaceAll("\\*", "x");
			}
			else {
				exp=exp;
			}
			txt.setText(exp+"\nInvalid input");
			txt.setForeground(Color.red);
			
		}
	}
}
