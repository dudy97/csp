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
        int backs = 0;
        int iter = 0;
        while( i < board.size()) {
            iter++;
            if (board.get(i) == 0 || goBack) {
                if(setNumbers.contains(i) && !goBack){
                    i++;
                }
                else if(setNumbers.contains(i) && goBack){
                    i=i-1;
                    goBack = true;
                }
                int startVal = board.get(i)+1;
                if(startVal > size){
                    board.set(i, 0);
                    i = i - 2;
                    goBack = true;

                }
                for (int j = startVal; j <= size; j++) {
                    board.set(i, j);
                    if (this.checkIfCorrect(i, j)) {
                        goBack = false;
                        break;
                    }
                    if(j==size){
                        backs++;
                        board.set(i, 0);
                        i = i - 2;
                        goBack = true;
                    }
                }
            }
            i++;
        }
        System.out.println(backs);
        System.out.println(iter);
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
        l.readFromFile("D:\\JA\\Intelli_projekty\\si2\\files\\test_futo_7_0.txt");
        l.proceedLines();
        FutoshikiSolver fs = new FutoshikiSolver(l.numbers, l.limitations, l.setNumbers, l.size);
        long start = System.currentTimeMillis();
        fs.solve();
        long finish = System.currentTimeMillis();
        double timeElapsed = (finish - start)/1000.;
        System.out.println("czas = " + timeElapsed);
        fs.printBoard();
    }
}
