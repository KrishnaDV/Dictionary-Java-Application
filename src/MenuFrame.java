
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public class MenuFrame extends JFrame {
	private static final long serialVersionUID = 1L;
    public MenuFrame() throws Exception {
        super("Dictionary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar mb = new JMenuBar();
        // create menu
        JMenu mnuDictionary = new JMenu("Dictionary");
        mb.add(mnuDictionary);

        // options in Dictionary Menu
        JMenuItem option = new JMenuItem("Add Word...");
        option.setIcon( getImage("add.gif"));
        option.setAccelerator( KeyStroke.getKeyStroke("F5"));
        mnuDictionary.add(option);
        option.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addWord();
            }
        });

        // options in Dictionary Menu
        option = new JMenuItem("Delete Word...");
        option.setIcon( getImage("delete.gif"));
        option.setAccelerator( KeyStroke.getKeyStroke("F6"));
        mnuDictionary.add(option);
        option.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deleteWord();
            }
        });

        mnuDictionary.addSeparator();

        // options in Dictionary Menu
        option = new JMenuItem("Search Word...");
        option.setIcon( getImage("search.gif"));
        option.setAccelerator( KeyStroke.getKeyStroke("F7"));
        mnuDictionary.add(option);
        option.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                searchWord();
            }
        });


        option = new JMenuItem("List Words");
        option.setIcon( getImage("list.gif"));
        option.setAccelerator( KeyStroke.getKeyStroke("F8"));
        mnuDictionary.add(option);
        option.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                listWords();
            }
        });

        mnuDictionary.addSeparator();

        option = new JMenuItem("Exit");
        mnuDictionary.add(option);
        option.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });

        addStorageMenu(mb);
        addToolbar();
        setJMenuBar(mb);

        // load dictionary from disk
        Dictionary.loadFromDisk();

    }

    public void exit() {
        if (Dictionary.isModified()) {
            int option = JOptionPane.showConfirmDialog(MenuFrame.this, "You have some pending changes. Do you want to write them to disk and then exit?",
                    "Save", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (option == JOptionPane.YES_OPTION) {  // exit after save
                Dictionary.saveToDisk();
                System.exit(0);
            }
            else if (option == JOptionPane.NO_OPTION) // exit without saving
            {
               System.exit(0);
            }
        // if cancel then do not exit
        } else {
            System.exit(0);
        }
    }

    public ImageIcon getImage(String filename){
    	try {
        return new ImageIcon(
                this.getClass().getResource(filename));
    	}
    	catch(Exception ex) {
    		System.out.println( ex.getMessage());
    		return null;
    	}
    }

    public void centerToParent(JFrame parent, JFrame child) {
        Dimension pd = parent.getSize();
        Dimension cd = child.getSize();
        int x = (int) (pd.getWidth() - cd.getWidth()) / 2;
        int y = (int) (pd.getHeight() - cd.getHeight()) / 2;
        child.setLocation(x, y);

    }

    public void addWord() {
        AddWord w = new AddWord();
        centerToParent(MenuFrame.this, w);
        w.setVisible(true);
    }

    public void deleteWord() {
        DeleteWord w = new DeleteWord();
        centerToParent(MenuFrame.this, w);
        w.setVisible(true);
    }

    public void searchWord() {
        SearchWord w = new SearchWord();
        centerToParent(MenuFrame.this, w);
        w.setVisible(true);
    }

    public void listWords() {
        ListWords w = new ListWords();
        w.setVisible(true);
        centerToParent(MenuFrame.this, w);
    }

    public void addToolbar() {
        JToolBar tb = new JToolBar();
        JButton b = new JButton( getImage("add.gif"));
        b.setPreferredSize( new Dimension(32,32));
        tb.add(b);
        b.setToolTipText("Add Word");
        b.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 addWord();
            }

        });

        b = new JButton( getImage("delete.gif"));
        b.setPreferredSize( new Dimension(32,32));
        tb.add(b);
        b.setToolTipText("Delete Word");
        b.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 deleteWord();
            }

        });

        b = new JButton( getImage("search.gif"));
        b.setPreferredSize( new Dimension(32,32));
        tb.add(b);
        b.setToolTipText("Search Word");
        b.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 searchWord();
            }

        });


        b = new JButton( getImage("list.gif"));
        tb.add(b);
        b.setToolTipText("List Words");
        b.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 listWords();
            }

        });

        tb.addSeparator();

        b = new JButton( getImage("save.gif"));
        tb.add(b);
        b.setToolTipText("Save Dictionary To Disk");
        b.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 Dictionary.saveToDisk();
            }

        });

        b = new JButton( getImage("load.gif"));
        tb.add(b);
        b.setToolTipText("Load Dictionary From Disk");
        b.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 Dictionary.loadFromDisk();
            }

        });

        getContentPane().add(tb, BorderLayout.NORTH);
    }
    //
    public void addStorageMenu(JMenuBar mb) {

        JMenu mnuStorage = new JMenu("Storage");

        // options in Storage Menu
        JMenuItem option = new JMenuItem("Save Dictionary");
        option.setIcon( getImage("save.gif"));
        option.setAccelerator( KeyStroke.getKeyStroke("F2"));
        mnuStorage.add(option);
        option.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = Dictionary.saveToDisk();
                if (result) {
                    JOptionPane.showMessageDialog(MenuFrame.this, "Saved Dictionary Successfully!", "Feedback",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(MenuFrame.this, "Could Not Save Dictionary Successfully! Error --> " + Dictionary.getMessage(), "Feedback",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        option = new JMenuItem("Load Dictionary");
        option.setIcon( getImage("load.gif"));
        option.setAccelerator( KeyStroke.getKeyStroke("F3"));
        mnuStorage.add(option);
        option.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = Dictionary.loadFromDisk();
                if (result) {
                    JOptionPane.showMessageDialog(MenuFrame.this, "Loaded Dictionary Successfully!", "Feedback",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(MenuFrame.this, "Could Not Load Dictionary Successfully! Error --> " + Dictionary.getMessage(), "Feedback",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        mb.add(mnuStorage);

    }

    public static void main(String[] args) throws Exception {
        MenuFrame f = new MenuFrame();
        f.setVisible(true);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
}
