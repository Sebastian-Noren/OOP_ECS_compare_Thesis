package ecs_bank.ecs_core.components;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class AddressComponent implements IComponent {
    public String street;
    public int streetNumber;
    public int zipCode;
    public String city;
    public String country;

    public AddressComponent(String street, int streetNumber, int zipCode, String city, String country) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }
}
