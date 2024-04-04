package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.PGate;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.SQLHelper.cleanDatabase;

public class PGTest {
    DashboardPage dashboardPage;
    PGate pGate;
    DataHelper dataHelper;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        cleanDatabase();
        dashboardPage = open("http://localhost:8080/", DashboardPage.class);
    }

    @Test
    @DisplayName("Successful pay by card")
    void shouldAllSuccessfulPay() {
        pGate = dashboardPage.selectCard();
        var card = DataHelper.getApprovedCard();
        var month = DataHelper.getMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        pGate.fromForm(card, month, year, name, cvc);
        pGate.clickButtonContinue();
        pGate.setApprovedBankMessage();

        var actual = SQLHelper.getPaymentGate().getStatus();
        var expected = DataHelper.getStatusTransaction().getApproved();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("No successful pay by card - DeclinedCardNumber")
    void shouldNoSuccessfulPayDeclinedCardNumber() {
        pGate = dashboardPage.selectCard();
        var card = DataHelper.getDeclinedCard();
        var month = DataHelper.getMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        pGate.fromForm(card, month, year, name, cvc);
        pGate.clickButtonContinue();
        pGate.setDeclinedBankMessage();

        var actual = SQLHelper.getPaymentGate().getStatus();
        var expected = DataHelper.getStatusTransaction().getDeclined();
        assertEquals(expected, actual);
    }



    @Test
    @DisplayName("No successful pay by card - TestOneMonth")
    void shouldNoSuccessfulPayTestOneMonth() {
        pGate = dashboardPage.selectCard();
        var card = DataHelper.getApprovedCard();
        var testMonth = DataHelper.getTestMonth().getOneMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        pGate.fromForm(card, testMonth, year, name, cvc);
        pGate.clickButtonContinue();
        pGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful pay by card - TestMarksMonth")
    void shouldNoSuccessfulPayTestMarksMonth() {
        pGate = dashboardPage.selectCard();
        var card = DataHelper.getApprovedCard();
        var testMonth = DataHelper.getTestMonth().getMarksMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        pGate.fromForm(card, testMonth, year, name, cvc);
        pGate.clickButtonContinue();
        pGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful pay by card - TestMoreMonth")
    void shouldNoSuccessfulPayTestMoreMonth() {
        pGate = dashboardPage.selectCard();
        var card = DataHelper.getApprovedCard();
        var testMonth = DataHelper.getTestMonth().getMoreMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        pGate.fromForm(card, testMonth, year, name, cvc);
        pGate.clickButtonContinue();
        pGate.setFormatMistake();
    }
    @Test
    @DisplayName("No successful pay by card - TestLessMonth")
    void shouldNoSuccessfulPayTestLessMonth() {
        pGate = dashboardPage.selectCard();
        var card = DataHelper.getApprovedCard();
        var testMonth = DataHelper.getTestMonth().getLessMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        pGate.fromForm(card, testMonth, year, name, cvc);
        pGate.clickButtonContinue();
        pGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful pay by card - TestSymbolMonth")
    void shouldNoSuccessfulPayTestSymbolMonth() {
        pGate = dashboardPage.selectCard();
        var card = DataHelper.getApprovedCard();
        var testMonth = DataHelper.getTestMonth().getSymbolMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        pGate.fromForm(card, testMonth, year, name, cvc);
        pGate.clickButtonContinue();
        pGate.setFormatMistake();
    }

    @Test
    @DisplayName("No successful pay by card - TestOneYear")
    void shouldNoSuccessfulPayTestOneYear() {
        pGate = dashboardPage.selectCard();
        var card = DataHelper.getApprovedCard();
        var month = DataHelper.getMonth();
        var testYear = DataHelper.getTestYear().getOneYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        pGate.fromForm(card, month, testYear, name, cvc);
        pGate.clickButtonContinue();
        pGate.setFormatMistake();
    }
    @Test
    @DisplayName("No successful pay by card - TestMarksYear")
    void shouldNoSuccessfulPayTestMarksYear() {
        pGate = dashboardPage.selectCard();
        var card = DataHelper.getApprovedCard();
        var month = DataHelper.getMonth();
        var testYear = DataHelper.getTestYear().getMarksYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        pGate.fromForm(card, month, testYear, name, cvc);
        pGate.clickButtonContinue();
        pGate.setFormatMistake();
    }
    @Test
    @DisplayName("No successful pay by card - TestLastYear")
    void shouldNoSuccessfulPayTestLastYear() {
        pGate = dashboardPage.selectCard();
        var card = DataHelper.getApprovedCard();
        var month = DataHelper.getMonth();
        var testYear = DataHelper.getTestYear().getLastYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        pGate.fromForm(card, month, testYear, name, cvc);
        pGate.clickButtonContinue();
        pGate.setFormatMistake();
    }
    @Test
    @DisplayName("No successful pay by card - TestSymbolYear")
    void shouldNoSuccessfulPayTestSymbolYear() {
        pGate = dashboardPage.selectCard();
        var card = DataHelper.getApprovedCard();
        var month = DataHelper.getMonth();
        var testYear = DataHelper.getTestYear().getSymbolYear();
        var name = DataHelper.getName();
        var cvc = DataHelper.getCVCCode();
        pGate.fromForm(card, month, testYear, name, cvc);
        pGate.clickButtonContinue();
        pGate.setFormatMistake();
    }
}
