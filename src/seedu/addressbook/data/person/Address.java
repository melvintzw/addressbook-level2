package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

import java.util.ArrayList;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can be in any format";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";

    public final String value;

    private final Block block = new Block();
    private final Street street = new Street();
    private final Unit unit = new Unit();
    private final PostalCode postalCode = new PostalCode();

    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        this.isPrivate = isPrivate;
        if (!isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }

        //splits address into 4 different components
        String[] splitAddress = splitAddress(trimmedAddress);

        block.setData(splitAddress[0]);
        street.setData(splitAddress[1]);
        unit.setData(splitAddress[2]);
        postalCode.setData(splitAddress[3]);

        String temp = block.getData();

        if (street.getData() != null) {
            temp = temp.concat(" " + street.getData());
        }
        if (unit.getData() != null) {
            temp = temp.concat(" " + unit.getData());
        }
        if (postalCode.getData() != null) {
            temp = temp.concat(" " + postalCode.getData());
        }

        value = temp;
    }

    /**
     * splits a given address into four Strings representing block, street, unit, postal code
     * first 3 fields contains the comma delimiter
     * @param  address input by user
     * @return split address
     */
    private String[] splitAddress(String address) {
        String[] splitAddress = new String[4];

        String tempStr = address;
        int i = 0;
        int commaIndex = tempStr.indexOf(',');
        while (commaIndex >= 0 && i < 4) {
            splitAddress[i] = tempStr.substring(0, commaIndex + 1);
            tempStr = tempStr.substring(commaIndex + 1).trim();
            i++;
            commaIndex = tempStr.indexOf(',');
        }
        if (i < 4) {
            //captures last address component that does not have a ",".
            splitAddress[i] = tempStr.trim();
        }

        return splitAddress;
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    class Block {
        private String _data;

        public String getData(){
            return _data;
        }

        public void setData(String data) {
            _data = data;
        }

    }

    class Street {
        private String _data;

        public String getData(){
            return _data;
        }

        public void setData(String data) {
            _data = data;
        }

    }

    class Unit {
        private String _data;

        public String getData(){
            return _data;
        }

        public void setData(String data) {
            _data = data;
        }

    }

    class PostalCode {
        private String _data;

        public String getData(){
            return _data;
        }

        public void setData(String data) {
            _data = data;
        }

    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
