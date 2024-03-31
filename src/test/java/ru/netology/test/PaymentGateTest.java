package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.CreditGatePage;
import ru.netology.page.PaymentGatePage;
import ru.netology.page.DashboardPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;




import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.netology.data.SQLHelper.cleanDatabase;
import static ru.netology.data.SQLHelper.cleanOrder;

public class PaymentGateTest {
    PaymentGatePage paymentGatePage;
    DashboardPage dashboardPage;

    @AfterEach
    void tearDown() { cleanOrder();}
    @AfterAll
    static void tearDownAll() {
        cleanDatabase();// после чистки перезапускаем джарник
        SelenideLogger.removeListener("allure");
    }
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @BeforeEach
    void setUp() { paymentGatePage = open("http://localhost:8080",PaymentGatePage.class);}

    @Test
    @DisplayName("Should successfully pay")
    void shouldSuccessfulPayment() {
        paymentGatePage = dashboardPage.selectCard();
        var cardNumber = DataHelper.getApprovedCard();
        var month = DataHelper.getMonth();
        var year = DataHelper.getYear();
        var name = DataHelper.getName();
        var cardCVC = DataHelper.getCVCCode();

        paymentGatePage.fillForm();
        paymentGatePage.clickButton();
        paymentGatePage.seeSuccessful();
        var actual = SQLHelper.getPaymentGate();
        var expected = DataHelper.OperationStatus.class;
        assertEquals(expected, actual);
    }


}

