import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class TextEditor {
    private JFrame frame;
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openItem;
    private JMenuItem saveItem;

    public TextEditor() {
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Text Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea(20, 40);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");

        openItem.addActionListener(new OpenFileListener());
        saveItem.addActionListener(new SaveFileListener());

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        menuBar.add(fileMenu);

        frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.setJMenuBar(menuBar);

        frame.pack();
        frame.setVisible(true);
    }

    private class OpenFileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(frame);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line;
                    StringBuilder content = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    textArea.setText(content.toString());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error opening file: " + ex.getMessage());
                }
            }
        }
    }

    private class SaveFileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showSaveDialog(frame);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(textArea.getText());
                    bufferedWriter.close();
                    JOptionPane.showMessageDialog(frame, "File saved successfully!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error saving file: " + ex.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TextEditor();
            }
        });
    }
}