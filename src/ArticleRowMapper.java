import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleRowMapper implements RowMapper<Article>{

	@Override
	public Article getRow(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		String title = rs.getString("title");
		int id = rs.getInt("int");
		String body = rs.getString("body");
		String nickname = rs.getString("nickname");
		int hit = rs.getInt("int");
		
		Article article = new Article();
		article.setTitle(title);
		article.setBody(body);
		article.setId(id);
		article.setNickname(nickname);
		article.setHit(hit);
		
		return article;
	}

}
