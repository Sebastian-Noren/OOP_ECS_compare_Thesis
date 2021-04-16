package ecs_bank.ecs_core;


import ecs_bank.ecs_core.components.AccountComponent;
import ecs_bank.ecs_core.components.AddressComponent;
import ecs_bank.ecs_core.components.PersonDetailComponent;
import ecs_bank.ecs_core.systems.AccountInitSystem;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 *     Based on Adam Martins ECS theory
 */
public class ECSMain {
    public static void main(String[] args) {


        EntityManagerECS managerECS = new EntityManagerECS();


        // CREATE 4 Entities
        int zero = managerECS.createEntity();
        int one = managerECS.createEntity();
        int two = managerECS.createEntity();
        int three = managerECS.createEntity();

        // ADD COMPONENTS
        managerECS.addComponent(zero, new PersonDetailComponent("Sebastian", "Losto", "198xxxxx-xxxx"));
        managerECS.addComponent(zero, new AccountComponent());
        managerECS.addComponent(zero, new AddressComponent("eriksdaln 1"));

        managerECS.addComponent(one, new PersonDetailComponent("John", "Doe", "19xxxx945-xxxx"));
        managerECS.addComponent(one, new AddressComponent("Lost 1"));

        managerECS.addComponent(two, new PersonDetailComponent("Adolf", "Bodil", "xxxx0112-31xx"));
        managerECS.addComponent(two, new AccountComponent());
        managerECS.addComponent(two, new AddressComponent("Didriksväg 26"));

        managerECS.addComponent(three, new PersonDetailComponent("Arnold", "Chan", "19xxxx01-3xxx"));
        managerECS.addComponent(three, new AccountComponent());
        managerECS.addComponent(three, new AddressComponent("Faltobo 10"));


        System.out.println(managerECS.getAllEntitiesPossessingComponent(PersonDetailComponent.class));

        // CHANGE VARIABLE COMPONENT
        managerECS.getComponent(zero, PersonDetailComponent.class).lastName = "CHANGE NAME";
        System.out.println(managerECS.getComponent(zero, PersonDetailComponent.class));
        System.out.println();


        // CHECK IF COMPONENT EXIST
        System.out.println(managerECS.getAllComponentsForEntity(zero).toString());
        System.out.println(managerECS.hasComponent(zero,PersonDetailComponent.class));
        managerECS.removeComponent(zero,PersonDetailComponent.class);
        System.out.println(managerECS.getAllComponentsForEntity(0).toString());
        System.out.println(managerECS.hasComponent(zero,PersonDetailComponent.class));
        System.out.println(managerECS.getAllComponentsOfType(PersonDetailComponent.class));
        System.out.println();

        System.out.println(managerECS.getAllEntitiesPossessingComponent(PersonDetailComponent.class));

        // SYSTEM INIT - 100 SEK on every account
        AccountInitSystem sys = new AccountInitSystem();
        sys.process(managerECS.getAllComponentsOfType(AccountComponent.class));
        System.out.println(managerECS.getAllComponentsOfType(AccountComponent.class));
        managerECS.getComponent(0, AccountComponent.class).amount = 1000;

        System.out.println();
        managerECS.printEntityComponents(1);
        managerECS.destroyEntity(1);
        System.out.println();

        for (int i = 0; i < managerECS.entityList.size(); i++) {
            System.out.println(managerECS.entityList.get(i));
        }


        // CREATE 4 Entities
        int four = managerECS.createEntity();
        int five = managerECS.createEntity();
        int six = managerECS.createEntity();
        managerECS.destroyEntity(3);
        int seven = managerECS.createEntity();


        managerECS.addComponent(four, new PersonDetailComponent("Sebastian", "Losto", "198xxxxx-xxxx"));
        managerECS.addComponent(four, new AccountComponent());
        managerECS.addComponent(four, new AddressComponent("eriksdaln 1"));

        managerECS.addComponent(five, new PersonDetailComponent("John", "Doe", "19xxxx945-xxxx"));
        managerECS.addComponent(five, new AddressComponent("Lost 1"));

        managerECS.addComponent(six, new PersonDetailComponent("Adolf", "Bodil", "xxxx0112-31xx"));
        managerECS.addComponent(six, new AccountComponent());
        managerECS.addComponent(six, new AddressComponent("Didriksväg 26"));

        managerECS.addComponent(seven, new PersonDetailComponent("Arnold", "Chan", "19xxxx01-3xxx"));
        managerECS.addComponent(seven, new AccountComponent());
        managerECS.addComponent(seven, new AddressComponent("Faltobo 10"));


    }
}
