package birdpoint.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

@SuppressWarnings("unchecked")
public abstract class GenericDAO<T> {

    /*
     * Essa classe possui operações comuns a todas as classes persistentes
     * para utilizar-la deverá criar uma classe DAO específica para a classe
     * persistente e extends GenericDAO<T> onde T é a classe Persistente 
     */
    public Session sessao;
    public Transaction transacao;
    private Class classe;

    public GenericDAO(Class classe) {
        this.classe = classe;
    }

    public boolean adicionar(T entity) {
        try {
            this.setSessao(HibernateUtil.getSessionFactory().openSession());
            this.setTransacao(getSessao().beginTransaction());
            this.getSessao().save(entity);
            this.getTransacao().commit();

        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível executar essa operação" + entity.getClass()
                    + ". Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } 
        return true;
    }

    public boolean adicionarPonto(T entity) {
        try {
            this.setSessao(HibernateUtil.getSessionFactory().openSession());
            this.setTransacao(getSessao().beginTransaction());
            this.getSessao().save(entity);
            this.getTransacao().commit();

        } catch (HibernateException e) {
            System.out.println("Erro ao se conectar com o banco de dados! Reconectando...");
            return false;
        } 
        return true;
    }

    public boolean atualizar(T entity) {
        try {
            this.setSessao(HibernateUtil.getSessionFactory().openSession());
            this.setTransacao(getSessao().beginTransaction());
            this.getSessao().merge(entity);
            this.getTransacao().commit();

        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível executar essa operação" + entity.getClass()
                    + ". Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean remover(T entity) {
        try {
            this.setSessao(HibernateUtil.getSessionFactory().openSession());
            this.setTransacao(getSessao().beginTransaction());
            this.getSessao().delete(entity);
            this.getTransacao().commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível executar essa operação" + entity.getClass()
                    + ". Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public List<T> listar() {
        List<T> lista = null;
        try {
            this.setSessao(HibernateUtil.getSessionFactory().openSession());
            setTransacao(getSessao().beginTransaction());
            lista = this.getSessao().createCriteria(classe).list();
        } catch (Throwable e) {
            if (getTransacao().isActive()) {
                getTransacao().rollback();
            }
            JOptionPane.showMessageDialog(null, "Não foi possível executar essa operação"
                    + ". Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    /*
     * ao passar uma chave primária
     * ele retorna um objeto referente a chave primária
     */
    public T carregaChavePrimaria(int chavePrimaria) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Object o = sessao.load(classe, chavePrimaria);
            return (T) o;
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível executar essa operação"
                    + ". Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public List<T> checkExists(String campo, String valor) {
        List<T> lista = null;
        try {
            this.setSessao(HibernateUtil.getSessionFactory().openSession());
            setTransacao(getSessao().beginTransaction());
            lista = this.getSessao().createCriteria(classe).add(Restrictions.ilike(campo, valor, MatchMode.ANYWHERE)).list();
        } catch (Throwable e) {
            if (getTransacao().isActive()) {
                getTransacao().rollback();
            }
            JOptionPane.showMessageDialog(null, "Não foi possível executar essa operação"
                    + ". Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return lista;

    }

    public List<T> checkExistsPonto(String campo, String valor) {
        List<T> lista = null;
        try {
            this.setSessao(HibernateUtil.getSessionFactory().openSession());
            setTransacao(getSessao().beginTransaction());
            lista = this.getSessao().createCriteria(classe).add(Restrictions.ilike(campo, valor, MatchMode.ANYWHERE)).list();
        } catch (Throwable e) {

        }
        return lista;
    }

    public List<T> checkExistsPontoFuncionario(String campo1, String valor1, String campo2, int valor2) {
        List<T> lista = null;
        try {
            this.setSessao(HibernateUtil.getSessionFactory().openSession());
            setTransacao(getSessao().beginTransaction());
            lista = this.getSessao().createCriteria(classe).add(Restrictions.eq(campo1, valor1))
                    .add(Restrictions.eq(campo2, valor2)).list();
        } catch (Throwable e) {

        }
        return lista;
    }
    
    public List<T> checkExistsPontoFuncionarioEntradaAutomatica(String campo1, String valor1, String campo2, int valor2, String campo3, Time valor3) {
        List<T> lista = null;
        try {
            this.setSessao(HibernateUtil.getSessionFactory().openSession());
            setTransacao(getSessao().beginTransaction());
            lista = this.getSessao().createCriteria(classe).add(Restrictions.eq(campo1, valor1))
                    .add(Restrictions.eq(campo2, valor2))
                    .add(Restrictions.eq(campo3, valor3)).list();
        } catch (Throwable e) {

        }
        return lista;
    }

    public List<T> checkExistseq(String campo, Object valor) {
        List<T> lista = null;
        try {
            this.setSessao(HibernateUtil.getSessionFactory().openSession());
            setTransacao(getSessao().beginTransaction());
            lista = this.getSessao().createCriteria(classe).add(Restrictions.eq(campo, valor)).list();
        } catch (Throwable e) {
            if (getTransacao().isActive()) {
                getTransacao().rollback();
            }
            JOptionPane.showMessageDialog(null, "Não foi possível executar essa operação"
                    + ". Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return lista;

    }

    public T consultarObjetoId(String campo, Object valor) {
        T objeto = null;
        try {
            this.setSessao(HibernateUtil.getSessionFactory().openSession());
            setTransacao(getSessao().beginTransaction());
            objeto = (T) this.getSessao().createCriteria(classe).add(Restrictions.eq(campo, valor)).uniqueResult();
        } catch (Throwable e) {
            if (getTransacao().isActive()) {
                getTransacao().rollback();
            }
            JOptionPane.showMessageDialog(null, "Não foi possível executar essa operação"
                    + ". Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return objeto;

    }

    public boolean consultarValorRepetido(String campo, Object valor) {
        T objeto = null;
        try {
            this.setSessao(HibernateUtil.getSessionFactory().openSession());
            setTransacao(getSessao().beginTransaction());
            objeto = (T) this.getSessao().createCriteria(classe).add(Restrictions.eq(campo, valor)).uniqueResult();
        } catch (Throwable e) {
            if (getTransacao().isActive()) {
                getTransacao().rollback();
            }
            JOptionPane.showMessageDialog(null, "Não foi possível executar essa operação"
                    + ". Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        if (objeto == null) {
            return false;
        } else {
            return true;
        }

    }

    public T carregarGradeCurricular(String campo, Object valor, String campo2, Object valor2) {
        T objeto = null;
        try {
            this.setSessao(HibernateUtil.getSessionFactory().openSession());
            setTransacao(getSessao().beginTransaction());
            objeto = (T) this.getSessao().createCriteria(classe).add(Restrictions.eq(campo, valor)).add(Restrictions.eq(campo2, valor2)).uniqueResult();
        } catch (Throwable e) {
            if (getTransacao().isActive()) {
                getTransacao().rollback();
            }
            JOptionPane.showMessageDialog(null, "Não foi possível executar essa operação"
                    + ". Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return objeto;

    }

    public <T> List<T> converterJsonEmLista(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(json).getAsJsonArray();
        List<T> list = new ArrayList<>();
        for (final JsonElement jsonElement : array) {
            T entity = gson.fromJson(jsonElement, clazz);
            list.add(entity);
        }
        return list;
    }

    /**
     * @return the sessao
     */
    public Session getSessao() {
        return sessao;
    }

    /**
     * @param sessao the sessao to set
     */
    public void setSessao(Session sessao) {
        this.sessao = sessao;
    }

    /**
     * @return the transacao
     */
    public Transaction getTransacao() {
        return transacao;
    }

    /**
     * @param transacao the transacao to set
     */
    public void setTransacao(Transaction transacao) {
        this.transacao = transacao;
    }

}
