package br.com.luizromao.leilao.login;

import br.com.luizromao.leilao.config.pages.LancesPage;
import br.com.luizromao.leilao.config.pages.LoginPage;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
		paginaDeLogin.efetuarLogin("fulano", "pass");

		String nomeUsuarioLogado = paginaDeLogin.getNomeUsuarioLogado();
		assertEquals("fulano", nomeUsuarioLogado);
		assertFalse(paginaDeLogin.isPaginaAtual());
	}

	@Test
	public void naoDeveriaEfetuarLoginComDadosInvalidos() {
		paginaDeLogin.efetuarLogin("invalido", "1233");

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
