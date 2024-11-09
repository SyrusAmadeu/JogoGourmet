package com.groumetgame.engine;

import javax.swing.JOptionPane;

import com.groumetgame.dishes.Dish;
import com.groumetgame.utils.JOptionPaneUtils;

/**
 * @author Syrus A D Godoy.
 * 
 * @Description A classe game engine possui toda a lógica de processamento do
 *              jogo, incluindo o algoritmo para percorrer a arvore unaria onde
 *              os pratos e tipos são salvos.
 */

public class GameEngine {
	private static GameEngine instance;
	private static Dish lastDish = null;
	private static int lastDishIndex = -1;
	private static int panelAnswer = 0;

	private GameEngine() {
	}

	public static GameEngine getInstance() {
		if (instance == null)
			instance = new GameEngine();

		return instance;
	}

	/**
	 * Inicia o loop principal do jogo
	 * 
	 * @param root É o objeto que possui as informações dos pratos e seus tipos
	 * 
	 * @Description Este metodo deverá iterar pelos nós da árvore unária, assim
	 *              verificando tipos (atributo filho) e pratos. Cada message em
	 *              JOptionPane possui um metodo exit que verifica se o x não foi
	 *              clicado.
	 * 
	 */

	public void start(Dish root) {
		boolean isPlaying = true;
		boolean dishFound = false;
		while (isPlaying) {

			String dishCategory = "";
			String dish = "";

			panelAnswer = JOptionPane.showOptionDialog(null, "Pensa em um prato que você gosta",
					JOptionPaneUtils.GAME_NAME_TITLE, JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null,
					JOptionPaneUtils.OK_OPTION, JOptionPaneUtils.OK_OPTION[0]);

			exitMessage(panelAnswer);

			dishFound = findDish(root);

			if (dishFound) {
				panelAnswer = JOptionPane.showOptionDialog(null, "Eu venci dessa vez!",
						JOptionPaneUtils.GAME_NAME_TITLE, JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
						JOptionPaneUtils.OK_OPTION, JOptionPaneUtils.OK_OPTION[0]);

				exitMessage(panelAnswer);
				continue;
			}

			while (dish == null || dish.isEmpty() || dish.isBlank()) {
				dish = JOptionPane.showInputDialog(null, "* Qual prato é?", JOptionPaneUtils.GIVE_UP_TITLE,
						JOptionPane.QUESTION_MESSAGE);

				exitMessage(dish);
			}

			while (dishCategory == null || dishCategory.isEmpty() || dishCategory.isBlank()) {
				dishCategory = JOptionPane.showInputDialog(null,
						"* O que ele é que o " + lastDish.getDishes().get(lastDishIndex) + " não é?",
						JOptionPaneUtils.GIVE_UP_TITLE, JOptionPane.QUESTION_MESSAGE);

				exitMessage(dishCategory);
			}

			Dish newDish = new Dish(dishCategory, dish);

			lastDish.addDishCategory(newDish);
			lastDishIndex = -1;
		}
	}

	private void exitMessage(String answer) {
		if (answer == null)
			exitMessage();
	}

	private void exitMessage(int answer) {
		if (answer == -1) {
			exitMessage();
		}
	}

	private void exitMessage() {
		boolean exit = JOptionPane.showOptionDialog(null, "Você deseja sair do jogo?", JOptionPaneUtils.GAME_NAME_TITLE,
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, JOptionPaneUtils.SIM_NAO_OPTION,
				JOptionPaneUtils.SIM_NAO_OPTION[0]) == 0;
		if (exit) {
			System.exit(0);
		}
	}

	private boolean findDish(Dish rootDish) {
		lastDish = rootDish;

		for (Dish dishCategory : rootDish.getDishCategories()) {
			if (displayConfirmDishMessage(dishCategory.getName())) {
				lastDish = dishCategory;
				break;
			}
		}

		if (!lastDish.equals(rootDish)) {
			return findDish(lastDish);
		}

		for (String prato : lastDish.getDishes()) {
			if (!displayConfirmDishMessage(prato)) {
				lastDishIndex++;
				continue;
			}

			return true;
		}

		return false;
	}

	private String buildDishQuestion(String dish) {
		StringBuilder message = new StringBuilder("O prato que você pensou é ");
		message.append(dish);
		message.append("?");

		return message.toString();
	}

	private boolean displayConfirmDishMessage(String dish) {
		return JOptionPane.showOptionDialog(null, buildDishQuestion(dish), JOptionPaneUtils.GAME_NAME_TITLE,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, JOptionPaneUtils.SIM_NAO_OPTION,
				JOptionPaneUtils.SIM_NAO_OPTION[0]) == 0;
	}
}
