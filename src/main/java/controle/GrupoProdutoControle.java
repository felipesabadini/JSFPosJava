package controle;

import entidade.GrupoProduto;
import facade.GrupoProdutoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean
@SessionScoped
public class GrupoProdutoControle {

    private GrupoProduto grupoProduto;
    
    @EJB
    private GrupoProdutoFacade grupoProdutoFacade;
    
    private TreeNode root;
    
    private TreeNode noGrupo;
    
    public List<GrupoProduto> listaTodos(){
        return grupoProdutoFacade.listaTodos();
    }

    public void salvar() {
        if(noGrupo != null) {
            grupoProduto.setGrupoProduto((GrupoProduto)noGrupo.getData());
        }
        grupoProdutoFacade.salvar(grupoProduto);
    }

    public void novo() {
        grupoProduto = new GrupoProduto();
        iniciaArvore();
    }

    public void excluir(GrupoProduto gp) {
        grupoProdutoFacade.remover(gp);
    }

    public void alterar(GrupoProduto gp) {
        this.grupoProduto = gp;
    }
    
    public void iniciaArvore() {
        root = new DefaultTreeNode(new GrupoProduto("Raiz"), null);
        montaArvore(root, grupoProdutoFacade.listaTodos());
    }
    
    public void montaArvore(TreeNode root, List<GrupoProduto> lista) {
        TreeNode no = null;
        for(GrupoProduto grupo: lista) {
            if(grupo.getGrupoProduto() == null) {
                TreeNode treeNode = new DefaultTreeNode(grupo, root);
                treeNode.setExpanded(true);
                no = treeNode;
            } else {
                TreeNode treeNode = new DefaultTreeNode(grupo, getPai(grupo, no));
                treeNode.setExpanded(true);
            }
        }
    }
    
    private TreeNode getPai(GrupoProduto grupo, TreeNode root) {
        GrupoProduto no = (GrupoProduto)root.getData();
        if(no.equals(grupo.getGrupoProduto())) {
            return root;
        } 
        for(TreeNode treeNode: root.getChildren()) {
            TreeNode pai = getPai(grupo, treeNode);
            GrupoProduto grupoPai = (GrupoProduto)pai.getData();
            if(grupoPai.equals(grupo.getGrupoProduto())) {
                pai.setExpanded(true);
                return pai;
            }
        }
        
        return root;
    }
    
    public GrupoProduto getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto(GrupoProduto grupoProduto) {
        this.grupoProduto = grupoProduto;
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
    
}
