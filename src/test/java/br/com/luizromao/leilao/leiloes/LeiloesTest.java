package br.com.luizromao.leilao.leiloes;

import br.com.luizromao.leilao.config.pages.CadastroLeilaoPage;
import br.com.luizromao.leilao.config.pages.LeiloesPage;
import br.com.luizromao.leilao.config.pages.LoginPage;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LeiloesTest {

    private LeiloesPage paginaDeLeiloes;
    private CadastroLeilaoPage paginaDeCadastroDeLeilao;

    @BeforeEach
    public void beforeEach() {
        LoginPage paginaDeLogin = new LoginPage();
        this.paginaDeLeiloes = paginaDeLogin.efetuarLogin("fulano", "pass");
        this.paginaDeCadastroDeLeilao = paginaDeLeiloes.carregarFormulario();
    }

    @AfterEach
    public void afterEach() {
        this.paginaDeLeiloes.fechar();
        this.paginaDeCadastroDeLeilao.fechar();
    }

    @Test
    public void deveriaCadastrarLeilao() {
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nomeLeilao = "Leilao do dia " + hoje;
        String valorInicial = "500.00";

        this.paginaDeLeiloes = paginaDeCadastroDeLeilao.cadastrarLeilao(nomeLeilao, valorInicial, hoje);

        assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nomeLeilao, valorInicial, hoje));
    }

    @Test
    public void deveriaExecutarValidacaoAoCadastrarLeilaoComDadosInvalidos() {
        this.paginaDeLeiloes = paginaDeCadastroDeLeilao.cadastrarLeilao("", "", "");

        assertFalse(paginaDeCadastroDeLeilao.isPaginaAtual());
        assertTrue(paginaDeLeiloes.isPaginaAtual());
        assertTrue(paginaDeCadastroDeLeilao.isMensagensDeValidacaoVisiveis());
    }

}