 import java.awt.*;
import java.awt.event.*;
public class Calculator {
    private Frame frame;
    private Panel panel;
    private TextField textField;
    private double number1, number2, result;
    private char operation;

    public Calculator() {
        createGUI();
    }

    private void createGUI() {
        frame = new Frame("Calculator");
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        panel = new Panel();
        panel.setLayout(new GridLayout(4, 4));

        textField = new TextField();
        frame.add(textField, BorderLayout.NORTH);

        String[] buttons = {"7", "8", "9", "/",
                            "4", "5", "6", "*",
                            "1", "2", "3", "-",
                            "0", ".", "=", "+"};

        for (String button : buttons) {
            Button b = new Button(button);
            b.addActionListener(new ButtonListener());
            panel.add(b);
        }

        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
                number1 = Double.parseDouble(textField.getText());
                operation = command.charAt(0);
                textField.setText("");
            } else if (command.equals("=")) {
                number2 = Double.parseDouble(textField.getText());
                switch (operation) {
                    case '+':
                        result = number1 + number2;
                        break;
                    case '-':
                        result = number1 - number2;
                        break;
                    case '*':
                        result = number1 * number2;
                        break;
                    case '/':
                        if (number2 != 0) {
                            result = number1 / number2;
                        } else {
                            textField.setText("Error: Division by zero");
                            return;
                        }
                        break;
                }
                textField.setText(String.valueOf(result));
            } else {
                textField.setText(textField.getText() + command);
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
