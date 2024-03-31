package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static Button getButton() {
        return new Button("Купить", "Купить в кредит", "Продолжить");
    }

    public static Form getForm() {

        return new Form("Номер карты", "Месяц", "Год", "Владелец", "CVC/CVV");
    }

    public static CardNumber getApprovedCard() {
        return new CardNumber("4444 4444 4444 4441", "APPROVED");
    }

    public static CardNumber getDeclinedCard() {
        return new CardNumber("4444 4444 4444 4442", "DECLINED");
    }

    public static TestCardNumber getTestCardNumber() {
        return new TestCardNumber (
                faker.numerify("#### #### #### ###"),
                "!!!! !!!! !!!! !!!!",
                "cccc cccc cccc cccc")
                ;
    }

    public static String getMonth() {
        return LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static TestMonth getTestMonth() {
        return new TestMonth("1", "!!", "13", "00", "cc");
    }

    public static String getYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static TestYear getTestYear() {
        return new TestYear("1","!!", "23", "cc");
    }

    public static Name getName() {
        return new Name(
                (faker.name().firstName().toUpperCase(Locale.ROOT) + " " + faker.name().lastName().toUpperCase(Locale.ROOT)
                ));
    }

    public static TestName getTestName() {
        return new TestName(
                (faker.name().firstName().toLowerCase(Locale.ROOT) + " " + faker.name().lastName().toLowerCase(Locale.ROOT)),
                "иванов иван", "ИВАНОВ ИВАН", "1111 111111", "cccc ccccccc");
    }

    public static CVCCode getCVCCode() {
        return new CVCCode(faker.numerify("###"));
    }

    public static TestCVCCode getTestCVCCode() {
        return new TestCVCCode("1", "11", "000", "!!!", "ccc");
    }

    public static StatusTransaction getStatusTransaction() {

        return new StatusTransaction("APPROVED", "DECLINED");
    }

    public static Toll getToll() {

        return new Toll("payment_entity", "credit_request_entity");
    }

    public static TollId getTollId() {

        return new TollId("payment_id", "credit_id");
    }

    public static BankMessage getBankMessage() {
        return new BankMessage("Операция одобрена Банком.", "Ошибка! Банк отказал в проведении операции");
    }

    public static MistakesMessage getMistakesMessage() {
        return new MistakesMessage("Поле обязательно для заполнения", "Неверный формат", "Истёк срок действия карты");
    }

    @Value
    public static class Button {
        String buy;
        String buyForCredit;
        String next;
    }

    @Value
    public static class Form {
        String cardNumber;
        String month;
        String year;
        String name;
        String codeCVC;
    }

    @Value
    public static class CardNumber {
        String cardNumber;
        String status;
    }

    @Value
    public static class TestCardNumber {
        String notEnoughCardNumber;
        String marksCardNumber;
        String textCardNumber;
    }
    @Value
    public static class TestMonth {
        String oneMonth;
        String marksMonth;
        String moreMonth;
        String lessMonth;
        String symbolMonth;
    }

    @Value
    public static class TestYear {
        String oneYear;
        String marksYear;
        String lastYear;
        String symbolYear;
    }

    @Value
    public static class Name {
        String firstNameAndLastName;
    }
    @Value
    public static class TestName {
        String lettersName;
        String smallName;
        String bigName;
        String numberName;
        String symbolsName;
    }

    @Value
    public static class CVCCode {
        String code;
    }

    @Value
    public static class TestCVCCode {
        String oneCode;
        String twoCode;
        String zeroCode;
        String marksCode;
        String symbolCode;
    }

    @Value
    public static class StatusTransaction {
        String approved;
        String declined;
    }

    @Value
    public static class Toll {
        String payment;
        String credit;
    }

    @Value
    public static class TollId {
        String payment_id;
        String credit_id;
    }

    @Value
    public static class BankMessage {
        String approvedBankMessage;
        String declinedBankMessage;
    }

    @Value
    public static class MistakesMessage {
        String fieldMistake;
        String formatMistake;
        String expiredCardMistake;
    }

    @Value
    public static class OperationStatus {
        String status;
    }

    @Value
    public static class TransactionId {
        String id;
    }


}
