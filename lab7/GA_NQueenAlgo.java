package lab7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GA_NQueenAlgo {
	public static final int POP_SIZE = 100;// Population size
	public static final double MUTATION_RATE = 0.03;
	public static final int MAX_ITERATIONS = 1000;

	List<Node> population = new ArrayList<Node>();
	Random rd = new Random();

	// initialize the individuals of the population
	public void initPopulation() {
		for (int i = 0; i < POP_SIZE; i++) {
			Node ni = new Node();
			ni.generateBoard();
			population.add(ni);
		}
	}

	public Node preproduce(Node x, Node y) {
		int c = rd.nextInt(Node.N);
		Node re = new Node();
		re.generateBoard();
		for (int i = 0; i < Node.N; i++) {
			if (i <= c) {
				re.setRow(i, x.getRow(i));
			} else {
				re.setRow(i, y.getRow(i));
			}
		}
		return re;
	}

	// Select a random parent from the population
	public Node getParentByRandomSelection() {
		return population.get(rd.nextInt(POP_SIZE));
	}

	// Select K individuals from the population at random and
	// select the best out of these to become a parent.
	public Node getParentByTournamentSelection() {
		List<Node> re = new ArrayList<Node>();
		int k = 3;
		for (int i = 0; i < k; i++) {
			re.add(population.get(rd.nextInt(POP_SIZE)));
		}
		Collections.sort(re, null);
		return re.get(0);
	}

	public Node execute() {
		initPopulation();
		int k = 0;
		while (k++ < MAX_ITERATIONS) {
			List<Node> newPopulation = new ArrayList<Node>();
			for (int i = 0; i < POP_SIZE; i++) {
				Node x = getParentByRandomSelection();
				Node y = getParentByTournamentSelection();
				Node child = preproduce(x, y);
				if (rd.nextDouble() < MUTATION_RATE) {
					mutate(child);
				}
				if (child.getH() == 0) {
					return child;
				}
				newPopulation.add(child);
			}
			Collections.sort(population);
		}
		return population.get(0);

	}

	// Mutate an individual by selecting a random Queen and
	// move it to a random row.
	public void mutate(Node node) {
		int i = rd.nextInt(Node.N);
		int row = rd.nextInt(Node.N);
		node.setRow(i, row);
	}
	// Crossover x and y to preproduce a child

}
