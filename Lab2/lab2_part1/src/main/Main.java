/******************************************************
 *
 * Project: javaRL
 * File: Main.java
 * @version 1.0
 * Date: 02.05.2003
 * 
 * @author  Alexander Kleiner,
 * 		    University of Freiburg
 * 		    kleiner@informatik.uni-freiburg.de
 * 
 * Description: Main class
 * 
 ******************************************************/
package main;
import viewer.Console;
import viewer.ValueFunction;
import viewer.Viewer;
import world.Maze;
import world.World;

public class Main {
    private static String m_ConfigFile;
    private static World m_World;
    private static Agent m_Agent;
    private static Viewer m_Viewer;

	private static byte type = Maze.MAZE_ALTERNATIVES;
	private static int size = 50;
    private static int dirt = 10;
    private static int block = 0;
    private static boolean fullyObservable = true;

    private static void parseArgument(String args[]) {
        String arg;
        int i = 0;

        while (i < args.length && args[i].startsWith("-")) {
            arg = args[i++];

            if (arg.compareToIgnoreCase("-h") == 0) {
                System.out.println("--------------------------------------------");
                System.out.println("Help:");
                System.out.println("--------------------------------------------");
                System.out.println("-a: Maze with alternatives (default)");
                System.out.println("-n: Maze without alternatives");
                System.out.println("-s: Simple maze (Only walls)");
                System.out.println("-o: Office like maze");
                System.out.println("-f: Fully observable maze ");
                System.out.println("-size #: Size of the maze in X and Y");
                System.out.println("-dirt #: Number of dirt");
                System.out.println("-block #: Number of blockades");
                System.exit(0);
                return;
            }
            if (arg.equals("-size")) {
                System.out.print("Size: ");
                if (i < args.length) {
                    size = (Integer.valueOf(args[i++])).intValue();
                    System.out.println(size);
                }
                else
                    System.err.println("-size requires a number");
            }
            if (arg.equals("-dirt")) {
                System.out.print("Dirt: ");
                if (i < args.length) {
                    dirt = (Integer.valueOf(args[i++])).intValue();
                    System.out.println(dirt);
                }
                else
                    System.err.println("-dirt requires a number");
            }

            if (arg.equals("-block")) {
                System.out.print("Blockades: ");
                if (i < args.length) {
                    block = (Integer.valueOf(args[i++])).intValue();
                    System.out.println(block);
                }
                else
                    System.err.println("-block requires a number");
            }
            if (arg.compareToIgnoreCase("-f") == 0) {
                fullyObservable = true;
            }

            if (arg.compareToIgnoreCase("-a") == 0) {
                type = Maze.MAZE_ALTERNATIVES;
            }
            else if (arg.compareToIgnoreCase("-n") == 0) {
                type = Maze.MAZE_NO_ALTERNATIVES;
            }
            else if (arg.compareToIgnoreCase("-s") == 0) {
                type = Maze.MAZE_ONLY_BORDER;
            }
            else if (arg.compareToIgnoreCase("-o") == 0) {
                type = Maze.MAZE_OFFICE;
            }
            else if (arg.compareToIgnoreCase("-q1") == 0) {
                type = Maze.MAZE_QCUT_1;
            }
            else if (arg.compareToIgnoreCase("-q2") == 0) {
                type = Maze.MAZE_QCUT_2;
            }
            else if (arg.compareToIgnoreCase("-q3") == 0) {
                type = Maze.MAZE_QCUT_3;
            }
            else if (arg.compareToIgnoreCase("-tm") == 0) {
                type = Maze.MAZE_TAXI_DOMAIN;
            }
        }
    }

    public static void main(String args[]) throws Exception {
        parseArgument(args);
        m_World = new World(size, dirt, block, type, fullyObservable);
        m_Agent = new Agent(m_World.getWorld());
		ValueFunction m_Function = new ValueFunction(m_World);

		m_Viewer = new Viewer(m_World, m_Agent, m_Function);
        m_World.addObserver(m_Viewer);

        Console.run(m_Viewer, 500, 500);
    }
}
