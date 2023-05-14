package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AutomationPracticeFormWithPageObject extends TestBase {

    @Test
    @DisplayName("Проверка заполнения и отправки формы")
    void testRegistrationForm() {
        String firstName = "John";
        String lastName = "Doe";
        String userEmail = "some-email@test.com";
        String userGender = "Male";
        String userNumber = "0123456789";
        String userSubject = "Computer Science";
        String currentAddress = "Address";
        String userHobbies = "Reading";
        String userState = "Uttar Pradesh";
        String userCity = "Agra";

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(userGender)
                .setNumber(userNumber)
                .setBirthDate("31", "August", "1995")
                .setSubject(userSubject)
                .setPicture("src/main/resources/picture.jpg")
                .setAddress(currentAddress)
                .setHobbies(userHobbies)
                .setStateAndCity(userState, userCity)
                .pressSubmit();

        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", firstName + " " + lastName)
                .verifyResult("Student Email", userEmail)
                .verifyResult("Gender", userGender)
                .verifyResult("Mobile", userNumber)
                .verifyResult("Date of Birth", "31 August,1995");
    }
}
