import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDB {
	public static void main(String[] args) {
		// 実際の運用ではpassを設定する。
		String url = "jdbc:ucanaccess://TestDB.accdb";
		String user = "user";
		String pass = "password";

		try (
			Connection con = DriverManager.getConnection(url);
			Statement stmt = con.createStatement();
		){
//			printTableStructure(stmt);
//			insertSample(stmt);
			printAllData(stmt);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void printTableStructure(Statement stmt) throws SQLException {
		String sql = "SELECT * FROM user";
		try (
			ResultSet rs = stmt.executeQuery(sql);
		) {
			ResultSetMetaData rsmd = rs.getMetaData();

			int columnCount = rsmd.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				System.out.println(rsmd.getColumnName(i) + " : " + rsmd.getColumnTypeName(i));
			}
		}
	}

	private static void printAllData(Statement stmt) throws SQLException {
		String sql = "SELECT * FROM user";
		try (
			ResultSet rs = stmt.executeQuery(sql);
		) {
			while (rs.next()) {
				StringBuffer sb = new StringBuffer();
				sb.append("id = ");             sb.append(rs.getInt("id"));
				sb.append(", user_name = ");    sb.append(rs.getString("user_name"));
				sb.append(", password = ");     sb.append(rs.getString("password"));
				sb.append(", win = ");          sb.append(rs.getInt("win"));
				sb.append(", lose = ");         sb.append(rs.getInt("lose"));
				sb.append(", play = ");         sb.append(rs.getInt("play"));
				sb.append(", created_time = "); sb.append(rs.getDate("created_time")); sb.append(" "); sb.append(rs.getTime("created_time"));
				System.out.println(sb.toString());
			}
		}
	}

	private static void insertSample(Statement stmt) throws SQLException {
		// idは自動でインクリメントされるため指定する必要はないが、SQL文を短くするために0を指定
		String sql = "INSERT INTO user VALUES(0, 'name', 'pass', 0, 5, 5, '2017-12-01 00:00:00');";
		int num = stmt.executeUpdate(sql);
		System.out.println(num + "件のレコードを追加しました");
	}
}











