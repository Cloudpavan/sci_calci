import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScientificCalculator extends JFrame implements ActionListener {
    private JTextArea textArea;
    private String expression;
    private double num1, num2, result;
    private String operator;
    private boolean operatorClicked;

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea(10, 10);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "sin", "cos", "tan", "C"
        };

        for (String buttonText : buttons) {
            JButton button = new JButton(buttonText);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.matches("[0-9.]")) {
            if (operatorClicked) {
                textArea.setText("");
                operatorClicked = false;
            }
            textArea.append(command);
            expression += command;
        } else if (command.matches("[+\\-*/]")) {
            if (!operatorClicked) {
                operator = command;
                expression += " " + operator + " ";
                textArea.setText(expression);
                operatorClicked = true;
            }
        } else if (command.equals("=")) {
            if (operatorClicked) {
                textArea.setText("Invalid Expression");
                return;
            }
            String[] parts = expression.split("\\s+");
            num1 = Double.parseDouble(parts[0]);
            num2 = Double.parseDouble(parts[2]);
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        textArea.setText("Cannot divide by zero");
                        return;
                    }
                    result = num1 / num2;
                    break;
            }
            textArea.setText(expression + " = " + result);
            operatorClicked = false;
        } else if (command.equals("C")) {
            textArea.setText("");
            expression = "";
            operatorClicked = false;
        } else if (command.equals("sin")) {
            double number = Double.parseDouble(textArea.getText());
            result = Math.sin(Math.toRadians(number));
            textArea.setText(textArea.getText() + " = " + result);
        } else if (command.equals("cos")) {
            double number = Double.parseDouble(textArea.getText());
            result = Math.cos(Math.toRadians(number));
            textArea.setText(textArea.getText() + " = " + result);
        } else if (command.equals("tan")) {
            double number = Double.parseDouble(textArea.getText());
            result = Math.tan(Math.toRadians(number));
            textArea.setText(textArea.getText() + " = " + result);
        }
    }

    public static void main(String[] args) {
        new ScientificCalculator();
    }
}
