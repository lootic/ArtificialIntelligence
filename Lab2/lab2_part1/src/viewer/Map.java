/******************************************************
 *
 * Project: searchDemo
 * File: Map.java
 * @version 1.0
 * Date: 26.04.2004
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

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JPanel;

import main.Agent;
import world.GridPos;
import world.World;

class Map extends JPanel {
    public boolean m_DrawAgentWorld = true;
    public boolean m_ShowClustering = false;
    public boolean m_ShowPlanning = true;
    public boolean m_ShowValueFunction = false;
    public boolean m_ShowNumbers = false;
    private HashMap m_ValueColors = new HashMap();
    private HashMap m_ValueNumbers = new HashMap();
    public World m_World;
    public Agent m_Agent;

    //private int maxWidth = 100;
    //private int maxHeight = 100;

    private int maxWidth;
    private int maxHeight;

    public Map(World world, Agent agent) {
        m_World = world;
        m_Agent = agent;
    }

    public void updateClusterView(Collection l) {
        Iterator it = l.iterator();
        while (it.hasNext()) {
            GridPos p = (GridPos) it.next();
            Color color = (Color) it.next();
            Integer clusterNum = (Integer) it.next();
        }
        repaint();
    }

    public void updateValueView(Collection l) {
        Iterator it = l.iterator();
        while (it.hasNext()) {
            GridPos p = (GridPos) it.next();
            Color color = (Color) it.next();
            Double clusterNum = (Double) it.next();
            m_ValueColors.put(new Point(p.m_x, p.m_y), new Color(color.getRGB()));
            m_ValueNumbers.put(new Point(p.m_x, p.m_y), clusterNum);
        }
        repaint();
    }

    public void setValueColor(GridPos p, Color c) {
        m_ValueColors.put(new Point(p.m_x, p.m_y), new Color(c.getRGB()));
    }
    public Color getValueColor(GridPos p) {
        return (Color) m_ValueColors.get(new Point(p.m_x, p.m_y));
    }
    public void setValueNumber(GridPos p, Double value) {
        m_ValueNumbers.put(new Point(p.m_x, p.m_y), value);
    }
    public Double getValueNumber(GridPos p) {
        return (Double) m_ValueNumbers.get(new Point(p.m_x, p.m_y));
    }
    public void setWidth(int w) {
        maxWidth = w;
    }
    public void setHeight(int h) {
        maxHeight = h;
    }
    public void clear() {
        m_ValueColors.clear();
        m_ValueNumbers.clear();
    }

    public void paintComponent(Graphics g) {
        if (m_DrawAgentWorld)
            drawMaze(g, m_Agent.m_World);
        else
            drawMaze(g, m_World);
    }

    public void draw(Graphics g) {
        if (m_DrawAgentWorld)
            drawMaze(g, m_Agent.m_World);
        else
            drawMaze(g, m_World);
    }

    public void drawMaze(Graphics g, World world) {
        maxWidth = getWidth();
        maxHeight = getHeight();
        double hstep = (double) maxWidth / (double) world.getWidth();
        double vstep = (double) maxHeight / (double) world.getHeight();
        Color black = new Color(0, 0, 0);

        for (int x = 0; x < world.getWidth(); x++)
            for (int y = 0; y < world.getHeight(); y++) {
                double _x1 = (double) x * hstep;
                double _y1 = (double) y * vstep;
                double _w = hstep * 1.1;
                double _h = vstep * 1.1;
                int x1 = (int) Math.round(_x1);
                int y1 = (int) Math.round(_y1);
                int w = (int) Math.round(_w);
                int h = (int) Math.round(_h);

                GridPos pos = new GridPos(x, y);

                if (m_ShowValueFunction) {
                    if (getValueColor(pos) != null) {
                        g.setColor(getValueColor(pos));
                        g.fillRect(x1, y1, w, h);
                    }
                    else {
                        g.setColor(black);
                        g.fillRect(x1, y1, w, h);
                    }
                    if (world.m_Maze.wallAt(pos)) {
                        g.setColor(Color.BLACK);
                        g.fillRect(x1, y1, w, h);
                    }
                    else if (world.m_Maze.blockadeAt(pos)) {
                        g.setColor(Color.ORANGE);
                        g.fillRect(x1, y1, w, h);
                    }
                    if (m_ShowNumbers && getValueColor(pos) != null) {
                        g.setColor(Color.WHITE);
                        Font f = g.getFont();
                        Font f2 = f.deriveFont(9.0f);
                        g.setFont(f2);
                        String s = new String("");
                        s += getValueNumber(pos).doubleValue();
                        g.drawString(s, x1 + (int) hstep / 2, y1 + (int) vstep / 2);
                    }
                }
                if (m_ShowPlanning) {
                    if (world.m_Maze.markedPath1At(pos)) {
                        g.setColor(Color.YELLOW);
                        g.fillRect(x1, y1, w, h);
                    }
                    else if (world.m_Maze.markedPath2At(pos)) {
                        g.setColor(Color.MAGENTA);
                        g.fillRect(x1, y1, w, h);
                    }
                    else if (world.m_Maze.markedAsFree(pos)) {
                        g.setColor(Color.WHITE);
                        g.fillRect(x1, y1, w, h);
                    }
                    else { // Unknown Terrain
                        g.setColor(Color.LIGHT_GRAY);
                        g.fillRect(x1, y1, w, h);
                    }

                    if (world.m_Maze.loadedAgentAt(pos)) {
                        g.setColor(Color.RED);
                        g.fillRect(x1, y1, w, h);
                    }
                    else if (world.m_Maze.agentAt(pos)) {
                        g.setColor(Color.GREEN);
                        g.fillRect(x1, y1, w, h);
                    }
                    else if (world.m_Maze.dirtAt(pos)) {
                        g.setColor(Color.BLUE);
                        g.fillRect(x1, y1, w, h);
                    }
                    //else if (world.m_Maze.destinationAt(pos)) {
                    //g.setColor(Color.LIGHT_GRAY);
                    //g.fillRect(x1, y1, w, h);
                    //}
                    else if (world.m_Maze.wallAt(pos)) {
                        g.setColor(Color.BLACK);
                        g.fillRect(x1, y1, w, h);
                    }
                    else if (world.m_Maze.blockadeAt(pos)) {
                        g.setColor(Color.ORANGE);
                        g.fillRect(x1, y1, w, h);
                    }
                    if (m_ShowNumbers) {
                        if (x > 0 && x < world.getWidth() - 1 && y > 0 && y < world.getHeight() - 1) {
                            g.setColor(Color.WHITE);
                            String s;
                            //s = "(" + x + "," + y + ")";
                            s = x + "," + y;
                            Font f = g.getFont();
                            Font f2 = f.deriveFont(7.0f);
                            g.setFont(f2);
                            g.drawString(s, x1 + (int) hstep / 2, y1 + (int) vstep / 2);
                        }
                    }
                }
            }
    }
}