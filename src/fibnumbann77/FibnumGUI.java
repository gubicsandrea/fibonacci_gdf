/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibnumbann77;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author user
 */
public class FibnumGUI extends JFrame{
    
    private JLabel labelForSpnNumber;
    private SpinnerModel spinnerModel;
    private JSpinner spnNumber;
    private JButton btnCalculate;

    public FibnumGUI(String title) throws HeadlessException {
        super(title);
        initComponents();
    }
    
    private void initComponents(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(400, 400));
        this.setResizable(false);
        
        labelForSpnNumber = new JLabel("Fibonacci: ");
        spinnerModel = new SpinnerNumberModel(1,1,Integer.MAX_VALUE,1);
        spnNumber = new JSpinner(spinnerModel);
        btnCalculate = new JButton("Csináld!");
        
        add(labelForSpnNumber);
        add(spnNumber);
        add(btnCalculate);
        
        pack();
    }
    
    public static void main(String[] args) {
        FibnumGUI gui = new FibnumGUI("Fibonacci számok");
        gui.setVisible(true);
    }
    
}
