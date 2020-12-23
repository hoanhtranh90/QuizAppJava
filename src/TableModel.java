import java.io.*;
import java.util.*;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private List<Question> data;
    private List<String> columnNames;

    public TableModel() {
        data = getTableDataList();
        columnNames = getColumnNamesList();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Boolean.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            case 6:
                return Integer.class;
            default:
                return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0 ? true : false;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return data.get(rowIndex).isSelect();
            case 1:
                return data.get(rowIndex).getQuesName();
            case 2:
                return data.get(rowIndex).getAnswer().get(0);
            case 3:
                return data.get(rowIndex).getAnswer().get(1);
            case 4:
                return data.get(rowIndex).getAnswer().get(2);
            case 5:
                return data.get(rowIndex).getAnswer().get(3);
            case 6:
                return data.get(rowIndex).getCorr();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                data.get(rowIndex).setSelect((Boolean) aValue);
                break;
//            case 1:
//                data.get(rowIndex).setQuesName(aValue != null ? aValue.toString() : null);
//                break;
//            case 2:
//                data.get(rowIndex).(aValue != null ? Integer.parseInt(aValue.toString()) : null);
//                break;
            default:
                break;
        }
    }

    public void addRow(Question do1) {
        data.add(do1);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void deleteRow() throws IOException {
        for(int rowIndex = data.size() - 1; rowIndex >= 0; rowIndex--) {
            if (data.get(rowIndex).isSelect()) {
                System.out.println(rowIndex);
                data.remove(rowIndex);

                File inputFile = new File("src/ques.txt");
                File tempFile = new File("src/quesTemp.txt");

                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                int lineToRemove = rowIndex+1;
                String currentLine;
                int count = 0;

                while ((currentLine = reader.readLine()) != null) {
                    count++;
                    if (count == lineToRemove) {
                        continue;
                    }
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                writer.close();
                reader.close();
                inputFile.delete();
                tempFile.renameTo(inputFile);

            }}
        fireTableDataChanged();
    }

    private List<Question> getTableDataList() {

        List<Question> list = new ArrayList<Question>();

//        StudentDO do1 = null;
//        for(int i = 0; i < 5; i++) {
//
//            do1 = new StudentDO();
//            do1.setSelect(false);
//            do1.setName("Student " + i);
//            do1.setAge(i);
//
//            list.add(do1);
//        }
        int[] keys;
        Question[] values;
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
        Question do1 = null;
        for(int i = 0; i < values.length; i++) {

            do1 = new Question();
            do1.setSelect(false);
            do1.setQuesName(values[i].getQuesName());
            do1.setCorr(values[i].getCorr());
            do1.setAnswer(values[i].getAnswer());

            list.add(do1);
        }
        return list;
    }

    private List<String> getColumnNamesList() {
        List<String> names = new ArrayList<String>();

        names.add("Select");
        names.add("Tên câu hỏi");
        names.add("Đáp án A");
        names.add("Đáp án B");
        names.add("Đáp án C");
        names.add("Đáp án D");
        names.add("Ansert");


        return names;
    }
}