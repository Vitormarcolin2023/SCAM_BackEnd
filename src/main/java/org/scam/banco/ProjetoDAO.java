package org.scam.banco;

import org.scam.classes.Projeto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {
    private String url = "jdbc:postgresql://localhost:5432/db_scam";
    private String usuario = "postgres";
    private String senha = "root";

    public void inserirProjeto(Projeto p) {
        String sql = "INSERT INTO projeto (id, nomeDoProjeto, descricao, areaDeAtuacao, dataInicioProjeto, dataFinalProjeto, tamanhoDoGrupo, curso, periodo, fk_aluno_id, fk_mentor_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, p.getId());
            stmt.setString(2, p.getNomeDoProjeto());
            stmt.setString(3, p.getDescricao());
            stmt.setString(4, p.getAreaDeAtuacao());
            stmt.setDate(5, new java.sql.Date(p.getDataInicioProjeto().getTime()));
            stmt.setDate(6, new java.sql.Date(p.getDataFinalProjeto().getTime()));
            stmt.setInt(7, p.getTamanhoDoGrupo());
            stmt.setString(8, p.getCurso());
            stmt.setString(9, p.getPeriodo());
            stmt.setInt(10, p.getFk_aluno_id());
            stmt.setInt(11, p.getFk_mentor_id());

            stmt.executeUpdate();
            System.out.println("✅ Projeto inserido com sucesso!");

        } catch (SQLException e) {
            System.out.println("❌ Erro ao inserir projeto: " + e.getMessage());
        }
    }

    public List<Projeto> listarProjetos() {
        List<Projeto> lista = new ArrayList<>();
        String sql = "SELECT * FROM projeto";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Projeto p = new Projeto();
                p.setId(rs.getInt("id"));
                p.setNomeDoProjeto(rs.getString("nomeDoProjeto"));
                p.setDescricao(rs.getString("descricao"));
                p.setAreaDeAtuacao(rs.getString("areaDeAtuacao"));
                p.setDataInicioProjeto(rs.getDate("dataInicioProjeto"));
                p.setDataFinalProjeto(rs.getDate("dataFinalProjeto"));
                p.setTamanhoDoGrupo(rs.getInt("tamanhoDoGrupo"));
                p.setCurso(rs.getString("curso"));
                p.setPeriodo(rs.getString("periodo"));
                p.setFk_aluno_id(rs.getInt("fk_aluno_id"));
                p.setFk_mentor_id(rs.getInt("fk_mentor_id"));

                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("❌ Erro ao listar projetos: " + e.getMessage());
        }

        return lista;
    }
}