// import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
// import org.fife.ui.rtextarea.RTextScrollPane;
// import com.jediterm.terminal.ui.JediTermWidget;
// import com.jediterm.terminal.ui.settings.DefaultSettingsProvider;
//download JTermial jar..and jediterm


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.awt.image.BufferedImage;
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
    private JMenuItem   back,forwad,lastEditLocation,switchEditor,switchGroup,goToFile,goToSymbolInWorkspace,goToSymbolInEditor,defination,declaration,
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
        //set icon
        BufferedImage iconImage = null;
        try {
            iconImage = ImageIO.read(getClass().getResource("216158_code_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //resize the image
        if (iconImage != null) {
            BufferedImage coloredIconImage = changeImageColor(iconImage, Color.BLUE);
            setIconImage(coloredIconImage); 
        }
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
        moveLineUp = new JMenuItem("Move Line Up");
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
        }else if(source == selectAll){
            textArea.selectAll();
        }else if(source ==expandSelection ){
            int start = textArea.getSelectionStart();
            int end = textArea.getSelectionEnd();
            if (start > 0)start--; 
                if(end < textArea.getText().length())end++;
                textArea.select(start,end);      
        }else if(source == shrinkSelection){
            int start = textArea.getSelectionStart();
            int end = textArea.getSelectionEnd();
            if(start < end){
                start++;
                end--;
            }
            textArea.select(start,end);
        }else if(source == copyLineUp){
            copyLineUp();
        }else if(source == copyLineDown){
            copyLineDown();
        }else if(source == moveLineUp){
            moveLineUp();
        }else if(source == moveLineDown){
            moveLineDown();
        }else if(source == duplicateSelection){
            duplicateSelection();
        }else if(source == addCursorAbove){
            JOptionPane.showMessageDialog(this, "subscribe to premium for cool features..");
        }else if(source == addCursorBelow){
            JOptionPane.showMessageDialog(this, "subscribe to premium for cool features..");
        }else if(source == addCursorToLineEnd){
            int endOfLine = textArea.getCaretPosition();
            while (endOfLine < textArea.getText().length() && textArea.getText().charAt(endOfLine)!= '\n') {
                endOfLine++;  
            }
            textArea.setCaretPosition(endOfLine);
        }else if(source == addNextOccurence){
            addNextOccurence();
        }else if(source == addPreviousOccurence){
            addPreviousOccurence();
        }else if(source == selectAllOccurences){
            selectAllOccurences();
        }else if(source == switchToCtrl){
            JOptionPane.showMessageDialog(this, "subscribe to premium for cool features..");
        }else if(source == columnSelectionMode){
            JOptionPane.showMessageDialog(this, "subscribe to premium for cool features..");
        }else if (source == commandPall) {
            showCommandPalette();
        } else if (source == openView) {
            openView();
        } else if (source == appearance) {
            setAppearance();
        } else if (source == editLayout) {
            editLayout();
        } else if (source == explorer) {
            openExplorer();
        } else if (source == search) {
            openSearch();
        } else if (source == sourceControl) {
            openSourceControl();
        } else if (source == run) {
            runCode();
        } else if (source == extensions) {
            openExtensions();
        } else if (source == testing) {
            openTesting();
        } else if (source == problems) {
            openProblems();
        } else if (source == output) {
            openOutput();
        } else if (source == debugConsole) {
            openDebugConsole();
        } else if (source == terminal) {
            openTerminal();
        } else if (source == wordWrap) {
            textArea.setLineWrap(!textArea.getLineWrap());
        }else if (source == back) {
                goBack();
        } else if (source == forwad) {
                goForward();
        } else if (source == lastEditLocation) {
                goToLastEditLocation();
        } else if (source == switchEditor) {
                switchEditor();
        } else if (source == switchGroup) {
                switchGroup();
        } else if (source == goToFile) {
                goToFile();
        } else if (source == goToSymbolInWorkspace) {
                goToSymbolInWorkspace();
        } else if (source == goToSymbolInEditor) {
                goToSymbolInEditor();
        } else if (source == defination) {
                goToDefinition();
        } else if (source == declaration) {
                goToDeclaration();
        } else if (source == typeDefination) {
                goToTypeDefinition();
        } else if (source == implementations) {
                goToImplementations();
        } else if (source == references) {
                goToReferences();
        } else if (source == goToLineColumn) {
                goToLineColumn();
        } else if (source == bracket) {
                goToBracket();
        } else if (source == nextProblem) {
                goToNextProblem();
        } else if (source == previousProblem) {
                goToPreviousProblem();
        } else if (source == nextChange) {
                goToNextChange();
        } else if (source == previousChange) {
                goToPreviousChange();

        }else if(source == newTerminal){
            openTerminal();
        }else if(source == splitTerminal){
            splitTerminal();
        }else if(source == runTask){
            runTask();
        }else if(source == runBuildTask){
            runBuildTask();
        }else if(source == runActive){
            runActive();
        }else if(source == runSelected){
            runSelected();
        }else if(source == showRunning){
            showRunning();
        }else if(source == restartRunning){
            restartRunning();
        }else if(source == terminateTask){
            terminateTask();
        }else if(source == configureTasks){
            configureTasks();
        }else if(source == configureDefault){
            configureDefault();
        }
    }
    private void splitTerminal(){
        JOptionPane.showMessageDialog(this, "Subscribe to premium for cool features!!");
    
    }
    private void runTask(){
       JOptionPane.showMessageDialog(this, "Subscribe to premium for cool features!!");

    }
    private void runBuildTask(){
        JOptionPane.showMessageDialog(this, "Subscribe to premium for cool features!!");

    }
    private void runActive(){
       JOptionPane.showMessageDialog(this, "Subscribe to premium for cool features!!");

    }
    private void runSelected(){
        JOptionPane.showMessageDialog(this, "Subscribe to premium for cool features!!");

    }
    private void showRunning(){
        JOptionPane.showMessageDialog(this, "Subscribe to premium for cool features!!");

    }
    private void restartRunning(){
        JOptionPane.showMessageDialog(this, "Subscribe to premium for cool features!!");

    }
    private void terminateTask(){
        JOptionPane.showMessageDialog(this, "Subscribe to premium for cool features!!");

    }
    private void configureDefault(){
        JOptionPane.showMessageDialog(this, "Subscribe to premium for cool features!!");

    }
    private void configureTasks(){
        JOptionPane.showMessageDialog(this, "Subscribe to premium for cool features!!");
        
    }
    private void goBack() {
        JOptionPane.showMessageDialog(this, "subscribe to premium!");
    }

    private void goForward() {
        JOptionPane.showMessageDialog(this, "subscribe to premium!");
    }

    private void goToLastEditLocation() {
        JOptionPane.showMessageDialog(this, "subscribe to premium!");
    }

    private void switchEditor() {
        JOptionPane.showMessageDialog(this, "subscribe to premium!");
    }

    private void switchGroup() {
        JOptionPane.showMessageDialog(this, "subscribe to premium!");
    }

    private void goToFile() {
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
    }

    private void goToSymbolInWorkspace() {
        String symbol = JOptionPane.showInputDialog(this, "Enter symbol:");
        if (symbol != null) {
            JOptionPane.showMessageDialog(this, "Go to Symbol in Workspace: " + symbol);
        }
    }

    private void goToSymbolInEditor() {
        String symbol = JOptionPane.showInputDialog(this, "Enter symbol:");
        if (symbol != null) {
            JOptionPane.showMessageDialog(this, "Go to Symbol in Editor: " + symbol);
        }
    }

    private void goToDefinition() {
        JOptionPane.showMessageDialog(this, "subscribe to premium!");
    }

    private void goToDeclaration() {
        JOptionPane.showMessageDialog(this, "subscribe to premium!");
    }

    private void goToTypeDefinition() {
        JOptionPane.showMessageDialog(this, "subscribe to premium!");
    }

    private void goToImplementations() {
        JOptionPane.showMessageDialog(this, "subscribe to premium!");
    }

    private void goToReferences() {
        JOptionPane.showMessageDialog(this, "subscribe to premium!");
    }

    private void goToLineColumn() {
        String lineColumn = JOptionPane.showInputDialog(this, "Enter line:column:");
        if (lineColumn != null) {
            JOptionPane.showMessageDialog(this, "Go to Line/Column: " + lineColumn);
        }
    }

    private void goToBracket() {
        JOptionPane.showMessageDialog(this, "subscribe to premium!");
    }

    private void goToNextProblem() {
        JOptionPane.showMessageDialog(this, "subscribe to premium!");
    }

    private void goToPreviousProblem() {
        JOptionPane.showMessageDialog(this, "subscribe to premium!");
    }

    private void goToNextChange() {
        JOptionPane.showMessageDialog(this, "subscribe to premium!");
    }

    private void goToPreviousChange() {
        JOptionPane.showMessageDialog(this, "subscribe to premium!");
    }
    private void showCommandPalette(){
        String command = JOptionPane.showInputDialog(this,"Enter Command");
        if(command != null){
            JOptionPane.showMessageDialog(this,"Executing command" + "" + command);
        }
    }
    private void openView(){
        JDialog viewDialog = new JDialog(this,"Open view",true);
        viewDialog.setSize(400,300);
        viewDialog.setLocationRelativeTo(this);
        viewDialog.setVisible(true);
    }
    private void setAppearance(){
        JColorChooser colorChooser = new JColorChooser();
        Color newColor = JColorChooser.showDialog(this,"Choose Background Color..",textArea.getBackground());
        if(newColor != null){
            textArea.setBackground(newColor);
        }
    }
    private void editLayout(){
        JOptionPane.showMessageDialog(this, "Subscribe to premium for cool features!");
    }
    private void openExplorer(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(this);
        if(option == JFileChooser.APPROVE_OPTION){
            File selectedDirectory = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Explorer opend for: " + selectedDirectory.getAbsolutePath());
        }
    }
    private void openSearch(){
        String searchTerm = JOptionPane.showInputDialog(this,"Enter term:");
        if (search != null) {
            JOptionPane.showMessageDialog(this, "Searching for: " + searchTerm);   
        }
    }
    private void openSourceControl(){
        JOptionPane.showMessageDialog(this, "Subscribe to premium for cool features!");
    }
    private void runCode(){
        JOptionPane.showMessageDialog(this, "running code...");
    }
    private void openExtensions(){
        JOptionPane.showMessageDialog(this, "Subscribe to premium for cool features!");
    }
    private void openTesting(){
        JOptionPane.showMessageDialog(this, "Subscribe to premium for cool features!");
    }
    private void openProblems(){
        JOptionPane.showMessageDialog(this, "Subscribe to premium for cool features!");
    }
    private void openOutput(){
        JOptionPane.showMessageDialog(this, "Subscribe to premium for cool features!");
    }
    private void openDebugConsole(){
        JOptionPane.showMessageDialog(this, "Subscribe to premium for cool features!");
    }
    private void openTerminal(){
        JOptionPane.showMessageDialog(this, "Subscribe to premium for cool features!");
        // JFrame terminalFrame = new JFrame("Terminal");
        // terminalFrame.setSize(800, 600);
        // terminalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // // Create JediTermWidget
        // JediTermWidget terminal = new JediTermWidget(new DefaultSettingsProvider());
        // terminalFrame.add(terminal, BorderLayout.CENTER);

        // // Show the terminal frame
        // terminalFrame.setVisible(true);

        // // You can run a shell command or start a process in the terminal
        // terminal.getTtyConnector().write("echo 'Welcome to the terminal!'\n");

    }
    private void copyLineUp(){
    //     try {
    //         // int start = textArea.getLineStartOffset(textArea.getCaretLineNumber());this will be added using the github library
    //         // int end = textArea.getLineEndOffset(textArea.getCaretLineNumber());
    //         String line = textArea.getText(start,end - start);
    //         textArea.insert(line, start);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    }
    private void copyLineDown(){
        //     try {
        //         // int start = textArea.getLineStartOffset(textArea.getCaretLineNumber());this will be added using the github library
        //         // int end = textArea.getLineEndOffset(textArea.getCaretLineNumber());
        //         String line = textArea.getText(start,end - start);
        //         textArea.insert(line, end);
        //     } catch (Exception e) {
        //         e.printStackTrace();
        //     }
        }
    private void moveLineUp(){
        // try {
        //     int currentLine = textArea.getCaretLineNumber();
        //     if (currentLine == 0)return;
        //     int startCurrent = textArea.getLineStartOffset(currentLine);
        //     int endCurrent = textArea.getLineEndOffset(currentLine);
        //     String lineCurrent = textArea.getText(startCurrent,endCurrent - startCurrent);

        //     int startPrev = textArea.getLineStartOffset(currentLine-1);
        //     int endPrev = textArea.getLineEndOffset(currentLine-1);
        //     String linePrev = textArea.getText(startPrev,endPrev - startPrev);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }
    private void moveLineDown(){
        // try {
        //     int currentLine = textArea.getCaretLineNumber();
        //     int totalLines = textArea.getLineCount();
        //     if (currentLine == totalLines -1)return;
        //     int startCurrent = textArea.getLineStartOffset(currentLine);
        //     int endCurrent = textArea.getLineEndOffset(currentLine);
        //     String lineCurrent = textArea.getText(startCurrent,endCurrent - startCurrent);

        //     int startNext = textArea.getLineStartOffset(currentLine +1);
        //     int endNext = textArea.getLineEndOffset(currentLine +1);
        //     String lineNext = textArea.getText(startNext,endNext - startNext);

        //     textArea.replaceRange(lineCurrent, startNext, endNext);
        //     textArea.replaceRange(lineNext, startCurrent, endCurrent);
        //     textArea.setCaretPosition(startNext);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }
    private void duplicateSelection(){
        String selectedText = textArea.getSelectedText();
        if (selectedText != null) {
            textArea.insert(selectedText, textArea.getSelectionEnd());    
        }
    }
    private void addNextOccurence(){
        String selectedText = textArea.getSelectedText();
        if (selectedText !=null) {
            String content = textArea.getText();
            int nextOccurence = content.indexOf(selectedText,textArea.getSelectionEnd());
            if (nextOccurence != -1) {
                textArea.select(nextOccurence, nextOccurence + selectedText.length());
            }  
        }
    }
    private void addPreviousOccurence(){
        String selectedText = textArea.getSelectedText();
        if (selectedText != null) {
            String content = textArea.getText();
            int previousOccurence = content.lastIndexOf(selectedText,textArea.getSelectionStart() -1);
            if (previousOccurence != -1) {
                textArea.select(previousOccurence, previousOccurence + selectedText.length());       
            }  
        }
    }
    private void selectAllOccurences(){
        String selectedText = textArea.getSelectedText();
        if (selectedText != null) {
            String content = textArea.getText();
            textArea.select(0,0);//this resets selection
            int occurence = content.indexOf(selectedText);
            while (occurence != -1) {
                textArea.select(textArea.getSelectionStart(), occurence + selectedText.length());
                occurence = content.indexOf(selectedText,occurence ++);     
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
        Timer timer = new Timer(5000, new ActionListener() {
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
    private BufferedImage changeImageColor(BufferedImage originalImage,Color color){
        BufferedImage coloredImage = new BufferedImage(originalImage.getWidth(),originalImage.getHeight(),BufferedImage.TYPE_INT_ARGB);
        for(int x = 0;x < originalImage.getWidth(); x++){
            for(int y = 0;y < originalImage.getHeight(); y++){
            int pixel = originalImage.getRGB(x, y);

            if ((pixel & 0xFF000000) != 0x00000000) {//check if the pixel is not transparent
             // Change the color of the pixel to the specified color while preserving the alpha channel
            int alpha = (pixel >> 24) & 0xFF;
            int newPixel = (alpha << 24) | (color.getRGB() & 0x00FFFFFF);
            coloredImage.setRGB(x, y, newPixel);
            }else{
                coloredImage.setRGB(x, y, pixel);//preserve original pixel
            }
        }
        }
        return coloredImage;

    }
    public static void main(String[]args){
        SwingUtilities.invokeLater(() -> new BsCode());
    }

}