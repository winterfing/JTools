package tools.TextAreaWindow;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//实现接口ActionListener
public class TextAreaWindow implements ActionListener
{

    JFrame jFrame;
    JPanel jpanel;
    JButton jButton1, jButton2, jButton3;
    JTextArea jTextAre = null;
    JScrollPane jscrollPane;

    public TextAreaWindow()
    {

        jFrame = new JFrame("JTextArea");
        Container contentPane = jFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        jTextAre = new JTextArea(10, 15);
        jTextAre.setTabSize(4);
        jTextAre.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 16));
        jTextAre.setLineWrap(true);// 激活自动换行功能
        jTextAre.setWrapStyleWord(true);// 激活断行不断字功能
        jTextAre.setBackground(Color.pink);

        jscrollPane = new JScrollPane(jTextAre);
        jpanel = new JPanel();
        jpanel.setLayout(new GridLayout(1, 3));

        jButton1 = new JButton("复制");
        jButton1.addActionListener(this);
        jButton2 = new JButton("粘贴");
        jButton2.addActionListener(this);
        jButton3 = new JButton("剪切");
        jButton3.addActionListener(this);

        jpanel.add(jButton1);
        jpanel.add(jButton2);
        jpanel.add(jButton3);

        contentPane.add(jscrollPane, BorderLayout.CENTER);
        contentPane.add(jpanel, BorderLayout.SOUTH);

        jFrame.setSize(400, 300);
        jFrame.setLocation(400, 200);
        jFrame.setVisible(true);
        jFrame.setAlwaysOnTop(true);

        jFrame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
    }

    // 覆盖接口ActionListener的方法actionPerformed
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == jButton1)
        {
            jTextAre.copy();
        }
        else if (e.getSource() == jButton2)
        {
            jTextAre.paste();
        }
        else if (e.getSource() == jButton3)
        {
            jTextAre.cut();
        }
    }

    public static void main(String[] args)
    {
        new TextAreaWindow();
    }
}