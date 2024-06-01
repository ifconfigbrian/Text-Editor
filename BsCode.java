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
    private JMenuItem newTextFile,newFile,newWindow,openFile,openFolder,openWorkspace,openRecent,addFolderToWorkSpace,saveWorkSpaceAs,
                     duplicateWorkSpace,saveItem,saveAs,saveAll,share,autoSave,preferences,revertFile,closeEditor,closeFolder,closeWindow,Exit;
    private JMenuItem cutItem,copyItem,pasteItem,redoItem,undoItem,find,replace,findInFiles,replaceInFiles,toggleLineComment,toggleBlockComment,emmet;
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
        newTextFile = new JMenuItem("New Text File");
        newFile = new JMenuItem("New File");
        newWindow = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        openFolder = new JMenuItem("Open Folder");
        openWorkspace = new JMenuItem("Open Workspace from File");
        openRecent = new JMenuItem("Open Recent");
        addFolderToWorkSpace = new JMenuItem("Add Folder to Workspace");
        saveWorkSpaceAs = new JMenuItem("Save Workspace As");
        duplicateWorkSpace = new JMenuItem("Duplicate Workspace");
        saveItem = new JMenuItem("Save");
        saveAs = new JMenuItem("Save As");
        saveAll = new JMenuItem("Save All");
        share = new JMenuItem("Share");
        autoSave = new JMenuItem("Auto Save");
        preferences = new JMenuItem("Preferences");
        revertFile = new JMenuItem("Revert File");
        closeEditor = new JMenuItem("Close Editor");
        closeFolder = new JMenuItem("Close Folder");
        closeWindow = new JMenuItem("Close Window");
        Exit = new JMenuItem("Exit");
        
        //add action listeners to the items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveItem.addActionListener(this);
        Exit.addActionListener(this);
        //add items to fileMenu
        fileMenu.add(newTextFile);
        fileMenu.add(newFile);
        fileMenu.add(newWindow);
        fileMenu.addSeparator();
        fileMenu.add(openFile);
        fileMenu.add(openFolder);
        fileMenu.add(openWorkspace);
        fileMenu.add(openRecent);
        fileMenu.addSeparator();
        fileMenu.add(addFolderToWorkSpace);
        fileMenu.add(saveWorkSpaceAs);
        fileMenu.add(duplicateWorkSpace);
        fileMenu.addSeparator();
        fileMenu.add(saveItem);
        fileMenu.add(saveAs);
        fileMenu.add(saveAll);
        fileMenu.addSeparator();
        fileMenu.add(share);
        fileMenu.addSeparator();
        fileMenu.add(autoSave);
        fileMenu.add(preferences);
        fileMenu.addSeparator();
        fileMenu.add(revertFile);
        fileMenu.add(closeEditor);
        fileMenu.add(closeFolder);
        fileMenu.add(closeWindow);
        fileMenu.addSeparator();
        fileMenu.add(Exit);
        //add fileMenu to menuBar
        menuBar.add(fileMenu);
        //initialize edit menu
        //find,replace,findInFiles,replaceInFiles,toggleLineComment,toggleBlockComment,emmet
        editMenu = new JMenu("Edit");
        cutItem = new JMenuItem("Cut");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");
        undoItem = new JMenuItem("Undo");
        redoItem = new JMenuItem("Redo");
        find = new JMenu("Find");
        replace = new JMenu("Replace");
        findInFiles= new JMenu("Find In Files");
        replaceInFiles = new JMenu("Replace In Files");
        toggleLineComment = new JMenu("Toggle Line Comment");
        toggleBlockComment = new JMenu("Toggle Block Comment");
        emmet = new JMenu("Emmet:Expand Abbreviation");
        //add action listeners
        cutItem.addActionListener(this);
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);
        undoItem.addActionListener(this);
        redoItem.addActionListener(this);
        //add to the menu
        editMenu.add(undoItem);
        editMenu.add(redoItem);
        editMenu.addSeparator();
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.addSeparator();
        editMenu.add(find);
        editMenu.add(replace);
        editMenu.addSeparator();
        editMenu.add(findInFiles);
        editMenu.add(replaceInFiles);
        editMenu.addSeparator();
        editMenu.add(toggleLineComment);
        editMenu.add(toggleBlockComment);
        editMenu.add(emmet);
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
        setSize(900,600);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e){
        JMenuItem source = (JMenuItem)e.getSource();
        if(source == newFile){
            textArea.setText("");
        }else if(source == openFile){
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
        }else if (source == Exit){
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
    public static void main(String[]args){
        new BsCode();
    }

}