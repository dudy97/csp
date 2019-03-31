import java.util.ArrayList;

public class FutoshikiSolver {
    ArrayList<Integer> board;
    ArrayList<Limitation> limitations;
    int size;

    public FutoshikiSolver(ArrayList<Integer> list, ArrayList<Limitation> limitations, int s){
        board = list;
        size = s;
        this.limitations = limitations;
    }

    public void solve(){
        for(int i = 0; i < board.size(); i++){
                if (board.get(i) == 0)
                    for (int j = 1; j <= size; j++) {
                        board.set(i, j);
                        if (this.checkIfCorrect(i, j)) {
                            break;
                        }
                    }
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
        l.readFromFile("D:\\JA\\Intelli_projekty\\si2\\files\\test_futo_9_2.txt");
        l.proceedLines();
        FutoshikiSolver fs = new FutoshikiSolver(l.numbers, l.limitations, l.size);
        fs.solve();
        fs.printBoard();
    }
}