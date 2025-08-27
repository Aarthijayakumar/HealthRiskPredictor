import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HealthRiskPredictorGUI extends JFrame {
    private JTextField nameField, ageField, bpField, sugarField, heartRateField;
    private JComboBox<String> genderBox, smokingBox, alcoholBox, exerciseBox;
    private JLabel resultLabel;

    public HealthRiskPredictorGUI() {
        setTitle("Health Risk Predictor");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);

        int y = 0;

        // Create labeled field
        JLabel label;

        label = new JLabel("Name:"); label.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(label, gbc);
        nameField = new JTextField(); nameField.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(nameField, gbc);
        y++;

        label = new JLabel("Age:"); label.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(label, gbc);
        ageField = new JTextField(); ageField.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(ageField, gbc);
        y++;

        label = new JLabel("Gender:"); label.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(label, gbc);
        genderBox = new JComboBox<>(new String[] {"Male", "Female", "Other"});
        genderBox.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(genderBox, gbc);
        y++;

        label = new JLabel("Blood Pressure (BP):"); label.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(label, gbc);
        bpField = new JTextField(); bpField.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(bpField, gbc);
        y++;

        label = new JLabel("Blood Sugar:"); label.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(label, gbc);
        sugarField = new JTextField(); sugarField.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(sugarField, gbc);
        y++;

        label = new JLabel("Heart Rate:"); label.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(label, gbc);
        heartRateField = new JTextField(); heartRateField.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(heartRateField, gbc);
        y++;

        label = new JLabel("Smoking:"); label.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(label, gbc);
        smokingBox = new JComboBox<>(new String[] {"Yes", "No"});
        smokingBox.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(smokingBox, gbc);
        y++;

        label = new JLabel("Alcohol Consumption:"); label.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(label, gbc);
        alcoholBox = new JComboBox<>(new String[] {"Yes", "No"});
        alcoholBox.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(alcoholBox, gbc);
        y++;

        label = new JLabel("Regular Exercise:"); label.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(label, gbc);
        exerciseBox = new JComboBox<>(new String[] {"Yes", "No"});
        exerciseBox.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(exerciseBox, gbc);
        y++;

        JButton predictButton = new JButton("Predict Risk");
        predictButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        predictButton.setBackground(new Color(59, 89, 182));
        predictButton.setForeground(Color.WHITE);
        predictButton.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2;
        panel.add(predictButton, gbc);
        y++;

        resultLabel = new JLabel("Result will appear here");
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        resultLabel.setOpaque(true);
        resultLabel.setBackground(Color.LIGHT_GRAY);
        gbc.gridy = y;
        panel.add(resultLabel, gbc);

        add(panel);

        predictButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                predictHealthRisk();
            }
        });
    }

    private void predictHealthRisk() {
        try {
            int age = Integer.parseInt(ageField.getText().trim());
            int bp = Integer.parseInt(bpField.getText().trim());
            int sugar = Integer.parseInt(sugarField.getText().trim());
            int heartRate = Integer.parseInt(heartRateField.getText().trim());
            String smoking = (String) smokingBox.getSelectedItem();
            String alcohol = (String) alcoholBox.getSelectedItem();
            String exercise = (String) exerciseBox.getSelectedItem();

            int score = 0;
            if (age > 50) score += 2;
            if (bp > 140) score += 2;
            if (sugar > 180) score += 2;
            if (heartRate > 100) score += 1;
            if ("Yes".equals(smoking)) score += 2;
            if ("Yes".equals(alcohol)) score += 1;
            if ("No".equals(exercise)) score += 1;

            String risk;
            Color color;
            if (score >= 7) {
                risk = "High Risk";
                color = Color.RED;
            } else if (score >= 4) {
                risk = "Medium Risk";
                color = Color.ORANGE;
            } else {
                risk = "Low Risk";
                color = new Color(0, 153, 0); // dark green
            }

            resultLabel.setText("Prediction: " + risk);
            resultLabel.setBackground(color);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter valid numeric values.");
            resultLabel.setBackground(Color.PINK);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HealthRiskPredictorGUI().setVisible(true);
        });
    }
}
