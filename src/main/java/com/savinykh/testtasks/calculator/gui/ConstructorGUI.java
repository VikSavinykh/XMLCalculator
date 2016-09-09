package com.savinykh.testtasks.calculator.gui;

import com.savinykh.testtasks.calculator.calculator.Calculator;
import com.savinykh.testtasks.calculator.domain.Operation;
import com.savinykh.testtasks.calculator.parser.StAXParser;
import com.savinykh.testtasks.calculator.exception.ApplicationException;
import com.savinykh.testtasks.calculator.validators.ValidatorXML;
import com.savinykh.testtasks.calculator.writer.AnswerWriter;
import com.savinykh.testtasks.calculator.writer.AnswerWriterXML;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ManUnited on 02.07.2016.
 * Класс ConstructorGUI для отображения графического интерфейса
 */
public class ConstructorGUI {

    private File defaultDir = new File("src/main/resources"); // директория по-умолчанию
    private File processedFile; // обрабатываемый файл
    private List<Operation> processedOperations;
    private JFrame mainFrame; // главное окно
    private JPanel panelStart = new JPanel(); // стартовая панель
    private JPanel panelWork = new JPanel(); // главная панель
    private StAXParser parser = new StAXParser(); // парсер
    private Calculator calculator = new Calculator();
    private ValidatorXML validatorXML = new ValidatorXML();

    private JMenuItem openXMLMenuItem = new JMenuItem("Open XML..."); // open XML
    private JMenuItem executeMenuItem = new JMenuItem("Calculate..."); // calculate

    // Путь до XSD-файла для валидации
    private final String xsdPath = "src/main/resources/Calculator.xsd";

    /**
     * Создание графического пользовательского интерфейса
     */
    public void create() {
        // создание окна, и настройка его и панелей
        mainFrame = new JFrame("Calculator");
        mainFrame.setSize(500, 500);
        panelWork.setLayout(null);
        panelStart.setLayout(null);

        // настройка и создание MenuBar
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File"); // File

        JMenu runMenu = new JMenu("Run"); // Run

        // Добавление обработчиков (слушателей) для кнопок
        openXMLMenuItem.addActionListener(new OpenXMLListener());
        fileMenu.add(openXMLMenuItem);
        executeMenuItem.addActionListener(new CalculateListener());
        runMenu.add(executeMenuItem);
        executeMenuItem.setEnabled(false);

        menuBar.add(fileMenu);
        menuBar.add(runMenu);
        mainFrame.setJMenuBar(menuBar);

        // Расположение и настройка openFileBtn1
        Icon openFileIcon = new ImageIcon("src/main/resources/image/open_file.png");
        JButton openFileBtn1 = new JButton(openFileIcon);
        openFileBtn1.setLayout(null);
        JLabel openFileLbl1 = new JLabel("Open XML...");
        openFileLbl1.setBounds(35, 100, 100, 25);
        openFileBtn1.add(openFileLbl1);
        openFileBtn1.setBounds((int) mainFrame.getBounds().getWidth() / 2 - 75,
            (int) mainFrame.getBounds().getHeight() / 2 - 140, 150, 150);
        openFileBtn1.addActionListener(new OpenXMLListener());
        panelStart.add(openFileBtn1);

        // Расположение и настройка openFileBtn2
        JButton openFileBtn2 = new JButton(openFileIcon);
        openFileBtn2.setLayout(null);
        JLabel openFileLbl2 = new JLabel("Open XML...");
        openFileLbl2.setBounds(35, 100, 100, 25);
        openFileBtn2.add(openFileLbl2);
        openFileBtn2.setBounds((int) mainFrame.getBounds().getWidth() / 4 - 75,
            (int) mainFrame.getBounds().getHeight() / 2 - 140, 150, 150);
        openFileBtn2.addActionListener(new OpenXMLListener());
        panelWork.add(openFileBtn2);

        // Расположение и настройка calculateBtn
        Icon calculateIcon = new ImageIcon("src/main/resources/image/calculate.png");
        JButton calculateBtn = new JButton(calculateIcon);
        calculateBtn.setLayout(null);
        calculateBtn.setBounds((int) mainFrame.getBounds().getWidth() * 3 / 4 - 75,
            (int) mainFrame.getBounds().getHeight() / 2 - 140, 150, 150);
        JLabel calculateLbl = new JLabel("Calculate...");
        calculateLbl.setBounds(45, 100, 100, 25);
        calculateBtn.add(calculateLbl);
        calculateBtn.addActionListener(new CalculateListener());
        panelWork.add(calculateBtn);

        // Расположение и настройка mainFrame
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(panelStart);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = mainFrame.getSize();
        mainFrame.setLocation((screenSize.width - frameSize.width) / 2,
            (screenSize.height - frameSize.height) / 2);
        mainFrame.setVisible(true);
    }

    /**
     * Обработчик события при нажатии на "Open XML..."
     */
    private class OpenXMLListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {

            // Работа с выбором и обработкой файла
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(defaultDir);
            fileChooser.setAcceptAllFileFilterUsed(false);
            XMLFileFilter xmlFileFilter = new XMLFileFilter(".xml");
            fileChooser.setFileFilter(xmlFileFilter);
            fileChooser.showOpenDialog(mainFrame);

            // назначение обрабатываемого файла и обрабатываемого ВИ
            processedFile = fileChooser.getSelectedFile();
            boolean isValid = validatorXML.validateXML(xsdPath, processedFile.getPath());
            if (isValid) {
                processedOperations = parser.parseDoc(processedFile.getPath());

                // разрешения работы кнопок и смена панелей
                executeMenuItem.setEnabled(true);
                mainFrame.remove(panelStart);
                mainFrame.add(panelWork);
                panelWork.revalidate();
            } else {
                JOptionPane.showMessageDialog(mainFrame,
                    "The selected file is not valid!", "Validator error",
                    JOptionPane.WARNING_MESSAGE);
            }

            // установление выбранной дирректории директоией по-умолчанию
            defaultDir = new File(processedFile.getAbsolutePath());
        }

    }

    /**
     * Обработчик события при нажатии на "Calculate..."
     */
    private class CalculateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // Создание панели выбора директории для сохранения файла
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(defaultDir);
            fileChooser.setDialogTitle("Save");
            fileChooser.setApproveButtonText("Save");
            fileChooser.setAcceptAllFileFilterUsed(false);
            XMLFileFilter xmlFileFilter = new XMLFileFilter(".xml");
            fileChooser.setFileFilter(xmlFileFilter);
            fileChooser.showOpenDialog(mainFrame);
            File outputFile = fileChooser.getSelectedFile();
            outputFile = new File(outputFile.toString() + ".xml");

            try {
                List<Double> results = processedOperations.stream()
                    .map(expression -> calculator.calculate(expression))
                    .collect(Collectors.toList());
                AnswerWriter writer = new AnswerWriterXML();
                writer.writeAnswer(results, outputFile.getPath());
                defaultDir = outputFile;
                boolean isValid = validatorXML.validateXML(xsdPath, outputFile.getPath());
                if (isValid) {
                    JOptionPane.showMessageDialog(mainFrame, "SUCCESS!");
                } else {
                    JOptionPane.showMessageDialog(mainFrame,
                        "Failed to generate the output xml-file", "Validator error",
                        JOptionPane.WARNING_MESSAGE);
                }
            } catch (ApplicationException ex) {
                JOptionPane.showMessageDialog(mainFrame,
                    ex.getErrorMessage().getMessage(), "Error",
                    JOptionPane.WARNING_MESSAGE);
            }
        }

    }
}
