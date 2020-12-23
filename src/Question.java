import java.util.ArrayList;

public class Question {
    private boolean select;
    private String quesName;
    private ArrayList<String> answer;
    private int corr;

    public Question(String quesName, ArrayList<String> answer, int corr) {
        this.quesName = quesName;
        this.answer = answer;
        this.corr = corr;
    }

    public Question() {
    }

    public String getQuesName() {
        return quesName;
    }

    public void setQuesName(String quesName) {
        this.quesName = quesName;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }

    public int getCorr() {
        return corr;
    }

    public void setCorr(int corr) {
        this.corr = corr;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
