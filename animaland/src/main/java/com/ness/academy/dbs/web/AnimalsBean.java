package com.ibm.itacademy.animaland.web;

import java.util.List;

import javax.faces.bean.ManagedBean;

import com.ibm.itacademy.animaland.Animal;
import com.ibm.itacademy.animaland.AnimalDao;
import com.ibm.itacademy.animaland.AnimalDaoFactory;

@ManagedBean
public class AnimalsBean {
	private AnimalDao animalDao = AnimalDaoFactory.getInstance().getAnimalDao();
	
	public List<Animal> getAnimals() {
		return animalDao.list();
	}
	
	public void delete(Animal animal) {
		System.err.println("Deleting an animal..." + animal);
	}
}
