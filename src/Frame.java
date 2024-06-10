import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame{
    JLabel l1 ;
    JTextField inputbox ;
    JButton button ;
    JPanel panel ;
    String inputString="";
    String outputString="";
    JTextArea area;
    JScrollPane scrollPane;
    public Frame(){
        setTitle("大写金额转换器💰");
        setBounds(500,100,500,300);
        init();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    void init(){
        l1 = new JLabel("输入金额");
        inputbox = new JTextField(15);
        button = new JButton("确认");
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(l1);
        panel.add(inputbox);
        panel.add(button);

        area = new JTextArea();    
        scrollPane = new JScrollPane(area);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                inputString = inputbox.getText();
                MoneyConverter moneyConverter = new MoneyConverter();
                outputString = moneyConverter.intfun(inputString);
                area.setText("");
                area.setText("准换结果\n"+outputString);
            }
            
        });

        this.setLayout(new GridLayout(2,3));
        this.add(panel);
        this.add(scrollPane);


    }
}

class Main{
    public static void main(String[] args) {
        new Frame();
    }
}