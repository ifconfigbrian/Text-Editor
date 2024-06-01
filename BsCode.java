import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class BsCode extends JFrame implements ActionListener{
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu,editMenu,selectionMenu,viewMenu,goMenu,runMenu,terminalMenu,helpMenu;
    private JMenuItem newItem,openItem,saveItem,exitItem;
    private JMenuItem cutItem,copyItem,pasteItem,redoItem,undoItem;
    private JMenuItem fontItem;
    private UndoManager undoManager;

    public BsCode(){
        setTitle("BS Code");
        //initialzing text area..
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        add(new JScrollPane(textArea),BorderLayout.CENTER);
        //initialize menu bar
        menuBar = new JMenuBar();
        //initialize file menu
        fileMenu = new JMenu("File");
        newItem = new JMenuItem("New");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");
        //add action listeners to the items
        newItem.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);
        //add items to fileMenu
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        //add fileMenu to menuBar
        menuBar.add(fileMenu);
        //initialize edit menu
        editMenu = new JMenu("Edit");
        cutItem = new JMenuItem("Cut");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");
        undoItem = new JMenuItem("Undo");
        redoItem = new JMenuItem("Redo");
        //add action listeners
        cutItem.addActionListener(this);
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);
        undoItem.addActionListener(this);
        redoItem.addActionListener(this);
        //add to the menu
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.addSeparator();
        editMenu.add(undoItem);
        editMenu.add(redoItem);
        //add to menubar
        menuBar.add(editMenu);
        //initialize selection menu
        selectionMenu = new JMenu("Selection");
        fontItem = new JMenuItem("Font");
        fontItem.addActionListener(this);
        selectionMenu.add(fontItem);
        menuBar.add(selectionMenu);
        //initialize view menu
        viewMenu = new JMenu("View");
        menuBar.add(viewMenu);
        //initialize go menu
        goMenu = new JMenu("Go");
        menuBar.add(goMenu);
        //initialize run menu
        runMenu = new JMenu("Run");
        menuBar.add(runMenu);
        //initialize terminal menu
        terminalMenu = new JMenu("Terminal");
        menuBar.add(terminalMenu);
        //initialize help menu
        helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        //set menubar
        setJMenuBar(menuBar);
        //initialize undo manager..
        undoManager = new UndoManager();
        //edit listener
        textArea.getDocument().addUndoableEditListener(new UndoableEditListener(){
            public void undoableEditHappened(UndoableEditEvent e){
                undoManager.addEdit(e.getEdit());
            }
        });
        setDefaultCloseOperation(3);
        setSize(900,700);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e){
        JMenuItem source = (JMenuItem)e.getSource();
        if(source == newItem){
            textArea.setText("");
        }else if(source == openItem){
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);
            if(option == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                try(BufferedReader reader = new BufferedReader(new FileReader(file))){
                    textArea.read(reader,null);
                }catch(IOException ioException){
                    JOptionPane.showMessageDialog(this,"Oops! Could not open file!!","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }else if(source == saveItem){
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(this);
            if(option == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                try(BufferedWriter writer = new BufferedWriter(new FileWriter (file))){
                    textArea.write(writer);
                }catch(IOException ioException){
                    JOptionPane.showMessageDialog(this,"Oops! Could not save file!!","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }else if (source == exitItem){
            System.exit(0);
        }else if(source == cutItem){
            textArea.cut();
        }else if(source == copyItem){
            textArea.copy();
        }else if(source == pasteItem){
            textArea.paste();
        }else if(source == undoItem){
            if(undoManager.canUndo()){
                undoManager.undo();
            }
        }else if(source == redoItem){
            if(undoManager.canRedo()){
                undoManager.redo();
            }
        }else if(source == fontItem){
            System.out.println("coming soon");
        }
    }
    // private void showFontDialog(){
    //     JFontChooser fontChooser = new JFontChooser();
    //     int option = fontChooser.showDialog(this);
    //     if(option == JFontChooser.OK_OPTION){
    //         Font font = fontChooser.getSelectedFont();
    //         textArea.setFont(font);
    //     }
    // }
    public static void main(String[]args){
        new BsCode();
    }

}