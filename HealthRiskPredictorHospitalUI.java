import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HealthRiskPredictorHospitalUI extends JFrame {

    // Input fields
    private JTextField nameField, ageField, bpField, sugarField, heartRateField;
    private JComboBox<String> genderBox, smokingBox, alcoholBox, exerciseBox;
    private JLabel resultLabel;

    public HealthRiskPredictorHospitalUI() {
        setTitle("Smart Health Risk Predictor");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main container
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 250, 255));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 102, 204));
        JLabel headerLabel = new JLabel("🏥 Smart Health Risk Predictor");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createTitledBorder("Patient Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Fields
        nameField = new JTextField(15);
        ageField = new JTextField(5);
        bpField = new JTextField(5);
        sugarField = new JTextField(5);
        heartRateField = new JTextField(5);

        genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        smokingBox = new JComboBox<>(new String[]{"Yes", "No"});
        alcoholBox = new JComboBox<>(new String[]{"Yes", "No"});
        exerciseBox = new JComboBox<>(new String[]{"Yes", "No"});

        // Add components row by row
        int row = 0;

        addRow(formPanel, gbc, row++, "Name:", nameField);
        addRow(formPanel, gbc, row++, "Age:", ageField);
        addRow(formPanel, gbc, row++, "Gender:", genderBox);
        addRow(formPanel, gbc, row++, "Blood Pressure (BP):", bpField);
        addRow(formPanel, gbc, row++, "Blood Sugar:", sugarField);
        addRow(formPanel, gbc, row++, "Heart Rate:", heartRateField);
        addRow(formPanel, gbc, row++, "Smoking:", smokingBox);
        addRow(formPanel, gbc, row++, "Alcohol Consumption:", alcoholBox);
        addRow(formPanel, gbc, row++, "Regular Exercise:", exerciseBox);

        // Predict Button
        JButton predictBtn = new JButton("Predict Risk");
        predictBtn.setBackground(new Color(0, 153, 76));
        predictBtn.setForeground(Color.WHITE);
        predictBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        predictBtn.setFocusPainted(false);
        predictBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbc.gridx = 1;
        gbc.gridy = row++;
        formPanel.add(predictBtn, gbc);

        // Result Label
        resultLabel = new JLabel("Result will appear here");
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        resultLabel.setForeground(Color.DARK_GRAY);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel resultPanel = new JPanel();
        resultPanel.setBackground(new Color(230, 247, 255));
        resultPanel.setBorder(BorderFactory.createTitledBorder("Prediction Result"));
        resultPanel.add(resultLabel);

        // Button Action
        predictBtn.addActionListener(e -> predictRisk());

        // Add everything
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(resultPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // Utility method to add rows
    private void addRow(JPanel panel, GridBagConstraints gbc, int row, String labelText, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    // Simple risk calculation logic
    private void predictRisk() {
        try {
            int age = Integer.parseInt(ageField.getText());
            int bp = Integer.parseInt(bpField.getText());
            int sugar = Integer.parseInt(sugarField.getText());
            int heartRate = Integer.parseInt(heartRateField.getText());
            String smoking = (String) smokingBox.getSelectedItem();
            String alcohol = (String) alcoholBox.getSelectedItem();
            String exercise = (String) exerciseBox.getSelectedItem();

            int riskScore = 0;
            if (age > 50) riskScore += 2;
            if (bp > 140) riskScore += 2;
            if (sugar > 120) riskScore += 2;
            if (heartRate > 100) riskScore += 1;
            if ("Yes".equals(smoking)) riskScore += 2;
            if ("Yes".equals(alcohol)) riskScore += 1;
            if ("No".equals(exercise)) riskScore += 1;

            String result;
            if (riskScore <= 2) {
                result = "✅ Low Risk – Keep up the good health!";
                resultLabel.setForeground(new Color(0, 153, 51));
            } else if (riskScore <= 5) {
                result = "⚠️ Moderate Risk – Consider lifestyle changes.";
                resultLabel.setForeground(new Color(255, 153, 0));
            } else {
                result = "❌ High Risk – Consult a doctor immediately!";
                resultLabel.setForeground(Color.RED);
            }

            resultLabel.setText(result);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for age, BP, sugar, and heart rate.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HealthRiskPredictorHospitalUI().setVisible(true));
    }
}
