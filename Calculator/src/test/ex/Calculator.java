package test.ex;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame implements ActionListener{
	//변수 정의
	JButton num_1,num_2,num_3,num_4,num_5,num_6,num_7,num_8,num_9,num_0
		,reset, multi, divide, dot, plus, minus, result;//버튼
	Vector<String> vlist;//숫자 한개씩 저장 용도
	List<Double> il;//낱개의 숫자 합칠 용도
	List<String> sl;//사직연산 부호 저장 용도
	JTextField tf;//textField
	String before_textfield_str="";//이전의 textfield 값 
	String cur_str="";//현재 누른값
	String result_str="";//숫자연결값
	String first_str="";//초기값, 숫자연결값

	String final_Result="";//최종 계산값
	//생성자
	public Calculator(){
		setLayout(new BorderLayout());
		
		tf=new JTextField(10);
		tf.setSize(200, 200);
		JPanel panel=new JPanel();
		//new GridLayout(행,열)
		panel.setLayout(new GridLayout(4,0));
		
		//font, 크기 지정
		Font font = new Font("Serif", Font.PLAIN, 20) ;
		
		//배열 생성
		vlist=new Vector<>();
		sl=new ArrayList<>();
		il=new ArrayList<>();
		
		//키 설정
		num_1=new JButton("1");
		num_2=new JButton("2");
		num_3=new JButton("3");
		reset=new JButton("C");
		num_4=new JButton("4");
		num_5=new JButton("5");
		num_6=new JButton("6");
		divide=new JButton("/");
		num_7=new JButton("7");
		num_8=new JButton("8");
		num_9=new JButton("9");
		multi=new JButton("*");
		num_0=new JButton("0");
		dot=new JButton(".");
		plus=new JButton("+");
		minus=new JButton("-");
		result=new JButton("=");
		
		//버튼에 리스너 등록하기
		num_1.addActionListener(this);
		num_2.addActionListener(this);
		num_3.addActionListener(this);
		num_4.addActionListener(this);
		num_5.addActionListener(this);
		num_6.addActionListener(this);
		num_7.addActionListener(this);
		num_8.addActionListener(this);
		num_9.addActionListener(this);
		num_0.addActionListener(this);
		plus.addActionListener(this);
		minus.addActionListener(this);
		divide.addActionListener(this);
		multi.addActionListener(this);
		dot.addActionListener(this);
		reset.addActionListener(this);
		result.addActionListener(this);
		
		
		//키 폰트
		num_1.setFont(font);
		num_2.setFont(font);
		num_3.setFont(font);
		num_4.setFont(font);
		num_5.setFont(font);
		num_6.setFont(font);
		num_7.setFont(font);
		num_8.setFont(font);
		num_9.setFont(font);
		num_0.setFont(font);
		reset.setFont(font);
		divide.setFont(font);
		plus.setFont(font);
		minus.setFont(font);
		multi.setFont(font);
		dot.setFont(font);
		result.setFont(font);
		result.setBackground(new Color(0,255,0));
		
		//키 패널 추가
		panel.add(num_1);
		panel.add(num_2);
		panel.add(num_3);
		panel.add(reset);
		panel.add(num_4);
		panel.add(num_5);
		panel.add(num_6);
		panel.add(divide);
		panel.add(num_7);
		panel.add(num_8);
		panel.add(num_9);		
		panel.add(multi);
		panel.add(num_0);
		panel.add(dot);
		panel.add(minus);
		panel.add(plus);
		
		//버튼의 위치 설정
		add(result, BorderLayout.SOUTH);
		add(tf,BorderLayout.NORTH);
		add(panel,BorderLayout.CENTER);
		
		//기본설정
		setBounds(200,200,500,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	public static void main(String[] args) {
		new Calculator();
		
	}

	//버튼 이벤트 start
	@Override
	public void actionPerformed(ActionEvent e) {
		try{
		//버튼에 이벤트가 일어났을때 String 값으로 저장된다.
		String btn=e.getActionCommand();
		
		//숫자를 눌렀을때
		if(!btn.equals("+")&&!btn.equals("/")&&!btn.equals("*")&&!btn.equals("-")
				&&!btn.equals("=")&&!btn.equals("C")){
			before_textfield_str=tf.getText();//이전값 가져오기
			cur_str=btn;//현재누른값
			result_str=before_textfield_str+cur_str;//이전+현재값 합침
			vlist.add(cur_str);//현재누른값 vector에 저장
			tf.setText(result_str);//숫자 연결값 textfield에 출력
		}//숫자를 눌렀을때 end
		
		//사칙연산을 눌렀을때
		if(btn.equals("+")||btn.equals("/")||btn.equals("*")||btn.equals("-")){
			//계산 결과값에 바로 사칙 연산할때
			if(vlist.isEmpty()){//숫자가 저장된 값이 없을때
				tf.setText(tf.getText()+btn);//이전의 값과 사칙연산 연결한 값 출력 
				sl.add(btn);//사칙연산 부호 저장
				return;//빠져나가기
			}//계산 결과값에 바로 사칙 연산할때 end
			
			//그외의 연산시
			for(int i=0;i<vlist.size();i++){
				result_str=first_str+vlist.get(i);//초기값, 배열의 값 연결		
				first_str=result_str;//연결값을 초기값으로		
			}//그외의 연산시 end
			
			tf.setText(tf.getText()+btn);////이전의 값과 사칙연산 연결한 값 출력
			il.add(Double.parseDouble(first_str));//배열에 다 합친값 double type으로 넣기
			sl.add(btn);//배열의 사칙연산부호 넣기
			
			//값, 배열 초기화
			vlist.clear();
			first_str="";
			before_textfield_str="";
			cur_str="";
			result_str="";
		}//사칙연산을 눌렀을때end
		
		//reset 시킬때
		if(btn.equals("C")){
			//text field 초기화
			tf.setText("");
			vlist.clear();
			sl.clear();
			il.clear();
			first_str="";
			before_textfield_str="";
			cur_str="";
			result_str="";			
		}//reset 시킬때 end
		
		//최종 계산할때
		if(btn.equals("=")){
			//사칙연산자후 값을 입력 받았을때 숫자들 연결
			for(int i=0;i<vlist.size();i++){
				result_str=first_str+vlist.get(i);//초기값+배열의 값			
				first_str=result_str;//숫자연결값을 초기값으로		
			}//for end
			
			//숫자연결값 숫자 배열에 추가
			il.add(Double.parseDouble(first_str));
			//초기 결과값 설정
			double first_num=0.0;
			//첫번째 인덱스값 저장
			first_num=il.get(0);
			
			//for문 시작
			for(int i=0,j=1;i<sl.size();i++,j++){
				int listSize=sl.size();//사칙연산 배열 크기 저장
				if(listSize>0){//사칙연산이 있을때만 실행
					if(sl.get(i)=="+"){
						first_num=first_num+il.get(j);
					}
					if(sl.get(i)=="-"){
						first_num=first_num-il.get(j);
					}
					if(sl.get(i)=="*"){
						first_num=first_num*il.get(j);
					}
					if(sl.get(i)=="/"){
						first_num=first_num/il.get(j);
					}
				}//사칙연산이 있을때만 실행
			}//for문 시작
			
			//결과값을 문자 저장
			final_Result=first_num+"";
			tf.setText(final_Result);
			//tf.getText();
			
			//숫자 배열 비우고 계산 결과값 저장
			il.clear();
			il.add(first_num);
			
			//초기화
			sl.clear();
			first_num=0.0;
			vlist.clear();
			first_str="";
			before_textfield_str="";
			cur_str="";
			result_str="";			
		}//최종 계산할때 end
		}catch(IndexOutOfBoundsException ioe){
			JOptionPane.showMessageDialog(this, "사칙연산자가 중복되었습니다.");
			tf.setText("");
			vlist.clear();
			sl.clear();
			il.clear();
			first_str="";
			before_textfield_str="";
			cur_str="";
			result_str="";
		}catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(this, "완성되지 않은 계산식입니다.");
			tf.setText("");
			vlist.clear();
			sl.clear();
			il.clear();
			first_str="";
			before_textfield_str="";
			cur_str="";
			result_str="";
		}catch(Exception ex){
			JOptionPane.showMessageDialog(this, "예기치 않는 오류입니다.");
			tf.setText("");
			vlist.clear();
			sl.clear();
			il.clear();
			first_str="";
			before_textfield_str="";
			cur_str="";
			result_str="";
		}
	}//버튼 이벤트 end
}