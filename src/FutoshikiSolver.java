import java.util.ArrayList;

public class FutoshikiSolver {
    ArrayList<Integer> board;
    ArrayList<Limitation> limitations;
    ArrayList<Integer> setNumbers;
    int size;

    public FutoshikiSolver(ArrayList<Integer> list, ArrayList<Limitation> limitations, ArrayList<Integer> sn, int s){
        board = list;
        size = s;
        this.limitations = limitations;
        setNumbers = sn;
    }

    public void solve() {
        boolean goBack = false;
        int i = 0;
        while( i < board.size()) {
            if (board.get(i) == 0 || goBack) {
                int startVal = board.get(i)+1;
                if(startVal > size){
                    board.set(i, 0);
                    i = i - 2;
                }
                for (int j = startVal; j <= size; j++) {
                    board.set(i, j);
                    if (this.checkIfCorrect(i, j)) {
                        goBack = false;
                        break;
                    }
                    if(j==size){
                        board.set(i, 0);
                        i = i - 2;
                        goBack = true;
                    }
                }
            }
            i++;
        }
    }

    public boolean checkIfCorrect(int index, int num){
        int row = index/size;
        int col = index%size;
        for(int i = col; i < board.size(); i = i + size) {
            if (board.get(i) == num && i != index)
                return false;
        }
        for(int i = 0; i < size; i++) {
            int currIdx = size * row + i;
            if (board.get(currIdx) == num && currIdx != index)
                return false;
        }
        if(index==12){
//            System.out.println("Smaller " + limitations.get(5).smaller);
//            System.out.println(index);
//            System.out.println(num);
//            System.out.println("Bigger " + limitations.get(5).bigger);
//            System.out.println(board.get(limitations.get(5).bigger));
//            System.out.println(board.get(limitations.get(5).bigger) != 0);
            System.out.println((limitations.get(5).smaller == index && num>board.get(limitations.get(5).bigger) && board.get(limitations.get(5).bigger) != 0));
            System.out.println((limitations.get(5).bigger == index && num < board.get(limitations.get(5).smaller) && board.get(limitations.get(5).smaller) != 0));
        }
        for(int i = 0; i< limitations.size(); i++){
            if((limitations.get(i).bigger == index && num<board.get(limitations.get(i).smaller) && board.get(limitations.get(i).smaller)!= 0) ||
                    (limitations.get(i).smaller == index && num>board.get(limitations.get(i).bigger) && board.get(limitations.get(i).bigger) != 0)){
                return false;
            }

        }
        return true;
    }

    public void printBoard(){
            for (int i = 0; i < board.size(); i++){
                if(i%size==0 && i!= 0)
                    System.out.println();
                System.out.print(board.get(i) + " ");
            }
    }

    public static void main(String[] args) {
        LoaderFutoshiki l = new LoaderFutoshiki();
        l.readFromFile("D:\\JA\\Intelli_projekty\\si2\\files\\test_futo_5_2.txt");
        l.proceedLines();
        FutoshikiSolver fs = new FutoshikiSolver(l.numbers, l.limitations, l.setNumbers, l.size);
        fs.solve();
        fs.printBoard();
    }
}
