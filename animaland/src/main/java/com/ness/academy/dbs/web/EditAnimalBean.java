package com.ibm.itacademy.animaland.web;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.itacademy.animaland.Animal;
import com.ibm.itacademy.animaland.AnimalDao;
import com.ibm.itacademy.animaland.AnimalDaoFactory;

@ManagedBean
public class EditAnimalBean {
	private static final Logger logger = LoggerFactory
			.getLogger(EditAnimalBean.class);

	private AnimalDao animalDao = AnimalDaoFactory.getInstance().getAnimalDao();

	@ManagedProperty("#{param.id}")
	private Long id;
	
	private Animal animal;
	
	@PostConstruct
	public void init() {
		if (id != null) {
			this.animal = animalDao.findById(id);
			logger.info("Initialized animal ID = {} to {}", this.id, this.animal);
		} else {
			this.animal = new Animal();
		}
	}

	public String submit() {
		logger.info("Saving animal {}", animal);
		animalDao.saveOrUpdate(animal);

		return "animals?faces-redirect=true";
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
}
