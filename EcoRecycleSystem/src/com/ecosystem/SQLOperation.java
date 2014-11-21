package com.ecosystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.ListModel;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.ecosystem.SQLConnection;

public class SQLOperation {
	// Statement stmt = null;
	java.sql.Statement stmt;
	SQLConnection connection;
	java.sql.PreparedStatement prepareStmt;
	Connection con;
	java.sql.ResultSet rs;

	public SQLOperation() {
		connection = new SQLConnection();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/ecorecycledb", "root", "");

			stmt = (Statement) con.createStatement();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[] getLocation() {
		// Class.forName("com.mysql.jdbc.Driver");
		String location[] = new String[50];
		String rcmDetails = null;
		try {
			String sql = "SELECT DISTINCT location,machine_id FROM rcmsystem";
			prepareStmt = con.prepareStatement(sql);

			System.out.println(prepareStmt);
			rs = prepareStmt.executeQuery();
			// rs = stmt.executeQuery(sql);
			int i = 0;
			while (rs.next()) {
				System.out.println("yes....");
				System.out.println(rs.getString(1));
				System.out.println(rs.getInt(2));
				rcmDetails = "" + rs.getInt(2) + "-" + rs.getString(1) + "";
				location[i] = rcmDetails;
				i++;
			}
			// }
			rs.close();
			closeDB();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return location;
	}

	public void insertItem(String itemType, String itemPrice) {
		// TODO Auto-generated method stub
		try {
			String insertSql = "INSERT INTO itemtype  ( typename, price ) VALUES ('"
					+ itemType + "', " + itemPrice + ")";
			prepareStmt = con.prepareStatement(insertSql);
			System.out.println(prepareStmt);
			int recordId = prepareStmt.executeUpdate();
			System.out.println(recordId);

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
	}

	public void deleteItem(String itemType) {
		try {
			String deleteItemSql = "DELETE FROM itemtype WHERE typename = '"
					+ itemType + "'";
			prepareStmt = con.prepareStatement(deleteItemSql);
			System.out.println(prepareStmt);
			int recordId = prepareStmt.executeUpdate();
			System.out.println(recordId);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
	}

	public void closeDB() {
		try {
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String[] getItemType() {
		// TODO Auto-generated method stub
		String itemType[] = new String[50];
		try {
			String sql = "SELECT typename FROM itemtype";
			prepareStmt = con.prepareStatement(sql);

			System.out.println(prepareStmt);
			rs = prepareStmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				System.out.println(rs.getString(1));
				String type = rs.getString(1);
				itemType[i] = type;
				i++;
			}
			// }
			rs.close();
			// closeDB();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemType;
		// return null;
	}

	public String[] getRCMDetails() {
		// TODO Auto-generated method stub
		String rcmDetails[] = new String[50];
		try {
			String sql = "SELECT machine_id,location FROM rcmsystem";
			prepareStmt = con.prepareStatement(sql);
			System.out.println(prepareStmt);
			rs = prepareStmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				// System.out.println(rs.getInt(1));
				// System.out.println(rs.getString(2));
				String RCMDetailStr = "" + rs.getInt(1) + " - "
						+ rs.getString(2) + "";
				rcmDetails[i] = RCMDetailStr;
				i++;
			}
			// }
			rs.close();
			// closeDB();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rcmDetails;
		// return null;
	}

	public void deleteRCM(String id) {
		// TODO Auto-generated method stub
		java.sql.PreparedStatement preStmtm;
		try {
			String deleteSql = "DELETE FROM statistics WHERE machine_id = " +id + "";
			preStmtm = con.prepareStatement(deleteSql);
			System.out.println(preStmtm);
			int recId = preStmtm.executeUpdate();
			if(recId > 0){
				String deleteRCMSql = "DELETE FROM rcmsystem WHERE  machine_id = '"
						+ id + "'";
				prepareStmt = con.prepareStatement(deleteRCMSql);
				System.out.println(prepareStmt);
				int recordId = prepareStmt.executeUpdate();
				System.out.println(recordId);	
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// closeDB();
	}

	public String[] getRCMUpdateDetails() {
		// TODO Auto-generated method stub
		String rcmDetails[] = new String[50];
		try {
			String sql = "SELECT * FROM rcmsystem";
			prepareStmt = con.prepareStatement(sql);
			System.out.println(prepareStmt);
			rs = prepareStmt.executeQuery();
			int i = 0;
			System.out.println("here");
			while (rs.next()) {
				System.out.println("here");
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getString(4));
				System.out.println(rs.getString(5));
				System.out.println(rs.getInt(6));
				System.out.println(rs.getInt(7));
				String RCMDetailStr = "" + rs.getInt(1) + " - "
						+ rs.getString(2) + " - " + rs.getString(2) + "-";
				rcmDetails[i] = RCMDetailStr;
				i++;
			}
			// }
			rs.close();
			// closeDB();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rcmDetails;
	}

	public String getSelectedRcmDetails(String rcmId) {
		// TODO Auto-generated method stub
		String rcmDetails[] = new String[50];
		String RCMDetailString = new String();
		try {
			String sql = "SELECT location,capacity,amount,type1,type2,type3 FROM rcmsystem where machine_id = "
					+ rcmId + "";
			prepareStmt = con.prepareStatement(sql);
			System.out.println(prepareStmt);
			rs = prepareStmt.executeQuery();
			// int i = 0;
			System.out.println("here");
			while (rs.next()) {
				/*
				 * System.out.println("here");
				 * System.out.println(rs.getString(1));
				 * System.out.println(rs.getString(2));
				 * System.out.println(rs.getString(3));
				 * System.out.println(rs.getString(4));
				 * System.out.println(rs.getString(5));
				 * System.out.println(rs.getString(6));
				 */

				RCMDetailString = "" + rs.getString(1) + " - "
						+ rs.getString(2) + " - " + rs.getString(3) + " - "
						+ rs.getString(4) + " - " + rs.getString(5) + " - "
						+ rs.getString(6) + "";
				System.out.println(RCMDetailString);
				// rcmDetails[i] = RCMDetailStr;
				// i++;
			}
			// }
			rs.close();
			// closeDB();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RCMDetailString;

	}

	public void updateRCM(String rcmId, String loc, String capacity,
			String amt, String type1, String type2, String type3) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE rcmsystem SET location = '" + loc
					+ "', type1 = '" + type1 + "', type2 = '" + type2
					+ "', type3 = '" + type3 + "', capacity = '" + capacity
					+ "', amount = '" + amt + "' WHERE machine_id = " + rcmId
					+ "";
			prepareStmt = con.prepareStatement(sql);
			System.out.println(prepareStmt);
			int rowId = prepareStmt.executeUpdate();
			// int i = 0;
			System.out.println("here");
			System.out.println(rowId); // }
			rs.close();
			// closeDB();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return RCMDetailString;

	}

	public String getItemTypeDetails(String selectedType) {
		// TODO Auto-generated method stub
		String itemTypeDetailString = null;
		try {
			String sql = "SELECT price FROM itemtype WHERE typename = '"
					+selectedType+ "'";
			prepareStmt = con.prepareStatement(sql);
			System.out.println(prepareStmt);
			rs = prepareStmt.executeQuery();
			while (rs.next()) {

				System.out.println("here");
				System.out.println(rs.getInt(1));

				itemTypeDetailString = "" + rs.getInt(1) + "";
			}

			rs.close();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemTypeDetailString;
	}

	public int updateItemType(String selectedType, String price) {
		// TODO Auto-generated method stub
		int rowId = 0;
		try {
			String sql = "UPDATE itemType SET price = '" + price
					+ "' WHERE typename = '" + selectedType + "'";
			prepareStmt = con.prepareStatement(sql);
			System.out.println(prepareStmt);
			rowId = prepareStmt.executeUpdate();
			// int i = 0;
			System.out.println("here");
			System.out.println(rowId); // }
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowId;
	}

	public int addRcm(String loc, String cap, String amt, ListModel types) {
		// TODO Auto-generated method stub
		String type1 = (String) types.getElementAt(0);
		String type2 = (String) types.getElementAt(1);
		String type3 = (String) types.getElementAt(2);
		int recordId = 0;
		try {
			String insertSql = "INSERT INTO rcmsystem (location,type1,type2,type3,capacity,amount,rcmstatus) VALUES ('"
					+ loc
					+ "','"
					+ type1
					+ "','"
					+ type2
					+ "','"
					+ type3
					+ "','" + cap + "','" + amt + "','operational')";
			prepareStmt = con.prepareStatement(insertSql);
			System.out.println(prepareStmt);
			recordId = prepareStmt.executeUpdate();
			System.out.println(recordId);

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recordId;
	}

	public String getRCMStatusDetails(String rcmId) {
		// TODO Auto-generated method stub

		String itemTypeDetailString = null;
		int availableCapacity = 0;
		int totalAmtReturned = 0;
		String loc = null;
		String available_amount = null;
		String status = null;
		Date lastEmptied = null;
		int type1Count = 0;
		int type2Count = 0;
		int type3Count = 0;

		String onlyPositive = null;
		java.sql.PreparedStatement pStmt;

		try {
			String sql = "SELECT SUM(total_amount),SUM(total_weight),(SELECT location FROM rcmsystem WHERE machine_id = "
					+ rcmId
					+ ")AS location,(SELECT capacity FROM rcmsystem WHERE machine_id = "
					+ rcmId
					+ ") AS capacity, (SELECT amount FROM rcmsystem WHERE machine_id = "
					+ rcmId
					+ ") AS initail_amount,  (SELECT rcmstatus FROM rcmsystem WHERE machine_id = "
					+ rcmId
					+ "), MAX(lastemptied) FROM statistics WHERE machine_id = "
					+ rcmId + "";
			prepareStmt = con.prepareStatement(sql);
			System.out.println(prepareStmt);
			rs = prepareStmt.executeQuery();
			while (rs.next()) {

				System.out.println("here");
				
				availableCapacity = (rs.getInt(4)) - (rs.getInt(2));
			
				System.out.println(availableCapacity);
				totalAmtReturned = rs.getInt(1);
				System.out.println(totalAmtReturned);
				loc = rs.getString(3);
				System.out.println(loc);
				int test = (rs.getInt(5)) - (rs.getInt(1));
				available_amount = ""+test;
				if((available_amount).equals("-")){
					String[] seperateAmount = available_amount.split("-");
					
					onlyPositive = seperateAmount[1];
					System.out.println(seperateAmount[1]);
				}
				System.out.println(available_amount);
				
				status = rs.getString(6);
				System.out.println(status);
				// int type1Count = rs.getInt(3);
				// System.out.println(type1Count);
				/*
				 * int type2Count = rs.getInt(4); int type3Count = rs.getInt(5);
				 */
				lastEmptied = rs.getDate(7);
				System.out.println(lastEmptied);
			}

			itemTypeDetailString = "" + availableCapacity + " - " + status
					+ " - " + totalAmtReturned + " - "  + loc + " - "
					+ onlyPositive + " - " + lastEmptied + "";

			rs.close();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemTypeDetailString;

		// return null;
	}

	public String getSelectedRcmItemTypes(String rcmId) {
		String itemTypeDetailString = null;
		try {
			String sql = "SELECT type1,type2,type3 FROM rcmsystem WHERE  machine_id = "
					+ rcmId + "";
			prepareStmt = con.prepareStatement(sql);
			System.out.println(prepareStmt);
			rs = prepareStmt.executeQuery();
			int i = 0;
			while (rs.next()) {

				System.out.println("here");
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));

				itemTypeDetailString = "" + rs.getString(1) + "-"
						+ rs.getString(2) + "-" + rs.getString(3) + "";
			}

			rs.close();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemTypeDetailString;

		// return null;
	}

	public String getRcmAmtWeight(String rcmId) {
		// TODO Auto-generated method stub
		String itemTypeDetailString = "";
		try {
			String sql = "SELECT SUM(total_weight),SUM(total_amount),(SELECT capacity FROM rcmsystem WHERE machine_id = "
					+ rcmId
					+ "), (SELECT amount FROM rcmsystem WHERE machine_id =  "
					+ rcmId
					+ ") FROM statistics where machine_id = "
					+ rcmId + "";
			prepareStmt = con.prepareStatement(sql);
			System.out.println(prepareStmt);
			rs = prepareStmt.executeQuery();
			int i = 0;
			while (rs.next()) {

				System.out.println("here");
				System.out.println(rs.getInt(1));
				System.out.println(rs.getInt(2));

				itemTypeDetailString = "" + rs.getInt(1) + "-" + rs.getInt(2)
						+ "-" + rs.getInt(3) + "-" + rs.getInt(4) + "";
			}
			rs.close();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemTypeDetailString;

	}

	public int rcmInsertItem(String rcmId, int total_amount_due,
			int weightInserted, String recyclableType) {
		// TODO Auto-generated method stub
		int recordId = 0;
		try {
			String insertSql = "INSERT INTO statistics  ( machine_id,total_amount,total_weight,itemtype ) VALUES ('"
					+ rcmId
					+ "', "
					+ total_amount_due
					+ ", "
					+ weightInserted
					+ ", '" + recyclableType + "')";
			prepareStmt = con.prepareStatement(insertSql);
			System.out.println(prepareStmt);
			recordId = prepareStmt.executeUpdate();
			System.out.println(recordId);

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println("some exception");
		}

		return recordId;

	}

	public void insertLastEmptiedDate(String rcmId) {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date currentDate = new Date();
		String thisdate = dateFormat.format(currentDate);
		System.out.println(dateFormat.format(currentDate));
		System.out.println(currentDate);
		try {
			String insertSql = "INSERT INTO statistics (machine_id,lastemptied) VALUES ('"
					+ rcmId + "', '" + thisdate + "')";
			prepareStmt = con.prepareStatement(insertSql);
			System.out.println(prepareStmt);
			prepareStmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
// }
