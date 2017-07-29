package controle;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import entidade.GrupoProduto;
import facade.GrupoProdutoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean
@SessionScoped
@URLMappings(mappings = {
    @URLMapping(id = "listaGrupoProduto",
            pattern = "/grupoproduto/listar",
            viewId = "/faces/grupoproduto/grupoprodutolista.xhtml")
    ,
    @URLMapping(id = "novoGrupoProduto",
            pattern = "/grupoproduto/novo",
            viewId = "/faces/grupoproduto/grupoprodutoedita.xhtml")
    ,
    @URLMapping(id = "editaGrupoProduto",
            pattern = "/grupoproduto/editar/#{estadoControle.id}",
            viewId = "/faces/grupoproduto/grupoprodutoedita.xhtml")
})
public class GrupoProdutoControle implements Serializable {
    
    @EJB
    private GrupoProdutoFacade grupoProdutoFacade;
    
    private String id;

    private GrupoProduto grupoProduto;
    
    private TreeNode root;
    
    private TreeNode noGrupo;
    
    public void iniciaArvore(){
        root = new DefaultTreeNode(new GrupoProduto("Raiz"), null);
        montaArvore(root, grupoProdutoFacade.listaTodos());
    }
    
    public void montaArvore(TreeNode root, List<GrupoProduto> lista){
        TreeNode no = null;
        for(GrupoProduto grupo : lista){
            if(grupo.getGrupoProduto() == null){
                TreeNode treeNode = new DefaultTreeNode(grupo, root);
                treeNode.setExpanded(true);
                no = treeNode;
            }else{
                TreeNode treeNode = new DefaultTreeNode(grupo,getPai(grupo, no));
                treeNode.setExpanded(true);
            }            
        }
    }
    
    private TreeNode getPai(GrupoProduto grupo, TreeNode root){
        GrupoProduto no = (GrupoProduto) root.getData();
        if(no.equals(grupo.getGrupoProduto())){
            return root;
        }
        for(TreeNode treeNode : root.getChildren()){
            TreeNode pai = getPai(grupo, treeNode);
            GrupoProduto grupoPai = (GrupoProduto)pai.getData();
            if(grupoPai.equals(grupo.getGrupoProduto())){
                pai.setExpanded(true);
                return pai;
            }
        }
        return root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getNoGrupo() {
        return noGrupo;
    }

    public void setNoGrupo(TreeNode noGrupo) {
        this.noGrupo = noGrupo;
    }
    
    
    public List<GrupoProduto> listaTodos(){
        return grupoProdutoFacade.listaTodos();
    }

    public GrupoProduto getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto(GrupoProduto grupoProduto) {
        this.grupoProduto = grupoProduto;
    }

    public void salvar() {
        if(noGrupo != null){
            grupoProduto.setGrupoProduto((GrupoProduto)noGrupo.getData());
        }
        grupoProdutoFacade.salvar(grupoProduto);
    }

    @URLAction(mappingId = "novoGrupoProduto", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    public void novo() {
        grupoProduto = new GrupoProduto();
        iniciaArvore();
    }

    public void excluir(GrupoProduto e) {
        grupoProdutoFacade.remover(e);
    }

    public void alterar(GrupoProduto e) {
        this.grupoProduto = e;
    }
    
    @URLAction(mappingId = "editaGrupoProduto", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    public void alterarPelaURL(){
        this.grupoProduto = grupoProdutoFacade.buscar(Long.parseLong(id));
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
