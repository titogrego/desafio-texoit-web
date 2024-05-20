package info.steps;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.dto.PhotoDto;
import info.pages.AbstractPage;
import info.pages.HomePage;
import info.utils.BaseTest;
import info.utils.Constants;
import info.utils.Utilidades;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class HomeSteps extends AbstractPage {
    HomePage homePage = new HomePage();
    @Quando("que eu esteja no site do desafio")
    public void que_eu_esteja_no_site_do_desafio() {
       BaseTest.navigationObj.navigateTo(Constants.baseUrl);
    }
    @Dado("acesso o menu Guide")
    public void acesso_o_menu_Guide() {
        homePage.clicarMenuGuide();
    }

    @Quando("clico no link {string}")
    public void clico_no_link(String link) {
        homePage.clicarNoLink(link);
        Utilidades.waitSeconds(10);
    }

    @Então("deve validar os dados do objeto com id {string}")
    public void deve_validar_os_dados_do_objeto_com_id(String id) throws JsonProcessingException {
        String objeto = homePage.retornaDadosFotos();
        ObjectMapper mapper = new ObjectMapper();
        List<PhotoDto> listaFotos = Arrays.asList(mapper.readValue(objeto, PhotoDto[].class));
        PhotoDto foto = listaFotos.stream().filter(c -> c.id == Integer.parseInt(id)).findAny().get();
        assertThat(foto.id,is(Integer.parseInt(id)));
        assertThat(foto.albumId,is(1));
        assertThat(foto.albumId,is(instanceOf(Integer.class)));
        assertThat(foto.title,is("accusamus ea aliquid et amet sequi nemo"));
        assertThat(foto.title,is(instanceOf(String.class)));
        assertThat(foto.url,is("https://via.placeholder.com/600/56a8c2"));
        assertThat(foto.url,is(instanceOf(String.class)));
        assertThat(foto.thumbnailUrl,is("https://via.placeholder.com/150/56a8c2"));
        assertThat(foto.thumbnailUrl,is(instanceOf(String.class)));
    }

}
