import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;
import java.util.*;
class Pre_Final{
    
    static JFrame frame = new JFrame("Calculator");
    static JButton calculateButton = new JButton("Compute");
    static JTextField textField1 = new JTextField();
    static JTextField textField2 = new JTextField();
    static List <JButton> buttons = getButtons();
    static JLabel labelAnswer = new JLabel("0");
    static JLabel labelNum1 = new JLabel("Enter 1st Number: ");
    static JLabel labelNum2 = new JLabel("Enter 2nd Number: ");
    public static void main(String[] args){

        //initializeComponent
        initializeComponent();
    }
    public static List<JButton> getButtons(){
        //list of Buttons;
        return List.of(
            new JButton("Addition"),
            new JButton("Subtraction"),
            new JButton("Multiplication"),
            new JButton("Division")
        );
    }
    public static void initializeComponent(){
        //initializing Java Swing Component
        int xAxis = 10;
        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).setSize(100,50);
            buttons.get(i).setLocation(xAxis,200);
            buttons.get(i).addActionListener(new OperationButton());
            frame.add(buttons.get(i));
            xAxis = xAxis + 120;
            System.out.println(buttons.get(i).getText());
        }
        textField1.setSize(250,30);
        textField1.setLocation(150,60);
        textField2.setSize(250,30);
        textField2.setLocation(150,100);
        calculateButton.setLocation(180,300);
        calculateButton.setSize(100,50);
        labelAnswer.setSize(100,50);
        labelAnswer.setLocation(225,370);
        labelAnswer.setFont(new Font("Verdana",Font.PLAIN,36));
        labelNum1.setSize(200,50);
        labelNum1.setLocation(40,50);
        labelNum2.setSize(200,50);
        labelNum2.setLocation(40,90);
        calculateButton.addActionListener(new CalculateButton());
        frame.add(labelAnswer);
        frame.add(labelNum1);
        frame.add(labelNum2);
        frame.add(calculateButton);
        frame.add(textField1);
        frame.add(textField2);
        frame.setLayout(null);
        frame.setSize(500,500);
        frame.setVisible(true);
    }
    static class CalculateButton implements ActionListener{
        //Click listener for Calculate Button
        public void actionPerformed(ActionEvent e){
            System.out.println(e.getActionCommand());
            try{
                if(textField1.getText().isEmpty() || textField2.getText().isEmpty()) throw new EmptyInputException("Empty Required Fields");
                
                    Calculator calculator = new Calculator(Integer.parseInt(textField1.getText()),Integer.parseInt(textField2.getText()));
                     System.out.println(calculator.getOperator());
                    if(calculator.getOperator() == null) throw new NoOperationException("Please add operation");  
                        calculator.calculate(); 
                        labelAnswer.setText(calculator.getAnswer());
                }
            catch(Exception error){
                JOptionPane.showMessageDialog(null,error.getMessage());
            }
        }

    }
    static class OperationButton implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //click listener for Operation button
            Calculator calculatorOperator = new Calculator();
            calculatorOperator.setOperator(e.getActionCommand());
        }
    }
}
class Calculator{
    public int num1;
    public int num2;
    public int answer;
    private static String operation;
    Calculator(){
        //constructor overloading
    }
    Calculator(int num1, int num2){
        this.num1 = num1;
        this.num2 = num2;
        //constructor overloading
    }
    public void setOperator(String operation){
        Calculator.operation = operation;
    }
    public String getOperator(){

        return Calculator.operation;
    }
    public void calculate(){
        switch(operation){
          case "Addition":
            this.answer = this.num1 + this.num2;
            break;
            case "Subtraction":
            this.answer = this.num1 - this.num2;
            break;
            case "Multiplication":
            this.answer = this.num1 * this.num2;
            break;
            case "Division":
            this.answer = this.num1 / this.num2;
            break;
        }
    }
    public String getAnswer(){
        return Integer.toString(answer);
    }
    
}