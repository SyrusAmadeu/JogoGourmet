package com.groumetgame.dishes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Syrus A D Godoy.
 * @Description
 * 
 *              A classe node serve como objeto para armazenar pratos e tipos.
 *              Ela segue a estrutra de uma arvore unaria
 */
public class Dish {

	private String name;
	private List<Dish> dishCategories;
	private List<String> dishes;

	public Dish(String name) {
		this.name = name;
		this.dishCategories = new ArrayList<Dish>();
		this.dishes = new ArrayList<String>();
	}

	public Dish(String name, String... dishCategory) {
		this.name = name;
		this.dishCategories = new ArrayList<Dish>();
		this.dishes = Arrays.asList(dishCategory);
	}

	public Dish() {
		this.dishCategories = new ArrayList<Dish>();
		this.dishes = new ArrayList<String>();
	}

	public void addDishCategory(Dish... dishCategory) {
		this.dishCategories.addAll(Arrays.asList(dishCategory));
	}

	public void addDish(String... dishes) {
		this.dishes.addAll(Arrays.asList(dishes));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Dish> getDishCategories() {
		return dishCategories;
	}

	public List<String> getDishes() {
		return dishes;
	}

}
