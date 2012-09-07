/******************************************************
 *
 * Project: javaRL
 * File: Viewer.java
 * @version 1.0
 * Date: 11.05.2003
 * 
 * @author  Alexander Kleiner,
 * 		    University of Freiburg
 * 		    kleiner@informatik.uni-freiburg.de
 * 
 * Description:
 * 
 *  
 ******************************************************/
package viewer;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Agent;
import world.Maze;
import world.World;

public class Viewer extends JApplet implements Observer {
    private World m_World;
    private Agent m_Agent;
    private Map m_Map;
    private JTextField t = new JTextField(15);
    private ViewableFunction m_Function;
    private boolean m_Planning_View_Valid = false;
    private boolean m_ValueFunction_View_Valid = false;

    private KeyListener kl = new KeyListener() {
        public void keyPressed(KeyEvent e) {
            char c = e.getKeyChar();
            int keyCode = e.getKeyCode();
            int modifiers = e.getModifiers();

            if (keyCode == 38)
                c = 'I';
            if (keyCode == 40)
                c = 'K';
            if (keyCode == 37)
                c = 'J';
            if (keyCode == 39)
                c = 'L';
            executeCommand(c);
            //System.out.println("Map KEY_PRESSED: "+c+" CODE: "+keyCode+" MODIFIERS: "+modifiers);
            e.consume();
        }
        public void keyReleased(KeyEvent e) {
            //System.out.println("Map KEY_RELEASED: ");
        }
        public void keyTyped(KeyEvent e) {
            //System.out.println("Map KEY_TYPED: ");
        }
    };

    private MouseListener ml = new MouseListener() {
        public void mouseClicked(MouseEvent e) {
            e.consume();
        }

        public void mouseEntered(MouseEvent e) {
            requestFocus();

            e.consume();
        }

        public void mouseExited(MouseEvent e) {
            e.consume();

        }

        public void mousePressed(MouseEvent e) {
//            /** Find corresponding grid cell **/
//            int x = e.getX();
//            int y = e.getY() - mb.getHeight();
//            int maxWidth = m_Map.getWidth();
//            int maxHeight = m_Map.getHeight();
//            double hstep = (double) maxWidth / (double) m_World.getWidth();
//            double vstep = (double) maxHeight / (double) m_World.getHeight();
//
//            int gridX = (int) ((double) x / hstep);
//            int gridY = (int) ((double) y / vstep);
//
//            if (e.getButton() == MouseEvent.BUTTON1) {
//                m_Agent.m_World.setBlockadeAt(gridX, gridY);
//                boolean ret = m_World.setBlockadeAt(gridX, gridY);
//                double value = 0.0;
//                if (ret)
//                    value = 1.0;
//                m_Map.repaint();
//                m_Agent.resetPlan();
//            }
        }

        public void mouseReleased(MouseEvent e) { //e.consume();
        }
    };

    private ActionListener al = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            t.setText(((JMenuItem) e.getSource()).getText());
            char c = translateMenuCommand(t.getText());
            System.out.println(t.getText());
            executeCommand(c);
        }
    };

    private class OptionsWindow extends JDialog implements ActionListener {
        private World m_World;
        private Agent m_Agent;
        private JCheckBox obsI;
        private JTextField sizeI, dirtI, blockI, typeI;
        private JLabel sizeT, dirtT, blockT, obsT, typeT;
        private JButton exitB;

        public OptionsWindow(World world, Agent agent, Viewer owner, boolean modal) {
            super();
            m_World = world;
            m_Agent = agent;

            getContentPane().setLayout(new FlowLayout());
            setSize(new Dimension(200, 200));

            setLocation(0, 500);
            setTitle("Maze Applet Options");
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            setVisible(true);

            //			this.agentSelect = new AgentSelectJComboBox();
            //			agentSelect.addActionListener(this);

            JPanel panelL = new JPanel(new GridLayout(6, 2));
            panelL.setSize(10, 10);
            getContentPane().add(panelL);
            String dummy;

            typeT = new JLabel("World Type ");
            typeI = new JTextField(3);
            typeI.setText(String.valueOf(m_World.m_initialType - 100));
            typeI.addActionListener(this);
            panelL.add(typeT);
            panelL.add(typeI);

            sizeT = new JLabel("World Size ");
            sizeI = new JTextField(3);
            sizeI.setText(String.valueOf(m_World.m_initialSize));
            sizeI.addActionListener(this);
            panelL.add(sizeT);
            panelL.add(sizeI);

            dirtT = new JLabel("Num Dirt. ");
            dirtI = new JTextField(3);
            dirtI.setText(String.valueOf(m_World.m_initialDirt));
            dirtI.addActionListener(this);
            panelL.add(dirtT);
            panelL.add(dirtI);

            blockT = new JLabel("Num Block. ");
            blockI = new JTextField(3);
            blockI.setText(String.valueOf(m_World.m_initialBlock));
            blockI.addActionListener(this);
            panelL.add(blockT);
            panelL.add(blockI);

            /*obsT = new JLabel("Full Observ.");
            obsI = new JCheckBox();
            obsI.addActionListener(this);
            Boolean obsB = new Boolean(m_World.m_fullyObservable);
            obsI.setSelected(obsB.booleanValue());
            panelL.add(obsT);
            panelL.add(obsI);*/

            exitB = new JButton("OK");
            exitB.addActionListener(this);
            panelL.add(exitB);

            validate();
        }

        public void actionPerformed(ActionEvent e) {
            String itemString;
            boolean change = false;
            int size = m_World.m_initialSize;
            int block = m_World.m_initialBlock;
            int dirt = m_World.m_initialDirt;
            int type = m_World.m_initialType - 100;
            boolean obs = m_World.m_fullyObservable;

            if (e.getSource() == this.exitB) {
                this.hide();
            }
            if (e.getSource() == this.obsI) {
                if (obs)
                    obs = false;
                else
                    obs = true;
            }
            try {
                if (typeI.getText().length() != 0 && type != Integer.parseInt(typeI.getText())) {
                    type = (Integer.valueOf(typeI.getText())).intValue();
                    change = true;
                }
                if (sizeI.getText().length() != 0 && size != Integer.parseInt(sizeI.getText())) {
                    size = (Integer.valueOf(sizeI.getText())).intValue();
                    change = true;
                }
                if (blockI.getText().length() != 0 && block != Integer.parseInt(blockI.getText())) {
                    block = (Integer.valueOf(blockI.getText())).intValue();
                    change = true;
                }
                if (dirtI.getText().length() != 0 && dirt != Integer.parseInt(dirtI.getText())) {
                    dirt = (Integer.valueOf(dirtI.getText())).intValue();
                    change = true;
                }
            }
            catch (NumberFormatException nfe) {
                System.err.println("Input is not a number!");
            }

            if (change) {
                if (block >= 0 && block < 100)
                    m_World.m_initialBlock = block;
                else
                    System.err.println("Illegal value");

                if (dirt > 0 && dirt < 100)
                    m_World.m_initialDirt = dirt;
                else
                    System.err.println("Illegal value");
                if (type >= 0 && type <= Maze.MAZE_MAX_DOMAIN)
                    m_World.m_initialType = type + 100;
                else
                    System.err.println("Illegal value");

                if (size > 0 && size <= 400)
                    m_World.m_initialSize = size;
                else
                    System.err.println("Illegal value");

                m_World.reset();
                m_Agent.m_World = m_World.getWorld();

                System.out.println("Created new World!");
                System.out.println("Size: " + size);
                System.out.println("Block: " + block);
                System.out.println("Dirt: " + dirt);
            }

        }
    }

    private JMenu[] menus =
        {
            new JMenu("File"),
            new JMenu("World"),
            new JMenu("Agent"),
            new JMenu("Search"),
//            new JMenu("View"),
//            new JMenu("Options")
            };
    private JMenuItem[] itemsFile = { new JMenuItem("Quit (Q)")};
    private JMenuItem[] itemsWorld = { new JMenuItem("List Targets (T)"), new JMenuItem("Reset (R)")};
    private JMenuItem[] itemsAgent =
        {
            new JMenuItem("Go North (UP)"),
            new JMenuItem("Go South (DOWN)"),
            new JMenuItem("Go West (LEFT)"),
            new JMenuItem("Go East (RIGHT)"),
            new JMenuItem("Suck Dirt (O)"),
//            new JMenuItem("Unload Passenger (U)"),
            };
    private JMenuItem[] itemsPlan =
        {
            new JMenuItem("Search & Follow Path to Target (A)"),
            new JCheckBoxMenuItem("Breadth-First Search (Z)"),
            new JCheckBoxMenuItem("Depth-First Search (X)"),
            new JCheckBoxMenuItem("Iterative Deepening Search (C)"),
            new JCheckBoxMenuItem("AStar Search (V)"),
            new JCheckBoxMenuItem("Custom Breadth-First Search (S)"),
            new JCheckBoxMenuItem("Custom Depth-First Search (D)")
            };
//    private JMenuItem[] itemsView =
//        {
//            new JCheckBoxMenuItem("Agent WM"),
//            new JCheckBoxMenuItem("World WM"),
//            new JCheckBoxMenuItem("Numbers"),
//            new JCheckBoxMenuItem("Plan"),
//            new JCheckBoxMenuItem("ValueFunction")};

//    private JMenuItem[] itemsOptions = { new JMenuItem("World Settings")};

    private JMenuBar mb;

    /** 
     * @throws java.awt.HeadlessException
     **/
    public Viewer(World world, Agent agent, ViewableFunction function) throws HeadlessException {
        super();
        m_World = world;
        m_Agent = agent;
        m_Map = new Map(m_World, m_Agent);
        m_Function = function;
        this.requestFocus();
    }

    public void init() {
        getContentPane().add(m_Map);
        addKeyListener(kl);
        addMouseListener(ml);
        // Init the FILE Menues
        for (int i = 0; i < itemsFile.length; i++) {
            itemsFile[i].addActionListener(al);
            menus[0].add(itemsFile[i]);
        }
        // Init the WORLD Menues
        for (int i = 0; i < itemsWorld.length; i++) {
            itemsWorld[i].addActionListener(al);
            menus[1].add(itemsWorld[i]);
        }
        // Init the AGENT Menues
        for (int i = 0; i < itemsAgent.length; i++) {
            itemsAgent[i].addActionListener(al);
            menus[2].add(itemsAgent[i]);
        }
        // Init the PLAN Menues
        for (int i = 0; i < itemsPlan.length; i++) {
            itemsPlan[i].addActionListener(al);
            menus[3].add(itemsPlan[i]);
            if (i == 0)
                menus[3].addSeparator();
        }
        itemsPlan[4].setSelected(true);
        // Init the VIEW Menues
//        for (int i = 0; i < itemsView.length; i++) {
//            itemsView[i].addActionListener(al);
//            menus[4].add(itemsView[i]);
//            if (i == 2 || i == 1)
//                menus[4].addSeparator();
//        }
//        itemsView[0].setSelected(true);
//        itemsView[3].setSelected(true);

//        // Init the Options Menues
//        for (int i = 0; i < itemsOptions.length; i++) {
//            itemsOptions[i].addActionListener(al);
//            menus[5].add(itemsOptions[i]);
//        }

        mb = new JMenuBar();
        for (int i = 0; i < menus.length; i++)
            mb.add(menus[i]);
        setJMenuBar(mb);
    }

    private char translateMenuCommand(String s) {
        char c = 'h';
        if (s.equals(new String("Help (H)")))
            c = 'h';
        else if (s.equals(new String("Search & Follow Path to Target (A)")))
            c = 'a';
        else if (s.equals(new String("List Targets (T)")))
            c = 't';
        else if (s.equals(new String("Suck Dirt (O)")))
            c = 'o';
//        else if (s.equals(new String("Unload Passenger (U)")))
//            c = 'u';
        else if (s.equals(new String("Go North (UP)")))
            c = 'I';
        else if (s.equals(new String("Go South (DOWN)")))
            c = 'K';
        else if (s.equals(new String("Go West (LEFT)")))
            c = 'J';
        else if (s.equals(new String("Go East (RIGHT)")))
            c = 'L';
        else if (s.equals(new String("Quit (Q)")))
            c = 'q';
        else if (s.equals(new String("Reset (R)")))
            c = 'r';
//        else if (s.equals(new String("Agent WM")))
//            c = '1';
//        else if (s.equals(new String("World WM")))
//            c = '2';
//        else if (s.equals(new String("Numbers")))
//            c = '3';
//        else if (s.equals(new String("Plan")))
//            c = '4';
//        else if (s.equals(new String("ValueFunction")))
//            c = '5';
        else if (s.equals(new String("Breadth-First Search (Z)")))
            c = 'Z';
        else if (s.equals(new String("Depth-First Search (X)")))
            c = 'X';
        else if (s.equals(new String("Iterative Deepening Search (C)")))
            c = 'C';
        else if (s.equals(new String("AStar Search (V)")))
            c = 'V';
        else if (s.equals(new String("Custom Breadth-First Search (S)")))
            c = 'S';
        else if (s.equals(new String("Custom Depth-First Search (D)")))
            c = 'D';

//        else if (s.equals(new String("World Settings"))) {
//            OptionsWindow op = new OptionsWindow(m_World, m_Agent, this, true);
//            op.show();
//        }
        else
            System.out.println("Unknown Menu Command!");
        return c;
    }

    private void executeCommand(char c) {
        switch (Character.toUpperCase(c)) {
            case 'Q' :
                System.exit(0);
                break;
            case 'H' :
                System.out.println("---------------HELP-----------------");
                System.out.println("-h- this help");
                System.out.println("-a- Plan and follow path to Target");
                System.out.println("-Use z,x,c,v,s,d to select algorithm");
                System.out.println("-Use arrow Keys for manual navigation, or:");
                System.out.println("-N/8- go north");
                System.out.println("-S/2- go south");
                System.out.println("-E/6- go east");
                System.out.println("-W/4- go west");
                System.out.println("-o- manually suck dirt");
                System.out.println("-t- print world");
                System.out.println("-y- print dirt coordinates");
                System.out.println("-r- reset world");
                System.out.println("-q- Quit");
                System.out.println("------------------------------------");
                break;
            case 'O' :
                m_Agent.act(World.SUCK_DIRT, m_World);
                break;
            case 'I' :
            	m_Agent.resetPlan();
                m_Agent.act(World.GO_NORTH, m_World);
                break;
            case 'K' :
            	m_Agent.resetPlan();
                m_Agent.act(World.GO_SOUTH, m_World);
                break;
            case 'L' :
            	m_Agent.resetPlan();
                m_Agent.act(World.GO_EAST, m_World);
                break;
            case 'J' :
            	m_Agent.resetPlan();
                m_Agent.act(World.GO_WEST, m_World);
                break;
            case 'A' :
                m_Agent.step(m_World);
                m_Map.repaint();
                break;
            case 'T' :
                m_World.print();
                break;
            case 'R' :
                {
                    m_World.reset();
                    System.out.println("Creating new World!");
                    int sm = m_Agent.getSearchMethod();
                    m_Agent = new Agent(m_World.getWorld());
                    m_Map.m_Agent = m_Agent;
                    m_Agent.setSearchMethod(sm);
                    m_Map.repaint();
                    break;
                }
            case 'Y' :
                m_World.printDirt();
                break;
//            case 's' :
//                m_Agent.m_World.setSenses(m_World.getSenses());
//                break;
//            case '1' :
//                m_Map.m_DrawAgentWorld = true;
//                itemsView[0].setSelected(m_Map.m_DrawAgentWorld);
//                itemsView[1].setSelected(!m_Map.m_DrawAgentWorld);
//                m_Map.repaint();
//                break;
//            case '2' :
//				m_Map.m_DrawAgentWorld = false;
//                itemsView[0].setSelected(m_Map.m_DrawAgentWorld);
//                itemsView[1].setSelected(!m_Map.m_DrawAgentWorld);
//                m_Map.repaint();
//                break;
//            case '3' :
//                if (m_Map.m_ShowNumbers)
//                    m_Map.m_ShowNumbers = false;
//                else
//                    m_Map.m_ShowNumbers = true;
//                itemsView[2].setSelected(m_Map.m_ShowNumbers);
//                m_Map.repaint();
//                break;
//            case '4' :
//                if (!m_Map.m_ShowPlanning) {
//                    m_Map.m_ShowPlanning = true;
//                    m_Map.m_ShowValueFunction = false;
//                }
//                itemsView[3].setSelected(m_Map.m_ShowPlanning);
//                itemsView[4].setSelected(m_Map.m_ShowValueFunction);
//                m_Map.repaint();
//                break;
//            case '5' :
//                if (!m_Map.m_ShowValueFunction) {
//                    Collection l = m_Function.getValues();
//                    m_Map.updateValueView(l);
//                    m_Map.m_ShowPlanning = false;
//                    m_Map.m_ShowValueFunction = true;
//                }
//                itemsView[3].setSelected(m_Map.m_ShowPlanning);
//                itemsView[4].setSelected(m_Map.m_ShowValueFunction);
//                m_Map.repaint();
//                break;
            case 'Z' :
                itemsPlan[1].setSelected(true);
                itemsPlan[2].setSelected(false);
                itemsPlan[3].setSelected(false);
                itemsPlan[4].setSelected(false);
                itemsPlan[5].setSelected(false);
                itemsPlan[6].setSelected(false);
                m_Agent.setSearchMethod(Agent.BREADTH_FIRST_SEARCH);
                m_Agent.resetPlan();
                m_Agent.step(m_World);
                break;
            case 'X' :
                itemsPlan[1].setSelected(false);
                itemsPlan[2].setSelected(true);
                itemsPlan[3].setSelected(false);
                itemsPlan[4].setSelected(false);
                itemsPlan[5].setSelected(false);
                itemsPlan[6].setSelected(false);
                m_Agent.setSearchMethod(Agent.DEPTH_FIRST_SEARCH);
                m_Agent.resetPlan();
                m_Agent.step(m_World);
                break;
            case 'C' :
                itemsPlan[1].setSelected(false);
                itemsPlan[2].setSelected(false);
                itemsPlan[3].setSelected(true);
                itemsPlan[4].setSelected(false);
                itemsPlan[5].setSelected(false);
                itemsPlan[6].setSelected(false);
                m_Agent.setSearchMethod(Agent.ITERATIVE_DEEPENING_SEARCH);
                m_Agent.resetPlan();
                m_Agent.step(m_World);
                break;
            case 'V' :
                itemsPlan[1].setSelected(false);
                itemsPlan[2].setSelected(false);
                itemsPlan[3].setSelected(false);
                itemsPlan[4].setSelected(true);
                itemsPlan[5].setSelected(false);
                itemsPlan[6].setSelected(false);
                m_Agent.setSearchMethod(Agent.ASTAR_SEARCH);
                m_Agent.resetPlan();
                m_Agent.step(m_World);
                break;
            case 'S' :
                itemsPlan[1].setSelected(false);
                itemsPlan[2].setSelected(false);
                itemsPlan[3].setSelected(false);
                itemsPlan[4].setSelected(true);
                itemsPlan[5].setSelected(false);
                itemsPlan[6].setSelected(false);
                m_Agent.setSearchMethod(Agent.CUSTOM_BREADTH_FIRST_SEARCH);
                m_Agent.resetPlan();
                m_Agent.step(m_World);
                break;
            case 'D' :
                itemsPlan[1].setSelected(false);
                itemsPlan[2].setSelected(false);
                itemsPlan[3].setSelected(false);
                itemsPlan[4].setSelected(true);
                itemsPlan[5].setSelected(false);
                itemsPlan[6].setSelected(false);
                m_Agent.setSearchMethod(Agent.CUSTOM_DEPTH_FIRST_SEARCH);
                m_Agent.resetPlan();
                m_Agent.step(m_World);
                break;
        }
    }

    // Observer Interface
    public void update(Observable o, Object arg) {
        m_Map.repaint();
    }
    
    public void update() {
    	m_Map.repaint();
    }
}
