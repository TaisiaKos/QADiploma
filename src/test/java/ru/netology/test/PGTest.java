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

        var actual = SQLHelper.getPaymentGate();
        var expected = card.getStatus();
        assertEquals(expected, actual);
    }


}
