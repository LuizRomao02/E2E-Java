package br.com.luizromao.leilao.config.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LancesPage {
    private static final String URL_LANCES = "http://localhost:8080/leilao/2";
    private WebDriver browser;

    public LancesPage(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        this.browser = new ChromeDriver();
        browser.navigate().to(URL_LANCES);
    }

    public boolean isPaginaAtual() {
        return browser.getCurrentUrl().contains(URL_LANCES);
    }

    public boolean isTituloLeilaoVisivel() {
        return browser.getPageSource().contains("Dados do Leilão");
    }

    public void fechar() {
        this.browser.quit();
    }
}
