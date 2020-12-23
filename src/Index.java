import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;

import static java.awt.Color.*;

public class Index {
    static JFrame goQuiz = new JFrame("QuizApp");
    static JFrame scopeFrame = new JFrame("QuizApp");
    static JFrame homeFrame = new JFrame("Home");
    static JFrame manageFrame = new JFrame("Manage");

    //goQuiz
    private JPanel panel;
     private JButton quesA;
     private JButton quesB;
     private JButton quesC;
     private JButton quesD;
     private JLabel label1;

     //scope
    private JPanel panel1;
    private JLabel label2;

    //home
    private JPanel home;
    private JButton goTest;

    //QuesManage
    private JPanel panelManage;
    private JButton goQuanLy;
    private JTable table1;

    int[] keys;
    Question[] values;
    int numberQues = 0;
    int anser = 1;
    String Dapan="";
    int scope = 0;



    public Index() {

        quesA.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);
                if (quesA.getText().equals(Dapan))
                {
                    quesA.setBackground(GREEN);
                    scope++;
                    System.out.println("Dung");
                }
                else {
                    System.out.println("sai");
                    quesA.setBackground(RED);
                }
                setTimeout(()->setBackGroung(quesA) ,1000);
                setTimeout(()->Ques(++numberQues) ,1200);
                setTimeout(()->changeScrrn(numberQues, values.length) ,1500);
                if(numberQues == values.length-1) {
                    panel1.setVisible(true);
                    label2.setText("so diem cua ban la " + scope);
                    scopeFrame.setContentPane(panel1);
                };
            }
        });
        quesB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (quesB.getText().equals(Dapan))
                {
                    quesB.setBackground(GREEN);
                    scope++;
                    System.out.println("Dung");
                }
                else {
                    System.out.println("sai");
                    quesB.setBackground(RED);
                }
                setTimeout(()->setBackGroung(quesB) ,1000);
                setTimeout(()->Ques(++numberQues) ,1200);
                setTimeout(()->changeScrrn(numberQues, values.length) ,1500);
                if(numberQues == values.length-1) {
                    panel1.setVisible(true);
                    label2.setText("so diem cua ban la " + scope);
                    scopeFrame.setContentPane(panel1);
                };
            }
        });
        quesC.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (quesC.getText().equals(Dapan))
                {
                    quesC.setBackground(GREEN);
                    scope++;
                    System.out.println("Dung");
                }
                else {
                    System.out.println("sai");
                    quesC.setBackground(RED);
                }
                setTimeout(()->setBackGroung(quesC) ,1000);
                setTimeout(()->Ques(++numberQues) ,1200);
                setTimeout(()->changeScrrn(numberQues, values.length) ,1500);
                if(numberQues == values.length-1) {
                    panel1.setVisible(true);
                    label2.setText("so diem cua ban la " + scope);
                    scopeFrame.setContentPane(panel1);
                };
            }
        });
        quesD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (quesD.getText().equals(Dapan))
                {
                    System.out.println("Dung");
                    quesD.setBackground(GREEN);
                    scope++;
                }
                else {
                    System.out.println("sai");
                    quesD.setBackground(RED);
                }
                setTimeout(()->setBackGroung(quesD) ,1000);
                setTimeout(()->Ques(++numberQues) ,1200);
                setTimeout(()->changeScrrn(numberQues, values.length) ,1500);


                if(numberQues == values.length-1) {
                    panel1.setVisible(true);
                    label2.setText("so diem cua ban la " + scope);
                    scopeFrame.setContentPane(panel1);
                };
            }
        });
        Map<Integer,Question> questions = new HashMap<>();

        int lineNumber = 1;
        try {
//            Path path = Paths.get("ques.txt");
            File myObj = new File("src/ques.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                Question question = new Question();
                String[] data = myReader.nextLine().split("/");

                question.setQuesName(data[0]);
                ArrayList<String> ans = new ArrayList<>();
                ans.add(data[1]);
                ans.add(data[2]);
                ans.add(data[3]);
                ans.add(data[4]);
                int corr = Integer.parseInt(data[5]);
                question.setAnswer(ans);
                question.setCorr(corr);
                questions.put(lineNumber,question);
                lineNumber++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


         keys = new int[questions.size()];
         values = new Question[questions.size()];
        int index = 0;
        for (Map.Entry<Integer, Question> mapEntry : questions.entrySet()) {
            keys[index] = mapEntry.getKey();
            values[index] = mapEntry.getValue();
            index++;
        }

        panel1.setVisible(false);
        panel1.setSize(0,0);
        label2.setFont (label2.getFont ().deriveFont (30.0f));
           Ques(numberQues);
//


        goTest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                homeFrame.setVisible(false);
                home.setVisible(false);
                manageFrame.setVisible(false);
                panelManage.setVisible(false);
                goQuiz.setVisible(true);
                goQuiz.setContentPane(panel);
            }
        });
        goQuanLy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                homeFrame.setVisible(false);
                home.setVisible(false);

                manageFrame.setVisible(true);
//                manageFrame.setContentPane(panel);
            }
        });
    }
    void setBackGroung(JButton name){
        name.setBackground(null);
    };
    void changeScrrn(int currQues,int length){
        if (currQues == length) {
            goQuiz.setVisible(false);
            scopeFrame.setVisible(true);
        };
    }
    void Ques(int currQues){

        label1.setText(values[currQues].getQuesName());
        quesA.setText(values[currQues].getAnswer().get(0));
        quesB.setText(values[currQues].getAnswer().get(1));
        quesC.setText(values[currQues].getAnswer().get(2));
        quesD.setText(values[currQues].getAnswer().get(3));
        anser = values[currQues].getCorr();
        Dapan=values[currQues].getAnswer().get(anser-1);
        System.out.println(Dapan);
        System.out.println(anser);

    }


    public static void main(String[] args) {

        scopeFrame.pack();
        scopeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scopeFrame.setVisible(false);
        scopeFrame.setSize(400,400);

        homeFrame.pack();
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setVisible(true);
        homeFrame.setContentPane(new Index().home);
        homeFrame.setSize(400,400);

        goQuiz.pack();
        goQuiz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        goQuiz.setVisible(false);
        goQuiz.setSize(400,400);



        manageFrame.pack();
        manageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        manageFrame.setVisible(false);
        manageFrame.setSize(1000,400);
        generateJTable();


    }

    public static void generateJTable() {

        manageFrame.setLayout(new BorderLayout());

        JButton addBtn = new JButton("Add");
        JButton delBtn = new JButton("Delete");

        TextField quesName = new TextField("",20);
        TextField answerA = new TextField();
        TextField answerB = new TextField();
        TextField answerC = new TextField();
        TextField answerD = new TextField();
        TextField correct = new TextField();
        JPanel buttonPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel addQues = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addQues.add(quesName);

        buttonPnl.add(addBtn);
        buttonPnl.add(delBtn);

        quesName.setSize(400,100);
        buttonPnl.add(quesName);
        buttonPnl.add(answerA);
        buttonPnl.add(answerB);
        buttonPnl.add(answerC);
        buttonPnl.add(answerD);
        buttonPnl.add(correct);

        final TableModel tableModel = new TableModel();
        JTable table = new JTable();
        table.setModel(tableModel);
        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);

        addBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Question do1 = new Question();
                do1.setQuesName(quesName.getText());
                ArrayList<String> anw = new ArrayList<>();
                anw.add(answerA.getText());
                anw.add(answerB.getText());
                anw.add(answerC.getText());
                anw.add(answerD.getText());
                do1.setAnswer(anw);
                do1.setCorr(Integer.parseInt(correct.getText()));
                tableModel.addRow(do1);

                BufferedWriter bw = null;
                FileWriter fw = null;

                try {
                    String data = "";
                    data = quesName.getText() + "/" + answerA.getText() + "/" + answerB.getText() + "/"
                            + answerC.getText() + "/" + answerD.getText() + "/" + correct.getText() ;
                    System.out.println(data);
                    File file = new File("src/ques.txt");
                    // if file doesnt exists, then create it
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    // true = append file
                    fw = new FileWriter(file.getAbsoluteFile(), true);
                    bw = new BufferedWriter(fw);
                    bw.write(data);
                    bw.write("\n");
                    System.out.println("Success...");
                } catch (IOException exception) {
                    exception.printStackTrace();
                } finally {
                    try {
                        if (bw != null)
                            bw.close();
                        if (fw != null)
                            fw.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } }
                });

        delBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tableModel.deleteRow();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        manageFrame.add(table.getTableHeader(), BorderLayout.NORTH);
        manageFrame.add(table, BorderLayout.CENTER);
        manageFrame.add(buttonPnl, BorderLayout.SOUTH);
    }




    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
}
