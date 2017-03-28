
import java.awt.Container;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListWords extends JFrame{
	 private static final long serialVersionUID = 1L;
     public ListWords() {
        super("List Of Words");

        Container c = getContentPane();

        Vector<String> headings = new Vector<String>();
        headings.add("Word");
        headings.add("Meaning");
        
        Vector<Vector<String>> rows = new Vector<Vector<String>>();

        TreeMap<String,String> words = Dictionary.getWords();
        for(String word :  words.keySet()) {
             Vector<String> row= new Vector<String>();
             row.add(word);
             row.add( words.get(word));
             rows.add(row);
        }

        JTable wordstable = new JTable(rows,headings);

        JScrollPane sp = new JScrollPane(wordstable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        c.add(sp);
        pack(); // get requried size based on components
    }

}
