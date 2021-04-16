package ecs_bank.ecs_core.components;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class AddressComponent implements IComponent {
    public String address;

    public AddressComponent(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "AddressComponent{" +
                "address='" + address + '\'' +
                '}';
    }
}
