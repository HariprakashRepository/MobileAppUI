import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.table.DefaultTableCellRenderer;
public class app {
    

    // private static JTextField usernameField;
    // private static JTextField passwordField;
    // private static JTextField xpathUsernameField;
    // private static JTextField xpathPasswordField;
    // private static JTextField xpathClickButtonField;
    
    public static void combineFiles(String inputFile1, String inputFile2, String inputFile3, String outputFile) {
        try (
                BufferedReader reader1 = new BufferedReader(new FileReader(inputFile1));
                BufferedReader reader2 = new BufferedReader(new FileReader(inputFile2));
                BufferedReader reader3 = new BufferedReader(new FileReader(inputFile3));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String line;

            
            while ((line = reader1.readLine()) != null) {
                writer.write(line);
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
            System.out.println("Files combined successfully!");
        } catch (IOException e) {
            System.err.println("Error combining files: " + e.getMessage());
        }
    }

    
    public static void main(String[] args) {
        
        ImageIcon icon = new ImageIcon("icon.jpg");
        JFrame frame = new JFrame("Mobile App Testing Application");
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class; // Ensure the correct class for rendering components
            }
        };

        Image image = icon.getImage();
        frame.setIconImage(image);
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
        JLabel xpathUsernameLabel = new JLabel("XPath of Username:");
        JLabel xpathPasswordLabel = new JLabel("XPath of Password:");
        //JLabel xpathClickButtonLabel = new JLabel("XPath of Click Button:");

        
        JTextField transactionNoField = new JTextField(15);
        JTextField transactionNameField = new JTextField(15);
        JTextField xpathId1Field = new JTextField(15);
        JTextField xpathId2Field = new JTextField(15);
        JTextField xpathId3Field = new JTextField(15);
        JTextField thinkTimeField = new JTextField(15);
        JTextField assertionNameField = new JTextField(15);
        JTextField assertionCheckField = new JTextField(15);

        JTextField UsernameField = new JTextField(15);
        JTextField PasswordField = new JPasswordField(15);
        JTextField xpathUsernameField = new JTextField(15);
        JTextField xpathPasswordField = new JTextField(15);
        //JTextField xpathClickButtonField = new JTextField(15);
    
        
        
        JButton addButton = new JButton("Add Transaction");

        
        JButton resetButton = new JButton("Revert Transactions");

        
        JButton runButton = new JButton("Run Commands");

        
        
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
        scrollPane.setPreferredSize(new Dimension(1000, 200));

        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); 

        
        addLabelAndField(panel, UsernameLabel, UsernameField, gbc);
        addLabelAndField(panel, PasswordLabel, PasswordField, gbc);
        addLabelAndField(panel, xpathUsernameLabel, xpathUsernameField, gbc);
        panel.add(showPassCheckBox);
        addLabelAndField(panel, xpathPasswordLabel, xpathPasswordField, gbc);
        //addLabelAndField(panel, xpathClickButtonLabel, xpathClickButtonField, gbc);

        
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
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveTransactionsToFile();
                    Thread.sleep(1000);
                } catch (InterruptedException f) {
                    
                    f.printStackTrace();
                }
                SaveMainclass();
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    executeCommands(selectedFile);
                }
            }
           
            private void saveTransactionsToFile() {

                String currentDirectory = System.getProperty("user.dir");
                File outputFile = new File(currentDirectory, "\\payloads\\file2.txt");

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        StringBuilder lineBuilder = new StringBuilder();
                        for (int j = 0; j < tableModel.getColumnCount(); j++) {
                            lineBuilder.append(tableModel.getValueAt(i, j));
                            if (j < tableModel.getColumnCount() - 1) {
                                lineBuilder.append(",");
                            }
                        }
                        
                        String ArrayElement1=lineBuilder.toString();
                        String[] ArrayElement2=ArrayElement1.split(",");
                        
                        if (ArrayElement2.length > 1 && ArrayElement2[1].trim().toLowerCase().contains("launch")) {
                            writer.write("fplmobileapp1("+ArrayElement2[3]+","+ArrayElement2[1]+","+ArrayElement2[4]+");");
                            writer.newLine();
                        }else if(ArrayElement2.length > 1 && ArrayElement2[1].trim().toLowerCase().contains("skip")){
                            writer.write("fplmobileapp2("+ArrayElement2[2]+","+ArrayElement2[3]+","+ArrayElement2[1]+","+ArrayElement2[4]+");");
                            writer.newLine();
                        }else if(ArrayElement2.length > 1 && ArrayElement2[1].trim().toLowerCase().contains("login")){
                            writer.write("fplmobileapp3("+ArrayElement2[10]+","+ArrayElement2[11]+","+ArrayElement2[2]+","+ArrayElement2[3]+","+ArrayElement2[1]+","+ArrayElement2[4]+","+ArrayElement2[8]+","+ArrayElement2[9]+");");
                            writer.newLine();
                        }else{
                            writer.write("fplmobileapp2("+ArrayElement2[2]+","+ArrayElement2[3]+","+ArrayElement2[1]+","+ArrayElement2[4]+");");
                            writer.newLine();
                        }
                        
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            private void SaveMainclass() {
                String currentDir = System.getProperty("user.dir");
                String inputFile1 = currentDir + "/payloads/file1.txt";
                String inputFile2 = currentDir + "/payloads/file2.txt";
                String inputFile3 = currentDir + "/payloads/file3.txt";
                String outputFile = currentDir + "/payloads/mainclass.java";
                combineFiles(inputFile1, inputFile2, inputFile3,outputFile);

            }

            @SuppressWarnings("deprecation")
            private void executeCommands(File file) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        
                        Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"" + line + "\"");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
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
                        //xpathClickButtonField.getText(),
                };
                tableModel.addRow(rowData);
                //System.out.println(rowData);
                
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
        //buttonPanel.add(showPassCheckBox);
        buttonPanel.add(addButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(runButton);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    static class PasswordRenderer extends DefaultTableCellRenderer {
        private static final String ASTERISKS = "********";

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column) {
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
