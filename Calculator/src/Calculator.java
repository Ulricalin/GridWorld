import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JTextField;

import jdk.nashorn.internal.ir.Flags;

public class Calculator implements ActionListener{
	private JTextField[] text = new JTextField[5];
	private JButton jb = new JButton();
	private int answer = 0;
	private int x1 = 0;
	private int x2 = 0;
	public Calculator() {
		JFrame jf = new JFrame("Calculator");
		jf.setResizable(true);//the frame can be resized
		JPanel jp = new JPanel();
		GridLayout gl = new GridLayout(2,5,2,2);//row column
		jp.setLayout(gl);
		String[] str = {"+", "-", "*", "/", "OK"};
		for (int i = 0; i < 5; i++) {
			text[i] = new JTextField();//init
		}
		//init
		text[0].setText("12");
		text[2].setText("2");
		text[3].setText("=");
		for (int i = 0; i < 5; i++) {
			jp.add(text[i]);
		}
		for (int i = 0; i < str.length; i++) {
			jb = new JButton(str[i]);
			jb.addActionListener(this);
			jp.add(jb);
		}
		jf.add(jp);
		jf.pack();
		jf.setLocation(300,300);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		Boolean TT = false;
		if (!s.equals("OK")) text[1].setText(s);
		else {
		x1 = Integer.parseInt(text[0].getText());
		x2 = Integer.parseInt(text[2].getText());
		if (text[1].getText().equals("+")) {

			answer = x1+x2;
		}
		else if (text[1].getText().equals("-")) {

			answer = x1-x2;
		}
		else if (text[1].getText().equals("*")) {

			answer = x1*x2;
		}
		else if (text[1].getText().equals("/")) {
			if (x2 == 0) {
				TT = true;
			} else {
			answer = x1/x2;
			}
		}
			if (!TT) text[4].setText(answer+"");
			else text[4].setText("Wrong!");
		}
	}
	public static void main(String[] args) {
		Calculator c = new Calculator();
	}
}