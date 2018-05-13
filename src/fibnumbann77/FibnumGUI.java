/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibnumbann77;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 * main GUI for the Fibonacci program
 * @author user
 */
public class FibnumGUI extends JFrame{
    
    private FibonacciRepository dbRepo  = new FibonacciDbRepository();
    
    private List<FibPair> numbers = new ArrayList<>();
    
    private Properties prop = new Properties();
    
    private JLabel labelForSpnNumber;
    private SpinnerModel spinnerModel;
    private JSpinner spnNumber;
    private JButton btnCalculate;
    private JButton btnAbout;
    private JProgressBar progressBar;
    private TableModel tblModel;
    private JTable tblNumbers;
    private JScrollPane scrollPane;
    private JTabbedPane tabs;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;

    public FibnumGUI(String title) throws HeadlessException {
        super(title);
        loadProperties();
        ((FibonacciDbRepository) dbRepo).initDatabase();
        numbers = dbRepo.load();
        initComponents();
        System.out.println(numbers);
    }
    
    private void initComponents(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(400, 400));
        this.setResizable(false);
        
        labelForSpnNumber = new JLabel("Fibonacci: ");
        int spnNumberDefaultValue = Integer.parseInt(prop.getProperty("spinner.default", "1"));
        spinnerModel = new SpinnerNumberModel(spnNumberDefaultValue,1,Integer.MAX_VALUE,1);
        spnNumber = new JSpinner(spinnerModel);
        btnCalculate = new JButton("Csináld!");
        progressBar = new JProgressBar();
        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Worker worker = new Worker();
                worker.addPropertyChangeListener(new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if("progress".equals(evt.getPropertyName())){
                            progressBar.setValue((Integer)evt.getNewValue());
                        }
                    }
                });
                btnCalculate.setEnabled(false);
                worker.execute();
            }
        });
        
        btnAbout = new JButton("About");
        btnAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = FibnumBANN77.PROGRAM_NAME + " v" + FibnumBANN77.VERSION + System.lineSeparator() + "Készítette: " + FibnumBANN77.CREATOR;
                JOptionPane.showMessageDialog(null, message);
            }
        });
        
        tblModel = new FibonacciTableModel(numbers);
        tblNumbers = new JTable(tblModel);
        
        tabs = new JTabbedPane();
        
        panel1 = new JPanel(new FlowLayout());
        panel2 = new JPanel(new FlowLayout());
        panel3 = new JPanel(new BorderLayout());
        
        panel1.setPreferredSize(new Dimension(390, 390));
        panel2.setPreferredSize(new Dimension(390, 390));
        panel3.setPreferredSize(new Dimension(390, 390));
        
        panel1.add(labelForSpnNumber);
        panel1.add(spnNumber);
        panel1.add(btnCalculate);
        panel1.add(progressBar);
        
        panel2.add(btnAbout);
        
        scrollPane = new JScrollPane(tblNumbers);
        
        panel3.add(scrollPane, BorderLayout.CENTER);
        
        tabs.addTab("Számítás", panel1);
        tabs.addTab("Előzmények", panel3);
        tabs.addTab("About", panel2);
        
        add(tabs);
        
        pack();
    }
    
    private void loadProperties(){
        try {
            InputStream is = this.getClass().getResourceAsStream("/application.properties");
            prop.load(is);
        } catch (IOException ex) {
            Logger.getLogger(FibnumGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        FibnumGUI gui = new FibnumGUI("Fibonacci számok");
        gui.setVisible(true);
    }
    
    class Worker extends SwingWorker<Void, Integer>{

        @Override
        protected Void doInBackground() throws Exception {
            try{
                for(int i = 20; i<= 100; i+=20){
                    Thread.sleep(1000);
                    setProgress(i);
                }
            } catch (InterruptedException ex){
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void done() {
            int n = (Integer) spnNumber.getValue();
            int fib = FibnumBANN77.computeFN(n);
            FibPair pair = new FibPair(n, fib);
            numbers.add(pair);
            ((AbstractTableModel) tblModel).fireTableDataChanged();
            String message = n + ". fibonacci száma: " + fib;
            JOptionPane.showMessageDialog(null, message, "Eredmény", JOptionPane.INFORMATION_MESSAGE);
            dbRepo.save(pair);
            btnCalculate.setEnabled(true);
            progressBar.setValue(0);
        }
        
        
        
    }
    
}
