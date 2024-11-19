import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SortedListGUI extends JFrame {
    private SortedList sortedList;
    private JTextArea textArea;
    private JTextField inputField;

    public SortedListGUI() {
        sortedList = new SortedList();
        setTitle("Silas's List Sorter");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 248, 255));

        add(createTitleLabel(), BorderLayout.NORTH);

        textArea = createTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("List Output"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Welcome to Silas's List Sorter", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(25, 25, 112));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        return titleLabel;
    }

    private JTextArea createTextArea() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setFont(new Font("Arial", Font.PLAIN, 14));
        area.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return area;
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(new Color(240, 248, 255));

        inputField = new JTextField(30);
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton addButton = createButton("Add", new Color(144, 238, 144), Color.BLACK);
        JButton searchButton = createButton("Search", new Color(173, 216, 230), Color.BLACK);

        panel.add(inputField);
        panel.add(addButton);
        panel.add(searchButton);

        addButton.addActionListener(e -> handleAddAction());
        searchButton.addActionListener(e -> handleSearchAction());

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton clearButton = createButton("Clear", new Color(255, 182, 193), Color.BLACK);

        clearButton.addActionListener(e -> handleClearAction());

        panel.add(clearButton, BorderLayout.CENTER);

        return panel;
    }

    private JButton createButton(String text, Color background, Color textColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(background);
        button.setForeground(textColor);
        button.setPreferredSize(new Dimension(120, 40));
        button.setFocusPainted(false);

        return button;
    }

    private void handleAddAction() {
        String input = inputField.getText().trim();

        if (!input.isEmpty()) {
            sortedList.add(input);
            textArea.append("Added: " + input + "\n");
            textArea.append("Current List: " + sortedList.getList() + "\n");
            inputField.setText("");
            inputField.requestFocus();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid string.", "Input Error", JOptionPane.ERROR_MESSAGE);
            inputField.requestFocus();
        }
    }

    private void handleSearchAction() {
        String input = inputField.getText().trim();

        if (!input.isEmpty()) {
            int index = sortedList.binarySearch(input);
            if (index >= 0) {
                textArea.append("Found: " + input + " at index " + index + "\n");
            } else {
                textArea.append(input + " can't be found. It would be at index: " + -(index + 1) + "\n");
            }
            inputField.setText("");
            inputField.requestFocus();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid string.", "Input Error", JOptionPane.ERROR_MESSAGE);
            inputField.requestFocus();
        }
    }

    private void handleClearAction() {
        sortedList.clearList();
        textArea.setText("");
        JOptionPane.showMessageDialog(this, "List cleared.", "Clear Operation", JOptionPane.INFORMATION_MESSAGE);
        inputField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SortedListGUI::new);
    }
}