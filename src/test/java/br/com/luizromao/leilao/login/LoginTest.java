package br.com.luizromao.leilao.login;

import br.com.luizromao.leilao.config.pages.LancesPage;
import br.com.luizromao.leilao.config.pages.LoginPage;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.Assert.*;

public class LoginTest {


	private LoginPage paginaDeLogin;

	@BeforeEach
	public void beforeEach() {
		this.paginaDeLogin = new LoginPage();
	}

	@AfterEach
	public void afterEach() {
		this.paginaDeLogin.fechar();
	}

	@Test
	public void deveriaEfetuarLoginComDadosValidos() {
		paginaDeLogin.preencherFormularioDeLogin("fulano", "pass");
		paginaDeLogin.efetuarLogin();

		String nomeUsuarioLogado = paginaDeLogin.getNomeUsuarioLogado();
		assertEquals("fulano", nomeUsuarioLogado);
		assertFalse(paginaDeLogin.isPaginaAtual());
	}

	@Test
	public void naoDeveriaEfetuarLoginComDadosInvalidos() {
		paginaDeLogin.preencherFormularioDeLogin("invalido", "1233");
		paginaDeLogin.efetuarLogin();

		assertNull(paginaDeLogin.getNomeUsuarioLogado());
		assertTrue(paginaDeLogin.isPaginaAtual());
		assertTrue(paginaDeLogin.isMensagemDeLoginInvalidoVisivel());
	}

	@Test
	public void naoDeveriaAcessarUrlRestritaSemEstarLogado() {
		LancesPage paginaDeLances = new LancesPage();

		assertFalse(paginaDeLances.isPaginaAtual());
		assertFalse(paginaDeLances.isTituloLeilaoVisivel());

		paginaDeLances.fechar();
	}
}
