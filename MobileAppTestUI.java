import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class MobileAppTestUI {
    
    private static JTextField usernameField;
    private static JTextField passwordField;
    private static JTextField xpathUsernameField;
    private static JTextField xpathPasswordField;
    private static JTextField xpathClickButtonField;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Transaction Manager");

        // Create labels
        JLabel transactionNoLabel = new JLabel("Transaction No:");
        JLabel transactionNameLabel = new JLabel("Transaction Name:");
        JLabel xpathId1Label = new JLabel("XPath ID 1:");
        JLabel xpathId2Label = new JLabel("XPath ID 2:");
        JLabel thinkTimeLabel = new JLabel("Think Time:");
        JLabel assertionNameLabel = new JLabel("Assertion Name:");
        JLabel assertionCheckLabel = new JLabel("Assertion Check:");

        JLabel UsernameLabel = new JLabel("Username:");
        JLabel PasswordLabel = new JLabel("Password:");
        JLabel xpathUsernameLabel = new JLabel("XPath of Username:");
        JLabel xpathPasswordLabel = new JLabel("XPath of Password:");
        JLabel xpathClickButtonLabel = new JLabel("XPath of Click Button:");

        // Create text fields
        JTextField transactionNoField = new JTextField(15);
        JTextField transactionNameField = new JTextField(15);
        JTextField xpathId1Field = new JTextField(15);
        JTextField xpathId2Field = new JTextField(15);
        JTextField thinkTimeField = new JTextField(15);
        JTextField assertionNameField = new JTextField(15);
        JTextField assertionCheckField = new JTextField(15);

        JTextField UsernameField = new JTextField(15);
        JTextField PasswordField = new JTextField(15);
        JTextField xpathUsernameField = new JTextField(15);
        JTextField xpathPasswordField = new JTextField(15);
        JTextField xpathClickButtonField = new JTextField(15);

        // Create Add button
        JButton addButton = new JButton("Add Transaction");

        // Create Reset button
        JButton resetButton = new JButton("Revert Transactions");

 // Create Run button
        JButton runButton = new JButton("Run Commands");

        // Create table and its model with additional columns
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        tableModel.addColumn("Transaction No");
        tableModel.addColumn("Transaction Name");
        tableModel.addColumn("XPath ID 1");
        tableModel.addColumn("XPath ID 2");
        tableModel.addColumn("Think Time");
        tableModel.addColumn("Assertion Name");
        tableModel.addColumn("Assertion Check");

        // Create scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 200));

        // Add components to the panel
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding

        // Add labels and text fields to the panel
        addLabelAndField(panel, UsernameLabel, UsernameField, gbc);
        addLabelAndField(panel, PasswordLabel, PasswordField, gbc);
        addLabelAndField(panel, xpathUsernameLabel, xpathUsernameField, gbc);
        addLabelAndField(panel, xpathPasswordLabel, xpathPasswordField, gbc);
        addLabelAndField(panel, xpathClickButtonLabel, xpathClickButtonField, gbc);

        //dynamic
        addLabelAndField(panel, transactionNoLabel, transactionNoField, gbc);
        addLabelAndField(panel, transactionNameLabel, transactionNameField, gbc);
        addLabelAndField(panel, xpathId1Label, xpathId1Field, gbc);
        addLabelAndField(panel, xpathId2Label, xpathId2Field, gbc);
        addLabelAndField(panel, thinkTimeLabel, thinkTimeField, gbc);
        addLabelAndField(panel, assertionNameLabel, assertionNameField, gbc);
        addLabelAndField(panel, assertionCheckLabel, assertionCheckField, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1; // Span across two columns
        gbc.fill = GridBagConstraints.NONE; // Don't allow expansion
        gbc.anchor = GridBagConstraints.EAST; // Align button to the center
        panel.add(addButton, gbc);
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTransactionsToFile();
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    executeCommands(selectedFile);
                }
            }
            private void saveTransactionsToFile() {
                String currentDirectory = System.getProperty("user.dir");
                File outputFile = new File(currentDirectory, "output.txt");
            
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        StringBuilder lineBuilder = new StringBuilder();
                        for (int j = 0; j < tableModel.getColumnCount(); j++) {
                            lineBuilder.append(tableModel.getValueAt(i, j));
                            if (j < tableModel.getColumnCount() - 1) {
                                lineBuilder.append(",");
                            }
                        }
                        writer.write(lineBuilder.toString());
                        writer.newLine();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            
            @SuppressWarnings("deprecation")
            private void executeCommands(File file) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Open a CMD window and execute the command
                        Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"" + line + "\"");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        // Add action listener to the Add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] rowData = {
                    transactionNoField.getText(),
                    transactionNameField.getText(),
                    xpathId1Field.getText(),
                    xpathId2Field.getText(),
                    thinkTimeField.getText(),
                    assertionNameField.getText(),
                    assertionCheckField.getText(),
                };
                tableModel.addRow(rowData);

                // Clear input fields after adding data
                clearFields();
            }
        

          
       

    private void clearFields() {
                transactionNoField.setText("");
                transactionNameField.setText("");
                xpathId1Field.setText("");
                xpathId2Field.setText("");
                thinkTimeField.setText("");
                assertionNameField.setText("");
                assertionCheckField.setText("");
            }
        });

        // Add action listener to the Reset button
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear table model data to remove all recorded transactions
                tableModel.setRowCount(0);
            }
        });

        // Add components to the main frame
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(runButton);
                frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Helper method to add label and text field to the panel
    private static void addLabelAndField(JPanel panel, JLabel label, JTextField textField, GridBagConstraints gbc) {
        gbc.gridx = 0; // Align label to the left
        gbc.anchor = GridBagConstraints.WEST; // Align label text to the left
        panel.add(label, gbc);

        gbc.gridx = 1; // Align text field to the right
        gbc.anchor = GridBagConstraints.EAST; // Align text field text to the right
        panel.add(textField, gbc);

        gbc.gridx = 0; // Reset gridx for the next row
        gbc.gridy++; // Move to the next row
    }
}
