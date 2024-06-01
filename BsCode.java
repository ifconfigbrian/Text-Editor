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
    //items for file menu
    private JMenuItem newTextFile,newFile,newWindow,openFile,openFolder,openWorkspace,openRecent,addFolderToWorkSpace,saveWorkSpaceAs,
                     duplicateWorkSpace,saveItem,saveAs,saveAll,share,autoSave,preferences,revertFile,closeEditor,closeFolder,closeWindow,Exit;
    //items for edit menu
    private JMenuItem cutItem,copyItem,pasteItem,redoItem,undoItem,find,replace,findInFiles,replaceInFiles,toggleLineComment,toggleBlockComment,emmet;
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
        //welcome,showAllCommands,documentation,editorPlayGround,showReleaseDates,keyboardShortcuts,videoTutorials,tipsTricks,reportIssue,devTools,updates,about
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
        }
    }
    public static void main(String[]args){
        new BsCode();
    }

}