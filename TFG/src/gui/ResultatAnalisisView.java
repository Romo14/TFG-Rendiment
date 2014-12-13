package gui;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class ResultatAnalisisView extends JFrame {

    private static final long serialVersionUID = 9073846157870757887L;

    public ResultatAnalisisView() {
	GroupLayout groupLayout = new GroupLayout(this);
	groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
		Alignment.LEADING).addGap(0, 450, Short.MAX_VALUE));
	groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
		Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));
	setLayout(groupLayout);
    }

}
