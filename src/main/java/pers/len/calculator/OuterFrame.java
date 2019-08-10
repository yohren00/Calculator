package pers.len.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OuterFrame {
    private JFrame jframe;
    private String[] name;
    private int att[][];
    private ArrayList<JComponent> Button;
    private static long num;
    private static byte op;

    public OuterFrame() {
        //按鍵填滿方式
        int[] fill = {GridBagConstraints.BOTH,
                GridBagConstraints.VERTICAL,
                GridBagConstraints.HORIZONTAL,
                GridBagConstraints.NONE,};
        //定位點
        int anchor[] = {GridBagConstraints.CENTER,
                GridBagConstraints.EAST,
                GridBagConstraints.SOUTHEAST,
                GridBagConstraints.SOUTH,
                GridBagConstraints.SOUTHWEST,
                GridBagConstraints.WEST,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NORTH,
                GridBagConstraints.NORTHEAST};
        //按鍵設定
        String s[] = {"0",
                "CE", "C", "Back", "/",
                "7", "8", "9", "x",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "+-", "0", ".", "="};
        this.name = s;//把s值給name，要不然會拋出null錯誤
        //各按鍵位置
        int a[][] = {{0, 0, 5, 1, 1, 1, fill[3], anchor[1]},
                {0, 1, 1, 1, 1, 1, fill[3], anchor[0]}, {1, 1, 1, 1, 1, 1, fill[3], anchor[0]},
                {2, 1, 1, 1, 1, 1, fill[3], anchor[0]}, {3, 1, 1, 1, 1, 1, fill[3], anchor[0]},
                {0, 2, 1, 1, 1, 1, fill[3], anchor[0]}, {1, 2, 1, 1, 1, 1, fill[3], anchor[0]},
                {2, 2, 1, 1, 1, 1, fill[3], anchor[0]}, {3, 2, 1, 1, 1, 1, fill[3], anchor[0]},
                {0, 3, 1, 1, 1, 1, fill[3], anchor[0]}, {1, 3, 1, 1, 1, 1, fill[3], anchor[0]},
                {2, 3, 1, 1, 1, 1, fill[3], anchor[0]}, {3, 3, 1, 1, 1, 1, fill[3], anchor[0]},
                {0, 4, 1, 1, 1, 1, fill[3], anchor[0]}, {1, 4, 1, 1, 1, 1, fill[3], anchor[0]},
                {2, 4, 1, 1, 1, 1, fill[3], anchor[0]}, {3, 4, 1, 1, 1, 1, fill[3], anchor[0]},
                {0, 5, 1, 1, 1, 1, fill[3], anchor[0]}, {1, 5, 1, 1, 1, 1, fill[3], anchor[0]},
                {2, 5, 1, 1, 1, 1, fill[3], anchor[0]}, {3, 5, 1, 1, 1, 1, fill[3], anchor[0]}};
        this.att = a;//把a值給att，要不然會拋出null錯誤

    }

    //視窗按鍵設定
    public void Run() {
        //視窗設定
        jframe = new JFrame("Test");//設計視窗並給予名稱
        jframe.setSize(400, 600);//給予視窗大小
        jframe.setLocationRelativeTo(null);//讓視窗開起在正中間
        jframe.setLayout(new GridBagLayout());//用GridBagLayout排版
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//關閉程式

        //新增顯示示框到版面
        Button = new ArrayList<JComponent>(21);
        JLabel jl0 = new JLabel(name[0]);
        jl0.setFont(new Font("標楷體", Font.BOLD, 20));
        jl0.setBackground(new Color(240,220,190));
        Button.add(jl0);

        //新增按鈕到版面
        for (int i = 1; i < 21; i++) {
            JButton jball = new JButton(name[i]);
            jball.setFont(new Font("標楷體", Font.BOLD, 16));
            Button.add(jball);

        }
        //按鈕版面的配置方式
        for (int i = 0; i < Button.size(); i++) {
            addComponent(i);
        }

        JButton j = (JButton) Button.get(1);
        j.addActionListener(new OneListener());


        jframe.setVisible(true);//顯示視窗
    }

    //按鈕版面的配置方式設定值
    private void addComponent(int i) {
        GridBagConstraints gbc = new GridBagConstraints();


        int[] a = att[i];
        gbc.gridx = a[0];
        gbc.gridy = a[1];
        gbc.gridwidth = a[2];
        gbc.gridheight = a[3];
        gbc.weightx = a[4];
        gbc.weighty = a[5];
        gbc.fill = a[6];
        gbc.anchor = a[7];
        gbc.insets = new Insets(10, 10, 10, 10);

        jframe.add(Button.get(i), gbc);
    }

    //按鈕1
    class OneListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            long result;//存放由字串轉成的數值
            JLabel btn = (JLabel) e.getSource();
            //處理數值1-9
            for (int i = 0; i <= 9; i++) {
                if (btn.equals(Button.get(i))) {
                    output_digit((JButton) Button.get(i));
                    break;
                }
            }
            if(btn.equals(Button.get(2))){
                result=0L;//把儲存的結果歸0
                num=0L;
                op=0;
                JLabel j = (JLabel) Button.get(0);
                j.setText(Long.toString(num));
            }else if(btn.equals(Button.get(16))){//加
                save_num((JButton) Button.get(16));
                op=1;
            }else if(btn.equals(Button.get(14))){//減
                save_num((JButton) Button.get(14));
                op=2;
            }else if(btn.equals(Button.get(10))){//乘
                save_num((JButton) Button.get(10));
                op=3;
            }else if(btn.equals(Button.get(4))){//除
                save_num((JButton) Button.get(4));
                op=4;
            }else if(btn.equals(Button.get(20))){
                JLabel j = (JLabel) Button.get(0);
                result=Long.parseLong(j.getText());

                switch(op){
                    case 1:
                        num+=result;
                        break;
                    case 2:
                        num-=result;
                        break;
                    case 3:
                        num*=result;
                        break;
                    case 4:
                        try{
                            num/=result;
                        }catch(ArithmeticException ae){
                            num = 0L;
                            result = 0L;
                            System.out.println("被除數不得為0");
                        }
                        break;
                    default:
                }
                result=0L;
                //輸出運算後的結果到顯示器
                j.setText(Long.toString(num));
            }
        }
        //輸出數值到顯示器
        private void output_digit (JButton btn){
            JLabel j = (JLabel) Button.get(0);
            j.setText(Long.toString(Long.parseLong(j.getText() + btn.getLabel())));
        }
        //把第一組數值儲存起來
        private void save_num(JButton oper){
            JLabel j = (JLabel) Button.get(0);
            num=Long.parseLong(j.getText());
            j.setText(Long.toString(0L));
        }
    }
}
