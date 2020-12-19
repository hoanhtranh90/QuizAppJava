import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Index {
    private JPanel panel;
    private JButton quesA;
    private JButton quesB;
    private JButton quesC;
    private JButton quesD;

    public Index() {
        quesA.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setTimeout(()->quesA.setText("Đáp án D"),1000);

            }
        });
    }

    public static void main(String[] args) {
        HashMap<String,Question> questions = new HashMap<>();

        int lineNumber = 1;
        try {
            File myObj = new File("E:\\javaSource\\QuizApp\\src\\ques.txt");
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
                int corr = Integer.parseInt(data[5])-1;
                question.setAnswer(ans);
                question.setCorr(corr);
                questions.put("question"+lineNumber,question);
                lineNumber++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        questions.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue().getQuesName());
        });
        JFrame jFrame = new JFrame("QuizApp");
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(new Index().panel);
        jFrame.setVisible(true);
        jFrame.setSize(400,400);
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
