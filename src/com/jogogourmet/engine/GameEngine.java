package com.jogogourmet.engine;

import javax.swing.JOptionPane;

import com.jogogourmet.dishes.Dish;
import com.jogogourmet.utils.JOptionPaneUtils;

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
		while (isPlaying) {

			String dishCategory = "";
			String dish = "";

			panelAnswer = JOptionPane.showOptionDialog(null, "Pensa em um prato que você gosta",
					JOptionPaneUtils.GAME_NAME_TITLE, JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null,
					JOptionPaneUtils.OK_OPTION, JOptionPaneUtils.OK_OPTION[0]);

			exitMessage(panelAnswer);

			if (findDish(root)) {
				panelAnswer = JOptionPane.showOptionDialog(null, "Eu venci dessa vez!",
						JOptionPaneUtils.GAME_NAME_TITLE, JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
						JOptionPaneUtils.OK_OPTION, JOptionPaneUtils.OK_OPTION[0]);

				exitMessage(panelAnswer);
				lastDishIndex = -1;
				
				continue;
			}

			dish = requiredInputLoop("* Qual prato é?");
			dishCategory =  requiredInputLoop("* O que ele é que o " + lastDish.getDishes().get(lastDishIndex) + " não é?");
			
			Dish newDish = new Dish(dishCategory, dish);

			lastDish.addDishCategory(newDish);
			lastDishIndex = -1;
		}
	}

	private String requiredInputLoop(String message) {
		String item = "";
		while (item == null || item.isEmpty() || item.isBlank()) {
			item = JOptionPane.showInputDialog(null, message, JOptionPaneUtils.GIVE_UP_TITLE,
					JOptionPane.QUESTION_MESSAGE);

			exitMessage(item);
		}
		return item;
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
