package controle;

import entidade.Temas;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class SistemaControle {

    private Temas temas = Temas.afterdark;

    public Temas getTemas() {
        return temas;
    }

    public void setTemas(Temas temas) {
        this.temas = temas;
    }
    
    public Temas[] getListaTemas(){
        return Temas.values();
    }

}
