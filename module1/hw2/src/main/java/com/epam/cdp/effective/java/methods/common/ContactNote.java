package com.epam.cdp.effective.java.methods.common;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ContactNote implements Comparable<ContactNote> {

    private String firstName;
    private String lastName;
    private String phoneNumber;

    /**
     * Constructor with parameters
     *
     * @param firstName   the first name
     * @param lastName    the last name.
     * @param phoneNumber the phone number
     */
    public ContactNote(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("Incorrect phone number");
        }
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int compareTo(ContactNote note) {
        if (note == this) {
            return 0;
        }

        if (note == null) {
            return -1;
        }

        if (this.firstName != null && note.getFirstName() != null) {
            return firstName.compareTo(note.getFirstName());
        }

        if (this.lastName != null && note.getLastName() != null) {
            return lastName.compareTo(note.getLastName());
        }

        return 0;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("phoneNumber", phoneNumber)
                .toString();
    }
}