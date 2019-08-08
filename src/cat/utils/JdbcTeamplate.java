package cat.utils;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.sql.*;
import java.util.*;

public class JdbcTeamplate {

	static String url = "jdbc:mysql://localhost:3306/cat?characterEncoding=UTF-8";
	static String user = "root";
	static String password = "";
	static String driverClass = "com.mysql.jdbc.Driver";

	static {
		// 1加载驱动
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param sql
	 *            传入insert update 或delete
	 * @param values
	 *            传入sql语句?号占位符的值列表 注：SQL中？号有几个，values的元素个数与?个数和致。 sql = update
	 *            emp set sal=? where empno=? values{5000,110}
	 * @return
	 * @throws Exception
	 */
	public static int insertOrUpdateOrDelete(String sql, Object... values)
			throws SQLException {
      /*--界面-->处理界面数据的java类-->调用数据的操作
      <--                <--*/
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);//获取Sql语句的执行对象
			if (values != null) {
				for (int i = 0; i < values.length; i++) {

					pstmt.setObject(i + 1, values[i]);// 给sql占位符赋值
				}
			}

			int res = pstmt.executeUpdate();// 执行语句，可判断是否出错。。
			return res;
		} finally {
			conn.close();
		}
	}

	public static Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println("连接ok");
		return conn;
	}

	// 分页查询 String sql = "select * from emp where deptno=?" 10
	// 2 5
	public static <E> PageUtil<E> queryDataByPage(String sql, Class<E> cls,
                                                  int currentPage, int pageSize, Object... values)
			throws SQLException, InstantiationException, IllegalAccessException {

		String pageSql = "select * from (select t.*,rownum rn from (" + sql
				+ ") t ) where rn>=? and rn<=?";

		Object[] paramValues = null;
		int begin = (currentPage - 1) * pageSize + 1;
		int end = currentPage * pageSize;
		if (values != null) {
			// 在原数组的长度的基础上+2
			paramValues = Arrays.copyOf(values, values.length + 2);
			paramValues[paramValues.length - 2] = begin;
			paramValues[paramValues.length - 1] = end;
		} else {
			paramValues = new Object[] { begin, end };

		}

		// 调用本类的查询方法
		List<E> rowData = queryData(pageSql, cls, paramValues);

		// 获得总记录数
		String tSql = "select count(*) coun from  (" + sql + " ) t";

		List<Map<String, Object>>  totalList =   queryData(tSql, values);
		Map<String, Object> map =  totalList.get(0);
		int totalRecord =   Integer.parseInt(String.valueOf(map.get("coun")));


		PageUtil<E> pageUtil = new PageUtil<E>(totalRecord, pageSize, currentPage, rowData);

		return pageUtil;
	}

	/**
	 *
	 * @param sql
	 * @param values
	 * @return 根据查询结果的元数据动态取列值
	 * @throws SQLException
	 */
	public static List<Map<String, Object>> queryData(String sql,
													  Object... values) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();// 存查询结果

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setObject(i + 1, values[i]);// 给sql占位符赋值
				}
			}
			ResultSet rs = pstmt.executeQuery();// 出错。。

			ResultSetMetaData rm = rs.getMetaData();

			int columnCount = rm.getColumnCount();// 列数

			while (rs.next()) {

				// Object[] rowData = new Object[columnCount];
				Map<String, Object> rowData = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = rm.getColumnName(i);// 获得列名
					String columnType = rm.getColumnTypeName(i);// 列的数据类型
					int scale = rm.getScale(i);// 小数位数。。
					Object val = null;
					if (columnType.startsWith("NUMBER") && scale == 0) {// 如果结果集的列的数据类型为Number且没有小数点时，可以调用getInt,getLong,getByte等整数相关的方法
						val = rs.getInt(columnName);
					} else if (columnType.startsWith("NUMBER") && scale > 0) {// 如果结果集的列的数据类型为Number且有小数点时，可以调用getInt,getLong,getByte等整数相关的方法
						val = rs.getDouble(columnName);
					} else if (columnType.startsWith("VARCHAR")
							|| columnType.startsWith("CHAR")) {// 字符串
						val = rs.getString(columnName);
					} else if (columnType.startsWith("DATE")
							|| columnType.startsWith("TIMESTAMP")) {
						val = rs.getDate(columnName);
					} else {
						val = rs.getObject(columnName);
					}
					// rowData[i-1] = val;//把列值存到一维数组中
					// map的key为列名称 ，value为列值
					rowData.put(columnName.toLowerCase(), val);
				}

				dataList.add(rowData);

			}

		} finally {
			conn.close();
		}
		return dataList;

	}

    // <E>泛型声明 List<E> List中的E取决于方法参数Class的泛型类型
    //根据查询语句及第二个参数的类型，把查询结果自动封装到第二个参数对应的实体类中（用了java的反射技术）
    public static <E> List<E> queryData(String sql, Class<E> cls,
                                        Object... values) throws SQLException, InstantiationException,
            IllegalAccessException {
        // JDBC//
        List<E> data = new ArrayList<E>();// 用于存储查询结果集合
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 2 建立连接 关闭连接
            conn = getConnection();
            // 3 执行SQL语句
            pstmt = conn.prepareStatement(sql);
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    pstmt.setObject(i + 1, values[i]);
                }
            }
            // 元数据ReultSetMeteData 获得表结构(列名，列数据类型，长度)
            // 执行查询 得到ResultSet
            rs = pstmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            // 获得查询结果列数
            int columnCount = metaData.getColumnCount();
            // 取出结果集中的数据
            while (rs.next()) {
                // rs.getString("empno");
                // 根据列数循环取出列名,再根据列名取出列值，再将列名做为Map的key,列值做为map的value
                Map<String, Object> rowData = new HashMap<String, Object>();// 此HashMap存储一条记录
                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i + 1)
                            .toLowerCase();
                    Object columnValue = rs.getObject(columnName);
                    // metaData.getco
                    rowData.put(columnName, columnValue);
                }
                // 此处可否使用反射执行将Map中的值存到JavaBean中
                E e = MapValueToVo.mapValueToVo(cls, rowData);// 反射将表中的一行数据存到对应的Vo中
                data.add(e);// 反射后的对象存到集合中
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return data;
    }



	//查询表长
	public static int getTableRowCount(String tableName){
		Connection conn =null;
		String sql = "select count(*) as rowcount from "+tableName;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int rowCount=0;
		try {
			conn = getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			rowCount=rs.getInt("rowcount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}
}