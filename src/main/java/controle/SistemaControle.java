package controle;

import entidade.Temas;
import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class SistemaControle implements Serializable {

    private Temas temas = Temas.bootstrap;

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
