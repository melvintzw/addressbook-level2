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

    private final String _block;
    private final String _street;
    private final String _unit;
    private final String _postalCode;
    public final String value; //not a field in the LO-ImplementClass, but without, needs lots of modifications

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

        String[] splitAddress = splitAddress(trimmedAddress);

        _block = splitAddress[0];
        _street = splitAddress[1];
        _unit = splitAddress[2];
        _postalCode = splitAddress[3];
        value = _block + " " + _street + " " + _unit + " " + _postalCode;
    }

    /**
     * splits a given address into four Strings representing block, street, unit, postal code
     * @param  address input by user
     * @return split address
     */
    private String[] splitAddress(String address) {
        String[] splitAddress = new String[4];

        //find indices of the 3 ','.
        String tempStr = address;
        for (int i = 0; i < 3; i++) {
            int commaIndex = tempStr.indexOf(',');
            splitAddress[i] = tempStr.substring(0, commaIndex + 1);
            tempStr = tempStr.substring(commaIndex + 2);
        }
        splitAddress[3] = tempStr;

        return splitAddress;
    }


    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return _block + " " + _street + " " + _unit + " " +_postalCode + ".";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this._postalCode.equals(((Address) other)._postalCode)); // state check
    }

    @Override
    public int hashCode() {
        return _postalCode.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
