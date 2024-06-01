
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
//ui//package fplMoblieTest;
//ui//import org.testng.annotations.Test;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import javax.swing.table.DefaultTableCellRenderer;

public class ui extends JFrame {

    private Map<String, List<JTextField>> xpathFeildsMap;
    private JComboBox<String> fileComboBox;

    public static void combineFiles(String inputFile1, String inputFile2, String inputFile3, String outputFile) {
        try (
                BufferedReader reader1 = new BufferedReader(new FileReader(inputFile1));
                BufferedReader reader2 = new BufferedReader(new FileReader(inputFile2));
                BufferedReader reader3 = new BufferedReader(new FileReader(inputFile3));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                String line;

                while ((line = reader1.readLine()) != null) {
                    writer.write(line.replace("mainclass3", outputFile.replace(System.getProperty("user.dir"), "")
                            .replace("//src//main//java//fplMoblieTest//", "").replace(".java", "")));
                    writer.newLine();
                }
                // writer.write("Hello World");
                // writer.newLine();
                while ((line = reader2.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                }
                while ((line = reader3.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                }
                System.out.println("Files combined and saved successfully!");

            } catch (IOException e) {
                System.err.println("Error combining files: " + e.getMessage());
            }
    }

    // ui//@Test
    // ui//public void main() {
    public static void main(String[] args) {

        ImageIcon icon = new ImageIcon("icon.jpg");
        final JFrame frame = new JFrame("Mobile App Testing Application");
        final DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class; // Ensure the correct class for rendering components
            }
        };

        Image image = icon.getImage();
        frame.setIconImage(image);
        JLabel deviceName = new JLabel("Device Name:");

        JLabel setPackage = new JLabel("Package Name:");
        JLabel setActivity = new JLabel("App Activity:");

        JLabel transactionNoLabel = new JLabel("Transaction No:");
        JLabel transactionNameLabel = new JLabel("Transaction Name:");
        JLabel xpathId1Label = new JLabel("XPath ID 1 (click):");
        JLabel xpathId2Label = new JLabel("XPath ID 2 (next page):");
        JLabel xpathId3Label = new JLabel("XPath ID 3 (assertion):");
        JLabel thinkTimeLabel = new JLabel("Think Time:");
        JLabel assertionNameLabel = new JLabel("Assertion Name:");
        JLabel assertionCheckLabel = new JLabel("Assertion Check:");

        JLabel UsernameLabel = new JLabel("Username:");
        JLabel PasswordLabel = new JLabel("Password:");
        JCheckBox showPassCheckBox = new JCheckBox("Show Password");

        showPassCheckBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

            }
        });

        // even error on ui

        JLabel xpathUsernameLabel = new JLabel("XPath of Username:");
        JLabel xpathPasswordLabel = new JLabel("XPath of Password:");
        // JLabel xpathClickButtonLabel = new JLabel("XPath of Click Button:");
        final JTextField deviceNameField = new JTextField(20);
        final JTextField setPackageField = new JTextField(20);
        final JTextField setActivityField = new JTextField(20);

        final JTextField transactionNoField = new JTextField(20);
        final JTextField transactionNameField = new JTextField(20);
        final JTextField xpathId1Field = new JTextField(20);
        final JTextField xpathId2Field = new JTextField(20);
        final JTextField xpathId3Field = new JTextField(20);
        final JTextField thinkTimeField = new JTextField(20);
        final JTextField assertionNameField = new JTextField(20);
        final JTextField assertionCheckField = new JTextField(20);

        final JTextField UsernameField = new JTextField(20);
        final JTextField PasswordField = new JPasswordField(20);
        final JTextField xpathUsernameField = new JTextField(20);
        final JTextField xpathPasswordField = new JTextField(20);
        // JTextField xpathClickButtonField = new JTextField(15);

        JButton addButton = new JButton("Add Transaction");
        JButton resetButton = new JButton("Revert Transactions");
        JButton Savebutton = new JButton("Save");
        JButton ChooseButton = new JButton("Choose MainClass");
        JButton ExecuteButton = new JButton("Execute");
        JTable table = new JTable(tableModel);

        tableModel.addColumn("Transaction No");
        tableModel.addColumn("Transaction Name");
        tableModel.addColumn("XPath ID 1");
        tableModel.addColumn("XPath ID 2");
        tableModel.addColumn("XPath ID 3");
        tableModel.addColumn("Think Time");
        tableModel.addColumn("Assertion Name");
        tableModel.addColumn("Assertion Check");
        tableModel.addColumn("Username");
        tableModel.addColumn("Password");
        tableModel.addColumn("XPath of Username");
        tableModel.addColumn("XPath of Password");
        int passwordColumnIndex = 9; // Assuming the Password column index is 9
        table.getColumnModel().getColumn(passwordColumnIndex).setCellRenderer(new PasswordRenderer());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 1000));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 2, 5, 5);

        addLabelAndField(panel, deviceName, deviceNameField, gbc);
        addLabelAndField(panel, setPackage, setPackageField, gbc);
        addLabelAndField(panel, setActivity, setActivityField, gbc);

        addLabelAndField(panel, UsernameLabel, UsernameField, gbc);
        addLabelAndField(panel, PasswordLabel, PasswordField, gbc);
        addLabelAndField(panel, xpathUsernameLabel, xpathUsernameField, gbc);
        panel.add(showPassCheckBox);
        addLabelAndField(panel, xpathPasswordLabel, xpathPasswordField, gbc);
        // addLabelAndField(panel, xpathClickButtonLabel, xpathClickButtonField, gbc);

        addLabelAndField(panel, transactionNoLabel, transactionNoField, gbc);
        addLabelAndField(panel, transactionNameLabel, transactionNameField, gbc);
        addLabelAndField(panel, xpathId1Label, xpathId1Field, gbc);
        addLabelAndField(panel, xpathId2Label, xpathId2Field, gbc);
        addLabelAndField(panel, xpathId3Label, xpathId3Field, gbc);
        addLabelAndField(panel, thinkTimeLabel, thinkTimeField, gbc);
        addLabelAndField(panel, assertionNameLabel, assertionNameField, gbc);
        addLabelAndField(panel, assertionCheckLabel, assertionCheckField, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(addButton, gbc);
        
        ChooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deviceNameString = deviceNameField.getText();
                String appPackageString = setPackageField.getText();
                String appActivityString = setActivityField.getText();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("file choosed : "+selectedFile.getAbsolutePath());
                    String classNameToCall = selectedFile.getName().substring(0, selectedFile.getName().lastIndexOf('.'));
                    
                    String currentDir2 = System.getProperty("user.dir");
                    String outputFilePath2 = currentDir2 + "\\temp.java";
                    try(
                    ////src//main//java//fplMoblieTest//
                    BufferedReader reader4 = new BufferedReader(new FileReader(selectedFile.getAbsolutePath()));
                    BufferedWriter writer2 = new BufferedWriter(new FileWriter(outputFilePath2))) {
                    String line2;
                    System.out.println("outputFilePath2 : "+outputFilePath2);
                    while ((line2 = reader4.readLine()) != null) {
                        writer2.write(line2.replace(classNameToCall,"temp"));
                        writer2.newLine();
                        File file = new File(outputFilePath2);
                        boolean deleted = file.delete();
                    } 
                    }catch (IOException f) {
                    System.err.println("Error combining files: " + f.getMessage());
                    }
                    try {
                        // Compile the Java file
                        ProcessBuilder compileBuilder = new ProcessBuilder("javac",outputFilePath2);
                        compileBuilder.redirectErrorStream(true);
                        Process compileProcess = compileBuilder.start();
                        printProcessOutput(compileProcess);
                        int compileResult = compileProcess.waitFor();
                        if (compileResult != 0) {
                            JOptionPane.showMessageDialog(null, "Compilation failed.");
                            return;
                        }
                    
                            
                        } catch (Exception p) {
                            // TODO: handle exception
                            System.err.println("Error combining files: " + p.getMessage());
                        }
                    
                    ChooseButton.setForeground(Color.RED);
                    ChooseButton.setText(classNameToCall);
                    // temp mainmethodMainclass=new temp();
                   
                    // mainmethodMainclass.main(new String[]{deviceNameString,appPackageString,appActivityString});                          
                    
                }
            }
            private void printProcessOutput(Process process) throws IOException {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            }
        });
        ExecuteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deviceNameString = deviceNameField.getText();
                String appPackageString = setPackageField.getText();
                String appActivityString = setActivityField.getText();
                temp mainmethodMainclass=new temp();
                mainmethodMainclass.main(new String[]{deviceNameString,appPackageString,appActivityString}); 
            }
        });
        Savebutton.addActionListener(new ActionListener() {
            private String endTime;

            @Override

            public void actionPerformed(ActionEvent e) {

                String deviceNameString = deviceNameField.getText();
                String appPackageString = setPackageField.getText();
                String appActivityString = setActivityField.getText();
                //
                String xpath1 = xpathId1Field.getText();
                String TransactionName = transactionNameField.getText();
                String xpathAss = xpathId3Field.getText();

                String Xpath2clickelement = xpathId1Field.getText();
                String PageName = transactionNameField.getText();
                String Xpath3NextElement = xpathId2Field.getText();
                String PageAssertion = xpathId3Field.getText();


                try {
                    saveTransactionsToFile();
                    Thread.sleep(1000);
                } catch (InterruptedException f) {

                    f.printStackTrace();
                }

                try {
                    SaveMainclass();
                    JOptionPane.showMessageDialog(frame, "Main Class Saved", "Info", JOptionPane.INFORMATION_MESSAGE);
                    Thread.sleep(1000);

                } catch (InterruptedException g) {

                    g.printStackTrace();
                }

            }

            private void saveTransactionsToFile() {

                String currentDirectory = System.getProperty("user.dir");
                File outputFile = new File(currentDirectory, "\\payload\\file2.txt");

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        StringBuilder lineBuilder = new StringBuilder();
                        for (int j = 0; j < tableModel.getColumnCount(); j++) {
                            lineBuilder.append(tableModel.getValueAt(i, j));
                            if (j < tableModel.getColumnCount() - 1) {
                                lineBuilder.append(",");
                            }
                        }

                        String ArrayElement1 = lineBuilder.toString();
                        String[] ArrayElement2 = ArrayElement1.split(",");

                        if (ArrayElement2.length > 1 && ArrayElement2[1].trim().toLowerCase().contains("launch")) {
                            writer.write("      fplmobileapp1(By.id(\"" + ArrayElement2[3] + "\"),\"" + ArrayElement2[1]
                                    + "\",By.id(\"" + ArrayElement2[4] + "\"));");
                            writer.newLine();
                        } else if (ArrayElement2.length > 1 && ArrayElement2[1].trim().toLowerCase().contains("skip")) {
                            writer.write("      fplmobileapp2(By.id(\"" + ArrayElement2[2] + "\"),By.id(\""
                                    + ArrayElement2[3] + "\"),\"" + ArrayElement2[1] + "\",By.id(\"" + ArrayElement2[4]
                                            + "\"));");
                            writer.newLine();
                        } else if (ArrayElement2.length > 1
                                && ArrayElement2[1].trim().toLowerCase().contains("login")) {
                            writer.write("      fplmobileapp3(By.id(\"" + ArrayElement2[10] + "\"),By.id(\""
                                    + ArrayElement2[11] + "\"),By.id(\"" + ArrayElement2[2] + "\"),By.id(\""
                                    + ArrayElement2[3] + "\"),\"" + ArrayElement2[1] + "\",By.id(\"" + ArrayElement2[4]
                                            + "\"),\"" + ArrayElement2[8] + "\",\"" + ArrayElement2[9] + "\");");
                            writer.newLine();
                        } else if (ArrayElement2.length > 1
                                && ArrayElement2[1].trim().toLowerCase().contains("logout")) {
                            writer.write("      fplmobileapp4(By.id(\"" + ArrayElement2[2] + "\"),By.id(\""
                                    + ArrayElement2[3] + "\"),\"" + ArrayElement2[1] + "\",By.id(\"" + ArrayElement2[4]
                                            + "\"));");
                            writer.newLine();
                        } else {
                            writer.write("      fplmobileapp2(By.id(\"" + ArrayElement2[2] + "\"),By.id(\""
                                    + ArrayElement2[3] + "\"),\"" + ArrayElement2[1] + "\",By.id(\"" + ArrayElement2[4]
                                            + "\"));");
                            writer.newLine();
                        }

                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            private void SaveMainclass() {
                String currentDir = System.getProperty("user.dir");
                String inputFile1 = currentDir + "/payload/file.txt";
                String inputFile2 = currentDir + "/payload/file2.txt";
                String inputFile3 = currentDir + "/payload/file3.txt";
               
                String outputFileName = JOptionPane.showInputDialog(null, "Enter the output file name:", "Output File",JOptionPane.PLAIN_MESSAGE);

                if (outputFileName != null && !outputFileName.trim().isEmpty()) {
                    // No need to declare 'outputFile' again
                    String outputFilePath = currentDir + "//src//main//java//fplMoblieTest//" + outputFileName.trim()+".java";
                    combineFiles(inputFile1, inputFile2, inputFile3, outputFilePath);
                    JOptionPane.showMessageDialog(null, "Files combined successfully into " + outputFilePath, "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Output file name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }

        });
        showPassCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox cb = (JCheckBox) e.getSource();
                if (cb.isSelected()) {
                    ((JPasswordField) PasswordField).setEchoChar((char) 0); // Show characters as plain text
                } else {
                    ((JPasswordField) PasswordField).setEchoChar('*'); // Mask characters with stars again
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] rowData = {
                        transactionNoField.getText(),
                        transactionNameField.getText(),
                        xpathId1Field.getText(),
                        xpathId2Field.getText(),
                        xpathId3Field.getText(),
                        thinkTimeField.getText(),
                        assertionNameField.getText(),
                        assertionCheckField.getText(),
                        UsernameField.getText(),
                        PasswordField.getText(),
                        xpathUsernameField.getText(),
                        xpathPasswordField.getText(),
                        // xpathClickButtonField.getText(),
                };
                tableModel.addRow(rowData);
                // System.out.println(rowData);

                clearFields();
            }

            private void clearFields() {
                transactionNoField.setText("");
                transactionNameField.setText("");
                xpathId1Field.setText("");
                xpathId2Field.setText("");
                xpathId3Field.setText("");
                thinkTimeField.setText("");
                assertionNameField.setText("");
                assertionCheckField.setText("");
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tableModel.setRowCount(0);
            }
        });

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        // buttonPanel.add(showPassCheckBox);
        buttonPanel.add(addButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(Savebutton);
        buttonPanel.add(ChooseButton);
        buttonPanel.add(ExecuteButton);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    static class PasswordRenderer extends DefaultTableCellRenderer {
        private static final String ASTERISKS = "********";

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setText(ASTERISKS); // Always display asterisks (*) regardless of the actual value
            return this;
        }
    }

    private static void addLabelAndField(JPanel panel, JLabel label, JTextField textField, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(textField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
    }

}
