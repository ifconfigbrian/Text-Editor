// import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
// import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.*;

public class BsCode extends JFrame implements ActionListener{
    private JTextArea textArea;
    // private RSyntaxTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu,editMenu,selectionMenu,viewMenu,goMenu,runMenu,terminalMenu,helpMenu,formatMenu;
    //items for file menu
    private JMenuItem newTextFile,newFile,newWindow,openFile,openFolder,openWorkspace,openRecent,addFolderToWorkSpace,saveWorkSpaceAs,
                     duplicateWorkSpace,saveItem,saveAs,saveAll,share,autoSave,preferences,revertFile,closeEditor,closeFolder,closeWindow,Exit;
    //items for edit menu
    private JMenuItem cutItem,copyItem,pasteItem,redoItem,undoItem,findItem,replaceItem,findInFilesItem,replaceInFilesItem,toggleLineCommentItem,
                      toggleBlockCommentItem,emmetItem;
    //items for selection menu
    private JMenuItem selectAll,expandSelection,shrinkSelection,copyLineUp,copyLineDown,moveLineUp,moveLineDown,duplicateSelection,addCursorAbove,
                       addCursorBelow,addCursorToLineEnd,addNextOccurence,addPreviousOccurence,selectAllOccurences,switchToCtrl,columnSelectionMode;
    //items for view menu
    private JMenuItem commandPall,openView,appearance,editLayout,explorer,search,sourceControl,run,extensions,testing,problems,output,debugConsole,
                      terminal,wordWrap;
    //items for go menu
    private JMenuItem back,forwad,lastEditLocation,switchEditor,switchGroup,goToFile,goToSymbolInWorkspace,goToSymbolInEditor,defination,declaration,
                      typeDefination,implementations,references,goToLineColumn,bracket,nextProblem,previousProblem,nextChange,previousChange; 
    //items for run menu
    private JMenuItem startDebugging,runWithout,stopDebugging,restartDebugging,openConfigurations,addConfiguration,stepOver,stepInto,stepOut,continuee,
                       toggleBreakpoint,newBreakpoint,enableAll,disableAll,removeAll,installAdditional;
    //items for terminal
    private JMenuItem newTerminal,splitTerminal,runTask,runBuildTask,runActive,runSelected,showRunning,restartRunning,terminateTask,configureTasks,configureDefault;
    //items for help
    private JMenuItem welcome,showAllCommands,documentation,editorPlayGround,showReleaseNotes,keyboardShortcuts,videoTutorials,tipsTricks,reportIssue,devTools,updates,about;
    //items for format menu
    private JMenuItem fontItem,fontSizeItem;                                   
    private UndoManager undoManager;

    public BsCode(){
        setTitle("BS Code");
        // textArea = new RSyntaxTextArea();
        //initialzing text area..
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(Color.LIGHT_GRAY);;
        add(new JScrollPane(textArea),BorderLayout.CENTER);
        //initialize menu bar
        menuBar = new JMenuBar();
        //initialize file menu
        fileMenu = new JMenu("File");
        //add items to the menu
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
        
        JMenuItem[] fileMenuItems = {
            newTextFile,newFile,newWindow,openFile,openFolder,openWorkspace,openRecent,addFolderToWorkSpace,saveWorkSpaceAs,
                     duplicateWorkSpace,saveItem,saveAs,saveAll,share,autoSave,preferences,revertFile,closeEditor,closeFolder,closeWindow,Exit
    };
        //add items to fileMenu
        for (JMenuItem item : fileMenuItems) {
            item.addActionListener(this);
        }
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
        //don't touch or change anything..please this section is cursed..
        editMenu = new JMenu("Edit");
        cutItem = new JMenuItem("Cut");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");
        undoItem = new JMenuItem("Undo");
        redoItem = new JMenuItem("Redo");
        findItem = new JMenuItem("Find");
        replaceItem = new JMenuItem("Replace");
        findInFilesItem = new JMenuItem("Find in Files");
        replaceInFilesItem = new JMenuItem("Replace in Files");
        toggleLineCommentItem = new JMenuItem("Toggle Line Comment");
        toggleBlockCommentItem = new JMenuItem("Toggle Block Comment");
        emmetItem = new JMenuItem("Emmet: Expand Abbreviation");

        cutItem.addActionListener(this);
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);
        undoItem.addActionListener(this);
        redoItem.addActionListener(this);
        findItem.addActionListener(this);
        replaceItem.addActionListener(this);
        findInFilesItem.addActionListener(this);
        replaceInFilesItem.addActionListener(this);
        toggleLineCommentItem.addActionListener(this);
        toggleBlockCommentItem.addActionListener(this);
        emmetItem.addActionListener(this);

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.addSeparator();
        editMenu.add(undoItem);
        editMenu.add(redoItem);
        editMenu.addSeparator();
        editMenu.add(findItem);
        editMenu.add(replaceItem);
        editMenu.add(findInFilesItem);
        editMenu.add(replaceInFilesItem);
        editMenu.addSeparator();
        editMenu.add(toggleLineCommentItem);
        editMenu.add(toggleBlockCommentItem);
        editMenu.addSeparator();
        editMenu.add(emmetItem);
        //add to menubar
        menuBar.add(editMenu);
        //initialize selection menu
        selectionMenu = new JMenu("Selection");
        selectAll = new JMenuItem("Select All");
        expandSelection = new JMenuItem("Expand Selection");
        shrinkSelection = new JMenuItem("Shrink Selection");
        copyLineUp = new JMenuItem("Copy Line Up");
        copyLineDown = new JMenuItem("Copy Line Down");
        moveLineUp = new JMenuItem("Select All");
        moveLineDown = new JMenuItem("Move Line Down");
        duplicateSelection = new JMenuItem("Duplicate Selection");
        addCursorAbove = new JMenuItem("Add Cursor Above");
        addCursorBelow = new JMenuItem("Add Cursor Below");
        addCursorToLineEnd = new JMenuItem("Add Cursor To Line End");
        addNextOccurence = new JMenuItem("Add Next Occurence");
        addPreviousOccurence = new JMenuItem("Add Previous Occurence");
        selectAllOccurences = new JMenuItem("Select All Occurences");
        switchToCtrl = new JMenuItem("Switch To ctrl+click for Multi-Cursor");
        columnSelectionMode = new JMenuItem("Column Selection Mode");
        JMenuItem[] selectionMenuItems = {
            selectAll,expandSelection,shrinkSelection,copyLineUp,copyLineDown,moveLineUp,moveLineDown,duplicateSelection,addCursorAbove,
            addCursorBelow,addCursorToLineEnd,addNextOccurence,addPreviousOccurence,selectAllOccurences,switchToCtrl,columnSelectionMode
    };
        //add action listeners to items
        for (JMenuItem item : selectionMenuItems) {
            item.addActionListener(this);
        }
        //add items to the menu
        selectionMenu.add(selectAll);
        selectionMenu.add(expandSelection);
        selectionMenu.add(shrinkSelection);
        selectionMenu.addSeparator();
        selectionMenu.add(copyLineUp);
        selectionMenu.add(copyLineDown);
        selectionMenu.add(moveLineUp);
        selectionMenu.add(moveLineDown);
        selectionMenu.add(duplicateSelection);
        selectionMenu.addSeparator();
        selectionMenu.add(addCursorAbove);
        selectionMenu.add(addCursorBelow);
        selectionMenu.add(addCursorToLineEnd);
        selectionMenu.add(addNextOccurence);
        selectionMenu.add(addPreviousOccurence);
        selectionMenu.add(selectAllOccurences);
        selectionMenu.addSeparator();
        selectionMenu.add(switchToCtrl);
        selectionMenu.add(columnSelectionMode);
        menuBar.add(selectionMenu);
        //initialize view menu
        viewMenu = new JMenu("View");
        commandPall = new JMenuItem("Command Palete");
        openView = new JMenuItem("Open View");
        appearance = new JMenuItem("Appearance");
        editLayout = new JMenuItem("Edit Layout");
        explorer = new JMenuItem("Explorer");
        search = new JMenuItem("Search");
        sourceControl = new JMenuItem("Source Control");
        run = new JMenuItem("Run");
        extensions = new JMenuItem("Extensions");
        testing = new JMenuItem("Testing");
        problems = new JMenuItem("Problems");
        output = new JMenuItem("Output");
        debugConsole = new JMenuItem("Debug Console");
        terminal = new JMenuItem("Terminal");
        wordWrap = new JMenuItem("Word Wrap");
        JMenuItem[] viewMenuItems = {
            commandPall,openView,appearance,editLayout,explorer,search,sourceControl,run,extensions,testing,problems,output,debugConsole,
            terminal,wordWrap
    };
        //add action listeners to items
        for (JMenuItem item : viewMenuItems) {
            item.addActionListener(this);
        }
        //adding the items to the menu
        viewMenu.add(commandPall);
        viewMenu.add(openView);
        viewMenu.addSeparator();
        viewMenu.add(appearance);
        viewMenu.add(editLayout);
        viewMenu.addSeparator();
        viewMenu.add(explorer);
        viewMenu.add(search);
        viewMenu.add(sourceControl);
        viewMenu.add(run);
        viewMenu.add(extensions);
        viewMenu.add(testing);
        viewMenu.addSeparator();
        viewMenu.add(problems);
        viewMenu.add(output);
        viewMenu.add(debugConsole);
        viewMenu.add(terminal);
        viewMenu.addSeparator();
        viewMenu.add(wordWrap);
        
        menuBar.add(viewMenu);
        //initialize go menu
        goMenu = new JMenu("Go");
        back = new JMenuItem("Back");
        forwad = new JMenuItem("Forward");
        lastEditLocation = new JMenuItem();
        switchEditor = new JMenuItem("Switch Editor");
        switchGroup = new JMenuItem("Switch Group");
        goToFile = new JMenuItem("Go To File");
        goToSymbolInWorkspace = new JMenuItem("Go To Symbol In Workspace");
        goToSymbolInEditor = new JMenuItem("Go To Symbol In Editor");
        defination = new JMenuItem("Go To Definition");
        declaration = new JMenuItem("Go To Declaration");
        typeDefination = new JMenuItem("Go To Type Definition");
        implementations = new JMenuItem("Go To Implementations");
        references = new JMenuItem("Go To References");
        goToLineColumn = new JMenuItem("Go To Line/Column");
        bracket = new JMenuItem("Go To Bracket");
        nextProblem = new JMenuItem("Next Problem");
        previousProblem = new JMenuItem("Previous Problem");
        nextChange = new JMenuItem("Next Change");
        previousChange = new JMenuItem("Previous Change");
        JMenuItem[] goMenuItems = {
            back,forwad,lastEditLocation,switchEditor,switchGroup,goToFile,goToSymbolInWorkspace,goToSymbolInEditor,defination,declaration,
            typeDefination,implementations,references,goToLineColumn,bracket,nextProblem,previousProblem,nextChange,previousChange
    };
        //add action listeners to items
        for (JMenuItem item : goMenuItems) {
            item.addActionListener(this);
        }
        //add to the menu
        goMenu.add(back);
        goMenu.add(forwad);
        goMenu.add(lastEditLocation);
        goMenu.addSeparator();
        goMenu.add(switchEditor);
        goMenu.add(switchGroup);
        goMenu.addSeparator();
        goMenu.add(goToFile);
        goMenu.add(goToSymbolInWorkspace);
        goMenu.addSeparator();
        goMenu.add(goToSymbolInEditor);
        goMenu.add(defination);
        goMenu.add(declaration);
        goMenu.add(typeDefination);
        goMenu.add(implementations);
        goMenu.add(references);
        goMenu.addSeparator();
        goMenu.add(goToLineColumn);
        goMenu.add(bracket);
        goMenu.addSeparator();
        goMenu.add(nextProblem);
        goMenu.add(previousProblem);
        goMenu.addSeparator();
        goMenu.add(nextChange);
        goMenu.add(previousChange) ;
        menuBar.add(goMenu);
        //initialize run menu
        runMenu = new JMenu("Run");
        startDebugging = new JMenuItem("Start Debugging");
        runWithout = new JMenuItem("Run Without Debugging");
        stopDebugging = new JMenuItem("Stop Debugging");
        restartDebugging = new JMenuItem("Restart Debugging");
        openConfigurations = new JMenuItem("Open Configurations");
        addConfiguration = new JMenuItem("Add Configuration");
        stepOver = new JMenuItem("Step Over");
        stepInto = new JMenuItem("Step Into");
        stepOut = new JMenuItem("Step Out");
        continuee = new JMenuItem("Continue");
        toggleBreakpoint = new JMenuItem("Toggle Breakpoint");
        newBreakpoint = new JMenuItem("New Breakpoint");
        enableAll = new JMenuItem("Enable All Breakpoints");
        disableAll = new JMenuItem("Disable All Breakpoints");
        removeAll = new JMenuItem("Remove All Breakpoints");
        installAdditional = new JMenuItem("Install Additional Debuggers");
        JMenuItem[] runMenuItems = {
            startDebugging,runWithout,stopDebugging,restartDebugging,openConfigurations,addConfiguration,stepOver,stepInto,stepOut,continuee,
            toggleBreakpoint,newBreakpoint,enableAll,disableAll,removeAll,installAdditional
    };
        //add action listeners to items
        for (JMenuItem item : runMenuItems) {
            item.addActionListener(this);
        }
        //add to the menu
        runMenu.add(startDebugging);
        runMenu.add(runWithout);
        runMenu.add(stopDebugging);
        runMenu.add(restartDebugging);
        runMenu.addSeparator();
        runMenu.add(openConfigurations);
        runMenu.add(addConfiguration);
        runMenu.addSeparator();
        runMenu.add(stepOver);
        runMenu.add(stepInto);
        runMenu.add(stepOut);
        runMenu.add(continuee);
        runMenu.addSeparator();
        runMenu.add(toggleBreakpoint);
        runMenu.add(newBreakpoint);
        runMenu.addSeparator();
        runMenu.add(enableAll);
        runMenu.add(disableAll);
        runMenu.add(removeAll);
        runMenu.addSeparator();
        runMenu.add(installAdditional);
        menuBar.add(runMenu);
        //initialize terminal menu
        terminalMenu = new JMenu("Terminal");
        newTerminal = new JMenuItem("New Terminal");
        splitTerminal = new JMenuItem("Split Terminal");
        runTask = new JMenuItem("Run Task");
        runBuildTask = new JMenuItem("Run Build Task");
        runActive = new JMenuItem("Run Active File");
        runSelected = new JMenuItem("Run Selected Text");
        showRunning = new JMenuItem("Show Running Task");
        restartRunning = new JMenuItem("Restart Running Task");
        terminateTask = new JMenuItem("Terminate Task");
        configureTasks = new JMenuItem("Configure Tasks");
        configureDefault = new JMenuItem("Configure Default Build Task");
        JMenuItem[] terminalMenuItems = {
            newTerminal,splitTerminal,runTask,runBuildTask,runActive,runSelected,showRunning,restartRunning,terminateTask,configureTasks,configureDefault
    };
        //add action listeners to items
        for (JMenuItem item : terminalMenuItems) {
            item.addActionListener(this);
        }
        //add to the menu
        terminalMenu.add(newTerminal);
        terminalMenu.add(splitTerminal);
        terminalMenu.addSeparator();
        terminalMenu.add(runTask);
        terminalMenu.add(runBuildTask);
        terminalMenu.add(runActive);
        terminalMenu.add(runSelected);
        terminalMenu.addSeparator();
        terminalMenu.add(showRunning);
        terminalMenu.add(restartRunning);
        terminalMenu.add(terminateTask);
        terminalMenu.addSeparator();
        terminalMenu.add(configureTasks);
        terminalMenu.add(configureDefault);
        //add to menubar
        menuBar.add(terminalMenu);
        //initialize help menu
        helpMenu = new JMenu("Help");
        welcome = new JMenuItem("Welcome");
        showAllCommands = new JMenuItem("Show All Commands");
        documentation = new JMenuItem("Documentation");
        editorPlayGround = new JMenuItem("Editor Playground");
        showReleaseNotes = new JMenuItem("Show Release Notes");
        keyboardShortcuts = new JMenuItem("Keyboard Shortcuts");
        videoTutorials = new JMenuItem("Video Tutorials");
        tipsTricks = new JMenuItem("Tips and Tricks");
        reportIssue = new JMenuItem("Report Issue");
        devTools = new JMenuItem("Toggle Developer Tools");
        updates = new JMenuItem("Updates");
        about = new JMenuItem("About");
        JMenuItem[] helpMenuItems = {
            welcome,showAllCommands,documentation,editorPlayGround,showReleaseNotes,keyboardShortcuts,videoTutorials,tipsTricks,reportIssue,devTools,updates,about
    };
        //add action listeners to items
        for (JMenuItem item : helpMenuItems) {
            item.addActionListener(this);
        }
        //add to the menu
        helpMenu.add(welcome);
        helpMenu.addSeparator();
        helpMenu.add(showAllCommands);
        helpMenu.addSeparator();
        helpMenu.add(documentation);
        helpMenu.add(editorPlayGround);
        helpMenu.add(showReleaseNotes);
        helpMenu.addSeparator();
        helpMenu.add(keyboardShortcuts);
        helpMenu.add(videoTutorials);
        helpMenu.add(tipsTricks);
        helpMenu.addSeparator();
        helpMenu.add(reportIssue);
        helpMenu.addSeparator();
        helpMenu.add(devTools);
        helpMenu.addSeparator();
        helpMenu.add(updates);
        helpMenu.addSeparator();
        helpMenu.add(about);
        //add to menu bar
        menuBar.add(helpMenu);
        // Initialize format menu
        formatMenu = new JMenu("Format");
        fontItem = new JMenuItem("Font");
        fontSizeItem = new JMenuItem("Font Size");
        fontItem.addActionListener(this);
        fontSizeItem.addActionListener(this);
        formatMenu.add(fontItem);
        formatMenu.add(fontSizeItem);
        menuBar.add(formatMenu);
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
    @Override
    public void actionPerformed(ActionEvent e){
        JMenuItem source = (JMenuItem)e.getSource();
        if(source == newTextFile || source == newFile){
            textArea.setText("");
        }else if (source == newWindow){
            new BsCode();
        //events for listed items
        }else if(source == openFolder || source == openWorkspace || source == openRecent || source == addFolderToWorkSpace){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.showOpenDialog(this);
            //events for listed items
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
            //events for listed items
        }else if(source == saveItem || source == saveWorkSpaceAs || source == duplicateWorkSpace || source == saveAs || source == saveAll){
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
            //events for listed items
        }else if (source == share){
            String text = textArea.getText();
            StringSelection stringSelection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            //events for listed items
        }else if(source == autoSave){
            autoSave();
        }
        //events for listed items
        else if(source == revertFile){
            revertFile();
        }
        //events for listed items
        else if(source == closeEditor){
            textArea.setText("");
        }
        //events for listed items
        else if(source == closeFolder){
        closeFolder();
       }
       //events for listed items
       else if(source == closeWindow){
        dispose();
      }
      //events for listed items
      else if (source == Exit){
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
        } else if (source == findItem) {
            findText();
        } else if (source == replaceItem) {
            replaceText();
        } else if (source == findInFilesItem) {
            findInFiles();
        } else if (source == replaceInFilesItem) {
            replaceInFiles();
        } else if (source == toggleLineCommentItem) {
            toggleLineComment();
        } else if (source == toggleBlockCommentItem) {
            toggleBlockComment();
        } else if (source == emmetItem) {
            emmetExpandAbbreviation();
        }else if (source == fontItem) {
            showFontDialog();
        } else if (source == fontSizeItem) {
            String size = JOptionPane.showInputDialog(this, "Enter Font Size:", "Font Size", JOptionPane.PLAIN_MESSAGE);
            if (size != null) {
                try {
                    int fontSize = Integer.parseInt(size);
                    Font currentFont = textArea.getFont();
                    textArea.setFont(new Font(currentFont.getName(), currentFont.getStyle(), fontSize));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid font size!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    private void showFontDialog() {
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        String font = (String) JOptionPane.showInputDialog(this, "Choose Font:", "Font", JOptionPane.PLAIN_MESSAGE, null, fonts, textArea.getFont().getFamily());
        if (font != null) {
            Font currentFont = textArea.getFont();
            textArea.setFont(new Font(font, currentFont.getStyle(), currentFont.getSize()));
        }
    }
    private void autoSave(){
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("autosave.txt"))){
                    textArea.write(writer);
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(null, "Auto-save failed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        timer.start();
    }
    private void revertFile(){
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))){
                textArea.read(reader, null);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "!!oops..could not open file!", "Error", JOptionPane.ERROR_MESSAGE);
            }     
        }
    }
    private void closeFolder() {
      //i will add this later,be patient..
        JOptionPane.showMessageDialog(this, "Close Folder functionality to be implemented.", "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    private void findText(){
        String searchText = JOptionPane.showInputDialog("Enter text to find");
        if(searchText != null){
            String text = textArea.getText();
            int index = text.indexOf(searchText);
            if(index >= 0){
                textArea.setCaretPosition(index);
                textArea.select(index, index + searchText.length());
                textArea.grabFocus();
            }else{
                 JOptionPane.showMessageDialog(this, "Text not found", "Find", JOptionPane.INFORMATION_MESSAGE); 
            }
       }
    }
    private void replaceText(){
        System.out.println("mistake");
        JPanel panel = new JPanel(new GridLayout(2,2));
        JTextField findField = new JTextField();
        JTextField replaceField = new JTextField();
        panel.add(new JLabel("Find"));
        panel.add(findField);
        panel.add(new JLabel("Replace:"));
        panel.add(replaceField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Replace Text", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            String findText = findField.getText();
            String replaceText = replaceField.getText();
            textArea.setText(textArea.getText().replace(findText,replaceText));
        }
    }
    private void findInFiles() {
        // I know i have said this alot but...i will do this later too:)
        JOptionPane.showMessageDialog(this, "Find in Files functionality coming soon...", "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    private void replaceInFiles() {
        //yikes i bet you won't use this feature today..i have never used such a feature too..be patient
        JOptionPane.showMessageDialog(this, "Replace in Files functionality coming sooner...", "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    private void toggleLineComment(){
        int start = textArea.getSelectionStart();
        int end = textArea.getSelectionEnd();
        try {
            int lineStart = textArea.getLineOfOffset(start);
            int lineEnd = textArea.getLineOfOffset(end);
            for(int i = lineStart; i <= lineEnd;i++){
                int startOffset = textArea.getLineOfOffset(i);
                int endOffset = textArea.getLineOfOffset(i);
                String lineText = textArea.getText(startOffset,endOffset - startOffset).trim();
                if (lineText.startsWith("//")) {
                    textArea.replaceRange(lineText.substring(2), startOffset, endOffset);     
                }else{
                    textArea.insert("//",startOffset);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error toggling line comment!!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void toggleBlockComment(){
        int start = textArea.getSelectionStart();
        int end = textArea.getSelectionEnd();
        try {
            String selectedText = textArea.getText(start, end - start);
            if (selectedText.startsWith("/*") && selectedText.endsWith("*/")) {
                textArea.replaceRange(selectedText.substring(2, selectedText.length() - 2), start, end);
            } else {
                textArea.insert("/*", start);
                textArea.insert("*/", end + 2);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error toggling block comment!!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void emmetExpandAbbreviation() {
        // soooon..
        JOptionPane.showMessageDialog(this, "Emmet Expand Abbreviation functionality to be implemented..", "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void main(String[]args){
        SwingUtilities.invokeLater(() -> new BsCode());
    }

}