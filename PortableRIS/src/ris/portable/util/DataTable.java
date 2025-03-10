package ris.portable.util;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DataTable implements Serializable{

	private static Log logger = LogFactory.getLog(DataTable.class);

	private static final long serialVersionUID = 1L;

	private int columnCount = 0;
	private int rowCount = 0;

	private String[] columnNames = new String[0];

	private ArrayList<DataRow> rows = new ArrayList<DataRow>();

	public DataTable(){
	}

	public DataTable(ResultSet rset)throws Exception{
		this.setTable(rset);
	}

	public void setTable(ResultSet rset) throws Exception{

		ResultSetMetaData meta = rset.getMetaData();

		columnCount = meta.getColumnCount();

		columnNames = new String[columnCount];
		for(int i= 0; i < columnCount; i++){
			columnNames[i] = meta.getColumnName(i+1).toUpperCase();	//大文字

			//logger.debug(columnNames[i] + ":" + meta.getColumnTypeName(i + 1) + ":type=" + meta.getColumnType(i + 1));
		}

		ArrayList<DataRow> list = new ArrayList<DataRow>();

		while (rset.next()) {
			DataRow row = new DataRow();

			for(int i = 0; i < columnCount; i++ ){

				if(meta.getColumnType(i + 1) == java.sql.Types.TIMESTAMP) {
					row.put(columnNames[i], rset.getTimestamp(i + 1));
				}
				else if(meta.getColumnType(i + 1) == java.sql.Types.NUMERIC) {
					row.put(columnNames[i], Util.toNullInteger(rset.getString(i + 1)));
				}
				else{
					row.put(columnNames[i], Util.toNullString(rset.getString(i + 1)));
				}
			}
			list.add(row);

		}

		rowCount = list.size();
		rows = list;
	}


	public ArrayList<DataRow> getRows() {
		return rows;
	}

	private void setRows(ArrayList<DataRow> rows) {
		this.rows = rows;
	}


	public String[] getColmunNames() {
		return columnNames;
	}
	private void setColmunNames(String[] colmunNames) {
		this.columnNames = colmunNames;
	}


	public int getRowCount() {
		//return rowCount;
		return rows.size();
	}
	private void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}


	public int getColumnCount() {
		return columnCount;
	}
	private void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}


	public ArrayList<DataRow> selectRow(String key, String value){
		return selectRow(new String[]{key}, new String[]{value});
	}

	public ArrayList<DataRow> selectRow(String[] keys, String[] values){
		ArrayList<DataRow> ret = new ArrayList<DataRow>();

		for(int i = 0; i < this.rowCount; i++){
			boolean isHit = true;
			for(int j = 0; j < keys.length; j++){
				if(!this.rows.get(i).get(keys[j]).equals(values[j])){
					isHit = false;
					break;
				}
			}
			if(isHit){
				ret.add(this.rows.get(i));
			}
		}

		return ret;
	}


	public DataRow getNewRow(){

		DataRow row = new DataRow();

		if(this.columnNames == null){
			return null;
		}

		if(this.columnNames.length == 0){
			return null;
		}

		for(int i = 0; i < this.columnNames.length; i++){
			row.put(columnNames[i], "");
		}

		return row;

	}

	public void remove(int index) {
		this.rows.remove(index);
		this.setRowCount(this.getRowCount());
	}

	public DataTable copy() {

		DataTable dt = new DataTable();

		dt.columnCount = this.getColumnCount();

		dt.columnNames = new String[dt.columnCount];

		for(int i= 0; i < this.columnCount; i++) {
			dt.columnNames[i] = this.columnNames[i];	//大文字
		}

		ArrayList<DataRow> list = new ArrayList<DataRow>();

		for(int i= 0; i < this.getRowCount(); i++) {

			DataRow row = new DataRow();

			for(int n = 0; n < dt.columnCount; n++) {
				row.put(dt.columnNames[n], this.getRows().get(i).get(dt.columnNames[n]));
			}

			list.add(row);
		}

		dt.rowCount = list.size();
		dt.rows = list;

		return dt;
	}
}
