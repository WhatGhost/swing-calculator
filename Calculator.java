

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Calculator extends JFrame implements ActionListener{
	final private String[] Keys={"0","1","2","+","3","4","5","-","6","7","8","*","9",".","=","/"};
	final private String[] Maths={"sin","cos","tan","+/-"};
	final private String[] M={"MC","MR","MS","M+"};
	private JButton[] keys=new JButton[Keys.length];
	private JButton[] maths=new JButton[Maths.length];
	private JButton[] m=new JButton[M.length];
	private JTextField resultText=new JTextField("0");
	private JTextField mText=new JTextField(5);
	private String operator="=";	//记录上一个输入的符号
	private double midreult=0.0;//记录中间计算结果
	private boolean firstnum=true;//标识当前输入的数字是否为第一个数字
	private boolean legal=true;//标识输入是否合法
	private double mNum=0;//存储器中的数值
	public Calculator(){
		super();
		init();
		this.setTitle("计算器");
		this.setLocation(300, 500);
		this.setResizable(false);
		this.pack();
		
	}
	public void init(){
        resultText.setHorizontalAlignment(JTextField.RIGHT);
        resultText.setEditable(false);
        mText.setHorizontalAlignment(JTextField.RIGHT);
        mText.setEditable(false);
        resultText.setBackground(Color.WHITE);
        mText.setBackground(Color.WHITE);
        resultText.setColumns(10);
        Box b=Box.createHorizontalBox();
        b.add(mText);
        b.add(Box.createHorizontalStrut(50));       
        b.add(resultText);
        
        JPanel text=new JPanel();
        text.add(b);
        
        JPanel keyPanel=new JPanel();
        keyPanel.setLayout(new GridLayout(4,4));
        for(int i=0;i<keys.length;i++){
        	keys[i]=new JButton(Keys[i]);
        	keyPanel.add(keys[i]);
        }
        
        JPanel mPanel=new JPanel();
        mPanel.setLayout(new GridLayout(4,1));
        for(int i=0;i<M.length;i++){
        	m[i]=new JButton(M[i]);
        	mPanel.add(m[i]);
        }
        
        JPanel mathPanel=new JPanel();
        mathPanel.setLayout(new GridLayout(4,1));
        for(int i=0;i<maths.length;i++){
        	maths[i]=new JButton(Maths[i]);
        	mathPanel.add(maths[i]);
        }
        
        JPanel buttons=new JPanel();
        buttons.setLayout(new FlowLayout());
        buttons.add(mathPanel);
        buttons.add(mPanel);
        buttons.add(keyPanel);
        
        this.setLayout(new BorderLayout());
        this.add(buttons,"Center");
        this.add(text,"North");
        
        for(int i=0;i<keys.length;i++){
        	keys[i].addActionListener(this);
        }
        for(int i=0;i<m.length;i++){
        	m[i].addActionListener(this);
        }
        
        for(int i=0;i<maths.length;i++){
        	maths[i].addActionListener(this);
        }
	}
	
	public void actionPerformed(ActionEvent e) {
		String label=e.getActionCommand();
		if(label.equals(Maths[0]))
			handlesin();
		if(label.equals(Maths[1]))
			handlecos();
		if(label.equals(Maths[2]))
			handletan();
		if(label.equals(Maths[3]))
			handlezf();
		if("0123456789.".indexOf(label)>=0)
			handlenum(label);
		if(label.equals(M[0])||label.equals(M[1])||label.equals(M[2])||label.equals(M[3]))
			handlem(label);
		if(label.equals(Keys[3])||label.equals(Keys[7])||label.equals(Keys[11])||label.equals(Keys[13])||label.equals(Keys[14])||label.equals(Keys[15]))
			handleoperator(label);
		
	}
	
	
	
	private void handlem(String key) {
		// TODO 自动生成的方法存根
		if(key.equals(M[0])){
			mNum=0;
			mText.setText(Double.toString(mNum));
		}
		if(key.equals(M[1]))
			mText.setText(Double.toString(mNum));
		if(key.equals(M[2]))
			mNum=Double.parseDouble(resultText.getText());
		if(key.equals(M[3]))
			mNum+=Double.parseDouble(resultText.getText());
	}
	private void handleoperator(String op) {
		// TODO 自动生成的方法存根
			if(operator.equals("/")){
				if(Double.parseDouble(resultText.getText())==0){
					resultText.setText("除数不能为零");
					legal=false;
					}
				else
					midreult/=Double.parseDouble(resultText.getText());
			}
			if(operator.equals("+"))
				midreult+=Double.parseDouble(resultText.getText());
			if(operator.equals("-"))
				midreult-=Double.parseDouble(resultText.getText());
			if(operator.equals("*"))
				midreult*=Double.parseDouble(resultText.getText());
			if(operator.equals("="))
				midreult=Double.parseDouble(resultText.getText());
			if(op.equals("=")&&legal)
				resultText.setText(Double.toString(midreult));
			operator=op;
			firstnum=true;
	}
	private void handlenum(String key) {
		// TODO 自动生成的方法存根
		if(firstnum==true)
			resultText.setText(key);
		 if(key.equals(".")&&resultText.getText().indexOf(".")<0)
			resultText.setText(resultText.getText()+key);
		 if(!firstnum&&!key.equals("."))
				resultText.setText(resultText.getText()+key);
		
		firstnum=false;
			
	}
	private void handlezf() {
		// TODO 自动生成的方法存根
		String text=resultText.getText();
		double n=Double.parseDouble(text);
		double r=-n;
		text=Double.toString(r);
		resultText.setText(text);
		firstnum=true;
	}
	private void handletan() {
		// TODO 自动生成的方法存根
		String text=resultText.getText();
		double n=Double.parseDouble(text);
		double r=Math.tan(n);
		text=Double.toString(r);
		resultText.setText(text);
		firstnum=true;
	}
	private void handlecos() {
		// TODO 自动生成的方法存根
		String text=resultText.getText();
		double n=Double.parseDouble(text);
		double r=Math.cos(n);
		text=Double.toString(r);
		resultText.setText(text);
		firstnum=true;
	}
	private void handlesin() {
		// TODO 自动生成的方法存根
		String text=resultText.getText();
		double n=Double.parseDouble(text);
		double r=Math.sin(n);
		text=Double.toString(r);
		resultText.setText(text);
		firstnum=true;
	}
	
	
	
	
	public static void main(String[] args){
		Calculator c=new Calculator();
		c.setVisible(true);
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	}
	
	
