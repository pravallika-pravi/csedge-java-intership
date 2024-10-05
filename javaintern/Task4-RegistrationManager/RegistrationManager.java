import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
public class RegistrationManager {
    private Frame frame;
    private Panel panel;
    private TextField nameField, emailField, phoneField;
    private Choice departmentChoice;
    private CheckboxGroup genderGroup;
    private Checkbox maleCheckbox, femaleCheckbox;
    private Button registerButton, displayButton, saveButton;
    private TextArea displayArea;
    private Vector<Registrant> registrants;

    public RegistrationManager() {
        createGUI();
        registrants = new Vector<>();
    }

    private void createGUI() {
        frame = new Frame("Registration Manager");
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        panel = new Panel();
        panel.setLayout(new GridLayout(6, 2));

        panel.add(new Label("Name:"));
        nameField = new TextField();
        panel.add(nameField);

        panel.add(new Label("Email:"));
        emailField = new TextField();
        panel.add(emailField);

        panel.add(new Label("Phone:"));
        phoneField = new TextField();
        panel.add(phoneField);

        panel.add(new Label("Department:"));
        departmentChoice = new Choice();
        departmentChoice.add("Computer Science");
        departmentChoice.add("Mathematics");
        departmentChoice.add("Physics");
        panel.add(departmentChoice);

        panel.add(new Label("Gender:"));
        genderGroup = new CheckboxGroup();
        maleCheckbox = new Checkbox("Male", genderGroup, true);
        femaleCheckbox = new Checkbox("Female", genderGroup, false);
        Panel genderPanel = new Panel();
        genderPanel.add(maleCheckbox);
        genderPanel.add(femaleCheckbox);
        panel.add(genderPanel);

        registerButton = new Button("Register");
        registerButton.addActionListener(new RegisterListener());
        panel.add(registerButton);

        displayButton = new Button("Display");
        displayButton.addActionListener(new DisplayListener());
        panel.add(displayButton);

        saveButton = new Button("Save");
        saveButton.addActionListener(new SaveListener());
        panel.add(saveButton);

        displayArea = new TextArea(10, 20);
        frame.add(panel, BorderLayout.NORTH);
        frame.add(displayArea, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private class RegisterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String department = departmentChoice.getSelectedItem();
            String gender = genderGroup.getSelectedCheckbox().getLabel();

            Registrant registrant = new Registrant(name, email, phone, department, gender);
            registrants.add(registrant);

            nameField.setText("");
            emailField.setText("");
            phoneField.setText("");
            departmentChoice.select(0);
            maleCheckbox.setState(true);
        }
    }

    private class DisplayListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            displayArea.setText("");
            for (Registrant registrant : registrants) {
                displayArea.append(registrant.toString() + "\n");
            }
        }
    }

    private class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                FileWriter fileWriter = new FileWriter("registrants.txt");
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                for (Registrant registrant : registrants) {
                    bufferedWriter.write(registrant.toString() + "\n");
                }
                bufferedWriter.close();
                JOptionPane.showMessageDialog(frame, "Registrants saved to file.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error saving registrants to file.");
            }
        }
    }

    private class Registrant {
        private String name, email, phone, department, gender;

        public Registrant(String name, String email, String phone, String department, String gender) {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.department = department;
            this.gender = gender;
        }

        public String toString() {
            return "Name: " + name + "\nEmail: " + email + "\nPhone: " + phone + "\nDepartment: " + department + "\nGender: " + gender;
        }
    }

    public static void main(String[] args) {
        new RegistrationManager();
    }
}