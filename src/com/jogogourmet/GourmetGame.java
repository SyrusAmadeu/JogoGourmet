package com.jogogourmet;

import javax.swing.JOptionPane;

import com.jogogourmet.dishes.Dish;
import com.jogogourmet.engine.GameEngine;
import com.jogogourmet.utils.JOptionPaneUtils;

/**
 * @author Syrus A D Godoy.
 * 
 */
public class GourmetGame {

	public static void main(String[] args) {
		boolean isPlaying = true;

		int letsPlayResponse = JOptionPane.showOptionDialog(null, "Vamos jogar um jogo?",
				JOptionPaneUtils.GAME_NAME_TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				JOptionPaneUtils.SIM_NAO_OPTION, JOptionPaneUtils.SIM_NAO_OPTION[0]);

		isPlaying = letsPlayResponse != -1 && letsPlayResponse != 1;

		if (isPlaying) {
			Dish rootDish = initRootDish();
			GameEngine.getInstance().start(rootDish);
		}

	}

	public static Dish initRootDish() {
		Dish rootDish = new Dish();
		Dish dishCategory1 = new Dish("massa");
		Dish dishCategory2 = new Dish("caldo");

		rootDish.addDishCategory(dishCategory1);
		rootDish.addDishCategory(dishCategory2);
		rootDish.addDish("Bolo de Chocolate");

		Dish dishSubCategory1 = new Dish("com molho");
		dishCategory1.addDish("Lasanha");
		dishCategory1.addDishCategory(dishSubCategory1);
		dishSubCategory1.addDish("Fagottini", "Capeleti", "Fetuccini");

		Dish dishSubCategory2 = new Dish("vegetariano");
		dishSubCategory2.addDish("Caldo de legumes");
		dishCategory2.addDish("Caldo de galinha");
		dishCategory2.addDishCategory(dishSubCategory2);

		return rootDish;
	}

}
