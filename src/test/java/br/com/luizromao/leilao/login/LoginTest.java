package br.com.luizromao.leilao.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

	private static final String URL_LOGIN = "http://localhost:8080/login";

	private WebDriver browser;
	
	@BeforeAll
	public static void beforeAll() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
	}
	
	@BeforeEach
	public void beforeEach() {
		this.browser = new ChromeDriver();
		browser.navigate().to(URL_LOGIN);

	}
	
	@AfterEach
	public void afterEach() {
		this.browser.quit();
	}
	
	@Test
	public void deveriaEfetuarLoginComDadosValidos() {
		browser.findElement(By.id("username")).sendKeys("fulano");
		browser.findElement(By.id("password")).sendKeys("pass");
		browser.findElement(By.id("login-form")).submit();

		assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));
		assertEquals("fulano", browser.findElement(By.id("usuarioLogado")).getText());
	}
	
	@Test
	public void naoDeveriaLogarComDadosInvalidos() {
	    browser.findElement(By.id("username")).sendKeys("invalido");
	    browser.findElement(By.id("password")).sendKeys("123123");
	    browser.findElement(By.id("login-form")).submit();

	    assertTrue(browser.getCurrentUrl().equals(URL_LOGIN + "?error"));
	    assertTrue(browser.getPageSource().contains("Usuário e senha inválidos.")); //pega toda a pagina html
	    assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuarioLogado")).getText());
	}
	
	@Test
	public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
	    this.browser.navigate().to("http://localhost:8080/leiloes/2");
	    
	    assertTrue(browser.getCurrentUrl().equals(URL_LOGIN));
	    assertFalse(browser.getPageSource().contains("Dados do Leilão"));
	}
}
