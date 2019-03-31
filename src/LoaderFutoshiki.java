import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class LoaderFutoshiki {
    int size;
    ArrayList<Integer> numbers;
    ArrayList<String> lines;
    ArrayList<Limitation> limitations;

    public LoaderFutoshiki(){
        size = 0;
        numbers = new ArrayList<>();
        lines = new ArrayList<>();
        limitations = new ArrayList<>();
    }

    public void readFromFile(String fileName){
        File file = new File(fileName);
        String line = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
//        System.out.println(lines);
    }

    public void proceedLines(){
        size = Integer.parseInt(lines.get(0));
        for(int i = 2; i < 2 + size; i++){
            String[] s = lines.get(i).split(";");
            for(int j = 0; j<s.length; j++)
                numbers.add(Integer.valueOf(s[j]));
        }
        for(int i = 3 + size; i < lines.size(); i++){
            String[] s = lines.get(i).split(";");
                String temp = s[0];
                int smaller = 0, bigger = 0;
                switch (temp.charAt(0)){
                    case 'A':
                        smaller = Character.getNumericValue(temp.charAt(1)) - 1;
                        break;
                    case 'B':
                        smaller = Character.getNumericValue(temp.charAt(1)) + size - 1;
                        break;
                    case 'C':
                        smaller = Character.getNumericValue(temp.charAt(1)) + 2*size - 1;
                        break;
                    case 'D':
                        smaller = Character.getNumericValue(temp.charAt(1)) + 3*size - 1;
                        break;
                }
                temp = s[1];
            switch (s[1].charAt(0)){
                case 'A':
                    bigger = Character.getNumericValue(temp.charAt(1)) - 1;
                    break;
                case 'B':
                    bigger = Character.getNumericValue(temp.charAt(1)) + size - 1;
                    break;
                case 'C':
                    bigger = Character.getNumericValue(temp.charAt(1)) + 2*size - 1;
                    break;
                case 'D':
                    bigger = Character.getNumericValue(temp.charAt(1)) + 3*size - 1;
                    break;
            }
            limitations.add(new Limitation(smaller, bigger));
                smaller = 0;
                bigger = 0;
        }
//        System.out.println("Size = " + size);
//        System.out.println(numbers);
//        System.out.println(limitations);

    }

    public static void main(String[] args) {
        LoaderFutoshiki l = new LoaderFutoshiki();
        l.readFromFile("D:\\JA\\Intelli_projekty\\si2\\files\\test_futo_4_0.txt");
        l.proceedLines();
    }
}
