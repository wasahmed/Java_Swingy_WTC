package com.wasahmed.swingy.controllers;

import com.wasahmed.swingy.models.Hero;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class GetData {
    private static Validator validator;
    public Hero createHero(
            String heroName,
            String heroClass,
            int heroLevel,
            int heroExp,
            int heroAttack,
            int heroDefence,
            int hp,
            int xCoordinate,
            int yCoordinate
    ){
        Hero hero = new Hero();
        hero.setHeroName(heroName);
        hero.setHeroClass(heroClass);
        hero.setHeroLevel(heroLevel);
        hero.setHeroExp(heroExp);
        hero.setHeroAttack(heroAttack);
        hero.setDefense(heroDefence);
        hero.setHp(hp);
        hero.setxCoordinate(xCoordinate);
        hero.setyCoordinate(yCoordinate);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        validate(hero);


        return (hero);
    }
    public static void validate(Hero hero) {
        Set<ConstraintViolation<Hero>> cvs = validator.validate(hero);
        if(cvs.size() > 0){
            for (ConstraintViolation<Hero> cv : cvs) {
                System.out.println(cv.getMessage());
            }
            System.exit(1);
        }
    }
}
