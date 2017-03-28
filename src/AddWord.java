
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddWord extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextField tfWord;
    private JTextArea  taMeaning;
    private JButton btnAdd;

    public AddWord() {
        super("Add Word");

        GridBagLayout gbl  = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.BOTH;

        tfWord = new JTextField(30);
        taMeaning = new JTextArea();
        btnAdd = new JButton("Add Word");
        btnAdd.addActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                 if (  tfWord.getText().length() > 0 && taMeaning.getText().length() > 0  ) {
                      Dictionary.addWord(tfWord.getText(), taMeaning.getText());
                      JOptionPane.showMessageDialog( AddWord.this, "Added Word Successfully!","Add Word", JOptionPane.INFORMATION_MESSAGE);
                      tfWord.setText("");
                      taMeaning.setText("");
                      tfWord.requestFocus();
                 }
                 else {
                     JOptionPane.showMessageDialog( AddWord.this, "Please enter word and meaning!","Add Word", JOptionPane.ERROR_MESSAGE);
                     tfWord.requestFocus();
                 }


            }
         }
        );

        Container c = getContentPane();
        c.setLayout(gbl);

        // add tfWord
        gbc.anchor = GridBagConstraints.EAST;
        c.add( new JLabel("Enter Word :"),gbc);
        gbc.anchor = GridBagConstraints.WEST;
        c.add(tfWord);

        // add taMeaning
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        c.add( new JLabel("Enter Meaning :"), gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        taMeaning.setRows(3);
        taMeaning.setColumns(30);
        JScrollPane sp = new JScrollPane(taMeaning, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        c.add(sp, gbc);

        // add button
        gbc.gridx  = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        c.add(btnAdd,gbc);

        pack(); // get requried size based on components
    }

}
