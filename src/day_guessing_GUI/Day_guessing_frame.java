package day_guessing_GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import player.PlayerList;
import randomSelector.randomSelector;

public class Day_guessing_frame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private final StringBuilder display = new StringBuilder();
    private PlayerList p1, p2;
    private JLabel lblNewLabel;

    // Inner class implementing ActionListener
    private class WeekDayButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String buttonLbl = ((JButton) e.getSource()).getText();

            if (p1.getChance() == 1)
                setPlayerChance(buttonLbl, p1);

            else if (p2.getChance() == 1)
                setPlayerChance(buttonLbl, p2);

            if (p1.getChance() == 0 && p2.getChance() == 0) {
                display.setLength(0);
                display.append("Click Submit Answer for score");
                // Update display window
                lblNewLabel.setText(display.toString());
            }

        }

        private void setPlayerChance(String btnLabel, PlayerList p) {
            switch (btnLabel) {
            case "Monday":
                p.setChoice(1);
                break;
            case "Tuesday":
                p.setChoice(2);
                break;
            case "Wednesday":
                p.setChoice(3);
                break;
            case "Thursday":
                p.setChoice(4);
                break;
            case "Friday":
                p.setChoice(5);
                break;
            case "Saturday":
                p.setChoice(6);
                break;
            case "Sunday":
                p.setChoice(7);
                break;
            }

            p.setChance(0);
        }

    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Day_guessing_frame frame = new Day_guessing_frame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Day_guessing_frame() {
        setTitle("Day Guessing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 380);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // reset output log
        String output_file = "log.txt";
        try {
            FileOutputStream fos = new FileOutputStream(output_file, false); // Create a new file and truncate existing
                                                                                // content
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Function: Import events
        // Create by Quanhui Yu
        // Date: 04/27/2024
        String filePath = "src/files_folder/Events.txt";
        Vector<String> title = new Vector<>(); // declare string vector to store the title
        Vector<String> date = new Vector<>(); // declare string vector to store the month/day/year
        Vector<String> day = new Vector<>(); // declare string vector to store the day Monday..Sunday

        try (FileInputStream events = new FileInputStream(filePath)) {
            Scanner eventScnr = new Scanner(events);
            eventScnr.useDelimiter(";"); // Set the delimiter to ";"

            while (eventScnr.hasNext()) {
                title.add(eventScnr.next());
                date.add(eventScnr.next());
                day.add(eventScnr.next());

            }

            eventScnr.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        // define changeable string and integer to display description OR calculation
        // final StringBuilder display = new StringBuilder();
        final AtomicInteger selector = new AtomicInteger();
        final AtomicInteger answer = new AtomicInteger();
        final AtomicInteger count = new AtomicInteger();
        // initialize players
        p1 = new PlayerList();
        p1.Player();
        p2 = new PlayerList();
        p2.Player();
        System.out.println("output chance of p1 " + p1.getChance());
        System.out.println("output chance of p2 " + p2.getChance());
        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        lblNewLabel = new JLabel("Click Start New to begin");// Display window
        lblNewLabel.setBounds(153, 23, 405, 134);
        contentPane.add(lblNewLabel);
        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        // for each player click on the option, each choice have its own parameter
        // mirror for monday = 1... sunday = 7
        // and the answer is dynamic as for description
        // as player click on the button, it will calculate the absolute of (dynamic
        // answer) - (button's parameter)
        // the smallest get 1 point to the score of PlayerList class
        // 0 is the correct answer.

        JButton mondayButton = new JButton("Monday");
        mondayButton.addActionListener(new WeekDayButtonActionListener());

        mondayButton.setBounds(60, 167, 100, 21);
        contentPane.add(mondayButton);

        JButton tuesdayButton = new JButton("Tuesday");
        tuesdayButton.addActionListener(new WeekDayButtonActionListener());
        tuesdayButton.setBounds(170, 167, 100, 21);
        contentPane.add(tuesdayButton);

        JButton wednesdayButton = new JButton("Wednesday");
        wednesdayButton.addActionListener(new WeekDayButtonActionListener());
        wednesdayButton.setBounds(280, 167, 100, 21);
        contentPane.add(wednesdayButton);

        JButton thursdayButton = new JButton("Thursday");
        thursdayButton.addActionListener(new WeekDayButtonActionListener());
        thursdayButton.setBounds(396, 167, 100, 21);
        contentPane.add(thursdayButton);

        JButton fridayButton = new JButton("Friday");
        fridayButton.addActionListener(new WeekDayButtonActionListener());
        fridayButton.setBounds(503, 167, 100, 21);
        contentPane.add(fridayButton);

        JButton saturdayButton = new JButton("Saturday");
        saturdayButton.addActionListener(new WeekDayButtonActionListener());
        saturdayButton.setBounds(60, 213, 100, 21);
        contentPane.add(saturdayButton);

        JButton sundayButton = new JButton("Sunday");
        sundayButton.addActionListener(new WeekDayButtonActionListener());
        sundayButton.setBounds(170, 213, 100, 21);
        contentPane.add(sundayButton);

        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        JButton btnNewButton_5_1 = new JButton("Start New");
        btnNewButton_5_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Generate random selector
                randomSelector rand = new randomSelector();
                selector.set(rand.random(title)); // Set the value of selector

                // define the answer
                answer.set(rand.score(day, selector.get())); // Get the value of selector
                // Output the description and date
                // System.out.println("t");
                display.setLength(0);
                display.append("<html>").append(title.get(selector.get())).append("<br>")
                        .append(date.get(selector.get())).append("</html>");
                // Update display winndow
                lblNewLabel.setText(display.toString());
                p1.setChoice(0);
                p2.setChoice(0);

            }
        });
        btnNewButton_5_1.setBounds(60, 282, 141, 21);
        contentPane.add(btnNewButton_5_1);

        JButton btnNewButton_5_1_1 = new JButton("Submit Answer");
        btnNewButton_5_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p1.setChance(1);
                p2.setChance(1);
                // display score board
                // output log
                if (Math.abs(p1.getChoice() - answer.get()) == 0) {
                    p1.setScore(1);
                } else if (Math.abs(p2.getChoice() - answer.get()) == 0) {
                    p2.setScore(1);
                } else if (Math.abs(p1.getChoice() - answer.get()) == Math.abs(p2.getChoice() - answer.get())) {
                    p1.setScore(1);
                    p2.setScore(1);
                }

                else if (Math.abs(p1.getChoice() - answer.get()) < Math.abs(p2.getChoice() - answer.get())) {
                    p1.setScore(1);
                } else {
                    p2.setScore(1);

                }

                display.setLength(0);
                display.append("<html>").append("Score of P1: " + p1.getScore()).append("<br>")
                        .append("Score of P2: \n" + p2.getScore());
                lblNewLabel.setText(display.toString());

                try (PrintWriter writer = new PrintWriter(new FileWriter(output_file, true))) {
                    count.addAndGet(1);
                    writer.println("Round " + count.get()); // Write message to log file
                    writer.println("Event index " + selector.get() + " | Answer: " + day.get(selector.get()));
                    writer.println("P1's pick: " + p1.getChoice() + " | P1's score: " + p1.getScore());
                    writer.println("P2's pick: " + p2.getChoice() + " | P2's score: " + p2.getScore());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
        btnNewButton_5_1_1.setBounds(239, 282, 141, 21);
        contentPane.add(btnNewButton_5_1_1);
    }
}
