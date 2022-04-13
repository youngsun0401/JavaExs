package swing.app;


import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/*
텍필1은 달러 혹은 엔, 텍필2는 원화이다.
가운데 콤보박스에서 달러 혹은 엔 중 하나를 선택한다.
텍필1에 값을 넣으면 환율 결과가 텍필2에 덮어씌워지고 텍필2에서도 마찬가지로 텍필1에 값을 덮어씌운다.
  그런데 여기서 텍필1이 텍필2를 업데이트하면 또 텍필2에서 값바뀜을 감지하고 역으로 텍필1을 업데이트하고 … 정확하진 않은데 뭔가 이러는 거 같으니까
  환율 전환과 역전환 함수는 한 번에 하나만 실행되도록
    멤버변수 keyOnce를 false로 초기화하고 함수 실행시 true로 바꿈, 함수는 이 변수가 false여야 실행되고 실행이 다 끝나면 다시 false로 초기화하도록 한다. 
*/

public class HwanYul extends JFrame {
	private static final long serialVersionUID = 1L;
	public static double USD2KRW = 1231;
	public static double JPY2KRW = 9.76;
	
	private JTextField textField1;
	private JTextField textField2;
	private JComboBox<String> currentSelect;
	
	private boolean keyOnce = false;
	
	public HwanYul() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(2000, 100, 800, 200);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//// 외국화폐 텍스트필드
		textField1 = new JTextField("0"); 
		textField1.setFont(new Font("Dialog", Font.PLAIN, 20));
		textField1.setColumns(10);
		textField1.getDocument().addDocumentListener( new DocumentListener() {// 값이 바뀌면 환율계산하고 결과값을 상대텍필에게 덮어씌움 
			@Override public void insertUpdate(DocumentEvent e) {update();}
			@Override public void removeUpdate(DocumentEvent e) {update();}
			@Override public void changedUpdate(DocumentEvent e) {}
			private void update() {
				if( !keyOnce ) {
					keyOnce = true;
					exchange();
					keyOnce = false;
				}
			}
		} );
		
		//// 한화 텍스트필드
		textField2 = new JTextField("0"); 
		textField2.setFont(new Font("Dialog", Font.PLAIN, 20));
		textField2.setColumns(10);
		textField2.getDocument().addDocumentListener( new DocumentListener() {// 값이 바뀌면 환율계산하고 결과값을 상대텍필에게 덮어씌움 
			@Override public void insertUpdate(DocumentEvent e) {update();}
			@Override public void removeUpdate(DocumentEvent e) {update();}
			@Override public void changedUpdate(DocumentEvent e) {}
			private void update() {
				if( !keyOnce ) {
					keyOnce = true;
					exchange_rev();
					keyOnce = false;
				}
			}
		} );

		String[] currentType = {"달러", "엔"};
		currentSelect= new JComboBox<String>( currentType ); 
		currentSelect.setFont(new Font("Dialog", Font.PLAIN, 20));
		currentSelect.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
			exchange();
		}});
		
		
		add(textField1);
		add(currentSelect);
		add(new JLabel(" = "));
		add(textField2);
		add(new JLabel("원"));

		setVisible(true);
	}
	
	//// 화폐를 선택하거나 입력필드에서 키보드 입력시 환전
	private void exchange() {
		long origin=0;
		try{ origin = Long.parseLong( textField1.getText() );} catch(Exception e) {}
		switch( currentSelect.getSelectedIndex() ) {
		case 0:// 달러
			try{ textField2.setText( Integer.toString( (int)(origin * USD2KRW) ) ); } catch(Exception e) {}
			break;
		case 1:// 엔
			try{ textField2.setText( Integer.toString( (int)(origin * JPY2KRW) ) );} catch(Exception e) {}
			break;
		}
	}
	//// 원화를 다른 화폐로 거꾸로 계산해 적용
	private void exchange_rev() {
		long origin=0;
		try{ origin = Long.parseLong( textField2.getText() );} catch(Exception e) {}
		switch( currentSelect.getSelectedIndex() ) {
		case 0:// 달러
			textField1.setText( Integer.toString( (int)(origin / USD2KRW) ) );
			break;
		case 1:// 엔
			textField1.setText( Integer.toString( (int)(origin / JPY2KRW) ) );
			break;
		}
	}
	
	
	

}
