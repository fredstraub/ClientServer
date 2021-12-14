import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientLab extends JFrame implements ActionListener {
 
    JLabel label;
    JTextField txtOne;
    JTextField txtTwo;
    JTextField txtThree;
    JTextField txtFour;
    JButton btnProcess;
    JTextArea txtS;

    public ClientLab() {
        this.setTitle("IP Address Check");
        this.setSize(600, 240);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
    
        label = new JLabel("IP Address: ");
        label.setBounds(10, 10, 100, 21);
        add(label);

        txtOne = new JTextField();
        txtOne.setBounds(120, 10, 80, 21);
        add(txtOne);

        txtTwo = new JTextField();
        txtTwo.setBounds(205, 10, 80, 21);
        add(txtTwo);

        txtThree = new JTextField();
        txtThree.setBounds(290,10,80,21);
        add(txtThree);

        txtFour = new JTextField();
        txtFour.setBounds(375, 10, 80, 21);
        add(txtFour);

        btnProcess = new JButton("Process");
        btnProcess.setBounds(200, 40, 90, 21);
        btnProcess.addActionListener(this);
        add(btnProcess);

        txtS = new JTextArea();
        txtS.setBounds(10, 85, 550, 120);
        add(txtS);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnProcess)) {
            try {
                processInformation();
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    private void processInformation() throws UnknownHostException, IOException{
        Socket s = new Socket("localhost", 50001);
        ObjectOutputStream p = new ObjectOutputStream(s.getOutputStream());

        int one = Integer.parseInt(txtOne.getText());
        int two = Integer.parseInt(txtTwo.getText());
        int three = Integer.parseInt(txtThree.getText());
        int four = Integer.parseInt(txtFour.getText());

        p.writeObject(new IPValue(one, two, three, four));
        p.flush();

        BufferedReader response = new BufferedReader(new InputStreamReader(s.getInputStream()));
        txtS.setText("The sever responded: " + response.readLine() + "\n" + response.readLine() + "\n" + response.readLine());
        p.close();
        response.close();
        s.close();
    }

    public static void main(String[] args) {
        new ClientLab();
    }
}